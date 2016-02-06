package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional

import com.luxsoft.lx.core.Empresa
import com.luxsoft.utils.*

@Transactional
class CierreContableService {


	def polizaService

    def saldoPorCuentaContableService



    def generarSaldosParaCierre(Empresa empresa,Integer ejercicio){
        
    	def cuentas=CuentaContable.findAllByEmpresaAndPadreIsNull(empresa)
    	log.debug "Generando cierre anual para ${empresa}  ejercicio:${ejercicio}"
    	cuentas.each{ c->
    		c.subCuentas.each{ subCuenta->
    			cierreAnual(empresa,ejercicio,subCuenta)
    		}
    		cierreAnual(empresa,ejercicio,c)
    	}
        
    }
    
    def cierreAnual(Empresa empresa,Integer ejercicio, def cuenta){
    	log.debug "Generando cierre anual para cuenta ${cuenta}"
    	def cierre=new Date()
        def mes=12
    	if(cuenta.detalle){
    		def saldoInicial=SaldoPorCuentaContable.findByCuentaAndEjercicioAndMes(cuenta,ejercicio,mes)
    		assert saldoInicial ,"No existe Saldo para la cuenta ${cuenta} (Id:${cuenta.id}) Periodo:${ejercicio}/${mes}"
    		def debe=0.0
    		def haber=0.0
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:13)
    		saldo.cierre=cierre
    		saldo.saldoInicial=saldoInicial.saldoFinal
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		def res=saldo.save(flush:true,failOnError:true)
    		log.debug 'Saldo generado: '+ res
    	}else{
            def saldoInicial=SaldoPorCuentaContable.findByEjercicioAndMesAndCuenta(ejercicio,12,cuenta)
    		assert saldoInicial ,'No existe el saldo inicial para la cuenta: '+cuenta+' año: '+ejercicio+ ' mes '+12
    		def debe=0.0
    		def haber=0.0
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:13)
    		saldo.cierre=cierre
    		saldo.saldoInicial=saldoInicial.saldoFinal
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		saldo.save(flush:true,failOnError:true)
    	}
    	
    }
    

    def generarPolizaDeCierre(Empresa empresa,Integer ejercicio ){
        empresa.refresh()
        log.info "Generando poliza de cierre anual $empresa ejercicio: $ejercicio"
        def poliza=Poliza.findByEmpresaAndEjercicioAndTipoAndSubTipo(empresa,ejercicio,'DIARIO','CIERRE_ANUAL')
        assert poliza==null,"Poliza de cierre anual para el ejercicion:${ejercicio} ya fue generada"
        
        poliza=new Poliza(
            empresa:empresa,
            ejercicio:ejercicio,
            tipo:'DIARIO',
            subTipo:'CIERRE_ANUAL' ,
            concepto:'CIERRE ANUAL ',
            mes:13,
            fecha:Periodo.getPeriodoAnual(ejercicio).fechaFinal)
        

    	def asiento="CIERRE ANUAL ${ejercicio}"
    	def saldos=SaldoPorCuentaContable
    		.findAll("from SaldoPorCuentaContable s where s.empresa=? and s.ejercicio=? and s.mes=13 and s.cuenta.deResultado=true and s.cuenta.padre!=null and s.cuenta.clave not like ?"
    			,[empresa,poliza.ejercicio,'90%'])
    	BigDecimal cargos=0
    	BigDecimal abonos=0	
    	saldos.each {saldo->
    		BigDecimal imp=saldo.saldoInicial
    		if(imp>0){
    			poliza.addToPartidas(
    				cuenta:saldo.cuenta,
    				debe:0.0,
    				haber:imp,
    				asiento:asiento,
    				concepto:asiento,
    				referencia:"",
    				tipo:poliza.tipo,
    				entidad:'SaldoPorCuetaContable',
    				origen:saldo.id
    				)
    			abonos+=imp
    		}else if(imp<0){
    			poliza.addToPartidas(
	    			cuenta:saldo.cuenta,
	    			debe:imp.abs(),
	    			haber:0.0,
	    			asiento:asiento,
	    			concepto:asiento,
	    			referencia:"",
	    			tipo:poliza.tipo,
	    			entidad:'SaldoPorCuetaContable',
	    			origen:saldo.id
    			)
    			cargos+=imp.abs()
    		}
    		
    	}
    	def resultado=cargos-abonos
    	
    	CuentaContable cuenta=CuentaContable.get(44)
    	if(resultado && cuenta){
    		poliza.addToPartidas(
    			cuenta:cuenta,
    			debe:0.0,
    			haber:resultado,
    			asiento:asiento,
    			descripcion:asiento,
    			referencia:"",
    			tipo:poliza.tipo,
    			entidad:'SaldoPorCuetaContable',
    			origen:cuenta.id)
    	}else{
    		poliza.addToPartidas(
    			cuenta:cuenta,
    			debe:resultado.abs(),
    			haber:0.0,
    			asiento:asiento,
    			descripcion:asiento,
    			referencia:"",
    			tipo:poliza.tipo,
    			entidad:'SaldoPorCuetaContable',
    			origen:cuenta.id
    			)
    	}
        //poliza.actualizar()
        assert poliza.id==null
        poliza=polizaService.save(poliza)
        saldoPorCuentaContableService.actualizarSaldos(poliza)
        return poliza
    	
    }
}
