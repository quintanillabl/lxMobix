<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Movimiento de cuenta  ${saldoPorCuentaBancariaInstance.id}</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Inversión bancaria ${saldoPorCuentaBancariaInstance.id} <small> (${session.empresa})</small>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  	<g:if test="${flash.error}">
				  		<small><span class="label label-danger ">${flash.error}</span></small>
				  	</g:if> 
				  </h3>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-8 col-md-offset-2">
				
				<form  class="form-horizontal"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Propiedades</div>
					  	<div class="panel-body">
						    
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							%{-- <f:with bean="${saldoPorCuentaBancariaInstance}">
								
								<f:display property="fecha" wrapper="bootstrap3"/>
								<f:display property="vencimiento" wrapper="bootstrap3"/>
								<f:display property="cuentaOrigen" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:display property="cuentaDestino" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:display property="importe" 
									widget="money" wrapper="bootstrap3"/>

								<f:display property="rendimientoReal" 
									widget="money" wrapper="bootstrap3"/>

								<f:display property="importeIsr" label="ISR"
									widget="money" wrapper="bootstrap3"/>

				    			<f:display property="plazo" widget="numeric"
				    				wrapper="bootstrap3"/>

				    			<f:display property="comentario" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
								
									
							</f:with> --}%
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
					  					<g:each in="${saldoPorCuentaBancariaInstance.movimientos.sort{it.importe}}" var="row">
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
					  							<td class="${row.importe<=0?'text-danger':'text-success'}">${formatNumber(number:row.importe,type:'currency')}</td>
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
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<lx:backButton />
						  			<a href="" class="btn btn-danger " data-toggle="modal" data-target="#deleteDialog"><i class="fa fa-trash"></i> Eliminar</a> 
						  			
						  		</div>
						  	</div>
						</div>

					</div>

				</form>
				
			</div>
		</div> <!-- end .row 2 -->

		
	</div>

	
	
</body>
</html>