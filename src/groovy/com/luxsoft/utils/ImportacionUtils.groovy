package com.luxsoft.utils

import groovy.sql.*
import org.springframework.jdbc.datasource.SingleConnectionDataSource
import com.luxsoft.utils.Periodo
import java.text.SimpleDateFormat
import org.apache.commons.logging.LogFactory
import com.luxsoft.cfdi.*

import com.luxsoft.lx.core.*
import com.luxsoft.lx.ventas.*


class ImportacionUtils {

	private static final log=LogFactory.getLog(this)


	public static  importarCfdis(String host){
		SingleConnectionDataSource ds=new SingleConnectionDataSource(
		            driverClassName:'com.mysql.jdbc.Driver',
		    		url:"jdbc:mysql://${host}:3306/mobix",
		            username:'root',
		            password:'sys')
		Sql sql=new Sql(ds)
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
		def res=[]
		sql.eachRow("select * from cfdi  "){ row->
		    def cfdi=new Cfdi()
		    cfdi.xmlName=row.xml_name
		    cfdi.cargarXml(row.xml)
		    cfdi.grupo='CARGA_INICIAL'
		    cfdi.referencia=row.origen
		    cfdi.creadoPor='admin'
		    cfdi.modificadoPor='admin'
		    cfdi.timbrado=df.parse(cfdi.timbreFiscal.fechaTimbrado)
		    cfdi.comentario=row.comentario
		    if(Periodo.obtenerYear(cfdi.fecha)==2015){
		    	def found=Cfdi.findByUuid(cfdi.uuid)
		    	if(found==null){
		    		cfdi=cfdi.save failOnError:true,flush:true
		    		res.add(cfdi)
		    		log.info "Cfdi importado: ${cfdi}"
	    		}else{
	    			log.info "Cfdi ${cfdi} ya ha sido importado"
	    		}
		    }
		}
		return res

	}

	public static importarVentas(String host){
		def ventas=findVentas(host)
		Cfdi.findAllByGrupo('CARGA_INICIAL').each{cfdi ->
			def empresa=Empresa.findByRfc(cfdi.emisorRfc)
			assert empresa,'No existe la empresa: '+cfdi.emisorRfc
			
			def cliente=Cliente.findByEmpresaAndRfc(empresa,cfdi.receptorRfc)
			if(cliente==null){
				def direccion=CfdiUtils.toDireccion(cfdi.getComprobante().getReceptor().getDomicilio())
				cliente=new Cliente(empresa:empresa,nombre:cfdi.receptor,rfc:cfdi.receptorRfc,direccion:direccion)
				cliente.save flush:true,failOnError:true
				log.info 'Cliente generado: '+cliente
			}
			def venta=Venta.findByCfdi(cfdi)
			if(venta==null){
				def ventaRow=ventas.find{it.uuid==cfdi.uuid}
				
				venta=new Venta(
					empresa:empresa,
					cliente:cliente,
					cfdi:cfdi,
					folio:cfdi.folio.toInteger(),
					serie:cfdi.serie,
					fecha:cfdi.fecha,
					comentario:ventaRow.comentario?:'VENTA IMPORTADA',
					importe:cfdi.getComprobante().getSubTotal(),
					subTotal:cfdi.getComprobante().getSubTotal(),
					impuesto:cfdi.getComprobante().getImpuestos().getTotalImpuestosTrasladados(),
					total:cfdi.total,
					creadoPor:'admin',
					modificadoPor:'admin',
					tipo:'ARRENDAMIENTO',
					formaDePago:cfdi.getComprobante().getMetodoDePago()
					)
				
				def partidas=findPartidasDeVentas(host,ventaRow.id)
				partidas.each{ det->
					def producto=Producto.findByClave('RT_MENSUAL')
					def ventaDet=new VentaDet(
							producto:producto,
							cantidad:det.cantidad,
							precio:det.precio,
							importeBruto:det.importe,
							descuentoTasa:0.0,
						    descuento:0.0,
						    importeNeto:det.importe,
						    impuesto:0.0,
							comentario:det.comentario
						)
					venta.addToPartidas(ventaDet)
				}
				venta=venta.save failOnError:true,flush:true
			    //println 'Alta de venta: '+venta+ 'Valida: '+venta.errors
			    println 'Venta generada: '+venta
			}else{
				println 'Actualizando venta existente...'+venta
			}
			
		}
		return []

	}

	private static findVentas(String host){
		SingleConnectionDataSource ds=new SingleConnectionDataSource(
		            driverClassName:'com.mysql.jdbc.Driver',
		    		url:"jdbc:mysql://${host}:3306/mobix",
		            username:'root',
		            password:'sys')
		Sql sql=new Sql(ds)
		sql.rows("select v.*,c.uuid from venta v  join cfdi c on(v.id=c.origen)")
	}

	private static findPartidasDeVentas(String host,Long id){
		SingleConnectionDataSource ds=new SingleConnectionDataSource(
		            driverClassName:'com.mysql.jdbc.Driver',
		    		url:"jdbc:mysql://${host}:3306/mobix",
		            username:'root',
		            password:'sys')
		Sql sql=new Sql(ds)
		return sql.rows("select v.*,p.descripcion,p.clave from venta_det v  join producto p on(v.producto_id=p.id) where venta_id=?",id)

	}
	
	
	
}


