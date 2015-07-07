<%@ page import="com.luxsoft.lx.tesoreria.MovimientoDeCuenta" %>



<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="movimientoDeCuenta.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importe" value="${fieldValue(bean: movimientoDeCuentaInstance, field: 'importe')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'referencia', 'error')} ">
	<label for="referencia">
		<g:message code="movimientoDeCuenta.referencia.label" default="Referencia" />
		
	</label>
	<g:textField name="referencia" value="${movimientoDeCuentaInstance?.referencia}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="movimientoDeCuenta.comentario.label" default="Comentario" />
		
	</label>
	<g:textField name="comentario" value="${movimientoDeCuentaInstance?.comentario}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'creadoPor', 'error')} required">
	<label for="creadoPor">
		<g:message code="movimientoDeCuenta.creadoPor.label" default="Creado Por" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="creadoPor" required="" value="${movimientoDeCuentaInstance?.creadoPor}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'cuenta', 'error')} required">
	<label for="cuenta">
		<g:message code="movimientoDeCuenta.cuenta.label" default="Cuenta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuenta" name="cuenta.id" from="${com.luxsoft.lx.tesoreria.CuentaBancaria.list()}" optionKey="id" required="" value="${movimientoDeCuentaInstance?.cuenta?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="movimientoDeCuenta.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.lx.core.Empresa.list()}" optionKey="id" required="" value="${movimientoDeCuentaInstance?.empresa?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="movimientoDeCuenta.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${movimientoDeCuentaInstance?.fecha}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="movimientoDeCuenta.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folio" type="number" value="${movimientoDeCuentaInstance.folio}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: movimientoDeCuentaInstance, field: 'modificadoPor', 'error')} required">
	<label for="modificadoPor">
		<g:message code="movimientoDeCuenta.modificadoPor.label" default="Modificado Por" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="modificadoPor" required="" value="${movimientoDeCuentaInstance?.modificadoPor}"/>

</div>

