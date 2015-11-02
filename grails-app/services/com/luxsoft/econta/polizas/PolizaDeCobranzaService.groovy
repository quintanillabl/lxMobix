package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import com.luxsoft.lx.cxc.Cobro
import static com.luxsoft.econta.polizas.PolizaUtils.*
/**
* 
* TODO: Ajustar para manejar el saldo cuando se trate de saldo parcial
**/
class PolizaDeCobranzaService extends AbstractProcesador{

    def generar(def empresa,Date fecha){
		return generar(empresa,'INGRESO','COBRANZA',fecha)
	}

	void procesar(def poliza){
		def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	def cobros=Cobro.findAll("from Cobro c where c.empresa=? and date(c.ingreso.fecha)=?",
                         [empresa,fecha])
    	cobros.each{ cobro ->
    		def desc=cobro.aplicaciones.collect{"Cobro F${it.cuentaPorCobrar.folio} ${it.cuentaPorCobrar.comentario}" }.join('/')
    		def asiento="COB: "+ cobro.aplicaciones.first().cuentaPorCobrar.partidas.first().producto.clave //.collect{it.cuentaPorCobrar.partidas.first().producto.clave}.join(' / ')
    		cargoABancos(poliza,cobro,desc,asiento)
    		abonoAClientes(poliza,cobro,desc,asiento)
    		cargoAIvaTraladadoPendiente(poliza,cobro,desc,asiento)
    		abonoAIvaTraladadoCobrado(poliza,cobro,desc,asiento)
    	}
    	poliza.concepto="Poliza de cobranza ${poliza.fecha.format('dd/MM/yyyy')}"
	}


	def cargoABancos(def poliza,def cobro,def descripcion,def asiento){
		log.info 'Cargo a bancos'
		assert cobro.ingreso.cuenta.cuentaContable,"Cuenta de banco sin cuenta contable ${cobro.ingreso.cuenta}"
		def desc = "$cobro.cliente.nombre $cobro.formaDePago  $cobro.referencia"
		cargoA(poliza,
			cobro.ingreso.cuenta.cuentaContable,
			cobro.ingreso.importe.abs(),
			desc,
			asiento,
			'Ingreso:'+cobro.ingreso.id,
			cobro
		)

	}

	def abonoAClientes(def poliza,def cobro,def descripcion,def asiento){
		
		cobro.aplicaciones.each{ aplicacion ->
			def desc="Cobro F${aplicacion.cuentaPorCobrar.folio} ${aplicacion.cuentaPorCobrar.comentario} ${cobro.cliente.nombre}"
			log.info 'Registrando abono a clientes : '+aplicacion.importe
			def venta=aplicacion.cuentaPorCobrar
			assert venta.cliente.cuentaContable,"Cliente $venta.cliente sin cuenta cuntable registrada"
			abonoA(poliza,
				venta.cliente.cuentaContable,
				venta.total,
				desc,
				asiento,
				'Cfdi:'+venta.cfdi.folio,
				venta
			)
		}
	}

	def cargoAIvaTraladadoPendiente(def poliza,def cobro,def descripcion,def asiento){
		cobro.aplicaciones.each{ aplicacion ->
			def venta=aplicacion.cuentaPorCobrar
			def desc="Cobro F${aplicacion.cuentaPorCobrar.folio} ${aplicacion.cuentaPorCobrar.comentario} ${cobro.cliente.nombre}"
			cargoA(
				poliza,
				IvaTrasladadoPendiente(poliza.empresa),
				venta.impuesto,
				desc,
				asiento,
				'Cfdi:'+venta.cfdi.folio,
				venta
			)
		}
	}

	def abonoAIvaTraladadoCobrado(def poliza,def cobro,def descripcion,def asiento){
		cobro.aplicaciones.each{ aplicacion ->
			def venta=aplicacion.cuentaPorCobrar
			def desc="Cobro F${aplicacion.cuentaPorCobrar.folio} ${aplicacion.cuentaPorCobrar.comentario} ${cobro.cliente.nombre}"
			abonoA(
				poliza,
				IvaTrasladadoCobrado(poliza.empresa),
				venta.impuesto,
				desc,
				asiento,
				'Cfdi:'+venta.cfdi.folio,
				venta
			)
		}
		
	}
}
