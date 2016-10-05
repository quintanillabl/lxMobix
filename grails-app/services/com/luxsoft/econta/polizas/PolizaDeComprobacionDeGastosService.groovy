package com.luxsoft.econta.polizas




import com.luxsoft.lx.cxp.Gasto
import com.luxsoft.lx.contabilidad.*
import static com.luxsoft.econta.polizas.PolizaUtils.*
import org.apache.commons.lang.StringUtils

class PolizaDeComprobacionDeGastosService extends AbstractProcesador{

    def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','COMPROBACION_GASTOS',fecha)
	}

	void procesar(def poliza){
		def empresa=poliza.empresa
    	def fecha=poliza.fecha

    	def gastos=Gasto.findAll(
    		"from Gasto g where g.empresa=? and date(g.fecha) = ? and g.gastoPorComprobar = true and g.concepto!='COMISIONES_BANCARIAS' and g.uuid!=null",
    		[empresa,fecha])

    	gastos.each{ gasto ->
    		def desc = "Prov F:${gasto.folio} (${gasto.fecha}) ${gasto.proveedor.nombre} "
    		cargoAGastos(poliza,gasto,desc)
    		cargoAIvaPendienteDeAcreditar(poliza,gasto,desc)
    		abonoAAcredoresDiversos(poliza,gasto,desc)
    	}
    	
    	poliza.concepto="COMPROBACION_GASTOS ${poliza.fecha.format('dd/MM/yyyy')}"
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
			    asiento:'COMPROBACION_GASTOS',
			    referencia:'F:'+gasto.folio,
			    origen:gasto.id.toString(),
			    entidad:gasto.class.toString()
			)
			addComplementoCompra(polizaDet,gasto)
			poliza.addToPartidas(polizaDet)
		}
	}

	def cargoAIvaPendienteDeAcreditar(def poliza,def gasto,def descripcion){
		log.info 'Cargo a iva pendiente de acreditar'
		def cuenta = IvaAcreditable(poliza.empresa)
		def polizaDet = new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
			debe:gasto.impuesto,
		    haber:0.0,
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'COMPROBACION_GASTOS',
		    referencia:'F:'+gasto.folio,
		    origen:gasto.id.toString(),
		    entidad:gasto.class.toString()
		)
		addComplementoCompra(polizaDet,gasto)
		poliza.addToPartidas(polizaDet)
	}

	def abonoAAcredoresDiversos(def poliza,def gasto,def descripcion){
		log.info 'Abono a acredores diversos'
		assert gasto.proveedor.subCuentaOperativa, "No existe la subCuenta operativa para el proveedor: $gasto.proveedor"
        def cuenta = CuentaContable.buscarPorClave(poliza.empresa,'107-' + gasto.proveedor.subCuentaOperativa)
        assert cuenta, 'No existe cuenta acredora ya sea para el proveedor o la generica provedores diversos'
		assert cuenta, "El proveedor ${gasto.proveedor} no tiene registrada la cuenta contable (acredora)"
		def polizaDet = new PolizaDet(
			cuenta:cuenta,
			concepto:cuenta.descripcion,
			debe:0.0,
		    haber:gasto.total,
		    descripcion:StringUtils.substring(descripcion,0,255),
		    asiento:'COMPROBACION_GASTOS',
		    referencia:'F:'+gasto.folio,
		    origen:gasto.id.toString(),
		    entidad:gasto.class.toString()
		)
		addComplementoCompra(polizaDet,gasto)
		poliza.addToPartidas(polizaDet)
	
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
