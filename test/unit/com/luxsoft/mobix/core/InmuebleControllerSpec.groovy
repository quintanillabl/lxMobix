package com.luxsoft.mobix.core



import grails.test.mixin.*
import spock.lang.*

@TestFor(InmuebleController)
@Mock(Inmueble)
class InmuebleControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.inmuebleInstanceList
            model.inmuebleInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.inmuebleInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def inmueble = new Inmueble()
            inmueble.validate()
            controller.save(inmueble)

        then:"The create view is rendered again with the correct model"
            model.inmuebleInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            inmueble = new Inmueble(params)

            controller.save(inmueble)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/inmueble/show/1'
            controller.flash.message != null
            Inmueble.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def inmueble = new Inmueble(params)
            controller.show(inmueble)

        then:"A model is populated containing the domain instance"
            model.inmuebleInstance == inmueble
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def inmueble = new Inmueble(params)
            controller.edit(inmueble)

        then:"A model is populated containing the domain instance"
            model.inmuebleInstance == inmueble
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/inmueble/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def inmueble = new Inmueble()
            inmueble.validate()
            controller.update(inmueble)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.inmuebleInstance == inmueble

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            inmueble = new Inmueble(params).save(flush: true)
            controller.update(inmueble)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/inmueble/show/$inmueble.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/inmueble/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def inmueble = new Inmueble(params).save(flush: true)

        then:"It exists"
            Inmueble.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(inmueble)

        then:"The instance is deleted"
            Inmueble.count() == 0
            response.redirectedUrl == '/inmueble/index'
            flash.message != null
    }
}
