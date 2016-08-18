package com.luxsoft.lx.tesoreria

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import java.text.DecimalFormat
import com.luxsoft.lx.cxp.Requisicion
import com.luxsoft.lx.bi.ReportCommand
import com.luxsoft.cfdi.ImporteALetra


@Secured(["hasAnyRole('ADMIN','TESORERIA')"])
@Transactional(readOnly = true)
class PagoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def pagoService

    def chequeService

    def reportService

    def index(Integer max) {
        params.max = 2000
        params.sort=params.sort?:'fecha'
        params.order='desc'
        respond Pago.findAllByEmpresa(session.empresa,params), model:[pagoInstanceCount: Pago.countByEmpresa(session.empresa)]
    }

    def show(Pago pagoInstance) {
        respond pagoInstance
    }

    def create() {
        //[pagoInstance:new Pago(fecha:new Date())]
        [pagoInstance: new PagoCommand(fecha:new Date())]
    }

    @Transactional
    def save(Pago command) {
        if (command == null) {
            notFound()
            return
        }
        // if (command.hasErrors()) {
        //     respond command.errors, view:'create'
        //     return
        // }
        def pagoInstance = pagoService.save(command)
        flash.message = "Pago a proveedor registrado"
        redirect action:'show',params:[id:pagoInstance.id]
        
    }

    def edit(Pago pagoInstance) {
        respond pagoInstance
    }

    @Transactional
    def update(Pago pagoInstance) {
        if (pagoInstance == null) {
            notFound()
            return
        }

        if (pagoInstance.hasErrors()) {
            respond pagoInstance.errors, view:'edit'
            return
        }
        pagoInstance.save flush:true
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Pago.label', default: 'Pago'), pagoInstance.id])
        redirect action:'show',id:pagoInstance.id
    }

    @Transactional
    def delete(Pago pagoInstance) {

        if (pagoInstance == null) {
            notFound()
            return
        }
        def res = pagoService.delete(pagoInstance)
        if(res?.comentario?.contains('CANCELADO')){
            flash.message = "Pago cancelado NO ELIMINADO POR HABER SIDO USADO EN UN CHEQUE"
            redirect action:"show", id:res.id
            return
        }
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Pago.label', default: 'Pago'), pagoInstance.id])
        redirect action:"index", method:"GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pago.label', default: 'Pago'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def requisicionesPendientes(){
        
        log.info 'Buscando requisiciones pendientes de pago '
        def empresa=session.empresa
        
        def list=Requisicion.findAll("from Requisicion r where r.empresa=? and r  not in(select p.requisicion from Pago p)",[empresa])

        def pattern = "\$##,###.##"
        def mf = new DecimalFormat(pattern)

        list=list.collect{ r->
            def nombre="Folio: ${r.folio} (${r.proveedor}) Pago:${r.pago.format('dd/MM/yyyy')} Total:${mf.format(r.total)} ${r?.formaDePago?.toString()}"
            [id:r.id,
            label:nombre,
            value:nombre,
            total:r.total,
            afavor:r.aFavor,
            pago:r.pago.format('dd/MM/yyyy'),
            formaDePago:r.formaDePago.toString()
            ]
        }
        def res=list as JSON
        
        render res

    }

    @Transactional
    def aplicar(Pago pago){
        pagoService.aplicar(pago)
        flash.message="Pago aplicado"
        redirect action:'show',params:[id:pago.id]
    }

    @Transactional
    def cancelarAplicacion(Pago pago){
        pagoService.cancelarAplicaiones(pago)
        flash.message="Aplicaciones de pago canceladas"
        redirect action:'show',params:[id:pago.id]
    }

    @Transactional
    def generarCheque(Pago pagoInstance){
        assert pagoInstance.cuenta.tipo=='CHEQUES','El pago no es con cheque'
        assert pagoInstance.cheque==null,'Cheque ya generado para el pago '+pagoInstance.id
        pagoInstance=chequeService.generarCheque(pagoInstance)
        redirect action:'show', params:[id:pagoInstance.id]
    }

    @Transactional
    def imprimirCheque(Pago pagoInstance){

        def cheque=pagoInstance.cheque
        
        def command=new ReportCommand()
        command.reportName=cheque.cuenta.impresionTemplate
        command.empresa=session.empresa
        
        params.ID=pagoInstance.cheque.id as String
        params.IMPLETRA=ImporteALetra.aLetra(pagoInstance.importe.abs())
        def stream=reportService.build(command,params)
        def file="Cheque_${cheque.cuenta.numero}_${cheque.folio}.pdf"
        cheque.impresion=new Date()
        cheque.save flush:true
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)

    }

    def imprimirPoliza(Pago pagoInstance){
        def cheque=pagoInstance.cheque
        def command=new ReportCommand()
        command.reportName="PolizaCheque"
        command.empresa=session.empresa
        params.ID=pagoInstance.cheque.id as String
        params.EMPRESA=session.empresa.nombre
        params.IMPLETRA=ImporteALetra.aLetra(pagoInstance.importe.abs())
        def stream=reportService.build(command,params)
        def file="PolizaCheque_${cheque.cuenta.numero}_${cheque.folio}.pdf"
        
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
        
    }

    @Transactional
    def cancelarCheque(Pago pagoInstance,String comentario){
        def cheque=pagoInstance.cheque
        chequeService.cancelarCheque(pagoInstance,comentario)
        flash.message="Cheque cancelado ${cheque.id} ${comentario}"
        redirect action:'show',params:[id:pagoInstance.id]
    }
}

import org.grails.databinding.BindingFormat
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.core.FormaDePago
import com.luxsoft.lx.cxp.Requisicion
import grails.validation.Validateable
import com.luxsoft.lx.sat.BancoSat

@Validateable
class PagoCommand{

    Empresa empresa

    Requisicion requisicion

    CuentaBancaria cuenta

    String aFavor

    //FormaDePago formaDePago

    @BindingFormat('dd/MM/yyyy')
    Date fecha=new Date()

    BigDecimal requisitado

    BigDecimal importe

    String referencia

    String comentario

    BancoSat bancoDestino

    String cuentaDestino

    static constraints={
        bancoDestino nullable:true
        cuentaDestino nullable:true, validator: { val, obj ->
            if(obj.formaDePago == FormaDePago.TRANSFERENCIA && !obj)
                return 'cuentaDestinoMandatoria'
        }

    }

    def toPago(){
        Pago pago=new Pago()
        pago.properties=this.properties
        return pago

    }
}
