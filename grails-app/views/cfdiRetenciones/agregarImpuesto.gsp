<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Alta de impuesto retenido</title>
	<asset:javascript src="forms/autoNumeric.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de impuesto retenido <small> ${cfdiRetencionesInstance.folio} (${cfdiRetencionesInstance.receptor})</small>
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
			
			<div class="col-md-10 ">
				
				<g:form class="form-horizontal" action="salvarImpuesto" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Importes</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${impuestoRetenidoInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${impuestoRetenidoInstance}" as="list" />
						    		</ul>
						    	</div>
						    	
						    </g:hasErrors>
							<f:with bean="${impuestoRetenidoInstance}">
								<div class="col-sm-7">
									<g:hiddenField name="retencion.id" value="${session.empresa.id}"/>
									<f:field property="baseRet" widget="money" wrapper="bootstrap3"/>
									<f:field property="impuesto" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="montoRet" widget="money" wrapper="bootstrap3"/>
									<f:field property="tipoPagoRet" wrapper="bootstrap3" widget-class="form-control"/>
								</div>
								
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton name="Salvar" class="btn btn-primary " />
						  			<g:link action="edit" id="${cfdiRetencionesInstance.id}" 
						  				class="btn btn-default"> Cancelar
						  			</g:link>
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
		})
	</script>
	
</body>
</html>