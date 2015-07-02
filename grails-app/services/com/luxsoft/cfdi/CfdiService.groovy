package com.luxsoft.cfdi

import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.CuentaPredial;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.TipoDeComprobante;


import org.apache.xmlbeans.XmlObject
import org.apache.xmlbeans.XmlOptions
import org.apache.xmlbeans.XmlValidationError

import org.apache.commons.lang.StringUtils
import org.bouncycastle.util.encoders.Base64
import java.text.DecimalFormat

import com.edicom.ediwinws.cfdi.client.CfdiClient
import org.bouncycastle.util.encoders.Base64
import com.edicom.ediwinws.service.cfdi.CancelaResponse
import grails.transaction.Transactional

import org.apache.commons.lang.exception.ExceptionUtils
import com.luxsoft.lx.ventas.*
import com.luxsoft.lx.core.Folio
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.utils.*
import com.luxsoft.mobix.core.Renta

@Transactional
class CfdiService {

	def springSecurityService
	def grailsApplication
	def cfdiSellador
	def cfdiTimbrador

    def generar(Venta venta) {
    	
    	//throw new UnsupportedOperationException('No se ha implementado el servicio')
    	def empresa=venta.empresa
    	assert empresa,"La empresa debe estar definida"

		
		def serie=grailsApplication.config.luxor.series.ventas
		assert serie,"Debe registrar la serie para cfdi de ventas 'luxsoft.cfdi.serie.venta'"
		def folio=venta.folio
		log.info "Generando CFDI folio:$folio  Serie:$serie y rfc:$empresa.rfc  Para venta $venta.id"
		
		ComprobanteDocument document=ComprobanteDocument.Factory.newInstance()
		Comprobante comprobante=document.addNewComprobante()
		comprobante.serie=serie
		comprobante.folio=folio
		CfdiUtils.depurar(document)
		def fecha=new Date()
		
		comprobante.setVersion("3.2")
		comprobante.setFecha(CfdiUtils.toXmlDate(fecha).getCalendarValue())
		comprobante.setFormaDePago("PAGO EN UNA SOLA EXHIBICION")
		comprobante.setMetodoDePago(venta.formaDePago)
		comprobante.setMoneda(venta.moneda.getCurrencyCode())
		comprobante.setTipoCambio("1.0")
		
		comprobante.setTipoDeComprobante(TipoDeComprobante.INGRESO)
		comprobante.setLugarExpedicion(empresa.direccion.municipio)
		comprobante.setNoCertificado(empresa.numeroDeCertificado)
		
		Emisor emisor=CfdiUtils.registrarEmisor(comprobante, empresa)
		Receptor receptor=CfdiUtils.registrarReceptor(comprobante, venta.cliente)
		
		comprobante.setSubTotal(venta.subTotal)
		comprobante.setDescuento(venta.descuentoSinIva)
		comprobante.setTotal(venta.total)
		
		Impuestos impuestos=comprobante.addNewImpuestos()
		String rfc=comprobante.getReceptor().getRfc()
		
		//Facturacion a clientes extranjero
		if(rfc=="XEXX010101000"){
			comprobante.setSubTotal(venta.total)
			comprobante.setDescuento(venta.descuento)
			comprobante.setTotal(venta.total)
		}else if(rfc=="XAXX010101000"){
			comprobante.setSubTotal(venta.total)
			comprobante.setDescuento(venta.descuento)
			//comprobante.setDescuento(venta.descuento)
			comprobante.setTotal(venta.total)
		}else{
			impuestos.setTotalImpuestosTrasladados(venta.impuesto)
			Traslados traslados=impuestos.addNewTraslados()
			Traslado traslado=traslados.addNewTraslado()
			traslado.setImpuesto(Traslado.Impuesto.IVA)
			traslado.setImporte(venta.impuesto)
			traslado.setTasa(venta.impuestoTasa)
		}


		// 	c.setValorUnitario(det.precio);
		// 	c.setImporte(det.importe);
		// 	if(cliente.rfc=='XAXX010101000'){
		// 		c.setValorUnitario(det.calcularPercioConImpuesto())
		// 		c.setImporte(det.calcularImporteConImpuesto())
		// 	}
		// 	if(det.producto.cuentaPredial){
		// 		CuentaPredial cp=c.addNewCuentaPredial()
		// 		cp.setNumero(det.producto.cuentaPredial)
		// 	}
			
			
		// }
		
		Conceptos conceptos=comprobante.addNewConceptos()
		
		venta.partidas.each {det->

			Concepto c=conceptos.addNewConcepto()
			c.setCantidad(det.cantidad)
			c.setUnidad(det.producto.unidad)
			c.setNoIdentificacion(det.producto.clave)
			String desc =det.comentario+' '+ venta.comentario
			//desc = StringUtils.abbreviate(desc, 250)
			c.setDescripcion(desc)
			
			if(rfc=="XEXX010101000" || rfc=="XAXX010101000"){
				c.setValorUnitario(det.precio)
				c.setImporte(det.importeNeto+det.impuesto)
			} else{
				c.setValorUnitario(det.precio)
				c.setImporte(det.importeNeto)
			}

			def renta=Renta.findByVentaDet(det)
			if(renta){
				 CuentaPredial cp=c.addNewCuentaPredial()
				 cp.setNumero(renta.arrendamiento.inmueble.cuentaPredial)
			}
			
		}
		
		byte[] encodedCert=Base64.encode(empresa.getCertificado().getEncoded())
		comprobante.setCertificado(new String(encodedCert))
		assert cfdiSellador,'No se ha registrado el sellador'
		comprobante.setSello(cfdiSellador.sellar(empresa.privateKey,document))
		
		XmlOptions options = new XmlOptions()
		options.setCharacterEncoding("UTF-8")
		options.put( XmlOptions.SAVE_INNER )
		options.put( XmlOptions.SAVE_PRETTY_PRINT )
		options.put( XmlOptions.SAVE_AGGRESSIVE_NAMESPACES )
		options.put( XmlOptions.SAVE_USE_DEFAULT_NAMESPACE )
		options.put(XmlOptions.SAVE_NAMESPACES_FIRST)
		ByteArrayOutputStream os=new ByteArrayOutputStream()
		document.save(os, options)
		
		
		validarDocumento(document)
		log.debug 'ComprobanteDocument generado y validado: '+document
		
		def cfdi=new Cfdi(comprobante)
		cfdi.tipo="INGRESO"
		cfdi.referencia="FACTURA"
		//cfdi.origen=venta.id.toString()
		cfdi.xml=os.toByteArray()
		cfdi.setXmlName("$cfdi.receptorRfc-$cfdi.serie-$cfdi.folio"+".xml")
		//cfdi.cadenaOriginal
		
		log.debug 'ComprobanteDocument generado y validado: '+document
		def user=springSecurityService.getCurrentUser().username
		venta.modificadoPor=user
		cfdi.modificadoPor=user
		cfdi.creadoPor=user
		cfdi.save(failOnError:true)
		cfdi=cfdiTimbrador.timbrar(cfdi,empresa)
		venta.cfdi=cfdi
		log.debug 'Documento timbrado: '+cfdi
		try {
			event('ventaFacturada',venta)
		}
		catch(Exception e) {
			log.error(e)
		}
		return cfdi
    }

