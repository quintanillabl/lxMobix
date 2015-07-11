<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Balanza de comprobacion</title>
	<asset:stylesheet src="datatables/jquery.dataTables.css"/>
	<asset:javascript src="datatables/jquery.dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Balanza de comprobación  (<small>${session.empresa.nombre}</small>)</p>
							
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
		        <g:link action="actualizar" class="btn btn-default ">
		            <i class="fa fa-cog"></i></span> Actualizar
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
		        	<li>
		        		<g:link action="balanzaDeComprobacion" >
		        		    <i class="fa fa-print"></i> Balanza de comprobación
		        		</g:link> 
		        	</li>
		        	<li>
		        		<g:link action="balanceGeneral" >
		        		    <i class="fa fa-print"></i> Balance general
		        		</g:link> 
		        	</li>
		        </ul>
		    </div>
		    
		</div>

		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">

					<thead>
						<tr>
							<th>Cuenta</th>
							<th>Descripcion</th>
							<th>Saldo Inicial</th>
							<th>Debe</th>
							<th>Haber</th>
							<th>Saldo Final</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${partidas.sort()}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"cuenta.clave")}
									</g:link>
								</td>
								<td>
									<g:link  action="show" id="${row.id}">
										${fieldValue(bean:row,field:"cuenta.descripcion")}
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