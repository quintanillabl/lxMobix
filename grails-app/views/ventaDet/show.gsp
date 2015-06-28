<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Partida de venta</title>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  %{-- <h3>Venta  <small>${venta}</small> --}%
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  	<g:if test="${flash.error}">
				  		<small><span class="label label-danger ">${flash.error}</span></small>
				  	</g:if> 
				  </h3>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${ventaDetInstance.id}" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Venta ${venta.folio} ${venta.cliente} (${venta.tipo})</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${ventaDetInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${ventaDetInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${ventaDetInstance}">
							<g:hiddenField name="venta.id" value="${venta.id}"/>
							<f:field property="producto" widget-class="form-control"/>
							<f:field property="comentario" widget-class="form-control"/>
							
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-4">
					  			<g:submitButton name="Salvar" class="btn btn-primary " />
					  			<g:link controller="venta" action="show" id="${venta?.id}" class="btn btn-default"> Cancelar</g:link>
					  		</div>
					  	</div>
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

	</div>
	
	<script type="text/javascript">
		
		
	</script>
</body>
</html>