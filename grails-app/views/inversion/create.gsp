<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Registro de inversión bancaria</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de inversión bancaria  <small> (${session.empresa})</small>
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
						    <g:hasErrors bean="${inversionInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${inversionInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${inversionInstance}">
								
								<f:field property="fecha" wrapper="bootstrap3"/>

								%{-- <f:field property="rendimientoFecha" wrapper="bootstrap3"/> --}%

								<f:field property="vencimiento" wrapper="bootstrap3"/>
								
								<f:field property="cuentaOrigen" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:field property="cuentaDestino" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:field property="importe" 
									widget="money" wrapper="bootstrap3"/>

								<f:field property="rendimientoReal" 
									widget="money" wrapper="bootstrap3"/>

								<f:field property="importeIsr" label="ISR"
									widget="money" wrapper="bootstrap3"/>
									
								
								%{-- <f:field property="tasa" 
									widget="porcentaje" 
									wrapper="bootstrap3" 
									label="Tasa de IVA(%)"/> --}%

								%{-- <f:field property="tasaIsr" 
									widget="porcentaje" 
									wrapper="bootstrap3" 
									label="Tasa ISR(%)"/> --}%

								%{-- <f:field property="rendimientoImpuesto" 
									widget="money" wrapper="bootstrap3"/> --}%

				    			<f:field property="plazo" widget="numeric"
				    				wrapper="bootstrap3"/>

				    			<f:field property="comentario" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
								
									
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
			
			$('#cuentaOrigen,#cuentaDestino,#importe').each(function(){
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
		});
	</script>
	
</body>
</html>