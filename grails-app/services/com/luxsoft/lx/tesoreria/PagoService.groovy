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
        pago.aFavor=pago.requisicion.aFavor
        pago.importe=pago.requisicion.total.abs()*-1
        pago.fecha=pago.requisicion.pago
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

    def delete(Pago pago){
        if(pago.aplicaciones){
            throw new RuntimeException("Pago ya con aplicaciones no se puede eliminar");
        }
        if(pago.cheque){
            def cheque=pago.cheque
            cheque.delete flush:true
        }
        pago.delete flush:true
    }

    def cancelarAplicaiones(Pago pago){
        log.info 'Cancelando aplicaciones del pago:'+pago.id
        def aplicaciones=pago.aplicaciones
        aplicaciones.each{
            pago.removeFromAplicaciones(it)
        }
        log.info 'Aplicaciones del pago: '+pago.aplicaciones.size()
        pago.save flush:true
    }
}
