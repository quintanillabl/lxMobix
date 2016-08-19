package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import com.luxsoft.lx.tesoreria.Pago
import com.luxsoft.lx.cxp.GastoDet
import com.luxsoft.lx.contabilidad.CuentaContable
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils
import com.luxsoft.lx.core.FormaDePago
import com.luxsoft.lx.contabilidad.*
import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.tesoreria.Cheque


@Transactional
class PolizaDePagoGastosService extends AbstractProcesador{



	def generar(def empresa,def fecha,def procesador){

		def pagos = Pago.findAll("from Pago p where p.empresa=? and date(p.fecha)=?  order by p.folio",[empresa,fecha])
		def polizas=[]
		def subTipo=procesador.nombre
		def tipo=procesador.tipo

		pagos.each{ pago ->
			
			//println 'Procesando pago:'+pago
			log.info 'Procesando pago: '+pago
			def cancelados = Cheque.findAllByEgresoAndCancelacionIsNotNull(pago)
			if(cancelados){
				polizas<<procesarCancelados(cancelados,empresa,fecha,tipo,subTipo)
			}

				//def poliza = find(empresa,subTipo,fecha,pago.class.name,pago.id)
			def poliza = Poliza.find(
				"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=? and p.entidad=? and p.origen=?",
				[empresa,subTipo,fecha,pago.class.name,pago.id])

			if(pago && pago.importe.abs()>0.0){
				if (poliza) {
					if(!poliza.manual){
						poliza.partidas.clear()
						log.info "Actualizando poliza ${subTipo }"+fecha.format('dd/MM/yyyy');
						procesar(poliza,pago)
						poliza.actualizar()
			        	cuadrar(poliza)
			        	depurar(poliza)
						poliza=polizaService.update(poliza)
					}
				} else {
					log.info "GENERANDO poliza ${subTipo } "+fecha.format('dd/MM/yyyy');
					poliza=build(empresa,fecha,tipo,subTipo)
					poliza.entidad=pago.class.name
					poliza.origen=pago.id
		        	procesar(poliza,pago)
		        	poliza.actualizar()
		        	cuadrar(poliza)
		        	depurar(poliza)
					poliza=polizaService.save(poliza)
				}

				polizas << poliza
			}

			
			
		}
        //return generar(empresa,procesador.tipo,procesador.nombre,fecha)
        return polizas
    }



	void procesar( def poliza,def pago){

		def empresa = poliza.empresa

		def fecha = poliza.fecha
		
		log.info "Generando poliza de egreso pago  ${fecha.format('dd/MM/yyyy')} Pago:${pago}"
		
		def tp=''
		switch(pago.formaDePago) {
			case FormaDePago.CHEQUE:
				if(pago.cheque != null){
					tp='CH-'+pago.cheque.folio
					break
				}else{
					def cheque = Cheque.where{egreso == pago}.find()
					tp='CH-'+cheque.folio
					break
				}
				
			case FormaDePago.TRANSFERENCIA:
				tp='TR-'+pago?.referencia
				break
			default:
			break
		}

		poliza.concepto="${tp} ${pago.aFavor}"
		
		def descripcion=poliza.concepto+' '+pago.requisicion.comentario

		def referencia=pago.referencia


		pago.aplicaciones.each { aplicacion ->

			def gasto = aplicacion.cuentaPorPagar
			def desc = "F. ${gasto.folio} (${gasto.fecha.text()}) ${pago.aFavor} ${pago.requisicion.comentario}"
			
			if(!fecha.isSameMonth(aplicacion.cuentaPorPagar.fecha) ){
				//Cancelacion de la provision
				cargoAcredoresDiversos(poliza, aplicacion,desc, referencia)
				
				cargoAIvaAcreditable( poliza,aplicacion,desc,referencia)
				abonoIvaAcreditable(poliza,aplicacion,desc,referencia)
			} else {
				cargoAGastos( poliza,aplicacion,desc,referencia)
				cargoAIvaAcreditable( poliza,aplicacion,desc,referencia)
			}
			//cargoAIvaAcreditable( poliza,aplicacion,desc,referencia)
			def cxp = aplicacion.cuentaPorPagar
			if(cxp.retensionIsr || cxp.retensionIva){				
				cargoAbonoARetencionIva(poliza,gasto,cxp,pago,referencia)
				abonoARetencionIsr(poliza,gasto,cxp,pago,referencia)
			}

		}
		if(pago.importe.abs()>0)
			abonoABancos(poliza,pago,descripcion,referencia)
		
	}

