<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Alta de producto</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de producto  <small> (${session.empresa})</small>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h3>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos del producto</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${productoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${productoInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${productoInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="clave" widget-class="form-control uppercase-field " />
							<f:field property="descripcion" widget-class="form-control"/>
							<f:field property="linea" widget-class="form-control"/>
							<f:field property="unidad" widget-class="form-control"/>
							<f:field property="inventariable" widget-class="form-control"/>
							<f:field property="precio" widget-class="form-control moneda-field" widget-type="text"/>
							<f:field property="descuento" widget-class="form-control data-descuento" widget-type="text"/>
							<f:field property="impuesto" widget-class="form-control data-descuento" widget-type="text"/>
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-4">
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
			$(".moneda-field").autoNumeric({wEmpty:'zero',aSep:""});
			$(".data-descuento").autoNumeric({vMin: '0', vMax: '99.9999'})
		});
	</script>
	
</body>
</html>