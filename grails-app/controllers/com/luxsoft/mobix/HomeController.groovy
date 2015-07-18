package com.luxsoft.mobix

import grails.plugin.springsecurity.annotation.Secured
import com.luxsoft.lx.core.Empresa

@Secured(["hasRole('USUARIO')"])
class HomeController {

    def index() { 
    	//render 'Hello Home Controller here'
    }

    
    def seleccionDeEmpresa(){
        /*
         def origin=request.getHeader('referer')
         session.periodo=command
         redirect(uri: request.getHeader('referer') )
         */
         //respond [model:empresas:Empresa.list()]
         //respond Empresa.list()
         def empresas=Empresa.list()
         // println 'Empresasa: '+empresas
         // [empresas:Empresa.list()]
         respond empresas,model:[empresas:empresas]
    }

    def setCurrentEmpresa(Empresa empresa){
        assert empresa, "No se puede fijar empresa nula"
        def origin=request.getHeader('referer')
        session.empresa=empresa
       
        //redirect action: 'index' //action:'index'
        //respond empresa,view:'index',model:[empresa:empresa]
        render view:'index'

    }
    
}
