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

		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3">
				<div class="btn-group">
				    
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Líneas
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="VENTAS">
				    	<g:link action="delete" class="btn btn-danger " id="${lineaInstance.id}"
				    		onclick="return confirm('Eliminar el línea: '+${lineaInstance.id});">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    	<g:link action="edit" class="btn btn-default " id="${lineaInstance.id}">
				    	    <i class="fa fa-pencil"></i> Editar
				    	</g:link>
				    </sec:ifAllGranted>
				</div>
				
				<div class="btn-group">
				    <button type="button" name="reportes"
				            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
				            role="menu">
				            Reportes <span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu">
				        
				    </ul>
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-6 col-md-offset-3">
		    	<fieldset disabled>
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">${lineaInstance}</div>
					  	<div class="panel-body">
					    <g:hasErrors bean="${lineaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${lineaInstance}" as="list" />
					    		</ul>
					    	</div>
					    	<g:if test="${flash.message}">
					    		<span class="label label-warning">${flash.message}</span>
					    	</g:if> 
					    </g:hasErrors>
						<f:with bean="${lineaInstance}">
							<f:field property="clave" widget-class="form-control"/>
							<f:field property="descripcion" widget-class="form-control " />
						</f:with>
						
					  </div>
					
					 
					 

				</g:form>
				</fieldset>

		    </div>
		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>