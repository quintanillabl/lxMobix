package com.luxsoft.lx.cxp

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

import com.luxsoft.lx.core.Proveedor
import com.luxsoft.lx.core.Empresa
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Requisicion)
@Build([Requisicion,Proveedor,Empresa])
@Mock([Requisicion,Proveedor,Empresa])
class RequisicionSpec extends Specification {

	def proveedor

	def empresa

    def setup() {
    	proveedor=Proveedor.build(nombre:'PROVEEDOR DEMO1')
    	empresa=Empresa.build(clave:'EMPRESA_DEMO')
    }

    def cleanup() {
    }



    void "Debe persistir una requisicion nueva"() {
    	given:'Una requisicion valida'
    	def req=Requisicion.buildWithoutSave(proveedor:proveedor,empresa:empresa)
    	
    	when:'Salvamos el proveedor'
    	req.save()
    	
    	then:'La requisicion persiste exitosamente'
    	req.errors.errorCount==0
    	req.id
    	Requisicion.get(req.id).proveedor==proveedor
    	println req
    }

    
}
