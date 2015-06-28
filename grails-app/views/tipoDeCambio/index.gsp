
<%@ page import="com.luxsoft.lx.core.TipoDeCambio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipoDeCambio.label', default: 'TipoDeCambio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tipoDeCambio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tipoDeCambio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="fecha" title="${message(code: 'tipoDeCambio.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="monedaOrigen" title="${message(code: 'tipoDeCambio.monedaOrigen.label', default: 'Moneda Origen')}" />
					
						<g:sortableColumn property="monedaFuente" title="${message(code: 'tipoDeCambio.monedaFuente.label', default: 'Moneda Fuente')}" />
					
						<g:sortableColumn property="factor" title="${message(code: 'tipoDeCambio.factor.label', default: 'Factor')}" />
					
						<g:sortableColumn property="fuente" title="${message(code: 'tipoDeCambio.fuente.label', default: 'Fuente')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'tipoDeCambio.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoDeCambioInstanceList}" status="i" var="tipoDeCambioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoDeCambioInstance.id}">${fieldValue(bean: tipoDeCambioInstance, field: "fecha")}</g:link></td>
					
						<td>${fieldValue(bean: tipoDeCambioInstance, field: "monedaOrigen")}</td>
					
						<td>${fieldValue(bean: tipoDeCambioInstance, field: "monedaFuente")}</td>
					
						<td>${fieldValue(bean: tipoDeCambioInstance, field: "factor")}</td>
					
						<td>${fieldValue(bean: tipoDeCambioInstance, field: "fuente")}</td>
					
						<td><g:formatDate date="${tipoDeCambioInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoDeCambioInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
