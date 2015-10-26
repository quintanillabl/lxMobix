package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional

@Transactional
class ComisionService {

	def movimientoDeCuentaService

    def save(Comision comision) {
    	assert !comision.id, 'La comision ya fue salvada'
    	
    	//comision.save()
    	
    	def retiro=new MovimientoDeCuenta(
    		empresa:comision.empresa,
    		cuenta:comision.cuenta,
    		fecha:comision.fecha,
    		importe:comision.comision.abs()*-1.0,
    		concepto:"Comision "+comision.comentario,
    		referencia:comision.referenciaBancaria,
    		comentario:'COMISION_BANCARIA'
    	)
    	movimientoDeCuentaService.preparar(retiro)
    	//retiro=movimientoDeCuentaService.save(retiro)
    	comision.addToMovimientos(retiro)
    	
    	if(comision.impuesto.abs()){
    		def retImpuesto=new MovimientoDeCuenta(
    			empresa:comision.empresa,
    			cuenta:comision.cuenta,
    			fecha:comision.fecha,
    			importe:comision.impuesto.abs()*-1.0,
    			concepto:"Iva comision "+comision.comentario,
    			referencia:comision.referenciaBancaria,
    			comentario:'IMPUESTO_COMISION_BANCARIA'
    		)
    		//retImpuesto=movimientoDeCuentaService.save(retImpuesto)
    		movimientoDeCuentaService.preparar(retImpuesto)
    		comision.addToMovimientos(retImpuesto)
    	}
    	comision.save flush:true,failOnError:true

    }
    
}
