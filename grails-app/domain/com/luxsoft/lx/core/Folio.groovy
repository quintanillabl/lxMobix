package com.luxsoft.lx.core

class Folio {
	
	Empresa empresa
	String serie
	Long folio=0

    static constraints = {
		serie blank:false,maxSize:10
		folio nullable:false
    }
	
	Long next(){
		folio++
		return folio
	}
	
	String toString(){
		return "$serie - $folio"
	}
}
