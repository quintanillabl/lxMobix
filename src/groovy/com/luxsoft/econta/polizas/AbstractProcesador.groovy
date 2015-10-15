package com.luxsoft.econta.polizas




import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.utils.Periodo

import static com.luxsoft.econta.polizas.PolizaUtils.*
import static org.apache.commons.lang.StringUtils.*


abstract class  AbstractProcesador {

    
	def polizaService

    def generar(def empresa,def fecha,def procesador){
        return generar(empresa,procesador.tipo,procesador.nombre,fecha)
    }

    def generar(Empresa empresa,String tipo,String subTipo,Date fecha){
        log.info "Generando poliza $empresa $tipo $subTipo $fecha"

    	def found = find(empresa,subTipo,fecha)

    	if (found) {
    		found.partidas.clear()
    		log.info "Actualizando poliza ${subTipo }"+fecha.format('dd/MM/yyyy');
    		procesar(found)
    		return polizaService.update(found)

    	} else {
    		log.info "GENERANDO poliza ${subTipo } "+fecha.format('dd/MM/yyyy');
    		def poliza=build(empresa,fecha,tipo,subTipo)
    		//poliza = procesar(poliza)
            procesar(poliza)
    		return polizaService.save(poliza)
    	}
    }
    

    /// Metodos comunes

	def cargoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:importe,
            haber:0.0,
            descripcion:descripcion,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
	}

	def abonoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:0.0,
            haber:importe,
            descripcion:descripcion,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
	}

    def find(def empresa, String subTipo, Date fecha){
    	log.info "Buscando poliza ${subTipo} $fecha $empresa"
		
		def found = Poliza.find(
			"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=?",
			[empresa,subTipo,fecha])
		return found
	}

    def build(def empresa,def fecha,def tipo,def subTipo){
		def mes=Periodo.obtenerMes(fecha)+1
		def ejercicio=Periodo.obtenerYear(fecha)
		def poliza= new Poliza(empresa:empresa,tipo:tipo,subTipo:subTipo,ejercicio:ejercicio,mes:mes)
		poliza.fecha=fecha
		log.debug 'Poliaza preparada: '+poliza
		return poliza
	}
}
