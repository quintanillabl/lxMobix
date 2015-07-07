
<%@ page import="com.luxsoft.lx.tesoreria.MovimientoDeCuenta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-movimientoDeCuenta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-movimientoDeCuenta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list movimientoDeCuenta">
			
				<g:if test="${movimientoDeCuentaInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="movimientoDeCuenta.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.referencia}">
				<li class="fieldcontain">
					<span id="referencia-label" class="property-label"><g:message code="movimientoDeCuenta.referencia.label" default="Referencia" /></span>
					
						<span class="property-value" aria-labelledby="referencia-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="referencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="movimientoDeCuenta.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.creadoPor}">
				<li class="fieldcontain">
					<span id="creadoPor-label" class="property-label"><g:message code="movimientoDeCuenta.creadoPor.label" default="Creado Por" /></span>
					
						<span class="property-value" aria-labelledby="creadoPor-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="creadoPor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="movimientoDeCuenta.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaBancaria" action="show" id="${movimientoDeCuentaInstance?.cuenta?.id}">${movimientoDeCuentaInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="movimientoDeCuenta.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${movimientoDeCuentaInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="movimientoDeCuenta.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${movimientoDeCuentaInstance?.empresa?.id}">${movimientoDeCuentaInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="movimientoDeCuenta.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${movimientoDeCuentaInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="movimientoDeCuenta.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="movimientoDeCuenta.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${movimientoDeCuentaInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${movimientoDeCuentaInstance?.modificadoPor}">
				<li class="fieldcontain">
					<span id="modificadoPor-label" class="property-label"><g:message code="movimientoDeCuenta.modificadoPor.label" default="Modificado Por" /></span>
					
						<span class="property-value" aria-labelledby="modificadoPor-label"><g:fieldValue bean="${movimientoDeCuentaInstance}" field="modificadoPor"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:movimientoDeCuentaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${movimientoDeCuentaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
