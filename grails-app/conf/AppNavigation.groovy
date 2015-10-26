
import grails.plugin.springsecurity.SpringSecurityUtils



navigation={
	user{
		
		catalogos(){
			lineas(controller:'linea',action:'index')
			productos(controller:'producto',action:'index')
			inmuebles(controller:'inmueble',action:'index'
				,titleText:'Inmuebles'
				//,enabled:SpringSecurityUtils.ifAllGranted('CONTABILIDAD')
				)
			clientes(controller:'cliente',action:'index')
			proveedores(controller:'proveedor',action:'index')
			cuentasSat(controller:'cuentaSat',action:'index')
			bancosSat(controller:'bancoSat',action:'index')

		}
		operaciones(){
			arrendamientos(controller:'arrendamiento',action:'index')
			ventas(controller:'venta',action:'index')
			cobros(controller:'cobro',action:'index',titleText:'Aplicación de cobros')
			compras(controller:'compra',action:'index')
			gastos(controller:'gasto',action:'index')
			requisiciones(controller:'requisicion',action:'index')
			//cuentasPorPagar()
			cuentasPorCobrar(titleText:'CxC')
			cfdis(controller:'cfdi',action:'index')
			retenciones(controller:'cfdiRetenciones',action:'index',titleText:'Retenciones')
		}
		contabilidad(){
			cuentasContables(controller:'cuentaContable',action:'index',titleText:'Cuentas')
			saldos(controller:'saldoPorCuentaContable',action:'index')
			polizas(controller:'poliza',action:'index')
			balanza(controller:'balanza',action:'index')
			reportes(controller:'contabilidad',action:'index')
			cierreAnual(controller:'poliza',action:'cierreAnual')
			contabilidadElectronica(controller:'contabilidadElectronica',action:'index')
		}
		tesoreria(){
			tipoDeCambio(controller:'tipoDeCambio',actio:'index')
			bancos(controller:'banco',action:'index')
			cuentas(controller:'cuentaBancaria',action:'index')
			movimientos(controller:'movimientoDeCuenta',action:'index')
			pagos(controller:'pago',action:'index')
			cobros(controller:'cobro',action:'index')
			saldos(controller:'saldoPorCuentaBancaria',action:'index')
			cheques(controller:'cheque',action:'index')
			comisiones(controller:'comision',action:'index')
			inversiones(controller:'inversion',action:'index')
		}
		info(view:'info')
	}
	
}