package com.luxsoft.lx.tesoreria

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.cxp.CuentaPorPagar
import groovy.transform.EqualsAndHashCode


//@EqualsAndHashCode(includes='cuentaPorPagar')
class AplicacionDePago {

	CuentaPorPagar cuentaPorPagar

    @BindingFormat('dd/MM/yyyy')
	Date fecha

	BigDecimal importe

	String comentario

	//Datos de CFDI...
	String uuid
	String serie
	String folio
	byte[] cfdiXml
	String cfdiXmlFileName
	

	static belongsTo = [pago: Pago]

    static constraints = {
    	comentario nullable:true
    	uuid nullable:true,maxSize:40,unique:true
    	serie nullable:true,maxSize:20
    	folio nullable:true,maxSize:20
    	cfdiXmlFileName nullable:true,maxSize:200
		cfdiXml nullable:true,maxSize:(1024 * 512)  // 50kb para almacenar el xml
    }

    static mapping = {
		fecha type:'date'
		
	}
	
}
