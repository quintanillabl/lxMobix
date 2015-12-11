<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Movimiento de cuenta  ${movimientoDeCuentaInstance.id}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Inversión bancaria ${movimientoDeCuentaInstance.id} <small> (${session.empresa})</small>
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
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${movimientoDeCuentaInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${movimientoDeCuentaInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${movimientoDeCuentaInstance}">

							
								
								<f:display property="empresa" wrapper="bootstrap3"/>
								<f:display property="cuenta" wrapper="bootstrap3"/>
								<f:display property="folio" wrapper="bootstrap3"/>
								<f:display property="fecha" wrapper="bootstrap3"/>
								<f:display property="importe" widget="money" wrapper="bootstrap3"/>
								<f:display property="concepto" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
				    			<f:display property="referencia" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
				    			<f:display property="comentario" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
								
									
							</f:with>
					  	</div>

					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-6">
						  			<lx:backButton />
						  			<g:link class="btn btn-default"
						  				action="estadoDeCuenta" id="${movimientoDeCuentaInstance.cuenta.id}">
						  				Estado de cuenta
						  			</g:link>
						  			<a href="" class="btn btn-danger " data-toggle="modal" data-target="#deleteDialog"><i class="fa fa-trash"></i> Eliminar</a> 
						  			
						  		</div>
						  	</div>
						</div>

					</div>

				</form>
				
			</div>
		</div> <!-- end .row 2 -->

		<div class="modal fade" id="deleteDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Eliminar el registro ${movimientoDeCuentaInstance.id}</h4>
					</div>

					<g:form action="delete" class="form-horizontal" method="DELETE">
						<g:hiddenField name="id" value="${movimientoDeCuentaInstance.id}"/>
						<div class="modal-body">
							<p><small>${movimientoDeCuentaInstance}</small></p>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-danger" name="aceptar"
									value="Eliminar" />
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