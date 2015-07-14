package com.luxsoft.cfdi

import groovy.transform.ToString
import java.io.ByteArrayInputStream
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.TipoDeComprobante


@ToString(includeNames=true,includes="id,serie,folio,tipo,fecha,uuid")
class Cfdi {
	
	String serie
	String folio
	String uuid
	Date timbrado
	String tipo
	Date fecha
	String emisor
	String emisorRfc
	String receptor
	String receptorRfc
	
	BigDecimal total
	
	String cadenaOriginal
	//String origen
	String xmlName
	byte[] xml

	byte[] acuse
	String acuseEstado
	String acuseCodigoEstatus
	String grupo
	String referencia
	
	Date dateCreated
	Date lastUpdated

	String creadoPor
	String modificadoPor
	
	ComprobanteDocument comprobanteDocument
	TimbreFiscal timbreFiscal
	String comentario

	static hasOne = [cancelacion: CancelacionDeCfdi]

    static constraints = {
		serie blannk:false,maxSize:15
		folio blank:false,maxSize:20
		uuid nullable:true,maxSize:300
		timbrado(nullable:true)
		tipo inList:['INGRESO','EGRESO','TRASLADO']
		fecha nullable:false
		emisor blank:false,maxSize:600
		emisorRfc blank:false,maxSize:13
		receptor blank:false,maxSize:600
		receptorRfc blank:false,maxSize:13
		xmlName nullable:true,maxSize:200
		xml maxSize:(1024 * 512)  // 50kb para almacenar el xml
		cadenaOriginal maxSize:1024*64, nullable:true 
		//origen blank:false,maxSize:255
		cancelacion nullable:true
		creadoPor maxSize:50
		modificadoPor maxSize:50
		acuse nullable:true,maxSize:(1024*256)
		acuseEstado nullable:true
		acuseCodigoEstatus nullable:true
		grupo nullable:true,maxSize:20
		comentario nullable:true,maxSize:100
    }
	
	static transients = ['comprobanteDocument','timbreFiscal']
	
	public Cfdi() {}
	
	public Cfdi(Comprobante c) {
		serie=c.serie
		folio=c.folio
		fecha=c.fecha.getTime()
		emisor=c.emisor.nombre
		emisorRfc=c.emisor.rfc
		receptor=c.receptor.nombre
		receptorRfc=c.receptor.rfc
		total=c.total
	}
	
	Comprobante getComprobante(){
		getComprobanteDocument().getComprobante()
	}
	
	public ComprobanteDocument getComprobanteDocument(){
		if(this.comprobanteDocument==null){
			loadComprobante()
		}
		return this.comprobanteDocument
	}

	public TimbreFiscal getTimbreFiscal(){
		if(!timbreFiscal)
			timbreFiscal=new TimbreFiscal(getComprobante())
		return timbreFiscal
	}
	
	void loadComprobante(){
		ByteArrayInputStream is=new ByteArrayInputStream(getXml())
		this.comprobanteDocument=ComprobanteDocument.Factory.parse(is)
	}

	void cargarXml(byte[] data){
		ByteArrayInputStream is=new ByteArrayInputStream(data)
		this.comprobanteDocument=ComprobanteDocument.Factory.parse(is)
		def c=getComprobante()
		this.xml=data
		this.uuid=getTimbreFiscal().UUID
		this.serie=c.serie
		this.folio=c.folio
		this.fecha=c.fecha.getTime()
		this.emisor=c.emisor.nombre
		this.emisorRfc=c.emisor.rfc
		this.receptor=c.receptor.nombre
		this.receptorRfc=c.receptor.rfc
		this.total=c.getTotal()
		//this.tipo=c.getTipoDeComprobante()==TipoDeComprobante.EGRESO?:'EGRESO'
		switch(c.getTipoDeComprobante()){
			case TipoDeComprobante.EGRESO:
				this.tipo='EGRESO'
				break
			case TipoDeComprobante.INGRESO:
				this.tipo='INGRESO'
				break
			case TipoDeComprobante.TRASLADO:
				this.tipo='TRASLADO'
				break	
		}

	}
	
	String toString(){
		return "($emisor) Id:$id  Tipo:$tipo Serie:$serie Folio:$folio  UUID:$uuid xmlName:$xmlName"
	}
}
