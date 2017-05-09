package com.luxsoft.nomina

import grails.transaction.Transactional
import java.math.RoundingMode

import org.bouncycastle.util.encoders.Base64
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante


import com.luxsoft.cfdi.Cfdi
import com.luxsoft.lx.core.Empresa

@Transactional
class NominaAsimiladoService {

	def nominaCfdiBuilder

	def cfdiSellador 

	def cfdiTimbrador

	def springSecurityService

    NominaAsimilado save(NominaAsimilado nomina){
    	if(nomina.id && nomina.partidas){
    		nomina.partidas.clear()
    	}
    	registrarPercepciones(nomina)
    	registrarDeducciones(nomina)
    	nomina.save failOnError:true, flush:true
    }


    def registrarPercepciones(NominaAsimilado nomina){
    	if(nomina.percepciones > 0){
	    	nomina.addToPartidas(
				clave:'P036', 
				claveSat:'016', 
				descripcion: nomina.concepto, 
				tipo: 'PERCEPCION',
				importeGravado: nomina.percepciones
				)
    	}	
    	
    }

    def registrarDeducciones(NominaAsimilado nomina){
    	if(nomina.percepciones > 0){

    		def impuesto = calcularImpuesto(nomina.percepciones, nomina.fecha.toYear())

	    	nomina.addToPartidas(
				clave:'D002', 
				claveSat:'002', 
				descripcion: 'ISTP', 
				tipo: 'DEDUCCION',
				importeExcento: impuesto
				)
    	}
    }
    

	def calcularImpuesto(BigDecimal percepciones, Integer ejercicio){
		if(percepciones<=0.0)
			return 0.0
		
		def tarifa = TarifaIsr.where {ejercicio == ejercicio}.list([sort:'porcentaje', order: 'desc']).get(0)
		
		def importeGravado = percepciones * tarifa.porcentaje
		importeGravado /= 100
		importeGravado = importeGravado.setScale(2,RoundingMode.HALF_EVEN)
		return importeGravado

	}

	def generarCfdi(NominaAsimilado ne){

		assert !ne.cfdi , "Ya se genero el cfdi para la nomina de asimilado ${ne.id} "

		

		ComprobanteDocument document = generarXml(ne)
		Comprobante comprobante = document.getComprobante()

		def cfdi=new Cfdi(
			tipo: 'EGRESO',
			tipoDeCfdi: 'E' ,
			fecha: ne.fecha,
			serie: comprobante.serie,
			folio: comprobante.folio,
			origen: ne.id.toString(),
			emisor: comprobante.getEmisor().nombre,
			receptor: comprobante.receptor.nombre,
			emisorRfc: comprobante.getEmisor().rfc,
			receptorRfc: comprobante.receptor.rfc,
			importe: comprobante.total,
			descuentos: 0,
			subtotal: comprobante.subTotal,
			impuesto: 0.0,
			total: comprobante.total,
			referencia: ne.id.toString()
		)

		def user = springSecurityService.getCurrentUser().username
		cfdi.modificadoPor=user
		cfdi.creadoPor=user

		def empresa = ne.empresa

		comprobante.setSello(cfdiSellador.sellar(empresa.privateKey,document))
		byte[] encodedCert=Base64.encode(empresa.getCertificado().getEncoded())
		comprobante.setCertificado(new String(encodedCert))

		salvarXml(cfdi, document)
		cfdi.save failOnError:true, flush:true
		ne.cfdi = cfdi
		ne.save()
		return cfdi

	}

	Cfdi salvarXml(Cfdi cfdi, ComprobanteDocument document){

		ByteArrayOutputStream os=new ByteArrayOutputStream()
    	document.save(os, NominaUtils.getXmlOptions())
    	cfdi.xml = os.toByteArray()
		//cfdi.setXmlName("$cfdi.receptorRfc-$cfdi.serie-$cfdi.folio"+".xml")
		cfdi.setXmlName("$cfdi.receptorRfc-$cfdi.serie-$cfdi.folio"+".xml")
		return cfdi;
	}

	ComprobanteDocument generarXml(NominaAsimilado ne){
		NominaCfdiBuilder nominaCfdiBuilder = new NominaCfdiBuilder()
		ComprobanteDocument document =  nominaCfdiBuilder.build(ne)
		Comprobante comprobante = document.getComprobante()
		comprobante.folio = ne.id.toString()
		comprobante.serie = 'NOMINA12'
		//comprobante.sello = cfdiSellador.sellar(ne.nomina.empresa.privateKey,document)
		return document
	}

	def timbrar(NominaAsimilado ne){
		def cfdi = ne.cfdi
		cfdi = cfdiTimbrador.timbrar(cfdi, ne.empresa)
		cfdi.save failOnError:true
		return ne

	}
}
