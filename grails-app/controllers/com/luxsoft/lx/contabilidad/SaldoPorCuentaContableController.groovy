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
        
        params.sort=params.sort?:'cuenta.clave'
        params.order='asc'

        def empresa=session.empresa
        def ejercicio=session.periodoContable.ejercicio
        def mes=session.periodoContable.mes

        def saldos=SaldoPorCuentaContable
            .findAll("from SaldoPorCuentaContable s where s.empresa=? and s.ejercicio=? and s.mes=? and s.cuenta.padre=null ",
                [empresa,ejercicio,mes])
        //def saldos=SaldoPorCuentaContable.findAllByEmpresaAndEjercicioAndMesAndPadere(empresa,ejercicio,mes,params)
        //def saldosCount=SaldoPorCuentaContable.countByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes)
        
        //respond saldos, model:[saldoPorCuentaContableInstanceCount: saldosCount]
        [saldoPorCuentaContableInstanceList:saldos]
    }

    def show(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        def saldos=saldoPorCuentaContableService.buscarSaldosDeSubCuentas(saldoPorCuentaContableInstance)
        def movimientos=[]
        if(saldoPorCuentaContableInstance.cuenta.detalle){
            movimientos=saldoPorCuentaContableService.buscarMovimientos(saldoPorCuentaContableInstance)
        }
        [saldo:saldoPorCuentaContableInstance,
        cuentaContableInstance:saldoPorCuentaContableInstance.cuenta,
        saldos:saldos,
        movimientos:movimientos
        ]
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

    @Transactional
    def actualizarSaldo(SaldoPorCuentaContable saldo){
        saldoPorCuentaContableService.actualizarSaldo(saldo)
        flash.message="Saldo  ${saldo} actualizado "
        redirect action:'show',id:saldo.id
    }

    def actualizarPeriodo(PeriodoContable periodoContable){
        log.info 'Actualizando periodo contable al: '+periodoContable
        //def origin=request.getHeader('referer')
        session.periodoContable=periodoContable
        //redirect(uri: request.getHeader('referer') )
        redirect action:'index'
    }
}
