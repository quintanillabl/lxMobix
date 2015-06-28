<%@ page import="com.luxsoft.lx.core.Proveedor" %>



<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="proveedor.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="nombre" cols="40" rows="5" maxlength="255" required="" value="${proveedorInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="proveedor.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="13" required="" value="${proveedorInstance?.rfc}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'correoElectronico', 'error')} ">
	<label for="correoElectronico">
		<g:message code="proveedor.correoElectronico.label" default="Correo Electronico" />
		
	</label>
	<g:field type="email" name="correoElectronico" value="${proveedorInstance?.correoElectronico}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'www', 'error')} ">
	<label for="www">
		<g:message code="proveedor.www.label" default="Www" />
		
	</label>
	<g:field type="url" name="www" value="${proveedorInstance?.www}"/>

</div>
<fieldset class="embedded"><legend><g:message code="proveedor.direccion.label" default="Direccion" /></legend>
<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.calle', 'error')} ">
	<label for="direccion.calle">
		<g:message code="proveedor.direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" maxlength="200" value="${direccionInstance?.calle}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.codigoPostal', 'error')} ">
	<label for="direccion.codigoPostal">
		<g:message code="proveedor.direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.colonia', 'error')} ">
	<label for="direccion.colonia">
		<g:message code="proveedor.direccion.colonia.label" default="Colonia" />
		
	</label>
	<g:textField name="colonia" value="${direccionInstance?.colonia}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.estado', 'error')} ">
	<label for="direccion.estado">
		<g:message code="proveedor.direccion.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${direccionInstance?.estado}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.municipio', 'error')} ">
	<label for="direccion.municipio">
		<g:message code="proveedor.direccion.municipio.label" default="Municipio" />
		
	</label>
	<g:textField name="municipio" value="${direccionInstance?.municipio}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.numeroExterior', 'error')} ">
	<label for="direccion.numeroExterior">
		<g:message code="proveedor.direccion.numeroExterior.label" default="Numero Exterior" />
		
	</label>
	<g:textField name="numeroExterior" maxlength="50" value="${direccionInstance?.numeroExterior}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.numeroInterior', 'error')} ">
	<label for="direccion.numeroInterior">
		<g:message code="proveedor.direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'direccion.pais', 'error')} ">
	<label for="direccion.pais">
		<g:message code="proveedor.direccion.pais.label" default="Pais" />
		
	</label>
	<g:textField name="pais" maxlength="100" value="${direccionInstance?.pais}"/>

</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'cuentaContable', 'error')} required">
	<label for="cuentaContable">
		<g:message code="proveedor.cuentaContable.label" default="Cuenta Contable" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuentaContable" name="cuentaContable.id" from="${com.luxsoft.lx.contabilidad.CuentaContable.list()}" optionKey="id" required="" value="${proveedorInstance?.cuentaContable?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: proveedorInstance, field: 'nacional', 'error')} ">
	<label for="nacional">
		<g:message code="proveedor.nacional.label" default="Nacional" />
		
	</label>
	<g:checkBox name="nacional" value="${proveedorInstance?.nacional}" />

</div>

