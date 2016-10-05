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
			<div class="col-md-10 col-md-offset-1">
				
				<g:form name="updateForm" class="form-horizontal" action="update" method="PUT" >	

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
								<f:field property="fecha" wrapper="bootstrap3"/>
								<f:display property="cuenta" wrapper="bootstrap3" widget-class="form-control "/>
								<f:field property="comision" widget="money" wrapper="bootstrap3"/>
								<f:field property="impuestoTasa" widget="porcentaje" wrapper="bootstrap3" label="Tasa de impuesto(%)"/>
								<f:field property="impuesto" widget="money"  wrapper="bootstrap3"/>
				    			<f:field property="referenciaBancaria" widget-class="form-control " wrapper="bootstrap3"/>
				    			<f:field property="gasto" wrapper="bootstrap3">
				    				<g:hiddenField id="gastoId" name="gasto.id" />
				    				<input type="text" value="${value}" id="gastoField" class="form-control">
				    			</f:field>
				    			<f:field property="comentario" widget-class="form-control "  wrapper="bootstrap3"/>
							</f:with>
						</div>
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-8">
					  			<g:submitButton name="Actualizar" class="btn btn-primary " />
					  			<a href="" class="btn btn-danger " data-toggle="modal" data-target="#deleteDialog"><i class="fa fa-trash"></i> Eliminar</a> 
					  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
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

		<div class="modal fade" id="deleteDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Eliminar el registro ${comisionInstance.id}</h4>
					</div>

					<g:form action="delete" class="form-horizontal" method="DELETE">
						<g:hiddenField name="id" value="${comisionInstance.id}"/>
						<div class="modal-body">
							<p><small>${comisionInstance}</small></p>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-danger" name="aceptar"
									value="Eliminar" />
						</div>
					</g:form>


				</div>
				<!-- moda-content -->
			</div>
			<!-- modal-di -->
		</div>

	</div>

<script type="text/javascript">
	$(function(){
		$('#comision,#cuenta').each(function(){
			$(this).attr("required",true);
		});
		$(".money").autoNumeric('init',{mRound:'B',aSign: '$'});
		$(".tc").autoNumeric('init',{vMin:'0.0000'});
		$(".porcentaje").autoNumeric('init',{vMin:'0.00',vMax:'99.00'});

		$('form[name=updateForm]').submit(function(e){
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

		$("#gastoField").autocomplete({
			source:'<g:createLink controller="comision" action="getCxPDisponibles"/>',
			minLength:1,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#gastoId").val(ui.item.id);
			}
		});
	});
</script>
	
</body>
</html>