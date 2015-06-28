<%@page expressionCodec="none" %>

<g:select class="form-control"  
	name="${property}" 
	value="${value?.id}"
	from="${com.luxsoft.mobix.core.Inmueble.findAll(
    "from Inmueble i where i.empresa=? and i.id not in(select a.inmueble from Arrendamiento a)"
    ,[session.empresa])}" 
	optionKey="id" 
	optionValue="clave"
	noSelection="[null:'Seleccione un inmueble']"
	/>