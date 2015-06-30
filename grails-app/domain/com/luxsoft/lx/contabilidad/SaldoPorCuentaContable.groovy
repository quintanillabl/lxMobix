package com.luxsoft.lx.contabilidad

import com.luxsoft.lx.core.Empresa
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='empresa,cuenta,ejercicio,mes')
@ToString(excludes='dateCreated',includeNames=true,includePackage=false)
class SaldoPorCuentaContable {
	
	Empresa empresa
	CuentaContable cuenta
	Integer ejercicio
	Integer mes
	Date fecha
	
	BigDecimal debe
	BigDecimal haber
	BigDecimal saldoInicial
	BigDecimal saldoFinal
	Date cierre
	
	Date dateCreated
	Date lastUpdated

	static mapping = {
		fecha type:'date'
	}

    static constraints = {
    	ejercicio inList:(2015..2018)
		mes inList:(1..13)
		cuenta unique:['empresa','ejercicio','mes','cuenta']
		debe sacale:6
		haber sacale:6
		saldoInicial sacale:6
		saldoFinal sacale:6
		cierre nullable:true
    }
	
}
