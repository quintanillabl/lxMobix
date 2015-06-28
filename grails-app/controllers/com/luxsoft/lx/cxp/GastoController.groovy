package com.luxsoft.lx.cxp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON
import com.luxsoft.cfdi.*


@Secured(["hasAnyRole('GASTOS','ADMIN')"])
@Transactional(readOnly = true)
class GastoController {

    def importadorService

    def gastoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.sort=params.sort?:'lastUpdated'
        params.order='desc'
        respond Gasto.findAllByEmpresa(session.empresa,params), model:[gastoInstanceCount: Gasto.countByEmpresa(session.empresa)]
    }

    def show(Gasto gastoInstance) {
        respond gastoInstance
    }

    def create() {
        [gastoInstance:new GastoCommand()]
    }

    @Transactional
    def save(GastoCommand command) {
        
        if (command == null) {
            notFound()
            return
        }
        if (command.hasErrors()) {
            render view:'create',model:[gastoInstance:command]
            return
        }
        def gastoInstance=gastoService.save(command.toGasto())
        flash.message="Gasto registrado $gastoInstance.id"
        redirect action:'edit',id:gastoInstance.id
    }

    def edit(Gasto gastoInstance) {
        respond gastoInstance
    }

    @Transactional
    def update(Gasto gastoInstance) {
        if (gastoInstance == null) {
            notFound()
            return
        }
        if (gastoInstance.hasErrors()) {
            respond gastoInstance.errors, view:'edit'
            return
        }
        gastoInstance=gastoService.save(gastoInstance)
        flash.message="Gasto actualizado $gastoInstance.id"
        redirect action:'index'
    }

    @Transactional
    def delete(Gasto gastoInstance) {

        if (gastoInstance == null) {
            notFound()
            return
        }

        gastoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Gasto.label', default: 'Gasto'), gastoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gasto.label', default: 'Gasto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    def uploadCfdi(){
        def xml=request.getFile('xml')
        //def referencia=params.referencia
        //def grupo=params.grupo
        def user=getAuthenticatedUser().username
        if (xml.empty) {
            flash.message = 'CFDI incorrecto (archivo vacío)'
            redirect action:'index'
            return
        }
        try {
            log.info 'Cargando CFDI con archivo: '+xml
            Cfdi cfdi=importadorService.cargarComprobante(xml.getBytes(),xml.getOriginalFilename())
            flash.message="CFDI  de gastos importado "+cfdi.toString()
            redirect action:'show',params:[id:cfdi.id]
        }
        catch(CfdiException e) {
            flash.message=e.message
            println 'Error cargando CFDI: '+e.message
            redirect action:'index'
        }
    }

    @Transactional
    def registrarCfdiXml(Gasto gasto){
        def xml=request.getFile('file')
        if (xml.empty) {
            flash.message = 'CFDI incorrecto (archivo vacío)'
            redirect action:'edit',id:gasto.id
            return
        }
        gasto.xml=xml.getBytes()
        gasto.xmlName=xml.getOriginalFilename()
        gasto.save failOnError:true
        flash.message="CFDI cargado"
        redirect action:'edit',id:gasto.id
    }
}

import org.grails.databinding.BindingFormat
//import grails.validation.Validateable
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.core.Proveedor
import com.luxsoft.lx.contabilidad.CuentaContable
import com.luxsoft.lx.utils.MonedaUtils
//@Validateable
class GastoCommand{

    Empresa empresa
    Proveedor proveedor
    CuentaContable cuentaContable
    
    @BindingFormat('dd/MM/yyyy')
    Date fecha=new Date()

    @BindingFormat('dd/MM/yyyy')
    Date vencimiento=new Date()+1

    Currency moneda=Currency.getInstance('MXN')
    BigDecimal tipoDeCambio=1.0
    //Importes
    BigDecimal importe=0.0
    BigDecimal descuento=0.0
    BigDecimal impuestoTasa=MonedaUtils.IVA*100
    BigDecimal impuesto=0.0

    BigDecimal retensionIvaTasa=0
    BigDecimal retensionIsrTasa=0
    
    //Datos de CFDI...
    String uuid
    String serie
    String folio

    String comentario

    static constraints={
        importFrom Gasto
    }

    Gasto toGasto(){
        def gasto=new Gasto()
        gasto.properties=properties
        gasto.impuestoTasa=impuestoTasa/100
        return gasto
    }
    
    
}


