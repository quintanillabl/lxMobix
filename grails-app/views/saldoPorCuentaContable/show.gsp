<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Cuenta ${cuentaContableInstance.id}</title>
</head>
<body>

	<div class="container">
		
		

		<div class="row page-header">
			<div class="col-md-8 ">
				<div class="btn-group">
					<g:if test="${cuentaContableInstance.padre}">
						<g:link action="show" class="btn btn-default " id="${cuentaContableInstance.padre.id}">
						    <i class="fa fa-step-backward"></i> ${cuentaContableInstance.padre}
						</g:link>
					</g:if>
					<g:else>
						<g:link action="index" class="btn btn-default ">
						    <i class="fa fa-step-backward"></i> Saldos
						</g:link>
						<g:link action="index" class="btn btn-default ">
						    <i class="fa fa-plus"></i> Nueva
						</g:link>
					</g:else>
				    <g:link action="actualizarSaldo" class="btn btn-default" id="${saldo.id}">
						<i class="fa fa-cog"></i> Actualizar 
					</g:link>
					<g:if test="${cuentaContableInstance.padre}">
						<g:link action="auxiliar" id="${saldo.id}" class="btn btn-default">
							Auxiliar
						</g:link>
						
						
					</g:if>
					
				</div>
				
				
			</div>
		</div> 

		<div class="row ">
		    
		    <div class="col-md-6  ">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Saldo de Cuenta ${cuentaContableInstance} 
							<g:if test="${cuentaContableInstance.padre}">
								<small>(${cuentaContableInstance.padre})</small>
							</g:if>
						</div>
					  <div class="panel-body">
						<f:with bean="${saldo}">
							<f:display property="saldoInicial" widget-class="form-control"
								cols="col-sm-4" colsLabel="col-sm-4" 
								widget="money"/>
							<f:display property="debe" widget-class="form-control"
								cols="col-sm-4" colsLabel="col-sm-4" 
								widget="money"/>
							<f:display property="haber" widget-class="form-control"
								cols="col-sm-4" colsLabel="col-sm-4" 
								widget="money"/>
							<f:display property="saldoFinal" widget-class="form-control"
								cols="col-sm-4" colsLabel="col-sm-4" 
								widget="money"/>
						</f:with>
					</div>

				</g:form>
				</fieldset>
		    </div>
		    
		    <div class="col-md-6">
		    	<g:if test="${!cuentaContableInstance.detalle}">
		    		<div class="panel panel-primary">
		    			<div class="panel-heading">Sub Cuentas</div>
		    			<table id="grid" class="table table-striped table-bordered table-condensed">
		    				<thead>
		    					<tr>
		    						<th>Clave</th>
		    						<th>S.Inicial</th>
		    						<th>Debe</th>
		    						<th>Haber</th>
		    						<th>S.Final</th>
		    					</tr>
		    				</thead>
		    				<tbody>
		    					<g:each in="${saldos?.sort() }" var="row">
		    						<tr id="${row.id}">
		    							<td >
		    								<g:link  action="show" id="${row.id}">
		    									${fieldValue(bean:row,field:"cuenta")}
		    								</g:link>
		    							</td>
		    							<td>${formatNumber(number:row.saldoInicial,type:'currency')}</td>
		    							<td>${formatNumber(number:row.debe,type:'currency')}</td>
		    							<td>${formatNumber(number:row.haber,type:'currency')}</td>
		    							<td>${formatNumber(number:row.saldoFinal,type:'currency')}</td>
		    							
		    						</tr>
		    					</g:each>
		    				</tbody>
		    			</table>
		    		</div>
		    	</g:if>
		    	<g:else>
		    		<div class="panel panel-primary">
		    			<div class="panel-heading">Movimientos</div>
		    			<table id="grid" class="table table-striped table-bordered table-condensed">
		    				<thead>
		    					<tr>
		    						<th>Fecha</th>
		    						<th>Concepto</th>
		    						<th>Debe</th>
		    						<th>Haber</th>
		    					</tr>
		    				</thead>
		    				<tbody>
		    					<g:each in="${movimientos?.sort() }" var="row">
		    						<tr id="${row.id}">
		    							<td >${formatDate(date:row.poliza.fecha,format:'dd/MM/yyyy')}</td>
		    							<td>${fieldValue(bean:row,field:"concepto")}</td>
		    							<td>${formatNumber(number:row.debe,type:'currency')}</td>
		    							<td>${formatNumber(number:row.haber,type:'currency')}</td>
		    						</tr>
		    					</g:each>
		    				</tbody>
		    			</table>
		    		</div>
		    	</g:else>
		    </div>

		</div><!-- end .row 2 -->

		<g:if test="${flash.message}">
			<div class="row">
				<div class="col-md-12">
					<div class="aler alert-warning">
						${flash.message}
					</div>
				</div>
			</div>
		</g:if> 

		<div class="modal fade" id="auxiliarDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Auxiliar contable</h4>
					</div>
					<g:form action="auxiliar" class="form-horizontal" >
						<g:hiddenField name="id" value="${saldo.id}"/>
						<div class="modal-body">
							<p><strong>Generar Auxiliar ${saldo.cuenta}</strong></p>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-info" name="aceptar"
									value="Generar" />
						</div>
					</g:form>
		
				</div>
				<!-- moda-content -->
			</div>
			<!-- modal-di -->
		</div>	

	</div>

	
</body>
</html>