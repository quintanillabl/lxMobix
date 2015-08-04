package com.luxsoft.lx.cxp

import com.luxsoft.lx.contabilidad.CuentaContable
import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
//import com.luxsoft.lx.utils.MonedaUtils

@ToString(includes='descripcion,cantidad,importe',includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='concepto,descripcion,cantidad,importe')
class GastoDet {

	CuentaContable cuentaContable
	String descripcion
	String unidad
	BigDecimal cantidad
	BigDecimal valorUnitario
	BigDecimal importe
	String comentario


    static constraints = {
    	valorUnitario(scale:4)
    	importe(scale:4)
    	unidad maxSize:20
    	comentario nullable:true
    }

    static belongsTo = [gasto: Gasto]

    def actualizarImportes(){
        importe=cantidad*valorUnitario
        return this
    }

}

