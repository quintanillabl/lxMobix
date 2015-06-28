package com.luxsoft.mobix.core

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.Producto

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ArrendamientoService)
@Mock([Arrendamiento,Renta,Producto])
@Build([Arrendamiento,Renta,Producto])
class ArrendamientoServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Debe salvar un arrendamiento nuevo"() {
    	given: 'Un arrendamiento valido'
        def producto=Producto.build(clave:'RENTA_MENSUAL')
    	def arrendamiento=Arrendamiento.buildWithoutSave(comentario:'TEST',producto:producto)
    	when:'salvamos'
    	def res=service.save(arrendamiento)
    	then:'El arrendamiento persiste exitosamente'
    	res.errors.errorCount==0
    	res.id
    	Arrendamiento.get(res.id).comentario=='TEST'
    }

    

    void "Debe mandar error al salvar arrendamientos invalidos"(){
        given:' Un arrendamiento invalido'
        def arrendamiento=Arrendamiento.buildWithoutSave(tipo:null,producto:null)
        when:'salvamos'
        def res=service.save(arrendamiento)
        then:'Debe mandar error'
        def e=thrown(ArrendamientoException)
        e.arrendamiento==arrendamiento
        e.message=="Arrendamiento invalido"
    }
}
