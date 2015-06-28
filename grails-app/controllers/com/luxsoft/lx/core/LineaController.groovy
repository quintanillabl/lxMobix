package com.luxsoft.lx.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMINISTRACION','ADMIN')"])
@Transactional(readOnly = true)
class LineaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Linea.findAllByEmpresa(session.empresa,params), model:[lineaInstanceCount: Linea.countByEmpresa(session.empresa)]
    }

    def show(Linea lineaInstance) {
        respond lineaInstance
    }

    def create() {
        respond new Linea(params)
    }

    @Transactional
    def save(Linea lineaInstance) {
        if (lineaInstance == null) {
            notFound()
            return
        }

        if (lineaInstance.hasErrors()) {
            respond lineaInstance.errors, view:'create'
            return
        }

        lineaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'linea.label', default: 'Linea'), lineaInstance.id])
                redirect lineaInstance
            }
            '*' { respond lineaInstance, [status: CREATED] }
        }
    }

    def edit(Linea lineaInstance) {
        respond lineaInstance
    }

    @Transactional
    def update(Linea lineaInstance) {
        if (lineaInstance == null) {
            notFound()
            return
        }

        if (lineaInstance.hasErrors()) {
            respond lineaInstance.errors, view:'edit'
            return
        }

        lineaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Linea.label', default: 'Linea'), lineaInstance.id])
                redirect lineaInstance
            }
            '*'{ respond lineaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Linea lineaInstance) {

        if (lineaInstance == null) {
            notFound()
            return
        }

        lineaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Linea.label', default: 'Linea'), lineaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
