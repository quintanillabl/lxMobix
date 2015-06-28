package com.luxsoft.lx.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Secured(["hasAnyRole('CONTABILIDAD','ADMIN')"])
@Transactional(readOnly = true)
class ClienteController {

    static allowedMethods = [save: "POST", update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort=params.sort?:'dateCreated'
        params.order='desc'
        respond Cliente.findAllByEmpresa(session.empresa,params), model:[clienteInstanceCount: Cliente.countByEmpresa(session.empresa)]
    }

    def show(Cliente clienteInstance) {
        respond clienteInstance
    }

    def create() {
        respond new Cliente(params)
    }

    @Transactional
    def save(Cliente clienteInstance) {
        if (clienteInstance == null) {
            notFound()
            return
        }

        if (clienteInstance.hasErrors()) {
            respond clienteInstance.errors, view:'create'
            return
        }

        clienteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cliente.label', default: 'Cliente'), clienteInstance.id])
                redirect clienteInstance
            }
            '*' { respond clienteInstance, [status: CREATED] }
        }
    }

    def edit(Cliente clienteInstance) {
        respond clienteInstance
    }

    @Transactional
    def update(Cliente clienteInstance) {
        if (clienteInstance == null) {
            notFound()
            return
        }

        if (clienteInstance.hasErrors()) {
            respond clienteInstance.errors, view:'edit'
            return
        }

        clienteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cliente.label', default: 'Cliente'), clienteInstance.id])
                redirect clienteInstance
            }
            '*'{ respond clienteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cliente clienteInstance) {

        if (clienteInstance == null) {
            notFound()
            return
        }

        clienteInstance.delete flush:true
        flash.message="Cliente eliminado"
        redirect action:"index"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def getClientesJSON() {

        def list=Cliente.findAllByEmpresaAndNombreIlike(session.empresa,"%"+params.term+"%",[max:10,sort:"nombre",order:"desc"])

        
        list=list.collect{ c->
            def nombre="$c.nombre"
            def direccion=[calle:c.direccion?.calle?:'']
            direccion.numeroInterior=c.direccion?.numeroInterior?:''
            direccion.numeroExterior=c.direccion?.numeroExterior?:''
            direccion.colonia=c.direccion?.colonia?:''

            def jsonDir=direccion as JSON
            //println 'Direccion: '+jsonDir
            jsonDir.toString()

            [id:c.id,
            label:nombre,
            value:nombre,
            nombre:nombre,
            rfc:c.rfc,
            emailCfdi:c.emailCfdi,
            direccion:jsonDir
            ]
        }
        def res=list as JSON
        
        render res
    }
}
