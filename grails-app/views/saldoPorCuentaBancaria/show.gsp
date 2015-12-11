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
				  <h3>Saldo por cuenta bancaria ${saldoPorCuentaBancariaInstance.id} <small> (${session.empresa})</small>
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
			
			<div class="col-md-12 ">
				
				<form  class="form-horizontal"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Cuenta ${saldoPorCuentaBancariaInstance.cuenta} Periodo(session.periodoTesoreria)</div>
					  	<div class="panel-body">
						    
					    	<f:with bean="${saldoPorCuentaBancariaInstance}">
								<div class="col-md-3">
						    		<f:display property="saldoInicial" wrapper="bootstrap3"/>
						    		
					    		</div>
					    		<div class="col-md-3">
						    		<f:display property="ingresos" wrapper="bootstrap3"/>
						    		
					    		</div>
					    		<div class="col-md-3">
						    		<f:display property="egresos" wrapper="bootstrap3"/>
						    		
					    		</div>
					    		<div class="col-md-3">
						    		
						    		<f:display property="saldoFinal" wrapper="bootstrap3"/>
					    		</div>
					    	</f:with>
						    
						    
							
					  	</div>

					  	<div class="row">
					  		<div class="col-md-12">
					  			<table id="grid" class="table table-striped table-bordered table-condensed">

					  				<thead>
					  					<tr>
					  						<th>Fecha</th>
					  						
					  						<th>Concepto</th>
					  						<th>Referencia</th>
					  						<th>Comentario</th>
					  						<th>Importe</th>
					  					</tr>
					  				</thead>
					  				<tbody>
					  					<g:each in="${movimientos.sort{it.fecha}}" var="row">
					  						<tr id="${row.id}">
					  							<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
					  							
					  							<td>${fieldValue(bean:row,field:"concepto")}</td>
					  							<td>${fieldValue(bean:row,field:"referencia")}</td>
					  							<td>${fieldValue(bean:row,field:"comentario")}</td>
					  							<td class="${row.importe<=0?'text-danger':'text-success'}">${formatNumber(number:row.importe,type:'currency')}</td>
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
						  			<g:link class="btn btn-default" action="imprimirEstadoDeCuenta"
						  				id="${saldoPorCuentaBancariaInstance.id}">
						  				Estado de cuenta
						  			</g:link>
	  			 					%{-- <g:jasperReport
	  			 						controller="saldoDeCuenta"
	  			 						action="imprimirEstadoDeCuenta" 
	  			 						jasper="EstadoDeCuentaBanco" 
	  			 						format="PDF" 
	  			 						name="Estado de cuenta">
	  									<g:hiddenField name="cuentaId" value="${saldoPorCuentaBancariaInstance.cuenta.id}"/>
	  									<g:hiddenField name="periodo" value="${session.periodoTesoreria.toPeriodo().fechaFinal.text()}"/>
	  									<g:hiddenField name="COMPANY" value="${session.empresa.nombre}"/>
	  								</g:jasperReport> --}%
						  			
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