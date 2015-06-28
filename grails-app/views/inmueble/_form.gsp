<g:form class="form-horizontal" action="update" id="${inmuebleInstance.id}" method="PUT">
	<div class="panel panel-primary">
		<div class="panel-heading">Inmueble: ${inmuebleInstance}</div>
		
	  	<div class="panel-body">


			<f:with bean="${inmuebleInstance}">
				<div class="col-sm-6">
					<f:field property="clave" widget-class="form-control uppercase-field" />
				</div>
				<div class="col-sm-6">
					<f:field property="descripcion" widget-class="form-control"/>
				</div>
				
			</f:with>

			<g:render template="/common/direccion" bean="${inmuebleInstance}"/>
			

		    <g:hasErrors bean="${inmuebleInstance}">
		    	<div class="alert alert-danger">
		    		<ul class="errors" >
		    			<g:renderErrors bean="${inmuebleInstance}" as="list" />
		    		</ul>
		    	</div>
		    </g:hasErrors>
		
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