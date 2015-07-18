package com.luxsoft.lx.ventas


import org.apache.commons.logging.LogFactory


class LineaDeProductoVentaListener {

	private static final log=LogFactory.getLog(this)
	
	
	
	def beforeSavePartida(VentaDet det){
		log.debug 'Evaluando partida: '+det
		if(det?.producto?.linea){
			if(det.producto.linea.clave==''){
				log.debug 'Ajustando el comentari de la partida: '+det
			}
		}
		return det
	}
	
}

