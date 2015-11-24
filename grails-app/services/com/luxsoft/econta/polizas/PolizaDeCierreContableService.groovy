package com.luxsoft.econta.polizas



import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.contabilidad.CuentaContable
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils

import com.luxsoft.lx.ventas.Venta
import com.luxsoft.mobix.core.Renta

class PolizaDeCierreContableService extends AbstractProcesador{

	

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

}
