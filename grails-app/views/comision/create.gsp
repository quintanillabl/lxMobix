<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Registro de comisión bancaria</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Comisión bancaria  <small> (${session.empresa})</small>
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
				
				<g:form name="createForm" class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${comisionInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${comisionInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${comisionInstance}">
								<f:field property="fecha" wrapper="bootstrap3"/>
								<f:field property="cuenta" wrapper="bootstrap3" widget-class="form-control "/>
								<f:field property="comision" widget="money" wrapper="bootstrap3"/>
								<f:field property="impuestoTasa" widget="porcentaje" wrapper="bootstrap3" label="Tasa de impuesto(%)"/>
								<f:field property="impuesto" widget="money"  wrapper="bootstrap3"/>
				    			<f:field property="referenciaBancaria" widget-class="form-control " wrapper="bootstrap3"/>
				    			<f:field property="comentario" widget-class="form-control "  wrapper="bootstrap3"/>

								%{-- <f:field property="egreso"  widget-class="form-control">
									<g:select class="form-control"  
										name="${property}" 
										value="${value?.id}"
										from="${disponibles}" 
										optionKey="id"
										required=""/>
								</f:field> --}%
									
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
			
			$('#comision,#cuenta').each(function(){
				$(this).attr("required",true);
			});
			$(".money").autoNumeric('init',{mRound:'B',aSign: '$'});
			$(".tc").autoNumeric('init',{vMin:'0.0000'});
			$(".porcentaje").autoNumeric('init',{vMin:'0.00',vMax:'99.00'});

			$('form[name=createForm]').submit(function(e){
	    		var button=$("#saveBtn");
	    		button.attr('disabled','disabled')
	    		 .html('Procesando...');
	    		$(".money,.porcentaje",this).each(function(index,element){
	    		  var val=$(element).val();
	    		  var name=$(this).attr('name');
	    		  var newVal=$(this).autoNumeric('get');
	    		  $(this).val(newVal);
	    		  console.log('Enviando elemento numerico con valor:'+name+" : "+val+ " new val:"+newVal);
	    		});
	    		return true;
			});	

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