<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inmuebles</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<g:if test="${flash.message}">
			  		<div class="alert alert-warning">
			  			<span class="label label-warning ">${flash.message}</span>
			  		</div>
			  	</g:if> 
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-8 col-md-offset-2">
				
				<g:form class="form-horizontal" action="update" id="${inmuebleInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Inmueble: ${inmuebleInstance}</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${inmuebleInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${inmuebleInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${inmuebleInstance}">
							<f:field property="clave" widget-class="form-control uppercase-field" />
							<f:field property="descripcion" widget-class="form-control" />
							<f:field property="cuentaPredial" widget-class="form-control"/>
							<f:field property="unidad" widget-class="form-control" />
							<f:field property="inventariable" widget-class="form-control" />
							<f:field property="precio" widget-class="form-control" widget-type="text"/>
						</f:with>
						<g:render template="/common/direccion" bean="${inmuebleInstance}"/>
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${inmuebleInstance.id}"> Cancelar</g:link>
					  		</div>
					  	</div>
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

	</div>

	
</body>
</html>