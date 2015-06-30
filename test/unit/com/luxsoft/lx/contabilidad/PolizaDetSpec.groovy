package com.luxsoft.lx.contabilidad

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(PolizaDet)
@Mock([PolizaDet,Poliza])
@Build([PolizaDet,Poliza])
class PolizaDetSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Alta de detalle"() {
    	setup:'Dada una poliza existente'
    	def poliza=Poliza.build(concepto:'VENTAS')

    	when:'Agregamos una partida'
    	PolizaDet.buildWithoutSave(poliza:poliza,debe:100.00,concepto:'VENTA 1')
    	and:'Salvamos la poliza'
    	poliza.save flush:true

    	then:'La partida persiste exitosamente'
    	def found=Poliza.get(poliza.id)
    	found.partidas.size()==1
    	def det=found.partidas.find{it.concepto=='VENTA 1'}
    	println det

    }
}
