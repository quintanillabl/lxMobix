<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Polizas de cierre anual</title>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Pólizas de cierre </p>
						<p class="text-center">
							<small>
								${session.empresa.nombre} 
								
							</small>
						</p>
							
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
		        
		        <g:link action="cierreAnual" class="btn btn-default ">
		            <span class="glyphicon glyphicon-repeat"></span> Refrescar
		        </g:link>
		        %{-- <a class="btn btn-default " href="#ejercicioDialog" data-toggle="modal" >
		        	<i class="fa fa-cog"></i> Generar
		        </a> --}%
		        
		    </div>
		    
		</div>

		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">

					<thead>
						<tr>
							<th>Ejercicio</th>
							<th>Mes</th>
							<th>Tipo</th>
							<th>Folio</th>
							<th>Fecha</th>
							<th>Concepto</th>
							<th>Debe</th>
							<th>Haber</th>
							<th>Cuadre</th>
							<th>Modificado</th>
							<th>M</th>
							<th>Cierre</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${polizaInstanceList}" var="row">
							<tr id="${row.id}" class="${row.cuadre?'danger':''}">
								<td >
									<g:link  action="show" id="${row.id}">
										${formatNumber(number:row.ejercicio,format:'####')}
									</g:link>
								</td>
								<td >
									<g:link  action="show" id="${row.id}">
										${formatNumber(number:row.mes,format:'####')}
									</g:link>
								</td>
								<td>${fieldValue(bean:row,field:"tipo")}</td>
								<td>${formatNumber(number:row.folio,format:'####')}</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td>${fieldValue(bean:row,field:"concepto")}</td>
								<td>${formatNumber(number:row.debe,type:'currency')}</td>
								<td>${formatNumber(number:row.haber,type:'currency')}</td>
								<td>${formatNumber(number:row.cuadre,type:'currency')}</td>
								<td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td>
								<td><g:checkBox name="myCheckbox" value="${row.manual}" disabled="disabled"/></td>
								<td><g:formatDate date="${row.cierre}" format="dd/MM/yyyy HH:mm"/></td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${polizaInstanceCount ?: 0}"/>
				</div>
			</div>
		</div> <!-- end .row 2 -->

		<div class="modal fade" id="ejercicioDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Generar poliza de cierre anual</h4>
					</div>
					<g:form action="generarCierreAnual" class="form-horizontal">
						<div class="modal-body">
							<f:with bean="${session.periodoContable}">
								<f:field property="ejercicio" widget-class="form-control"/>
							</f:with>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-primary" name="aceptar"
									value="Aceptar" />
						</div>
					</g:form>


				</div>
				<!-- moda-content -->
			</div>
			<!-- modal-di -->
		</div>


	</div>


	
</body>
</html>