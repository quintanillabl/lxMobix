<%@ page import="com.luxsoft.lx.tesoreria.CuentaBancaria" %>



<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="cuentaBancaria.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" maxlength="50" required="" value="${cuentaBancariaInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="cuentaBancaria.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="100" required="" value="${cuentaBancariaInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="cuentaBancaria.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipo" from="${cuentaBancariaInstance.constraints.tipo.inList}" required="" value="${cuentaBancariaInstance?.tipo}" valueMessagePrefix="cuentaBancaria.tipo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'activo', 'error')} ">
	<label for="activo">
		<g:message code="cuentaBancaria.activo.label" default="Activo" />
		
	</label>
	<g:checkBox name="activo" value="${cuentaBancariaInstance?.activo}" />

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'banco', 'error')} required">
	<label for="banco">
		<g:message code="cuentaBancaria.banco.label" default="Banco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="banco" name="banco.id" from="${com.luxsoft.lx.tesoreria.Banco.list()}" optionKey="id" required="" value="${cuentaBancariaInstance?.banco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'cuentaContable', 'error')} required">
	<label for="cuentaContable">
		<g:message code="cuentaBancaria.cuentaContable.label" default="Cuenta Contable" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cuentaContable" name="cuentaContable.id" from="${com.luxsoft.lx.contabilidad.CuentaContable.list()}" optionKey="id" required="" value="${cuentaBancariaInstance?.cuentaContable?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'diasInversionIsr', 'error')} required">
	<label for="diasInversionIsr">
		<g:message code="cuentaBancaria.diasInversionIsr.label" default="Dias Inversion Isr" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="diasInversionIsr" type="number" value="${cuentaBancariaInstance.diasInversionIsr}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="cuentaBancaria.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.lx.core.Empresa.list()}" optionKey="id" required="" value="${cuentaBancariaInstance?.empresa?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="cuentaBancaria.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folio" type="number" value="${cuentaBancariaInstance.folio}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'folioFinal', 'error')} required">
	<label for="folioFinal">
		<g:message code="cuentaBancaria.folioFinal.label" default="Folio Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folioFinal" type="number" value="${cuentaBancariaInstance.folioFinal}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'folioInicial', 'error')} required">
	<label for="folioInicial">
		<g:message code="cuentaBancaria.folioInicial.label" default="Folio Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folioInicial" type="number" value="${cuentaBancariaInstance.folioInicial}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'moneda', 'error')} required">
	<label for="moneda">
		<g:message code="cuentaBancaria.moneda.label" default="Moneda" />
		<span class="required-indicator">*</span>
	</label>
	<g:currencySelect name="moneda" value="${cuentaBancariaInstance?.moneda}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'plazo', 'error')} required">
	<label for="plazo">
		<g:message code="cuentaBancaria.plazo.label" default="Plazo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="plazo" type="number" value="${cuentaBancariaInstance.plazo}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'tasaDeInversion', 'error')} required">
	<label for="tasaDeInversion">
		<g:message code="cuentaBancaria.tasaDeInversion.label" default="Tasa De Inversion" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tasaDeInversion" value="${fieldValue(bean: cuentaBancariaInstance, field: 'tasaDeInversion')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: cuentaBancariaInstance, field: 'tasaIsr', 'error')} required">
	<label for="tasaIsr">
		<g:message code="cuentaBancaria.tasaIsr.label" default="Tasa Isr" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tasaIsr" value="${fieldValue(bean: cuentaBancariaInstance, field: 'tasaIsr')}" required=""/>

</div>

