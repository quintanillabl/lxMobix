package com.luxsoft.lx.contabilidad


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import com.luxsoft.lx.utils.MonedaUtils
import com.luxsoft.lx.sat.BancoSat

@ToString(includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='cuentaOrigen,numero,fecha')
class PolizaCheque {

	String numero
	
	BancoSat bancoEmisorNacional

	String bancoEmisorExtranjero

	String cuentaOrigen

	Date fecha

	String beneficiario

	String rfc

	BigDecimal monto

	Currency moneda = MonedaUtils.PESOS

	BigDecimal tipoDeCambio = 1.0

    Date dateCreated
    
    Date lastUpdated


    static constraints = {
    	numero minSize:1,maxSize:20
    	bancoEmisorExtranjero nullable:true,maxSize:150
    	cuentaOrigen maxSize:50
    	beneficiario maxSize:300
    	rfc maxSize:13
    	monto scale:6
    	tipoDeCambio scale:5
    }

    static belongsTo = [polizaDet:PolizaDet]

    static mapping = {
    	fecha type:'date'
    }
}
