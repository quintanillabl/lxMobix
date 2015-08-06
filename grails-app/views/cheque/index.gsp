<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Control de cheques</title>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Control de cheques  (<small>${session.empresa.nombre}</small>)</p>
					</h4>
					<g:if test="${flash.message}">
						<span class="label label-warning">${flash.message}</span>
					</g:if> 
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row toolbar-panel">
		    <div class="col-md-4">
		    	<input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
		      </div>

		    <div class="btn-group">
		        <lx:refreshButton/>
		        <lx:createButton/>
		    </div>
		    <div class="btn-group">
		        <button type="button" name="reportes"
		                class="btn btn-default dropdown-toggle" data-toggle="dropdown"
		                role="menu">
		                Reportes <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu">
		        	
		        </ul>
		    </div>
		    
		    
		</div>

		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">

					<thead>
						<tr>
							<th>Id</th>
							<th>Cuenta</th>
							<th>Desc</th>
							<th>Pago</th>
							<th>Fecha</th>
							<th>Folio</th>
							<th>Importe</th>
							<th>Impreso</th>
							<th>Cancelación</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${chequeInstanceList}"
							var="row">
							<tr>
								<td>
									<g:link action="show"
										id="${row.id}" >
										${row.id}
									</g:link>
								</td>
								<td>
									${fieldValue(bean: row, field: "cuenta.numero")}
								</td>
								<td>${fieldValue(bean: row, field: "cuenta.nombre")}</td>
								<td>${fieldValue(bean: row, field: "egreso.id")}</td>
								<td><lx:shortDate date="${row.egreso.fecha }"/></td>
								<td><g:formatNumber number="${row.folio}" format="####"/></td>
								<td><lx:moneyFormat number="${row.egreso.importe.abs()}"/></td>
								<td><lx:shortDate date="${row.fechaImpresion}"/></td>
								<td><g:if test="${!row.cancelacion }">
										<g:link action="cancelar" id="${row.id}" ><i class="icon-remove-sign"></i> Cancelar</g:link>
									</g:if>
									<g:else>
										<lx:shortDate date="${row.cancelacion}"/>	
									</g:else>
								</td>				
								
								
								
							</tr>
						</g:each>
					</tbody>
				</table>
				
				
			</div>
		</div> <!-- end .row 2 -->
		
	</div>

	<script type="text/javascript">
		$(function(){
			
 			$('#grid').dataTable({
                responsive: true,
                "language": {
					"url": "${assetPath(src: 'plugins/dataTables/dataTables.spanish.txt')}"
	    		},
	    		"dom": 'T<"clear">lfrtip',
	    		"tableTools": {
	    		    "sSwfPath": "${assetPath(src: 'plugins/dataTables/swf/copy_csv_xls_pdf.swf')}"
	    		},
	    		"order": []
            });
	    	
	    	$("#filtro").on('keyup',function(e){
	    		var term=$(this).val();
	    		$('#grid').DataTable().search(
					$(this).val()
	    		        
	    		).draw();
	    	});

		});
	</script>
</body>
</html>
