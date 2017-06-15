package com.luxsoft.cfdix

import grails.transaction.Transactional

import com.luxsoft.lx.cxc.AplicacionDeCobro

@Transactional
public class CfdiPagosService {

	def generarCfdi(AplicacionDeCobro aplicacion){
		log.info 'Generar CFDI de recepción de pago'
		return aplicacion

	}
}