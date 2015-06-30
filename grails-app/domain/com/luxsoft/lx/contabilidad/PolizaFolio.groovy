package com.luxsoft.lx.contabilidad

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import com.luxsoft.lx.core.Empresa

@EqualsAndHashCode(includes='empresa,ejercicio,mes,tipo,folio')
@ToString(includes='empresa,ejercicio,mes,tipo,folio',includeNames=true,includePackage=false)
class PolizaFolio {

	Empresa empresa
	Integer ejercicio
	Long mes
	String tipo
	Long folio=0

	Date dateCreated
	Date lastUpdated

    static constraints = {
		tipo maxSize:10
		mes inList:(1..12)  
		folio nullable:false,unique:['empresa','tipo','mes']
    }
	
	Long next(){
		folio++
		return folio
	}
	
	String toString(){
		return "${emresa.clave} (${ejercicio} ${mes}) $tipo - $folio"
	}

   
}
