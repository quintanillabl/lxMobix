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


@Transactional
class PolizaDePagoGastosService extends AbstractProcesador{

	def generar(def empresa,def fecha,def procesador){

		def pagos = Pago.findAll("from Pago p where p.empresa=? and date(p.fecha)=?",[empresa,fecha])
		def polizas=[]
		def subTipo=procesador.nombre
		def tipo=procesador.tipo

		pagos.each{ pago ->
				
				//def poliza = find(empresa,subTipo,fecha,pago.class.name,pago.id)
			def poliza = Poliza.find(
				"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=? and p.entidad=? and p.origen=?",
				[empresa,subTipo,fecha,pago.class.name,pago.id])

			if (poliza) {
				poliza.partidas.clear()
				log.info "Actualizando poliza ${subTipo }"+fecha.format('dd/MM/yyyy');
				procesar(poliza,pago)
		        cuadrar(poliza)
				poliza=polizaService.update(poliza)

			} else {
				log.info "GENERANDO poliza ${subTipo } "+fecha.format('dd/MM/yyyy');
				poliza=build(empresa,fecha,tipo,subTipo)
				poliza.entidad=pago.class.name
				poliza.origen=pago.id
		        procesar(poliza,pago)
		        poliza.actualizar()
		        cuadrar(poliza)
				poliza=polizaService.save(poliza)
			}
			polizas << poliza
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
				tp='CH-'+pago.referencia
				break
			case FormaDePago.TRANSFERENCIA:
				tp='TR-'+pago?.cheque?.folio
				break
			default:
			break
		}

		poliza.concepto="${tp} ${pago.aFavor}"
		
		def descripcion=poliza.concepto+' '+pago.requisicion.comentario

		def referencia=pago.referencia

		pago.aplicaciones.each { aplicacion ->

			def gasto = aplicacion.cuentaPorPagar
			def desc = "F. ${gasto.folio} (${gasto.fecha.text()})  ${pago.requisicion.comentario}"
			
			cargoAGastos( poliza,aplicacion,desc,referencia)
			cargoAIvaAcreditable( poliza,aplicacion,desc,referencia)
			def cxp = aplicacion.cuentaPorPagar
			if(cxp.retensionIsr || cxp.retensionIva){
				abonoARetenciones(poliza,gasto,cxp,pago,referencia)
			}

		}
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
			

			cargoA(poliza,
				gastoDet.cuentaContable,
				gastoDet.importe,
				descripcion,
				'PAGO',
				referencia,
				gasto
			)

		}
	}

	def cargoAIvaAcreditable(def poliza,def aplicacion,def descripcion,def referencia){

		def gasto = aplicacion.cuentaPorPagar
		
		cargoA(
			poliza,
			IvaAcreditable(poliza.empresa),
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
				desc += "F. ${gasto.folio} ${gasto.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
			}
		}

		def det=new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
		    debe:0.0,
		    haber:pago.importe.abs(),
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'PAGO',
		    referencia:referencia,
		    origen:pago.id.toString(),
		    entidad:pago.class.toString()
		)
		

		if(pago.formaDePago==FormaDePago.CHEQUE){
			
			def cheque=pago.cheque
			assert cheque,'Pago sin cheque registrado'
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
		poliza.addToPartidas(det)
	}

	def abonoARetenciones(def poliza,def gasto,def cxp,def pago,def referencia){
		def desc = "F. ${cxp.folio} ${cxp.fecha.text()} ${pago.aFavor} ${pago.requisicion.comentario}"
		if(cxp.retensionIsr){
			cargoA(
				poliza,
				IvaRetenidoPendient(poliza.empresa),
				cxp.retensionIva.abs(),
				desc,
				'PAGO',
				referencia,
				cxp
			)
		}
		if(cxp.retensionIva){
			abonoA(
				poliza,
				ImpuestoRetenidoDeIva(poliza,empresa),
				cxp.retensionIva.abs(),
				desc,
				'PAGO',
				referencia,
				cxp
			)
		}
	}

	def find(def empresa, String subTipo, Date fecha,String entidad,Long origen){
    	log.info "Buscando poliza ${subTipo} $fecha $empresa $entidad $origen"
		
		def found = Poliza.find(
			"from Poliza p where p.empresa=? and p.subTipo=? and date(p.fecha)=? and p.entidad=? and p.origen=?",
			[empresa,subTipo,fecha,entidad,origen])
		return found
	}

}
