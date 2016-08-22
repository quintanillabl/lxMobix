package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import static com.luxsoft.econta.polizas.PolizaUtils.*

import com.luxsoft.lx.tesoreria.Inversion
import com.luxsoft.lx.contabilidad.Poliza
import com.luxsoft.lx.contabilidad.TransaccionTransferencia
import com.luxsoft.lx.contabilidad.PolizaDet



class PolizaDeInversionesService extends AbstractProcesador {

	def generar(def empresa,Date fecha){
		return generar(empresa,'DIARIO','INVERSIONES',fecha)
	}

	void procesar(def poliza){
    	
    	def empresa=poliza.empresa
    	def fecha=poliza.fecha
    	log.info "Generando poliza de inversiones bancarias $empresa ${fecha.text()} "
    	def inversiones=Inversion.findAll("from Inversion i where i.empresa=? and date(i.fecha)=?",
    		[empresa,fecha])
    	
    	inversiones.each{ inversion ->
    		def desc=inversion.comentario
    		cargoABancos(poliza,inversion,desc)
    		abonoABancos(poliza,inversion,desc)
    		cargoDeInteresesABancarios(poliza,inversion,desc)
    		abonoAInteresesBancarios(poliza,inversion,desc)
    		cargoAIsr(poliza,inversion,desc)
    	}
    	poliza.concepto="Inversiones bancarias ${poliza.fecha.asPeriodoText()}"
    	
    }

    def cargoABancos(def poliza,def inversion,def descripcion){
    	if(inversion.importe.abs()){
    		cargoA(
    			poliza,
    			inversion.cuentaDestino.cuentaContable,
    			inversion.importe.abs(),
    			descripcion,
    			'INVERSION ',
    			' ',
    			inversion
    		)
    	}
    	
    }

    def abonoABancos(def poliza,def inversion,def descripcion){
    	if(inversion.importe.abs()){
            /*
    		abonoA(
    			poliza,
    			inversion.cuentaOrigen.cuentaContable,
    			inversion.importe.abs(),
    			descripcion,
    			'INVERSION ',
    			' ',
    			inversion
    		)
            */
            def det=new PolizaDet(
                cuenta:inversion.cuentaOrigen.cuentaContable,
                concepto:inversion.cuentaOrigen.cuentaContable.descripcion,
                debe:0.0,
                haber:inversion.importe.abs(),
                descripcion:descripcion,
                asiento:'INVERSION',
                referencia:'',
                origen:inversion.id.toString(),
                entidad:inversion.class.toString()
            )
            def bancoOrigen = inversion?.cuentaOrigen?.banco?.bancoSat
            def bancoDestino = inversion?.cuentaDestino?.banco?.bancoSat
            
            if(bancoDestino && bancoDestino){
                log.info('Generando registro de Transaccion transferencia SAT para pago: '+inversion.id)
                def rfc=inversion.empresa.rfc
                def transferencia=new TransaccionTransferencia(
                    polizaDet:det,
                    bancoOrigenNacional:bancoOrigen.clave,
                    cuentaOrigen:inversion.cuentaOrigen.numero,
                    fecha:inversion.fecha,
                    beneficiario:inversion.empresa.nombre,
                    rfc:rfc,
                    monto:inversion.importe.abs(),
                    bancoDestinoNacional: bancoDestino.clave,
                    cuentaDestino: inversion.cuentaDestino.numero
                )
                det.transferencia=transferencia
            }
            poliza.addToPartidas(det)
    	}
    	
    }

    def cargoDeInteresesABancarios(def poliza,def inversion,def descripcion){
    	if(inversion.rendimientoReal.abs()){
    		cargoA(
    			poliza,
    			inversion.cuentaOrigen.cuentaContable,
    			inversion.rendimientoReal.abs(),
    			descripcion,
    			'INT INVERSION ',
    			' ',
    			inversion
    		)
    	}
    	
    }

    def abonoAInteresesBancarios(def poliza,def inversion,def descripcion){
    	if(inversion.rendimientoReal.abs()+inversion.importeIsr.abs()){
    		abonoA(
    			poliza,
    			InteresesBancarios(poliza.empresa),
    			inversion.rendimientoReal.abs()+inversion.importeIsr.abs(),
    			descripcion,
    			'INT INVERSION ',
    			' ',
    			inversion
    		)
    	}
    	
    }

    def cargoAIsr(def poliza,def inversion,def descripcion){
    	if(inversion.importeIsr.abs()){
    		cargoA(
    			poliza,
    			IsrBancario(poliza.empresa),
    			inversion.importeIsr.abs(),
    			descripcion,
    			'ISR RET INVERSION ',
    			' ',
    			inversion
    		)
    	}
    	
    }

   
}
