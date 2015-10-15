package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import com.luxsoft.lx.tesoreria.Pago
import com.luxsoft.lx.cxp.GastoDet
import com.luxsoft.lx.contabilidad.CuentaContable
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils


@Transactional
class PolizaDePagoGastosService extends AbstractProcesador{

	

	void procesar( def poliza){

		def empresa = poliza.empresa
		def fecha = poliza.fecha
		def pagos = Pago.findAll("from Pago p where p.empresa=? and date(p.fecha)=?",[empresa,fecha])
		
		log.info "Generando poliza de pago de gastos para ${fecha.format('dd/MM/yyyy')} pagos a procesar: ${pagos.size()}"
		poliza.concepto="Pago(s) de gasto(s) del ${poliza.fecha.format('dd/MM/yyyy')}"
		
		pagos.each{ pago ->
			def descripcion = 'PENDIENTE'

			def referencia='ND'

			if(pago.cheque){
				referencia = "CH-"+pago.cheque.folio
			}

			pago.aplicaciones.each { aplicacion ->

				cargoAGastos( poliza,aplicacion,descripcion,referencia)
				cargoAIvaAcreditable( poliza,aplicacion,descripcion,referencia)
			}
			abonoABancos(poliza,pago,descripcion,referencia)
		}
		
		
	}

	def cargoAGastos(def poliza,def aplicacion,descripcion,def referencia){
		
		//def gastoDet = GastoDet.findByCuentaPorPagar(aplicacion.cuentaPorPagar)
		def pago=aplicacion.pago

		def gasto = aplicacion.cuentaPorPagar
		if( gasto ) {

			def gastoDet = gasto.partidas.find{ it.cuentaContable }
			assert gastoDet,'No hay cuenta contable asignada para registrar el gasto '+gasto
			log.info 'Cargo a gasto :'+gastoDet

			cargoA(poliza,
				gastoDet.cuentaContable,
				gastoDet.importe,
				descripcion,
				'PAGO',
				referencia,
				aplicacion.pago
			)

		}
	}

	

	def cargoAIvaAcreditable(def poliza,def aplicacion,def descripcion,def referencia){

		def gasto = aplicacion.cuentaPorPagar
		
		cargoA(
			poliza,
			IvaAcreditable,
			gasto.impuesto,
			descripcion,
			'PAGO',
			referencia,
			gasto
		)
	}

	

	def abonoABancos(def poliza,def pago,descripcion,def referencia){
		def cuenta=pago.cuenta.cuentaContable
		assert cuenta,"La cuenta de banco ${pago.cuenta} no tiene cuenta contable asignada"
		abonoA(
			poliza,
			pago.cuenta.cuentaContable,
			pago.importe,
			descripcion,
			'PAGO',
			referencia,
			pago
		)
	}

}
