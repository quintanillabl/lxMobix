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
        


    	def ejercicioAnterior = ejercicio
        def mesAnterior = mes 

        if(mes == 1 ){
            mesAnterior = 12
            ejercicioAnterior = ejercicio -1
        }else{
            mesAnterior = mes-1
        }
        
        def anterior = SaldoPorCuentaBancaria.find{
            cuenta==cuenta && ejercicio == ejercicioAnterior && mes ==mesAnterior
        }

        
       
        
        def ingresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe>0",
    			[cuenta,ejercicio,mes])[0]?:00
    	def egresos=MovimientoDeCuenta
    		.executeQuery("select sum(x.importe) from MovimientoDeCuenta x where x.cuenta=? and year(fecha)=? and month(fecha)=? and importe<0",
    			[cuenta,ejercicio,mes])[0]?:00
    	
    	def saldo=SaldoPorCuentaBancaria.findOrCreateByEmpresaAndCuentaAndEjercicioAndMes(cuenta.empresa,cuenta,ejercicio,mes)
        
        if(anterior){
            saldo.saldoInicial=anterior.saldoFinal
            log.info 'Saldo anterior: '+anterior.saldoFinal
        }
    	   
        else
            saldo.saldoInicial = 0.0
    	saldo.ingresos=ingresos
    	saldo.egresos=egresos
    	saldo.saldoFinal=saldo.saldoInicial+(ingresos+egresos)
    	saldo.save(failOnError:true,flush:true)
        
    }

    def actualizar(MovimientoDeCuenta movimiento){
    	def ejercicio=Periodo.obtenerYear(movimiento.fecha)
    	def mes=Periodo.obtenerMes(movimiento.fecha)+1
    	return actualizarSaldo(movimiento.cuenta,ejercicio,mes)
    }
}
