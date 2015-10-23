<%@ page import="com.luxsoft.lx.tesoreria.Comision" %>



<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="comision.comentario.label" default="Comentario" />
		
	</label>
	<g:textField name="comentario" maxlength="200" value="${comisionInstance?.comentario}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'referenciaBancaria', 'error')} ">
	<label for="referenciaBancaria">
		<g:message code="comision.referenciaBancaria.label" default="Referencia Bancaria" />
		
	</label>
	<g:textField name="referenciaBancaria" maxlength="100" value="${comisionInstance?.referenciaBancaria}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'comision', 'error')} required">
	<label for="comision">
		<g:message code="comision.comision.label" default="Comision" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="comision" value="${fieldValue(bean: comisionInstance, field: 'comision')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'cuenta', 'error')} required">
	<label for="cuenta">
		<g:message code="comision.cuenta.label" default="Cuenta" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuenta" name="cuenta.id" from="${com.luxsoft.lx.tesoreria.CuentaBancaria.list()}" optionKey="id" required="" value="${comisionInstance?.cuenta?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="comision.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.lx.core.Empresa.list()}" optionKey="id" required="" value="${comisionInstance?.empresa?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="comision.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${comisionInstance?.fecha}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'impuesto', 'error')} required">
	<label for="impuesto">
		<g:message code="comision.impuesto.label" default="Impuesto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuesto" value="${fieldValue(bean: comisionInstance, field: 'impuesto')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'impuestoTasa', 'error')} required">
	<label for="impuestoTasa">
		<g:message code="comision.impuestoTasa.label" default="Impuesto Tasa" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impuestoTasa" value="${fieldValue(bean: comisionInstance, field: 'impuestoTasa')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: comisionInstance, field: 'movimientos', 'error')} ">
	<label for="movimientos">
		<g:message code="comision.movimientos.label" default="Movimientos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${comisionInstance?.movimientos?}" var="m">
    <li><g:link controller="movimientoDeCuenta" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="movimientoDeCuenta" action="create" params="['comision.id': comisionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta')])}</g:link>
</li>
</ul>


</div>

