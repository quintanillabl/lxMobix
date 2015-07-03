package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class SaldoPorCuentaContableController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def saldoPorCuentaContableService

    def index(Integer max) {
        params.max = Math.min(max ?: 1000, 1200)
        params.sort=params.sort?:'cuenta.clave'
        params.order='asc'
        respond SaldoPorCuentaContable.findAllByEmpresa(session.empresa,params), model:[saldoPorCuentaContableInstanceCount: SaldoPorCuentaContable.countByEmpresa(session.empresa)]
    }

    def show(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        def saldos=saldoPorCuentaContableService.buscarSaldosDeSubCuentas(saldoPorCuentaContableInstance)
        [saldo:saldoPorCuentaContableInstance,cuentaContableInstance:saldoPorCuentaContableInstance.cuenta,saldos:saldos]
    }

    def create() {
        respond new SaldoPorCuentaContable(params)
    }

    @Transactional
    def save(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        if (saldoPorCuentaContableInstance.hasErrors()) {
            respond saldoPorCuentaContableInstance.errors, view:'create'
            return
        }

        saldoPorCuentaContableInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'saldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect saldoPorCuentaContableInstance
            }
            '*' { respond saldoPorCuentaContableInstance, [status: CREATED] }
        }
    }

    def edit(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        respond saldoPorCuentaContableInstance
    }

    @Transactional
    def update(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        if (saldoPorCuentaContableInstance.hasErrors()) {
            respond saldoPorCuentaContableInstance.errors, view:'edit'
            return
        }

        saldoPorCuentaContableInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SaldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect saldoPorCuentaContableInstance
            }
            '*'{ respond saldoPorCuentaContableInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SaldoPorCuentaContable saldoPorCuentaContableInstance) {

        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        saldoPorCuentaContableInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SaldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'saldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def actualizar(){        
        def pContable=session.periodoContable
        saldoPorCuentaContableService.generar(session.empresa,pContable.ejercicio,pContable.mes)
        flash.message="Cuentas actualizadas"
        redirect action:'index'

    }
}
