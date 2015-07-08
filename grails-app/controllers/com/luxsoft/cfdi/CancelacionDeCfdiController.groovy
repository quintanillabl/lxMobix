package com.luxsoft.cfdi


import org.springframework.security.access.annotation.Secured
@Secured(["hasAnyRole('ADMINISTRACION','VENTAS')"])
class CancelacionDeCfdiController {

    def index() { }


    def descargarAcuseXml(long id){
		
		CancelacionDeCfdi c=CancelacionDeCfdi.get(id)
		def name="AcuseDeCancelacion_${c.cfdi.xmlName}"
		response.setContentType("application/octet-stream")
		response.setHeader("Content-disposition", "attachment; filename=\"${name}\"")
		response.outputStream << c.getAka()

		// response.setContentType("application/octet-stream")
		// response.setHeader("Content-disposition", "attachment; filename=\"$cfdi.xmlName\"")
		// response.outputStream << cfdi.getComprobanteDocument().newInputStream()
		
	}

	def mostrarAcuse(long id){
		
		def c=CancelacionDeCfdi.get(id)
		String aka=new String(c.getAka())
		//render view:'cfdiXml',model:[cfdiInstance:cfdi,xml:cfdi.getComprobanteDocument().xmlText()]
		render(text: aka, contentType: "text/xml", encoding: "UTF-8")
	}
}

