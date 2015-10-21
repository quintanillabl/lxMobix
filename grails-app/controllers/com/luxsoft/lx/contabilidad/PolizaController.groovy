package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.lx.bi.ReportCommand

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class PolizaController {

    static allowedMethods = [save: "POST", update: "GET", delete: "GET"]

    def generadorDePoliza

    def polizaService
    
    def cierreContableService

    def reportService

    

    def index2(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        def empresa=session.empresa
        def ejercicio=session.periodoContable.ejercicio
        def mes=session.periodoContable.mes
        def polizas=Poliza.findAllByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes,params)
        def polizasCount=Poliza.countByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes)
        respond polizas,model:[polizaInstanceCount: polizasCount]
    }

    def index(){
        def subTipo=params.subTipo?:'TODAS'
        def ejercicio=session.periodoContable.ejercicio
        def mes=session.periodoContable.mes
        
        def polizas=Poliza.where{
            empresa==session.empresa &&
            ejercicio==ejercicio &&
            mes==mes
        }
        def procesador = null
        if(subTipo!='TODAS'){
            polizas=polizas.where {subTipo==subTipo}
            procesador = ProcesadorDePoliza.findByNombre(subTipo)
        }

        def list=polizas.list(sort:'folio',order:'asc')
        
        respond list,model:[subTipo:subTipo,procesador:procesador]
        //render view:'index',model:[polizaInstanceList:polizas,subTipo:subTipo]
        
    }

    @Transactional
    def generar(GeneradorDePolizaCommand command){
        if(command == null) {
            notFound()
            return
        }
        if (command.hasErrors()) {
            flash.message="Errores: "+command.errors
            //render view:command.tipo?:'index'
            redirect action:'index',params:[subTipo:command.tipo]
            return
        }
        log.debug 'Generando poliza: '+command
        if(command.procesador.tipo=='EGRESO'){
            def polizas=generadorDePoliza.generar(
                command.empresa,
                command.fecha,
                command.procesador)
            flash.message="Polizas generadas: PENDIENTE"
            redirect action:'index',params:[subTipo:command.tipo]
        }else{
            def poliza = generadorDePoliza.generar(
                command.empresa,
                command.fecha,
                command.procesador)

            flash.message="Póliza generada ${poliza.id}"
            redirect action:'show',id:poliza.id
        }
        
        
    }


    def show(Poliza polizaInstance) {
        respond polizaInstance
    }

    def create() {
        [polizaInstance:new PolizaCommand(mes:session.periodoContable.mes)]
    }

    @Transactional
    def save(PolizaCommand command) {
        if (command == null) {
            notFound()
            return
        }

        if (command.hasErrors()) {
            render view:'create',model:[polizaInstance:command]
            return
        }

        def polizaInstance=polizaService.save(command.toPoliza())
        flash.message="Poliza ${polizaInstance.id} generada"
        redirect action:'edit',id:polizaInstance.id
    }

    def edit(Poliza polizaInstance) {
        respond polizaInstance
    }

    @Transactional
    def update(Poliza polizaInstance) {

        if (polizaInstance == null) {
            notFound()
            return
        }
        
        if (polizaInstance.hasErrors()) {
            respond polizaInstance.errors, view:'edit'
            return
        }
        
        log.debug 'Actualizando/Recalculando poliza: '+polizaInstance
        def procesador=ProcesadorDePoliza.findByNombre(polizaInstance.subTipo)
        if(procesador){
            generadorDePoliza.generar(
                polizaInstance.empresa,
                polizaInstance.fecha,
                procesador
                )
            flash.message="Póliza actualizada ${polizaInstance.id}"
        } else {
            flash.messabe = "No existe procesador para la poliza por lo que no se puede re calcular"
        }
        redirect action:'show',id:polizaInstance.id
        
    }

    @Transactional
    def delete(Poliza polizaInstance) {
        if (polizaInstance == null) {
            notFound()
            return
        }
        polizaService.delete(polizaInstance)
        flash.message="Poliza ${polizaInstance.id} eliminada"
        redirect action:'index',params:[subTipo:polizaInstance.subTipo]
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def actualizarPeriodo(PeriodoContable periodoContable){
        def origin=request.getHeader('referer')
        session.periodoContable=periodoContable
        log.info 'Periodo actualizado: '+periodoContable+' Origen: '+origin+ '  Target:'+request.getHeader('referer') 
        redirect(uri: request.getHeader('referer') )
    }

    def afterInterceptor = { model, modelAndView ->
        //println "Current view is ${modelAndView.viewName}"
        if (!model.periodo) 
            model.periodo=session.periodoContable
        //println "View is now ${modelAndView.viewName}"
    }

    def print(Poliza polizaInstance){
        def command=new ReportCommand()
        command.reportName="PolizaContable"
        command.empresa=session.empresa
        def stream=reportService.build(command,[ID:polizaInstance.id,EMPRESA:session.empresa.nombre])
        def file="Poliza_${polizaInstance.tipo}_${polizaInstance.folio}_${polizaInstance.ejercicio}${polizaInstance.mes}"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }

    def cierreAnual(){
        [polizaInstanceList:Poliza.findByEmpresaAndTipo(session.empresa,'CIERRE_ANUAL')]
    }

    
    @Transactional
    def generarCierreAnual(){
        cierreContableService.generarPolizaDeCierre(session.empresa,session.periodoContable.ejercicio)
        redirect action:'index'

    }
}

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.core.Empresa
import groovy.transform.ToString

@ToString(includeNames=true,includePackage=false)
class GeneradorDePolizaCommand{
    
    Empresa empresa

    Integer ejercicio

    Integer mes

    String tipo

    ProcesadorDePoliza procesador

    @BindingFormat('dd/MM/yyyy')
    Date fecha=new Date()
    
    static constraints = {
        ejercicio inList:(2014..2018)
        mes inList:(1..13)
        tipo(maxSize:30)
    }
}

class PolizaCommand{

    Empresa empresa
    Integer ejercicio
    Integer mes
    String tipo

    @BindingFormat('dd/MM/yyyy')
    Date fecha=new Date()
    String concepto
    

    static constraints={
        importFrom Poliza
        //tipo(inList:['INGRESO','EGRESO','DIARIO','COMPRAS','GENERICA','CIERRE_ANUAL'])
    }

    Poliza toPoliza(){
        def poliza=new Poliza()
        poliza.properties=properties
        poliza.manual=true
        poliza.concepto=this.concepto.toUpperCase()
        return poliza
    }
    
    
}


