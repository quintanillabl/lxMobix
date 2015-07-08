<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inmuebles</title>
</head>
<body>

	<div class="container">
		
		


		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3 toolbar-panel">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i></span> Inmuebles
				    </g:link>
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="VENTAS">
				    	%{-- <g:link action="delete" class="btn btn-danger " id="${inmuebleInstance.id}">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link> --}%
				    	%{-- <a href="#deleteDialog" class="btn btn-danger "><i class="fa fa-trash"></i> Eliminar</a> --}%

				    	<g:link action="edit" class="btn btn-default " id="${inmuebleInstance.id}">
				    	    <i class="fa fa-pencil"></i> Editar
				    	</g:link>

				    </sec:ifAllGranted>
				    <g:link action="print" class="btn btn-default " id="${inmuebleInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
				    <lx:deleteButton bean="${inmuebleInstance}"/>
				</div>
				
				
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-6 col-md-offset-3">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Folio ${inmuebleInstance.id} ${inmuebleInstance.clave} <small>(${inmuebleInstance.descripcion})</small></div>
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
							<f:field property="descripcion" widget-class="form-control"/>
							<f:field property="cuentaPredial" widget-class="form-control"/>
							
						</f:with>
						<g:render template="/common/direccion" bean="${inmuebleInstance}"/>
					  </div>
					  <g:if test="${flash.message}">
					  	<span class="label label-warning">${flash.message}</span>
					  </g:if> 
					</div>
				</g:form>
				</fieldset>

		    </div>

		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>