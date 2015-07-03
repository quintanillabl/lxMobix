<table id="grid" class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<th>Clave</th>
			<th>S.Inicial</th>
			<th>Debe</th>
			<th>Haber</th>
			<th>S.Final</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${saldos?.sort{it.clave} }" var="row">
			<tr id="${row.id}">
				<td >
					<g:link  action="show" id="${row.id}">
						${fieldValue(bean:row,field:"cuenta")}
					</g:link>
				</td>
				<td>${formatNumber(number:row.saldoInicial,type:'currency')}</td>
				<td>${formatNumber(number:row.debe,type:'currency')}</td>
				<td>${formatNumber(number:row.haber,type:'currency')}</td>
				<td>${formatNumber(number:row.saldoFinal,type:'currency')}</td>
				
			</tr>
		</g:each>
	</tbody>
</table>