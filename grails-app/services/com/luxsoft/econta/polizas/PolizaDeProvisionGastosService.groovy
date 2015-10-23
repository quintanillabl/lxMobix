package com.luxsoft.econta.polizas




import com.luxsoft.lx.cxp.Gasto
import static com.luxsoft.econta.polizas.PolizaUtils.*

class PolizaDeProvisionGastosService extends AbstractProcesador{

    def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','PROVISION_GASTOS',fecha)
	}

	void procesar(def poliza){
		def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	def inicio=fecha.inicioDeMes()

    	def gastos=Gasto.findAll("from Gasto g where g.empresa=? and date(g.fecha) between ? and ?",
    		[empresa,inicio,fecha])

    	gastos=gastos.findAll { it.buscarSaldoAlCorte(fecha) }

    	gastos.each{ gasto ->
    		def desc = "Prov F:${gasto.folio} (${gasto.fecha}) ${gasto.proveedor.nombre} "
    		cargoAGastos(poliza,gasto,desc)
    		cargoAIvaPendienteDeAcreditar(poliza,gasto,desc)
    		abonoAAcredoresDiversos(poliza,gasto,desc)
    	}
    	
    	poliza.concepto="PROVISION ${poliza.fecha.format('dd/MM/yyyy')}"
	}

	def cargoAGastos(def poliza,def gasto,def descripcion){
		log.info 'Cargo a gastos'
		gasto.partidas.each { det ->
			assert det.cuentaContable,"Detalle del gasto sin cuenta contable ${gasto.id}"
			cargoA(poliza,
				det.cuentaContable,
				det.importe,
				descripcion,
				'PROVISION',
				'F:'+gasto.folio,
				gasto
			)
		}
	}

	def cargoAIvaPendienteDeAcreditar(def poliza,def gasto,def descripcion){
		log.info 'Cargo a iva pendiente de acreditar'
		cargoA(poliza,
			IvaPendienteDeAcreditar(poliza.empresa),
			gasto.impuesto,
			descripcion,
			'PROVISION',
			'F:'+gasto.folio,
			gasto
		)
	}

	def abonoAAcredoresDiversos(def poliza,def gasto,def descripcion){
		log.info 'Abono a acredores diversos'
		abonoA(poliza,
			AcredoresDiversos(poliza.empresa),
			gasto.total,
			descripcion,
			'PROVISION',
			'F:'+gasto.folio,
			gasto
		)
	}

}
