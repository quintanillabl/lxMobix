
<%@ page import="com.luxsoft.lx.tesoreria.Pago" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pago.label', default: 'Pago')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pago" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pago" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pago">
			
				<g:if test="${pagoInstance?.importe}">
				<li class="fieldcontain">
					<span id="importe-label" class="property-label"><g:message code="pago.importe.label" default="Importe" /></span>
					
						<span class="property-value" aria-labelledby="importe-label"><g:fieldValue bean="${pagoInstance}" field="importe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.referencia}">
				<li class="fieldcontain">
					<span id="referencia-label" class="property-label"><g:message code="pago.referencia.label" default="Referencia" /></span>
					
						<span class="property-value" aria-labelledby="referencia-label"><g:fieldValue bean="${pagoInstance}" field="referencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="pago.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${pagoInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.creadoPor}">
				<li class="fieldcontain">
					<span id="creadoPor-label" class="property-label"><g:message code="pago.creadoPor.label" default="Creado Por" /></span>
					
						<span class="property-value" aria-labelledby="creadoPor-label"><g:fieldValue bean="${pagoInstance}" field="creadoPor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="pago.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaBancaria" action="show" id="${pagoInstance?.cuenta?.id}">${pagoInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="pago.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${pagoInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="pago.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${pagoInstance?.empresa?.id}">${pagoInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="pago.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${pagoInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="pago.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${pagoInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.formaDePago}">
				<li class="fieldcontain">
					<span id="formaDePago-label" class="property-label"><g:message code="pago.formaDePago.label" default="Forma De Pago" /></span>
					
						<span class="property-value" aria-labelledby="formaDePago-label"><g:fieldValue bean="${pagoInstance}" field="formaDePago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="pago.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${pagoInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.modificadoPor}">
				<li class="fieldcontain">
					<span id="modificadoPor-label" class="property-label"><g:message code="pago.modificadoPor.label" default="Modificado Por" /></span>
					
						<span class="property-value" aria-labelledby="modificadoPor-label"><g:fieldValue bean="${pagoInstance}" field="modificadoPor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pagoInstance?.requisicion}">
				<li class="fieldcontain">
					<span id="requisicion-label" class="property-label"><g:message code="pago.requisicion.label" default="Requisicion" /></span>
					
						<span class="property-value" aria-labelledby="requisicion-label"><g:link controller="requisicion" action="show" id="${pagoInstance?.requisicion?.id}">${pagoInstance?.requisicion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:pagoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${pagoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
