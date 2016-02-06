<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application">
	<title>Polizas de cierre anual</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	
</head>
<body>
<g:set var="procesadores" value="${com.luxsoft.lx.contabilidad.ProcesadorDePoliza.list()}" scope="request" />
	
	<div class="container-fluid">
	    
	    <div class="row">
	      <div class="col-md-12">
	        
	        <div class="alert alert-info">
	          <h4 class="text-center"> Polizas de cierre</h4>
	          <p class="text-center">
	            <small>
	              ${session.empresa.nombre} 
	              
	            </small>
	          </p>
	          <g:if test="${flash.message}">
	            <span class="label label-warning">${flash.message}</span>
	          </g:if> 
	        </div>

	      </div>
	    </div>

        <div class="row">

        	<div class="col-md-2">

	          	<div class="list-group">
	              <g:link class="list-group-item ${subTipo=='TODAS'?'active':''}" 
	                action="index" params="[subTipo:'TODAS']">
	                <i class="fa fa-bars"></i> Todas
	              </g:link>

	              <g:each in="${procesadores}" var="row">
	                
	                <g:link class="list-group-item ${subTipo=='row.nombre'?'active':''}" 
	                  action="index" params="[subTipo:row.nombre]">
	                  <i class="fa fa-bars"></i> ${row.label}
	                </g:link>

	              </g:each>
	            </div>

        	</div>

	        <div class="col-md-10">
	          	<div class="row toolbar-panel">
	              <div class="col-md-4">
	              	<input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
	                </div>

	              <div class="btn-group">
	                  
	                  <g:link action="index" class="btn btn-default ">
	                      <span class="glyphicon glyphicon-repeat"></span> Refrescar
	                  </g:link>
	                  
	                 
	              </div>
				</div>

	            <div class="row">
		            <div class="col-md-12">
		              	<table id="grid" class="table table-striped table-bordered table-condensed">

		              		<thead>
		              			<tr>
		              				<th>Folio</th>
		              				<th>Tipo</th>
		              				<th>SubTipo</th>
		              				<th>Ejercicio</th>
		              				<th>Mes</th>
		              				<th>Fecha</th>
		              				<th>Concepto</th>
		              				<th>Debe</th>
		              				<th>Haber</th>
		              				<th>Cuadre</th>
		              				<th>Modificado</th>
		              				<th>Cierre</th>
		              			</tr>
		              		</thead>
		              		<tbody>
		              			<g:each in="${polizaInstanceList}" var="row">
		              				<tr id="${row.id}" class="${row.cuadre?'danger':''}">
		              					<td>
		              						<g:link  action="show" id="${row.id}">
		              							${formatNumber(number:row.folio,format:'####')}
		              						</g:link>
		              					</td>
		              					<td >
		              						<g:link  action="show" id="${row.id}">
		              							${fieldValue(bean:row,field:"tipo")}
		              						</g:link>
		              					</td>
		              					<td >
		              						<g:link  action="show" id="${row.id}">
		              							${fieldValue(bean:row,field:"subTipo")}
		              						</g:link>
		              					</td>
		              					<td >
		              						<g:link  action="show" id="${row.id}">
		              							${row.ejercicio}
		              						</g:link>
		              					</td>
		              					<td >
		              						<g:link  action="show" id="${row.id}">
		              							${row.mes}
		              						</g:link>
		              					</td>
		              					
		              					<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
		              					<td>${fieldValue(bean:row,field:"concepto")}</td>
		              					<td>${formatNumber(number:row.debe,type:'currency')}</td>
		              					<td>${formatNumber(number:row.haber,type:'currency')}</td>
		              					<td>${formatNumber(number:row.cuadre,type:'currency')}</td>
		              					<td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td>
		              					<td><g:formatDate date="${row.cierre}" format="dd/MM/yyyy HH:mm"/></td>
		              				</tr>
		              			</g:each>
		              		</tbody>
		              	</table>
		              	<div class="pagination">
		              		<g:paginate total="${polizaInstanceCount ?: 0}"/>
		              	</div>
		            </div>
	            </div> 

	        </div>


	</div>
	
	<g:render template="/common/cambioDePeriodo" bean="${session.periodoContable}"/>
	<g:render template="generarDialog"/>    
	
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