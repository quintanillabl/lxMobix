<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Venta</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h3>Alta de venta  <small> (${session.empresa})</small>
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
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${ventaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${ventaInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${ventaInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="cliente" widget-class="form-control" widget-autofocus="on"/>
							<f:field property="fecha"/>
							<f:field property="tipo" widget-class="form-control"/>
							<f:field property="formaDePago" >
								<g:select class="form-control "  
									name="${property}" 
									value="${value}"
									from="${['CHEQUE','TRANSFERENCIA','EFECTIVO','NO DEFINIDO']}"/>
							</f:field>
							<f:field property="usoCfdi" >
								<g:select class="form-control "  
									name="${property}" 
									value="${value}"
									from="${['G01','G02','G03','P01','I01']}"/>
							</f:field>
							<f:field property="metodoDePago" >
								<g:select class="form-control "  
									name="${property}" 
									value="${value}"
									from="${['PPD','PUE']}"/>
							</f:field>
							%{-- <f:field property="fecha" >
								<input type="text" class="form-control fechaField" value="${g.formatDate(date:ventaInstance.fecha,format:'dd/MM/yyyy')}">
							</f:field> --}%
							<f:field property="comentario" widget-class="form-control"/>
							
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-4">
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
			$(".fechaField").datepicker({});
		});
		
	</script>
</body>
</html>