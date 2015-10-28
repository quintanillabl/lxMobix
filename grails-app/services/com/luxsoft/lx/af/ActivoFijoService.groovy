package com.luxsoft.lx.af

import grails.transaction.Transactional
import org.apache.commons.lang.time.DateUtils
import java.math.RoundingMode
import groovy.time.TimeCategory

@Transactional
class ActivoFijoService {


	def generarDepreciaciones(def activo){

		activo.depreciaciones.clear()

		def moi=activo.valorDepreciable
		def fechaIni=activo.adquisicion
		def tasa=activo.depreciacionTasa
		def acu=0.0
		def depMensual=(moi*tasa/100)/12
		
		use(TimeCategory){
		    
		    def fecha = fechaIni
		    while(moi-acu>0){
		        acu+=depMensual
		        def a=activo.addToDepreciaciones(ejercicio:fecha.toYear(),mes:fecha.toMonth(),depreciacion:depMensual)
		        fecha=fecha+1.month
			}
		}
		activo.save(failOnError:true,flush:true)
	}

  //   def calcularDepreciacion(def activo,Date fecha) {
    	
  //   	if (!fecha.after(activo.adquisicion))
  //   		return 0.0

  //   	BigDecimal tm = (activo.tasaDepreciacion/12)/100

		// Date fechaIni = DateUtils.truncate(activo.adquisicion,Calendar.MONTH)
  //   	BigDecimal monto = 0.0
  //   	BigDecimal depMensual = (activo.valorDepreciable*tm).setScale(2, RoundingMode.HALF_EVEN);

		// Calendar c = Calendar.getInstance()
		// c.setTime(fechaIni)
		// fechaIni = c.getTime()

  //   	BigDecimal remantente = 0.0;

		// while (fechaIni.before(fecha)) {
		// 	monto += depMensual
		// 	c.add(Calendar.MONTH, 1);
		// 	fechaIni = c.getTime();
		// 	remantente = activo.valorDepreciable-monto
		// 	if (remantente <= 0.0) {
		// 		return activo.valorDepreciable
		// 	}
		// 	if (remantente.compareTo(depMensual) < 0) {
		// 		return remantente
		// 	}
		// }
		// return monto
  //   }
}
