package com.luxsoft.cfdi.retenciones

import grails.transaction.Transactional

import org.xml.sax.helpers.DefaultHandler
import javax.xml.XMLConstants
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory
import javax.xml.transform.stream.StreamSource

import com.luxsoft.cfdi.retenciones.ObjectFactory
import com.luxsoft.cfdi.retenciones.Retenciones
import com.luxsoft.lx.core.Folio

@Transactional
class CfdiRetencionesService {

	def grailsApplication

    def retencionesBuilder

    def retencionesTibrador

    def retencionSellador

    def retencionesCadenaBuilder

    def save(RetencionesCommand command){
        def retenciones=new CfdiRetenciones(
            folio:nextFolio(command.empresa),
            emisor:command.empresa.nombre,
            emisorRfc:command.empresa.rfc,
            receptor:command.receptor,
            receptorRfc:command.receptorRfc,
            receptorCurp:command.receptorCurp,
            receptorNacionalidad:command.nacional?'Nacional':'Extranjero',
            receptorRegistroTributario:command.registroTributario,
            fecha:command.fecha,
            tipoDeRetencion:command.tipoDeRetencion,
            retencionDescripcion:command.retencionDescripcion,
            mesInicial:command.mesInicial,
            mesFinal:command.mesFinal,
            ejercicio:command.ejercicio,
            total:command.total,
            totalGravado:command.totalGravado,
            totalExcento:command.totalExcento,
            totalRetenido:command.totalRetenido
        )
        retenciones.save failOnError:true,flush:true
    }


    def generarXml(CfdiRetenciones retenciones){
        retenciones=retencionesBuilder.buildXml(retenciones)
        return retenciones

    }

    /*
    def  toXml(CfdiRetenciones bean){
        JAXBContext context = JAXBContext.newInstance(Retenciones.class);

        Unmarshaller u = context.createUnmarshaller()
        ByteArrayInputStream is=new ByteArrayInputStream(bean.xml)
        Object o = u.unmarshal( is )
        Retenciones retenciones=(Retenciones)o

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ByteArrayOutputStream out=new ByteArrayOutputStream()
        m.marshal(retenciones,out);

        return out.toString("UTF-8");
    }
    */

    

    def timbrar(CfdiRetenciones bean){
        assert bean.xml,' No se ha generado el xml para este comprobante :'+bean
        //Sello digital
        //log.info 'Timbrando documento de retencion y pagos: '+bean
        //String cadenaOriginal=retencionesCadenaBuilder.generarCadena(retenciones)
        
        bean=retencionesTibrador.timbrar(bean)
        return bean
        //redirec acion:'edit',id:bean.id
    }


    def buildCatalogoDeRetenciones(){
        def map=[:]
        map['01']='Servicios profesionales'
        map['02']='Regalías por derechos de autor'
        map['03']='Autotransporte terrestre de carga'
        map.each{k,v->
            TipoDeRetencion.findOrSaveWhere(clave:k,descripcion:v)
        }
    }

    def buildCatalogoDeImpuestos(){
        def map=[:]
        map['01']='ISR'
        map['02']='IVA'
        map['03']='IEPS'
        map.each{k,v->
            TipoDeImpuesto.findOrSaveWhere(clave:k,descripcion:v)
        }
    }

    private Long nextFolio(def empresa){
        def folio=Folio.findByEmpresaAndSerie(empresa,'CFDI_RETENCIONES')
        if(folio==null){
            folio=new Folio(empresa:empresa,serie:'CFDI_RETENCIONES',folio:0l)
        }
        def res=folio.next()
        folio.save()
        return res
    }


}
