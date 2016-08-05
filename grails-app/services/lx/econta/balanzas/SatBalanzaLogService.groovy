package lx.econta.balanzas

import grails.transaction.Transactional

import org.bouncycastle.util.encoders.Base64

import com.luxsoft.lx.contabilidad.*
import lx.econta.utils.*
import com.luxsoft.lx.core.Empresa

@Transactional
class SatBalanzaLogService {

    def generar(Empresa empresa, int ejercicio, int mes, String comentario = null) {

    	def tipo = definirTipo(empresa,ejercicio,mes)
    	Balanza balanza = buildBalanzaSat(empresa,ejercicio,mes,tipo[0])
    	byte[] xml = BalanzaUtils.toXmlByteArray(balanza)

    	def balanzaSat

    	def found = SatBalanzaLog.where { rfc == empresa.rfc && ejercicio == ejercicio && mes == mes && acuse == null}.find()
    	
    	if(found) 
    		balanzaSat = found
    	else {
    		balanzaSat = new SatBalanzaLog(
				rfc: balanza.rfc,
				nombre: empresa.nombre,
	 			ejercicio: balanza.anio,
				mes: mes,
				comentario: comentario,
				tipo:tipo)
    	}
    	balanzaSat.comentario = comentario
    	balanzaSat.xml = xml
		balanzaSat.save failOnError:true, flush:true
    }

    def buildBalanzaSat(Empresa empresa, int ejercicio, int month,String tipo){

    	ObjectFactory factory = new ObjectFactory()
        Balanza balanza = factory.createBalanza()
        
        balanza.with {
            version = '1.1'
            anio = ejercicio
            mes = month.toString().padLeft(2,'0')
            rfc = empresa.rfc
            tipoEnvio = tipo
            noCertificado = empresa.numeroDeCertificado
			certificado = new String(Base64.encode(empresa.getCertificado().getEncoded()))
        }
    	def cuentas=CuentaContable.where{ empresa==empresa && padre==null}
    	
    	cuentas.each { cta ->
    		Balanza.Ctas ctas = factory.createBalanzaCtas()
    		def saldo=SaldoPorCuentaContable.findWhere(empresa:empresa,cuenta:cta, ejercicio:ejercicio,mes:month)
    		if(saldo){
				ctas.with {
		    	    debe = saldo.debe
		    	    haber = saldo.haber
		    	    numCta = saldo.cuenta.clave
		    	    saldoIni = saldo.saldoInicial
		    	    saldoFin = saldo.saldoFinal
		    	}
		    	balanza.getCtas().add(ctas)
    		}
        	
        	cta.subCuentas.each { subCta ->
        		Balanza.Ctas child = factory.createBalanzaCtas()
        		def s2=SaldoPorCuentaContable.findWhere(empresa:empresa,cuenta:subCta, ejercicio:ejercicio,mes:month)
        		if(s2){
					child.with {
			    	    debe = s2.debe
			    	    haber = s2.haber
			    	    numCta = s2.cuenta.clave
			    	    saldoIni = s2.saldoInicial
			    	    saldoFin = s2.saldoFinal
			    	}
			    	balanza.getCtas().add(child)
        		}
				
        	}
    		
    	}
    	Date lastUpdated = findUltimaModificacionContable(empresa,ejercicio,month)
        balanza.setFechaModBal(DateUtils.getXmlGregorianCalendar(lastUpdated))
        return balanza
    }

    def String definirTipo(Empresa empresa,int e, int m){
    	def found = SatBalanzaLog.where { rfc == empresa.rfc && ejercicio == 2016 && mes == 7 && acuse != null}.find()
    	return found ? 'NORMAL' : 'COMPLEMENTARIA'
    }

    def findUltimaModificacionContable(Empresa empresa, int ejercicio, int mes){
    	def found  = PolizaDet.where {poliza.empresa == empresa && poliza.ejercicio == ejercicio && poliza.mes == mes}.last()
    	return found ? found.poliza.lastUpdated : new Date()
    }
}
