package com.luxsoft.lx.cxc

import org.grails.databinding.BindingFormat
import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import com.luxsoft.lx.core.FormaDePago
import com.luxsoft.lx.core.Cliente
import com.luxsoft.lx.tesoreria.Banco

@EqualsAndHashCode(includes='id,cliente')
@ToString(includes='cliente,total,disponible,formaDePago',includeNames=true,includePackage=false)
class Cobro {

	@BindingFormat('dd/MM/yyyy')
	Date fecha
	
	Cliente cliente

	FormaDePago formaDePago
	
	Banco banco
	
	String referencia
	
	BigDecimal importe=0.0
	
	BigDecimal aplicado=0.0
	
	BigDecimal disponible=0.0
	
	String comentario

	String usuario

	Boolean anticipo=false
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [aplicaciones: AplicacionDeCobro]

    static constraints = {
    	comentario nullable:true, maxSize:300
    	referencia nullable:true,maxSize:20
    	banco nullable:true,maxSize:30
    	usuario nullable:true
    	anticipo nullable:true
    }

    static mapping = {
		aplicaciones cascade: "all-delete-orphan"
	}

	static transients = ['disponible','aplicado']
	
	BigDecimal getAplicado(){
		//partidas.sum (0.0),{it.importeBruto}
		return aplicaciones.sum(0.0,{it.importe})
	}

	BigDecimal getDisponible(){
		return  importe-getAplicado()
	}

    
}

