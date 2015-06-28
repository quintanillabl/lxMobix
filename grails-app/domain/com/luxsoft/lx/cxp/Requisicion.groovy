package com.luxsoft.lx.cxp

import org.grails.databinding.BindingFormat
import groovy.transform.EqualsAndHashCode
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.core.Proveedor
import com.luxsoft.lx.core.Autorizacion

@EqualsAndHashCode(includes='empresa,proveedor,id')
class Requisicion {

	Empresa empresa
	
	Proveedor proveedor

	Long folio

	@BindingFormat('dd/MM/yyyy')
	Date pago

	Autorizacion autorizacion

	String tipo

	BigDecimal total=0.0
	
	String comentario

	Date dateCreated
	Date lastUpdated

	static hasMany = [partidas: RequisicionDet]

    static constraints = {
    	folio unique: 'empresa'
    	comentario nullable:true
    	autorizacion nullable:true
    	tipo inList:['GASTO','COMPRA']
    	total scale:4
    }

    static mapping = {
    	partidas cascade: "all-delete-orphan"
    	pago type:'date'
    }

    String toString(){
    	return "Folio:$folio Proveedor:${proveedor?.nombre} Total:${total}"
    }


}
