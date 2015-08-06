<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Pagos</title>
	%{-- <asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>  --}%
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Registro de pagos  </p>
						<p class="text-center"><small>${session.empresa.nombre}</small></p>
							
					</h4>
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
							<th>Proveedor</th>
							<th>Req</th>
							<th>Fecha</th>
							<th>Cuenta</th>
							<th>Total</th>
							<th>Aplicado</th>
							<th>Comentario</th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${pagoInstanceList}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"requisicion.proveedor")}
									</g:link>
								</td>
								<td>${fieldValue(bean:row,field:"requisicion.id")}</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td>${fieldValue(bean:row,field:"cuenta.nombre")} ${fieldValue(bean:row,field:"cuenta.numero")}</td>
								<td>${formatNumber(number:row.importe,type:'currency')}</td>
								<td>${formatNumber(number:row.aplicado,type:'currency')}</td>
								<td>${fieldValue(bean:row,field:"comentario")}</td>
								%{-- <td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td> --}%
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${pagoInstanceCount ?: 0}"/>
				</div>
			</div>
		</div> <!-- end .row 2 -->
		%{-- <g:render template="uploadXmlFile"/> --}%
	${assetPath(src: 'plugins/dataTables/swf/copy_csv_xls_pdf.swf')}
	</div>


	<script type="text/javascript">
		$(document).ready(function(){
 			$('#grid').dataTable({
                responsive: true,
                "language": {
					"url": "${assetPath(src: 'plugins/dataTables/dataTables.spanish.txt')}"
	    		},
	    		"dom": 'T<"clear">ltip',
	    		"tableTools": {
	    		    "sSwfPath": "${assetPath(src: 'plugins/dataTables/swf/copy_csv_xls_pdf.swf')}"
	    		},
	    		"order": []
            });
			
			// $('#grid').dataTable( {
			// 	responsive: true,
	  //       	"paging":   false,
	  //       	"ordering": false,
	  //       	"info":     false
	  //       	,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
	  //   	} );
	    	
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