    void validarDocumento(ComprobanteDocument document) {
    	List<XmlValidationError> errores=findErrors(document);
    	if(errores.size()>0){
    		StringBuffer buff=new StringBuffer();
    		for(XmlValidationError e:errores){
    			buff.append(e.getMessage()+"\n");
    		}
    		throw new CfdiException(message:"Datos para generar el comprobante fiscal (CFDI) incorrectos "+buff.toString());
    	}
    }
    
    List findErrors(final XmlObject node){
    	final XmlOptions options=new XmlOptions();
    	final List errors=new ArrayList();
    	options.setErrorListener(errors);
    	node.validate(options);
    	return errors;
    	
    }

    def currentUser(){
    	def principal = springSecurityService.principal
      	return principal.username
    }

    @Transactional
	def CancelacionDeCfdi cancelar(Cfdi cfdi,String comentario){

		CancelacionDeCfdi cancel=new CancelacionDeCfdi()
		cancel.cfdi=cfdi

		def empresa=Empresa.findByRfc(cfdi.emisorRfc)
		if(!empresa){
			throw new CfdiException(message:"No localizo empresa $cfdi.emisorRfc del CFDI",cfdi:cfdi)
		}
		//byte[] pfxData=grailsApplication.mainContext.getResource("/WEB-INF/sat/gasoc.pfx").file.readBytes()
		byte[] pfxData=empresa.certificadoDigitalPfx
		String[] uuids=[cfdi.uuid]
		def client=new CfdiClient()
		def passwordPfx=empresa.passwordPfx
		if(!passwordPfx) throw new CfdiException(message:'No se ha registrado la clave de cancelacion PAC en la empresa: '+empresa,cfdi:cfdi)
		CancelaResponse res=client.cancelCfdi(
				empresa.usuarioPac
				, empresa.passwordPac
				, empresa.getRfc()
				, uuids
				, pfxData
				, passwordPfx);
		String msg=res.getText()
		println 'Message: '+ new String(msg)
		//cancel.message=Base64.decode(msg)
		String aka=res.getAck()
		//println 'Aka:'+aka

		cancel.aka=Base64.decode(aka.getBytes())
		cancel.save failOnError:true

		def venta=Venta.findByCfdi(cfdi)
		if(venta){
			venta.comentario="VENTA CANCELADA"
			venta.cancelada=true
			venta.partidas.clear()
			venta.actualizarImportes()
			cancel.comentario="Venta Origen: "+venta.id
			venta?.cfdi=null
			venta?.save()
			venta.modificadoPor=currentUser()
			/*
			if(venta.saldo==venta.total){
				venta.comentario="VENTA CANCELADA"
				venta.cancelada=true
				venta.partidas.clear()
				venta.actualizarImportes()
				venta.delete()
			}else{
				cancel.comentario="Venta Origen: "+venta.id
				venta?.cfdi=null
				venta?.save()
			}
			*/
		}
		return cancel

	}

