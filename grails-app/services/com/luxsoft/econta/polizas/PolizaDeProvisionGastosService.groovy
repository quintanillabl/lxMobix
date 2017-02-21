package com.luxsoft.econta.polizas

import com.luxsoft.lx.cxp.Gasto
import com.luxsoft.lx.contabilidad.*
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils
import com.luxsoft.lx.utils.MonedaUtils

class PolizaDeProvisionGastosService extends AbstractProcesador{

    def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','PROVISION_GASTOS',fecha)
	}

	void procesar(def poliza){
		def empresa=poliza.empresa
    	def fecha=poliza.fecha

    	def gastos=Gasto.findAll("from Gasto g where g.empresa=? and date(g.fecha) = ? and g.gastoPorComprobar = false",
    		[empresa,fecha]) 

    	gastos.each{ gasto ->
    		def desc = "Prov ${gasto.folio?'F:'+gasto.folio:''} (${gasto.fecha}) ${gasto.proveedor.nombre} "
    		cargoAGastos(poliza,gasto,desc)
    		cargoAIvaPendienteDeAcreditar(poliza,gasto,desc)
    		abonoAAcredoresDiversos(poliza,gasto,desc)
    		abonoARetencionIsr(poliza,gasto,desc)
    		cargoAbonoARetencionIva(poliza,gasto,desc)
    	}
    	
    	poliza.concepto="PROVISION ${poliza.fecha.format('dd/MM/yyyy')}"
	}

	def cargoAGastos(def poliza,def gasto,def descripcion){
		log.info 'Cargo a gastos'
		gasto.partidas.each { det ->
			assert det.cuentaContable,"Detalle del gasto sin cuenta contable ${gasto.id}"
			def cuenta = det.cuentaContable
			def polizaDet = new PolizaDet(
				cuenta:cuenta,
				concepto:cuenta.descripcion,
				debe:det.importe,
			    haber:0.0,
			    descripcion:StringUtils.substring(descripcion,0,255),
			    asiento:'PROVISION',
			    referencia:gasto.folio?"F: ${gasto.folio} ":"",
			    origen:gasto.id.toString(),
			    entidad:gasto.class.toString()
			)
			addComplementoCompra(polizaDet,gasto)
			poliza.addToPartidas(polizaDet)
		}
	}

	def cargoAIvaPendienteDeAcreditar(def poliza,def gasto,def descripcion){
		log.info 'Cargo a iva pendiente de acreditar'
		def cuenta = IvaPendienteDeAcreditar(poliza.empresa)
		def impuesto = gasto.impuesto 
		if(gasto.retensionIva>0){
			impuesto = impuesto - gasto.retensionIva
		}
		def polizaDet = new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
			debe:impuesto,
		    haber:0.0,
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'PROVISION ',
		    referencia:gasto.folio?"F: ${gasto.folio} ":"",
		    origen:gasto.id.toString(),
		    entidad:gasto.class.toString()
		)
		addComplementoCompra(polizaDet,gasto)
		poliza.addToPartidas(polizaDet)
	}

	def abonoAAcredoresDiversos(def poliza,def gasto,def descripcion){
		log.info 'Abono a acredores diversos'
		//def cuenta = AcredoresDiversos(poliza.empresa)
		def cuenta = gasto.proveedor?.cuentaContable
		assert cuenta, "El proveedor ${gasto.proveedor} no tiene registrada la cuenta contable (acredora)"
		def polizaDet = new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
			debe:0.0,
		    haber:gasto.total,
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'PROVISION',
		    referencia:gasto.folio?"F: ${gasto.folio} ":"",
		    origen:gasto.id.toString(),
		    entidad:gasto.class.toString()
		)
		addComplementoCompra(polizaDet,gasto)
		poliza.addToPartidas(polizaDet)
		
	}

	def abonoARetencionIsr(def poliza,def cxp, def desc){
		def referencia = 'F:'+cxp.folio
		def det = null
		if(cxp.retensionIsr){
			def concepto=cxp.concepto
			
			if(concepto=='HONORARIOS_ASIMILADOS'){
				det = abonoA(
					poliza,
					RetencionIsrHonorariosAsimilados(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PROVISION',
					referencia,
					cxp
				)
			}
			else if(concepto == 'HONORARIOS_CON_RETENCION'){
				det = abonoA(
					poliza,
					RetencionIsrHonorarios(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PROVISION',
					referencia,
					cxp
				)
			}
			else if(concepto == 'SERVICIOS_PROFESIONALES'){
				det = abonoA(
					poliza,
					RetencionIsrServiciosProfesionales(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PROVISION',
					referencia,
					cxp
				)
					
			}
			else if(concepto == 'RETENCION_PAGOS'){
				det = abonoA(
					poliza,
					RetencionIsrDividendos(poliza.empresa),
					cxp.retensionIsr.abs(),
					desc,
					'PROVISION',
					referencia,
					cxp
				)
			}
		}
		if(det!= null){
			addComplementoCompra(det,cxp)
		}
	}

	def cargoAbonoARetencionIva(def poliza,def cxp,def desc){
		def referencia = 'F:'+cxp.folio
		def det = null
		if(cxp.retensionIva){
			det = cargoA(
				poliza,
				IvaRetenidoPendient(poliza.empresa),
				cxp.retensionIva.abs(),
				desc,
				'PROVISION RET',
				referencia,
				cxp
			)
			addComplementoCompra(det,cxp)

			det = abonoA(
				poliza,
				ImpuestoRetenidoDeIva(poliza.empresa),
				cxp.retensionIva.abs(),
				desc,
				'PROVISION RET',
				referencia,
				cxp
			)
			addComplementoCompra(det,cxp)
		}
		
	}

	def cargoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		def det=new PolizaDet(
			cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:importe.abs(),
            haber:0.0,
            descripcion:StringUtils.substring(descripcion,0,255),
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
		poliza.addToPartidas(det)
		return det;
	}

	def abonoA(def poliza,def cuenta,def importe,def descripcion,def asiento,def referencia,def entidad){
		def det=new PolizaDet(
			cuenta:cuenta,
        	concepto:cuenta.descripcion,
            debe:0.0,
            haber:importe.abs(),
            descripcion:StringUtils.substring(descripcion,0,255),
            asiento:asiento,
            referencia:referencia,
            origen:entidad.id.toString(),
            entidad:entidad.class.toString()
		)
		poliza.addToPartidas(det)
		return det
	}


	def addComplementoCompra(def polizaDet, def gasto){
		log.info("Agregando complenento de compra nacional para gasto: $gasto  UUID:$gasto.uuid")
		if(gasto.uuid){
			def compra = new TransaccionCompraNal(
				polizaDet:polizaDet,
				uuidcfdi:gasto.uuid,
				rfc: gasto.proveedor.rfc,
				montoTotal: gasto.total
			)
			polizaDet.compraNal = compra
		}

	}

}
