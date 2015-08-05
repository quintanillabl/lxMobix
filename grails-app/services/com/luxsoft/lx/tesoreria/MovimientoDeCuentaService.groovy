package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional
import com.luxsoft.lx.core.Folio

@Transactional
class MovimientoDeCuentaService {

	def springSecurityService
	def saldoPorCuentaBancariaService

    def save(MovimientoDeCuenta movimiento) {
    	assert !movimiento.id,"El movimiento $movimiento.id ya ha sido persistida"
    	movimiento.with{
    		def user=springSecurityService.getCurrentUser().username
    		creadoPor=user
    		modificadoPor=user
    		folio=nextFolio(delegate)
    	}
    	movimiento.save(failOnError:true)
    	saldoPorCuentaBancariaService.actualizar(movimiento)
        event('altaDemovimiento',movimiento)
    	return movimiento
    }


    private Long nextFolio(MovimientoDeCuenta movimiento){
        def folio=Folio.findByEmpresaAndSerie(movimiento.empresa,'MOVIMIENTO_DE_CUENTA')
        if(folio==null){
            folio=new Folio(empresa:movimiento.empresa,serie:'MOVIMIENTO_DE_CUENTA',folio:0l)
        }
        def res=folio.next()
        folio.save()
        return res
    }


}
