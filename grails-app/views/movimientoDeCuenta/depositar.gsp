<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Movimiento de cuenta</title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Registro de Deposito  <small> (${session.empresa})</small>
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
				
				<g:form class="form-horizontal" action="saveDeposito" >	

					<div class="panel panel-success">
						<div class="panel-heading">Deposito  ( Periodo:${session.periodoTesoreria})</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${movimientoDeCuentaInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${movimientoDeCuentaInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${movimientoDeCuentaInstance}">
								<div class="col-sm-12">
									<f:field property="cuenta" wrapper="bootstrap3" widget-class="form-control"/>
									
									<f:field property="fecha" wrapper="bootstrap3">
										<input 
											id="fecha" 
											type="text" 
											name="fecha"  
											class="form-control " 
											/>
									</f:field>
									<f:field property="importe" widget="money" wrapper="bootstrap3"/>
									<f:field property="concepto" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="referencia" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="comentario" wrapper="bootstrap3" widget-class="form-control"/>
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
			$(".money").autoNumeric({wEmpty:'zero',aSep:"",lZero: 'deny'});

				$("#fecha").datepicker({
					changeMonth: false,
			      	changeYear: false,
			      	showAnim: "fadeIn",
			      	hideIfNoPrevNext: true,
			      	minDate:"${formatDate(date:session.periodoTesoreria.toPeriodo().fechaInicial,format:'dd/MM/yyyy')}",
			      	maxDate:"${formatDate(date:session.periodoTesoreria.toPeriodo().fechaFinal,format:'dd/MM/yyyy')}",
			      	showOptions: { direction: "up" }
				});
		});

	</script>
	
</body>
</html>