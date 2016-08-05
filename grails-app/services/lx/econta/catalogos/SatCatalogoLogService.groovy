package lx.econta.catalogos

import grails.transaction.Transactional

import org.bouncycastle.util.encoders.Base64

import lx.econta.catalogos.*
import com.luxsoft.lx.core.Empresa
import com.luxsoft.lx.contabilidad.CuentaContable

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

		CuentaContable.where { padre == null }.each {
	        Catalogo.Ctas cta = factory.createCatalogoCtas();
	        cta.setNumCta(it.clave) 
	        cta.setDesc(it.descripcion) 
	        cta.setCodAgrup(it.cuentaSat.codigo) 
	        cta.setNatur(it.naturaleza[0]=='M'?'D':it.naturaleza[0]) 
	        cta.setNivel(1) 
	        catalogo.getCtas().add(cta) 
	        
	        it.subCuentas.each{ sub ->
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

		return catalogo
    }


    //def url = "http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogoCuentas/CatalogoCuentas_1_1.xslt"

}
