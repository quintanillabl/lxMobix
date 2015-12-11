package com.luxsoft.lx.tesoreria

import grails.transaction.Transactional
import com.luxsoft.utils.Periodo
import com.luxsoft.lx.core.Empresa


@Transactional
class SaldoPorCuentaBancariaService {

    def actualizarSaldos(Empresa empresa,Integer ejercicio,Integer mes) {
    	def cuentas=CuentaBancaria.findAllByEmpresa(empresa)
    	def saldos=[]
    	cuentas.each{
    		saldos<<actualizarSaldo(it,ejercicio,mes)
    	}
    	return saldos
    }

    def actualizarSaldo(CuentaBancaria cuenta,Integer ejercicio,Integer mes ){
    	
    	log.info("Actualiando saldo para cuenta ${cuenta} Periodo:${mes} / ${ejercicio}  ")
        if(mes > 12 ) mes = 12

    	def ej = ejercicio
        def month = mes 
        if(mes == 1 ){
            month = 12
            ej =ejercicio -1
        }
        
        def anterior = SaldoPorCuentaBancaria.where{
            cuenta==cuenta && ejercicio == ej && mes ==month
        }.get()

        log.info 'Saldo anterior: '+anterior
       
        
        def ingresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe>0",
    			[cuenta,ejercicio,mes])[0]?:00
    	def egresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe<0",
    			[cuenta,ejercicio,mes])[0]?:00
    	
    	def saldo=SaldoPorCuentaBancaria.findOrCreateByEmpresaAndCuentaAndEjercicioAndMes(cuenta.empresa,cuenta,ejercicio,mes)
        if(anterior)
    	   saldo.saldoInicial=anterior.saldoFinal
        else
            saldo.saldoInicial = 0.0
    	saldo.ingresos=ingresos
    	saldo.egresos=egresos
    	saldo.saldoFinal=saldo.saldoInicial+(ingresos+egresos)
    	saldo.save(flush:true)
        
    }

    def actualizar(MovimientoDeCuenta movimiento){
    	def ejercicio=Periodo.obtenerYear(movimiento.fecha)
    	def mes=Periodo.obtenerMes(movimiento.fecha)+1
    	return actualizarSaldo(movimiento.cuenta,ejercicio,mes)
    }
}