	def cargarCombrobante(byte[] bytes,String fileName,String referencia,String grupo,String usuario) {

		try {
	        Cfdi cfdi=new Cfdi()
	        cfdi.xmlName=fileName
	        cfdi.cargarXml(bytes)
	        
	        def found=Cfdi.findByUuid(cfdi.uuid)
	        if(found){
	            throw new CfdiException(message:"UUID ya registrado $found.uuid",cfdi:cfdi)
	        }
	        cfdi.referencia=referencia
	        cfdi.grupo=grupo
	        cfdi.creadoPor=usuario
			cfdi.modificadoPor=usuario

	        cfdi.save (failOnError:true)
	        
	        //validarEnElSat(cfdi)
	        log.info "CFDI importado: "+cfdi.uuid
	        return cfdi    
		}
		catch(Exception e) {
			log.error e
	        e.printStackTrace()
			String msg=ExceptionUtils.getRootCauseMessage(e)
			log.info msg
			throw new CfdiException(message:msg)
		}
	}

	def Acuse validarEnElSat(Cfdi cfdi){
	    try {
	        def emisor=cfdi.comprobante.emisor.rfc
	        def receptor=cfdi.comprobante.receptor.rfc
	        DecimalFormat format=new DecimalFormat("####.000000")
	        String stotal=format.format(cfdi.comprobante.total)
	        String qq="?re=$emisor&rr=$receptor&tt=$stotal&id=$cfdi.uuid"
	        log.debug 'Validando en SAT Expresion impresa: '+qq
	        Acuse acuse=consultaService.consulta(qq)
	        cfdi.acuseEstado=acuse.getEstado().getValue().toString()
	        cfdi.acuseCodigoEstatus=acuse.getCodigoEstatus().getValue().toString()
	        registrarAcuse(cfdi,acuse)
	        cfdi.save()
	        return acuse
	    }
	    catch(Exception e) {
	        String msg=ExceptionUtils.getRootCauseMessage(e)
	        cfdi.acuseEstado='SIN VALIDAR'
	        cfdi.acuseCodigoEstatus=msg
	        //throw new CfdiException(message:msg,cfdi:cfdi)
	        //return null
	    }
	    
	
	}

}

// class CfdiException extends RuntimeException{
// 	String message
// 	Cfdi cfdi
// }
