package com.luxsoft.cfdix

import grails.transaction.Transactional

import com.luxsoft.cfdi.Cfdi
import lx.cfdi.v33.Comprobante
import lx.cfdi.v33.pagos.PagosUtils
import com.luxsoft.cfdix.v33.CfdiPagoBuilder
import com.luxsoft.lx.cxc.Cobro



@Transactional
public class CfdiPagosService {

	def springSecurityService

	def generarCfdi(Cobro cobro){
		log.info 'Generar CFDI cobro'
		assert cobro.cfdi == null," Ya se ha generado el comprobante de pago"
		def builder = new CfdiPagoBuilder()
		def comprobante = builder.build(cobro)
	
		def cfdi = new Cfdi()
		cfdi.tipo="PAGO"
		cfdi.referencia="COBRO"
		cfdi.serie = comprobante.serie
		cfdi.folio = comprobante.folio
		cfdi.fecha = Date.parse("yyyy-MM-dd'T'HH:mm:ss",comprobante.fecha)
		cfdi.emisor = comprobante.emisor.nombre
		cfdi.emisorRfc = comprobante.emisor.rfc
		cfdi.receptor = comprobante.receptor.nombre
		cfdi.receptorRfc = comprobante.receptor.rfc
		cfdi.total = comprobante.total
		cfdi.versionCfdi = comprobante.version
		cfdi.xml = toXmlByteArray(comprobante)
		cfdi.setXmlName("$cfdi.receptorRfc-${'CFDIV33'}-$cfdi.serie-$cfdi.folio"+".xml")
		
		def user=springSecurityService.getCurrentUser().username
		cobro.modificadoPor=user
		cfdi.modificadoPor=user
		cfdi.creadoPor=user
		cfdi.save(failOnError:true)
		
		cobro.cfdi=cfdi
		
		return cobro

	}

	def toXmlByteArray(Comprobante comprobante){
		ByteArrayOutputStream os = new ByteArrayOutputStream()
		PagosUtils.getMarshaller()
		.marshal(comprobante, os)
		return os.toByteArray()

	}
}