<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Concepto de poliza</title>
	
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
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
				<div class="panel panel-primary">
					<div class="panel-heading">Concepto para póliza ${polizaInstance.tipo} ${polizaInstance.folio} (${polizaInstance.mes} - ${polizaInstance.ejercicio})</div>
				  	
					<div class="panel-body ">
					
						<legend>  <span id="conceptoLabel">Concepto ${polizaDetInstance.cuenta}</span></legend>
						<g:hasErrors bean="${polizaDetInstance}">
							<div class="alert alert-danger">
								<ul class="errors" >
									<g:renderErrors bean="${polizaDetInstance}" as="list" />
								</ul>
							</div>
						</g:hasErrors>

						
						<form name="updateForm"  class="form-horizontal">	
							<g:hiddenField name="poliza.id" value="${polizaInstance.id}"/>
							<f:with bean="${polizaDetInstance}">
								<f:display property="debe" widget="money" cols="col-sm-8" colsLabel="col-sm-3"/>
								<f:display property="haber" widget="money" cols="col-sm-8" colsLabel="col-sm-3"/>
								<f:display property="concepto" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
								<f:display property="asiento" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
								<f:display property="referencia" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
							</f:with>
						</form>
					
					</div>					
				 
					<div class="panel-footer">
						<div class="btn-group">
							<g:link class="btn btn-default btn-sm" controller="poliza" action="edit" id="${polizaInstance.id}" >
								<span class="glyphicon glyphicon-hand-left"></span> Regresar
							</g:link>
						</div>
					</div><!-- end .panel-footer -->

				</div>
			</div>
		</div> <!-- end .row 2 -->

	</div>
	
	
</body>
</html>