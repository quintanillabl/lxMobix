package com.luxsoft.econta.polizas

import grails.transaction.Transactional

import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.ventas.Venta

import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.contabilidad.CuentaContable
import org.apache.commons.lang.StringUtils

import com.luxsoft.mobix.core.Renta

@Transactional
class PolizaDeFacturacionService extends ProcesadorDePolizaService{

	

	def generar(Empresa empresa,Date fecha){
		return generar(empresa,'DIARIO','FACTURACION',fecha)
	}

    def procesar(Poliza poliza){
    	def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	def ventas=Venta.findAll("from Venta v where v.empresa=? and date(fecha)=?",[empresa,fecha])
    	log.info "Generando poliza de facturacion para ${fecha.format('dd/MM/yyyy')} ventas registradas: ${ventas.size()}"

    	ventas.each{ venta ->
    		log.info 'Procesando: '+venta
    		def descripcion=venta.comentario

    		if(venta.tipo=='ARRENDAMIENTO' && venta.cfdi){
    			venta.partidas.each{det ->
    				
    				descripcion="Prov F.${venta.cfdi.folio} ${det.producto.clave} ${det.comentario} "
    				//descripcion=it.producto.clave
    			}
    			descripcion=StringUtils.substring(descripcion,0,255)
    			cargoAClientes( poliza, venta,descripcion)
    	        
    	        venta.partidas.each{
    	            def renta=Renta.findByVentaDet(it)
    	            if(renta){
    		            abonoAIngresosPorArrendamiento (poliza,renta,descripcion)
    	            }
    	        }
    	        abonoAIvaTraladadoPendiente(poliza,venta,descripcion)

    		} else if( venta.tipo== 'SERVICIOS') {
    			// def descripcion=it.comentario
    			
    			
    			// venta.partidas.each{ det ->
    			//     descripcion="Prov F.$it.folio ${det.producto.clave} $it.comentario "
    			    
    			// }
    			// println "Cargo a: ${it.cliente.cuentaContable} de: ${it.total}  Desc:$descripcion "
    			// def OtrosIngresos=CuentaContable.findByClave('704-0001')
    			// println "Abono a Otros ingresos ${OtrosIngresos} de: ${it.subTotal} Desc:$descripcion"
    			// def IVA_TRASLADADO_PENDIENTE=CuentaContable.findByClave('209-001')
    			// println "Abono a ${IVA_TRASLADADO_PENDIENTE} de: ${it.impuesto}"
    		}
    	}

    	poliza.concepto="Poliza de facturacion ${poliza.fecha.format('dd/MM/yyyy')}"
    	return poliza
    }

	// def cargoARentasPorCobrar(def poliza,def venta){
	// 	log.info 'Registrando cargo a rentas por cobrar: '+venta.total
	// 	assert venta.cliente.cuentaContable,"Cliente $venta.cliente sin cuenta cuntable registrada"
	// 	cargoA(poliza,
	// 		venta.cliente.cuentaContable,
	// 		venta.total,
	// 		'RENTA',
	// 		'Cfdi:'+venta.cfdi.folio,
	// 		venta
	// 	)

	// }

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
			CuentaContable.findByClave('209-0001'),
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

	

	/*
	*def fecha=Date.parse('dd/MM/yyyy','14/08/2015')

	def ventas=Venta.findAllByFecha(fecha)
	ventas.each{
	   
	    if(it.tipo=='SERVICIOS'){
	         println 'Contabilizando :'+it
	        def descripcion=it.comentario
	       	
	        
	        it.partidas.each{ det ->
	            descripcion="Prov F.$it.folio ${det.producto.clave} $it.comentario "
	            
	        }
	        println "Cargo a: ${it.cliente.cuentaContable} de: ${it.total}  Desc:$descripcion "
	        def OtrosIngresos=CuentaContable.findByClave('704-0001')
	        println "Abono a Otros ingresos ${OtrosIngresos} de: ${it.subTotal} Desc:$descripcion"
	        def IVA_TRASLADADO_PENDIENTE=CuentaContable.findByClave('209-001')
	        println "Abono a ${IVA_TRASLADADO_PENDIENTE} de: ${it.impuesto}"
	    }
	}
	*/

}
