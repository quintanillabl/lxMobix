package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional
import grails.events.Listener
import com.luxsoft.lx.ventas.Venta


@Transactional
class ContabilidadService {
    
    def saldoPorCuentaContableService


	@Listener(topic="altaDeVenta")
    def altaDeVenta(Venta venta) {
    	log.info('Venta registrada: '+venta.id)
    }

    @Listener(topic="bajaDeVenta")
    def bajaDeVenta(Venta venta){
    	log.info('Venta eliminada: '+venta.id)
    }

    @Listener(topic="ventaFacturada")
    def ventaFacturada(Venta venta){
    	log.info("Venta ${venta.folio} facturada UUID:${venta.cfdi.uuid}")
    }

    
    @Listener(topic="modificacionDePoliza")
    def modificacionDePoliza(Poliza poliza){
        // Poliza.withNewSession { session ->
        //     def p=Poliza.get(poliza.id)
        //     p.partidas.each{
        //         log.debug("Actualizando saldo de cuent:"+it.cuenta)
        //         saldoPorCuentaContableService.actualizarSaldo(it.cuenta,p.ejercicio,p.mes)
        //     }
            
        // }
        /*
        def cuadre=poliza.getCuadre().abs()
        if(cuadre==0.00){
            log.debug "Actualizando saldo de cuentas para poliza ${poliza.id}"
            Poliza.withNewSession { session ->
                def p=Poliza.get(poliza.id)
                p.partidas.each{
                    log.debug("Actualizando saldos..."+it.cuenta)
                    saldoPorCuentaContableService.actualizarSaldo(it.cuenta,p.ejercicio,p.mes)
                }
                
            }
        }else{
            log.debug("Poliza  ${poliza.id} con desdcuadre de: ${poliza.cuadre} NO SE PUEDE ACTUALIZAR SALDOS")

        }
        */
        
    }
    
}
