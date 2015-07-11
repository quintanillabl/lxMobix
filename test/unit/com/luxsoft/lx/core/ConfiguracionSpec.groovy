package com.luxsoft.lx.core

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Configuracion)
@Build(Configuracion)
class ConfiguracionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Debe mandar un error cuando se busque un atributo no definido"() {
    	setup:'Una configuracion nueva'
    	def conf=new Configuracion(empresa:Empresa.build(),modulo:'CONTABILIDAD')
    	when:'Buscamos un atributo inexistente'
    	conf.buscarAtributo('NO EXISTE')
    	then:
    	def e=thrown(RuntimeException)
    	e.cause==null
    }
}
