package com.luxsoft.mobix.core

class CoreFilters {

    def filters = {
        
        // all(controller:'*', action:'*') {
        //     before = {
        //         // if (!session.empresa && !actionName.equals('login')) {
        //         //     redirect controller:'home',action:'seleccionDeEmpresa'
        //         // }
        //     }
        //     after = { Map model ->
        //         //println 'Action :'+actionName
        //         //println "Emprea operando: "+session.empresa

        //     }
        //     afterView = { Exception e ->

        //     }
        // }
        
        
        // allExceptTwo(controller: 'login|logout|empresa',  invert: true){
        //     before = {
        //         if(!session.empresa && !actionName.equals('seleccionDeEmpresa')){
        //             println 'Debe seleccionar una empresa para operar...'
        //             // flash.message="Seleccion una empresa"
        //             redirect controller:'home',action:'seleccionDeEmpresa'
        //             //forward controller:'home',action:'seleccionDeEmpresa'
        //             return true
        //         }

        //     }
        //     after ={ Map model ->
        //         println 'After action:'+actionName+ " Controller: "+controllerName
        //     }

        // }
        
        validarEmpresa(controller:'*',action:'*',controllerExclude:'logout|empresa'){
            before={
                
                if(!session.empresa &&  (actionName!='auth') &&  (actionName!='denied') 
                    && (actionName!='seleccionDeEmpresa') 
                    && (actionName!='setCurrentEmpresa') ){
                    println 'Before: '+actionName+ " Controller: "+controllerName+ ' Empresa: '+session.empresa
                    redirect controller:'home',action:'seleccionDeEmpresa'
                    return false
                }

            
            }
            
        }
        
        
        
    }
}
