package com.luxsoft.lx.cxc

import grails.transaction.Transactional
import com.luxsoft.lx.tesoreria.MovimientoDeCuenta

@Transactional
class CobroService {

	def springSecurityService

	def movimientoDeCuentaService

    def saldoPorCuentaBancariaService

    def save(Cobro cobro) {
		assert !cobro.id,"El cobro $cobro.id ya ha sido persistido"
		cobro.with{
			def user=springSecurityService.getCurrentUser().username
			creadoPor=user
			modificadoPor=user
		}
        
		cobro.validate(["cliente","fecha","formaDePago","banco","referencia","importe","cuentaDestino","comentario"])
		if(cobro.hasErrors()){
			throw new CobroException(message:'Errores de validacion en el cobro',cobro:cobro)
		}
        def ingreso=generarIngreso(cobro)
        ingreso=movimientoDeCuentaService.save(ingreso)
        cobro.ingreso=ingreso
        cobro.save(failOnError:true,flush:true)
        //saldoPorCuentaBancariaService.actualizar(cobro.ingreso)
	    event('altaDeCobro',cobro)
		return cobro
    }


    def generarIngreso(Cobro cobro){
    	def ingreso=new MovimientoDeCuenta(
            empresa:cobro.empresa,
            cuenta:cobro.cuentaDestino,
            fecha:cobro.fecha,
            importe:cobro.importe,
            concepto:'COBRO',
            referencia:cobro.referencia,
            comentario:cobro.comentario+' - '+cobro.cliente.nombre
        )
    }

    def agregarAplicacion(Cobro cobro,AplicacionDeCobro aplicacion){	

        cobro.addToAplicaciones(aplicacion)
    	cobro.save flush:true,failOnError:true
    	return cobro

    }

    def eliminarAplicacion(AplicacionDeCobro aplicacion){
        def cobro=aplicacion.pago
        cobro.removeFromAplicaciones(aplicacion)
        cobro.save flush:true,failOnError:true
        return cobro

    }

    def delete(Cobro cobro){
        def ingreso=cobro.ingreso
        cobro.delete flush:true
        saldoPorCuentaBancariaService.actualizar(ingreso)

    }
}

class CobroException extends RuntimeException{
	String message
	Cobro cobro
}
