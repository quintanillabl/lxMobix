package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.transaction.NotTransactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.lx.bi.ReportCommand
import org.apache.commons.lang.exception.ExceptionUtils

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class PolizaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def generadorDePoliza

    def polizaService
    
    def cierreContableService

    def reportService

    

    def index2(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort='tipo'
        params.order='desc'
        def empresa=session.empresa
        def ejercicio=session.periodoContable.ejercicio
        def mes=session.periodoContable.mes
        //def polizas=Poliza.findAllByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes,params)
        def polizas = Poliza.findAll(
            "from Poliza p where p.empresa= ? and p.ejercicio = ? and p.mes = ? order by p.tipo, p.subTipo, p.folio asc"
            ,[empresa,ejercicio,mes])
        //def polizasCount=Poliza.countByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes)
        respond polizas,model:[polizaInstanceCount: polizas.size()]
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

        def list=polizas.list(sort:'tipo',order:'asc')
        //list = list.sort {it.subTipo}
        list = list.sort { a,b -> a.tipo <=> b.tipo ?: a.subTipo <=> b.subTipo ?: a.folio <=> b.folio }
        
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
            flash.message="Polizas generadas: "+polizas.size()
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
        PolizaCheque cheque = polizaInstance.partidas.find {it.cheque != null}?.cheque
        respond polizaInstance , model:[cheque: cheque]
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
            respond polizaInstance.errors, view:'show'
            return
        }
        if(polizaInstance.manual){
            log.debug 'Actualizando poliza: '+polizaInstance  
            polizaInstance = polizaService.update(polizaInstance)
            flash.message="Póliza actualizada ${polizaInstance.id}"
            redirect action:'edit',id:polizaInstance.id
            return
        }
        
        def procesador=ProcesadorDePoliza.findByNombre(polizaInstance.subTipo)
        log.info 'Recalculando poliza: '+polizaInstance+ ' Con procesador: '+procesador
        if(procesador){
            generadorDePoliza.generar(
                polizaInstance.empresa,
                polizaInstance.fecha,
                procesador
                )
            flash.message="Póliza actualizada ${polizaInstance.id}"
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

    def printComplemento(Poliza polizaInstance){
        def command=new ReportCommand()
        if(polizaInstance.partidas.find {it.cheque || it.transferencia})
            command.reportName = "PolizaComplementoMetodoPago"
        else 
            command.reportName = "PolizaComprobanteNacional"
        command.empresa=session.empresa
        try {
            def stream=reportService.build(command,[POLIZA_ID:polizaInstance.id,EMPRESA:session.empresa.nombre])
            def file="PolizaComplemento_${polizaInstance.tipo}_${polizaInstance.folio}_${polizaInstance.ejercicio}${polizaInstance.mes}"+new Date().format('ss')+'.'+command.formato.toLowerCase()
            render(
                file: stream.toByteArray(), 
                contentType: 'application/pdf',
                fileName:file)
        }
        catch(Exception e) {
            String msg="Error ejecutando reporte $command.reportName" + ExceptionUtils.getRootCauseMessage(e)
            throw new RuntimeException(msg)
        }
        
    }

    def cierreAnual(){

        def subTipo = 'CIERRE_ANUAL'
        def q = Poliza.where {empresa==session.empresa && subTipo==subTipo}
        def list=q.list(sort:'ejercicio',order:'asc')
        [polizaInstanceList:list,subTipo:'CIERRE_ANUAL']
        
    }

    
    @Transactional
    def generarCierreAnual(){
        def empresa = session.empresa
        def ejercicio = session.periodoContable.ejercicio
        def poliza=Poliza.findByEmpresaAndEjercicioAndTipoAndSubTipo(empresa,ejercicio,'DIARIO','CIERRE_ANUAL')
        if(poliza){
            flash.message = "Polia de cierre anual para $empresa del ejercicio $ejercicio ya ha sido generada"
            redirect action:'index'
            return
        }
        def cuentaDeCierre = CuentaContable.findByEmpresaAndClave(empresa,'304-'+ejercicio.toString())
        if(!cuentaDeCierre){
            flash.message = "Debe dar de alta la cuenta de resutlados 304-$ejercicio"
            redirect action:'index'
            return
        }
        cierreContableService.generarPolizaDeCierre(empresa,ejercicio)
        redirect action:'index'

    }

    @NotTransactional
    def recalcularFolios(String subTipo){
        if(!subTipo){
            flash.message = "Debe seleccionar un sub tipo de poliza para regenerar los folios"
            redirect action:'index',params:[subTipo:subTipo]
            return    
        }
        def empresa = session.empresa
        def periodo=session.periodoContable
        flash.message = "Folios de $subTipo recalculados para el periodo $periodo"
        //def poliza=Poliza.findByEmpresaAndEjercicioAndTipoAndSubTipo(empresa,ejercicio,'DIARIO','CIERRE_ANUAL')
        def polizas = Poliza.findAll {empresa== empresa && subTipo==subTipo && ejercicio == periodo.ejercicio && mes == periodo.mes}

        polizas = polizas.sort {it.fecha}
        log.info('Polizs a ordenar: ' + polizas.size())

        def folio = 1
        polizas.each { p ->
            p.folio = folio++
            p.save failOnError:true,flush:true
        }
        redirect action:'index',params:[subTipo:subTipo]
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
        ejercicio inList:(2014..2030)
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


