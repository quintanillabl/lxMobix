package com.luxsoft.lx.cxp

import org.grails.databinding.BindingFormat
import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode


import com.luxsoft.lx.core.Proveedor
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.lx.utils.MonedaUtils

@ToString(includes='proveedor,uuid,serie,folio',includeNames=true,includePackage=false)
@EqualsAndHashCode(includes='id')
class CuentaPorPagar {
	
	Empresa empresa
	Proveedor proveedor
	CuentaContable cuentaContable

	
	
	@BindingFormat('dd/MM/yyyy')
	Date fecha

	@BindingFormat('dd/MM/yyyy')
	Date vencimiento

	Currency moneda=Currency.getInstance('MXN')
	BigDecimal tipoDeCambio=1.0

	//Importes
	BigDecimal importe=0.0
	BigDecimal descuento=0.0
	BigDecimal subTotal=0.0

	BigDecimal impuestoTasa=MonedaUtils.IVA
	BigDecimal impuesto=0.0

	BigDecimal retensionIvaTasa=0
	BigDecimal retensionIva=0
	BigDecimal retensionIsrTasa=0
	BigDecimal retensionIsr=0
	
	BigDecimal total=0
	
	// Requisicion
	BigDecimal requisitado=0
	BigDecimal pendienteRequisitar=0

	//Saldo
	BigDecimal pagosAplicados=0
	
	BigDecimal totalMN=0
	BigDecimal saldoActual=0
	BigDecimal saldoAlCorte=0

	//Datos de CFDI...
	byte[] cfdiXml
	String cfdiXmlFileName
	String uuid
	String serie
	String folio

	byte[] acuse
	String acuseEstado
	String acuseCodigoEstatus

	String comentario

	Date dateCreated
	Date lastUpdated
	String creadoPor
	String modificadoPor	

    static constraints = {
		tipoDeCambio(scale:6)
		
		importe(scale:4)
		descuento(scale:4)
		subTotal(scale:4)

		impuesto(scale:4)
		total(scale:4)
		retensionIsr(scale:4)
		retensionIva(sacle:4)
		
		comentario(nullable:true)
    	uuid nullable:true,maxSize:40,unique:true
    	serie nullable:true,maxSize:20
    	folio nullable:true,maxSize:20
    	vencimiento (validator:{vencimiento,gasto->
    		if(vencimiento.compareTo(gasto.fecha)<0)
    			return "invalido"
    		else return true
    	})
    	cfdiXmlFileName nullable:true,maxSize:200
		cfdiXml nullable:true,maxSize:(1024 * 512)  // 50kb para almacenar el xml
		acuse nullable:true,maxSize:(1024*256)
		acuseEstado nullable:true,maxSize:100
		acuseCodigoEstatus nullable:true,maxSize:100
		
    }
	
	static mapping ={
		proveedor fetch:'join'
		requisitado formula:'(select ifnull(sum(x.requisitado),0) from requisicion_det x where x.cuenta_por_pagar_id=id)'
		//pagosAplicados formula:'(select ifnull(sum(x.total),0) from aplicacion x where x.factura_id=id)'
		fecha type:'date'
		vencimiento type:'date'
	}
	
	static transients = ['pendienteRequisitar','saldoActual','saldoAlCorte','pagosAplicados']
	
	
	
	
	BigDecimal getTotalMN(String property){
		return "${property}"*tc
		
	}
	
	public BigDecimal getPendienteRequisitar(){
		def req=requisitado?:0.0
		return total-req
	}
	
	public BigDecimal getSaldoActual(){
		def pag=pagosAplicados?:0.0
		return total-pag
	}
	
}
