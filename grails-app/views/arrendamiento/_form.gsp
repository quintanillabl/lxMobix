<%@ page import="com.luxsoft.mobix.core.Arrendamiento" %>



<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="arrendamiento.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipo" from="${arrendamientoInstance.constraints.tipo.inList}" required="" value="${arrendamientoInstance?.tipo}" valueMessagePrefix="arrendamiento.tipo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'diaDeCorte', 'error')} required">
	<label for="diaDeCorte">
		<g:message code="arrendamiento.diaDeCorte.label" default="Dia De Corte" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="diaDeCorte" from="${1..15}" class="range" required="" value="${fieldValue(bean: arrendamientoInstance, field: 'diaDeCorte')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="arrendamiento.comentario.label" default="Comentario" />
		
	</label>
	<g:textArea name="comentario" cols="40" rows="5" maxlength="300" value="${arrendamientoInstance?.comentario}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="arrendamiento.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${com.luxsoft.lx.core.Cliente.list()}" optionKey="id" required="" value="${arrendamientoInstance?.cliente?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="arrendamiento.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.lx.core.Empresa.list()}" optionKey="id" required="" value="${arrendamientoInstance?.empresa?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'envioAutomatio', 'error')} ">
	<label for="envioAutomatio">
		<g:message code="arrendamiento.envioAutomatio.label" default="Envio Automatio" />
		
	</label>
	<g:checkBox name="envioAutomatio" value="${arrendamientoInstance?.envioAutomatio}" />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'facturacionAutomatica', 'error')} ">
	<label for="facturacionAutomatica">
		<g:message code="arrendamiento.facturacionAutomatica.label" default="Facturacion Automatica" />
		
	</label>
	<g:checkBox name="facturacionAutomatica" value="${arrendamientoInstance?.facturacionAutomatica}" />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'fin', 'error')} required">
	<label for="fin">
		<g:message code="arrendamiento.fin.label" default="Fin" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fin" precision="day"  value="${arrendamientoInstance?.fin}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'inicio', 'error')} required">
	<label for="inicio">
		<g:message code="arrendamiento.inicio.label" default="Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inicio" precision="day"  value="${arrendamientoInstance?.inicio}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'inmueble', 'error')} required">
	<label for="inmueble">
		<g:message code="arrendamiento.inmueble.label" default="Inmueble" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inmueble" name="inmueble.id" from="${com.luxsoft.mobix.core.Inmueble.list()}" optionKey="id" required="" value="${arrendamientoInstance?.inmueble?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'precio', 'error')} required">
	<label for="precio">
		<g:message code="arrendamiento.precio.label" default="Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precio" value="${fieldValue(bean: arrendamientoInstance, field: 'precio')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="arrendamiento.producto.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="producto.id" from="${com.luxsoft.lx.core.Producto.list()}" optionKey="id" required="" value="${arrendamientoInstance?.producto?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'proximaFactura', 'error')} required">
	<label for="proximaFactura">
		<g:message code="arrendamiento.proximaFactura.label" default="Proxima Factura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="proximaFactura" precision="day"  value="${arrendamientoInstance?.proximaFactura}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'renovacionAutomatica', 'error')} ">
	<label for="renovacionAutomatica">
		<g:message code="arrendamiento.renovacionAutomatica.label" default="Renovacion Automatica" />
		
	</label>
	<g:checkBox name="renovacionAutomatica" value="${arrendamientoInstance?.renovacionAutomatica}" />

</div>

<div class="fieldcontain ${hasErrors(bean: arrendamientoInstance, field: 'rentas', 'error')} ">
	<label for="rentas">
		<g:message code="arrendamiento.rentas.label" default="Rentas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${arrendamientoInstance?.rentas?}" var="r">
    <li><g:link controller="renta" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="renta" action="create" params="['arrendamiento.id': arrendamientoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'renta.label', default: 'Renta')])}</g:link>
</li>
</ul>


</div>

