package com.luxsoft.lx.contabilidad

import grails.test.mixin.TestFor
import spock.lang.Specification

import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.Empresa

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Poliza)
@Mock([Poliza,Empresa])
@Build([Poliza,Empresa])
class PolizaSpec extends Specification {

	def empresa

    def setup() {
    	empresa=Empresa.build(clave:'DEMO')
    }

    def cleanup() {
    }

    void "Debe persistir una poliza"() {
    	setup:'Dada una poliza'
    	def concepto='VENTAS DIARIAS'
    	def poliza=Poliza.buildWithoutSave(empresa:empresa,tipo:'DIARIO',concepto:concepto)
    	when:'Salvamos la poliza'
    	poliza.save()
    	then:'La poliza persiste correctamente en la base de datos'
    	poliza.errors.errorCount==0
    	poliza.id
    	Poliza.get(poliza.id).concepto==concepto
    	println poliza

    }
}
