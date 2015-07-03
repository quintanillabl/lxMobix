package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional
import java.text.SimpleDateFormat
import com.luxsoft.lx.core.Empresa


@Transactional
class SaldoPorCuentaContableService {


	def buscarSaldosDeSubCuentas(SaldoPorCuentaContable saldo){
		def saldos=[]
		def empresa=saldo.empresa
		def ejercicio=saldo.ejercicio
		def mes=saldo.mes
		saldo.cuenta.subCuentas.each{ cuenta->
			def found=SaldoPorCuentaContable.findByEmpresaAndCuentaAndEjercicioAndMes(empresa,cuenta,ejercicio,mes)
			if(found)
				saldos.add(found)
		}
		return saldos
	}

	def generar(Empresa empresa,Integer ejercicio,Integer mes){
		def cuentas=CuentaContable.findAllByEmpresa(empresa)
		cuentas.each{ cuenta->
			def found=SaldoPorCuentaContable.findByEmpresaAndCuentaAndEjercicioAndMes(empresa,cuenta,ejercicio,mes)
			if(!found){
				found=new SaldoPorCuentaContable(empresa:empresa,cuenta:cuenta,ejercicio:ejercicio,mes:mes)
				found.save failOnError:true
			}
		}
	}

    def actualizarSaldos(int year,int mes){
		
		def cuentas=CuentaContable.findAllByDetalle('false')
		
		log.info 'Actualizando saldos para cuentas en el periodo: '+year+'/'+mes
		cuentas.each{ c->
			c.subCuentas.each{
				//println 'Procesando cuenta: '+it.clave
				actualizarSaldo(year,mes,it)
			}
			actualizarSaldo(year,mes,c)
			
		}
	}
	
	def actualizarSaldo(int year,int mes, def cuenta){
		
		def calendar=Calendar.getInstance()
		calendar.set(Calendar.MONTH,mes-1)
		calendar.set(Calendar.YEAR, year)
		calendar.set(Calendar.DATE,1)
		
		def fecha=calendar.getTime().inicioDeMes()
		//def fecha=new Date(2013,8,1)
		//println 'Actualizando saldo para cuenta '+cuenta+' Per:'+mes+' /'+year+ 'icio de mes: '+fecha
		if(cuenta.detalle){
			
			def saldoInicial=0
			if(mes==1){
				def cierreAnual=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year-1,13)
				log.info 'SaldoInicial obtenido: '+cierreAnual
				saldoInicial=cierreAnual.saldoFinal
			}else{
				saldoInicial=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year,mes-1)?.saldoFinal?:0.0
			}
			def row=PolizaDet
				.executeQuery("select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta=? and year(d.poliza.fecha)=? and month(d.poliza.fecha)=? and d.poliza.tipo!=?"
				,[cuenta,year,mes,'CIERRE_ANUAL'])
				
			def debe=row.get(0)[0]?:0.0
			def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			log.info "Cuenta $cuenta.clave Saldo inicial:$saldoInicial Debe:$debe Haber:$haber"
			
