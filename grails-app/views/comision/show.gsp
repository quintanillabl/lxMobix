
<%@ page import="com.luxsoft.lx.tesoreria.Comision" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comision.label', default: 'Comision')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-comision" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-comision" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list comision">
			
				<g:if test="${comisionInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="comision.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${comisionInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.referenciaBancaria}">
				<li class="fieldcontain">
					<span id="referenciaBancaria-label" class="property-label"><g:message code="comision.referenciaBancaria.label" default="Referencia Bancaria" /></span>
					
						<span class="property-value" aria-labelledby="referenciaBancaria-label"><g:fieldValue bean="${comisionInstance}" field="referenciaBancaria"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.comision}">
				<li class="fieldcontain">
					<span id="comision-label" class="property-label"><g:message code="comision.comision.label" default="Comision" /></span>
					
						<span class="property-value" aria-labelledby="comision-label"><g:fieldValue bean="${comisionInstance}" field="comision"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="comision.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaBancaria" action="show" id="${comisionInstance?.cuenta?.id}">${comisionInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="comision.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${comisionInstance?.empresa?.id}">${comisionInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="comision.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${comisionInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.impuesto}">
				<li class="fieldcontain">
					<span id="impuesto-label" class="property-label"><g:message code="comision.impuesto.label" default="Impuesto" /></span>
					
						<span class="property-value" aria-labelledby="impuesto-label"><g:fieldValue bean="${comisionInstance}" field="impuesto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.impuestoTasa}">
				<li class="fieldcontain">
					<span id="impuestoTasa-label" class="property-label"><g:message code="comision.impuestoTasa.label" default="Impuesto Tasa" /></span>
					
						<span class="property-value" aria-labelledby="impuestoTasa-label"><g:fieldValue bean="${comisionInstance}" field="impuestoTasa"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${comisionInstance?.movimientos}">
				<li class="fieldcontain">
					<span id="movimientos-label" class="property-label"><g:message code="comision.movimientos.label" default="Movimientos" /></span>
					
						<g:each in="${comisionInstance.movimientos}" var="m">
						<span class="property-value" aria-labelledby="movimientos-label"><g:link controller="movimientoDeCuenta" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:comisionInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${comisionInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
