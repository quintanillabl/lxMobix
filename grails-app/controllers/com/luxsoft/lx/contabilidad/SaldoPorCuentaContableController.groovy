package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import java.text.NumberFormat
import com.luxsoft.lx.bi.ReportCommand

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class SaldoPorCuentaContableController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def saldoPorCuentaContableService
    def cierreContableService
     def reportService

    def index(Integer max) {
        
        params.sort=params.sort?:'cuenta.clave'
        params.order='asc'

        def empresa=session.empresa
        def ejercicio=session.periodoContable.ejercicio
        def mes=session.periodoContable.mes

        def saldos=SaldoPorCuentaContable
            .findAll("from SaldoPorCuentaContable s where s.empresa=? and s.ejercicio=? and s.mes=? and s.cuenta.padre=null order by s.cuenta.clave ",
                [empresa,ejercicio,mes])
        //def saldos=SaldoPorCuentaContable.findAllByEmpresaAndEjercicioAndMesAndPadere(empresa,ejercicio,mes,params)
        //def saldosCount=SaldoPorCuentaContable.countByEmpresaAndEjercicioAndMes(empresa,ejercicio,mes)
        
        //respond saldos, model:[saldoPorCuentaContableInstanceCount: saldosCount]
        [saldoPorCuentaContableInstanceList:saldos]
    }

    def show(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        def saldos=saldoPorCuentaContableService.buscarSaldosDeSubCuentas(saldoPorCuentaContableInstance)
        def movimientos=[]
        if(saldoPorCuentaContableInstance.cuenta.detalle){
            movimientos=saldoPorCuentaContableService.buscarMovimientos(saldoPorCuentaContableInstance)
        }
        [saldo:saldoPorCuentaContableInstance,
        cuentaContableInstance:saldoPorCuentaContableInstance.cuenta,
        saldos:saldos,
        movimientos:movimientos
        ]
    }

    def create() {
        respond new SaldoPorCuentaContable(params)
    }

    @Transactional
    def save(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        if (saldoPorCuentaContableInstance.hasErrors()) {
            respond saldoPorCuentaContableInstance.errors, view:'create'
            return
        }

        saldoPorCuentaContableInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'saldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect saldoPorCuentaContableInstance
            }
            '*' { respond saldoPorCuentaContableInstance, [status: CREATED] }
        }
    }

    def edit(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        respond saldoPorCuentaContableInstance
    }

    @Transactional
    def update(SaldoPorCuentaContable saldoPorCuentaContableInstance) {
        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        if (saldoPorCuentaContableInstance.hasErrors()) {
            respond saldoPorCuentaContableInstance.errors, view:'edit'
            return
        }

        saldoPorCuentaContableInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SaldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect saldoPorCuentaContableInstance
            }
            '*'{ respond saldoPorCuentaContableInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SaldoPorCuentaContable saldoPorCuentaContableInstance) {

        if (saldoPorCuentaContableInstance == null) {
            notFound()
            return
        }

        saldoPorCuentaContableInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SaldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), saldoPorCuentaContableInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'saldoPorCuentaContable.label', default: 'SaldoPorCuentaContable'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def actualizar(){        
        def pContable=session.periodoContable
        saldoPorCuentaContableService.generar(session.empresa,pContable.ejercicio,pContable.mes)
        flash.message="Cuentas actualizadas"
        redirect action:'index'

    }

    @Transactional
    def actualizarSaldo(SaldoPorCuentaContable saldo){
        saldoPorCuentaContableService.actualizarSaldo(saldo)
        flash.message="Saldo  ${saldo} actualizado "
        redirect action:'show',id:saldo.id
    }

    def actualizarPeriodo(PeriodoContable periodoContable){
        log.info 'Actualizando periodo contable al: '+periodoContable
        //def origin=request.getHeader('referer')
        session.periodoContable=periodoContable
        //redirect(uri: request.getHeader('referer') )
        redirect action:'index'
    }

    @Transactional
    def generarCierreAnual(){
        log.info "Generando saldos para cierre anual ${session.periodoContable.ejercicio} / ${session.periodoContable.mes}"
        cierreContableService.generarSaldosParaCierre(session.empresa,session.periodoContable.ejercicio)
        redirect action:'index'

    }

    def print(){
        def command=new ReportCommand()
        command.reportName="BalanzaDeComprobacion"
        command.empresa=session.empresa
        params.YEAR=session.periodoContable.ejercicio
        params.MES=session.periodoContable.mes
        params.EMPRESA=session.empresa.nombre
        params.INICIAL=0.0
        def stream=reportService.build(command,params)
        def file="Balanza_${cparams.YEAR}${params.MES}_"+new Date().format('ss')+'.'+command.formato.toLowerCase()
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
    }

    def auxiliar(SaldoPorCuentaContable saldo){
        def dets=PolizaDet.findAll(
            "from PolizaDet d where d.cuenta=? and d.poliza.ejercicio=? and d.poliza.mes=? "
            ,[saldo.cuenta,saldo.ejercicio,saldo.mes])
        [saldo:saldo,partidas:dets]
    }
    
    def imprimirAuxiliarContable(){
        log.info 'Imprimiento auxiliar'+params
        
        def saldo=SaldoPorCuentaContable.get(params.long('id'))
        
        if(!saldo)
            throw new RuntimeException("No existe saldo : "+id)
            
        def dets=PolizaDet.findAll("from PolizaDet d where d.cuenta=? and date(d.fecha) between ? and ? order by d.fecha"
            ,[saldo.cuenta,saldo.fecha.inicioDeMes(),saldo.fecha.finDeMes()])
        
        def saldoPadre=SaldoPorCuentaContable.get(saldo.cuenta.padre.id)
        
        def acumulado=0.0
        def modelData=dets.collect { det ->
            acumulado=(acumulado+(det.debe-det.haber))
            def res=[
            'Poliza':det.poliza.folio.toLong()
            ,'Tipo':det.poliza.tipo
            ,'Fecha':det.poliza.fecha
            ,'Descripcion':det.descripcion
            ,'Debe':det.debe
             ,'Haber':det.haber
             ,'Asiento':det.asiento
             ,'Acumulado':acumulado
             ]
            return res
        }
        NumberFormat nf=NumberFormat.getNumberInstance()
        def repParams=[CUENTA:saldo.cuenta.clave
            ,FECHA_INI:saldo.fecha.inicioDeMes().text()
            ,FECHA_FIN:saldo.fecha.finDeMes().text()
            ,INICIAL:nf.format(saldo.saldoInicial)
            ,CARGOS:nf.format(saldo.debe)
            ,ABONOS:nf.format(saldo.haber)
            ,SALDO:nf.format(saldo.saldoFinal)
            ,YEAR:saldo.year.toString()
            ,MES:saldo.mes.toString()
            ,CONCEPTO:saldo.cuenta.descripcion
            ]
        repParams<<params
        println 'Ejecutando reporte params:'+params+'\n Registros: '+modelData
        chain(controller:'jasper',action:'index',model:[data:modelData],params:repParams)
        
    }
}

