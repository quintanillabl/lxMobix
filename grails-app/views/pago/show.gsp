<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Pago ${pagoInstance?.id}</title>
	
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
		    	
				<form class="form-horizontal" >	

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
									<g:if test="${pagoInstance.cuenta.tipo=='CHEQUES'}">
										<legend>Cheque: </legend>
										<f:display property="cheque.cuenta" />
										<f:display property="cheque.folio" />
										<f:display property="cheque.impresion" />
										<div class="form-group">
											<div class="col-sm-10 col-sm-offset-2">
												<sec:ifAllGranted roles="TESORERIA">
													<g:if test="${!pagoInstance.cheque}">
														<g:link action="generarCheque" class="btn btn-primary " id="${pagoInstance.id}" onclick="return confirm('Generar cheque?');">
														    <i class="fa fa-list-alt"></i> Generar
														</g:link> 
													</g:if>
													<g:else>
														<div class="btn-group">
															<lx:printButton action="imprimirCheque" id="${pagoInstance.id}"/>
															<g:link action="imprimirPoliza" class="btn btn-default" 
																	id="${pagoInstance.id}" >
																    Imprimir Poliza
															</g:link> 
															<g:if test="${!pagoInstance.cheque.impresion}">
																<a class="btn btn-danger " 
																	data-toggle="modal" 
																	data-target="#cancelDialog">
																	<i class="fa fa-ban"></i> Cancelar
																</a> 
															</g:if>
															
															
														</div>
														
													</g:else>
												</sec:ifAllGranted>
												
											</div>
										</div>
									</g:if>
								</div>
								
								
							</f:with>
				  		</div>
				  		<div class="col-sm-12">
				  			<legend>Aplicaciones</legend>
				  			
				  		</div>
				  		<table id="grid" class="table">
				  			<thead>
				  				<tr>
				  					<th>CxP</th>
				  					<th>Folio</th>
				  					<th>Cfdi</th>
				  					<th>Proveedor</th>
				  					<th>Fecha (CxP)</th>
				  					<th>Total (CxP)</th>
				  					<th>Saldo (CxP)</th>
				  					<th>Fecha</th>
				  					<th>Importe</th>
				  					<th>Comentario</th>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${pagoInstance.aplicaciones}" var="row">
				  					<tr id="${row.id}">
				  						
				  						<td>${row.cuentaPorPagar.id}</td>
				  						<td>${row.cuentaPorPagar.folio}</td>
				  						<td>
				  							<abbr title="${row.cuentaPorPagar.uuid}">
				  							${org.apache.commons.lang.StringUtils.substringAfterLast(row.cuentaPorPagar.uuid,'-')}
				  							</abbr>
				  						</td>
				  						<td>${row.cuentaPorPagar.proveedor.nombre}</td>
				  						<td>${formatDate(date:row.cuentaPorPagar.fecha,format:'dd/MM/yyyy')}</td>
				  						<td>${formatNumber(number:row.cuentaPorPagar.total,type:'currency')}</td>
				  						<td>${formatNumber(number:row.cuentaPorPagar.saldoActual,type:'currency')}</td>
				  						<td>${formatDate(date:row.fecha,format:'dd/MM/yyyy')}</td>
				  						<td>${formatNumber(number:row.importe,type:'currency')}</td>
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
				</form>
				
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

		<div class="modal fade" id="cancelDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Cancelar cheque</h4>
					</div>

					<g:form action="cancelarCheque" class="form-horizontal" method="DELETE">
						<g:hiddenField name="id" value="${pagoInstance.id}"/>
						<div class="modal-body">
							<div class="form-group">
								<label for="comentario" class="control-label col-sm-2">Comentario</label>
								<div class="col-sm-10">
									<input name="comentario" class="form-control" value="" required>
								</div>
							</div>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-danger" name="aceptar"
									value="Cancelar" />
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