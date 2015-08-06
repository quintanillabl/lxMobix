<g:select class="form-control"  
	name="${property}" 
	value="${value?.id}"
	from="${com.luxsoft.lx.tesoreria.CuentaBancaria.findAllByEmpresa(session.empresa)}" 
	optionKey="id" 
	/>