package com.luxsoft.econta.polizas

import grails.transaction.Transactional
import static com.luxsoft.econta.polizas.PolizaUtils.*

import com.luxsoft.lx.tesoreria.Comision
import com.luxsoft.lx.contabilidad.Poliza

class PolizaDeComisionesBancariasService extends AbstractProcesador{


    def generar(def empresa,Date fecha){
        return generar(empresa,'DIARIO','COMISIONES_BANCARIAS',fecha)
    }
    
    void procesar(def poliza){
        def empresa=poliza.empresa
        def fecha=poliza.fecha
        log.info "Generando poliza de comisiones bancarias $empresa ${fecha.text()} "
        def comisiones=Comision.findAll("from Comision c where c.empresa=? and date(c.fecha)=?",
            [empresa,fecha])
        comisiones.each{ comision ->
            def desc="$comision.cuenta.numero ($comision.cuenta.nombre) ${comision.fecha.format('dd/MMMM/yyyy')}"
            cartoADeudores(poliza,comision,desc)
            cargoAIvaAcreditable(poliza,comision,desc)
            abonoABancos(poliza,comision,desc)
        }
        poliza.concepto="Comisiones bancarias  ${fecha.format('dd/MMMM/yyyy')}"
    }

    

    def cartoADeudores(def poliza,def comision,def descripcion){
    	cargoA(poliza,
    		Deudores(poliza.empresa),
    		comision.comision.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
    }

    def cargoAIvaAcreditable(def poliza,def comision,def descripcion){
    	cargoA(poliza,
    		IvaAcreditable(poliza.empresa),
    		comision.impuesto.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
    }

    def abonoABancos(def poliza,def comision,def descripcion){
    	abonoA(
    		poliza,
    		comision.cuenta.cuentaContable,
    		comision.comision.abs(),
    		descripcion,
    		'COMISION',
    		comision.referenciaBancaria,
    		comision
    	)
        abonoA(
            poliza,
            comision.cuenta.cuentaContable,
            comision.impuesto.abs(),
            descripcion,
            'COMISION',
            comision.referenciaBancaria,
            comision
        )
    }

   
 
}
