package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class CuentaBancariaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CuentaBancaria.list(params), model:[cuentaBancariaInstanceCount: CuentaBancaria.count()]
    }

    def show(CuentaBancaria cuentaBancariaInstance) {
        respond cuentaBancariaInstance
    }

    def create() {
        respond new CuentaBancaria(params)
    }

    @Transactional
    def save(CuentaBancaria cuentaBancariaInstance) {
        if (cuentaBancariaInstance == null) {
            notFound()
            return
        }

        if (cuentaBancariaInstance.hasErrors()) {
            respond cuentaBancariaInstance.errors, view:'create'
            return
        }

        cuentaBancariaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cuentaBancaria.label', default: 'CuentaBancaria'), cuentaBancariaInstance.id])
                redirect cuentaBancariaInstance
            }
            '*' { respond cuentaBancariaInstance, [status: CREATED] }
        }
    }

    def edit(CuentaBancaria cuentaBancariaInstance) {
        respond cuentaBancariaInstance
    }

    @Transactional
    def update(CuentaBancaria cuentaBancariaInstance) {
        if (cuentaBancariaInstance == null) {
            notFound()
            return
        }

        if (cuentaBancariaInstance.hasErrors()) {
            respond cuentaBancariaInstance.errors, view:'edit'
            return
        }

        cuentaBancariaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CuentaBancaria.label', default: 'CuentaBancaria'), cuentaBancariaInstance.id])
                redirect cuentaBancariaInstance
            }
            '*'{ respond cuentaBancariaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CuentaBancaria cuentaBancariaInstance) {

        if (cuentaBancariaInstance == null) {
            notFound()
            return
        }

        cuentaBancariaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CuentaBancaria.label', default: 'CuentaBancaria'), cuentaBancariaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaBancaria.label', default: 'CuentaBancaria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
