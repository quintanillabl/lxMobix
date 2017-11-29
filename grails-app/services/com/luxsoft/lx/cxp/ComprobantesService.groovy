package com.luxsoft.lx.cxp

import com.luxsoft.cfdi.ComprobanteFiscal
import org.apache.commons.lang.exception.ExceptionUtils
import com.luxsoft.cfdi.Acuse


class ComprobantesService {

	static transactional = false

	def  consultaService

	def importarDirectorio(File dir) {
        assert dir.isDirectory(), 'Debe seleccionar un directorio'
        
        def rows = 0;
        
        dir.listFiles().each { file ->
        	if( rows < 10 ) {
        		try {
        		if(file.isDirectory()){
        			importarDirectorio(file)
        		}
        		if( file.getName().endsWith('.xml')) {
        			importar(file)	
        			rows ++
        		}

        	}
        	catch(Exception e) {
        		String msg=ExceptionUtils.getRootCauseMessage(e)
        		println 'Error importando archivo: ' + file.name + ' ' + msg
        	}
        	}
        	
        	
        }
       return 0	
    }
	
	def importar(File xmlFile) {
		def xml = new XmlSlurper().parse(xmlFile)
        if(xml.name()=='Comprobante') {
        	def data=xml.attributes()
            def version = data.version
            if(version == null){
                version = data.Version
            }
            if(version == '3.3'){
                return importarCfdi33(xml)
			} else {
				return importarCfdi32(xml)
			}
        }
	}

	def importarCfdi32(def xml) {
		
		def receptor = xml.breadthFirst().find { it.name() == 'Receptor'}.attributes()
		def receptorNombre = receptor['nombre']
		def receptorRfc = receptor['rfc']

        def emisor = xml.breadthFirst().find { it.name() == 'Emisor'}.attributes()
        def emisorNombre = emisor['nombre']
        def emisorRfc = emisor['rfc']          

        def serie=xml.attributes()['serie']
        def folio=xml.attributes()['folio']
        def fecha=Date.parse("yyyy-MM-dd'T'HH:mm:ss", xml.attributes()['fecha'])  

        def timbre=xml.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
        def uuid=timbre.attributes()['UUID']
        def total = xml.attributes()['total'] as BigDecimal

    	println "Importando CFDI 3.2: ${uuid}  De: ${emisorRfc} para ${receptorRfc} ${fecha.text()}  ${total}" 
		def acuse = validar(uuid, emisorRfc, receptorRfc, total)
		
		ComprobanteFiscal comprobante = new ComprobanteFiscal(
			emisor: emisorNombre,
			emisorRfc: emisorRfc,
			receptorNombre: receptorNombre,
			receptorRfc: receptorRfc,
			uuid: uuid,
			total: total
		)
		if( acuse ){
			println "Acuse Estado: ${acuse.getEstado().getValue().toString()} Codigo: ${acuse.getCodigoEstatus().getValue().toString()}"
			comprobante.acuseEstado = acuse.getEstado().getValue().toString()
			comprobante.acuseCodigo = acuse.getCodigoEstatus().getValue().toString()
			comprobante.ultimaValidacion = new Date()
		}
		//comprobante.save failOnError: true , flush: true
		return comprobante
	}

	def importarCfdi33(def xml ) {

		def emisor = xml.breadthFirst().find { it.name() == 'Emisor'}.attributes()
        def emisorNombre = emisor['Nombre']
        def emisorRfc = emisor['Rfc']          


		def receptor = xml.breadthFirst().find { it.name() == 'Receptor'}.attributes()
		def receptorNombre = receptor['Nombre']
		def receptorRfc = receptor['Rfc']

        
        def serie=xml.attributes()['Serie']
        def folio=xml.attributes()['Folio']
        def fecha=Date.parse("yyyy-MM-dd'T'HH:mm:ss", xml.attributes()['Fecha'])  

        def timbre=xml.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
        def uuid=timbre.attributes()['UUID']
        def total = xml.attributes()['Fotal'] as BigDecimal
        println "Importando CFDI 3.3: ${uuid}  De: ${emisorRfc} para ${receptorRfc} ${fecha.text()}  ${total}" 
		def acuse = validar(uuid, emisorRfc, receptorRfc, total)
		println "Acuse Estado: ${acuse.estado} Codigo: ${acuse.codigoEstatus}"
		return [
			emisorRfc: emisorRfc,
			receptorRfc: receptorRfc,
			uuid: uuid,
			total: total
		]
	}

	def Acuse validar(String uuid, String emisorRfc, String receptorRfc, BigDecimal total){
        try {
            String qq="?re=$emisorRfc&rr=$receptorRfc&tt=$total&id=${uuid}"
            log.info 'Validando en SAT Expresion impresa: '+qq
            Acuse acuse = consultaService.consulta(qq)
            return acuse
        }
        catch(Exception e) {
            String msg=ExceptionUtils.getRootCauseMessage(e)
            log.error('Error validando ', msg)
        }
        
        
    }

}