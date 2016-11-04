<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="layout" content="application"/>
	<title>Póliza ${polizaInstance?.id}</title>
	%{-- <asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>  --}%
</head>
<body>

	<div class="container-fluid form-panel">

		<div class="row toolbar-panel">
			<div class="col-md-12 ">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default " params="[subTipo:polizaInstance.subTipo]">
				        <i class="fa fa-step-backward"></i> Pólizas
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nueva
				    </g:link>
				    <g:link action="print" class="btn btn-default " id="${polizaInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 

				    <g:link action="edit" class="btn btn-default " id="${polizaInstance.id}">
				        <i class="fa fa-pencil"></i> Editar
				    </g:link>
				   <g:if test="${polizaInstance.tipo!='GENERICA'}">
				   		
				   		<g:link action="update" class="btn btn-default " 
				   			id="${polizaInstance.id}"
				   			onclick="return confirm('Recalular poliza: '+${polizaInstance.id});">
				   		    <i class="fa fa-cog"></i> Recalcular
				   		</g:link>
				   		<g:link action="delete" class="btn btn-danger " 
				   			id="${polizaInstance.id}"
				   			onclick="return confirm('Eliminar el poliza: '+${polizaInstance.id});">
				   		    <i class="fa fa-trash"></i> Eliminar
				   		</g:link>
				   </g:if>
				</div>
				<div class="btn-group">
				    <button type="button" name="reportes"
				            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
				            role="menu">
				            Reportes <span class="caret"></span>
				    </button>

				    <ul class="dropdown-menu">
				    	<li>
				    		<g:link action="printComplemento"  id="${polizaInstance.id}">
				    		    <i class="fa fa-print"></i> Complementos
				    		</g:link> 
				    	</li>
				        
				    </ul>
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Id: ${polizaInstance.id}
							<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
						</div>
				  		<div class="panel-body">
				  			
				    		<g:hasErrors bean="${polizaInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${polizaInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>
				    		<f:with bean="${polizaInstance}">
				    			<g:hiddenField name="poliza.id" value="${polizaInstance.id}"/>
				    			<div class="col-sm-6">
				    				<f:display property="ejercicio" />
				    				<f:display property="mes"  />
				    				<f:display property='tipo' />
				    				<f:display property='subTipo' />
				    				<f:display property='folio' />
				    				<div class="form-group">
				    					<label for="fecha" class="control-label col-sm-2">LabelDesc</label>
				    					<div class="col-sm-10">
				    						<input name="fecha" class="form-control" 
				    							value="${polizaInstance.fecha.format('dd/MM/yyyy')}">
				    					</div>
				    				</div>

				    			</div>
				    			<div class="col-sm-6">
				    				<f:display property="concepto"  />
				    				<f:display property="debe"  widget="money"/>
				    				<f:display property="haber"  widget="money"/>
				    				<f:display property="dateCreated" label="Creado">
				    					<input type="text" 
				    						value="${polizaInstance.dateCreated.format('dd/MM/yyyy HH:mm:ss')} (${polizaInstance.creadoPor})"
				    						class="form-control" disabled
				    						>
				    				</f:display>
				    				<f:display property="lastUpdated" label="Modificado">
				    					<input type="text" 
				    						value="${polizaInstance.lastUpdated.format('dd/MM/yyyy HH:mm:ss')} (${polizaInstance.modificadoPor})"
				    						class="form-control" disabled
				    						>
				    				</f:display>
				    				<f:display property="manual"  />
				    				<g:if test="${polizaInstance.manual}">
				    					<div class="form-group">
				    						<label for="manual" class="control-label col-sm-6">Póliza manual</label>
				    						
				    					</div>
				    				</g:if>
				    			</div>
				    		</f:with>
				  		</div>

					</div>
				</g:form>
				</fieldset>
			</div>
		</div><!-- end .row 2 -->
		
		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Cuenta</th>
							<th>Concepto</th>
							<th>Debe</th>
							<th>Haber</th>
							<th>Descripcion</th>
							<th>Asiento</th>
							<th>Referencia</th>
							<th>Origen</th>
							<th>Transaccion</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${polizaInstance.partidas}" var="row">
							<tr id="${row.id}">
								<td >
									<g:link  controller="gastoDet" action="show" id="${row.id}">
										${fieldValue(bean:row,field:"cuenta.clave")}
									</g:link>
								</td>
								<td>${fieldValue(bean:row,field:"concepto")}</td>
								<td>${g.formatNumber(number:row.debe,type:'currency')}</td>
								<td>${g.formatNumber(number:row.haber,type:'currency')}</td>
								<td>${fieldValue(bean:row,field:"descripcion")}</td>
								<td>${fieldValue(bean:row,field:"asiento")}</td>
								<td>${fieldValue(bean:row,field:"referencia")}</td>
								<td>${fieldValue(bean:row,field:"origen")}</td>
								<td>
									<g:if test ="${row.cheque}">
										<a  data-toggle="popover" role="button" tabindex="0"
											
											title="Cheque"
											data-placement="left" 
											data-content="${row.cheque}" >
											Trx-CHE
										</a>	
									</g:if>
									<g:elseif test="${row.transferencia}">
										<a  data-toggle="popover" role="button" tabindex="0"
											title="Transferencia"
											data-placement="left" 
											data-content="${row.transferencia}" >
											Trx-TR
										</a>	
									</g:elseif>
									<g:elseif test="${row.compraNal}">
										<a  data-toggle="popover" role="button" tabindex="0"
											title="Complemento compra Nacional"
											data-placement="left" 
											data-content="${row.compraNal}" >
											Trx-ComNal
										</a>	
									</g:elseif>
								</td>
								
								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</div>
		
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

	<script>
		$(function(){
			$('[data-toggle="popover"]').popover();
		});
	</script>

	
</body>
</html>