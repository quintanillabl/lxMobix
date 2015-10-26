
<%@ page import="com.luxsoft.lx.tesoreria.Inversion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="tesoreria">
		<g:set var="entityName" value="${message(code: 'comision.label', default: 'Inversión')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
<content tag="header">
	Inversiones bancarias
</content>
<content tag="grid">
	

	<table id="grid" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Id</th>
				<th>Fecha</th>
				<th>Vencimiento</th>
				<th>Cuenta Ori</th>
				<th>Cuenta Dest</th>
				<th>Importe</th>
				<th>Rendimiento</th>
				<th>ISR</th>
				<th>Comentario</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${inversionInstanceList}" var="row">
				<tr>
					<lx:idTableRow id="${row.id}"/>
					<td>
						<g:link action="show" id="${row.id}">
							<lx:shortDate date="${row.fecha }"/>
						</g:link>
						
					</td>
					<td>
						<g:link action="show" id="${row.id}">
							<lx:shortDate date="${row.vencimiento }"/>
						</g:link>
						
					</td>
					<td>
						${fieldValue(bean: row, field: "cuentaOrigen.numero")} 
						(${fieldValue(bean: row, field: "cuentaOrigen.nombre")})
					</td>
					<td>
						${fieldValue(bean: row, field: "cuentaDestino.numero")} 
						(${fieldValue(bean: row, field: "cuentaDestino.nombre")})
					</td>
					<td><lx:moneyFormat number="${row.importe}"/></td>
					<td><lx:moneyFormat number="${row.rendimientoReal}"/></td>
					<td><lx:moneyFormat number="${row.importeIsr}"/></td>
					<td>${fieldValue(bean: row, field: "comentario")} </td>
					
				</tr>
			</g:each>
		</tbody>
	</table>
</content>
	
</html>
