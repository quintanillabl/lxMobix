<%@ page import="com.luxsoft.lx.core.Configuracion" %>



<div class="fieldcontain ${hasErrors(bean: configuracionInstance, field: 'empresa', 'error')} required">
	<label for="empresa">
		<g:message code="configuracion.empresa.label" default="Empresa" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="empresa" name="empresa.id" from="${com.luxsoft.lx.core.Empresa.list()}" optionKey="id" required="" value="${configuracionInstance?.empresa?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: configuracionInstance, field: 'atributos', 'error')} ">
	<label for="atributos">
		<g:message code="configuracion.atributos.label" default="Atributos" />
		
	</label>
	

</div>

