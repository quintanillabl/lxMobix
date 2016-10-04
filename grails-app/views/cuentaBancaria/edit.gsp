<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inmuebles</title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container-fluid">
		
		<div class="row row-header">

			<div class="col-md-12">

				<g:if test="${flash.message}">
			  		<div class="alert alert-warning">
			  			<span class="label label-warning ">${flash.message}</span>
			  		</div>
			  	</g:if> 
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-12 ">
				
				<g:form class="form-horizontal" action="update" id="${cuentaBancariaInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Cuenta: ${cuentaBancariaInstance}</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${cuentaBancariaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${cuentaBancariaInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${cuentaBancariaInstance}">
							
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
								<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${value.id}"/>
								<input type="text" value="${value}" id="cuentaField" class="form-control">
							</f:field>
							<f:field property="cuentaRetencion" widget-class="form-control" widget-type="text"/>
							<f:field property="impresionTemplate" widget-class="form-control" widget-type="text" label="Plantilla de impresión"/>
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${cuentaBancariaInstance.id}"> Cancelar</g:link>
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