package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional
import org.apache.commons.lang.exception.ExceptionUtils



@Transactional
class InversionService {

	def movimientoDeCuentaService

    def  save(Inversion inversion) {
		
		assert inversion.cuentaOrigen!=inversion.cuentaDestino, 'No se puede hacer inversion sobre la misma cuenta'
		
		inversion.comision=0
		inversion.impuesto=0
		
		inversion.comentario=inversion.comentario?:'RE-INVERSION AUTOMATICA'
		
		if(inversion.importe.abs()){
			MovimientoDeCuenta egreso=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaOrigen,
				fecha:inversion.fecha,
				importe:inversion.importe.abs()*-1.0,
				concepto:inversion.comentario,
				referencia:inversion.comentario,
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(egreso)
			inversion.addToMovimientos(egreso)

			MovimientoDeCuenta ingreso=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaDestino,
				fecha:inversion.fecha,
				importe:inversion.importe.abs(),
				concepto:inversion.comentario,
				referencia:inversion.comentario,
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(ingreso)
			inversion.addToMovimientos(ingreso)

		}
		
		if(inversion.rendimientoReal.abs()){
			
			
			MovimientoDeCuenta rendimiento=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaDestino,
				fecha:inversion.vencimiento,
				importe:inversion.rendimientoReal.abs()+inversion.importeIsr.abs(),
				concepto:inversion.comentario,
				referencia:'INTERESE BRUTOS',
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(rendimiento)
			inversion.addToMovimientos(rendimiento)
			
		}
		
		return inversion.save(failOnError:true)
	}

    def  update(Inversion inversion) {

    	inversion.movimientos.clear()
		
		if(inversion.importe.abs()){
			MovimientoDeCuenta egreso=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaOrigen,
				fecha:inversion.fecha,
				importe:inversion.importe.abs()*-1.0,
				concepto:inversion.comentario,
				referencia:inversion.comentario,
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(egreso)
			inversion.addToMovimientos(egreso)

			MovimientoDeCuenta ingreso=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaDestino,
				fecha:inversion.vencimiento,
				importe:inversion.importe.abs(),
				concepto:inversion.comentario,
				referencia:inversion.comentario,
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(ingreso)
			inversion.addToMovimientos(ingreso)

		}
		
		if(inversion.rendimientoReal.abs()){
			
			MovimientoDeCuenta rendimiento=new MovimientoDeCuenta(
				empresa:inversion.empresa,
				cuenta:inversion.cuentaDestino,
				fecha:inversion.vencimiento,
				importe:inversion.rendimientoReal.abs()+inversion.importeIsr.abs(),
				concepto:inversion.comentario,
				referencia:'INTERESE BRUTOS',
				comentario:'INVERSION'
			)
			movimientoDeCuentaService.preparar(rendimiento)
			inversion.addToMovimientos(rendimiento)
		}
		
		return inversion.save(failOnError:true)
	}
    	
	def eliminarInversion(Inversion inversion){
		inversion.delete(flush:true)
	}
}
