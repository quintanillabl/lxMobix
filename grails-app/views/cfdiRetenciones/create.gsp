<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Comprobante de retención y pago</title>
	<asset:javascript src="forms/autoNumeric.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de comprobante de retención y pago  <small> (${session.empresa})</small>
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
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${cfdiRetencionesInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${cfdiRetencionesInstance}" as="list" />
						    		</ul>
						    	</div>
						    	
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${cfdiRetencionesInstance}">
								<div class="col-sm-7">
									<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
									<f:field property="fecha" wrapper="bootstrap3"/>
									<f:display property="empresa" wrapper="bootstrap3"/>
									<f:field property="receptor" wrapper="bootstrap3" widget-class="form-control">
										<input 
											id="receptor" 
											type="text" 
											name="${property}"  
											class="form-control" 
											value="${value}">
										</input>
									</f:field>
									<f:field property="receptorRfc" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="receptorCurp" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="nacional" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="registroTributario" wrapper="bootstrap3" widget-class="form-control"/>

									
									<f:field property="tipoDeRetencion" wrapper="bootstrap3">
										<g:select class="form-control"  
											name="${property}" 
											value="${value?.id}"
											from="${com.luxsoft.cfdi.retenciones.TipoDeRetencion.list()}" 
											optionKey="id" 
											noSelection="[null:'Seleccione un tipo']"
											/>
									</f:field>
									<f:field property="retencionDescripcion" wrapper="bootstrap3" widget-class="form-control"/>
									


								</div>

								<div class="col-sm-5">
									<f:field property="mesInicial" cols="col-sm-8" colsLabel="col-sm-4" widget-class="form-control"/>
									<f:field property="mesFinal" cols="col-sm-8" colsLabel="col-sm-4" widget-class="form-control"/>
									<f:field property="ejercicio" cols="col-sm-8" colsLabel="col-sm-4" widget-class="form-control"/>
									<f:field property="total" widget="money" 
									cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="totalGravado" widget="money" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="totalExcento" widget="money" cols="col-sm-8" colsLabel="col-sm-4"/>
									%{-- <f:field property="totalRetenido" widget="money" cols="col-sm-8" colsLabel="col-sm-4"/> --}%

									

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
			
			$("#totalesPanel :input").each(function(){
			 	var input = $(this); // This is the jquery object of the input, do what you will
			 	input.removeAttr("type")
			 	.prop('type','text')
			 	//.prop('disabled','disabled')
			 	.addClass('form-control')
			 	.addClass('text-right');
			 	//input.autoNumeric({wEmpty:'zero',aSep:""});
			});
			$(".money").autoNumeric({wEmpty:'zero',aSep:"",lZero: 'deny'});
			$(".porcentaje").autoNumeric({vMin: '0', vMax: '99.9999',lZero: 'deny'});

			$("#receptor").autocomplete({
				source:'<g:createLink  action="buscarReceptores"/>',
				minLength:1,
				select:function(e,ui){
					console.log('Valor seleccionado: '+ui.item.nombre+ " Nacional: "+ui.item.nacional);
					$("#receptorRfc").val(ui.item.rfc);
					$("#nacional").val(ui.item.nacional);
					$( "#nacional" ).prop("checked",  ui.item.nacional);
					
				}
			});

		})
	</script>
	
</body>
</html>