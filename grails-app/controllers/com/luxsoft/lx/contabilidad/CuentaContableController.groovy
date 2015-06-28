package com.luxsoft.lx.contabilidad



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class CuentaContableController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def cuentaContableService


    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def list=CuentaContable.findAllByEmpresaAndPadreIsNull(session.empresa,params)
        respond list, model:[cuentaContableInstanceCount: CuentaContable.countByEmpresaAndPadreIsNull(session.empresa)]
    }

    def show(CuentaContable cuentaContableInstance) {
        //respond cuentaContableInstance
        [cuentaContableInstance:cuentaContableInstance]
    }

    def create() {
        respond new CuentaContable(params)
    }

    @Transactional
    def save(CuentaContable cuentaContableInstance) {
        if (cuentaContableInstance == null) {
            notFound()
            return
        }

        if (cuentaContableInstance.hasErrors()) {
            respond cuentaContableInstance.errors, view:'create'
            return
        }
        assert cuentaContableService,'Debe existir el servicio'
        cuentaContableInstance=cuentaContableService.save(cuentaContableInstance)
        flash.message="Cuenta registrada: "+cuentaContableInstance.id
        redirect action:'show',params:[id:cuentaContableInstance.id]
    }

    def edit(CuentaContable cuentaContableInstance) {
        respond cuentaContableInstance
    }

    @Transactional
    def update(CuentaContable cuentaContableInstance) {
        if (cuentaContableInstance == null) {
            notFound()
            return
        }

        if (cuentaContableInstance.hasErrors()) {
            respond cuentaContableInstance.errors, view:'edit'
            return
        }

        cuentaContableInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CuentaContable.label', default: 'CuentaContable'), cuentaContableInstance.id])
                redirect cuentaContableInstance
            }
            '*'{ respond cuentaContableInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CuentaContable cuentaContableInstance) {

        if (cuentaContableInstance == null) {
            notFound()
            return
        }
        cuentaContableService.delete cuentaContableInstance
        flash.message="Cuenta $cuentaContableInstance eliminada"
        if(cuentaContableInstance.padre){
            forward  action:'show',id:cuentaContableInstance.padre.id
            return
        }
        redirect action:'index'
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaContable.label', default: 'CuentaContable'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    
    def createSubCuenta(CuentaContable cuenta){
        if (cuenta == null) {
            notFound()
            return
        }
        if(cuenta.detalle){
            flash.message="Cuenta de detalle no puede agregar sub cuentas"
            render view:'show',model:[cuenta:cuenta,subCuenta:new CuentaContable()]
            return
        }
        [cuenta:cuenta,subCuenta:new CuentaContable()]

    }

    @Transactional
    def saveSubCuenta(CuentaContable subCuenta){
        def cuenta=CuentaContable.get(params.cuentaId)
        assert cuenta,'No existe la cuenta de padre'
        assert subCuenta.padre==null,'La sub cuenta no debe tener padre'
        subCuenta.validate(['clave','descripcion','cuentaSat','detalle'])
        if (subCuenta.hasErrors()) {
            render view:'createSubCuenta',model:[subCuenta:subCuenta]
            return
        }
        cuenta=cuentaContableService.agregarSubCuenta(cuenta,subCuenta)
        flash.message="Sub cuenta $subCuenta registrada "
        redirect action:'show',params:[id:cuenta.id]
        

    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def getCuentasDeDetalleJSON() {
        
        def term=params.term+'%'
        def list=CuentaContable.findAllByEmpresaAndDetalleAndDescripcionIlike(session.empresa,true,term,[max:20,sort:"clave",order:"desc"])

        
        list=list.collect{ c->
            def nombre="$c.clave $c.descripcion"
            
            [id:c.id,
            label:nombre,
            value:nombre]
        }
        def res=list as JSON
        render res
    }

}

