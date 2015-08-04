<g:select class="form-control"  
	name="${property}" 
	value="${value?.id}"
	from="${com.luxsoft.lx.tesoreria.Banco.findAllByEmpresa(session.empresa)}" 
	optionKey="id" 
	optionValue="nombre"
	noSelection="[null:'Seleccione un banco']"
	/>