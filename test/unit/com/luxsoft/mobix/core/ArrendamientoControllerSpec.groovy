package com.luxsoft.mobix.core



import grails.test.mixin.*
import spock.lang.*
import grails.buildtestdata.mixin.Build

@TestFor(ArrendamientoController)
@Mock(Arrendamiento)
@Build(Arrendamiento)
class ArrendamientoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.arrendamientoInstanceList
            model.arrendamientoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.arrendamientoInstance!= null
    }

    void "Debe mandar salvar arrendamiento valido"(){
        setup:'a mock service'
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def mockService=Mock(ArrendamientoService)
            def arrendamiento=Arrendamiento.build()
            1 * mockService.save(_) >> arrendamiento
            controller.arrendamientoService=mockService

        when:'controller save is executed'
            response.reset()
            //def a=Arrendamiento.buildWithoutSave()
            def command=new ArrendamientoCommand()
            //command.validate() // No VALIDAMOS PARA PASAR EL COMMAND
            controller.save(command)
            
        then:"A redirect is issued to the show action"
            println response.redirectedUrl
            response.redirectedUrl == '/arrendamiento/show/1'
            controller.flash.message != null
    }


    void "Debe mostrar create al salvar Arrendamientos invalidos"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def command = new ArrendamientoCommand()
            command.validate()  /* <<<<<<<<----------MUY IMPORTANTE EN UNIT TEST NO DETONA Domain.validate()*/
            controller.save(command)

        then:"The create view is rendered again with the correct model"
            model.arrendamientoInstance!= null
            view == 'create'
        
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def arrendamiento = new Arrendamiento(params)
            controller.show(arrendamiento)

        then:"A model is populated containing the domain instance"
            model.arrendamientoInstance == arrendamiento
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def arrendamiento = new Arrendamiento(params)
            controller.edit(arrendamiento)

        then:"A model is populated containing the domain instance"
            model.arrendamientoInstance == arrendamiento
    }

    def "Debe generar rentas"(){
        setup:'a mock service'
            def mockService=Mock(ArrendamientoService)
            def arrendamiento=Arrendamiento.build()
            1 * mockService.generarRentas(!null)
            controller.arrendamientoService=mockService
        when:"Se ejecuta la accion generarRentas"
            def a=Arrendamiento.build()
            controller.generarRentas(a)

        then:"A redirect is issued to the show action"
            println response.redirectedUrl
            response.redirectedUrl == '/arrendamiento/show/'+a.id
            controller.flash.message != null


    }

    // void "Test the update action performs an update on a valid domain instance"() {
    //     when:"Update is called for a domain instance that doesn't exist"
    //         request.contentType = FORM_CONTENT_TYPE
    //         request.method = 'PUT'
    //         controller.update(null)

    //     then:"A 404 error is returned"
    //         response.redirectedUrl == '/arrendamiento/index'
    //         flash.message != null


    //     when:"An invalid domain instance is passed to the update action"
    //         response.reset()
    //         def arrendamiento = new Arrendamiento()
    //         arrendamiento.validate()
    //         controller.update(arrendamiento)

    //     then:"The edit view is rendered again with the invalid instance"
    //         view == 'edit'
    //         model.arrendamientoInstance == arrendamiento

    //     when:"A valid domain instance is passed to the update action"
    //         response.reset()
    //         populateValidParams(params)
    //         arrendamiento = new Arrendamiento(params).save(flush: true)
    //         controller.update(arrendamiento)

    //     then:"A redirect is issues to the show action"
    //         response.redirectedUrl == "/arrendamiento/show/$arrendamiento.id"
    //         flash.message != null
    // }

    // void "Test that the delete action deletes an instance if it exists"() {
    //     when:"The delete action is called for a null instance"
    //         request.contentType = FORM_CONTENT_TYPE
    //         request.method = 'DELETE'
    //         controller.delete(null)

    //     then:"A 404 is returned"
    //         response.redirectedUrl == '/arrendamiento/index'
    //         flash.message != null

    //     when:"A domain instance is created"
    //         response.reset()
    //         populateValidParams(params)
    //         def arrendamiento = new Arrendamiento(params).save(flush: true)

    //     then:"It exists"
    //         Arrendamiento.count() == 1

    //     when:"The domain instance is passed to the delete action"
    //         controller.delete(arrendamiento)

    //     then:"The instance is deleted"
    //         Arrendamiento.count() == 0
    //         response.redirectedUrl == '/arrendamiento/index'
    //         flash.message != null
    // }
}
