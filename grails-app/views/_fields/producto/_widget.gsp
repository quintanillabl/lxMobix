<%@page expressionCodec="none" %>

<g:select class="form-control"  
	name="${property}" 
	value="${value?.id}"
	from="${com.luxsoft.lx.core.Producto.findAll(
    "from Producto i where i.empresa=? "
    ,[session.empresa])}" 
	optionKey="id" 
	noSelection="[null:'Seleccione un inmueble']"
	/>

	