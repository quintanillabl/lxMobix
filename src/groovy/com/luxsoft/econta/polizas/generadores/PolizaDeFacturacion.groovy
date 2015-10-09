package com.luxsoft.econta.polizas.generadores

import com.luxsoft.lx.core.*
import com.luxsoft.lx.ventas.*
import com.luxsoft.lx.contabilidad.*
import org.apache.commons.logging.LogFactory

class PolizaDeFacturacion extends DefaultPolizaBuilder {

	def generar(Empresa empresa,def subTipo,Date fecha){
		
		procesar(empresa,'DIARIO',subTipo,fecha){
			
			def ventas=Venta.findAll("from Venta v where v.empresa=? and date(fecha)=?",[empresa,fecha])
			log.info "Generando poliza de facturacion para ${fecha.format('dd/MM/yyyy')} ventas registradas: ${ventas.size()}"
			ventas.each{ venta ->
				log.info 'Procesando: '+venta
				if(venta.tipo=='ARRENDAMIENTO' && venta.cfdi){

					cargoARentasPorCobrar venta
			        
			        venta.partidas.each{
			            def renta=Renta.findByVentaDet(it)
			            if(renta){
				            abonoAIngresosPorArrendamiento renta
			            }
			        }
			        abonoAIvaTraladadoPendiente venta
				}
			}
		}
		poliza.concepto="Poliza de facturacion ${poliza.fecha.format('dd/MM/yyyy')}"
		return poliza
	}

	

	def cargoARentasPorCobrar(def venta){
		log.info 'Registrando cargo a rentas por cobrar: '+venta.total
		assert venta.cliente.cuentaContable,"Cliente $venta.cliente sin cuenta cuntable registrada"
		cargoA(
			venta.cliente.cuentaContable,
			venta.total,
			'RENTA',
			'Cfdi:'+venta.cfdi.folio,venta.id.toString(),
			venta
		)
	}

	def abonoAIvaTraladadoPendiente(def venta){
		abonoA(
			CuentaContable.findByClave('209-0001'),
			venta.impuesto,
			'RENTA',
			'Cfdi:'+venta.cfdi.folio,
			venta
		)
	}

	def abonoAIngresosPorArrendamiento(def renta){
		assert renta.arrendamiento.cuentaContable,"Arrendamiento $renta.arrendamiento.cliente sin cuenta cuntable registrada"
		abonoA(
			renta.arrendamiento.cuentaContable,
			renta.ventaDet.importeNetoSinIva,
			'RENTA',
			'Cfdi:'+venta.cfdi.folio,
			venta
		)
	}

	def cargoA(def cuenta,def importe,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
            debe:importe,
            haber:0.0,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id,
            entidad:entidad.class
		)
	}

	def abonoA(def cuenta,def importe,def asiento,def referencia,def entidad){
		poliza.addToPartidas(
        	cuenta:cuenta,
            debe:0.0,
            haber:importe,
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id,
            entidad:entidad.class
		)
	}
		
	
}


