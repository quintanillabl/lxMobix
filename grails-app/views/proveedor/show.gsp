<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${proveedorInstance.nombre}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		


		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3">
				<div class="btn-group">
				    
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Proveedores
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="ADMIN">
				    	<g:link action="delete" class="btn btn-danger " id="${proveedorInstance.id}"
				    		onclick="return confirm('Eliminar el proveedor: '+${proveedorInstance.id});">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    </sec:ifAllGranted>
				    <sec:ifAllGranted roles="CONTABILIDAD">
				    	<g:link action="edit" class="btn btn-default " id="${proveedorInstance.id}">
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
						<div class="panel-heading">${proveedorInstance}</div>
					  	<div class="panel-body">
					    <g:hasErrors bean="${proveedorInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${proveedorInstance}" as="list" />
					    		</ul>
					    	</div>
					    	<g:if test="${flash.message}">
					    		<span class="label label-warning">${flash.message}</span>
					    	</g:if> 
					    </g:hasErrors>
						<f:with bean="${proveedorInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="nombre" widget-class="form-control " />
							<f:field property="rfc" widget-class="form-control"/>
							<f:field property="nacional" widget-class="form-control"/>
							<f:field property="email" widget-class="form-control"/>
							<f:field property="www" widget-class="form-control"/>
							<f:field property="cuentaContable" widget-class="form-control"/>
						</f:with>
						<g:render template="/common/direccion" bean="${proveedorInstance}"/>
					  </div>
					
					 
					 

				</g:form>
				</fieldset>

		    </div>
		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>