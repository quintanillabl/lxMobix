<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Cuenta ${cuentaContableInstance.clave}</title>
	<asset:javascript src="mask/jquery.mask.min.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h1>${cuentaContableInstance}<p><small>${session.empresa}</small></p>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h1>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${cuentaContableInstance.id}" method="PUT">	

					<div class="panel panel-primary">
					  <div class="panel-body">
					    <g:hasErrors bean="${cuentaContableInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${cuentaContableInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${cuentaContableInstance}">
							<f:field property="clave" widget-class="form-control cuenta" 
								input-autofocus="on" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="descripcion" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="tipo" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="subTipo" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="naturaleza" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="cuentaSat" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="detalle" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="deResultado" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
							<f:field property="suspendida" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
						</f:with>
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3 col-md-6">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
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
		$('.cuenta2').mask('000-00000');
	});
</script>
	
</body>
</html>