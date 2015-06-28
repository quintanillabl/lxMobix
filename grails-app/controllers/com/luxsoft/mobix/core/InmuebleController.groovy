package com.luxsoft.mobix.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.validation.Validateable
import com.luxsoft.lx.core.*

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class InmuebleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Inmueble.findAllByEmpresa(session.empresa,params), model:[inmuebleInstanceCount: Inmueble.countByEmpresa(session.empresa)]
    }

    def show(Inmueble inmuebleInstance) {
        respond inmuebleInstance
    }

    def create() {
    	
        [inmuebleInstance:new InmuebleCommand()]
    }

    @Transactional
    def save(InmuebleCommand command) {
        if (command == null) {
            notFound()
            return
        }

        if (command.hasErrors()) {
            respond command, view:'create',model:[inmuebleInstance:command]
            return
        }
        def inmuebleInstance=command.toInmueble(session.empresa)
        inmuebleInstance.save flush:true
        flash.message = "Inmueble ${inmuebleInstance.id} generado exitosamente"
        //respond inmuebleInstance,view:'show',model:[inmuebleInstance:command]
        redirect action:'show',params:[id:inmuebleInstance.id]
    }

    def edit(Inmueble inmuebleInstance) {
        respond inmuebleInstance
    }

    @Transactional
    def update(Inmueble inmuebleInstance) {

        if (inmuebleInstance == null) {
            notFound()
            return
        }

        if (inmuebleInstance.hasErrors()) {
            respond inmuebleInstance.errors, view:'edit'
            return
        }

        inmuebleInstance.save flush:true
        flash.message="Inmueble actualizado : "+inmuebleInstance.id
        redirect action:'show',params:[id:inmuebleInstance.id]

        // request.withFormat {
        //     form multipartForm {
        //         flash.message = message(code: 'default.updated.message', args: [message(code: 'Inmueble.label', default: 'Inmueble'), inmuebleInstance.id])
        //         redirect inmuebleInstance
        //     }
        //     '*'{ respond inmuebleInstance, [status: OK] }
        // }
    }

    @Transactional
    def delete(Inmueble inmuebleInstance) {

        if (inmuebleInstance == null) {
            notFound()
            return
        }

        inmuebleInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Inmueble.label', default: 'Inmueble'), inmuebleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inmueble.label', default: 'Inmueble'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

@Validateable
class InmuebleCommand{

	String clave
	String descripcion
	String cuentaPredial
	Direccion direccion

	static constraints={
		importFrom Inmueble
	}

	def toInmueble(Empresa empresa){
		Inmueble i= new Inmueble(
			clave:clave,
			descripcion:descripcion,
			unidad:'PIEZA',
			cuentaPredial:cuentaPredial,
			direccion:direccion,
			empresa:empresa
			)
		return i
	}
}
