package com.luxsoft.lx.cxp

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.lx.contabilidad.CuentaContable

@Secured(["hasAnyRole('GASTOS','ADMIN')"])
@Transactional(readOnly = true)
class GastoDetController {

    def gastoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GastoDet.list(params), model:[gastoDetInstanceCount: GastoDet.count()]
    }

    def show(GastoDet gastoDetInstance) {
        def gasto=gastoDetInstance.gasto
        [gastoInstance:gasto,gastoDetInstance:gastoDetInstance]
    }

    def create(Gasto gasto) {
        [gastoInstance:gasto,gastoDetInstance:new GastoDetCommand(cuentaContable:gasto.cuentaContable)]
    }

    

    @Transactional
    def save(GastoDetCommand command) {
        if (command == null) {
            notFound()
            return
        }
        if (command.hasErrors()) {
            respond command.errors, model:[gastoInstance:command.gasto,gastoDetInstance:command],view:'create'
            return
        }

        /** ---------- Persistencia mover a service ------------**/
        def det=command.toDet()
        def gasto=command.gasto
        det=gastoService.agregarConcepto(gasto,det)
        /** --------------------------------------------------- **/

        flash.message="Partida agregada"
        redirect controller:'gasto',action:'edit',params:[id:gasto.id]
        
    }

    def edit(GastoDet gastoDetInstance) {
        respond gastoDetInstance
    }

    @Transactional
    def update(GastoDet gastoDetInstance) {
        if (gastoDetInstance == null) {
            notFound()
            return
        }

        if (gastoDetInstance.hasErrors()) {
            respond gastoDetInstance.errors, view:'edit'
            return
        }

        gastoDetInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GastoDet.label', default: 'GastoDet'), gastoDetInstance.id])
                redirect gastoDetInstance
            }
            '*'{ respond gastoDetInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GastoDet gastoDetInstance) {
        if (gastoDetInstance == null) {
            notFound()
            return
        }
        def gasto=gastoService.eleiminarPartida(gastoDetInstance)
        redirect controller:'gasto', action:'edit',id:gasto.id
        
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gastoDet.label', default: 'GastoDet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

import grails.validation.Validateable
@Validateable
class GastoDetCommand{

    Gasto gasto    
    String concepto
    String descripcion
    BigDecimal cantidad
    BigDecimal valorUnitario
    String unidad
    String comentario
    CuentaContable cuentaContable

    static constraints={
        importFrom GastoDet
        cantidad min:1.0
        valorUnitario min:1.0
        unidad inList:['PIEZA','SERVICIO','KILOS','GRAMOS','NO APLICA']
        cuentaContable nullable:true
    }

    def toDet(){
        GastoDet det=new GastoDet(
            concepto:concepto,
            descripcion:descripcion,
            unidad:unidad,
            cantidad:cantidad,
            valorUnitario:valorUnitario,
            cuentaContable:cuentaContable,
            comentario:comentario
            )
        return det

    }
}

/*
CuentaContable cuentaContable
    String concepto
    String descripcion
    String unidad
    BigDecimal cantidad
    BigDecimal valorUnitario
    BigDecimal importe
    String comentario
*/
