<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Requisición ${requisicionInstance?.id}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-10 col-md-offset-1">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Requisiciones
				    </g:link>
				    <g:link action="print" class="btn btn-default " id="${requisicionInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
			
				    <g:link action="createPartida" class="btn btn-default " id="${requisicionInstance.id}">
				        <i class="fa fa-plus"></i> Agregar concepto
				    </g:link> 
				    <buttn id="saveBtn" class="btn btn-success">
				    	<i class="fa fa-floppy-o"></i> Salvar
				    </buttn>
				    
				    <g:link action="delete" class="btn btn-danger " id="${requisicionInstance.id}"
				    	onclick="return confirm('Eliminar el gasto: '+${requisicionInstance.id});">
				        <i class="fa fa-trash"></i> Eliminar
				    </g:link>
				</div>
				
				<div class="btn-group">
				    <button type="button" name="reportes"
				            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
				            role="menu">
				            Reportes <span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu">
				        
				    </ul>
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-10 col-md-offset-1 ">
		    	
				<g:form name="updateForm" class="form-horizontal"  action="update" id="${requisicionInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Proveedor: ${requisicionInstance.proveedor} Folio: ${requisicionInstance.folio}</div>
				  		
				  		<div class="panel-body">
				    		<g:hasErrors bean="${requisicionInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${requisicionInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${requisicionInstance}">
								<g:hiddenField name="id" value="${requisicionInstance.id}"/>
								<g:hiddenField name="version" value="${requisicionInstance.id}"/>
								<div class="row">
									<div class="col-md-12">
										<f:field property="aFavor" widget-class="form-control"/>
									</div>
									<div class="col-sm-8">

										<f:field property="pago" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:field property="formaDePago" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:field property="tipo" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:field property="comentario" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>

									</div>
									<div class="col-sm-4 " id="totalesPanel">
										<f:display property="total" widget="money" />
									</div>
								</div>
								
								
							</f:with>
				  		</div>

				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					<th>CxP</th>
				  					<th>Folio</th>
				  					<th>Fecha</th>
				  					<th>Vencimiento</th>
				  					<th>Total</th>
				  					<th>Saldo</th>
				  					<th>Requisitado</th>
				  					<th>Comentario</th>
				  					<th></th>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${requisicionInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						<td>${row.cuentaPorPagar.id}</td>
				  						<td >
				  							<g:link  action="showPartida" id="${row.id}">
				  								${fieldValue(bean:row,field:"cuentaPorPagar.folio")}
				  							</g:link>
				  						</td>
				  						<td><g:formatDate date="${row.cuentaPorPagar.fecha}" format="dd/MM/yyyy"/></td>
				  						<td><g:formatDate date="${row.cuentaPorPagar.vencimiento}" format="dd/MM/yyyy"/></td>
				  						<td>${g.formatNumber(number:row.cuentaPorPagar.total,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.cuentaPorPagar.saldo,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.requisitado,type:'currency')}</td>
				  						<td>${fieldValue(bean:row,field:"comentario")}</td>
				  						<td>
				  							<g:link  action="eliminarPartida" id="${row.id}" 
				  								onclick="return confirm('Eliminar partida');">
				  								<span class="glyphicon glyphicon-trash"></span>
				  							</g:link>
				  						</td>
				  					</tr>
				  				</g:each>
				  			</tbody>
				  		</table>
				  		
				  		<div class="panel-footer">
				  			<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
				  			
				  			%{-- <div class="form-group">
				  				<div class="buttons col-md-4">
				  					<g:submitButton name="Salvar" class="btn btn-primary " />
				  					<g:link action="index" class="btn btn-default"> Cancelar</g:link>
				  				</div>
				  			</div> --}%
				  		</div>
					</div>
				</g:form>
				
			</div>
			
			

		</div><!-- end .row 2 -->
		
		<g:if test="${flash.error}">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="alert alert-danger">
						${flash.error}
					</div>
				</div>
			</div>
		</g:if>
		
	
	</div>
<script type="text/javascript">
	$(function(){
		//$(".money").autoNumeric({wEmpty:'zero',aSep:""});
		
		$("#totalesPanel :input").each(function(){
		 	var input = $(this); // This is the jquery object of the input, do what you will
		 	input.removeAttr("type")
		 	.prop('type','text')
		 	.prop('disabled','disabled')
		 	.addClass('form-control')
		 	.addClass('text-right');
		});

		$("#saveBtn").click(function(){
			console.log('Salvar la forma');
			$("form[name=updateForm]").submit();
		});

		$('form[name=updateForm]').submit(function(e){
			console.log("Desablidatndo submit button....");
    		$(this).children('input[type=submit]').attr('disabled', 'disabled');
    		var button=$("#saveBtn");
    		button.attr('disabled','disabled')
    		//.attr('value','Procesando...');
    		.html('Procesando...')
    		//e.preventDefault(); 
    		return true;
		});
		
	});
</script>
	
</body>
</html>