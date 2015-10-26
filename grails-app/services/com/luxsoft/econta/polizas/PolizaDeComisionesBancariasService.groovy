package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import static com.luxsoft.econta.polizas.PolizaUtils.*

import com.luxsoft.lx.tesoreria.Comision
import com.luxsoft.lx.contabilidad.Poliza

class PolizaDeComisionesBancariasService extends AbstractProcesador{

    

	def generar(def empresa,def fecha,def procesador){
		log.info "Generando poliza de comisiones bancarias $empresa ${fecha.text()} Procesador:$procesador"
		def comisiones=Comision.findAll("from Comision c where c.empresa=? and date(c.fecha)=?",
			[empresa,fecha])
		def polizas=[]
		def subTipo=procesador.nombre
		def tipo=procesador.tipo

		comisiones.each{ comision ->
				
			def poliza = Poliza.find(
				"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=? and p.entidad=? and p.origen=?",
				[empresa,subTipo,fecha,comision.class.name,comision.id]
				)

			if (poliza) {
				poliza.partidas.clear()
				log.info "Actualizando poliza ${subTipo }"+fecha.format('dd/MM/yyyy');
				procesar(poliza,comision)
		        cuadrar(poliza)
				poliza=polizaService.update(poliza)

			} else {
				log.info "GENERANDO poliza ${subTipo } "+fecha.format('dd/MM/yyyy');
				poliza=build(empresa,fecha,tipo,subTipo)
				poliza.entidad=comision.class.name
				poliza.origen=comision.id
				poliza.concepto="Comisiones bancarias ${comision.cuenta} ${comision.fecha.asPeriodoText()}"
		        procesar(poliza,comision)
		        poliza.actualizar()
		        cuadrar(poliza)
				poliza=polizaService.save(poliza)
			}
			polizas << poliza
		}
        return polizas
    }

    void procesar( def poliza,def comision){

    	def empresa = poliza.empresa
    	def fecha = poliza.fecha
    	
    	def desc="$comision.cuenta.numero ($comision.cuenta.nombre) ${comision.fecha.asPeriodoText()}"
    	cargoAComisiones(poliza,comision,desc)
    	cargoAIvaAcreditable(poliza,comision,desc)
    	abonoABancos(poliza,comision,desc)
    	
    	
    }

    def cargoAComisiones(def poliza,def comision,def descripcion){
    	cargoA(poliza,
    		ComisionesBancarias(poliza.empresa),
    		comision.comision.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
    }

    def cargoAIvaAcreditable(def poliza,def comision,def descripcion){
    	cargoA(poliza,
    		IvaAcreditable(poliza.empresa),
    		comision.impuesto.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
    }

    def abonoABancos(def poliza,def comision,def descripcion){
    	abonoA(
    		poliza,
    		comision.cuenta.cuentaContable,
    		comision.comision.abs()+comision.impuesto.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
    }

   
 
}
