package com.luxsoft.lx.tesoreria

import com.luxsoft.lx.core.FormaDePago
import com.luxsoft.lx.cxp.Requisicion


class Pago extends MovimientoDeCuenta{

	Requisicion requisicion

	FormaDePago formaDePago

	static hasMany = [aplicaciones: AplicacionDePago]


    static constraints = {
    	formaDePago inList:[FormaDePago.TRANSFERENCIA,FormaDePago.CHEQUE,FormaDePago.EFECTIVO]
    }
    
    static embedded = ['autorizacion']

    String toString(){
		return "$formaDePago Folio:$folio ${cuenta?.numero}   ${importe}"
	}

	BigDecimal getAplicado(){
		if(aplicaciones==null)
			return 0.0
		return aplicaciones.sum(0.0,{it.importe}).abs()
	}

	BigDecimal getDisponible(){
		return  importe-getAplicado()
	}
}
