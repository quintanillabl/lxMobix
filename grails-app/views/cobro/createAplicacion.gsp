<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Aplicacion de cobro</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<g:link action="edit" id="${aplicacionDeCobroInstance.pago.id}">
						<h3>Cobro:${aplicacionDeCobroInstance.pago.id}  (${aplicacionDeCobroInstance.pago.cliente})
							<small>  </small>
							<strong>Disponible: ${g.formatNumber(number:aplicacionDeCobroInstance.pago.disponible,type:'currency')}</strong>
							<g:if test="${flash.message}">
								<small><span class="label label-warning ">${flash.message}</span></small>
							</g:if> 
							<g:if test="${flash.error}">
								<small><span class="label label-danger ">${flash.error}</span></small>
							</g:if> 
						</h3>
					</g:link>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-8 col-md-offset-2">
				
				<g:form name="createForm" class="form-horizontal" action="saveAplicacion"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Aplicación</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${aplicacionDeCobroInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${aplicacionDeCobroInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
						    <g:hiddenField name="cobro.id" value="${aplicacionDeCobroInstance?.pago?.id}"/>
							<f:with bean="${aplicacionDeCobroInstance}">
								<div class="col-sm-12">
									<f:field property="cuentaPorCobrar" wrapper="bootstrap3" widget-class="form-control">
										<g:hiddenField id="factura" name="cuentaPorCobrar.id"  />
										<input id="facturaField" class="form-control" type="text" required="true"/>
									</f:field>
									<f:field property="fecha" wrapper="bootstrap3"/>
									<f:field property="importe" widget="money" wrapper="bootstrap3"/>
									<f:field property="comentario" wrapper="bootstrap3" widget-class="form-control"/>
								</div>
								
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton id="saveBtn" name="Aplicar" class="btn btn-primary " />
						  			<g:link action="edit" class="btn btn-default" id="${aplicacionDeCobroInstance.pago.id}"> Cancelar</g:link>
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
			
			$(".money").autoNumeric('init',{wEmpty:'zero',mRound:'B',aSign: '$'});
			$("#facturaField").autocomplete({
				source:"${ g.createLink(action:'facturasPendientes',id:aplicacionDeCobroInstance.pago.id) }",
				data:{id:"${aplicacionDeCobroInstance.pago.id}"},
				minLength:1,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.id);
					$("#factura").val(ui.item.id);
					var saldo=ui.item.saldo;
					var disponible=ui.item.disponible;
					var importe=saldo
					if(disponible<saldo)
						importe=disponible
					$("#importe").autoNumeric('set',importe);
				}
			});
			$('form[name=createForm]').submit(function(e){
				
	    		var button=$("#saveBtn");
	    		button.attr('disabled','disabled')
	    		 .html('Procesando...');
	    		$(".money",this).each(function(index,element){
	    		  var val=$(element).val();
	    		  var name=$(this).attr('name');
	    		  var newVal=$(this).autoNumeric('get');
	    		  $(this).val(newVal);
	    		  console.log('Enviando elemento numerico con valor:'+name+" : "+val+ " new val:"+newVal);
	    		});
	    		//e.preventDefault(); 
	    		return true;
			});
		});
	</script>
	
</body>
</html>