package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional
import com.luxsoft.lx.core.Empresa

@Transactional
class BalanzaService {

	def generar(Empresa empresa,PeriodoContable periodo){
		generar(empresa,periodo.ejercicio,periodo.mes)
	}

    def generar(Empresa empresa,Integer ejercicio,Integer mes){
    	def balanza=Balanza.findOrCreateWhere(empresa:empresa,ejercicio:ejercicio,mes:mes)

    	def hql="from CuentaContable c where c.empresa=? and c.parent=null"
    	//def cuentas=CuentaContable.findAll(hql,empresa)
    	def cuentas=CuentaContable.where{ empresa==empresa && padre==null}
    	cuentas.each{ cuenta->
    		def found=balanza.partidas.find{it.cuenta==cuenta}
    		if(!found){
    			found=new BalanzaDet(cuenta:cuenta)
    			balanza.addToPartidas(found)
    		}
    	}
    	balanza.partidas.each{
    		def saldo=SaldoPorCuentaContable.findWhere(empresa:empresa,ejercicio:ejercicio,mes:mes)
    		if(saldo){
    			it.saldoIni=saldo.saldoInicial
    			it.debe=saldo.debe
    			it.haber=saldo.haber
    			it.saldoFin=saldo.saldoFinal
    		}
    	}
    	balanza.save(flush:true,failOnError:true)
    	return balanza
    }
}
