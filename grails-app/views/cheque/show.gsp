
<%@ page import="com.luxsoft.lx.tesoreria.Cheque" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cheque.label', default: 'Cheque')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cheque" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cheque" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cheque">
			
				<g:if test="${chequeInstance?.fechaImpresion}">
				<li class="fieldcontain">
					<span id="fechaImpresion-label" class="property-label"><g:message code="cheque.fechaImpresion.label" default="Fecha Impresion" /></span>
					
						<span class="property-value" aria-labelledby="fechaImpresion-label"><g:formatDate date="${chequeInstance?.fechaImpresion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${chequeInstance?.cancelacion}">
				<li class="fieldcontain">
					<span id="cancelacion-label" class="property-label"><g:message code="cheque.cancelacion.label" default="Cancelacion" /></span>
					
						<span class="property-value" aria-labelledby="cancelacion-label"><g:formatDate date="${chequeInstance?.cancelacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${chequeInstance?.comentarioCancelacion}">
				<li class="fieldcontain">
					<span id="comentarioCancelacion-label" class="property-label"><g:message code="cheque.comentarioCancelacion.label" default="Comentario Cancelacion" /></span>
					
						<span class="property-value" aria-labelledby="comentarioCancelacion-label"><g:fieldValue bean="${chequeInstance}" field="comentarioCancelacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${chequeInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="cheque.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaBancaria" action="show" id="${chequeInstance?.cuenta?.id}">${chequeInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${chequeInstance?.egreso}">
				<li class="fieldcontain">
					<span id="egreso-label" class="property-label"><g:message code="cheque.egreso.label" default="Egreso" /></span>
					
						<span class="property-value" aria-labelledby="egreso-label"><g:link controller="movimientoDeCuenta" action="show" id="${chequeInstance?.egreso?.id}">${chequeInstance?.egreso?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${chequeInstance?.folio}">
				<li class="fieldcontain">
					<span id="folio-label" class="property-label"><g:message code="cheque.folio.label" default="Folio" /></span>
					
						<span class="property-value" aria-labelledby="folio-label"><g:fieldValue bean="${chequeInstance}" field="folio"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:chequeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${chequeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
