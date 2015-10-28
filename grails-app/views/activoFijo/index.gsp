
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="operaciones">
		<g:set var="entityName" value="${message(code: 'comision.label', default: 'Activo fijo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
<content tag="header">
	Activo Fijo 
</content>
<content tag="grid">

	<table id="grid" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Id</th>
				<th>Descripcion</th>
				<th>Factura</th>
				<th>Adquisicion</th>
				<th>Valor Original</th>
				<th>Dep (Tasa)</th>
				<th>Cuenta</th>
				%{-- <th>Depreciacion (Acu)</th>
				<th>Pendiente</th> --}%
			</tr>
		</thead>
		<tbody>
			<g:each in="${activoFijoInstanceList}" var="row">
				<tr>
					<lx:idTableRow id="${row.id}"/>
					<td>${fieldValue(bean: row, field: "descripcion")} </td>
					<td>${fieldValue(bean: row, field: "factura")} </td>
					<td><lx:shortDate date="${row.adquisicion}"/></td>
					<td><lx:moneyFormat number="${row.valorOriginal}"/></td>
					<td>${formatNumber(bean:row,number:row.depreciacionTasa,type:'percent')}</td>
					<td>${fieldValue(bean: row, field: "cuentaContable.clave")} </td>
					%{-- <td><lx:moneyFormat number="${row.depreciacinAcumulada}"/></td>
					<td><lx:moneyFormat number="${row.pendiente}"/></td> --}%
				</tr>
			</g:each>
		</tbody>
	</table>
</content>
	
</html>
