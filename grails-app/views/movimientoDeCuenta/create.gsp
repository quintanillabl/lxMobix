<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Movimiento de cuenta</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de movimiento  <small> (${session.empresa})</small>
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
			
			<div class="col-md-8 col-md-offset-2">
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${gastoInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${gastoInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${gastoInstance}">
								<div class="col-sm-12">
									<f:field property="proveedor" cols="col-sm-8" colsLabel="col-sm-3"/>
									<f:field property="fecha" cols="col-sm-4" colsLabel="col-sm-3"/>
									<f:field property="vencimiento" cols="col-sm-4" colsLabel="col-sm-3"/>
									<f:field property="serie" widget-class="form-control "  cols="col-sm-4" colsLabel="col-sm-3"/>
									<f:field property="folio" widget-class="form-control "  cols="col-sm-4" colsLabel="col-sm-3"/>
									
									<f:field property="moneda" widget-class="form-control" cols="col-sm-4" colsLabel="col-sm-3"/>
									<f:field property="tipoDeCambio" widget-readonly="readonly" widget-class="form-control" cols="col-sm-4" colsLabel="col-sm-3" label="T.C."/>
									<f:field property="cuentaContable" cols="col-sm-8" colsLabel="col-sm-3">
										<g:hiddenField id="cuentaId" name="cuentaContable.id" />
										<input type="text" value="${value}" id="cuentaField" class="form-control">
									</f:field>
									<f:field property="comentario" cols="col-sm-8" colsLabel="col-sm-3" widget-class="form-control"/>
									
								</div>
								
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
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
			$(".money").autoNumeric({wEmpty:'zero',aSep:"",lZero: 'deny'});
			$(".porcentaje").autoNumeric({vMin: '0', vMax: '99.9999',lZero: 'deny'});

		})
	</script>
	
</body>
</html>