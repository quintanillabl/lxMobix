<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Comisión ${comisionInstance.id}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3">
				
				<g:form  class="form-horizontal" >	

					<div class="panel panel-primary">
						<div class="panel panel-heading">
							Comisión: ${comisionInstance.id}  Cuenta: ${comisionInstance.cuenta}
						</div>
					  	<div class="panel-body">
					    	<g:hasErrors bean="${comisionInstance}">
					    		<div class="alert alert-danger">
					    			<ul class="errors" >
					    				<g:renderErrors bean="${comisionInstance}" as="list" />
					    			</ul>
					    		</div>
					    	</g:hasErrors>
							<f:with bean="${comisionInstance}">
								<g:hiddenField name="id" value="${comisionInstance.id}"/>
								<g:hiddenField name="version" value="${comisionInstance.version}"/>
								<f:display property="fecha" wrapper="bootstrap3"/>
								<f:display property="cuenta" wrapper="bootstrap3" widget-class="form-control "/>
								<f:display property="comision" widget="money" wrapper="bootstrap3"/>
								<f:display property="impuestoTasa" widget="porcentaje" wrapper="bootstrap3" label="Tasa de impuesto(%)"/>
								<f:display property="impuesto" widget="money"  wrapper="bootstrap3"/>
				    			<f:display property="referenciaBancaria" widget-class="form-control " wrapper="bootstrap3"/>
				    			<f:display property="comentario" widget-class="form-control "  wrapper="bootstrap3"/>
							</f:with>
						</div>
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-3 col-md-6">
					  			<lx:backButton />

					  			<g:link action="edit" class="btn btn-default" id="${comisionInstance.id}"> Editar</g:link>
					  			<!-- <g:link action="index" class="btn btn-default"> Cancelar</g:link> -->

					  		</div>
					  	</div>
					  	<g:if test="${flash.message}">
					  		<small><span class="label label-warning ">${flash.message}</span></small>
					  	</g:if> 
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

	</div>

<script type="text/javascript">
	$(function(){
		$('#comision,#cuenta').each(function(){
			$(this).attr("required",true);
		});
		$(".money").autoNumeric('init',{mRound:'B',aSign: '$'});
		$(".tc").autoNumeric('init',{vMin:'0.0000'});
		$(".porcentaje").autoNumeric('init',{vMin:'0.00',vMax:'99.00'});

		

	 	$("#impuestoTasa").blur(function(){
			
			var comision=$("#comision").autoNumeric('get');
			var tasa=$("#impuestoTasa").autoNumeric('get');
			tasa=tasa/100;
			var impuesto=comision*tasa;
			//importe=Math.round(importe*100)/100;
			$("#impuesto").autoNumeric('set',impuesto);
			//$("#hiddenImpuesto").val(impuesto);
			
		});
	});
</script>
	
</body>
</html>