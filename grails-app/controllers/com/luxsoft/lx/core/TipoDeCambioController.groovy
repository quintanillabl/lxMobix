package com.luxsoft.lx.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','TESORERIA','ADMIN')"])
@Transactional(readOnly = true)
class TipoDeCambioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TipoDeCambio.list(params), model:[tipoDeCambioInstanceCount: TipoDeCambio.count()]
    }

    def show(TipoDeCambio tipoDeCambioInstance) {
        respond tipoDeCambioInstance
    }

    def create() {
        respond new TipoDeCambio(params)
    }

    @Transactional
    def save(TipoDeCambio tipoDeCambioInstance) {
        if (tipoDeCambioInstance == null) {
            notFound()
            return
        }

        if (tipoDeCambioInstance.hasErrors()) {
            respond tipoDeCambioInstance.errors, view:'create'
            return
        }

        tipoDeCambioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoDeCambio.label', default: 'TipoDeCambio'), tipoDeCambioInstance.id])
                redirect tipoDeCambioInstance
            }
            '*' { respond tipoDeCambioInstance, [status: CREATED] }
        }
    }

    def edit(TipoDeCambio tipoDeCambioInstance) {
        respond tipoDeCambioInstance
    }

    @Transactional
    def update(TipoDeCambio tipoDeCambioInstance) {
        if (tipoDeCambioInstance == null) {
            notFound()
            return
        }

        if (tipoDeCambioInstance.hasErrors()) {
            respond tipoDeCambioInstance.errors, view:'edit'
            return
        }

        tipoDeCambioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TipoDeCambio.label', default: 'TipoDeCambio'), tipoDeCambioInstance.id])
                redirect tipoDeCambioInstance
            }
            '*'{ respond tipoDeCambioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoDeCambio tipoDeCambioInstance) {

        if (tipoDeCambioInstance == null) {
            notFound()
            return
        }

        tipoDeCambioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TipoDeCambio.label', default: 'TipoDeCambio'), tipoDeCambioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoDeCambio.label', default: 'TipoDeCambio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
