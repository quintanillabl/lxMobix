package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import static com.luxsoft.econta.polizas.PolizaUtils.*

import com.luxsoft.lx.tesoreria.Inversion
import com.luxsoft.lx.contabilidad.Poliza



class PolizaDeInversionesService extends AbstractProcesador {

	def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','INVERSIONES',fecha)
	}

	void procesar(def poliza){
    	
    	def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	log.info "Generando poliza de inversiones bancarias $empresa ${fecha.text()} "
    	def inversiones=Inversion.findAll("from Inversion i where i.empresa=? and date(i.fecha)=?",
    		[empresa,fecha])
    	
    	inversiones.each{ inversion ->
    		def desc=inversion.comentario
    		cargoABancos(poliza,inversion,desc)
    		abonoABancos(poliza,inversion,desc)
    		cargoDeInteresesABancarios(poliza,inversion,desc)
    		abonoAInteresesBancarios(poliza,inversion,desc)
    		cargoAIsr(poliza,inversion,desc)
    	}
    	poliza.concepto="Inversiones bancarias ${poliza.fecha.asPeriodoText()}"
    	
    }

    def cargoABancos(def poliza,def inversion,def descripcion){
    	cargoA(
    		poliza,
    		inversion.cuentaOrigen.cuentaContable,
    		inversion.importe.abs(),
    		descripcion,
    		'INVERSION ',
    		inversion.comentario,
    		inversion
    	)
    }

    def abonoABancos(def poliza,def inversion,def descripcion){
    	abonoA(
    		poliza,
    		inversion.cuentaDestino.cuentaContable,
    		inversion.importe.abs(),
    		descripcion,
    		'INVERSION ',
    		inversion.comentario,
    		inversion
    	)
    }

    def cargoDeInteresesABancarios(def poliza,def inversion,def descripcion){
    	cargoA(
    		poliza,
    		inversion.cuentaOrigen.cuentaContable,
    		inversion.rendimientoReal.abs(),
    		descripcion,
    		'INT INVERSION ',
    		inversion.comentario,
    		inversion
    	)
    }

    def abonoAInteresesBancarios(def poliza,def inversion,def descripcion){
    	abonoA(
    		poliza,
    		InteresesBancarios(poliza.empresa),
    		inversion.rendimientoReal.abs()+inversion.importeIsr.abs(),
    		descripcion,
    		'INT INVERSION ',
    		inversion.comentario,
    		inversion
    	)
    }

    def cargoAIsr(def poliza,def inversion,def descripcion){
    	cargoA(
    		poliza,
    		IsrBancario(poliza.empresa),
    		inversion.importeIsr.abs(),
    		descripcion,
    		'ISR RET INVERSION ',
    		inversion.comentario,
    		inversion
    	)
    }

   
}
