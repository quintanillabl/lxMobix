package com.luxsoft.lx.core

import grails.transaction.Transactional

@Transactional
class FolioService {

    def nextFolio(Empresa empresa,String serie){
        def folio=Folio.findByEmpresaAndSerie(empresa,serie)
        if(folio==null){
            folio=new Folio(empresa:empresa,serie:serie,folio:0l)
            //folio.save(failOnError:true,flush:true)
        }
        def res=folio.next()
        folio.save(failOnError:true)
        return res
    }

    // private Long nextFolio(Venta venta){
    //     def folio=Folio.findByEmpresaAndSerie(venta.empresa,'VENTA')
    //     if(folio==null){
    //         folio=new Folio(empresa:venta.empresa,serie:'VENTA',folio:0l)
    //     }
    //     def res=folio.next()
    //     folio.save()
    //     return res
    // }
}
