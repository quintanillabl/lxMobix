package com.luxsoft.cfdi

import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import org.springframework.security.access.annotation.Secured

import grails.validation.Validateable
import org.grails.databinding.BindingFormat
import groovy.transform.ToString

import net.sf.jasperreports.engine.JRExporterParameter
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.JRPdfExporterParameter
import net.sf.jasperreports.export.PdfExporterConfiguration
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.springframework.core.io.Resource
import com.luxsoft.lx.core.Cliente



@Secured(["hasAnyRole('OPERADOR','VENTAS','ADMIN')"])
class CfdiController {

	def cfdiService

	def jasperService

    def index(Integer max) {
		params.max = Math.min(max ?: 50, 100)
		params.sort=params.sort?:'dateCreated'
		params.order='desc'
		[cfdiInstanceList:Cfdi.findAllByEmisorRfc(session.empresa.rfc,params), cfdiInstanceCount: Cfdi.countByEmisor(session.empresa.nombre)]
		//[cfdiInstanceList:Cfdi.list(params), cfdiInstanceCount: Cfdi.count()]
	}

	def search(CfdiSearch command){
		command.receptor=command.receptor?:'%'
        command.receptor=command.receptor+'%'
        //command.receptor='%'
        command.fechaInicial=command.fechaInicial?:new Date()-30
        command.fechaFinal=command.fechaFinal?:new Date()
        command.folio=command.folio?:'%'
        params.max = 50
        params.sort=params.sort?:'dateCreated'
        params.order='desc'
        log.info 'Buscando por: '+command
        println 'Buscando por :'+command
        def hql="from Cfdi c where lower(c.receptor) like ?  and date(c.fecha) between ? and ? and folio like ? order by fecha desc"
        
        def list=Cfdi.findAll(hql,[command.receptor.toLowerCase(),command.fechaInicial
        	,command.fechaFinal
        	,command.folio]
        	,params)
        render view:'index',model:[cfdiInstanceList:list,cfdiInstanceCount:list.size()]
    }
	
	def show(Cfdi cfdi){
		def cliente=Cliente.findByNombre(cfdi.receptor)
		def model=[cfdiInstance:cfdi]
		if(cliente){
			model.correoDeEnvio=cliente.emailCfdi
		}
		return model
		
	}
	
	def mostrarXml(long id){
		def cfdi=Cfdi.findById(id)
		//render view:'cfdiXml',model:[cfdiInstance:cfdi,xml:cfdi.getComprobanteDocument().xmlText()]
		render(text: cfdi.comprobanteDocument.xmlText(), contentType: "text/xml", encoding: "UTF-8")
	}


	
	def descargarXml(long id){
		Cfdi cfdi=Cfdi.findById(id)
		
		response.setContentType("application/octet-stream")
		response.setHeader("Content-disposition", "attachment; filename=\"$cfdi.xmlName\"")
		response.outputStream << cfdi.getComprobanteDocument().newInputStream()
		
	}
	
	def imprimir(Cfdi cfdi){
		
		
		Comprobante cfd=cfdi.getComprobante()
		def conceptos=cfd.getConceptos().getConceptoArray()
		
		def modelData=conceptos.collect { cc ->
			
			def res=[
			'cantidad':cc.getCantidad()
			 ,'NoIdentificacion':cc.getNoIdentificacion()
			 ,'descripcion':cc.getDescripcion()
			 ,'unidad':cc.getUnidad()
			 ,'ValorUnitario':cc.getValorUnitario()
			 ,'Importe':cc.getImporte()
			 ]
			 if(cc.cuentaPredial){
				res.CUENTA_PREDIAL=cc.cuentaPredial.numero
			}
			return res
		}
		def repParams=CfdiPrintUtils.resolverParametros(cfdi)
		File logoFile = grailsApplication.mainContext.getResource("images/kyo_logo.png").file

		if(logoFile.exists()){
			repParams['EMPRESA_LOGO']=logoFile
		}

		params<<repParams
		params.FECHA=cfd.fecha.getTime().format("yyyy-MM-dd'T'HH:mm:ss")

		
		//println 'Generando PDF de CFDI: '+repParams
		//println 'LOGO: '+ logoFile
		chain(controller:'jasper',action:'index',model:[data:modelData],params:params)

		
	}

	def mandarCorreo(CfdiMail command){
		command.validate()
		if(command.hasErrors()){
			flash.message="Errores en parametros para envio de correo ${command.errors}"
			redirect action:'show',params:[id:command?.cfdi?.id]
		}
		def pdfStream=generarPdf(command.cfdi)
		def cfdi=command.cfdi
		sendMail{
			multipart true
			to command.mail
			subject "CFDI: de  $cfdi.folio de $cfdi.emisor"
			from "noreplay@kyo.mx"
			body "Envío de comprobante fiscal digital generado por $cfdi.emisor"
			attachBytes "${command.cfdi.xmlName}", "text/xml", command.cfdi.xml
			attachBytes "${command.cfdi.xmlName.replace('.xml','.pdf')}", 'application/pdf', pdfStream.toByteArray()
		}
		flash.message="Correo enviado"
		redirect action:'show',params:[id:command.cfdi.id]

	}

