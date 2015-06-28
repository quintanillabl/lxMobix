<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Alta de sub cuenta</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="mask/jquery.mask.min.js"/> 
</head>
<body>

	<div class="container">
		
		

		<div class="row page-header">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="saveSubCuenta" >	

					<div class="panel panel-primary">
						<div class="panel panel-heading">
							SubCuenta de :${cuenta}
						</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${subCuenta}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${subCuenta}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${subCuenta}">
							<g:hiddenField name="cuentaId" value="${cuenta.id}"/>
							<f:field property="clave" widget-class="form-control cuenta" input-autofocus="on"/>
							<f:field property="descripcion" widget-class="form-control"/>
							<f:field property="cuentaSat" widget-class="form-control"/>
							<f:field property="detalle" widget-class="form-control"/>
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
	<script type="text/javascript">
		$(function(){
			$('.cuenta').mask('00000');
		});
	</script>
	
</body>
</html>