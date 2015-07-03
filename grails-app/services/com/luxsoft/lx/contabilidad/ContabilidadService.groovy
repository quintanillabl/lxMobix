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
        if(poliza.cuadre==0.0){
            log.info("Poliza actualizada ${poliza.id} OK Actualizando saldos de ${poliza.partidas.size()}")
            Poliza.withNewSession{
                def pol=Poliza.get(poliza.id)
                pol.partidas.each{det ->
                    log.info("Actualizando cuenta: "+det.cuenta)
                }
            }
            
        }else{
            log.info("Poliza con cuadre ${poliza.id} cuadre: ${poliza.cuadre} NO SE PUEDE ACTUALIZAR SALDOS")

        }
        
    }
}
