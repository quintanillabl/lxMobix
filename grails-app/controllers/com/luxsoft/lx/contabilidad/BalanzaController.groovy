package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.lx.bi.ReportCommand

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class BalanzaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def balanzaService
    def reportService

    def index() {
        def periodo=session.periodoContable
        //def balanza=Balanza.findWhere(empresa: session.empresa,ejercicio:periodo.ejercicio,mes:periodo.mes)
        def saldos=SaldoPorCuentaContable.findAllWhere(empresa: session.empresa,ejercicio:periodo.ejercicio,mes:periodo.mes)
        println 'Saldos registrados: '+saldos.size()
        //[partidas:balanza.partidas?:[0]]
        [partidas:saldos]
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

    def balanzaDeComprobacion(){
        def command=new ReportCommand()
        command.reportName="BalanzaDeComprobacion"
        command.empresa=session.empresa
        params.YEAR=session.periodoContable.ejercicio as String
        params.MES=session.periodoContable.mes as String
        params.EMPRESA_ID=session.empresa.id
         params.EMPRESA=session.empresa.nombre
        params.INICIAL=0.0
        def stream=reportService.build(command,params)
        def file="Balanza_${params.YEAR}${params.MES}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }

    def balanceGeneral(){
        def command=new ReportCommand()
        command.reportName="BalanceGeneral"
        command.empresa=session.empresa
        params.YEAR=session.periodoContable.ejercicio as String
        params.MES=session.periodoContable.mes as String
        params.EMPRESA=session.empresa.nombre
        params.EMPRESA_ID=session.empresa.id
        params.INICIAL=0.0
        def stream=reportService.build(command,params)
        def file="BalanceGeneral_${params.YEAR}${params.MES}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }

    def estadoDeResultados(){
        def command=new ReportCommand()
        command.reportName="EstadoDeResultados"
        command.empresa=session.empresa
        params.YEAR=session.periodoContable.ejercicio as String
        params.MES=session.periodoContable.mes as String
        params.EMPRESA=session.empresa.nombre
        params.EMPRESA_ID=session.empresa.id
        params.INICIAL=0.0
        def stream=reportService.build(command,params)
        def file="EstadoDeResultados_${params.YEAR}${params.MES}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }
}
