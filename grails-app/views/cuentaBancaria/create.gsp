<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Cuenta bancaria </title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h1>Alta de cuenta bancaria <p><small>${session.empresa}</small></p>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h1>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
					  <div class="panel-body">
					    <g:hasErrors bean="${cuentaBancariaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${cuentaBancariaInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
					    
						<f:with bean="${cuentaBancariaInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="banco" widget-class="form-control" />
							<f:field property="numero" widget-class="form-control" />
							<f:field property="tipo" widget-class="form-control"/>
							<f:field property="nombre" widget-class="form-control"/>
							<f:field property="moneda" widget-class="form-control"/>
							<f:field property="activo" widget-class="form-control"/>
							<f:field property="folioInicial" widget-class="form-control" widget-type="text"/>
							<f:field property="folioFinal" widget-class="form-control" widget-type="text"/>
							<f:field property="folio" widget-class="form-control" widget-type="text"/>
							<f:field property="tasaDeInversion" widget-class="form-control" widget-type="text"/>
							<f:field property="tasaIsr" widget-class="form-control" widget-type="text"/>
							<f:field property="diasInversionIsr" widget-class="form-control" widget-type="text"/>
							<f:field property="plazo" widget-class="form-control" widget-type="text"/>
							<f:field property="cuentaContable" >
								<g:hiddenField id="cuentaId" name="cuentaContable.id" />
								<input type="text" value="${value}" id="cuentaField" class="form-control">
							</f:field>
							<f:field property="impresionTemplate" widget-class="form-control" widget-type="text"/>
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
		$("#cuentaField").autocomplete({
			source:'<g:createLink controller="cuentaContable" action="getCuentasDeDetalleJSON"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#cuentaId").val(ui.item.id);
			}
		});
	});
</script>
	
</body>
</html>