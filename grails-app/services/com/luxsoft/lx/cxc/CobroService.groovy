package com.luxsoft.lx.cxc

import grails.transaction.Transactional
import com.luxsoft.lx.tesoreria.MovimientoDeCuenta

@Transactional
class CobroService {

	def springSecurityService

	def movimientoDeCuentaService

    def save(Cobro cobro) {
		assert !cobro.id,"El cobro $cobro.id ya ha sido persistido"
		cobro.with{
			def user=springSecurityService.getCurrentUser().username
			creadoPor=user
			modificadoPor=user
		}
		cobro.validate()
		if(cobro.hasErrors()){
			throw new CobroException(message:'Errores de validacion en el cobro',cobro:cobro)
		}
		cobro.save(failOnError:true)
		registrarIngreso(cobro)
	    event('altaDeCobro',cobro)
		return cobro
    }


    def registrarIngreso(Cobro cobro){
    	if(cobro.cuentaDestino){
    		def mov=new MovimientoDeCuenta(
    			empresa:cobro.empresa,
    			cuenta:cobro.cuentaDestino,
    			fecha:cobro.fecha,
    			importe:cobro.importe,
    			concepto:'COBRO',
    			referencia:cobro.referencia,
    			comentario:cobro.comentario
    			)
    		movimientoDeCuentaService.save(mov)
    	}
    }

    def registrarAplicacion(Cobro cobro,AplicacionDeCobro aplicacion){	

    	cobro.addToAplicaciones(cobro)
    	cobro.save flush:true,failOnError:true
    	def cxc=aplicacion.cuentaPorPagar
    	cxc.addToAplicaciones(aplicacion)
    	cxc.actualizar()
    	cxc.save flush:true
    	
    	return cobro

    }
}

class CobroException extends RuntimeException{
	String message
	Cobro cobro
}
