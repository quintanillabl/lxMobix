package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional
import org.apache.commons.lang.exception.ExceptionUtils



@Transactional
class InversionService {

	def movimientoDeCuentaService

    def  save(Inversion inversion) {
		//def cuenta=inversion.cuentaOrigen
		//inversion.cuentaDestino=cuenta
		assert inversion.cuentaOrigen!=inversion.cuentaDestino, 'No se puede hacer inversion sobre la misma cuenta'
		
		//inversion.vencimiento=inversion.fecha+inversion.plazo
		inversion.comision=0
		inversion.impuesto=0
		//inversion.moneda=cuenta.moneda

		inversion.comentario=inversion.comentario?:'RE-INVERSION AUTOMATICA'
		
		MovimientoDeCuenta egreso=new MovimientoDeCuenta(
			empresa:inversion.empresa,
			cuenta:inversion.cuentaOrigen,
			fecha:inversion.fecha,
			importe:inversion.importe.abs()*-1.0,
			concepto:inversion.comentario,
			referencia:inversion.comentario,
			comentario:'INVERSION'
		)
		
		//Calculando la utilitdad
		def rendimiento=0

		def tasa=inversion.tasa

		def importe=inversion.importe

		/*
		def importeIsr=(inversion.importe*(inversion.tasaIsr/100))/inversion.cuentaOrigen.diasInversionIsr
		importeIsr=importeIsr*inversion.plazo
		if(tasa>0){
			rendimiento=importe*(tasa/100)
			rendimiento=(rendimiento/360)*inversion.plazo
			rendimiento=rendimiento-importeIsr
		}
		*/

		importe+=inversion.rendimientoReal
		
		//inversion.importeIsr=importeIsr
		
		inversion.rendimientoCalculado=rendimiento
		//inversion.rendimientoReal=rendimiento
		
		//Generando el ingreso
		MovimientoDeCuenta ingreso=new MovimientoDeCuenta(
			empresa:inversion.empresa,
			cuenta:inversion.cuentaDestino,
			fecha:inversion.vencimiento,
			importe:importe.abs(),
			concepto:inversion.comentario,
			referencia:inversion.comentario,
			comentario:'INVERSION'

			
		)
		
		movimientoDeCuentaService.preparar(ingreso)
		movimientoDeCuentaService.preparar(egreso)
		inversion.addToMovimientos(ingreso)
		inversion.addToMovimientos(egreso)
		
		return inversion.save(failOnError:true)
		
    }
    	
	def eliminarInversion(Inversion inversion){
		inversion.delete(flush:true)
	}
}
