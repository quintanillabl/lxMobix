package com.luxsoft.lx.core


import grails.test.mixin.*
import spock.lang.*
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */


@Build(Cliente)
@TestFor(Cliente)
class ClienteSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Debe persistir un cliente valido"() {
        
    	setup:'Un cliente nuevo'
    	//def cliente=Cliente.buildWithoutSave(nombre:'TEST CLIENTE')
    	def cliente=Cliente.buildWithoutSave(nombre:'TEST CLIENTE')
    	assert cliente
    	when:'Salvamos'
    	cliente.save()
    	then:'Debe persistir correctamente el cliente'
    	cliente.errors.errorCount==0
    	cliente.id
    	Cliente.get(cliente.id).nombre=='TEST CLIENTE'
    	println "$cliente ${cliente.rfc}"
    }
}
