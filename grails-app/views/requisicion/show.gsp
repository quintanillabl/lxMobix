<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Requisición ${requisicionInstance?.folio}</title>
	
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
				    <g:link action="edit" class="btn btn-default " id="${requisicionInstance.id}">
				        <i class="fa fa-pencil"></i> Editar
				    </g:link> 
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-10 col-md-offset-1 ">
		    	
				<g:form name="updateForm" class="form-horizontal"   id="${requisicionInstance.id}">	

					<div class="panel panel-primary">
						<div class="panel-heading">Folio: ${requisicionInstance.folio} (${requisicionInstance.proveedor} )</div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${requisicionInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${requisicionInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${requisicionInstance}">
								<div class="row">
									<div class="col-md-12">
										<f:display property="aFavor" widget-class="form-control"/>
									</div>
									<div class="col-sm-8">

										<f:display property="pago" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:display property="formaDePago" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:display property="tipo" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>
										<f:display property="comentario" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-2 col-sm-offset-1"/>

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
				  					
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${requisicionInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						<td>${row.cuentaPorPagar.id}</td>
				  						<td >${fieldValue(bean:row,field:"cuentaPorPagar.folio")}</td>
				  						<td><g:formatDate date="${row.cuentaPorPagar.fecha}" format="dd/MM/yyyy"/></td>
				  						<td><g:formatDate date="${row.cuentaPorPagar.vencimiento}" format="dd/MM/yyyy"/></td>
				  						<td>${g.formatNumber(number:row.cuentaPorPagar.total,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.cuentaPorPagar.saldo,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.requisitado,type:'currency')}</td>
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

	
</body>
</html>