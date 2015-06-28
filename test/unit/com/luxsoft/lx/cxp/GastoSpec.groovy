package com.luxsoft.lx.cxp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.Empresa

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gasto)
@Mock([Gasto,Empresa])
@Build([Gasto,Empresa])
class GastoSpec extends Specification {

	def empresa

    def setup() {
    	empresa=Empresa.build(clave:'DEMO')
    }

    def cleanup() {
    }

    void "No debe vencimientos incorrectos"(){
    	given:'Una gasto '
    	def fecha=new Date()
    	def vto=fecha-1
    	def gasto=Gasto.buildWithoutSave(fecha:fecha,vencimiento:vto)
    	when:'Validamos el vencimiento'
    	gasto.validate(['vencimiento'])
    	
    	then:'La validacion debe fallar'
    	gasto.hasErrors()
    	gasto.errors.errorCount==1
    	gasto.errors['vencimiento'].code=='invalido'
    }

    
}