	def cargoAGastos(def poliza,def aplicacion,descripcion,def referencia){
		
		//def gastoDet = GastoDet.findByCuentaPorPagar(aplicacion.cuentaPorPagar)
		def pago=aplicacion.pago

		def gasto = aplicacion.cuentaPorPagar
		if( gasto ) {
			//def desc = "F. ${gasto.folio} ${gasto.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
			def gastoDet = gasto.partidas.find{ it.cuentaContable }
			assert gastoDet,'No hay cuenta contable asignada para registrar el gasto '+gasto
			log.info 'Cargo a gasto :'+gastoDet
			/*
			cargoA(poliza,
				gastoDet.cuentaContable,
				gasto.subTotal,
				descripcion,
				'PAGO',
				referencia,
				gasto
			)
			*/
			
			def polizaDet = new PolizaDet(
				cuenta:gastoDet.cuentaContable,
				concepto:gastoDet.cuentaContable.descripcion,
				debe:gasto.subTotal,
			    haber:0.0,
			    descripcion:StringUtils.substring(descripcion,0,255),
			    asiento:'PAGO',
			    referencia:referencia,
			    origen:gasto.id.toString(),
			    entidad:gasto.class.toString()
			)
			if(gasto.uuid){
				//Compra nacional
				def compra = new TransaccionCompraNal(
					polizaDet:polizaDet,
					uuidcfdi:gasto.uuid,
					rfc: gasto.proveedor.rfc,
					montoTotal: gasto.total
				)
				polizaDet.compraNal = compra
			}
			poliza.addToPartidas(polizaDet)
			
			

		}
	}

	def cargoAcredoresDiversos(def poliza,def aplicacion,descripcion,def referencia){
		
		def pago=aplicacion.pago

		def gasto = aplicacion.cuentaPorPagar
		log.info 'PROVISION:  Cargo a acredores diversosgasto :'+gasto.total

		def cuenta=gasto.proveedor.cuentaContable?:AcredoresDiversos(poliza.empresa)
		assert cuenta, 'No existe cuenta acredora ya sea para el proveedor o la generica provedores diversos'
		/*
		cargoA(poliza,
			cuenta,
			gasto.total,
			descripcion,
			'PAGO',
			referencia,
			gasto
		)
		*/
		
		
		def polizaDet = new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
			debe:gasto.total,
		    haber:0.0,
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'PAGO',
		    referencia:referencia,
		    origen:gasto.id.toString(),
		    entidad:gasto.class.toString()
		)
		if(gasto.uuid){
			def compra = new TransaccionCompraNal(
				polizaDet:polizaDet,
				uuidcfdi:gasto.uuid,
				rfc: gasto.proveedor.rfc,
				montoTotal: gasto.total
			)
			polizaDet.compraNal = compra
		}
		
