<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Pago</title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de pago  <small> (${session.empresa})</small>
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
			
			<div class="col-md-8 col-md-offset-2 ">
				
				<g:form name="createForm" class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body" >
						    <g:hasErrors bean="${pagoInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${pagoInstance}" as="list" />
						    		</ul>
						    	</div>
						    	
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${pagoInstance}">
								<f:field property="requisicion" wrapper="bootstrap3">
									<g:hiddenField id="requisicion" name="requisicion.id" value="${value}" />
									<input 
										id="requisicionField" 
										type="text" 
										class="form-control" 
										value="${value}" 
										placeholder="Seleccione la requisición a pagar"
										required autofocus="true">
									</input>
								</f:field>
								<f:field property="cuenta"  wrapper="bootstrap3" widget-class="form-control"/>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="formaDePago">F.Pago</label>
									<div class="col-sm-9">
										<input id="formaDePago" type="text" readonly="true" class="form-control " >
									</div>
								</div>
								<f:field property="fecha" wrapper="bootstrap3" widget-class="form-control"/>
								
								<f:field property="importe" wrapper="bootstrap3" widget="money" />
								<f:field property="aFavor" wrapper="bootstrap3" widget-class="form-control" widget-readOnly="readOnly"/>
								<f:field property="bancoDestino" wrapper="bootstrap3">
									<g:hiddenField id="bancoDestinoId" name="bancoDestino.id" value="${value}" />
									<input 
										id="bancoDestinoField" 
										type="text" 
										class="form-control" 
										value="${value}" 
										placeholder="Seleccione un Banco destino " disabled>
									</input>
								</f:field>
								<f:field property="cuentaDestino" wrapper="bootstrap3" widget-class="form-control" widget-disabled="true"/>
								<f:field property="referencia" wrapper="bootstrap3" widget-class="form-control"/>
								<f:field property="comentario" wrapper="bootstrap3" widget-class="form-control"/>
							</f:with>
					  	</div>
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton id="saveBtn" name="Salvar" class="btn btn-primary " />
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
			$("#importe").attr("required","required")
				.attr("readonly","readonly")
				;
			$(".money").autoNumeric('init',{wEmpty:'zero',mRound:'B',aSign: '$'});
			$('form[name=createForm]').submit(function(e){
	    		var button=$("#saveBtn");
	    		button.attr('disabled','disabled')
	    		.html('Procesando...');
	    		$(".money,.porcentaje,.tc",this).each(function(index,element){
	    		  var val=$(element).val();
	    		  var name=$(this).attr('name');
	    		  var newVal=$(this).autoNumeric('get');
	    		  $(this).val(newVal);
	    		  console.log('Enviando elemento numerico con valor:'+name+" : "+val+ " new val:"+newVal);
	    		});
	    		//e.preventDefault(); 
	    		return true;
			});
			$("#requisicionField").autocomplete({
				source:'<g:createLink  action="requisicionesPendientes"/>',
				minLength:1,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.id);
					$("#requisicion").val(ui.item.id);
					$("#importe").autoNumeric('set',ui.item.total);
					$("#aFavor").val(ui.item.afavor);
					$("#fecha").val(ui.item.pago);
					var fp = ui.item.formaDePago;
					$("#formaDePago").val(fp);
					if(fp === 'TRANSFERENCIA'){
						$("#bancoDestinoField").prop( "disabled", false );
						$("#bancoDestinoField").prop( "required", true );
						$("#cuentaDestino").prop( "disabled", false );
						$("#cuentaDestino").prop( "required", true );
					} else {
						$("#bancoDestinoField").prop( "disabled", true );
						$("#bancoDestinoField").prop( "required", false );
						$("#cuentaDestino").prop( "disabled", true );
						$("#cuentaDestino").prop( "required", false );
					}
				}
			});

			$("#bancoDestinoField").autocomplete({
				source:'<g:createLink  controller="bancoSat" action="bancos"/>',
				minLength:1,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.id);
					$("#bancoDestinoId").val(ui.item.id);
					
				}
			});

			
		});
	</script>
	
</body>
</html>