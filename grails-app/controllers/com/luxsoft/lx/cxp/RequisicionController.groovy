package com.luxsoft.lx.cxp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.cfdi.*
import java.text.DecimalFormat
import com.luxsoft.lx.bi.ReportCommand



@Secured(["hasAnyRole('GASTOS','ADMIN')"])
@Transactional(readOnly = true)
class RequisicionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET",savePartida:"POST"]

    def requisicionService

    def reportService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Requisicion.findAllByEmpresa(session.empresa,params), 
            model:[requisicionInstanceCount: Requisicion.countByEmpresa(session.empresa)]
    }

    def show(Requisicion requisicionInstance) {
        respond requisicionInstance
    }

    def create() {
        respond new Requisicion(params)
    }

    @Transactional
    def save(Requisicion requisicionInstance) {
        if (requisicionInstance == null) {
            notFound()
            return
        }
        requisicionInstance.validate(['proveedor','pago','comentario','tipo'])
        if (requisicionInstance.hasErrors()) {
            respond requisicionInstance.errors, view:'create'
            return
        }
        requisicionInstance=requisicionService.save requisicionInstance
        flash.message="Requisición generada: ${requisicionInstance.id}"
        redirect action:'edit',id:requisicionInstance.id

        
    }

    def edit(Requisicion requisicionInstance) {
        respond requisicionInstance
    }

    @Transactional
    def update(Requisicion requisicionInstance) {
        if (requisicionInstance == null) {
            notFound()
            return
        }
        requisicionInstance.validate(['proveedor','pago','comentario','tipo'])
        if (requisicionInstance.hasErrors()) {
            respond requisicionInstance.errors, view:'create'
            return
        }
        requisicionInstance=requisicionService.update requisicionInstance
        flash.message="Requisición actualizada: ${requisicionInstance.id}"
        redirect action:'index'

        
    }

    @Transactional
    def delete(Requisicion requisicionInstance) {

        if (requisicionInstance == null) {
            notFound()
            return
        }
        requisicionInstance.delete flush:true
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'Requisicion.label', default: 'Requisicion'), requisicionInstance.id])
        redirect action:"index"
        
    }

    @Transactional
    def createPartida(Requisicion requisicionInstance){
        [requisicionInstance:requisicionInstance,requisicionDetInstance:new RequisicionDet()]
    }

    @Transactional
    def savePartida(Requisicion requisicion,RequisicionDet requisicionDetInstance){
        //println 'Salvando partida: '+requisicionDetInstance
        //println 'Requisicion: '+requisicion
        requisicionService.agregarPartida(requisicion,requisicionDetInstance)
        flash.message="Partida registrada"
        redirect action:'edit',params:[id:requisicion.id]
    }

    @Transactional
    def eliminarPartida(RequisicionDet requisicionDet){
        def requisicion=requisicionDet.requisicion
        requisicionService.eliminarPartida(requisicionDet)
        flash.message="Partida eliminada"
        redirect action:'edit',params:[id:requisicion.id]
    }

    def cxpPendientes(Requisicion requisicion) {
        def proveedor=requisicion.proveedor
        //def list=Cliente.findAllByEmpresaAndNombreIlike(session.empresa,"%"+params.term+"%",[max:10,sort:"nombre",order:"desc"])
        params.sort='fecha'
        params.order="desc"
        params.max=20
        def hql="from CuentaPorPagar c where c.empresa=? and c.proveedor=? and c.total-c.requisitado>0 and str(c.id) like ?"
        def list =CuentaPorPagar.findAll(hql,[session.empresa,proveedor,params.term],params)

        def pattern = "\$##,###.##"
        def mf = new DecimalFormat(pattern)

        list=list.collect{ c->
            def nombre="""Id: ${c.id} (Folio:${c.folio}) ${c.fecha.format('dd/MM/yyyy')} Tot: ${mf.format(c.total)} Pen: ${mf.format(c.getPendienteRequisitar()) } (${c.empresa.clave})
            """
            [id:c.id,
            label:nombre,
            value:nombre,
            total:c.total,
            pendiente:c.getPendienteRequisitar()
            ]
        }
        def res=list as JSON
        render res
    }

    def print(Requisicion requisicion){
        def command=new ReportCommand()
        command.reportName="Requisicion"
        command.empresa=session.empresa
        params.ID=requisicion.id as String
        params.EMPRESA=session.empresa.nombre
        def stream=reportService.build(command,params)
        def file="Requisicion_${requisicion.id}.pdf"
        render(
            file: stream.toByteArray(), 
            contentType: 'application/pdf',
            fileName:file)
        
    }
    

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requisicion.label', default: 'Requisicion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
