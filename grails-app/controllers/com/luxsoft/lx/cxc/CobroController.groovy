package com.luxsoft.lx.cxc



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class CobroController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def cobroService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cobro.list(params), model:[cobroInstanceCount: Cobro.count()]
    }

    def show(Cobro cobroInstance) {
        respond cobroInstance
    }

    def create() {
        respond new Cobro(params)
    }

    @Transactional
    def save(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        try {
            cobroInstance=cobroService.save(cobroInstance)
            flash.message = "Cobro registrado ${cobroInstance.id}"
            redirect action:'edit' ,params:[id:cobroInstance.id]
            return
            
        }
        catch(CobroException e) {
            flash.message=e.message
            flash.error=e.message
            render view:'create',model:[cobroInstane:e.cobro]
        }
    }

    def createAplicacion(Cobro cobroInstance){
        [aplicacionDeCobroInstance:new AplicacionDeCobro(cobro:cobroInstance,fecha:new Date())]
    }

    @Transactional
    def saveAplicacion(AplicacionDeCobro aplicacion){
        def cobro=aplicacion.pago
        cobro=cobroService.agregarAplicacion(cobro,aplicacion)
        flash.message="Aplicacion registrada "
        redirect action:'edit',params:[id:cobro.id]
    }

    def edit(Cobro cobroInstance) {
        respond cobroInstance
    }

    @Transactional
    def update(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        if (cobroInstance.hasErrors()) {
            render view:'edit',model:[cobroInstance:cobroInstance]
            return
        }
        cobroInstance=cobroService.update(cobroInstance)
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Cobro.label', default: 'Cobro'), cobroInstance.id])
        redirect cobroInstance
    }

    @Transactional
    def delete(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        cobroService.delete(cobroInstance)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cobro.label', default: 'Cobro'), cobroInstance.id])
        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobro.label', default: 'Cobro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
