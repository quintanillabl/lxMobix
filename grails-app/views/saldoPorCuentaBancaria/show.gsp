
<%@ page import="com.luxsoft.lx.tesoreria.SaldoPorCuentaBancaria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'saldoPorCuentaBancaria.label', default: 'SaldoPorCuentaBancaria')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-saldoPorCuentaBancaria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-saldoPorCuentaBancaria" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list saldoPorCuentaBancaria">
			
				<g:if test="${saldoPorCuentaBancariaInstance?.ejercicio}">
				<li class="fieldcontain">
					<span id="ejercicio-label" class="property-label"><g:message code="saldoPorCuentaBancaria.ejercicio.label" default="Ejercicio" /></span>
					
						<span class="property-value" aria-labelledby="ejercicio-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="ejercicio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.mes}">
				<li class="fieldcontain">
					<span id="mes-label" class="property-label"><g:message code="saldoPorCuentaBancaria.mes.label" default="Mes" /></span>
					
						<span class="property-value" aria-labelledby="mes-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="mes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.cuenta}">
				<li class="fieldcontain">
					<span id="cuenta-label" class="property-label"><g:message code="saldoPorCuentaBancaria.cuenta.label" default="Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="cuenta-label"><g:link controller="cuentaBancaria" action="show" id="${saldoPorCuentaBancariaInstance?.cuenta?.id}">${saldoPorCuentaBancariaInstance?.cuenta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.cierre}">
				<li class="fieldcontain">
					<span id="cierre-label" class="property-label"><g:message code="saldoPorCuentaBancaria.cierre.label" default="Cierre" /></span>
					
						<span class="property-value" aria-labelledby="cierre-label"><g:formatDate date="${saldoPorCuentaBancariaInstance?.cierre}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="saldoPorCuentaBancaria.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${saldoPorCuentaBancariaInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.egresos}">
				<li class="fieldcontain">
					<span id="egresos-label" class="property-label"><g:message code="saldoPorCuentaBancaria.egresos.label" default="Egresos" /></span>
					
						<span class="property-value" aria-labelledby="egresos-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="egresos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="saldoPorCuentaBancaria.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${saldoPorCuentaBancariaInstance?.empresa?.id}">${saldoPorCuentaBancariaInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.ingresos}">
				<li class="fieldcontain">
					<span id="ingresos-label" class="property-label"><g:message code="saldoPorCuentaBancaria.ingresos.label" default="Ingresos" /></span>
					
						<span class="property-value" aria-labelledby="ingresos-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="ingresos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="saldoPorCuentaBancaria.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${saldoPorCuentaBancariaInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.saldoFinal}">
				<li class="fieldcontain">
					<span id="saldoFinal-label" class="property-label"><g:message code="saldoPorCuentaBancaria.saldoFinal.label" default="Saldo Final" /></span>
					
						<span class="property-value" aria-labelledby="saldoFinal-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="saldoFinal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${saldoPorCuentaBancariaInstance?.saldoInicial}">
				<li class="fieldcontain">
					<span id="saldoInicial-label" class="property-label"><g:message code="saldoPorCuentaBancaria.saldoInicial.label" default="Saldo Inicial" /></span>
					
						<span class="property-value" aria-labelledby="saldoInicial-label"><g:fieldValue bean="${saldoPorCuentaBancariaInstance}" field="saldoInicial"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:saldoPorCuentaBancariaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${saldoPorCuentaBancariaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
