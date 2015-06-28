package com.luxsoft.lx.contabilidad

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build
import com.luxsoft.lx.core.Empresa
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CuentaContable)
@Mock([CuentaContable,Empresa])
@Build([CuentaContable,Empresa])
class CuentaContableSpec extends Specification {

	def empresa

    def setup() {
    	empresa=Empresa.build(clave:'DEMO')
    }

    def cleanup() {
    }

    void "No debe permitir sub cuentas en cuenta de detalle"(){
    	given:'Una cuenta de detalle'
    	def cuenta=CuentaContable.buildWithoutSave(clave:'4001-001',detalle:true)
    	when:'Agregamos una sub cuenta '
    	cuenta.addToSubCuentas(CuentaContable.buildWithoutSave(clave:'4001-001-001',detalle:true))
    	
    	then:'La validacion debe fallar'
    	!cuenta.validate()
    	cuenta.errors.errorCount==1
    	cuenta.hasErrors()
    	cuenta.errors['padre'].code=='subCuentasNoPermitidas'
    	println cuenta.errors['padre']


    }

    void "Debe persistir cuentas nuevas"() {
    	given:'Una cuenta nueva'
    	def cuenta=CuentaContable.buildWithoutSave(clave:'4001-001',detalle:false)

    	when:'Salvamos'
    	cuenta.save()

    	then:'La cuenta persiste correctamente'
    	cuenta.errors.errorCount==0
    	cuenta.id
    	CuentaContable.get(cuenta.id).clave=='4001-001'
    }

    void "Debe persistir sub cuentas"(){
    	given:'Una cuenta padre'

    	def cuenta=CuentaContable.build(clave:'4001',detalle:false,empresa:empresa)
    	println 'Padre: '+cuenta.padre

    	when:'Agregamos una sub cuenta y salvamos'
    	def subCuenta=CuentaContable.build(clave:'4001-001',detalle:true,padre:cuenta,empresa:empresa)
    	cuenta.save()

    	then:'La subcuenta persiste correctamente'
    	def found=CuentaContable.findByClave('4001-001')
    	found.padre.id==cuenta.id

    	when:'Agregamos otra sub cuenta'
    	CuentaContable.build(clave:'4001-002',detalle:true,padre:cuenta,empresa:empresa)

    	then:'La cuenta padre tiene 2 sub cuentas'
    	CuentaContable.get(cuenta.id).subCuentas.size()==2


    }
}
