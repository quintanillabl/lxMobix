<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Pago ${pagoInstance?.id}</title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-12 ">
				<div class="btn-group">
				    
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Pagos
				    </g:link>

				   
				    
			        <sec:ifAllGranted roles="TESORERIA">
			        	
			        	<g:if test="${!pagoInstance.aplicaciones}">
			        	    <lx:deleteButton bean="${pagoInstance}" />
			        	    <g:link  action="aplicar" class="btn btn-default " id="${pagoInstance.id}" onclick="return confirm('Aplicar el pago?');">
			        	    	<i class="fa fa-plus"></i> Aplicar
			        	    </g:link>
			        	</g:if>
			        	<g:else>
			        		<g:link  action="cancelarAplicacion" class="btn btn-danger " id="${pagoInstance.id}" 
			        			onclick="return confirm('Cancelar aplicaciones del pago?');">
			        			<i class="fa fa-plus"></i> Cancelar aplicación
			        		</g:link>
			        	</g:else>
			        	
			    	</sec:ifAllGranted>
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	
				<g:form name="updateForm" class="form-horizontal" action="update" method="PUT">	
					<g:hiddenField name="id" value="${pagoInstance.id}"/>
					<g:hiddenField name="version" value="${pagoInstance.version}"/>
					<div class="panel panel-primary">
						<div class="panel-heading">Requisición: ${pagoInstance.requisicion}</div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${pagoInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${pagoInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${pagoInstance}">
								<div class="col-sm-6">
									<f:display property="requisicion" wrapper="bootstrap3"/>
									<f:display property="aFavor" wrapper="bootstrap3"/>
									<f:display property="fecha" wrapper="bootstrap3" />
									<f:display property="folio" widget-class="form-control" wrapper="bootstrap3"/>
									<f:display property="cuenta" wrapper="bootstrap3" widget-class="form-control"/>
									<f:display property="formaDePago" wrapper="bootstrap3" widget-class="form-control"/>
									<f:display property="referencia" wrapper="bootstrap3" widget-class="form-control"/>
									<f:display property="comentario" widget-class="form-control" wrapper="bootstrap3"/>
								</div>

								<div class="col-sm-6" id="totalesPanel">
									<f:display property="importe" widget="money" wrapper="bootstrap3"/>
									<f:display property="aplicado" widget="money" wrapper="bootstrap3"/>
									<f:display property="disponible" widget="money" wrapper="bootstrap3"/>
									<f:display property="creadoPor" widget-class="form-control" wrapper="bootstrap3"/>
									
									<g:if test="${pagoInstance.formaDePago.toString() == 'TRANSFERENCIA'}">
										<legend>Transferencia: </legend>
										<f:field property="bancoDestino" wrapper="bootstrap3">
											<g:hiddenField id="bancoDestinoId" name="bancoDestino.id" value="${value}" />
											<input 
												id="bancoDestinoField" 
												type="text" 
												class="form-control" 
												value="${value}" 
												placeholder="Seleccione un Banco destino " >
											</input>
										</f:field>
										<f:field property="cuentaDestino" wrapper="bootstrap3" widget-class="form-control" />
										
									</g:if>
								</div>
								
								
							</f:with>
				  		</div>

				  		<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<g:submitButton id="saveBtn" name="Actualizar" class="btn btn-primary " />
						  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
						  		</div>
						  	</div>
						</div>

					</div>
				</g:form>
				
			</div>

		</div><!-- end .row 2 -->

		
		<g:if test="${flash.error}">
			<div class="row">
				<div class="col-md-12">
					<div class="alert alert-danger">
						${flash.error}
					</div>
				</div>
			</div>
		</g:if>

		
	
	</div>

	<script>
		$(function(){
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