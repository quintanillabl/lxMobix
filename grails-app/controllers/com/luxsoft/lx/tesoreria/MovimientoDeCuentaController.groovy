package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class MovimientoDeCuentaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        

        //params.max = Math.min(max ?: 20, 100)
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        
        def empresa=session.empresa
        def periodo=session.periodoContable.toPeriodo()
       def list=MovimientoDeCuenta.findAll("from MovimientoDeCuenta m where m.empresa=? and date(m.fecha)   between  ? and ?",
            [empresa,periodo.fechaInicial,periodo.fechaFinal],params)
        
        respond list
        
    }

    def show(MovimientoDeCuenta movimientoDeCuentaInstance) {
        respond movimientoDeCuentaInstance
    }

    def create() {
        respond new MovimientoDeCuenta(params)
    }

    @Transactional
    def save(MovimientoDeCuenta movimientoDeCuentaInstance) {
        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }

        if (movimientoDeCuentaInstance.hasErrors()) {
            respond movimientoDeCuentaInstance.errors, view:'create'
            return
        }

        movimientoDeCuentaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta'), movimientoDeCuentaInstance.id])
                redirect movimientoDeCuentaInstance
            }
            '*' { respond movimientoDeCuentaInstance, [status: CREATED] }
        }
    }

    def edit(MovimientoDeCuenta movimientoDeCuentaInstance) {
        respond movimientoDeCuentaInstance
    }

    @Transactional
    def update(MovimientoDeCuenta movimientoDeCuentaInstance) {
        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }

        if (movimientoDeCuentaInstance.hasErrors()) {
            respond movimientoDeCuentaInstance.errors, view:'edit'
            return
        }

        movimientoDeCuentaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MovimientoDeCuenta.label', default: 'MovimientoDeCuenta'), movimientoDeCuentaInstance.id])
                redirect movimientoDeCuentaInstance
            }
            '*'{ respond movimientoDeCuentaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MovimientoDeCuenta movimientoDeCuentaInstance) {

        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }

        movimientoDeCuentaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MovimientoDeCuenta.label', default: 'MovimientoDeCuenta'), movimientoDeCuentaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
