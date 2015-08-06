package com.luxsoft.lx.tesoreria

class Cheque {

	CuentaBancaria cuenta
	Integer folio=1
	Date impresion
	MovimientoDeCuenta egreso
	Date cancelacion
	String comentarioCancelacion

    static constraints = {
		impresion(nullable:true)
		cancelacion nullable:true
		comentarioCancelacion nullable:true
		folio min:1
    }
   
}
