package com.luxsoft.mobix.core

import grails.transaction.Transactional
import com.luxsoft.lx.core.Producto
import com.luxsoft.lx.core.Linea
import grails.validation.ValidationException
import com.luxsoft.lx.ventas.Venta
import com.luxsoft.lx.ventas.VentaDet

@Transactional
class ArrendamientoService {

    def ventaService

    def save(Arrendamiento a) {
    	def clave="RT_${a.tipo}"
    	Producto p=Producto.findByClave(clave)
    	if(!p){
            def linea=Linea.findByClave('RENTA')
            if(linea==null)
                throw new ArrendamientoException(message:'No exite la linea RENTA dada de alta')
    		p = new Producto(clave:clave,descripcion:"Renta ${a.tipo}",unidad:'SERVICIO',linea:linea,empresa:a.empresa)
    		p.save failOnError:true
    	}
    	a.producto=p
    	a.validate()
    	if(a.hasErrors()){
    		throw new ArrendamientoException(message:'Arrendamiento invalido',arrendamiento:a)
    	}
    	a.save failOnError:true
    	return a
    }

    def generarRentas(Arrendamiento arrendamiento){
        if(!arrendamiento.rentas){
            def rentas=arrendamiento.generarRentas()
            rentas.each{ renta ->
                def found=arrendamiento.rentas.find{it.folio==renta.folio}
                if(!found){
                    def calendar=Calendar.getInstance()
                    calendar.setTime(renta.inicio)
                    calendar.set(Calendar.DATE,arrendamiento.diaDeCorte)
                    renta.fechaDeCobro=calendar.getTime()
                    arrendamiento.addToRentas(renta)
                }
                    
            }
            arrendamiento.save()
        }
        return arrendamiento
    }

    def generarVenta(Renta renta){
        if(renta.ventaDet)
            throw new RentaException(message:'Renta ya facturada',renta:renta)
        def arr=renta.arrendamiento
        Venta venta=new Venta(
            cliente:arr.cliente,
            fecha:renta.fechaDeCobro,
            tipo:'ARRENDAMIENTO',
            empresa:renta.arrendamiento.empresa,
            comentario:"Renta de ${renta.arrendamiento.inmueble.descripcion} (${renta.periodo})"
        )
        VentaDet det=new VentaDet(
            producto:arr.producto,
            cantidad:1,
            precio:arr.precio,
            importeBruto:arr.precio,
            comentario:'Renta de inmueble'
        )

        venta.addToPartidas(det)
        ventaService.save venta
        renta.ventaDet=det
        renta.save flush:true
        return venta

    }
}

class ArrendamientoException extends RuntimeException{
	Arrendamiento arrendamiento
	String message
}
class RentaException extends RuntimeException{
    Renta renta
    String message
}
