package com.luxsoft.cfdix

import groovy.xml.*
import java.text.MessageFormat
import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle

import com.luxsoft.cfdi.Cfdi


class CfdiTimbre {
	
	String version
	String uuid
	String fechaTimbrado
	String selloCFD
	String selloSAT
	String noCertificadoSAT
	
	CfdiTimbre(Cfdi cfdi){
		build(cfdi)
	}

	def build(Cfdi cfdi){

	    ByteArrayInputStream is=new ByteArrayInputStream(cfdi.xml)
		def xml = new XmlSlurper().parse(is)
		def timbre=xml.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
		
	    this.version = timbre.attributes()['version']
		this.uuid = timbre.attributes()['UUID']
		this.fechaTimbrado = timbre.attributes()['FechaTimbrado']
		this.selloCFD = timbre.attributes()['selloCFD']
		this.selloSAT = timbre.attributes()['selloSAT']
		this.noCertificadoSAT = timbre.attributes()['noCertificadoSAT']
	    
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
	}
	
	public String cadenaOriginal(){
		String pattern="||{0}|{1}|{2}|{3}|{4}||";
		return MessageFormat.format(pattern, version,uuid,fechaTimbrado,selloCFD,noCertificadoSAT)
	}

}
