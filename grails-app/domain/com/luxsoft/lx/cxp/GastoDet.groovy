package com.luxsoft.lx.cxp

import com.luxsoft.lx.contabilidad.CuentaContable
import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
//import com.luxsoft.lx.utils.MonedaUtils

@ToString(includes='concepto,descripcion,cantidad,importe',includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='concepto,descripcion,cantidad,importe')
class GastoDet {

	CuentaContable cuentaContable
	String concepto
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

/*
Concepto c=conceptos.addNewConcepto()
			c.setCantidad(det.cantidad)
			c.setUnidad(det.producto.unidad)
			c.setNoIdentificacion(det.producto.clave)
			String desc = det.producto.descripcion
			desc = StringUtils.abbreviate(desc, 250)
			c.setDescripcion(desc)
			if(rfc=="XEXX010101000" || rfc=="XAXX010101000"){
				c.setValorUnitario(det.precio)
				c.setImporte(det.importeNeto+det.impuesto)
			} else{
				c.setValorUnitario(det.precio)
				c.setImporte(det.importeNeto)
			}
			*/