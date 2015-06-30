package com.luxsoft.lx.contabilidad

import grails.validation.Validateable
import groovy.transform.ToString



@ToString(includes='ejercicio,mes',includeNames=true,includePackage=false)
@Validateable(nullable=true)
class PeriodoContable implements Serializable{
	
	Integer ejercicio
    Integer mes

    static constraints={
        ejercicio inList:(2015..2018)
        mes inList:(1..12)
    }

    String toString(){
    	return "$ejercicio - $mes"
    }


	
}