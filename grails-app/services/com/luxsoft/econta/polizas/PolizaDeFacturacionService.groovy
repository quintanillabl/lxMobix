package com.luxsoft.econta.polizas



import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.lx.contabilidad.*
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils

import com.luxsoft.lx.ventas.Venta
import com.luxsoft.mobix.core.Renta

class PolizaDeFacturacionService extends AbstractProcesador{

	def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','FACTURACION',fecha)
	}

    void procesar(def poliza){
    	
    	def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	def ventas=Venta.findAll("from Venta v where v.empresa=? and date(fecha)=? and cancelada is false ",[empresa,fecha])
    	log.info "Generando poliza de facturacion para ${fecha.format('dd/MM/yyyy')} ventas registradas: ${ventas.size()} Empresa ${empresa}"

    	ventas.each{ venta ->
    		log.info 'Procesando: '+venta
    		def descripcion = 'PENDIENTE'
    		def det = venta.partidas?venta.partidas.first():null

    		// Ajustamos la descripcion a partir de la primera partida
    		if( det ){ 
    			descripcion="Prov F.${venta.cfdi?venta.cfdi.folio:''} ${det.producto.clave} ${venta.comentario} "
    			descripcion=StringUtils.substring(descripcion,0,255)
    		}

    		if(venta.tipo=='ARRENDAMIENTO' && venta.cfdi){
    			
    			cargoAClientes( poliza, venta,descripcion)
    	        
    	        venta.partidas.each{
    	            def renta=Renta.findByVentaDet(it)
    	            if(renta){
    		            abonoAIngresosPorArrendamiento (poliza,renta,descripcion)
    	            }
    	        }
    	        abonoAIvaTraladadoPendiente(poliza,venta,descripcion)

    		} else if( venta.tipo== 'SERVICIOS') {

    			// println "Cargo a: ${it.cliente.cuentaContable} de: ${it.total}  Desc:$descripcion "
    			cargoAClientes( poliza, venta,descripcion)

    			// println "Abono a Otros ingresos ${OtrosIngresos} de: ${it.subTotal} Desc:$descripcion"
    			abonoAIngresosPorServicios( poliza, venta, descripcion)
    			
    			// println "Abono a ${IVA_TRASLADADO_PENDIENTE} de: ${it.impuesto}"
    			abonoAIvaTraladadoPendiente( poliza,venta,descripcion)
    			
    		}else if(venta.tipo=='NORMAL'){
    			cargoAClientes( poliza, venta,descripcion)

    			// println "Abono a Otros ingresos ${OtrosIngresos} de: ${it.subTotal} Desc:$descripcion"
    			abonoAIngresosPorActivoFijo( poliza, venta, descripcion)
    			
    			// println "Abono a ${IVA_TRASLADADO_PENDIENTE} de: ${it.impuesto}"
    			abonoAIvaTraladadoPendiente( poliza,venta,descripcion)
    		}


    	}

    	poliza.concepto="Poliza de facturacion ${poliza.fecha.format('dd/MM/yyyy')}"
    	//return poliza
    }

	def cargoAClientes(def poliza,def venta,def descripcion){
		log.info 'Registrando cargo a clientes : '+venta.total
		assert venta.cliente.cuentaContable,"Cliente $venta.cliente sin cuenta cuntable registrada"
		cargoA(poliza,
			venta.cliente.cuentaContable,
			venta.total,
			descripcion,
			venta.tipo,
			'Cfdi:'+venta.cfdi.folio,
			venta
		)

	}

	def abonoAIvaTraladadoPendiente(def poliza,def venta,def descripcion){
		abonoA(
			poliza,
			IvaTrasladadoPendiente(poliza.empresa),
			venta.impuesto,
			descripcion,
			venta.tipo,
			'Cfdi:'+venta.cfdi.folio,
			venta
		)
	}

	def abonoAIngresosPorArrendamiento(def poliza,def renta,def descripcion){
		assert renta.arrendamiento.cuentaContable,"Arrendamiento $renta.arrendamiento sin cuenta cuntable registrada"
		abonoA(
			poliza,
			renta.arrendamiento.cuentaContable,
			renta.ventaDet.importeNeto,
			descripcion,
			renta.ventaDet.venta.tipo,
			'Cfdi:'+renta.ventaDet.venta.cfdi.folio,
			renta.ventaDet.venta
		)
	}

	def abonoAIngresosPorServicios(def poliza,def venta,def descripcion){
		abonoA(
			poliza,
			Servicios(poliza.empresa),
			venta.subTotal,
			descripcion,
			venta.tipo,
			'CFDI: '+venta.cfdi.folio,
			venta
		)
	}

	def abonoAIngresosPorActivoFijo(def poliza,def venta,def descripcion){
		def cuenta=CuentaContable.buscarPorClave(poliza.empresa,"403-0002")
		//println 'Generando abono a la cuenta: '+cuenta
		
		abonoA(
			poliza,
			cuenta,
			venta.subTotal,
			descripcion,
			venta.tipo,
			'CFDI: '+venta.cfdi.folio,
			venta
			)
		
	}

	
	def cargoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		def det=new PolizaDet(
			cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:importe.abs(),
            haber:0.0,
            descripcion:StringUtils.substring(descripcion,0,255),
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
		addComplementoCompra(det,entidad)
		poliza.addToPartidas(det)
		return det;
	}

	def abonoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		def det=new PolizaDet(
			cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:0.0,
            haber:importe.abs(),
            descripcion:StringUtils.substring(descripcion,0,255),
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
		addComplementoCompra(det,entidad)
		poliza.addToPartidas(det)
		return det
	}

	def addComplementoCompra(def polizaDet, def entidad){
		if(entidad.cfdi){
			def cfdi = entidad.cfdi
			def compra = new TransaccionCompraNal(
				polizaDet:polizaDet,
				uuidcfdi:cfdi.uuid,
				rfc: cfdi.receptorRfc,
				montoTotal: cfdi.total
			)
			polizaDet.compraNal = compra
		}

	}
	

}
