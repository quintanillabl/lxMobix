package com.luxsoft.lx.tesoreria


import com.luxsoft.lx.core.Empresa
import org.grails.databinding.BindingFormat

class Comision {

	Empresa empresa

	@BindingFormat('dd/MM/yyyy')
	Date fecha

	CuentaBancaria cuenta

	BigDecimal comision

	BigDecimal impuestoTasa

	BigDecimal impuesto

	String comentario

	String referenciaBancaria
	
	static hasMany =[movimientos:MovimientoDeCuenta]

    static constraints = {
		comentario(nullable:true,maxSize:200)
		referenciaBancaria(nullable:true,maxSize:100)
    }
	
	static mapping ={
		movimientos cascad:"all-delete-orphan"
	}
	
	
	
	// def generarMovimientos(){
		
		
		
	// }
	
	// def beforeInsert(){
	// 	generarMovimientos()
	// }
}

