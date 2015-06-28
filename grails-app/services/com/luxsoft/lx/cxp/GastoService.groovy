package com.luxsoft.lx.cxp

import grails.transaction.Transactional
import com.luxsoft.lx.utils.MonedaUtils

@Transactional
class GastoService {

    def springSecurityService

    def save(Gasto gasto){
        gasto.with{
            def user=springSecurityService.getCurrentUser().username
            creadoPor=delegate.creadoPor?:user
            modificadoPor=user
            partidas=delegate.partidas?:[] as Set
        } 
        gasto.save failOnError:true

        return gasto
    }

    def update(Gasto gasto){

    }

    def agregarConcepto(Gasto gasto,GastoDet det){

        if(det.cuentaContable==null)
            det.cuentaContable=gasto.cuentaContable
        gasto.addToPartidas(det)
        actualizarImportes gasto
        gasto.save failOnError:true
    }

    def eleiminarPartida(GastoDet det){
        Gasto gasto=det.gasto
        gasto.removeFromPartidas(det)
        gasto.actualizarImportes()
        gasto=gasto.save failOnError:true
        return gasto
    }


    def cargarComprobanteFislcal(){

    }

    def actualizarImportes(Gasto gasto){
        if(gasto.partidas){
            gasto.with{
                partidas*.actualizarImportes()
                importe=partidas.sum(0.0,{it.importe}) 
                subTotal=importe-descuento
                impuesto=MonedaUtils.calcularImpuesto(subTotal,impuestoTasa?:MonedaUtils.IVA)

            }
            // gasto.partidas*.actualizarImportes()
            // gasto.importe=gasto.partidas.sum(0.0,{it.importe})
            // gasto.impuesto=MonedaUtils.calcularImpuesto(gasto.importe,impuesto?:MonedaUtils.IVA)
        }
        gasto.with{
            total=subTotal+impuesto
        }
    }
}
/*
import org.apache.commons.io.IOUtils
def gasto=Gasto.get(3)
def data=gasto.xml
String xmlData = IOUtils.toString(data, "UTF-8");
//println xmlData

def res = new XmlSlurper().parse(new ByteArrayInputStream(data))
//println "Data:"+ cfdi.class
//def cfdi=new XmlParser().parseText(xmlData)

//def file=new File('/Users/rcancino/Downloads/SGM950714DC2_CARR700317575_MRY8666403.xml')
//println file.getText()
//def res = new XmlSlurper().parseText(file.getText())

res.breadthFirst().each{
    
    //println it.attributes().LugarExpedicion
    
    // if(it.name()=='TimbreFiscalDigital'){
    // 	println it.name()
    //     println it.getClass()
    //     println it.attributes()
       
    // }
	
}

println 'Comprobante:  '+res.attributes()
def emisor= res.breadthFirst().find { it.name() == 'Emisor'}
def receptor=res
println 'Emisor: '+emisor.attributes()
def timbre=res.breadthFirst().find { it.name() == 'TimbreFiscalDigital'}
println 'Timbre:'+timbre.attributes()
*/

