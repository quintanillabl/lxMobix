<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Gasto ${gastoInstane?.id}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-6 ">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Gastos
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <g:link action="print" class="btn btn-default " id="${gastoInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
				    <g:link action="edit" class="btn btn-default " id="${gastoInstance.id}">
				        <i class="fa fa-pencil"></i> Editar
				    </g:link>
				   
				</div>
				
				
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Id: ${gastoInstance.id}</div>
				  		<div class="panel-body">
				  			<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
				    		<g:hasErrors bean="${gastoInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${gastoInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>
				    		<f:with bean="${gastoInstance}">
				    			<g:hiddenField name="gasto.id" value="${gastoInstance.id}"/>
				    			<div class="col-sm-8">
				    				<div class="col-sm-6">
				    					<f:field property="fecha" cols="col-sm-8" colsLabel="col-sm-4"/>
				    					<f:field property="serie" widget-class="form-control "  cols="col-sm-8" colsLabel="col-sm-4"/>
				    					
				    					<f:field property="moneda" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
				    					<f:field property="uuid" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4" widget-readonly="readonly"/>

				    				</div>
				    				<div class="col-sm-6">
				    					<f:field property="vencimiento"  cols="col-sm-9" colsLabel="col-sm-3"/>
				    					<f:field property="folio" widget-class="form-control "  cols="col-sm-9" colsLabel="col-sm-3"/>
				    					
				    					<f:field property="tipoDeCambio" widget-class="form-control" cols="col-sm-9" colsLabel="col-sm-3"/>
				    				</div>
				    				<div class="col-sm-12">
				    					<f:field property="cuentaContable" widget-class="form-control" 
				    						label="Cta" />
				    					<f:field property="comentario" widget-class="form-control"  />
				    				</div>
				    			</div>
				    			

				    			<div class="col-sm-4" id="totalesPanel">
				    				<f:field property="importe"  cols="col-sm-6" colsLabel="col-sm-4"/>
				    				<f:field property="descuento" cols="col-sm-6" colsLabel="col-sm-4"/>
				    				<f:field property="subTotal" cols="col-sm-6" colsLabel="col-sm-4"/>
				    				<f:field property="impuesto"  cols="col-sm-6" colsLabel="col-sm-4"/>
				    				<f:field property="total"  cols="col-sm-6" colsLabel="col-sm-4"/>
				    			</div>
				    			
				    			
				    		</f:with>
				  		</div>
				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					<th>Concepto</th>
				  					<th>Descripcion</th>
				  					<th>Unidad</th>
				  					<th>Cantidad</th>
				  					<th>Valor unitario</th>
				  					<th>Importe</th>
				  					
				  					<th>Cuental</th>
				  					<th>Comentario</th>
				  					
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${gastoInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						<td >
				  							<g:link  controller="gastoDet" action="show" id="${row.id}">
				  								${fieldValue(bean:row,field:"concepto")}
				  							</g:link>
				  						</td>
				  						<td>
				  							<g:link  controller="gastoDet" action="show" id="${row.id}">
				  								${fieldValue(bean:row,field:"descripcion")}
				  							</g:link>
				  						</td>
				  						<td>${fieldValue(bean:row,field:"unidad")}</td>
				  						<td>${formatNumber(number:row.cantidad,format:'##.##')}</td>
				  						<td>${g.formatNumber(number:row.valorUnitario,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.importe,type:'currency')}</td>
				  						
				  						<td>${fieldValue(bean:row,field:"cuentaContable.clave")}</td>
				  						<td>${fieldValue(bean:row,field:"comentario")}</td>
				  						
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
				</fieldset>
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
		
	});
</script>
	
</body>
</html>