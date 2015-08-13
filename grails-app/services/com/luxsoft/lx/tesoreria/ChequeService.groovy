package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional

@Transactional
class ChequeService {

	def springSecurityService

	def disponibles(){
		def hql="from MovimientoDeCuenta m join m.cuenta c where m.empresa=? and c.tipo=? and m not in (select "
	}

    def generarCheque(Pago egreso) {
    	def cuenta=egreso.cuenta
    	def folio=cuenta.folio++
    	def cheque=new Cheque(cuenta:cuenta,egreso:egreso,folio:folio)
    	cheque.with{
    		def user=springSecurityService.getCurrentUser().username
    		creadoPor=user
    		modificadoPor=user
    	}
    	cheque.save failOnError:true
    	//cuenta.folio=folio
    	cuenta.save(flush:true)
    	return egreso
    	
    }

    def cancelarCheque(Pago pago,String comentario){
        def cheque=pago.cheque
        cheque.cancelacion=new Date()
        cheque.comentarioCancelacion=comentario
        def user=springSecurityService.getCurrentUser().username
        cheque.modificadoPor=user
        cheque.save flush:true

        
        return pago
    }
}
