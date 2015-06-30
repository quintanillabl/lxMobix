package com.luxsoft.lx.contabilidad

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

import com.luxsoft.lx.core.Empresa
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PolizaService)
@Mock([Poliza,PolizaDet,Empresa])
@Build([Poliza,PolizaDet,Empresa])
class PolizaServiceSpec extends Specification {

	def empresa

    def setup() {
    	empresa=Empresa.build(clave:'DEMO1')
    }

    def cleanup() {
    }

    void "Debe persistir correctamente una poliza"() {
    	given:'Una poliza valdida'
    	def poliza=buildPolizaDemo()
    	
    	when:'Salvamos la poliza'
    	poliza=service.save(poliza)
    	then:'La poliza persiste en la base de datos'
    	def found=Poliza.get(poliza.id)
    	found.partidas.size()==2
    	println found
    }

    void "Debe actualizar el importe de la poliza con los detalles"(){
		given:'Una poliza valdida'
		def poliza=buildPolizaDemo()
		
		when:'Salvamos la poliza'
		poliza=service.save(poliza)

		then:'Debe actualizar correctamente los totales de la poliza'
		def found=Poliza.get(poliza.id)
		found.debe==1000.00
		found.haber==1000.00
		

    }

    private Poliza buildPolizaDemo(){
    	def poliza=Poliza.buildWithoutSave(empresa:empresa,concepto:'VENTA DIARIA',tipo:'DIARIO',ejercicio:2015,mes:1)
    	PolizaDet.buildWithoutSave(poliza:poliza,concepto:'VENTA1',debe:1000.00)
    	PolizaDet.buildWithoutSave(poliza:poliza,concepto:'ARRENDAMIENTO',haber:1000.00)
    	return poliza
    }
}
