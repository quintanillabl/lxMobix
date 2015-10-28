<table id="grid" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>Ejercicio</th>
					<th>Mes</th>
					<th>Depreciación</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${activoFijoInstance.depreciaciones.sort()}" var="row">
					<tr>
						<td>${formatNumber(bean:row,number:row.ejercicio,format:'####')}</td>
						<td>${formatNumber(bean:row,number:row.mes,format:'##')}</td>
						<td><lx:moneyFormat number="${row.depreciacion}"/></td>
						
					</tr>
				</g:each>
			</tbody>
</table>




