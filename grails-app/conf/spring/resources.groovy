
import org.springframework.web.servlet.i18n.FixedLocaleResolver
import com.luxsoft.cfdi.CfdiCadenaBuilder
import com.luxsoft.cfdi.CfdiSellador
import com.luxsoft.cfdi.CfdiTimbrador
import grails.util.Environment
import com.luxsoft.lx.contabilidad.ContaListener

// Place your Spring DSL code here
beans = {
	switch(Environment.current){
		/*
		case Environment.PRODUCTION:
			cfdiTimbrador(CfdiTimbrador){
				timbradoDePrueba=false
			}
			break
		case Environment.DEVELOPMENT:
			cfdiTimbrador(CfdiTimbrador){
				timbradoDePrueba=true
			}
			break
		case Environment.TEST:
			cfdiTimbrador(CfdiTimbrador){
			timbradoDePrueba=true
		}
		*/
	}
	
	cfdiCadenaBuilder(CfdiCadenaBuilder){
		
	}

	cfdiSellador(CfdiSellador){
		cadenaBuilder=ref("cfdiCadenaBuilder")
	}
	cfdiTimbrador(CfdiTimbrador){}
	
	localeResolver(FixedLocaleResolver,Locale.US)

	contaListener(ContaListener){}
}
