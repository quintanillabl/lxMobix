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

        if(det.cuentaContable==null)
            det.cuentaContable=gasto.cuentaContable
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

    def importar(File xmlFile){
        def xml = new XmlSlurper().parse(xmlFile)
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        if(xml.name()=='Comprobante'){
            def data=xml.attributes()
            log.debug 'Comprobante:  '+xml.attributes()  
            def receptor=xml.breadthFirst().find { it.name() == 'Receptor'}
            def cuenta=CuentaContable.findByClave('600')
            assert cuenta,'No se ha declarado la cuenta general de gastos'
            if('Receptor'==receptor.name()){
                def empresa=Empresa.findByRfc(receptor.attributes()['rfc'])
                log.debug 'Importando Gasto/Compra para '+empresa
                
                def emisorNode= xml.breadthFirst().find { it.name() == 'Emisor'}
                
                if('Emisor'==emisorNode.name()){
                    emisorNode.breadthFirst().each{
                        log.debug it.name()
                    }
                    def nombre=emisorNode.attributes()['nombre']
                    def rfc=emisorNode.attributes()['rfc']
                    
                    def proveedor=Proveedor.findByRfc(rfc)
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
                    if(gasto==null){
                        gasto=new Gasto(
                            empresa:empresa,
                            proveedor:proveedor,
                            cuentaContable:cuenta,
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
                            cfdiXml:xmlFile.getBytes()
                        )
                        def traslados=xml.breadthFirst().find { it.name() == 'Traslados'}
                        traslados.children().each{ t->
                            //println it.name()+ ' Val: '+it.attributes() 
                            if(t.attributes()['impuesto']=='IVA'){
                                println 'IVA: '+t.name()+ ' Val: '+t.attributes() 
                                def tasa=t.attributes()['tasa'] as BigDecimal
                                gasto.impuestoTasa=tasa
                                gasto.impuesto=t.attributes()['importe'] as BigDecimal
                            }
                        }
                        
                        def conceptos=xml.breadthFirst().find { it.name() == 'Conceptos'}
                        conceptos.children().each{
                            def model=it.attributes()
                            println model
                            def det=new GastoDet(
                                cuentaContable:cuenta,
                                concepto:'GASTO',
                                descripcion:model['descripcion'],
                                unidad:model['unidad'],
                                cantidad:model['cantidad'],
                                valorUnitario:model['valorUnitario'],
                                importe:model['importe'],
                                comentario:"Concepto importado  ${xmlFile.name}"
                            )
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

    def Acuse validarEnElSat(Gasto gasto){
        try {
            def emisor=gasto.proveedor.rfc
            def receptor=gasto.empresa.rfc
            DecimalFormat format=new DecimalFormat("####.000000")
            String stotal=format.format(gasto.total)
            String qq="?re=$emisor&rr=$receptor&tt=$stotal&id=$gasto.uuid"
            log.debug 'Validando en SAT Expresion impresa: '+qq
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

}
