<%@ page import="com.luxsoft.mobix.core.Renta" %>



<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'comentario', 'error')} ">
	<label for="comentario">
		<g:message code="renta.comentario.label" default="Comentario" />
		
	</label>
	<g:textField name="comentario" value="${rentaInstance?.comentario}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'arrendamiento', 'error')} required">
	<label for="arrendamiento">
		<g:message code="renta.arrendamiento.label" default="Arrendamiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="arrendamiento" name="arrendamiento.id" from="${com.luxsoft.mobix.core.Arrendamiento.list()}" optionKey="id" required="" value="${rentaInstance?.arrendamiento?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'fin', 'error')} required">
	<label for="fin">
		<g:message code="renta.fin.label" default="Fin" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fin" precision="day"  value="${rentaInstance?.fin}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'folio', 'error')} required">
	<label for="folio">
		<g:message code="renta.folio.label" default="Folio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folio" type="number" value="${rentaInstance.folio}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'importe', 'error')} required">
	<label for="importe">
		<g:message code="renta.importe.label" default="Importe" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importe" value="${fieldValue(bean: rentaInstance, field: 'importe')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: rentaInstance, field: 'inicio', 'error')} required">
	<label for="inicio">
		<g:message code="renta.inicio.label" default="Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inicio" precision="day"  value="${rentaInstance?.inicio}"  />

</div>

