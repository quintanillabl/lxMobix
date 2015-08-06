package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional

@Transactional
class PagoService extends MovimientoDeCuentaService{

    // def save(Pago pago) {
    // 	pago.save failOnError:true,flush:true
    // 	return pago
    // }
    def save(Pago pago){
        pago.concepto="PAGO PROVEEDOR"
        pago.importe=pago.requisicion.total.abs()*-1
        return super.save(pago)
    }

    def aplicar(Pago pago){
    	if(!pago.aplicaciones){
    		def requisicion=pago.requisicion
    		requisicion.partidas.each{
    			pago.addToAplicaciones(
    				cuentaPorPagar:it.cuentaPorPagar,
    				fecha:pago.fecha,
    				importe:it.requisitado,
    				comentario:'Aplicacion de pago')
    		}
    	}
    }

    def cancelarAplicaiones(Pago pago){
    	if(pago.aplicaciones){
    		pago.aplicaciones.clear()
    		pago.save flush:true
    	}
    }
}
