package com.luxsoft.econta.polizas

import com.luxsoft.lx.contabilidad.*

class PolizaUtils {


	public static OtrosIngresos = CuentaContable.findByClave('704-0001')
	public static IvaTrasladadoPendiente = CuentaContable.findByClave('209-0001')
	public static IvaAcreditable = CuentaContable.findByClave('118-0001')
	
	public static void buildProcesadores(){
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'FACTURACION',
			label:'Facturación',
			tipo:'DIARIO',
			service:'polizaDeFacturacionService')

		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'PAGO_GASTOS',
			label:'Pagos (Gastos)',
			tipo:'EGRESO',
			service:'polizaDePagoGastosService')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'PAGO_IMPUESTOS',
			label:'Pagos (Impuestos)',
			tipo:'EGRESO',
			service:'polizaDePagoDeImpuestos')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'DEPRECIACIONES',
			label:'Depreciaciones',
			tipo:'DIARIO',
			service:'polizaDeDepreciaciones')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'AMORTIZACIONES',
			label:'Amortizaciones',
			tipo:'DIARIO',
			service:'polizaDeAmortizaciones')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'COMISIONES_BANCARIAS',
			label:'Comisiones',
			tipo:'EGRESO',
			service:'polizaDeComisionesBancarias')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'INVERSIONES',
			label:'Inversiones',
			service:'polizaDeInversiones')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'DETERMINACION_DE_IVA',
			label: 'Determinación (IVA)',
			tipo:'DIARIO',
			service:'polizaDeDeterminacionDelIva')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'PROVISION_ISR',
			label: 'Provisión (ISR)',
			service:'polizaDeProvisionIsr')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'TRASPASO_IVA_DETERMINADO',
			label: 'Traspaso (IVA)',
			service:'polizaDeTraspasoDeIvaDeterminado')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'PROVISION_GASTOS',
			label: 'Provision (Gastos)',
			service:'polizaDeProvisionGastos')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'HONORARIOS',
			label: 'Honorarios',
			service:'polizaDeHonorarios')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'CANCELACION_DE_PROVISION_GASTOS',
			label: 'Cancelacion (Prov IVA)',
			service:'polizaDeCancelacionDeProvisionGastos')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'CHEQUE_CANCELADO',
			label: 'Cancelacion (Cheques)',
			service:'polizaDeChequeCancelado')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'CANCELACION_DE_PROVISION_SEGUROS',
			label: 'Cancelación (Seguros)',
			service:'polizaDeCancelacionDeProvisionSeguros')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'IMPUESTO_PREDIAL',
			label: 'Predial',
			service:'polizaDeImpuestoPredial')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'HONORARIOS_ASIMILADOS_A_SALARIOS',
			label: 'Honorarios asimilados',
			service:'polizaDeHonorariosAsimiladosASalarios')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'COBRANZA',
			label: 'Cobranza',
			service:'polizaDeCobranza')
		
		ProcesadorDePoliza.findOrSaveWhere(
			nombre:'HONORARIOS_CON_RETENCIONES',
			label: 'Honorarios (Con ret)',
			service:'polizaDeHonorariosConRetenciones')
		
	}
}