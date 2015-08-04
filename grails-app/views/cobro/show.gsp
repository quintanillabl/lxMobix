
<%@ page import="com.luxsoft.lx.cxc.Cobro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cobro.label', default: 'Cobro')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cobro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cobro" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cobro">
			
				<g:if test="${cobroInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="cobro.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${cobroInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.referencia}">
				<li class="fieldcontain">
					<span id="referencia-label" class="property-label"><g:message code="cobro.referencia.label" default="Referencia" /></span>
					
						<span class="property-value" aria-labelledby="referencia-label"><g:fieldValue bean="${cobroInstance}" field="referencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.banco}">
				<li class="fieldcontain">
					<span id="banco-label" class="property-label"><g:message code="cobro.banco.label" default="Banco" /></span>
					
						<span class="property-value" aria-labelledby="banco-label"><g:link controller="banco" action="show" id="${cobroInstance?.banco?.id}">${cobroInstance?.banco?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="cobro.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:fieldValue bean="${cobroInstance}" field="usuario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.anticipo}">
				<li class="fieldcontain">
					<span id="anticipo-label" class="property-label"><g:message code="cobro.anticipo.label" default="Anticipo" /></span>
					
						<span class="property-value" aria-labelledby="anticipo-label"><g:formatBoolean boolean="${cobroInstance?.anticipo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.aplicaciones}">
				<li class="fieldcontain">
					<span id="aplicaciones-label" class="property-label"><g:message code="cobro.aplicaciones.label" default="Aplicaciones" /></span>
					
						<g:each in="${cobroInstance.aplicaciones}" var="a">
						<span class="property-value" aria-labelledby="aplicaciones-label"><g:link controller="aplicacionDeCobro" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="cobro.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="cliente" action="show" id="${cobroInstance?.cliente?.id}">${cobroInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="cobro.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${cobroInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="cobro.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${cobroInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.formaDePago}">
				<li class="fieldcontain">
					<span id="formaDePago-label" class="property-label"><g:message code="cobro.formaDePago.label" default="Forma De Pago" /></span>
					
						<span class="property-value" aria-labelledby="formaDePago-label"><g:fieldValue bean="${cobroInstance}" field="formaDePago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="cobro.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${cobroInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cobroInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="cobro.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${cobroInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:cobroInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cobroInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
