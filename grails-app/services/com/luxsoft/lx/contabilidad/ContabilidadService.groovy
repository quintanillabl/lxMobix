package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional
import grails.events.Listener
import com.luxsoft.lx.ventas.Venta


@Transactional
class ContabilidadService {

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
        log.info("Poliza actualizada "+poliza)
    }
}
