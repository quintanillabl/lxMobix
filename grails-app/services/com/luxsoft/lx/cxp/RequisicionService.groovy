package com.luxsoft.lx.cxp

import grails.transaction.Transactional

@Transactional
class RequisicionService {

	def folioService

    def save(Requisicion requisicion) {
    	if(!requisicion.id){
    		requisicion.folio=folioService.nextFolio(requisicion.empresa,'REQUISICONES')
    	}
    	if(!requisicion.partidas) requisicion.partidas=[] as Set
    	requisicion.total=requisicion.partidas.sum(0.0,{it.requisitado})
    	requisicion.save failOnError:true,flush:true
    	return requisicion
    }

    def update(Requisicion requisicion){
    	requisicion.total=requisicion.partidas.sum(0.0,{it.requisitado})
    	requisicion.save failOnError:true,flush:true
    	return requisicion
    }

    def agregarPartida(Requisicion requisicion,RequisicionDet det){
        requisicion.addToPartidas(det)
        requisicion.total=requisicion.partidas.sum(0.0,{it.requisitado})
        requisicion.save failOnError:true,flush:true
        return requisicion
    }

    def eliminarPartida(RequisicionDet det){
        Requisicion requisicion=det.requisicion
        requisicion.removeFromPartidas(det)
        requisicion.total=requisicion.partidas.sum(0.0,{it.requisitado})
        requisicion.save failOnError:true,flush:true
        return requisicion
    }

    
}

class RequisicionException extends RuntimeException{
	String message
	Requisicion requisicion
}