	//@Secured(["hasAnyRole('VENTAS',ADMINISTRACION','ADMIN')"])
	def cancelar(Cfdi cfdi){
		log.info 'Cancelando cfdi: '+cfdi.uuid
		def message=params.comentaio?:'NA'
		def res=cfdiService.cancelar(cfdi,message)
		flash.message="CFDI Cancelado exitosamente"
		//redirect controller:'cancelacionDeCfdi', action:'index'
		redirect action:'show',params:[id:cfdi.id]
	}

	def imprimirCfdi(Cfdi cfdi){
		def pdfStream=generarPdf(cfdi)
		render(file: pdfStream.toByteArray(), contentType: 'application/pdf',fileName:cfdi.xmlName.replace('.xml','.pdf'))
	}

	def generarPdf(Cfdi cfdi){
		Comprobante cfd=cfdi.getComprobante()
		def conceptos=cfd.getConceptos().getConceptoArray()
		
		def modelData=conceptos.collect { cc ->
			
			def res=[
			'cantidad':cc.getCantidad()
			 ,'NoIdentificacion':cc.getNoIdentificacion()
			 ,'descripcion':cc.getDescripcion()
			 ,'unidad':cc.getUnidad()
			 ,'ValorUnitario':cc.getValorUnitario()
			 ,'Importe':cc.getImporte()
			 ]
			 if(cc.cuentaPredial){
				res.CUENTA_PREDIAL=cc.cuentaPredial.numero
			}
			return res
		}
		def repParams=CfdiPrintUtils.resolverParametros(cfdi)
		File logoFile = grailsApplication.mainContext.getResource("images/kyo_logo.png").file
		
		
		
		if(logoFile.exists()){
			params['EMPRESA_LOGO']=logoFile
		}
		params<<repParams
		params.FECHA=cfd.fecha.getTime().format("yyyy-MM-dd'T'HH:mm:ss")

		params.IMPORTE=params.IMPORTE as String
		params.IVA=params.IVA as String
		params.TOTAL=params.TOTAL as String
		params.DESCUENTOS=params.DESCUENTOS as String

		def reportDef=new JasperReportDef(
			name:'MobixCFDI'
			,fileFormat:JasperExportFormat.PDF_FORMAT
			,reportData:modelData,
			,parameters:params
			)

		/*
		Resource resource = reportDef.getReport()
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportDef.reportData)
		JasperPrint print= JasperFillManager.fillReport(JasperCompileManager.compileReport(resource.inputStream)
			, reportDef.parameters
			, jrBeanCollectionDataSource)
		ByteArrayOutputStream  pdfStream = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfStream); // your output goes here
		//exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
		exporter.exportReport();
		*/
		//println 'Generando PDF para CFDI con parametros: '+params
		def pdfStream=jasperService.generateReport(reportDef)
		//render(file: pdfStream.toByteArray(), contentType: 'application/pdf',fileName:cfdi.xmlName.replace('.xml','.pdf'))
		return pdfStream
	}

	//@Transactional
    def uploadCfdi(){
        def xml=request.getFile('xml')
		def referencia=params.referencia
		def grupo=params.grupo
		def user=getAuthenticatedUser().username
        if (xml.empty) {
            flash.message = 'CFDI incorrecto (archivo vacío)'
            redirect action:'index'
            return
        }
		try {
            log.info 'Cargando CFDI con archivo: '+xml
            Cfdi cfdi=cfdiService.cargarCombrobante(
            	xml.getBytes(),
            	xml.getOriginalFilename(),
            	referencia,
            	grupo,
            	user)
            flash.message="CFDI importado "+cfdi.toString()
            redirect action:'show',params:[id:cfdi.id]
        }
        catch(CfdiException e) {
			flash.message=e.message
			println 'Error cargando CFDI: '+e.message
			redirect action:'index'
        }
        

    }

	

	
}

class CfdiMail{
	Cfdi cfdi
	String mail

	static constraints={
		mail email:true
	}
}

@Validateable
@ToString(includeNames=true,includePackage=false)
class CfdiSearch{
    
    String receptor

    @BindingFormat('dd/MM/yyyy')
    Date fechaInicial=new Date()-30
    
    @BindingFormat('dd/MM/yyyy')
    Date fechaFinal=new Date()

    String folio
    

    static constraints={
        fechaInicial nullable:true
        fechaFinal nullable:true
        receptor nullable:true
        folio nullable:true
    }
}
