package com.luxsoft.lx.ventas



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured(["hasAnyRole('VENTAS','ADMIN')"])
@Transactional(readOnly = true)
class VentaDetController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def ventaService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VentaDet.list(params), model:[ventaDetInstanceCount: VentaDet.count()]
    }

    def show(VentaDet ventaDetInstance) {
        if (ventaDetInstance == null) {
            notFound()
            return
        }
        [venta:ventaDetInstance.venta,ventaDetInstance:ventaDetInstance]
    }

    def create(Venta venta) {
        assert venta,'No puede asignar partias sin una venta'
        [ventaInstance:venta,ventaDetInstance:new VentaDet()]
    }

    @Transactional
    def save(VentaDetCommand command) {
        println 'Salvando con params: '+params
        if (command == null) {
            notFound()
            return
        }

        if (command.hasErrors()) {

            respond command.errors, model:[ventaInstance:command.venta,ventaDetInstance:command],view:'create'
            return
        }
        def ventaDetInstance=command.toVentaDet()
        def venta=command.venta
        venta=ventaService.agregarPartida(venta,ventaDetInstance)
        /** ---------- Persistencia mover a service ------------
        def ventaDetInstance=command.toVentaDet()
        ventaDetInstance.actualizarImportes()
        def venta=command.venta
        venta.addToPartidas(ventaDetInstance)
        venta.actualizarImportes()
        venta.save failOnError:true
         --------------------------------------------------- **/

        flash.message="Partida agregada"
        redirect controller:'venta',action:'show',params:[id:venta.id]
        
    }

    def edit(VentaDet ventaDetInstance) {
        if (ventaDetInstance == null) {
            notFound()
            return
        }
        [venta:ventaDetInstance.venta,ventaDetInstance:ventaDetInstance]
    }

    @Transactional
    def update(VentaDet ventaDetInstance) {
        if (ventaDetInstance == null) {
            notFound()
            return
        }

        if (ventaDetInstance.hasErrors()) {
            respond ventaDetInstance.errors, view:'edit'
            return
        }

        ventaDetInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'VentaDet.label', default: 'VentaDet'), ventaDetInstance.id])
                redirect ventaDetInstance
            }
            '*'{ respond ventaDetInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(VentaDet ventaDetInstance) {
        if (ventaDetInstance == null) {
            notFound()
            return
        }
        def venta=ventaService.eleiminarPartida(ventaDetInstance)
        redirect controller:'venta', action:'show',id:venta.id
        
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ventaDet.label', default: 'VentaDet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

import grails.validation.Validateable
import com.luxsoft.lx.core.Producto
@Validateable
class VentaDetCommand{

        Venta venta    
        Producto producto
        BigDecimal cantidad=0.0
        BigDecimal precio=0.0
        BigDecimal descuentoTasa=0.0
        BigDecimal impuesto=0.0
        String comentario

    static constraints={
        importFrom VentaDet
        cantidad min:1.0
        precio min:1.0
    }

    def toVentaDet(){
        VentaDet det=new VentaDet(producto:producto,
            cantidad:cantidad,
            precio:precio,
            descuentoTasa:descuentoTasa,
            impuesto:impuesto,
            comentario:comentario
            )
        return det

    }
}
