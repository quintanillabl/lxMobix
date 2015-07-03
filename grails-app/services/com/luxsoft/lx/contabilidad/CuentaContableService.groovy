package com.luxsoft.lx.contabilidad

import grails.transaction.Transactional

@Transactional
class CuentaContableService {

    def save(CuentaContable cuenta) {
    	if(cuenta.padre==null) cuenta.nivel=1
    	cuenta=cuenta.save failOnError:true
    	return cuenta
    }

    def agregarSubCuenta(CuentaContable cuenta,CuentaContable subCuenta){
    	
    	assert !cuenta.detalle,'Cuenta de detalle no puede recibir sub cuentas'
    	subCuenta.clave=cuenta.clave+'-'+subCuenta.clave
    	subCuenta.tipo=cuenta.tipo
    	subCuenta.subTipo=cuenta.subTipo
    	subCuenta.naturaleza=cuenta.naturaleza
    	subCuenta.deResultado=cuenta.deResultado
    	subCuenta.empresa=cuenta.empresa
    	subCuenta.nivel=cuenta.nivel+1

    	subCuenta.validate()
    	//println "Errores de validacion: "+subCuenta.errors
    	if(subCuenta.hasErrors()){
    		throw new CuentaContableException(cuenta:subCuenta,message:"Errores de validacion el agregar sub cuenta")
    	}
    	cuenta.addToSubCuentas(subCuenta)
    	//println 'Sub cuentas: '+cuenta.subCuentas.size()
    	cuenta.save flush:true
    	return cuenta
    }

    def delete(CuentaContable cuenta){
    	cuenta.delete flush:true
    	return cuenta
    }
}

class CuentaContableException extends RuntimeException{
	CuentaContable cuenta
	String message
}