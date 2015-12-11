package com.luxsoft.lx.tesoreria



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.lx.core.PeriodoOperativo
import com.luxsoft.lx.bi.ReportCommand


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class MovimientoDeCuentaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def movimientoDeCuentaService

    def reportService

    def cambiarPeriodo(PeriodoOperativo periodo){
        def origin=request.getHeader('referer')
        session.periodoTesoreria=periodo
        log.info 'Periodo de tesoreria actualizado: '+periodo+' Origen: '+origin+ '  Target:'+request.getHeader('referer') 
        redirect(uri: request.getHeader('referer') )
    }

    def index() {
        
        params.sort='fecha'
        params.order='desc'
        def empresa=session.empresa
        def periodo=session.periodoTesoreria
        def list=MovimientoDeCuenta
            .findAll("from MovimientoDeCuenta m where m.empresa=? and year(m.fecha)=? and month(m.fecha)=? order by fecha desc",
            [empresa,periodo.ejercicio,periodo.mes],params)
        [movimientoDeCuentaInstanceList:list,periodo:periodo]
    }

    def show(MovimientoDeCuenta movimientoDeCuentaInstance) {
        respond movimientoDeCuentaInstance
    }

    def depositar() {
        respond new MovimientoDeCuenta(fecha:new Date())
    }


    @Transactional
    def saveDeposito(MovimientoDeCuenta movimientoDeCuentaInstance) {
        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }
        movimientoDeCuentaInstance=movimientoDeCuentaService.save movimientoDeCuentaInstance
        flash.message = "Deposito ${movimientoDeCuentaInstance.id} registrado exitosamente"
        redirect action:'show',id:movimientoDeCuentaInstance.id
        
    }

    def retirar() {
        respond new MovimientoDeCuenta(fecha:new Date())
    }

    @Transactional
    def saveRetiro(MovimientoDeCuenta movimientoDeCuentaInstance) {
        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }
        movimientoDeCuentaInstance.importe = movimientoDeCuentaInstance.importe.abs()*-1
        movimientoDeCuentaInstance=movimientoDeCuentaService.save movimientoDeCuentaInstance
        flash.message = "Deposito ${movimientoDeCuentaInstance.id} registrado exitosamente"
        redirect action:'show',id:movimientoDeCuentaInstance.id
        
    }

    def edit(MovimientoDeCuenta movimientoDeCuentaInstance) {
        respond movimientoDeCuentaInstance
    }

    

    @Transactional
    def delete(MovimientoDeCuenta movimientoDeCuentaInstance) {
        if (movimientoDeCuentaInstance == null) {
            notFound()
            return
        }
        movimientoDeCuentaInstance.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'MovimientoDeCuenta.label', default: 'MovimientoDeCuenta'), movimientoDeCuentaInstance.id])
        redirect action:"index", method:"GET"
        
    }

    def estadoDeCuenta(CuentaBancaria cuenta){
        
        def command=new ReportCommand()
        command.reportName="EstadoDeCuentaBanco"
        command.empresa=session.empresa
        def stream=reportService.build(command,
            [ID:cuenta.id as String
            ,COMPANY:session.empresa.nombre]
            )
        def file="EsatdoDeCuenta_${cuenta.numero}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
