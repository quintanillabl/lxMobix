
<%@ page import="com.luxsoft.mobix.core.Renta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'renta.label', default: 'Renta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-renta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-renta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="comentario" title="${message(code: 'renta.comentario.label', default: 'Comentario')}" />
					
						<th><g:message code="renta.arrendamiento.label" default="Arrendamiento" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'renta.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="fin" title="${message(code: 'renta.fin.label', default: 'Fin')}" />
					
						<g:sortableColumn property="folio" title="${message(code: 'renta.folio.label', default: 'Folio')}" />
					
						<g:sortableColumn property="importe" title="${message(code: 'renta.importe.label', default: 'Importe')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rentaInstanceList}" status="i" var="rentaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rentaInstance.id}">${fieldValue(bean: rentaInstance, field: "comentario")}</g:link></td>
					
						<td>${fieldValue(bean: rentaInstance, field: "arrendamiento")}</td>
					
						<td><g:formatDate date="${rentaInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${rentaInstance.fin}" /></td>
					
						<td>${fieldValue(bean: rentaInstance, field: "folio")}</td>
					
						<td>${fieldValue(bean: rentaInstance, field: "importe")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rentaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