		poliza.addToPartidas(polizaDet)
		
	}

	def cargoAIvaAcreditable(def poliza,def aplicacion,def descripcion,def referencia){

		def gasto = aplicacion.cuentaPorPagar
		def impuesto = gasto.impuesto - gasto.retensionIva
		cargoA(
			poliza,
			IvaAcreditable(poliza.empresa),
			impuesto,
			descripcion,
			'PAGO',
			referencia,
			gasto
		)
	}

	def abonoIvaAcreditable(def poliza,def aplicacion,def descripcion,def referencia){

		def gasto = aplicacion.cuentaPorPagar
		
		abonoA(
			poliza,
			IvaPendienteDeAcreditar(poliza.empresa),
			gasto.impuesto,
			descripcion,
			'PAGO',
			referencia,
			gasto
		)
	}

	

	def abonoABancos(def poliza,def pago,descripcion,def referencia){
		def cuenta=pago.cuenta.cuentaContable
		assert cuenta,"La cuenta de banco ${pago.cuenta} no tiene cuenta contable asignada"
		def desc=""
		pago.aplicaciones.each{ aplicacion ->
			def gasto = aplicacion.cuentaPorPagar
			if(gasto){
				//desc += "F. ${gasto.folio} ${gasto.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
				desc += "F. ${gasto.folio} ${gasto.fecha.text()} ${pago.aFavor}"
			}
		}

		def det=new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
		    debe:0.0,
		    haber:pago.importe.abs(),
		    descripcion:poliza.concepto,
		    asiento:'PAGO',
		    referencia:referencia,
		    origen:pago.id.toString(),
		    entidad:pago.class.toString()
		)
		

		if(pago.formaDePago==FormaDePago.CHEQUE){
			
			def cheque=pago.cheque?pago.cheque:null
			assert cheque,'Pago sin cheque registrado'+pago.id
			assert cheque.cuenta
			assert pago.aFavor, 'No esta registrado aFavor de quien está el pago '+pago.id
			def rfc=pago.rfc?:pago.requisicion.proveedor.rfc
			def polCheque=new PolizaCheque(
				polizaDet:det,
				numero:cheque.folio.toString(),
				bancoEmisorNacional:cheque.cuenta.banco.bancoSat,
				cuentaOrigen:cheque.cuenta.numero,
				fecha:cheque.dateCreated,
				beneficiario:pago.aFavor,
				rfc:rfc,
				monto:pago.importe
			)
			det.cheque=polCheque
		}
		if(pago.formaDePago==FormaDePago.TRANSFERENCIA){
			
			if(pago.bancoDestino && pago.cuentaDestino){
				log.info('Generando registro de Transaccion transferencia SAT para pago: '+pago.id)
				assert pago.aFavor, 'No esta registrado aFavor de quien está el pago '+pago.id
				def rfc=pago.rfc?:pago.requisicion.proveedor.rfc
				def transferencia=new TransaccionTransferencia(
					polizaDet:det,
					bancoOrigenNacional:pago.cuenta.banco.bancoSat,
					cuentaOrigen:pago.cuenta.numero,
					fecha:pago.dateCreated,
					beneficiario:pago.aFavor,
					rfc:rfc,
					monto:pago.importe,
					bancoDestinoNacional: pago.bancoDestino,
					cuentaDestino: pago.cuentaDestino
				)
				det.transferencia=transferencia
			}
			
			
		}
		poliza.addToPartidas(det)
	}

	def abonoARetencionIsr(def poliza,def gasto,def cxp,def pago,def referencia){
		def desc = "F. ${cxp.folio} ${cxp.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
		if(cxp.retensionIsr){
			def concepto=gasto.concepto
			

			if(concepto=='HONORARIOS_ASIMILADOS'){
				abonoA(
					poliza,
					RetencionIsrHonorariosAsimilados(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PAGO',
					referencia,
					cxp
				)
			}
			else if(concepto == 'HONORARIOS_CON_RETENCION'){
				abonoA(
					poliza,
					RetencionIsrHonorarios(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PAGO',
					referencia,
					cxp
				)
			}
			else if(concepto == 'SERVICIOS_PROFESIONALES'){
				abonoA(
					poliza,
					RetencionIsrServiciosProfesionales(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PAGO',
					referencia,
					cxp
				)
					
			}
			else if(concepto == 'RETENCION_PAGOS'){
				abonoA(
					poliza,
					RetencionIsrDividendos(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PAGO',
					referencia,
					cxp
				)
					
			}
			
		}
	}

	def cargoAbonoARetencionIva(def poliza,def gasto,def cxp,def pago,def referencia){
		
		
		if(cxp.retensionIva){
			def desc = "F. ${cxp.folio} ${cxp.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
			
			cargoA(
				poliza,
				IvaRetenidoPendient(poliza.empresa),
				cxp.retensionIva.abs(),
				desc,
				'PAGO',
				referencia,
				cxp
			)

			abonoA(
				poliza,
				ImpuestoRetenidoDeIva(poliza.empresa),
				cxp.retensionIva.abs(),
				desc,
				'PAGO',
				referencia,
				cxp
			)
		}
	}

	def procesarCancelados(def cancelados,def empresa,def fecha,def tipo,def subTipo){

		cancelados.each{ cheque ->

			log.info "GENERANDO Poliza de cheque cancelado $cheque"
			def pago=cheque.egreso
			def poliza = Poliza.find(
				"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=? and p.entidad=? and p.origen=?",
				[empresa,subTipo,fecha,cheque.class.name,cheque.id])
			
			if(!poliza){
				
				poliza=build(empresa,fecha,tipo,subTipo)
				poliza.entidad=cheque.class.name
				poliza.origen=cheque.id
				
			}

			poliza.partidas.clear()

			def tp='CH-'+cheque.folio

			poliza.concepto="${tp} CANCELADO ${pago.aFavor}"
			
			def descripcion=poliza.concepto

			def referencia=cheque.folio.toString()

			def cuenta=pago.cuenta.cuentaContable
			assert cuenta,"La cuenta de banco ${pago.cuenta} no tiene cuenta contable asignada"
			
			def det=new PolizaDet(
				cuenta:cuenta,
				concepto:cuenta.descripcion,
			    debe:0.0,
			    haber:0.0,
			    descripcion:StringUtils.substring(descripcion,0,255),
			    asiento:'CHEQUE',
			    referencia:referencia,
			    origen:cheque.id.toString(),
			    entidad:cheque.class.toString()
			)
			 
			/*
			assert cheque.cuenta
			//assert pago.aFavor, 'No esta registrado aFavor de quien está el pago '+pago.id
			def rfc=pago.rfc?:pago.requisicion.proveedor.rfc
			def polCheque=new PolizaCheque(
				polizaDet:det,
				numero:cheque.folio.toString(),
				bancoEmisorNacional:cheque.cuenta.banco.bancoSat,
				cuentaOrigen:cheque.cuenta.numero,
				fecha:cheque.dateCreated,
				beneficiario:pago.aFavor,
				rfc:rfc,
				monto:0.0
			)
			det.cheque=polCheque
			*/
			poliza.addToPartidas(det)
			poliza=polizaService.save(poliza)
		}
			

			
	}

}
