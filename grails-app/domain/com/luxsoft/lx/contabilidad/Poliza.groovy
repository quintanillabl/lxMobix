package com.luxsoft.lx.contabilidad


import org.grails.databinding.BindingFormat
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import com.luxsoft.lx.utils.Mes
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.utils.MonedaUtils

@EqualsAndHashCode(includes='empresa,ejercicio,mes,tipo,folio')
@ToString(includes='empresa,ejercicio,mes,tipo,folio,debe,haber',includeNames=true,includePackage=false)
class Poliza {

	Empresa empresa

	Integer ejercicio

	Integer mes
	
	String tipo

	Integer folio

	@BindingFormat('dd/MM/yyyy')
	Date fecha

	String concepto

	BigDecimal debe=0.0

	BigDecimal haber=0.0

	String comentario

	Boolean manual=false

	List partidas=[]

	Date cierre

	Date dateCreated
	Date lastUpdated

	String creadoPor
	String modificadoPor
	
	static hasMany = [partidas:PolizaDet]

    static constraints = {
		ejercicio inList:(2015..2018)
		mes inList:(1..13)
		tipo(inList:['INGRESO','EGRESO','DIARIO','COMPRAS','CIERRE_ANUAL'])
		folio unique:['empresa','ejercicio','mes','tipo']
		debe(nullable:false,scale:6)
		haber(nullable:false,scale:6)
		concepto(maxSize:300)
		comentario nullable:true,maxSize:50
		creadoPor nullable:true,maxSize:50
		modificadoPor nullable:true,maxSize:50
		cierre nullable:true
    }
	
	
	static mapping ={
		partidas cascade: "all-delete-orphan"
	}
	
	static transients = {'cuadre'}
	
	def getCuadre(){
		return MonedaUtils.round(debe-haber)
	}
	
	
	
	def actualizar(){
		//if(!partidas) 
		debe=partidas.sum (0.0,{it.debe})
		haber=partidas.sum(0.0,{it.haber})
	}

	
	def beforeInsert(){
		actualizar()
	}
	
	def beforeUpdate(){
		actualizar()
	}
	
	// Pasar al service
	// def cuadrar(){
	// 	def dif=debe-haber
		
	// 	if(dif.abs()<=0.0)
	// 		return
	// 	if(dif.abs()<5.0){
			
	// 		//Otros productos/gastos
	// 		if(dif>0.0){
				
	// 			def cta=CuentaContable.findByClave("702-0003")
	// 			println 'Generando PRODUCTO en poliza: '+dif+' Cta: '+cta
	// 			//Producto
	// 			addToPartidas(
	// 				cuenta:CuentaContable.findByClave("702-0003"),
	// 				debe:0.0, 
	// 				haber:dif.abs(),
	// 				asiento:"OTROS INGRESOS "+tipo,
	// 				descripcion:"OTROS INGRESOS",
	// 				referencia:"",
	// 				fecha:fecha
	// 				,tipo:tipo
	// 			)
	// 		}else{
	// 			//Gasto
	// 			def cta=CuentaContable.findByClave("704-0002")
	// 			println 'Cuenta localizada: '+cta
	// 			addToPartidas(
	// 				cuenta:CuentaContable.findByClave("704-0002"),
	// 				debe:dif.abs(),
	// 				haber:0.0,
	// 				asiento:"OTROS GASTOS "+tipo,
	// 				descripcion:"OTROS GASTOS",
	// 				referencia:"",
	// 				fecha:fecha
	// 				,tipo:tipo
	// 			)
			
	// 		}
	// 	}else{
	// 		//Cuadre por verificar
	// 		println 'Registrando diferencia: '+dif
	// 		addToPartidas(
	// 		cuenta:CuentaContable.findByClave("800-0001"),
	// 		debe:dif<0.0?dif.abs():0.0,
	// 		haber:dif>0.0?dif.abs():0.0,
	// 		asiento:"CUDRE POR ACLARAR "+tipo,
	// 		descripcion:"VERIFICAR",
	// 		referencia:"",
	// 		fecha:fecha
	// 		,tipo:tipo
	// 		)
	// 	}
		
	// }
	
	// def afterUpdate(){
	// 	publishEvent(new PolizaUpdateEvent(this))
	// }
	
	// def afterInsert(){
	// 	log.info('Insertando poliza...')
	// 	publishEvent(new PolizaUpdateEvent(this))
	// }
}

// class PolizaUpdateEvent extends ApplicationEvent{
	
// 	PolizaUpdateEvent(Poliza poliza){
// 		super(poliza)
// 	}
// }

