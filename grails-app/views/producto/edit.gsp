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

		<div class="row row-header ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${productoInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">${productoInstance} Id:${productoInstance.id}</div>
					  <div class="panel-body">
					  	<g:if test="${flash.message}">
					  		<small><span class="label label-warning ">${flash.message}</span></small>
					  	</g:if> 
					    <g:hasErrors bean="${productoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${productoInstance}" as="list" />
					    		</ul>
					    	</div>
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
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${productoInstance.id}"> Cancelar</g:link>
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