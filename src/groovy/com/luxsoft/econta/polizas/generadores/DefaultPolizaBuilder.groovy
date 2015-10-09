package com.luxsoft.econta.polizas.generadores

import com.luxsoft.lx.core.*
import com.luxsoft.lx.ventas.*
import com.luxsoft.lx.contabilidad.*
import org.apache.commons.logging.LogFactory
import com.luxsoft.utils.Periodo

class DefaultPolizaBuilder {

	private static log=LogFactory.getLog(this)

	def poliza

	def build(def empresa,def fecha,def tipo,def subTipo){
		def mes=Periodo.obtenerMes(fecha)+1
		def ejercicio=Periodo.obtenerYear(fecha)
		poliza= new Poliza(empresa:empresa,tipo:tipo,subTipo:subTipo,ejercicio:ejercicio,mes:mes)
		poliza.fecha=fecha
		log.debug 'Poliaza preparada: '+poliza
		return poliza
	}
	
	def procesar(Empresa empresa,def tipo,def subTipo,Date fecha,Closure task){
		def poliza = build(empresa,fecha,tipo,subTipo)
		
		task()
		return poliza
	}
	

	def cargoA(def cuenta,def importe,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
            debe:importe,
            haber:0.0,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id,
            entidad:entidad.class
		)
	}

	def abonoA(def cuenta,def importe,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
            debe:0.0,
            haber:importe,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id,
            entidad:entidad.class
		)
	}
		
	
}


