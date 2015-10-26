<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Inversión bancaria ${inversionInstance.id}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="page-header">
				  <h3>Inversión bancaria ${inversionInstance.id} <small> (${session.empresa})</small>
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
				
				<form  class="form-horizontal"  >	

					<div class="panel panel-primary">
						<div class="panel-heading">Datos generales</div>
					  	<div class="panel-body">
						    <g:hasErrors bean="${inversionInstance}">
						    	<div class="alert alert-danger">
						    		<ul class="errors" >
						    			<g:renderErrors bean="${inversionInstance}" as="list" />
						    		</ul>
						    	</div>
						    </g:hasErrors>
						    <g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:with bean="${inversionInstance}">
								
								<f:display property="fecha" wrapper="bootstrap3"/>

								%{-- <f:display property="rendimientoFecha" wrapper="bootstrap3"/> --}%

								<f:display property="vencimiento" wrapper="bootstrap3"/>
								
								<f:display property="cuentaOrigen" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:display property="cuentaDestino" 
									wrapper="bootstrap3" widget-class="form-control "/>

								<f:display property="importe" 
									widget="money" wrapper="bootstrap3"/>

								<f:display property="rendimientoReal" 
									widget="money" wrapper="bootstrap3"/>

								<f:display property="importeIsr" label="ISR"
									widget="money" wrapper="bootstrap3"/>
									
								
								%{-- <f:display property="tasa" 
									widget="porcentaje" 
									wrapper="bootstrap3" 
									label="Tasa de IVA(%)"/> --}%

								%{-- <f:display property="tasaIsr" 
									widget="porcentaje" 
									wrapper="bootstrap3" 
									label="Tasa ISR(%)"/> --}%

								%{-- <f:display property="rendimientoImpuesto" 
									widget="money" wrapper="bootstrap3"/> --}%

				    			<f:display property="plazo" widget="numeric"
				    				wrapper="bootstrap3"/>

				    			<f:display property="comentario" 
				    				widget-class="form-control "  wrapper="bootstrap3"/>
								
									
							</f:with>
					  	</div>

					  	<div class="row">
					  		<div class="col-md-12">
					  			<table id="grid" class="table table-striped table-bordered table-condensed">

					  				<thead>
					  					<tr>
					  						<th>Nombre</th>
					  						<th>Cuenta</th>
					  						<th>Importe</th>
					  						<th>Concepto</th>
					  						<th>Referencia</th>
					  						<th>Comentario</th>
					  						<th>Creado</th>
					  					</tr>
					  				</thead>
					  				<tbody>
					  					<g:each in="${inversionInstance.movimientos.sort{it.importe}}" var="row">
					  						<tr id="${row.id}">
					  							<td >
					  								<g:link  action="show" id="${row.id}">
					  									${fieldValue(bean:row,field:"cuenta.nombre")}
					  								</g:link>
					  							</td>
					  							<td>
					  								<g:link  action="show" id="${row.id}">
					  									${fieldValue(bean:row,field:"cuenta.numero")}
					  								</g:link>
					  							</td>
					  							<td class="${row.importe<=0?'text-danger':'text-success'}">${formatNumber(number:row.importe,type:'currency')}</td>
					  							<td>${fieldValue(bean:row,field:"concepto")}</td>
					  							<td>${fieldValue(bean:row,field:"referencia")}</td>
					  							<td>${fieldValue(bean:row,field:"comentario")}</td>
					  							<td><g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/></td>
					  						</tr>
					  					</g:each>
					  				</tbody>
					  			</table>
					  			
					  			
					  		</div>
					  	</div> <!-- end .row 2 -->
					 
						<div class="panel-footer">
						  	<div class="form-group">
						  		<div class="buttons col-md-offset-4 col-md-4">
						  			<lx:backButton />
						  			<a href="" class="btn btn-danger " data-toggle="modal" data-target="#deleteDialog"><i class="fa fa-trash"></i> Eliminar</a> 
						  			
						  		</div>
						  	</div>
						</div>

					</div>

				</form>
				
			</div>
		</div> <!-- end .row 2 -->

		<div class="modal fade" id="deleteDialog" tabindex="-1">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Eliminar el registro ${inversionInstance.id}</h4>
					</div>

					<g:form action="delete" class="form-horizontal" method="DELETE">
						<g:hiddenField name="id" value="${inversionInstance.id}"/>
						<div class="modal-body">
							<p><small>${inversionInstance}</small></p>
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<g:submitButton class="btn btn-danger" name="aceptar"
									value="Eliminar" />
						</div>
					</g:form>


				</div>
				<!-- moda-content -->
			</div>
			<!-- modal-di -->
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
	
</body>
</html>