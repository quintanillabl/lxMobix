package com.luxsoft.lx.cxc

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.ventas.Venta
import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='cuentaPorCobrar,fecha,importe,comentario')
@ToString(includes='cuentaPorCobrar,fecha,comentario',includeNames=true,includePackage=false)
class AplicacionDeCobro {

	CuentaPorCobrar cuentaPorCobrar 

    @BindingFormat('dd/MM/yyyy')
	Date fecha

	BigDecimal importe

	String comentario

	static belongsTo = [pago: Cobro]

    static constraints = {
    	comentario nullable:true
    }

    static mapping = {
		fecha type:'date'
		
	}
    
}
