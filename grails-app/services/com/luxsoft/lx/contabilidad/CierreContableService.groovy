package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional

import com.luxsoft.lx.core.Empresa

@Transactional
class CierreContableService {


	def polizaService

    def generarCierreAnual(Empresa empresa,Integer ejercicio){
        
    	def cuentas=CuentaContable.findAllByPadreIsNull()
    	log.debug "Generando cierre anual para ${empresa}  ejercicio:${ejercicio}"
    	cuentas.each{ c->
    		c.subCuentas.each{ subCuenta->
                actualizarSaldoParaCierreAnual(empresa,ejercicio,subCuenta)
    			cierreAnual(empresa,ejercicio,subCuenta)
    		}
    		cierreAnual(empresa,ejercicio,c)
    	}
        
    }
    
    def cierreAnual(Empresa empresa,Integer ejercicio, def cuenta){
    	log.debug "Generando cierre anual para cuenta ${cuenta}"
    	def cierre=new Date()
    	if(cuenta.detalle){
    		def saldoInicial=SaldoPorCuentaContable.findByEmpresaAndEjercicioAndMesAndCuenta(empresa,ejercicio,12,cuenta)
    		assert saldoInicial ,'No existe el saldo inicial para la cuenta: '+cuenta+' año: '+ejercicio+ ' mes '+12
    		def debe=0.0
    		def haber=0.0
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:13)
    		saldo.cierre=cierre
    		saldo.saldoInicial=saldoInicial.saldoFinal
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		def res=saldo.save(failOnError:true)
    		log.debug 'Saldo generado: '+ res
    	}else{
    		def saldoInicial=SaldoPorCuentaContable.findByCuentaAndEjercicioAndMes(cuenta,ejercicio,12)
    		assert saldoInicial ,'No existe el saldo inicial para la cuenta: '+cuenta+' año: '+ejercicio+ ' mes '+12
    		def debe=0.0
    		def haber=0.0
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:13)
    		saldo.cierre=cierre
    		saldo.saldoInicial=saldoInicial.saldoFinal
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		saldo.save(failOnError:true)
    	}
    	
    }
    
    
    
    def actualizarSaldoParaCierreAnual(Empresa empresa,Integer ejercicio,def cuenta){
    	
    	def mes=13
    	
    	if(cuenta.detalle){
    		def row=PolizaDet
    			.executeQuery("select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta=? and d.poliza.ejercicio=?  and d.poliza.tipo=?"
    			,[cuenta,ejercicio,'CIERRE_ANUAL'])
    		def debe=row.get(0)[0]?:0.0
    		def haber=row.get(0)[1]?:0.0
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:mes)
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		saldo.save(flush:true,failOnError:true)
    		log.debug " Saldo actualizado $saldo"
    	}else{
    		
    		def row=PolizaDet.executeQuery(
    			"select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta.padre=? and year(d.poliza.fecha)=? and d.poliza.tipo=?"
    			,[cuenta,year,'CIERRE_ANUAL'])
    	
    		def debe=row.get(0)[0]?:0.0
    		def haber=row.get(0)[1]?:0.0
    		println " Cuenta: $cuenta.clave debe:$debe  haber:$haber"
    		def saldo=SaldoPorCuentaContable.findOrCreateWhere(empresa:empresa,cuenta:cuenta,year:year,mes:mes)
    		saldo.debe=debe
    		saldo.haber=haber
    		saldo.saldoFinal=saldo.saldoInicial+debe-haber
    		saldo.save(flush:true,failOnError:true)
            log.debug " Saldo actualizado $saldo"
    	}
    	
    }
    

    def actualizarPolizaDeCierreAnual(Empresa empresa,Integer ejercicio ){
        def poliza=Poliza.findByEmpresaAndEjercicioAndTipo(empresa,ejercicio,'CIERRE_ANUAL')
        if(!poliza){
            poliza=new Poliza(empresa:empresa,
                ejercicio:ejercicio,
                tipo:'CIERRE_ANUAL', 
                concepto:'CIERRE ANUAL ',
                mes:12,
                fecha:new Date(ejercicio,11,31))
        }
    	def asiento="CIERRE ANUAL ${ejercicio}"
    	def saldos=SaldoPorCuentaContable
    		.findAll("from SaldoPorCuentaContable s where s.empresa=? and s.ejercicio=? and s.mes=13 and s.cuenta.deResultado=true and s.cuenta.padre!=null and s.cuenta.clave not like ?"
    			,[poliza.empresa,poliza.ejercicio,'90%'])
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
    	
    	// CuentaContable cuenta=CuentaContable.buscarPorClave("304-0013")
    	// if(resultado && cuenta){
    	// 	poliza.addToPartidas(
    	// 		cuenta:cuenta,
    	// 		debe:0.0,
    	// 		haber:resultado,
    	// 		asiento:asiento,
    	// 		descripcion:asiento,
    	// 		referencia:"",
    	// 		tipo:poliza.tipo,
    	// 		entidad:'SaldoPorCuetaContable',
    	// 		origen:cuenta.id)
    	// }else{
    	// 	poliza.addToPartidas(
    	// 		cuenta:cuenta,
    	// 		debe:resultado.abs(),
    	// 		haber:0.0,
    	// 		asiento:asiento,
    	// 		descripcion:asiento,
    	// 		referencia:"",
    	// 		tipo:poliza.tipo,
    	// 		entidad:'SaldoPorCuetaContable',
    	// 		origen:cuenta.id
    	// 		)
    	// }
        poliza.actualizar()
        poliza=polizaService.save(poliza)
        return poliza
    	
    }
}