			saldo.fecha=fecha
			saldo.cierre=fecha
			saldo.saldoInicial=saldoInicial
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			def res=saldo.save(failOnError:true)
			//println res
		}else{
			//println 'Actualizando saldo para cuenta de mayor: '+cuenta
			def saldoInicial=0
			if(mes==1){
				def cierreAnual=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year-1,13)
				saldoInicial=cierreAnual.saldoFinal
			}else{
				saldoInicial=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year,mes-1)?.saldoFinal?:0.0
				
			}
			def row=PolizaDet.executeQuery(
				"select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta.padre=? and year(d.poliza.fecha)=? and month(d.poliza.fecha)=? and d.poliza.tipo!=?"
				,[cuenta,year,mes,'CIERRE_ANUAL'])
		
			//println 'Saldo inicial: '+saldoInicial.get(0)
			def debe=row.get(0)[0]?:0.0
			def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			
			saldo.fecha=fecha
			saldo.cierre=fecha
			saldo.saldoInicial=saldoInicial
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			saldo.save(failOnError:true)
		}
		
	}
	
	def reclasificarMovimientos(SaldoPorCuentaContable saldo,CuentaContable destino,def partidas){
		Set cuentas=[]
		partidas.each{
			
			def polizaDet=PolizaDet.get(it.toLong())
			cuentas.add(polizaDet.cuenta)
			println 'Reclasificando: '+it
			polizaDet.cuenta=destino
			polizaDet.descripcion+="(Rec)"
			polizaDet.save()
		}
		cuentas.add(destino)
		
		//Actualizamos los saldos
		cuentas.each{
			if(it.padre)
				actualizarSaldo(saldo.year,saldo.mes,it.padre)
		}
	}
	
	def cierreAnual(year){
		def cuentas=CuentaContable.findAllByDetalle('false')
		println 'Generando cierre para : '+year
		cuentas.each{ c->
			c.subCuentas.each{
				//println 'Procesando cuenta: '+it.clave
				cierre(year,year.toYear(),it)
			}
			//println 'Actualizando saldo para la cuenta de mayor: '+c.clave
			cierre(year,year.toYear(),c)
			
		}
	}
	
	def cierre(Date fecha,int year, def cuenta){
		
		//def fecha=calendar.getTime().inicioDeMes()
		//def fecha=new Date(2013,8,1)
		
		//def fecha=new SimpleDateFormat("yyyy").parse(year.to)
		println 'Cerrando cuenta'+cuenta+' Per:'+year
		if(cuenta.detalle){
			
			//println 'Actualizando saldo para cuenta: '+cuenta
			def saldoInicial=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year,12)
			assert saldoInicial ,'No existe el saldo inicial para la cuenta: '+cuenta+' año: '+year+ ' mes '+12
				//.executeQuery("select sum(d.debe-d.haber) from PolizaDet d where d.cuenta=? and year(d.poliza.fecha)<?",[cuenta,year])
			
			/*
			def row=PolizaDet.executeQuery(
				"select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta=? and year(d.poliza.fecha)=? "
				,[cuenta,year])
			*/
			
			//def debe=row.get(0)[0]?:0.0
			def debe=0.0
			def haber=0.0
			//def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:13])
			saldo.fecha=fecha
			saldo.cierre=fecha
			saldo.saldoInicial=saldoInicial.saldoFinal
			//saldo.saldoInicial=saldoInicial.get(0)?:0.0
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			def res=saldo.save(failOnError:true)
			println res
		}else{
			//println 'Actualizando saldo para cuenta de mayor: '+cuenta
			//def saldoInicial=PolizaDet.executeQuery("select sum(d.debe-d.haber) from PolizaDet d where d.cuenta.padre=? and year(d.poliza.fecha)<?",[cuenta,year])
			
		def saldoInicial=SaldoPorCuentaContable.findByCuentaAndYearAndMes(cuenta,year,12)
		assert saldoInicial ,'No existe el saldo inicial para la cuenta: '+cuenta+' año: '+year+ ' mes '+12
		println 'Saldo inicial localizado: '+saldoInicial.id
		
			//println 'Saldo inicial: '+saldoInicial.get(0)
			//def debe=row.get(0)[0]?:0.0
			def debe=0.0
			//def haber=row.get(0)[1]?:0.0
			def haber=0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:13])
			
			saldo.fecha=fecha
			saldo.cierre=fecha
			//saldo.saldoInicial=saldoInicial.get(0)?:0.0
			saldo.saldoInicial=saldoInicial.saldoFinal
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			saldo.save(failOnError:true)
		}
		
	}
	
	def actualizarCierreAnual(int year){
		println 'Actualizando cierre anual Year: '+year
		
		def saldos=SaldoPorCuentaContable.findAllByYearAndMes(year,13)
		for(SaldoPorCuentaContable saldo:saldos){
			def cuenta =saldo.cuenta
			actualizarCierreAnual(year,cuenta)
		}
	}
	
	def actualizarCierreAnual(int year,def cuenta){
		
		def mes=13
		
		if(cuenta.detalle){
			
			
			def row=PolizaDet
				.executeQuery("select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta=? and year(d.poliza.fecha)=?  and d.poliza.tipo=?"
				,[cuenta,year,'CIERRE_ANUAL'])
			
			
			def debe=row.get(0)[0]?:0.0
			def haber=row.get(0)[1]?:0.0
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			def res=saldo.save(failOnError:true)
			println " Cuenta: $cuenta.clave debe:$debe  haber:$haber"
		}else{
			
			def row=PolizaDet.executeQuery(
				"select sum(d.debe),sum(d.haber) from PolizaDet d where d.cuenta.padre=? and year(d.poliza.fecha)=? and d.poliza.tipo=?"
				,[cuenta,year,'CIERRE_ANUAL'])
		
			def debe=row.get(0)[0]?:0.0
			def haber=row.get(0)[1]?:0.0
			println " Cuenta: $cuenta.clave debe:$debe  haber:$haber"
			def saldo=SaldoPorCuentaContable.findOrCreateWhere([cuenta:cuenta,year:year,mes:mes])
			saldo.debe=debe
			saldo.haber=haber
			saldo.saldoFinal=saldo.saldoInicial+debe-haber
			saldo.save(failOnError:true)
		}
		
	}
}

