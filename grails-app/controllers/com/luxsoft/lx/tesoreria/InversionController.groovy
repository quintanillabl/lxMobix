package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(["hasAnyRole('ADMIN','TESORERIA','GASTOS')"])
@Transactional(readOnly = true)
class InversionController {

    def inversionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def beforeInterceptor = {
        if(!session.periodoTesoreria){
            session.periodoTesoreria=new Date()
        }
    }

    def cambiarPeriodo(){
        def fecha=params.date('fecha', 'dd/MM/yyyy')
        session.periodoTesoreria=fecha
        redirect(uri: request.getHeader('referer') )
    }

    def index(Integer max) {
        params.sort="fecha"
        params.order="desc"
        def periodo=session.periodoTesoreria

        def list=Inversion
            .findAll("from Inversion i where i.empresa=? and date(i.fecha) between ? and ?",
            [session.empresa,periodo.inicioDeMes(),periodo.finDeMes()],
            params)
        [inversionInstanceList:list]
    }
    

    def show(Inversion inversionInstance) {
        respond inversionInstance
    }

    def create() {
        respond new Inversion(params)
    }

    @Transactional
    def save(Inversion inversionInstance) {
        if (inversionInstance == null) {
            notFound()
            return
        }

        if (inversionInstance.hasErrors()) {
            respond inversionInstance.errors, view:'create'
            return
        }

        inversionInstance = inversionService.save inversionInstance

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'inversion.label', default: 'Inversion'), inversionInstance.id])
                redirect inversionInstance
            }
            '*' { respond inversionInstance, [status: CREATED] }
        }
    }

    def edit(Inversion inversionInstance) {
        respond inversionInstance
    }

    @Transactional
    def update(Inversion inversionInstance) {
        if (inversionInstance == null) {
            notFound()
            return
        }

        if (inversionInstance.hasErrors()) {
            respond inversionInstance.errors, view:'edit'
            return
        }

        inversionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Inversion.label', default: 'Inversion'), inversionInstance.id])
                redirect inversionInstance
            }
            '*'{ respond inversionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Inversion inversionInstance) {

        if (inversionInstance == null) {
            notFound()
            return
        }

        inversionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Inversion.label', default: 'Inversion'), inversionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inversion.label', default: 'Inversion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
