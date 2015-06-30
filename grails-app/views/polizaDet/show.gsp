
<%@ page import="com.luxsoft.lx.contabilidad.PolizaDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'polizaDet.label', default: 'PolizaDet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-polizaDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-polizaDet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list polizaDet">
			
				<g:if test="${polizaDetInstance?.asiento}">
				<li class="fieldcontain">
					<span id="asiento-label" class="property-label"><g:message code="polizaDet.asiento.label" default="Asiento" /></span>
					
						<span class="property-value" aria-labelledby="asiento-label"><g:fieldValue bean="${polizaDetInstance}" field="asiento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.concepto}">
				<li class="fieldcontain">
					<span id="concepto-label" class="property-label"><g:message code="polizaDet.concepto.label" default="Concepto" /></span>
					
						<span class="property-value" aria-labelledby="concepto-label"><g:fieldValue bean="${polizaDetInstance}" field="concepto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.origen}">
				<li class="fieldcontain">
					<span id="origen-label" class="property-label"><g:message code="polizaDet.origen.label" default="Origen" /></span>
					
						<span class="property-value" aria-labelledby="origen-label"><g:fieldValue bean="${polizaDetInstance}" field="origen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.entidad}">
				<li class="fieldcontain">
					<span id="entidad-label" class="property-label"><g:message code="polizaDet.entidad.label" default="Entidad" /></span>
					
						<span class="property-value" aria-labelledby="entidad-label"><g:fieldValue bean="${polizaDetInstance}" field="entidad"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="polizaDet.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaContable" action="show" id="${polizaDetInstance?.cuenta?.id}">${polizaDetInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.debe}">
				<li class="fieldcontain">
					<span id="debe-label" class="property-label"><g:message code="polizaDet.debe.label" default="Debe" /></span>
					
						<span class="property-value" aria-labelledby="debe-label"><g:fieldValue bean="${polizaDetInstance}" field="debe"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.haber}">
				<li class="fieldcontain">
					<span id="haber-label" class="property-label"><g:message code="polizaDet.haber.label" default="Haber" /></span>
					
						<span class="property-value" aria-labelledby="haber-label"><g:fieldValue bean="${polizaDetInstance}" field="haber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.poliza}">
				<li class="fieldcontain">
					<span id="poliza-label" class="property-label"><g:message code="polizaDet.poliza.label" default="Poliza" /></span>
					
						<span class="property-value" aria-labelledby="poliza-label"><g:link controller="poliza" action="show" id="${polizaDetInstance?.poliza?.id}">${polizaDetInstance?.poliza?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${polizaDetInstance?.referencia}">
				<li class="fieldcontain">
					<span id="referencia-label" class="property-label"><g:message code="polizaDet.referencia.label" default="Referencia" /></span>
					
						<span class="property-value" aria-labelledby="referencia-label"><g:fieldValue bean="${polizaDetInstance}" field="referencia"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:polizaDetInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${polizaDetInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
