<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Cliente</title>
	
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h1>Cliente<p><small>${clienteInstance}</small></p>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h1>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${clienteInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Folio ${clienteInstance.id}</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${clienteInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${clienteInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${clienteInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="nombre" widget-class="form-control " />
							<f:field property="rfc" widget-class="form-control"/>
							<f:field property="cuentaContable"  >
								<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${gastoDetInstance?.cuentaContable?.id}"/>
								<input type="text" id="cuentaField" class="form-control" value="${gastoDetInstance?.cuentaContable}" >
							</f:field>
							
						</f:with>
						<g:render template="/common/direccion" bean="${clienteInstance}"/>
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${clienteInstance.id}"> Cancelar</g:link>
					  		</div>
					  	</div>
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

		<script type="text/javascript">
			$(function (){

				$("#cuentaField").autocomplete({
					source:'<g:createLink controller="cuentaContable" action="getCuentasDeDetalleJSON"/>',
					minLength:3,
					select:function(e,ui){
						$("#cuentaId").val(ui.item.id);
					}
				});

			});
		</script>

	</div>

	
</body>
</html>