package com.luxsoft.econta.polizas.generadores

import com.luxsoft.lx.core.*
import com.luxsoft.lx.ventas.*
import com.luxsoft.lx.contabilidad.*
import org.apache.commons.logging.LogFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.beans.BeansException

class PolizaGenerator implements ApplicationContextAware{

	def applicationContext

	def polizaService
	
	private static final log=LogFactory.getLog(this)

	def generar(def empresa,def subTipo,Date fecha){
		
		def poliza
		
		switch(subTipo) {
			case 'FACTURACION':
				poliza = new PolizaDeFacturacion().generar(empresa,subTipo,fecha)
			break
			default:
				throw new RuntimeException('No existe procesador de polizas para el sub tipo: '+subTipo)
			break
		}
		
		//poliza = polizaService.save poliza
		return poliza
	}	

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		this.applicationContext=applicationContext
	}
	
}


