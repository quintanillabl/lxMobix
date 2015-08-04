<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Aplicacion de cobro</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
				  <h3>Aplicación de cobro  <small> (${session.empresa})</small>
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
				
				<g:form name="createForm" class="form-horizontal" action="saveAplicacion"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${aplicacionDeCobroInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${aplicacionDeCobroInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
						    <g:hiddenField name="cobro.id" value="${aplicacionDeCobroInstance?.pago?.id}"/>
							<f:with bean="${aplicacionDeCobroInstance}">
								<div class="col-sm-12">
									<f:field property="cuentaPorCobrar" wrapper="bootstrap3" widget-class="form-control">
										<g:select class="form-control"  
											name="${property}" 
											value="${value}"
											from="${facturas}" 
											optionKey="id"
											required="true"
											/>
									</f:field>
									<f:field property="fecha" wrapper="bootstrap3"/>
									<f:field property="importe" widget="money" wrapper="bootstrap3"/>
									<f:field property="comentario" wrapper="bootstrap3" widget-class="form-control"/>
								</div>
								
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton name="Aplicar" class="btn btn-primary " />
						  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
						  		</div>
						  	</div>
						</div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->
	</div>

	<script type="text/javascript">
		$(function(){
			$(".money").autoNumeric({wEmpty:'zero',aSep:"",lZero: 'deny'});
		});
	</script>
	
</body>
</html>