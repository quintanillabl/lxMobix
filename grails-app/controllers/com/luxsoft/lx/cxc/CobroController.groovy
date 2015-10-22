package com.luxsoft.lx.cxc



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import com.luxsoft.lx.ventas.Venta
import grails.converters.JSON
import java.text.DecimalFormat


@Secured(["hasAnyRole('ADMIN','TESORERIA','GASTOS')"])
@Transactional(readOnly = true)
class CobroController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def cobroService

    def index(Integer max) {
        params.max = 200
        params.sort=params.sort?:'fecha'
        params.order='desc'
        respond Cobro.findAllByEmpresa(session.empresa,params), model:[cobroInstanceCount: Cobro.countByEmpresa(session.empresa)]
    }

    def show(Cobro cobroInstance) {
        respond cobroInstance
    }

    def create() {
        respond new Cobro(fecha:new Date())
    }

    @Transactional
    def save(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        try {
            cobroInstance=cobroService.save(cobroInstance)
            flash.message = "Cobro registrado ${cobroInstance.id}"
            redirect action:'edit' ,params:[id:cobroInstance.id]
            return
            
        }
        catch(CobroException e) {
            log.error(e)
            flash.message=e.message
            flash.error=e.message
            render view:'create',model:[cobroInstance:e.cobro]
        }
    }

    @Secured(["hasRole('GASTOS')"])
    def createAplicacion(Cobro cobroInstance){
        
        [aplicacionDeCobroInstance:new AplicacionDeCobro(pago:cobroInstance,fecha:new Date())]
    }

    @Transactional
    @Secured(["hasRole('GASTOS')"])
    def saveAplicacion(Cobro cobro,AplicacionDeCobro aplicacion,Venta factura){

        
        cobro=cobroService.agregarAplicacion(cobro,aplicacion)
        flash.message="Aplicacion registrada para la factura ${factura}"
        redirect action:'edit',params:[id:cobro.id]
    }

    @Transactional
    @Secured(["hasRole('GASTOS')"])
    def deleteAplicacion(AplicacionDeCobro aplicacion){
        def cobro=aplicacion.pago
        cobroService.eliminarAplicacion(aplicacion)
        flash.message="Aplicacion de cobro eliminada"
        redirect action:'edit',params:[id:cobro.id]
    }



    def edit(Cobro cobroInstance) {
        respond cobroInstance
    }

    @Transactional
    def update(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        if (cobroInstance.hasErrors()) {
            render view:'edit',model:[cobroInstance:cobroInstance]
            return
        }
        cobroInstance=cobroService.update(cobroInstance)
        flash.message = message(code: 'default.updated.message', args: [message(code: 'Cobro.label', default: 'Cobro'), cobroInstance.id])
        redirect cobroInstance
    }

    @Transactional
    @Secured(["hasRole('TESORERIA')"])
    def delete(Cobro cobroInstance) {
        if (cobroInstance == null) {
            notFound()
            return
        }
        cobroService.delete(cobroInstance)
        flash.message = "Cobro ${cobroInstance.id} eliminado"
        redirect action:"index", method:"GET"
    }


    def facturasPendientes(Cobro cobro){
        def cliente=cobro.cliente
        
        
        log.info 'Buscando facturas pendientes para cliente: '+cobro.cliente+ ' Term: '+params.term
        
        def list=Venta.findAll(
            "from Venta v where v.empresa=? and v.cliente=? and v.total-v.pagos>=0 and str(v.folio) like ? order by v.fecha desc",
            [cobro.empresa,cliente,params.term])

        def pattern = "\$##,###.##"
        def mf = new DecimalFormat(pattern)

        list=list.collect{ v->
            def nombre="Factura: ${v.folio} Fecha:${v.fecha.format('dd/MM/yyyy')} Total:${mf.format(v.total)} Saldo:${mf.format(v.saldo)}"
            [id:v.id,
            label:nombre,
            value:nombre,
            total:v.total,
            saldo:v.saldo,
            disponible:cobro.disponible
            ]
        }
        def res=list as JSON
        
        render res

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cobro.label', default: 'Cobro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
