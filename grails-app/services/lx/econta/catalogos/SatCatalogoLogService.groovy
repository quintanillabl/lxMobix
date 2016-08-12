package lx.econta.catalogos

import grails.transaction.Transactional

import org.bouncycastle.util.encoders.Base64

import lx.econta.catalogos.*
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.CuentaContable
import org.apache.commons.lang.exception.ExceptionUtils

@Transactional
class SatCatalogoLogService {

    def generar(Empresa empresa, int ejercicio, int mes, String comentario = null) {
    	
    	Catalogo catalogo = buildCatalogo(empresa,ejercicio,mes)
    	byte[] xml = CatalogoUtils.toXmlByteArray(catalogo)
		SatCatalogoLog log = new SatCatalogoLog(
			rfc: catalogo.rfc,
			nombre: empresa.nombre,
	 		ejercicio: ejercicio,
			mes: SatCatalogoLog.Mes.fromValue(mes),
			comentario: comentario,
			xml: xml
		)

		log.save failOnError:true, flush:true
    }

    def buildCatalogo(Empresa empresa, int ejercicio, int mes){
    	ObjectFactory factory = new ObjectFactory()
    	Catalogo catalogo = factory.createCatalogo()
    	catalogo.version = '1.1'
    	catalogo.rfc = empresa.rfc
		catalogo.anio = ejercicio
		catalogo.mes = mes.toString().padLeft(2,'0')
		catalogo.noCertificado = empresa.numeroDeCertificado
		catalogo.certificado = new String(Base64.encode(empresa.getCertificado().getEncoded()))

		CuentaContable.where { empresa == empresa && padre == null && suspendida==false}.each {
	        Catalogo.Ctas cta = factory.createCatalogoCtas();
	        cta.setNumCta(it.clave) 
	        cta.setDesc(it.descripcion) 
	        cta.setCodAgrup(it.cuentaSat.codigo) 
	        cta.setNatur(it.naturaleza[0]=='M'?'D':it.naturaleza[0]) 
	        cta.setNivel(1) 
	        catalogo.getCtas().add(cta) 
	        
	        it.subCuentas.findAll().each{ sub ->
                if(sub.suspendida == false) {
                    Catalogo.Ctas child = factory.createCatalogoCtas() 
                    child.setNumCta(sub.clave) 
                    child.setDesc(sub.descripcion) 
                    child.setCodAgrup(sub.cuentaSat.codigo) 
                    child.setNatur(sub.naturaleza[0]=='M'?'D':sub.naturaleza[0]) 
                    child.setSubCtaDe(it.clave) 
                    child.setNivel(2) 
                    catalogo.getCtas().add(child) 
                }
	        }
			
		}

		return catalogo
    }

    def importar(byte[] data, fileName){

    	Catalogo catalogo 
    	try {
            def schema = CatalogoUtils.getSchema()
            def unmarshaller = CatalogoUtils
                .getContext()
                .createUnmarshaller()
            unmarshaller.setSchema(schema)
    		catalogo = (Catalogo)unmarshaller.unmarshal(new ByteArrayInputStream(data))
    	}
    	catch(javax.xml.bind.JAXBException jxe) {
    		def mm = ExceptionUtils.getRootCauseMessage(jxe)
    		log.error(jxe)
    		throw new SatCatalogoLogException(
    			"El archivo $fileName no es un archivo XML valido para crear un Catalogo (${mm})")
    	}
    	
    	SatCatalogoLog cat = SatCatalogoLog.where {
    		rfc == catalogo.rfc && ejercicio == catalogo.anio && mes == SatCatalogoLog.Mes.fromValue(catalogo.mes.toInteger())
    	}.find()
    	if(cat){
    		throw new SatCatalogoLogException("Ya exste el catalogo  ${cat.mes} - ${cat.ejercicio}  para $cat.nombre Folio(Id):$cat.id")
    	}
    	def empresa = Empresa.where {rfc == catalogo.rfc}.find()
    	assert empresa, 'No existe empresa con rfc: '+catalogo.rfc

    	cat = new SatCatalogoLog(
    			rfc:catalogo.rfc,
    			nombre:empresa.nombre,
    			ejercicio: catalogo.anio,
    			mes: SatCatalogoLog.Mes.fromValue(catalogo.mes.toInteger()),
    			comentario: "Importadoc con: ${fileName}",
    			xml: CatalogoUtils.toXmlByteArray(catalogo)
    		)
    	cat.save failOnError:true, flush:true
    }


    //def url = "http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogoCuentas/CatalogoCuentas_1_1.xslt"

}

class SatCatalogoLogException extends RuntimeException {

	SatCatalogoLogException(String message){
		super(message)
	}

}