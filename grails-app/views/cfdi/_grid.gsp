<asset:stylesheet src="datatables/dataTables.css"/>
<asset:javascript src="datatables/dataTables.js"/> 
<table class="table table-striped table-bordered table-condensed" id="grid">
	<thead>
		<tr>
			<g:sortableColumn property="id" title="Id"/>
			<th>Serie</th>
			<th>Folio</th>
			<th>Fecha</th>
			<th>Receptor</th>
			<th>UUID</th>			
			<th>Total</th>
			<th>Ver</th>
			<th>Comentario</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${cfdiInstanceList}" var="row">
			<tr class="${row.cancelacion?'danger':''}">
				<td>
					<g:link action="show" id="${row.id}">
						${fieldValue(bean:row,field:"id")}
					</g:link>
				</td>
				<td>${fieldValue(bean:row,field:"serie")}</td>
				<td>${fieldValue(bean:row,field:"folio")}</td>
				<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
				<td>
					<abbr title="${row.receptor}">
					${org.apache.commons.lang.StringUtils.substring(row.receptor,0,50)}
					</abbr>
				</td>
				<td>
					<abbr title="${row.uuid}">
					${org.apache.commons.lang.StringUtils.substringAfterLast(row.uuid,'-')}
					</abbr>
				</td>
				<td><g:formatNumber number="${row.total}" type="currency"/></td>
				<td>${fieldValue(bean:row,field:"versionCfdi")}</td>
				<td>
					<g:if test="${row.cancelacion}">CANCELADO</g:if>
					<g:else>${row.comentario}</g:else>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>
<div class="pagination">
	<g:paginate total="${cfdiInstanceCount ?: 0}"/>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#grid').dataTable( {
        	"paging":   false,
        	"ordering": false,
        	"info":     false
        	,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
    	} );
    	
    	$("#filtro").on('keyup',function(e){
    		var term=$(this).val();
    		$('#grid').DataTable().search(
				$(this).val()
    		        
    		).draw();
    	});

	});
</script>