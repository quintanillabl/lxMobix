package com.luxsoft.lx.ventas


import org.apache.commons.logging.LogFactory


class LineaDeProductoVentaListener {

	private static final log=LogFactory.getLog(this)
	
	
	
	def beforeSavePartida(VentaDet det){
		log.debug 'Evaluando partida: '+det
		if(det.producto.linea.clave=='SERV_INM'){
			def direccion=det?.inmueble?.direccion
			if(direccion){
				def comentario="Pago de ${det.producto.descripcion.trim()} para  inmueble:${direccion.toLabel()} (${det.comentario})"
				log.debug 'Ajustando el comentari de la partida a: '+comentario
				det.comentario=comentario
			}
			
		}
		return det
	}
	
}

