package com.luxsoft.lx.cxp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

import com.luxsoft.lx.core.Proveedor
import com.luxsoft.lx.core.Empresa

/**
 *
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RequisicionDet)
@Build([Requisicion,RequisicionDet,Gasto])
@Mock([Requisicion,RequisicionDet,Gasto])
class RequisicionDetSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Debe persistir detalles de requisicion"(){
    	given:'Una requisicion '
    	def requisicion=Requisicion.build()

    	when:'Agregamos cuentas por pagar y persistimos'
    	(1..3).each{
    		
    		def gasto=Gasto.build(
					fecha:new Date(),
					vencimiento:(new Date())+1,
					empresa:requisicion.empresa,
					proveedor:requisicion.proveedor,
					total:1000.00,
					folio:it)
    		println 'Agregando gasto: '+gasto
    		RequisicionDet.buildWithoutSave(
    			requisicion:requisicion,
    			requisitado:1000.00,
    			cuentaPorPagar:gasto
				)
    	}
    	requisicion.save flush:true
    	requisicion.partidas.each{
    		println it
    	}
    	then:'La requisicion y sus partidas persiste exitosamente'
    	def found=Requisicion.get(requisicion.id)
    	found.partidas.size()==3
    	found.partidas.find{it.folio==1}
    	found.partidas.find{it.folio==3}
    }
}
