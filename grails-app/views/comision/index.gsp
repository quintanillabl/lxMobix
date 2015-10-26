
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
<content tag="grid">
	

	<table id="grid" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Id</th>
				<th>Fecha</th>
				<th>Cuenta</th>
				<th>Comision</th>
				<th>Tasa(IVA)</th>
				<th>Impuesto</th>
				<th>Rererencia</th>
				<th>Comentario</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${comisionInstanceList}" var="row">
				<tr>
					<lx:idTableRow id="${row.id}"/>
					<td>
						<g:link action="show" id="${row.id}">
							<lx:shortDate date="${row.fecha }"/>
						</g:link>
						
					</td>
					<td>${fieldValue(bean: row, field: "cuenta.numero")} (${fieldValue(bean: row, field: "cuenta.nombre")})</td>
					<td><lx:moneyFormat number="${row.comision}"/></td>
					<td><g:formatNumber number="${row.impuestoTasa}" format="##.##"/></td>
					<td><lx:moneyFormat number="${row.impuesto}"/></td>
					<td>${fieldValue(bean: row, field: "referenciaBancaria")} </td>
					<td>${fieldValue(bean: row, field: "comentario")} </td>
					
				</tr>
			</g:each>
		</tbody>
	</table>
</content>
	
</html>
