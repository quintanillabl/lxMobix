package com.luxsoft.lx.cxc

import grails.transaction.Transactional
import com.luxsoft.lx.ventas.Venta

@Transactional
class CuentaPorCobrarService {

    def generarCuentaPorCobrar(Venta venta) {
    	if(!venta.cfdi){
    		throw new CuentaPorCobrarException(message:"Venta sin facturar no puede generar cuenta por cobrar")
    	}
    	def cxc=new CuentaPorCobrar(venta)
        cxc.saldo=cxc.total
    	cxc.save failOnError:true
    	//event('altaDeCuentaPorCobrar',cxc)
    	return cxc
    }

    def eliminar(CuentaPorCobrar cxc){

    }
}

class CuentaPorCobrarException extends RuntimeException{
	String message
	CuentaPorCobrar cuentaPorCobrar
}
