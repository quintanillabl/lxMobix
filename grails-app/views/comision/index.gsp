
<%@ page import="com.luxsoft.lx.tesoreria.Comision" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="tesoreria">
		<g:set var="entityName" value="${message(code: 'comision.label', default: 'Comision')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
<content tag="header">
	Comisiones bancarias
</content>
	%{-- <body>
		<a href="#list-comision" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-comision" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="comentario" title="${message(code: 'comision.comentario.label', default: 'Comentario')}" />
					
						<g:sortableColumn property="referenciaBancaria" title="${message(code: 'comision.referenciaBancaria.label', default: 'Referencia Bancaria')}" />
					
						<g:sortableColumn property="comision" title="${message(code: 'comision.comision.label', default: 'Comision')}" />
					
						<th><g:message code="comision.cuenta.label" default="Cuenta" /></th>
					
						<th><g:message code="comision.empresa.label" default="Empresa" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'comision.fecha.label', default: 'Fecha')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${comisionInstanceList}" status="i" var="comisionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${comisionInstance.id}">${fieldValue(bean: comisionInstance, field: "comentario")}</g:link></td>
					
						<td>${fieldValue(bean: comisionInstance, field: "referenciaBancaria")}</td>
					
						<td>${fieldValue(bean: comisionInstance, field: "comision")}</td>
					
						<td>${fieldValue(bean: comisionInstance, field: "cuenta")}</td>
					
						<td>${fieldValue(bean: comisionInstance, field: "empresa")}</td>
					
						<td><g:formatDate date="${comisionInstance.fecha}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${comisionInstanceCount ?: 0}" />
			</div>
		</div>
	</body> --}%
</html>
