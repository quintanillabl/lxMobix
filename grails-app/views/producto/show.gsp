<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${productoInstance.clave}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">

		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3">
				<div class="btn-group">
				    
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Productos
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="ADMIN">
				    	<g:link action="delete" class="btn btn-danger " id="${productoInstance.id}"
				    		onclick="return confirm('Eliminar el proveedor: '+${productoInstance.id});">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    </sec:ifAllGranted>
				    <sec:ifAllGranted roles="CONTABILIDAD">
				    	<g:link action="edit" class="btn btn-default " id="${productoInstance.id}">
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
						<div class="panel-heading">${productoInstance}</div>
					  	<div class="panel-body">
					    <g:hasErrors bean="${productoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${productoInstance}" as="list" />
					    		</ul>
					    	</div>
					    	<g:if test="${flash.message}">
					    		<span class="label label-warning">${flash.message}</span>
					    	</g:if> 
					    </g:hasErrors>
						<f:with bean="${productoInstance}">
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
					
					 
					 

				</g:form>
				</fieldset>

		    </div>
		</div>

		<!-- end .row 2 -->

	</div>
	<script type="text/javascript">
		$(function(){
			$(".moneda-field").autoNumeric({wEmpty:'zero',aSep:""});
			$(".data-descuento").autoNumeric({vMin: '0', vMax: '99.9999'})
		});
	</script>
	
</body>
</html>