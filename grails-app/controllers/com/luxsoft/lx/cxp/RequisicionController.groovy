package com.luxsoft.lx.cxp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.cfdi.*


@Secured(["hasAnyRole('GASTOS','ADMIN')"])
@Transactional(readOnly = true)
class RequisicionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def requisicionService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Requisicion.findAllByEmpresa(session.empresa,params), 
            model:[requisicionInstanceCount: Requisicion.countByEmpresa(session.empresa)]
    }

    def show(Requisicion requisicionInstance) {
        respond requisicionInstance
    }

    def create() {
        respond new Requisicion(params)
    }

    @Transactional
    def save(Requisicion requisicionInstance) {
        if (requisicionInstance == null) {
            notFound()
            return
        }
        requisicionInstance.validate(['proveedor','pago','comentario','tipo'])
        if (requisicionInstance.hasErrors()) {
            respond requisicionInstance.errors, view:'create'
            return
        }
        requisicionInstance=requisicionService.save requisicionInstance
        flash.message="Requisición generada: ${requisicionInstance.id}"
        redirect action:'edit',id:requisicionInstance.id

        
    }

    def edit(Requisicion requisicionInstance) {
        respond requisicionInstance
    }

    @Transactional
    def update(Requisicion requisicionInstance) {
        if (requisicionInstance == null) {
            notFound()
            return
        }
        requisicionInstance.validate(['proveedor','pago','comentario','tipo'])
        if (requisicionInstance.hasErrors()) {
            respond requisicionInstance.errors, view:'create'
            return
        }
        requisicionInstance=requisicionService.update requisicionInstance
        flash.message="Requisición actualizada: ${requisicionInstance.id}"
        redirect action:'index'

        
    }

    @Transactional
    def delete(Requisicion requisicionInstance) {

        if (requisicionInstance == null) {
            notFound()
            return
        }

        requisicionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Requisicion.label', default: 'Requisicion'), requisicionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requisicion.label', default: 'Requisicion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
