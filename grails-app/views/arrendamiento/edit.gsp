<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Arrendamiento ${arrendamientoInstance.id}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		

		<div class="row row-header">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" method="PUT" id="${arrendamientoInstance.id}">	

					<div class="panel panel-primary">
						<div class="panel panel-heading">
							Arrendamiento: ${arrendamientoInstance.id}  Cliente: ${arrendamientoInstance.cliente}
						</div>
					  	<div class="panel-body">
					    	<g:hasErrors bean="${arrendamientoInstance}">
					    		<div class="alert alert-danger">
					    			<ul class="errors" >
					    				<g:renderErrors bean="${arrendamientoInstance}" as="list" />
					    			</ul>
					    		</div>
					    	</g:hasErrors>
							<f:with bean="${arrendamientoInstance}">
								<f:display property="inmueble" widget-class="form-control" value="${arrendamientoInstance.inmueble.descripcion}"/>
								<f:display property="tipo" widget-class="form-control" />
								<f:field property="diaDeCorte" widget-class="form-control">
									<g:select class="form-control"  
									name="${property}" 
									value="${value}"
									from="${(1..20)}" 
									/>
							</f:field>
							<f:field property="envioAutomatico" widget-class="form-control"/>
							<f:field property="facturacionAutomatica" widget-class="form-control"/>
							<f:field property="inicio" widget-class="form-control"/>
							<f:field property="fin" widget-class="form-control"/>
							<f:field property="precio" widget-class="form-control moneda-field " widget-type="text"/>
							<f:field property="comentario" widget-class="form-control"/>
						</f:with>
						</div>
					  
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3 col-md-6">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
					  		</div>
					  	</div>
					  	<g:if test="${flash.message}">
					  		<small><span class="label label-warning ">${flash.message}</span></small>
					  	</g:if> 
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

	</div>

<script type="text/javascript">
	$(function(){
		$(".moneda-field").autoNumeric({wEmpty:'zero',aSep:""});
	});
</script>
	
</body>
</html>