package com.luxsoft.cfdi.retenciones

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.databinding.BindingFormat

//@ToString(includes='nombre',includeNames=true,includePackage=false)
//@EqualsAndHashCode(includes='nombre,rfc')
class CfdiRetenciones {

	/*
	* Folio interno
	*/
	String folio

	String emisor
	
	String emisorRfc

	String emisorCurp

	String receptor

	String receptorRfc

	String receptorCurp

	String receptorNacionalidad

	String receptorRegistroTributario

	@BindingFormat('dd/MM/yyyy')
	Date fecha

	TipoDeRetencion tipoDeRetencion

	String retencionDescripcion

	Integer mesInicial

	Integer mesFinal

	Integer ejercicio

	String comentario

	BigDecimal total
	BigDecimal totalGravado
	BigDecimal totalExcento
	BigDecimal totalRetenido

	// Campos nuevos para complemento de Dividendos
	String cveTipDivOUtil
	BigDecimal montoISRAcredRetMexico = 0.0
	BigDecimal montoDivAcumNal = 0.0

	List impuestosRetenidos=[]
	

	String xmlName
	byte[] xml

	String uuid
	String fechaDeTimbrado

	Date dateCreated
	Date lastUpdated

    static constraints = {
    	emisor maxSie:300
    	retencionDescripcion nullable:true
    	emisorCurp nullable:true, maxSie:50
    	receptorNacionalidad inList:['Nacional','Extranjero']
    	receptorCurp nullable:true,maxSize:50
    	receptorRegistroTributario nullable:true,maxSize:20
    	mesInicial size:1..12
    	mesFinal size:1..12
    	ejercicio size:2004..2024
    	comentario nullable:true
    	total scale:6,min:0.0
    	totalGravado scale:6
    	totalExcento scale:6
    	totalRetenido scale:6
    	
    	
    	xmlName nullable:true,maxSize:200
		xml nullable:true,maxSize:(1024 * 512)  // 50kb para almacenar el xml
		uuid nullable:true,maxSize:50
		fechaDeTimbrado nullable:true,maxSize:50

		montoISRAcredRetMexico nullable: true
		montoDivAcumNal nullable: true
		cveTipDivOUtil nullable: true
    }

    static hasMany = [impuestosRetenidos: ImpuestoRetenido]

    static mapping = {
		impuestosRetenidos cascade: "all-delete-orphan"
		totalRetenido formula:'(select ifnull(sum(x.monto_ret),0) from impuesto_retenido x where x.retencion_id=id)'
		//pagos formula:'(select ifnull(sum(x.importe),0) from aplicacion_de_cobro x where x.cuenta_por_cobrar_id=id)'
		
	}

	def toXml(){
		if(xml){
			ByteArrayInputStream xml=new ByteArrayInputStream(xml)
			InputStreamReader reader=new InputStreamReader(xml)
			return reader.getText()
		}
		else{
			return null
		}

	}


	static Map TiposDeDividendos = [
		'01': 'Proviende de CUFIN',
		'02': 'No providne de CUFIN',
		'03': 'Rembolso o reduccion de capital',
		'04': 'Liquidación de la persona moral',
		'05': 'CUFINRE',
		'06': 'Providne de CUFIN al 31 de diciembre 2013'
		/*
		[id: '01', descripcion: 'Proviende de CUFIN'],
		[id: '02', descripcion: 'No providne de CUFIN'],
		[id: '03', descripcion: 'Rembolso o reduccion de capital'],
		[id: '04', descripcion: 'Liquidación de la persona moral'],
		[id: '05', descripcion: 'CUFINRE'],
		[id: '06', descripcion: 'Providne de CUFIN al 31 de diciembre 2013']
		*/
	]




}
