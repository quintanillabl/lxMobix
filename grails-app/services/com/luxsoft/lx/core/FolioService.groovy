package com.luxsoft.lx.core

import grails.transaction.Transactional

@Transactional
class FolioService {

    def nextFolio(Empresa empresa,String serie){
        def folio=Folio.findByEmpresaAndSerie(empresa,serie)
        if(folio==null){
            folio=new Folio(empresa:empresa,serie:serie,folio:0l)
        }
        def res=folio.next()
        folio.save()
        return res
    }
}
