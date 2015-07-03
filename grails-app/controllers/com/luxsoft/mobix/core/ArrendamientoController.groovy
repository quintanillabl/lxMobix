package com.luxsoft.mobix.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.core.Cliente

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN','VENTAS')"])
@Transactional(readOnly = true)
class ArrendamientoController {

    static allowedMethods = [save: "POST", update: "PUT"]

    def arrendamientoService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        respond Arrendamiento.findAllByEmpresa(session.empresa,params), model:[arrendamientoInstanceCount: Arrendamiento.countByEmpresa(session.empresa)]
    }

    def show(Arrendamiento arrendamientoInstance) {
        
        respond arrendamientoInstance
    }

    def create() {
        def command=new ArrendamientoCommand()
        respond command,model:[arrendamientoInstance:command]
    }

    @Transactional
    def save(ArrendamientoCommand command) {
       
        if (command == null) {
            notFound()
            return
        }
        
        if (command.hasErrors()) {
            //respond arrendamientoInstance.errors, view:'create'
            respond command.errors,view:'create',model:[arrendamientoInstance:command]
            return
        }

        def arrendamientoInstance=arrendamientoService.save(command.toArrendamiento())
        flash.message = message(code: 'default.created.message', args: [message(code: 'arrendamiento.label', default: 'Arrendamiento'), arrendamientoInstance.id])
        redirect action:'show',id:arrendamientoInstance.id
    }

    

    def edit(Arrendamiento arrendamientoInstance) {
        respond arrendamientoInstance
    }

    @Transactional
    def update(Arrendamiento arrendamientoInstance) {
        if (arrendamientoInstance == null) {
            notFound()
            return
        }

        if (arrendamientoInstance.hasErrors()) {
            respond arrendamientoInstance.errors, view:'edit'
            return
        }

        arrendamientoInstance.save flush:true
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Arrendamiento.label', default: 'Arrendamiento'), arrendamientoInstance.id])
        redirect action:'show',id:arrendamientoInstance.id
    }

    @Transactional
    def delete(Arrendamiento arrendamientoInstance) {

        if (arrendamientoInstance == null) {
            notFound()
            return
        }
        //arrendamientoInstance.delete flush:true
        try {
            arrendamientoService.delete(arrendamientoInstance)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'Arrendamiento.label', default: 'Arrendamiento'), arrendamientoInstance.id])
            redirect action:"index"
            return
        }
        catch(ArrendamientoException e) {
            flash.message=e.message
            //render view:'index'//,model:[arrendamientoInstance:Arrendamiento.get(arrendamientoInstance.id)]
            redirect action:'show',id:arrendamientoInstance.id
            return
            //redirect action:'index'
        }
        
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'arrendamiento.label', default: 'Arrendamiento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

     @Transactional
    def generarRentas(Arrendamiento arrendamiento){
        if (arrendamiento == null) {
            notFound()
            return
        }
        arrendamientoService.generarRentas(arrendamiento)
        flash.message="Rentas generadas"
        redirect action:'show',params:[id:arrendamiento.id]
        
    }
}

import org.grails.databinding.BindingFormat
//import grails.validation.Validateable

//@Validateable
class ArrendamientoCommand{

    Empresa empresa
    Cliente cliente
    Inmueble inmueble
    String tipo
    BigDecimal precio
    @BindingFormat('dd/MM/yyyy')
    Date inicio
    @BindingFormat('dd/MM/yyyy')
    Date fin
    Integer diaDeCorte =10
    Boolean renovacionAutomatica=true
    Boolean facturacionAutomatica=true
    Boolean envioAutomatico=true
    String comentario



    static constraints={
        importFrom Arrendamiento
    }

    def toArrendamiento(){
        Arrendamiento arrendamiento=new Arrendamiento()
        arrendamiento.properties=this.properties
        return arrendamiento

    }
}
