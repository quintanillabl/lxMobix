package com.luxsoft.lx.ventas



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.validation.Validateable
import com.luxsoft.lx.core.*
import grails.validation.ValidationException
import org.apache.commons.lang.exception.ExceptionUtils

@Secured(["hasAnyRole('VENTAS','ADMIN')"])
@Transactional(readOnly = true)
class VentaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def ventaService 

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort=params.sort?:'dateCreated'
        params.order='desc'
        
        respond Venta.findAllByEmpresa(session.empresa,params), model:[ventaInstanceCount: Venta.countByEmpresa(session.empresa)]
    }

    def show(Venta ventaInstance) {
        respond ventaInstance
    }

    def create() {
        //respond new VentaCommand(fecha:new Date())
        [ventaInstance:new VentaCommand(fecha:new Date())]
    }

    @Transactional
    def save(VentaCommand command) {
        if (command == null) {
            notFound()
            return
        }

        if (command.hasErrors()) {
            respond command.errors,model:[ventaInstance:command],view:'create'
            return
        }
        try {
            def ventaInstance=ventaService.save(command.toVenta())
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'venta.label', default: 'Venta'), ventaInstance.id])
                    redirect ventaInstance
                }
                '*' { respond ventaInstance, [status: CREATED] }
            }
            
        }
        catch(ValidationException ex) {
            //flash.message="Error de validacion"
            flash.error="${ex.errors.errorCount} errores de validacion en la Venta"
            command.errors=ex.errors
           respond command.errors,model:[ventaInstance:command,errors:ex.errors],view:'create' 
        }
        
    }

    def edit(Venta ventaInstance) {
        respond ventaInstance
    }

    @Transactional
    def update(Venta ventaInstance) {
        if (ventaInstance == null) {
            notFound()
            return
        }

        if (ventaInstance.hasErrors()) {
            respond ventaInstance.errors, view:'edit'
            return
        }

        ventaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Venta.label', default: 'Venta'), ventaInstance.id])
                redirect ventaInstance
            }
            '*'{ respond ventaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Venta ventaInstance) {

        if (ventaInstance == null) {
            notFound()
            return
        }
        ventaService.delete ventaInstance
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Venta.label', default: 'Venta'), ventaInstance.id])
        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'venta.label', default: 'Venta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def facturar(Venta ventaInstance){
        if (ventaInstance == null) {
            notFound()
            return
        }
        try {
            ventaInstance=ventaService.mandarFacturar(ventaInstance)
            redirect action:'show',controller:'cfdi',id:ventaInstance.cfdi.id
        }
        catch(Exception e) {
            e.printStackTrace()
            flash.message="Error al tratar de facturar venta "
            flash.error=ExceptionUtils.getRootCauseMessage(e)
            redirect action:'show',id:ventaInstance.id
        }
        
        
        
    }
}

import org.grails.databinding.BindingFormat

@Validateable
class VentaCommand{

    Empresa empresa
    Cliente cliente

    @BindingFormat('dd/MM/yyyy')
    Date fecha

    String comentario
    String tipo



    static constraints={
        importFrom Venta
    }

    def toVenta(Empresa empresa){
        Venta venta=new Venta()
        venta.properties=this.properties
        return venta

    }
}