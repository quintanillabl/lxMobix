package com.luxsoft.lx.contabilidad


import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import groovy.transform.ToString

@ToString(includes='cuenta,debe,haber,concepto,asiento,referencia,entidad,origen',includeNames=true,includePackage=false)
class PolizaDet {
	
	CuentaContable cuenta
	BigDecimal debe
	BigDecimal haber
	String concepto
	String descripcion
	String asiento
	String referencia
	String origen
	String entidad

	
	
	
	static belongsTo = [poliza:Poliza]

    static constraints = {
    	concepto(nullable:true,maxSize:50)
    	asiento nullable:true,maxSize:20
    	referencia nullable:true
		origen(nullable:true,maxSize:20)
		entidad(nullable:true,maxSize:50)
		descripcion(nullable:true,maxSize:255)
    }
	
	static mapping ={
		poliza fetch:'join'
	}
	
	boolean equals(Object obj){
		if(!obj.instanceOf(PolizaDet))
			return false
		if(this.is(obj))
			return true
		def eb=new EqualsBuilder()
		eb.append(id, obj.id)
		eb.append(id, obj.asiento)
		eb.append(id, obj.concepto)
		eb.append(id, obj.referencia)
		return eb.isEquals()
	}
	
	int hashCode(){
		def hb=new HashCodeBuilder(17,35)
		hb.append(id)
		hb.append(asiento)
		hb.append(concepto)
		hb.append(referencia)
		return hb.toHashCode()
	}
	
}

