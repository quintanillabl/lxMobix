
<%@ page import="com.luxsoft.lx.contabilidad.PolizaDet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'polizaDet.label', default: 'PolizaDet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-polizaDet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-polizaDet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="asiento" title="${message(code: 'polizaDet.asiento.label', default: 'Asiento')}" />
					
						<g:sortableColumn property="concepto" title="${message(code: 'polizaDet.concepto.label', default: 'Concepto')}" />
					
						<g:sortableColumn property="origen" title="${message(code: 'polizaDet.origen.label', default: 'Origen')}" />
					
						<g:sortableColumn property="entidad" title="${message(code: 'polizaDet.entidad.label', default: 'Entidad')}" />
					
						<th><g:message code="polizaDet.cuenta.label" default="Cuenta" /></th>
					
						<g:sortableColumn property="debe" title="${message(code: 'polizaDet.debe.label', default: 'Debe')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${polizaDetInstanceList}" status="i" var="polizaDetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${polizaDetInstance.id}">${fieldValue(bean: polizaDetInstance, field: "asiento")}</g:link></td>
					
						<td>${fieldValue(bean: polizaDetInstance, field: "concepto")}</td>
					
						<td>${fieldValue(bean: polizaDetInstance, field: "origen")}</td>
					
						<td>${fieldValue(bean: polizaDetInstance, field: "entidad")}</td>
					
						<td>${fieldValue(bean: polizaDetInstance, field: "cuenta")}</td>
					
						<td>${fieldValue(bean: polizaDetInstance, field: "debe")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${polizaDetInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
