package com.luxsoft.lx.tesoreria

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured



@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class PagoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pago.list(params), model:[pagoInstanceCount: Pago.count()]
    }

    def show(Pago pagoInstance) {
        respond pagoInstance
    }

    def create() {
        [pagoInstance:new Pago()]
    }

    @Transactional
    def save(Pago pagoInstance) {
        if (pagoInstance == null) {
            notFound()
            return
        }

        if (pagoInstance.hasErrors()) {
            respond pagoInstance.errors, view:'create'
            return
        }

        pagoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pago.label', default: 'Pago'), pagoInstance.id])
                redirect pagoInstance
            }
            '*' { respond pagoInstance, [status: CREATED] }
        }
    }

    def edit(Pago pagoInstance) {
        respond pagoInstance
    }

    @Transactional
    def update(Pago pagoInstance) {
        if (pagoInstance == null) {
            notFound()
            return
        }

        if (pagoInstance.hasErrors()) {
            respond pagoInstance.errors, view:'edit'
            return
        }
        pagoInstance.save flush:true
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Pago.label', default: 'Pago'), pagoInstance.id])
        redirect action:'edit',id:pagoInstance.id
    }

    @Transactional
    def delete(Pago pagoInstance) {

        if (pagoInstance == null) {
            notFound()
            return
        }

        pagoInstance.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Pago.label', default: 'Pago'), pagoInstance.id])
        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.core.FormaDePago
import com.luxsoft.lx.cxp.Requisicion
import grails.validation.Validateable

@Validateable
class PagoCommand{

    Empresa empresa

    Requisicion requisicion

    CuentaBancaria cuenta

    FormaDePago formaDePago

    @BindingFormat('dd/MM/yyyy')
    Date fecha=new Date()

    BigDecimal requisitado

    BigDecimal importe

    String referencia

    String comentario

    static constraints={
        
    }

    def toPago(){
        Pago pago=new Pago()
        pago.properties=this.properties
        return pago

    }
}
