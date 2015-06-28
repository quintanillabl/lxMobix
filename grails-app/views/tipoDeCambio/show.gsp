
<%@ page import="com.luxsoft.lx.core.TipoDeCambio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoDeCambio.label', default: 'TipoDeCambio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipoDeCambio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tipoDeCambio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipoDeCambio">
			
				<g:if test="${tipoDeCambioInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="tipoDeCambio.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${tipoDeCambioInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.monedaOrigen}">
				<li class="fieldcontain">
					<span id="monedaOrigen-label" class="property-label"><g:message code="tipoDeCambio.monedaOrigen.label" default="Moneda Origen" /></span>
					
						<span class="property-value" aria-labelledby="monedaOrigen-label"><g:fieldValue bean="${tipoDeCambioInstance}" field="monedaOrigen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.monedaFuente}">
				<li class="fieldcontain">
					<span id="monedaFuente-label" class="property-label"><g:message code="tipoDeCambio.monedaFuente.label" default="Moneda Fuente" /></span>
					
						<span class="property-value" aria-labelledby="monedaFuente-label"><g:fieldValue bean="${tipoDeCambioInstance}" field="monedaFuente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.factor}">
				<li class="fieldcontain">
					<span id="factor-label" class="property-label"><g:message code="tipoDeCambio.factor.label" default="Factor" /></span>
					
						<span class="property-value" aria-labelledby="factor-label"><g:fieldValue bean="${tipoDeCambioInstance}" field="factor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.fuente}">
				<li class="fieldcontain">
					<span id="fuente-label" class="property-label"><g:message code="tipoDeCambio.fuente.label" default="Fuente" /></span>
					
						<span class="property-value" aria-labelledby="fuente-label"><g:fieldValue bean="${tipoDeCambioInstance}" field="fuente"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="tipoDeCambio.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${tipoDeCambioInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tipoDeCambioInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="tipoDeCambio.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${tipoDeCambioInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tipoDeCambioInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tipoDeCambioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
