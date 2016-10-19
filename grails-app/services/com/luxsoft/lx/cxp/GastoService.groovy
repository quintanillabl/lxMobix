package com.luxsoft.lx.cxp

import grails.transaction.Transactional
import com.luxsoft.lx.utils.MonedaUtils

import groovy.io.FileType
import org.apache.commons.io.IOUtils
import com.luxsoft.lx.core.*
import java.text.SimpleDateFormat
import com.luxsoft.lx.contabilidad.CuentaContable

import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller
import org.apache.commons.lang.exception.ExceptionUtils
import com.luxsoft.cfdi.Acuse
import java.text.DecimalFormat
import groovy.xml.*


@Transactional
class GastoService {

    def springSecurityService

    def  consultaService

    def save(Gasto gasto){
        gasto.with{
            def user=springSecurityService.getCurrentUser().username
            creadoPor=delegate.creadoPor?:user
            modificadoPor=user
            partidas=delegate.partidas?:[] as Set
        } 
        gasto.save failOnError:true
        return gasto
    }

    def update(Gasto gasto){
        gasto.with{
            modificadoPor=springSecurityService.getCurrentUser().username
        }
        actualizarImportes gasto
        gasto.save failOnError:true
        return gasto
    }

    def agregarConcepto(Gasto gasto,GastoDet det){

        //if(det.cuentaContable==null)
            //det.cuentaContable=gasto.cuentaContable
        if(!det.concepto)
            det.concepto=gasto.concepto
        gasto.addToPartidas(det)
        //actualizarImportes gasto
        //gasto.save failOnError:true
        update(gasto)
    }

    def eleiminarPartida(GastoDet det){
        Gasto gasto=det.gasto
        gasto.removeFromPartidas(det)
        // actualizarImportes()
        // gasto=gasto.save failOnError:true
        update(gasto)
    }

    def actualizarPartida(GastoDet det){
        Gasto gasto=det.gasto
        return update(gasto)
    }


    def cargarComprobanteFislcal(){

    }

    def actualizarImportes(Gasto gasto){
        if(gasto.partidas){
            gasto.with{
                partidas*.actualizarImportes()
                importe=partidas.sum(0.0,{it.importe}) 
                subTotal=importe-descuento
                if(impuestoTasa>1) impuestoTasa/=100
                impuesto=MonedaUtils.calcularImpuesto(subTotal,impuestoTasa?:MonedaUtils.IVA)

            }
            // gasto.partidas*.actualizarImportes()
            // gasto.importe=gasto.partidas.sum(0.0,{it.importe})
            // gasto.impuesto=MonedaUtils.calcularImpuesto(gasto.importe,impuesto?:MonedaUtils.IVA)
        }
        gasto.with{
            total=subTotal+impuesto
        }
    }

    def importar2(File xmlFile){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        def xml = new XmlSlurper().parse(xmlFile)
        def comprobante=xml.attributes()
    }

    def asignarCfdi(Gasto gasto,File xmlFile){
        def xml = new XmlSlurper().parse(xmlFile)
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        if(xml.name()!='Comprobante'){
            throw new GastoException(message:'Archivo XML no es un CFDI',gasto:gasto)
        }
        def data=xml.attributes()
        def receptor=xml.breadthFirst().find { it.name() == 'Receptor'}
        assert receptor.name()=='Receptor','Error en el archivo XML'
        def empresa=Empresa.findByRfc(receptor.attributes()['rfc'])
        if(empresa!=gasto.empresa){
            throw new GastoException(message:"El receptor (${receptor.attributes()['rfc']}) no corresponed a la empresa del gasto (${gasto.empresa.rfc})",gasto:gasto)
        }

        def serie=xml.attributes()['serie']
        def folio=xml.attributes()['folio']
        def fecha=df.parse(xml.attributes()['fecha'])
        def timbre=xml.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
        def uuid=timbre.attributes()['UUID']
        def subTotal=data['subTotal'] as BigDecimal
        
        gasto.fecha = fecha
        gasto.importe=data['subTotal'] as BigDecimal
        gasto.descuento=data['descuento'] as BigDecimal?:0.0
        gasto.subTotal=subTotal
        gasto.total=data['total'] as BigDecimal
        gasto.cfdiXmlFileName=xmlFile.name
        gasto.uuid=uuid
        gasto.serie=serie
        gasto.folio=folio
        gasto.cfdiXml=xmlFile.getBytes()

        def traslados=xml.breadthFirst().find { it.name() == 'Traslados'}
        
        if(traslados){
            traslados.children().each{ t->
                if(t.attributes()['impuesto']=='IVA'){
                    def tasa=t.attributes()['tasa'] as BigDecimal
                    gasto.impuestoTasa=tasa
                    gasto.impuesto=t.attributes()['importe'] as BigDecimal
                }
            }
        }
        def retenciones=xml.breadthFirst().find { it.name() == 'Retenciones'}
        
        if(retenciones){
            retenciones.breadthFirst().each{
                def map=it.attributes()
                if(map.impuesto=='IVA'){
                    def imp=map.importe as BigDecimal
                    def tasa=imp*100/subTotal
                    gasto.retensionIva=imp
                    gasto.retensionIvaTasa=tasa
                }
                if(map.impuesto=='ISR'){
                    def imp=map.importe as BigDecimal
                    def tasa=imp*100/subTotal
                    gasto.retensionIsr=imp
                    gasto.retensionIsrTasa=tasa
                }
            }    
        }
        

        gasto.save failOnError:true, flush:true

        return gasto

    }

