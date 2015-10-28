package com.luxsoft.lx.af



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(["hasAnyRole('ADMIN','TESORERIA','GASTOS')"])
@Transactional(readOnly = true)
class ActivoFijoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def activoFijoService

    def index(Integer max) {
        //params.max = Math.min(max ?: 10, 100)
        def q = ActivoFijo.where {
            empresa==session.empresa
        }
        respond q.list(params)
    }

    def show(ActivoFijo activoFijoInstance) {
        respond activoFijoInstance
    }

    def create() {
        respond new ActivoFijo(params)
    }

    @Transactional
    def save(ActivoFijo activoFijoInstance) {
        if (activoFijoInstance == null) {
            notFound()
            return
        }

        if (activoFijoInstance.hasErrors()) {
            respond activoFijoInstance.errors, view:'create'
            return
        }

        activoFijoInstance.save flush:true
        flash.message = message(code: 'default.created.message', args: [message(code: 'activoFijo.label', default: 'ActivoFijo'), activoFijoInstance.id])
        redirect action:'edit', id:activoFijoInstance.id
        
    }

    def edit(ActivoFijo activoFijoInstance) {
        respond activoFijoInstance
    }

    @Transactional
    def update(ActivoFijo activoFijoInstance) {
        if (activoFijoInstance == null) {
            notFound()
            return
        }

        if (activoFijoInstance.hasErrors()) {
            respond activoFijoInstance.errors, view:'edit'
            return
        }

        activoFijoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ActivoFijo.label', default: 'ActivoFijo'), activoFijoInstance.id])
                redirect activoFijoInstance
            }
            '*'{ respond activoFijoInstance, [status: OK] }
        }
    }

    @Transactional
    def generarDepreciaciones(ActivoFijo activoFijoInstance) {
        if (activoFijoInstance == null) {
            notFound()
            return
        }
        activoFijoInstance = activoFijoService.generarDepreciaciones(activoFijoInstance)
        flash.message = "Depreciaciones generadas"
        redirect action:'edit', id:activoFijoInstance.id
    }

    @Transactional
    def delete(ActivoFijo activoFijoInstance) {

        if (activoFijoInstance == null) {
            notFound()
            return
        }

        activoFijoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ActivoFijo.label', default: 'ActivoFijo'), activoFijoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activoFijo.label', default: 'ActivoFijo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
