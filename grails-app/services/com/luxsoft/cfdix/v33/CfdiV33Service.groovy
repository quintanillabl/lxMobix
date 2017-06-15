package com.luxsoft.cfdix.v33

import grails.transaction.Transactional
import java.text.SimpleDateFormat

import com.luxsoft.cfdi.Cfdi
import lx.cfdi.v33.Comprobante
import lx.cfdi.v33.CfdiUtils
import com.luxsoft.lx.ventas.Venta


@Transactional
public class CfdiV33Service {

	def springSecurityService

	final static SimpleDateFormat CFDI_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

	def generar(Venta venta){
		CfdiBuilder33  builder = new CfdiBuilder33()
		CfdiSellador33 sellador = new CfdiSellador33()
		Comprobante comprobante = builder.build(venta, 'FACTURA')
		comprobante = sellador.sellar(comprobante, venta.empresa)

		def cfdi = new Cfdi()
		cfdi.tipo="INGRESO"
		cfdi.referencia="FACTURA"
		cfdi.serie = comprobante.serie
		cfdi.folio = comprobante.folio
		cfdi.fecha = CFDI_DATE_FORMAT.parse(comprobante.fecha)
		cfdi.emisor = comprobante.emisor.nombre
		cfdi.emisorRfc = comprobante.emisor.rfc
		cfdi.receptor = comprobante.receptor.nombre
		cfdi.receptorRfc = comprobante.receptor.rfc
		cfdi.total = comprobante.total
		cfdi.versionCfdi = comprobante.version

		cfdi.xml = CfdiUtils.toXmlByteArray(comprobante)
		cfdi.setXmlName("$cfdi.receptorRfc-${'CFDIV33'}-$cfdi.serie-$cfdi.folio"+".xml")
		
		def user=springSecurityService.getCurrentUser().username
		venta.modificadoPor=user
		cfdi.modificadoPor=user
		cfdi.creadoPor=user
		cfdi.save(failOnError:true)
		
		venta.cfdi=cfdi
		//venta.save flush:true

		return cfdi

	}


	def timbrar(Cfdi cfdi){
		
	}

	def toXml(Comprobante comprobante){
		return CfdiUtils.serialize(comprobante)
	}

}