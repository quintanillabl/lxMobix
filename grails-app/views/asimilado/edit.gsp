<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Asimilado ${asimiladoInstance.nombre}</title>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h1>${asimiladoInstance.nombre}<p><small>${session.empresa}</small></p>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h1>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" method="PUT" id="${asimiladoInstance.id}">	

					<div class="panel panel-primary">
					  <div class="panel-body">
					    <g:hasErrors bean="${asimiladoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${asimiladoInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${asimiladoInstance}">
							<g:hiddenField name="id" value="${asimiladoInstance?.id}" />
				  			<g:hiddenField name="version" value="${asimiladoInstance?.version}" />
							<g:hiddenField name="empresa.id" value="${asimiladoInstance.empresa.id}"/>
							<f:field property="nombre" widget-class="form-control" wrapper="bootstrap3"/>
							<f:field property="rfc" widget-class="form-control" wrapper="bootstrap3"/>
							<f:field property="curp" widget-class="form-control" wrapper="bootstrap3"/>
							<f:field property="bancoSat" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
							<f:field property="numeroDeCuenta" widget-class="form-control" wrapper="bootstrap3"/>
							<f:field property="clabe" widget-class="form-control" wrapper="bootstrap3"/>
							<f:field property="formaDePago" widget-class="form-control" wrapper="bootstrap3">
								<g:select class="form-control chosen-select "  
									name="${property}" 
									value="${value}"
									from="${['CHEQUE','TRANSFERENCIA']}"/>
							</f:field>
						</f:with>
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-4">
					  			<g:submitButton name="Salvar" class="btn btn-primary " />
					  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
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