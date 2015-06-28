<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Pago</title>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de pago  <small> (${session.empresa})</small>
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
			
			<div class="col-md-6 col-md-offset-3 ">
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body" >
						    <g:hasErrors bean="${pagoInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${pagoInstance}" as="list" />
						    		</ul>
						    	</div>
						    	
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${pagoInstance}">
								<f:field property="requisicion" widget-class="form-control"/>
								<f:field property="cuenta" widget-class="form-control"/>
								<f:field property="formaDePago" widget-class="form-control"/>
								<f:field property="fecha" widget-class="form-control"/>
								
								<f:field property="importe" widget-class="form-control"/>
								<f:field property="referencia" />
								<f:field property="comentario" widget-class="form-control"/>
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton name="Salvar" class="btn btn-primary " />
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
			$("#formPanel :input").each(function(){
			 	var input = $(this); // This is the jquery object of the input, do what you will
			 	input.removeAttr("type")
			 	.prop('type','text')
			 	.addClass('form-control')
			 	.addClass('text-right');
			});
		});
	</script>
	
</body>
</html>