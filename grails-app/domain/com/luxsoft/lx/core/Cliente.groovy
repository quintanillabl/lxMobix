package com.luxsoft.lx.core


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

@ToString(includes='nombre',includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='nombre,rfc')
class Cliente {

	String nombre
	String rfc
	String emailCfdi
	//TipoDeCliente tipo

	Direccion direccion
	Empresa empresa

	Date dateCreated
	Date lastUpdated

	static embedded = ['direccion']

    static constraints = {
    	direccion nullable:true
		nombre unique:true
		emailCfdi nullable:true
		rfc blank:false,minSize:12,maxSize:13
    }

    static mapping = {
		
	}

	 String toString(){
    	return "$nombre"
    }

    def beforeUpdate() {
    	capitalizarNombre()
    }

    def beforeInsert() {
    	capitalizarNombre()
    }

     private capitalizarNombre(){
    	nombre=nombre.toUpperCase()
    }

}
