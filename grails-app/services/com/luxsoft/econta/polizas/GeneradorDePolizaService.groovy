package com.luxsoft.econta.polizas

import grails.transaction.Transactional

@Transactional
class GeneradorDePolizaService {

	def polizaDeFacturacionService

    def generar(def empresa,def subTipo,Date fecha){
    	switch(subTipo) {
    		case 'FACTURACION':
    			return polizaDeFacturacionService.generar(empresa,fecha)
    		break
    		default:
    			throw new RuntimeException('No existe procesador de polizas para el sub tipo: '+subTipo)
    		break
    	}
    }
}
