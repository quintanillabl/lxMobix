package com.luxsoft.lx.core

import grails.validation.Validateable
import groovy.transform.ToString
import com.luxsoft.utils.Periodo


@ToString(includes='ejercicio,mes',includeNames=true,includePackage=false)
@Validateable(nullable=true)
class PeriodoOperativo implements Serializable{
	
	Integer ejercicio=Periodo.currentYear()
    Integer mes=Periodo.currentMes()

    static constraints={
        ejercicio inList:(2014..2030)
        mes inList:(1..12)
    }

    String toString(){
    	return "$mes - $ejercicio "
    }

    Periodo toPeriodo(){
        return Periodo.getPeriodoEnUnMes(mes-1,ejercicio)
    }


	
}