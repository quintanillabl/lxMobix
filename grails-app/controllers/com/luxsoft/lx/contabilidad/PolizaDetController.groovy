package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class PolizaDetController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def polizaService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PolizaDet.list(params), model:[polizaDetInstanceCount: PolizaDet.count()]
    }

    def show(PolizaDet polizaDetInstance) {
        def poliza=polizaDetInstance.poliza
        [polizaInstance:poliza,polizaDetInstance:polizaDetInstance]
    }

    def create(Poliza poliza) {
        [polizaInstance:poliza,polizaDetInstance:new PolizaDetCommand()]
    }

    @Transactional
    def save(PolizaDetCommand command) {
        if (command == null) {
            notFound()
            return
        }
        if (command.hasErrors()) {
            respond command.errors, model:[polizaInstance:command.poliza,polizaDetInstance:command],view:'create'
            return
        }
        def det=command.toDet()
        def poliza=command.poliza
        det=polizaService.agregarConcepto(poliza,det)
        flash.message="Concepto agregada"
        redirect controller:'poliza',action:'edit',params:[id:poliza.id]
        
    }

    def edit(PolizaDet polizaDetInstance) {
        def poliza=polizaDetInstance.poliza
        [polizaInstance:poliza,polizaDetInstance:polizaDetInstance]
        
    }

    @Transactional
    def update(PolizaDet polizaDetInstance) {
        if (polizaDetInstance == null) {
            notFound()
            return
        }
        if (polizaDetInstance.hasErrors()) {
            respond polizaDetInstance.errors, view:'edit'
            return
        }
        polizaDetInstance=polizaService.actualizarPartida(polizaDetInstance)
        flash.message = message(code: 'default.updated.message', args: [message(code: 'PolizaDet.label', default: 'PolizaDet'), polizaDetInstance.id])
        redirect controller:'poliza',action:'edit',params:[id:polizaDetInstance.poliza.id]
    }

    @Transactional
    def delete(PolizaDet polizaDetInstance) {
        if (polizaDetInstance == null) {
            notFound()
            return
        }
        def poliza=polizaService.eleiminarPartida(polizaDetInstance)
        redirect controller:'poliza', action:'edit',id:poliza.id
        
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'polizaDet.label', default: 'PolizaDet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

import grails.validation.Validateable
@Validateable
class PolizaDetCommand{

    Poliza poliza
    CuentaContable cuenta
    BigDecimal debe
    BigDecimal haber
    String concepto

    String asiento
    String referencia
    String origen
    String entidad
    String descripcion

    static constraints={
        importFrom PolizaDet
    }

    def toDet(){
        PolizaDet det=new PolizaDet()
        det.properties=properties
        det.poliza=null
        return det
    }
}
