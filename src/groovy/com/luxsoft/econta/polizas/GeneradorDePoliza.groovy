package com.luxsoft.econta.polizas

import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class GeneradorDePoliza  implements ApplicationContextAware{


    private ApplicationContext context

    def generar(def empresa,def fecha,def procesador){
        def service=context.getBean(procesador.service)
        assert service,"No existe el procesador de polizas: ${procesador}"
        log.info 'Generando poliza con procesador: '+procesador
        def res=service.generar(empresa,fecha,procesador)
        return res
    }

    public void setApplicationContext(ApplicationContext applicationContext){
        context=applicationContext
    }

    
}
