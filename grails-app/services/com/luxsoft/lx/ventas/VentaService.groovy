package com.luxsoft.lx.ventas

import grails.transaction.Transactional
import grails.validation.ValidationException
import com.luxsoft.lx.core.Folio
import com.luxsoft.mobix.core.Renta

@Transactional
class VentaService {

	def springSecurityService
    def cfdiService
    def ventaListenerManager
    

    def save(Venta venta) {
    	assert !venta.id,"La venta $venta.id ya ha sido persistida"
    	
    	
    	venta.with{
    		def user=springSecurityService.getCurrentUser().username
    		creadoPor=user
    		modificadoPor=user
    		folio=nextFolio(delegate)

    	}
        venta.actualizarImportes()
    	venta.save(failOnError:true)
        event('altaDeVenta',venta)
    	return venta

    }

    def delete(Venta venta){
        if(venta.cfdi){
            throw new VentaException(venta:venta,message:'Venta ya facturada imposible eliminar')
        }
        venta.partidas.each{
            def found=Renta.findByVentaDet(it)
            if(found){
                found.ventaDet=null
            }
        }
        venta.delete flush:true

        event('bajaDeVenta',venta)
        
    }

    def mandarFacturar(Venta venta){
        if(venta.cfdi)
            throw new VentaException(message:'Venta ya facturada',venta:venta)
        def cfdi=cfdiService.generar(venta)
        return venta

    }

    def agregarPartida(Venta venta,VentaDet det){
        
        det.actualizarImportes()
        
        //Aplicando listeners
        ventaListenerManager.beforeSavePartida(det)

        venta.addToPartidas(det)
        venta.actualizarImportes()
        venta.save failOnError:true

    }

    def eleiminarPartida(VentaDet det){
        Venta venta=det.venta
        venta.removeFromPartidas(det)
        venta.modificadoPor=springSecurityService.getCurrentUser().username
        venta.actualizarImportes()
        venta=venta.save failOnError:true
        return venta
    }


    private Long nextFolio(Venta venta){
        def folio=Folio.findByEmpresaAndSerie(venta.empresa,'VENTA')
        if(folio==null){
            folio=new Folio(empresa:venta.empresa,serie:'VENTA',folio:0l)
        }
        def res=folio.next()
        folio.save()
        return res
    }
}

class VentaException extends RuntimeException{
	Venta venta
	String message
}
