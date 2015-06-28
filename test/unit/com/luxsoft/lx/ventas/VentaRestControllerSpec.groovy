package com.luxsoft.lx.ventas

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(VentaRestController)
//@Mock([Venta,Cliente,Empresa])
@Build([Venta,Empresa])
class VentaRestControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "GET a List of  Ventas as JSON "() {
    	given:'Un grupo de ventas'
    	//initVentas()

    	when:'Invocamos el metodo index'
    	controller.index()
    	then:'Obtenemos una lista JSON the Ventas'
    	response.json
    	response.json.find({it.folio==1})
    	println response.text
    }

    private initVentas(){
        def empresa=Empresa.build(clave:'DEMO',direccion:new Direccion())
    	1..10.each{
    		def venta=Venta.build(folio:it,empresa:empresa)
    		println venta
    	}
    }
}
