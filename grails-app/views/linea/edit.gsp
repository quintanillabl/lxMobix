<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${lineaInstance}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">


		<div class="row row-header ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${lineaInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">${lineaInstance} Id:${lineaInstance.id}</div>
					  <div class="panel-body">
					  	<g:if test="${flash.message}">
					  		<small><span class="label label-warning ">${flash.message}</span></small>
					  	</g:if> 
					    <g:hasErrors bean="${lineaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${lineaInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${lineaInstance}">
							<f:field property="clave" widget-class="form-control " />
							<f:field property="descripcion" widget-class="form-control " />
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${lineaInstance.id}"> Cancelar</g:link>
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