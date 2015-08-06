<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Registro de cobros</title>
	<asset:stylesheet src="datatables/jquery.dataTables.css"/>
	<asset:javascript src="datatables/jquery.dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Registro de cobros  (<small>${session.empresa.nombre}</small>)</p>
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
		        	<li>
		        		<g:link controller="report" action="cobranza"> Cobros x Periodo</g:link>	
		        	</li>
		            
		        </ul>
		    </div>
		    
		    
		</div>

		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">

					<thead>
						<tr>
							<th>Cliente</th>
							<th>Fecha</th>
							<th>F.P.</th>
							<th>Referencia</th>
							<th>Importe</th>
							<th>Aplicado</th>
							<th>Disponible</th>
							<th>Comentario</th>
							<th>Ingreso</th>
							<th>Modificado</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${cobroInstanceList}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  action="edit" id="${row.id}">
										${fieldValue(bean:row,field:"cliente")}
									</g:link>
								</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td>${fieldValue(bean:row,field:"formaDePago")}</td>
								<td>${fieldValue(bean:row,field:"referencia")}</td>
								<td>${formatNumber(number:row.importe,type:'currency')}</td>
								<td>${formatNumber(number:row.aplicado,type:'currency')}</td>
								<td>${formatNumber(number:row.disponible,type:'currency')}</td>
								<td>${fieldValue(bean:row,field:"comentario")}</td>
								<td>${row.ingreso.id}</td>
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