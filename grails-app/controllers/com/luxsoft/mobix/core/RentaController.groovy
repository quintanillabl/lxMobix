package com.luxsoft.mobix.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import org.apache.commons.lang.exception.ExceptionUtils

@Transactional(readOnly = true)
@Secured(["hasAnyRole('OPERADOR','ADMIN')"])
class RentaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def arrendamientoService


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Renta.list(params), model:[rentaInstanceCount: Renta.count()]
    }

    def show(Renta rentaInstance) {
        respond rentaInstance
    }

    def create() {
        respond new Renta(params)
    }

    @Transactional
    def save(Renta rentaInstance) {
        if (rentaInstance == null) {
            notFound()
            return
        }

        if (rentaInstance.hasErrors()) {
            respond rentaInstance.errors, view:'create'
            return
        }

        rentaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'renta.label', default: 'Renta'), rentaInstance.id])
                redirect rentaInstance
            }
            '*' { respond rentaInstance, [status: CREATED] }
        }
    }

    def edit(Renta rentaInstance) {
        respond rentaInstance
    }

    @Transactional
    def update(Renta rentaInstance) {
        if (rentaInstance == null) {
            notFound()
            return
        }

        if (rentaInstance.hasErrors()) {
            respond rentaInstance.errors, view:'edit'
            return
        }

        rentaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Renta.label', default: 'Renta'), rentaInstance.id])
                redirect rentaInstance
            }
            '*'{ respond rentaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Renta rentaInstance) {

        if (rentaInstance == null) {
            notFound()
            return
        }

        rentaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Renta.label', default: 'Renta'), rentaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'renta.label', default: 'Renta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def generarVenta(Renta rentaInstance){
        if (rentaInstance == null) {
            notFound()
            return
        }
        if(rentaInstance.ventaDet){
            flash.message="Renta ya atendida en la venta: ${renta.ventaDet.venta.folio}"
            return
        }
        try {
            def venta=arrendamientoService.generarVenta(rentaInstance)
            redirect controller:'venta',action:'show' ,id:venta.id
        }
        catch(Exception e) {
            e.printStackTrace()
            flash.message="Error al tratar de generar venta "
            flash.error=ExceptionUtils.getRootCauseMessage(e)
            redirect action:'show',id:rentaInstance.id
        }
        
        

    }
}
