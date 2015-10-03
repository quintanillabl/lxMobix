import com.luxsoft.sec.*
import org.bouncycastle.jce.provider.BouncyCastleProvider


class BootStrap {

	def cfdiRetencionesService

    def init = { servletContext ->

    	java.security.Security.addProvider(new BouncyCastleProvider())

    	
		def userRole=Rol.findOrSaveWhere(authority:'USUARIO')
		def mostradorRole=Rol.findOrSaveWhere(authority:'OPERADOR')
		def administracionRole=Rol.findOrSaveWhere(authority:'ADMINISTRACION')
		def adminRole=Rol.findOrSaveWhere(authority:'ADMIN')
		def ventasRole=Rol.findOrSaveWhere(authority:'VENTAS')
		def tesoreriaRole=Rol.findOrSaveWhere(authority:'TESORERIA')
		def gastosRole=Rol.findOrSaveWhere(authority:'GASTOS')


		def admin=Usuario.findByUsername('admin')
		if(!admin){
			admin=new Usuario(username:'admin'
				,password:'admin'
				,apellidoPaterno:'admin'
				,apellidoMaterno:'admin'
				,nombres:'admin'
				,nombre:' ADMIN ADMIN'
				,numeroDeEmpleado:'0000')
			.save(flush:true,failOnError:true)
			UsuarioRol.create(admin,userRole,true)
			UsuarioRol.create(admin,adminRole,true)
		}

		def contaRol=Rol.findOrSaveWhere(authority:'CONTABILIDAD')
		if(!admin.getAuthorities().contains(contaRol))
			UsuarioRol.create(admin,contaRol,true)	

		def contaManagerRol=Rol.findOrSaveWhere(authority:'CONTABILIDAD_MANAGER')
		

		cfdiRetencionesService.buildCatalogoDeRetenciones()
		cfdiRetencionesService.buildCatalogoDeImpuestos()
		
		
		

    }
    def destroy = {
    }
}
