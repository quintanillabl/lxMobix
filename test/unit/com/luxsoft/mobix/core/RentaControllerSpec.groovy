package com.luxsoft.mobix.core



import grails.test.mixin.*
import spock.lang.*

@TestFor(RentaController)
@Mock(Renta)
class RentaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }
    
    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def renta = new Renta(params)
            controller.show(renta)

        then:"A model is populated containing the domain instance"
            model.rentaInstance == renta
    }

    
    

    
}
