package com.luxsoft.lx.contabilidad

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import com.luxsoft.lx.utils.MonedaUtils
import com.luxsoft.lx.sat.BancoSat

@ToString(excludes='dateCreated,lastUpdated,polizaDet',includeNames=true,includePackage=false)
@EqualsAndHashCode
class TransaccionTransferencia {

	String cuentaOrigen

	BancoSat bancoOrigenNacional

	String bancoOrigenExtranjero = ''

	String cuentaDestino
	
	BancoSat bancoDestinoNacional

	String bancoDestinoExtranjero

	Date fecha

	String beneficiario

	String rfc

	BigDecimal monto

	Currency moneda = MonedaUtils.PESOS

	BigDecimal tipoDeCambio = 1.0

    Date dateCreated
    
    Date lastUpdated

    static constraints = {
    	cuentaOrigen nullable:true, maxSize:50
    	cuentaDestino maxSize:50
    	cuentaDestino nullable:true, maxSize:50
    	bancoDestinoExtranjero nullable:true, maxSize:150
        bancoOrigenExtranjero nullable:true
    	beneficiario maxSize:300
    	rfc maxSize:13
    	monto scale:6
    	tipoDeCambio scale:5
    }

    static belongsTo = [polizaDet:PolizaDet]

    static mapping = {
    	//fecha type:'date'
    }
}
