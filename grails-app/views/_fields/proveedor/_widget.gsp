<%@page expressionCodec="none" %>

<g:select class="form-control"  
	name="${property}" 
	value="${value?.id}"
	from="${com.luxsoft.lx.core.Proveedor.findAllByEmpresa(session.empresa)}" 
	optionKey="id" 
	optionValue="nombre"
	noSelection="[null:'Seleccione un proveedor']"
	/>