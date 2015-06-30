package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional

@Transactional
class PolizaService {

    def springSecurityService

    def save(Poliza poliza) {
        poliza.with{
            def user=currentUser()
            creadoPor=user
            modificadoPor=user
            if(folio==null)
                folio=nextFolio(poliza)

        }
    	poliza.save failOnError:true
    	return poliza
    }

    def update(Poliza poliza){
        poliza.modificadoPor=currentUser()
        poliza.save flush:true
        return poliza
    }

    def agregarConcepto(Poliza poliza,PolizaDet det){
        poliza.addToPartidas(det)
        poliza.modificadoPor=currentUser()
        poliza.actualizar()
        //actualizarImportes poliza
        poliza.save failOnError:true
    }

    def eleiminarPartida(PolizaDet det){
        Poliza poliza=det.poliza
        poliza.modificadoPor=currentUser()
        poliza.removeFromPartidas(det)
        poliza.actualizar()
        poliza=poliza.save failOnError:true
        return poliza
    }


    private Long nextFolio(Poliza poliza){
        def folio=PolizaFolio.findByEmpresaAndEjercicioAndMesAndTipo(poliza.empresa,poliza.ejercicio,poliza.mes,poliza.tipo)
        if(folio==null){
            folio=new PolizaFolio(empresa:poliza.empresa,ejercicio:poliza.ejercicio,mes:poliza.mes,tipo:poliza.tipo,folio:0l)
        }
        def res=folio.next()
        folio.save()
        return res
    }

    def currentUser(){
        return springSecurityService.getCurrentUser().username
    }

    
}
