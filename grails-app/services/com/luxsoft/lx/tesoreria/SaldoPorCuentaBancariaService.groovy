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
    	
    	def saldoFinalMesAnterior=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha) < ?",
    			[cuenta,ejercicio,mes])[0]?:00
    	def hql="select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha) < ? and ingreso=?"
    	def ingresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe>0",
    			[cuenta,ejercicio,mes])[0]?:00
    	def egresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe<0",
    			[cuenta,ejercicio,mes])[0]?:00
    	
    	def saldo=SaldoPorCuentaBancaria.findOrCreateByEmpresaAndCuentaAndEjercicioAndMes(cuenta.empresa,cuenta,ejercicio,mes)
    	saldo.saldoInicial=saldoFinalMesAnterior
    	saldo.ingresos=ingresos
    	saldo.egresos=egresos
    	saldo.saldoFinal=saldoFinalMesAnterior+(ingresos+egresos)
    	saldo.save(flush:true)
    }

    def actualizar(MovimientoDeCuenta movimiento){
    	def ejercicio=Periodo.obtenerYear(movimiento.fecha)
    	def mes=Periodo.obtenerMes(movimiento.fecha)+1
    	return actualizarSaldo(movimiento.cuenta,ejercicio,mes)
    }
}
