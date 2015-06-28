<%@ page import="com.luxsoft.lx.core.Producto" %>



<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="producto.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clave" maxlength="40" required="" value="${productoInstance?.clave}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${productoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'unidad', 'error')} required">
	<label for="unidad">
		<g:message code="producto.unidad.label" default="Unidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="unidad" maxlength="15" required="" value="${productoInstance?.unidad}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descuento', 'error')} required">
	<label for="descuento">
		<g:message code="producto.descuento.label" default="Descuento" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descuento" value="${fieldValue(bean: productoInstance, field: 'descuento')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'impuesto', 'error')} required">
	<label for="impuesto">
		<g:message code="producto.impuesto.label" default="Impuesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuesto" value="${fieldValue(bean: productoInstance, field: 'impuesto')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'inventariable', 'error')} ">
	<label for="inventariable">
		<g:message code="producto.inventariable.label" default="Inventariable" />
		
	</label>
	<g:checkBox name="inventariable" value="${productoInstance?.inventariable}" />

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precio', 'error')} required">
	<label for="precio">
		<g:message code="producto.precio.label" default="Precio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precio" value="${fieldValue(bean: productoInstance, field: 'precio')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precioNeto', 'error')} required">
	<label for="precioNeto">
		<g:message code="producto.precioNeto.label" default="Precio Neto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precioNeto" value="${fieldValue(bean: productoInstance, field: 'precioNeto')}" required=""/>

</div>

