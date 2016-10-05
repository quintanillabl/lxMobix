
package com.luxsoft.lx.tesoreria


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import com.luxsoft.lx.core.TipoDeCambio
import com.luxsoft.lx.utils.MonedaUtils
import org.grails.databinding.BindingFormat
import grails.converters.JSON
import com.luxsoft.lx.cxp.Gasto


@Secured(["hasAnyRole('ADMIN','TESORERIA','GASTOS')"])
@Transactional(readOnly = true)
class ComisionController {

    def comisionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    

    

    def index(Integer max) {
        params.sort="fecha"
        params.order="desc"
        def periodo=session.periodoTesoreria

        def list=Comision
            .findAll("from Comision c where c.empresa=? and year(c.fecha) = ? and month(c.fecha)=? ",
            [session.empresa,periodo.ejercicio,periodo.mes],
            params)
        [comisionInstanceList:list]
    }

    def show(Comision comisionInstance) {
        respond comisionInstance
    }

    def create() {
        respond new Comision(fecha:new Date(),impuestoTasa:16.00)
    }

    @Transactional
    def save(ComisionCommand command) {
        if (command == null) {
            notFound()
            return
        }
        if (command.hasErrors()) {
            render view:'create',model:[comisionInstance:command]
            return
        }
        
        def comisionInstance =command.toComision()

        if(command.cuenta.moneda!=MonedaUtils.PESOS){
            def cuenta=command.cuenta
            def dia=command.fecha-1
            def tc=TipoDeCambio.find("from TipoDeCambio t where date(t.fecha)=? and t.monedaFuente=?",[dia,cuenta.moneda])
            if(!tc){
                flash.message="No existe Tipo de cambio  en ${cuenta.moneda} para el ${dia.format('dd/MM/yyyy')} "
                render view:'create',model:[comisionInstance:command]
                return
            }
        }
        comisionInstance=comisionService.save(comisionInstance)
        flash.message = "Comisión bancaria ${comisionInstance.id} registrada"
        redirect comisionInstance
    }

    def edit(Comision comisionInstance) {
        respond comisionInstance
    }

    @Transactional
    def update(Comision comisionInstance) {
        if (comisionInstance == null) {
            notFound()
            return
        }

        if (comisionInstance.hasErrors()) {
            respond comisionInstance.errors, view:'edit'
            return
        }

        comisionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Comision.label', default: 'Comision'), comisionInstance.id])
                redirect comisionInstance
            }
            '*'{ respond comisionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Comision comisionInstance) {

        if (comisionInstance == null) {
            notFound()
            return
        }

        comisionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Comision.label', default: 'Comision'), comisionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comision.label', default: 'Comision'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def getCxPDisponibles() {

        def term=params.term+'%'
        log.info 'Buscando gastos: '+term
        def args=[session.empresa,term.toLowerCase()]
        def params=[max:30,sort:"fecha",order:"desc"]
        def hql="from Gasto g where g.empresa=? and g.concepto='COMISIONES_BANCARIAS' and ( lower(g.proveedor.nombre) like ?) and g not in(select c.gasto from Comision c) "
        def list=Gasto.findAll(hql,args,params)
        
        list=list.collect{ c->
            def nombre="$c.proveedor.nombre ${c.fecha.text()} ($c.folio) $c.importe"
            [id:c.id,
            label:nombre,
            value:nombre]
        }
        def res=list as JSON
        render res
    }

}

import com.luxsoft.lx.tesoreria.CuentaBancaria;
import grails.validation.Validateable
import com.luxsoft.lx.core.Empresa

@Validateable
class ComisionCommand {

    Empresa empresa 

    @BindingFormat('dd/MM/yyyy')
    Date fecha

    CuentaBancaria cuenta 

    BigDecimal comision

    BigDecimal impuestoTasa

    BigDecimal impuesto

    String comentario
    
    String referenciaBancaria

    static constraints = {
        importFrom Comision
        impuestoTasa minSize:0.1,maxSize:99.00
    }

    def toComision(){
        def comision=new Comision()
        comision.properties=properties
        return comision
    }
    
}




