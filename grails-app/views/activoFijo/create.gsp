<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Registro de activo fijo</title>
	<asset:javascript src="forms/autoNumeric.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de activo fijo  <small> (${session.empresa})</small>
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
						    <g:hasErrors bean="${activoFijoInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${activoFijoInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${activoFijoInstance}">
								
								<f:field property="gastoDet" wrapper="bootstrap3" label="Gasto">
									<input type="text" name="gastoDet.id" value="" class="form-control">
								</f:field>

								<f:field property="adquisicion" wrapper="bootstrap3"/>
								
								<f:field property="cuentaContable"  wrapper="bootstrap3">
									<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${gastoDetInstance?.cuentaContable?.id}"/>
									<input type="text" id="cuentaField" class="form-control" value="${gastoDetInstance?.cuentaContable}" >
								</f:field>

								<f:field property="valorOriginal" 
									widget="money" wrapper="bootstrap3"/>

								<f:field property="valorDepreciable" label="Depreciable" 
									widget="money" wrapper="bootstrap3"/>

								

								<f:field property="tipoDeDepreciacion" wrapper="bootstrap3"/>

								<f:field property="depreciacionTasa" 
									widget="porcentaje" wrapper="bootstrap3"/>
								
								<f:field property="descripcion" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>

				    			<f:field property="factura" label="Factura"
				    				widget-class="form-control "  
				    				wrapper="bootstrap3"/>

				    			<f:field property="serie" label="No Serie"
				    				widget-class="form-control "  wrapper="bootstrap3"/>

				    			<f:field property="modelo" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>

				    			<f:field property="consignatario" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>

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
			
			$('#cuentaContable,#valorOriginal,#valorDepreciable,#depreciacionTasa').each(function(){
				$(this).attr("required",true);
			});

			$(".money").autoNumeric('init',{mRound:'B',aSign: '$'});
			$(".tc").autoNumeric('init',{vMin:'0.0000'});
			$(".porcentaje").autoNumeric('init',{vMin:'0.00',vMax:'99.00'});

			$("#cuentaField").autocomplete({
				source:'<g:createLink controller="cuentaContable" action="getCuentasDeDetalleJSON"/>',
				minLength:3,
				select:function(e,ui){
					$("#cuentaId").val(ui.item.id);
				}
			});

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