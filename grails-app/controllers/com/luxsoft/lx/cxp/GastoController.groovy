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
        params.max = 200
        params.sort=params.sort?:'fecha'
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
    def importarCfdi(){
        def xml=request.getFile('xmlFile')
        if(xml==null){
            flash.message="Archivo XML no localizado"
            redirect action:'index'
            return
        }
        File xmlFile = File.createTempFile(xml.getName(),".temp");
        xml.transferTo(xmlFile)
        //println 'Xml File:'+xmlFile.getCanonicalPath()
        def gasto=gastoService.importar(xmlFile)
        if(gasto.instanceOf(Gasto)){
            flash.message="Gasto importado desde archivo XML ${xml.getName()}"
            redirect action:'edit',id:gasto.id
            return
        }
        flash.message="No es posible importar el gasto desde el archivo ${xml.getName()}"
        redirect action:'index'

    }

   

    @Transactional
    def validarEnElSat(Gasto gasto){
        if(gasto.acuse){
            flash.message="Factura de gastos (CFDI) ya validado en el SAT"
            redirect action:'edit',id:gasto.id
            return
        }
        gastoService.validarEnElSat(gasto)
        flash.message="Factura de gastos (CFDI) validado en el SAT"
        redirect action:'edit',id:gasto.id
        return
    }

    def mostrarAcuse(Gasto gasto){
        def acuse=gastoService.toAcuse(gasto.acuse)
        def xml=gastoService.toXml(acuse)
        render(text: xml, contentType: "text/xml", encoding: "UTF-8")
    }

    def descargarAcuse(Gasto gasto){
        log.info 'Descargando archivo xml: '+gasto.uuid
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=Acuse_${gasto.uuid}.xml")
        response.outputStream << gasto.cfdiXml
        return
        
    }

    def verCfdiXml(Gasto gasto){
        def acuse=gastoService.toAcuse(gasto.acuse)
        def xml=gastoService.toXml(acuse)
        render(text: xml, contentType: "text/xml", encoding: "UTF-8")
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


