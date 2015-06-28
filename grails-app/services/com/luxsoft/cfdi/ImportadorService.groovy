package com.luxsoft.cfdi

import grails.transaction.Transactional
import org.apache.commons.lang.exception.ExceptionUtils

@Transactional
class ImportadorService {

    def cargarCfdi(byte[] bytes,String fileName,String referencia,String grupo,String usuario) {

    	try {
            Cfdi cfdi=new Cfdi()
            cfdi.fileName=fileName
            cfdi.cargarXml(bytes)
            
            def found=Cfdi.findByUuid(cfdi.uuid)
            if(found){
                throw new CfdiException(message:"UUID ya registrado $found.uuid",cfdi:cfdi)
            }
            
            cfdi.referencia=referencia
            cfdi.grupo=grupo
            cfdi.usuario=usuario

            cfdi.save (failOnError:true)
            
            validarEnElSat(cfdi)
            log.info "CFDI importado: "+cfdi.uuid
            return cfdi    
    	}
    	catch(Exception e) {
    		//log.error e
            e.printStackTrace()
    		String msg=ExceptionUtils.getRootCauseMessage(e)
    		log.info msg
    		throw new CfdiException(message:msg)
    	}
    }
}
