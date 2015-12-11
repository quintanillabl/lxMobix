package com.luxsoft.mobix.core

import com.luxsoft.utils.Periodo
import com.luxsoft.lx.contabilidad.PeriodoContable
import com.luxsoft.lx.core.PeriodoOperativo

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
                    
                    redirect controller:'home',action:'seleccionDeEmpresa'
                    return false
                }

            
            }
        }

        all(controller:'*', action:'*') {

            before = {
                
                if(!session.ejercicion){
                    session.ejercicio=Periodo.obtenerYear(new Date())
                }
                
                if(!session.mes){
                    session.mes=Periodo.obtenerMes(new Date())
                }
                if(!session.periodoContable){
                    def today=new Date()
                    session.periodoContable=new PeriodoContable(
                            ejercicio:Periodo.obtenerYear(today),
                            mes:Periodo.obtenerMes(today)+1
                        )
                }
                if(!session.periodoTesoreria){
                    session.periodoTesoreria=new PeriodoOperativo()
                }
            }
            after = { Map model ->
                
            }
            afterView = { Exception e ->

            }
        }
        
        
        
    }
}
