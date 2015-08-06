package com.luxsoft.lx.tesoreria

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.cxp.CuentaPorPagar

class AplicacionDePago {

	CuentaPorPagar cuentaPorPagar

    @BindingFormat('dd/MM/yyyy')
	Date fecha

	BigDecimal importe

	String comentario

	static belongsTo = [pago: Pago]

    static constraints = {
    	comentario nullable:true
    }

    static mapping = {
		fecha type:'date'
		aplicaciones cascade: "all-delete-orphan"
		
	}
	
}
