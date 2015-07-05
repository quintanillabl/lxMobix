package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class BalanzaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def balanzaService

    def index() {
        def periodo=session.periodoContable
        //def balanza=Balanza.findWhere(empresa: session.empresa,ejercicio:periodo.ejercicio,mes:periodo.mes)
        def saldos=SaldoPorCuentaContable.findAllWhere(empresa: session.empresa,ejercicio:periodo.ejercicio,mes:periodo.mes)
        //[partidas:balanza.partidas?:[0]]
        [partidas:saldos?:[0]]
    }

    def show(Balanza balanzaInstance) {
        respond balanzaInstance
    }

    def edit(Balanza balanzaInstance) {
        respond balanzaInstance
    }

    @Transactional
    def update(Balanza balanzaInstance) {
        if (balanzaInstance == null) {
            notFound()
            return
        }

        if (balanzaInstance.hasErrors()) {
            respond balanzaInstance.errors, view:'edit'
            return
        }

        balanzaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Balanza.label', default: 'Balanza'), balanzaInstance.id])
                redirect balanzaInstance
            }
            '*'{ respond balanzaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Balanza balanzaInstance) {

        if (balanzaInstance == null) {
            notFound()
            return
        }

        balanzaInstance.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Balanza.label', default: 'Balanza'), balanzaInstance.id])
        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'balanza.label', default: 'Balanza'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def actualizar(){        
        def pContable=session.periodoContable
        balanzaService.generar(session.empresa,pContable.ejercicio,pContable.mes)
        flash.message="Balanza actualizada"
        redirect action:'index'

    }
}
