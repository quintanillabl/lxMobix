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
	
	
	
	def generarMovimientos(){
		
		addToMovimientos(
			empresa:this.empresa,
			cuenta:this.cuenta,
			fecha:this.fecha,
			moneda:this.cuenta.moneda,
			importe:comision.abs()*-1.0,
			concepto:"Comision "+comentario,
			referencia:this.referenciaBancaria,
			comentario:'COMISION_BANCARIA')

		if(getImpuestoMN().abs()>0){
			addToMovimientos(cuenta:this.cuenta
				,fecha:this.fecha
				,moneda:this.cuenta.moneda
				,importe:impuesto.abs()*-1.0
				,concepto:"Iva comision "+comentario
				,referencia:this.referenciaBancaria
				,comentario:'IMPUESTO_COMISION_BANCARIA')
		}
		
	}
	
	def beforeInsert(){
		generarMovimientos()
	}
}

