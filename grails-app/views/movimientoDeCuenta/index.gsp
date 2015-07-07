<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Movimientos de cuentas</title>
	<asset:stylesheet src="datatables/jquery.dataTables.css"/>
	<asset:javascript src="datatables/jquery.dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Movimientos de cuentas bancarias  (<small>${session.empresa.nombre}</small>)</p>
						
							
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
		        
		        <g:link action="index" class="btn btn-default ">
		            <span class="glyphicon glyphicon-repeat"></span> Refrescar
		        </g:link>
		        <g:link action="print" class="btn btn-default " >
		            <i class="fa fa-print"></i> Imprimir
		        </g:link> 
		        <a href="#periodoDialog" data-toggle="modal" class="btn btn-default " >
		        	<i class="fa fa-calendar"></i>  Periodo: ${session.periodoContable.mes} / ${session.periodoContable.ejercicio}
		        </a>
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
							<th>Nombre</th>
							<th>Cuenta</th>
							<th>Importe</th>
							<th>Concepto</th>
							<th>Referencia</th>
							<th>Comentario</th>
							<th>Creado</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${movimientoDeCuentaInstanceList}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"cuenta.nombre")}
									</g:link>
								</td>
								<td>
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"cuenta.numero")}
									</g:link>
								</td>
								<td>${formatNumber(number:row.importe,type:'currency')}</td>
								<td>${fieldValue(bean:row,field:"concepto")}</td>
								<td>${fieldValue(bean:row,field:"referencia")}</td>
								<td>${fieldValue(bean:row,field:"comentario")}</td>
								<td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td>
							</tr>
						</g:each>
					</tbody>
				</table>
				
				
			</div>
		</div> <!-- end .row 2 -->
		<g:render template="/common/cambioDePeriodo" bean="${session.periodoContable}"/>
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