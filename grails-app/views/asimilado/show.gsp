<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${asimiladoInstance.nombre}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="well well-sm">
					<h2> 
						${asimiladoInstance.nombre} <small>(${session.empresa})</small>
					</h2>
					<g:if test="${flash.message}">
						<span class="label label-warning">${flash.message}</span>
					</g:if> 
				</div>
			</div>
		</div><!-- end .row -->


		<div class="row toolbar-panel">
			<div class="col-md-6 col-md-offset-3">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Catálogo
				    </g:link>
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="TESORERIA">
				    	<g:link action="delete" class="btn btn-danger " id="${asimiladoInstance.id}">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    </sec:ifAllGranted>
				    <sec:ifAllGranted roles="TESORERIA">
				    	<g:link action="edit" class="btn btn-default " id="${asimiladoInstance.id}">
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
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Folio ${asimiladoInstance.id}</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${bancoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${asimiladoInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${asimiladoInstance}">
							<f:display property="nombre" widget-class="form-control" wrapper="bootstrap3"/>
							<f:display property="rfc" widget-class="form-control" wrapper="bootstrap3"/>
							<f:display property="curp" widget-class="form-control" wrapper="bootstrap3"/>
							<f:display property="bancoSat" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
							<f:display property="numeroDeCuenta" widget-class="form-control" wrapper="bootstrap3"/>
							<f:display property="clabe" widget-class="form-control" wrapper="bootstrap3"/>
							<f:display property="formaDePago" widget-class="form-control" wrapper="bootstrap3"/>
						</f:with>
					  </div>

					</div>
				</g:form>
				</fieldset>

		    </div>
		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>