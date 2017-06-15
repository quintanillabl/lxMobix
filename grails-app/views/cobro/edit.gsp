<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Cobro ${cobroInstane?.id}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-12 ">
				<div class="btn-group">
				    
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Cobros
				    </g:link>

				    <g:link action="print" class="btn btn-default " id="${cobroInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 

				    <g:if test="${!cobroInstance.aplicaciones}">
			    	    <sec:ifAllGranted roles="TESORERIA">
			    	    	<lx:deleteButton bean="${cobroInstance}" />
			    		</sec:ifAllGranted>
				    	
				    </g:if>
				    <g:if test="${cobroInstance.disponible}">
			    	    <sec:ifAllGranted roles="GASTOS">
			    	    	<g:link  action="createAplicacion" class="btn btn-default " id="${cobroInstance.id}">
			    	    		<i class="fa fa-plus"></i> Agregar aplicacion
			    	    	</g:link>
			    		</sec:ifAllGranted>
				    </g:if>
				    <button id="saveBtn" class="btn btn-success">
				    	<i class="fa fa-floppy-o"></i> Actualizar
				    </button>
				    
				    
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	
				<g:form name="updateForm" class="form-horizontal"  action="update" id="${cobroInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Cliente: ${cobroInstance.cliente} Folio: ${cobroInstance.id}</div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${cobroInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${cobroInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${cobroInstance}">
								<g:hiddenField name="id" value="${cobroInstance.id}"/>
								<g:hiddenField name="version" value="${cobroInstance.version}"/>

								<div class="col-sm-6">
									<f:display property="cliente" wrapper="bootstrap3"/>
									<f:display property="fecha" wrapper="bootstrap3" />
									<f:display property="formaDePago" wrapper="bootstrap3" widget-class="form-control"/>
									<f:display property="referencia" wrapper="bootstrap3" widget-class="form-control"/>
									<f:display property="banco" wrapper="bootstrap3" widget-class="form-control"/>
									<f:field property="comentario" widget-class="form-control" wrapper="bootstrap3"/>
								</div>

								<div class="col-sm-6" id="totalesPanel">
									<f:display property="importe" widget="money" wrapper="bootstrap3"/>
									<f:display property="aplicado" widget="money" wrapper="bootstrap3"/>
									<f:display property="disponible" widget="money" wrapper="bootstrap3"/>
									
								</div>
								
							</f:with>
				  		</div>
				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					<th>Documento</th>
				  					<th>Fecha Docto</th>
				  					<th>Importe Docto</th>
				  					<th>Fecha</th>
				  					<th>Aplicado</th>
				  					<th>Saldo Docto</th>
				  					<th>Comentario</th>
				  					<th>CFDI pago</th>
				  					<th>D</th>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${cobroInstance.aplicaciones}" var="row">
				  					<tr id="${row.id}">
				  						<td>${fieldValue(bean:row,field:"cuentaPorCobrar.folio")}</td>
				  						<td>${fieldValue(bean:row,field:"cuentaPorCobrar.fecha")}</td>
				  						<td>${formatNumber(number:row.cuentaPorCobrar.total,type:'currency')}</td>
				  						<td>${formatDate(date:row.fecha,format:'dd/MM/yyyy')}</td>
				  						
				  						<td>${g.formatNumber(number:row.importe,type:'currency')}</td>
				  						<td>${formatNumber(number:row.cuentaPorCobrar.saldo,type:'currency')}</td>
				  						<td>${fieldValue(bean:row,field:"comentario")}</td>
				  						<td>
				  							<g:if test="${row.cuentaPorCobrar.cfdi.versionCfdi == '3.3'}">
				  								<g:link  action="generarCfdi" id="${row.id}" 
				  									onclick="return confirm('Generar CFDI de recepción del pago al documento: ${row.cuentaPorCobrar.folio}');">
				  								Generar
				  							</g:link>
				  							</g:if>
				  							<g:else>
				  								${fieldValue(bean:row,field:"cfdiCobro.id")}
				  							</g:else>
				  							
				  						</td>
				  						<td>
				  							<g:link  action="deleteAplicacion" id="${row.id}" 
				  								onclick="return confirm('Eliminar aplicacion para el docto: ${row.cuentaPorCobrar.folio}');">
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
<script type="text/javascript">
	$(function(){
		//$(".money").autoNumeric({wEmpty:'zero',aSep:""});
		
		$("#totalesPanel :input").each(function(){
		 	var input = $(this); // This is the jquery object of the input, do what you will
		 	input.removeAttr("type").prop('type','text')
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
    		e.preventDefault(); 
    		return true;
		});
		
	});
</script>
	
</body>
</html>