<table id="grid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>numero</th>
			<th>Banco</th>
			<th>Banco(Ext)</th>
			<th>Cuenta</th>
			<th>fecha</th>
			<th>Beneficiario</th>
			<th>RFC</th>
			<th>Monto</th>
			<th>Moneda</th>
			<th>T.C.</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${polizaInstance.cheques}" var="row">
			<tr id="${row.id}">
				<td >
					<g:link  controller="poliza" action="mostrarCheque" id="${row.id}">
						${fieldValue(bean:row,field:"numero")}
					</g:link>
				</td>
				<td>${fieldValue(bean:row,field:"bancoEmisorNacional")}</td>
				<td>${fieldValue(bean:row,field:"bancoEmisorExtranjero")}</td>
				<td>${fieldValue(bean:row,field:"cuentaOrigen")}</td>
				<td>${row.fecha.text()}</td>
				<td>${fieldValue(bean:row,field:"beneficiario")}</td>
				<td>${fieldValue(bean:row,field:"rfc")}</td>
				<td>${g.formatNumber(number:row.monto.abs(),type:'currency')}</td>
				<td>${fieldValue(bean:row,field:"moneda")}</td>
				<td>${g.formatNumber(number:row.tipoDeCambio,format:'##.#####')}</td>
				
			</tr>
		</g:each>
	</tbody>
</table>