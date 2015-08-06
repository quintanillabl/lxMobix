package com.luxsoft.lx.tesoreria

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import java.text.DecimalFormat
import com.luxsoft.lx.cxp.Requisicion




@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class PagoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def pagoService

    def index(Integer max) {
        params.max = 200
        params.sort=params.sort?:'fecha'
        params.order='desc'
        respond Pago.findAllByEmpresa(session.empresa,params), model:[pagoInstanceCount: Pago.countByEmpresa(session.empresa)]
    }

    def show(Pago pagoInstance) {
        respond pagoInstance
    }

    def create() {
        [pagoInstance:new Pago(fecha:new Date())]
    }

    @Transactional
    def save(Pago command) {
        if (command == null) {
            notFound()
            return
        }
        // if (command.hasErrors()) {
        //     respond command.errors, view:'create'
        //     return
        // }
        def pagoInstance = pagoService.save(command)
        flash.message = "Pago a proveedor registrado"
        redirect action:'edit',params:[id:pagoInstance.id]
        
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

    def requisicionesPendientes(){
        
        log.info 'Buscando requisiciones pendientes de pago '
        
        def list=Requisicion.findAll("from Requisicion r where r  not in(select p.requisicion from Pago p)")

        def pattern = "\$##,###.##"
        def mf = new DecimalFormat(pattern)

        list=list.collect{ r->
            def nombre="Id: ${r.id} (${r.proveedor}) Pago:${r.pago.format('dd/MM/yyyy')} Total:${mf.format(r.total)} "
            [id:r.id,
            label:nombre,
            value:nombre,
            total:r.total
            ]
        }
        def res=list as JSON
        
        render res

    }

    @Transactional
    def aplicar(Pago pago){
        pagoService.aplicar(pago)
        flash.message="Pago aplicado"
        redirect action:'show',params:[id:pago.id]
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
