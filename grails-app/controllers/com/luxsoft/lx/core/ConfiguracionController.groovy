package com.luxsoft.lx.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured


@Secured(["hasAnyRole('CONTABILIDAD','TESORERIA','VENTAS','GASTOS')"])
@Transactional(readOnly = true)
class ConfiguracionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.sort=params.sort?:'modulo'
        params.order='desc'
        def list=Configuracion.findAllByEmpresa(session.empresa,params)
        respond list, model:[configuracionInstanceCount: Configuracion.countByEmpresa(session.empresa)]
    }

    def show(Configuracion configuracionInstance) {
        respond configuracionInstance
    }

    def create() {
        respond new Configuracion(params)
    }

    @Transactional
    def save(Configuracion configuracionInstance) {
        if (configuracionInstance == null) {
            notFound()
            return
        }

        if (configuracionInstance.hasErrors()) {
            respond configuracionInstance.errors, view:'create'
            return
        }

        configuracionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), configuracionInstance.id])
                redirect configuracionInstance
            }
            '*' { respond configuracionInstance, [status: CREATED] }
        }
    }

    def edit(Configuracion configuracionInstance) {
        respond configuracionInstance
    }

    @Transactional
    def update(Configuracion configuracionInstance) {
        if (configuracionInstance == null) {
            notFound()
            return
        }

        if (configuracionInstance.hasErrors()) {
            respond configuracionInstance.errors, view:'edit'
            return
        }

        configuracionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Configuracion.label', default: 'Configuracion'), configuracionInstance.id])
                redirect configuracionInstance
            }
            '*'{ respond configuracionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Configuracion configuracionInstance) {

        if (configuracionInstance == null) {
            notFound()
            return
        }

        configuracionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Configuracion.label', default: 'Configuracion'), configuracionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'configuracion.label', default: 'Configuracion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
