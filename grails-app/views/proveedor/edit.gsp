<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${proveedorInstance.nombre}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		
		


		<div class="row row-header ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="update" id="${proveedorInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">${proveedorInstance} Id:${proveedorInstance.id}</div>
					  <div class="panel-body">
					  	<g:if test="${flash.message}">
					  		<small><span class="label label-warning ">${flash.message}</span></small>
					  	</g:if> 
					    <g:hasErrors bean="${proveedorInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${proveedorInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
						<f:with bean="${proveedorInstance}">
							<f:field property="nombre" widget-class="form-control " />
							<f:field property="rfc" widget-class="form-control"/>
							<f:field property="nacional" widget-class="form-control"/>
							<f:field property="email" widget-class="form-control"/>
							<f:field property="www" widget-class="form-control"/>
							<f:field property="cuentaContable"  >
								<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${gastoDetInstance?.cuentaContable?.id}"/>
								<input type="text" id="cuentaField" class="form-control" value="${gastoDetInstance?.cuentaContable}" >
							</f:field>
						</f:with>
						<g:render template="/common/direccion" bean="${proveedorInstance}"/>
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<g:link action="show" class="btn btn-default" id="${proveedorInstance.id}"> Cancelar</g:link>
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