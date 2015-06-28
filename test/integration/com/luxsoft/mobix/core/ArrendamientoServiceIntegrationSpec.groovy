package com.luxsoft.mobix.core

import grails.test.spock.IntegrationSpec
import com.luxsoft.lx.core.Producto
import com.luxsoft.lx.core.Cliente
import grails.buildtestdata.mixin.Build

@Build(Arrendamiento)
class ArrendamientoServiceIntegrationSpec extends IntegrationSpec {

	
	ArrendamientoService arrendamientoService

	def grailsApplication

    def setup() {
    }

    def cleanup() {
    }

    // void "Al salvar debe generar el producto para RENTA si este no existe"(){
    //     given: 'Un arrendamiento valido'
    //     //def cliente=Cliente.build()
    //     def arrendamiento=Arrendamiento
    //     	.buildWithoutSave(comentario:'TEST')
    //     when:'salvamos'
    //     assert arrendamientoService,'Debe existir el service'
    //     arrendamientoService.save(arrendamiento)
    //     then:'El producto para la renta es generado'
    //     Producto.findByClave('RENTA_MENSUAL')

    // }

    def "Grails application is not null"(){
    	expect:
    	assert grailsApplication!=null
    	assert applicationContext!=null
    }

    def "Arrendamiento service no debe ser nulo"(){
    	setup:
    	applicationContext.getBeanDefinitionNames().each{
    		println it
    	}
    	expect:
    	assert arrendamientoService!=null
    }
}