    def importar(File xmlFile){
        def xml = new XmlSlurper().parse(xmlFile)
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        if(xml.name()=='Comprobante'){
            def data=xml.attributes()
            log.debug 'Comprobante:  '+xml.attributes()  
            def receptor=xml.breadthFirst().find { it.name() == 'Receptor'}
            def cuenta=CuentaContable.findByClave('600-0000')
            assert cuenta,'No se ha declarado la cuenta general de gastos'
            if('Receptor'==receptor.name()){
                def empresa=Empresa.findByRfc(receptor.attributes()['rfc'])

               // assert empresa,'No existe empresa para: '+receptor.name()+ ' RFC: '+receptor.attributes()['rfc']+ 'Archivo: '+xmlFile.name
                log.debug 'Importando Gasto/Compra para '+empresa
                
                def emisorNode= xml.breadthFirst().find { it.name() == 'Emisor'}
                if(empresa==null){
                    empresa=Empresa.findByRfc(emisorNode.attributes()['rfc'])
                    assert empresa,'No existe empresa que pueda registrar este CFDI'
                }
                
                if('Emisor'==emisorNode.name()){

                    def nombre=emisorNode.attributes()['nombre']
                    def rfc=emisorNode.attributes()['rfc']
                    
                    def proveedor=Proveedor.findByEmpresaAndRfc(empresa,rfc)
                    if(!proveedor){
                        log.debug "Alta de proveedor: $nombre ($rfc)"
                        def domicilioFiscal=emisorNode.breadthFirst().find { it.name() == 'DomicilioFiscal'}
                        def dom=domicilioFiscal.attributes()
                        def direccion=new Direccion(
                            calle:dom.calle,
                            numeroExterior:dom.noExterior,
                            numeroInterior:dom.noInterior,
                            colonia:dom.colonia,
                            municipio:dom.municipio,
                            estado:dom.estado,
                            pais:dom.pais,
                            codigoPostal:dom.codigoPostal)
                        proveedor=new Proveedor(nombre:nombre,rfc:rfc,direccion:direccion,empresa:empresa)
                        proveedor.save failOnError:true,flush:true
                        
                    }
                    def serie=xml.attributes()['serie']
                    def folio=xml.attributes()['folio']
                    def fecha=df.parse(xml.attributes()['fecha'])
                    def timbre=xml.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
                    def uuid=timbre.attributes()['UUID']
                    def gasto=Gasto.findByUuid(uuid)
                    def subTotal=data['subTotal'] as BigDecimal
                    if(gasto==null){
                        gasto=new Gasto(
                            empresa:empresa,
                            proveedor:proveedor,
                            fecha:fecha,
                            vencimiento:fecha+1,
                            importe:data['subTotal'] as BigDecimal,
                            descuento:data['descuento'] as BigDecimal?:0.0,
                            subTotal:data['subTotal'] as BigDecimal,
                            total:data['total'] as BigDecimal,
                            comentario:'Importacion de gasto',
                            xmlFileName:xmlFile.name,
                            uuid:uuid,
                            serie:serie,
                            folio:folio,
                            cfdiXml:xmlFile.getBytes(),
                            gastoPorComprobar: false
                        )
                        def traslados=xml.breadthFirst().find { it.name() == 'Traslados'}
                        if(traslados){
                            traslados.children().each{ t->
                                //println it.name()+ ' Val: '+it.attributes() 
                                if(t.attributes()['impuesto']=='IVA'){
                                    println 'IVA: '+t.name()+ ' Val: '+t.attributes() 
                                    def tasa=t.attributes()['tasa'] as BigDecimal
                                    gasto.impuestoTasa=tasa
                                    gasto.impuesto=t.attributes()['importe'] as BigDecimal
                                }
                            }
                        }
                        def retenciones=xml.breadthFirst().find { it.name() == 'Retenciones'}
                        if(retenciones){
                            retenciones.breadthFirst().each{
                                def map=it.attributes()
                                if(map.impuesto=='IVA'){
                                   def imp=map.importe as BigDecimal
                                   def tasa=imp*100/subTotal
                                   gasto.retensionIva=imp
                                   gasto.retensionIvaTasa=tasa
                                   
                                }
                                if(map.impuesto=='ISR'){
                                   def imp=map.importe as BigDecimal
                                   def tasa=imp*100/subTotal
                                   gasto.retensionIsr=imp
                                   gasto.retensionIsrTasa=tasa
                                }
                            }
                        }
                        
                        
                        def conceptos=xml.breadthFirst().find { it.name() == 'Conceptos'}
                        conceptos.children().each{
                            def model=it.attributes()
                            def det=new GastoDet(
                                cuentaContable:cuenta,
                                descripcion:model['descripcion'],
                                unidad:model['unidad'],
                                cantidad:model['cantidad'],
                                valorUnitario:model['valorUnitario'],
                                importe:model['importe'],
                                comentario:"Concepto importado  ${xmlFile.name}"
                            )
                            if(!gasto.partidas){
                                det.retencionIsr=gasto.retensionIsr
                                det.retencionIsrTasa=gasto.retensionIsrTasa
                                det.retencionIva=gasto.retensionIva
                                det.retencionIvaTasa=gasto.retensionIvaTasa
                            }
                            gasto.addToPartidas(det)

                        }
                        
                        gasto=save(gasto)
                        
                    }
                    else{
                        log.info 'Gasto ya registrado...'+uuid

                    }
                    return gasto
                }
            }

        }
        return null
    }

