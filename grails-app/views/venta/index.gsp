<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Ventas</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-info">
					<h3>
						<p class="text-center"> Ventas  ${session.empresa.nombre}</p>
					</h3>
					<g:if test="${flash.message}">
						<span class="label label-warning">${flash.message}</span>
					</g:if> 
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row toolbar-panel">
		    <div class="col-md-6">
		    	<input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
		      </div>

		    <div class="btn-group">
		        
		        <g:link action="index" class="btn btn-default ">
		            <span class="glyphicon glyphicon-repeat"></span> Refrescar
		        </g:link>
		    </div>
		    <div class="btn-group">
		        <button type="button" name="operaciones"
		                class="btn btn-default dropdown-toggle" data-toggle="dropdown"
		                role="menu">
		                Operaciones <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu">
		            <li>
		                <g:link action="create" ><i class="fa fa-plus"></i> Nuevo</g:link>
		            </li>
		        </ul>
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
							<th>Folio</th>
							<th>Cliente</th>
							<th>Fecha</th>
							<th>Tipo</th>
							<th>Comentario</th>
							<th>Total</th>
							<th>Estatus</th>
							<th>Modificado</th>

						</tr>
					</thead>
					<tbody>
						<g:each in="${ventaInstanceList}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"folio")}
									</g:link>
								</td>
								<td>
									<g:link  action="show" id="${row.id}">
										<abbr title="${row.cliente.nombre}">
										${org.apache.commons.lang.StringUtils.substring(row.cliente.nombre,0,40)}
										</abbr>
									</g:link>
								</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td>${row.tipo.toString()[0..4]}</td>
								<td>
									<abbr title="${row.comentario}">
										${org.apache.commons.lang.StringUtils.substring(row.comentario,0,40)}
									</abbr>
									
								</td>
								<td>${g.formatNumber(number:row.total,type:'currency')}</td>
								<td>${fieldValue(bean:row,field:"status")}</td>
								<td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${ventaInstanceCount ?: 0}"/>
				</div>
			</div>
		</div> <!-- end .row 2 -->

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
</body>
</html>