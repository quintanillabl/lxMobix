package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
import grails.plugin.springsecurity.annotation.Secured

@Secured(["hasRole('TESORERIA')"])
class ChequeController {

    def chequeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = 200
        params.sort=params.sort?:'dateCreated'
        params.order='desc'
        def empresa=session.empresa
        def query=Cheque.where{
            cuenta.empresa==empresa
        }
        respond query.list(params)
    }

    def show(Cheque chequeInstance) {
        respond chequeInstance
    }

    def create() {
        def disponibles=MovimientoDeCuenta.findAll("from MovimientoDeCuenta m join m.cuenta c where m.empresa=? and c.tipo=? ")
        respond new Cheque(params)
    }

    @Transactional
    def save(Cheque chequeInstance) {
        if (chequeInstance == null) {
            notFound()
            return
        }   
        chequeInstance.validate(["movimiento"])
        if (chequeInstance.hasErrors()) {
            respond chequeInstance.errors, view:'create'
            return
        }
        chequeInstance=chequeService.registrarCheque(chequeInstance.egreso.id)
        flash.message = message(code: 'default.created.message', args: [message(code: 'cheque.label', default: 'Cheque'), chequeInstance.id])
        redirect chequeInstance
        
    }

    def edit(Cheque chequeInstance) {
        respond chequeInstance
    }

    @Transactional
    def update(Cheque chequeInstance) {
        if (chequeInstance == null) {
            notFound()
            return
        }

        if (chequeInstance.hasErrors()) {
            respond chequeInstance.errors, view:'edit'
            return
        }

        chequeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cheque.label', default: 'Cheque'), chequeInstance.id])
                redirect chequeInstance
            }
            '*'{ respond chequeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cheque chequeInstance) {

        if (chequeInstance == null) {
            notFound()
            return
        }

        chequeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cheque.label', default: 'Cheque'), chequeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cheque.label', default: 'Cheque'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