    def getXml(Gasto gasto){
        ByteArrayInputStream is=new ByteArrayInputStream(gasto.cfdiXml)
        def xml = new XmlSlurper().parse(is)
        return xml
    }

    def getCfdiXml(Gasto gasto){
        ByteArrayInputStream is=new ByteArrayInputStream(gasto.cfdiXml)
        def xml = new XmlSlurper().parse(is)
        return XmlUtil.serialize(xml)
    }

    def Acuse validarEnElSat(Gasto gasto){
        try {
            def emisor=gasto.proveedor.rfc
            def receptor=gasto.empresa.rfc
            def xml=getXml(gasto)
            def data=xml.attributes()
            def total=data['total']
            //DecimalFormat format=new DecimalFormat("####.000000")
            //String stotal=format.format(gasto.total)
            String qq="?re=$emisor&rr=$receptor&tt=$total&id=${gasto.uuid.toUpperCase()}"
            log.info 'Validando en SAT Expresion impresa: '+qq

            Acuse acuse=consultaService.consulta(qq)
            gasto.acuseEstado=acuse.getEstado().getValue().toString()
            gasto.acuseCodigoEstatus=acuse.getCodigoEstatus().getValue().toString()
            registrarAcuse(gasto,acuse)
            gasto.save()
            return acuse
        }
        catch(Exception e) {
            String msg=ExceptionUtils.getRootCauseMessage(e)
            gasto.acuseEstado='SIN VALIDAR'
            gasto.acuseCodigoEstatus=msg
        }
        
        
    }

    def  registrarAcuse(Gasto gasto,Acuse acuse){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ByteArrayOutputStream out=new ByteArrayOutputStream()
            m.marshal(acuse,out);
            gasto.acuse=out.toByteArray()
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    def  toXml(Acuse acuse){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter w=new StringWriter();
            m.marshal(acuse,w);
            return w.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    def  toAcuse(byte[] data){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class)
            Unmarshaller u = context.createUnmarshaller()
            ByteArrayInputStream is=new ByteArrayInputStream(data)
            Object o = u.unmarshal( is )
            return (Acuse)o
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    def corregirProveedores(){
        def gastos=Gasto.findAll().each{
            def proveedor=it.proveedor
            if(proveedor.empresa.id!=it.empresa.id){
                log.info "Corrigiendo proveedor para el gasto ${it}"
                def empresa=it.empresa
                def found=Proveedor.findByEmpresaAndRfc(empresa,proveedor.rfc)
                if(!found){
                    println "Dando de alta al proveedor ${proveedor.rfc} para la empresa:${empresa}"
                    def direccion=new Direccion(
                        calle:proveedor.direccion.calle,
                        numeroInterior:proveedor.direccion.numeroInterior,
                        numeroExterior:proveedor.direccion.numeroExterior,
                        colonia:proveedor.direccion.colonia,
                        municipio:proveedor.direccion.municipio,
                        codigoPostal:proveedor.direccion.codigoPostal,
                        estado:proveedor.direccion.estado,
                        pais:proveedor.direccion.pais
                        )
                    found=new Proveedor(
                        empresa:empresa,
                        nombre:proveedor.nombre,
                        direccion:direccion,
                        rfc:proveedor.rfc,
                        nacional:proveedor.nacional,
                        email:proveedor.email,
                        www:proveedor.www
                    )
                    found.save flush:true
                }
                it.proveedor=found
                it.save flush:true
            }
        }
    }

}

class GastoException extends RuntimeException{
    String message
    Gasto gasto
}
