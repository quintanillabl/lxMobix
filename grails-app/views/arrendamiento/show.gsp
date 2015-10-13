<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Arrendamiento ${arrendamientoInstance.id}</title>
</head>
<body>

	<div class="container">


		<div class="row page-header">
			<div class="col-md-8 ">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <span class="glyphicon glyphicon-repeat"></span> Catálogo
				    </g:link>
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="VENTAS">
				    	<g:link action="delete" class="btn btn-danger " id="${arrendamientoInstance.id}" onclick="return confirm('Eliminar el arrendamiento?');">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    	<g:link action="edit" class="btn btn-default " id="${arrendamientoInstance.id}">
				    	    <i class="fa fa-pencil"></i> Editar
				    	</g:link>
				    	<g:link action="generarRentas" class="btn btn-default " id="${arrendamientoInstance.id}" 
				    		onclick="return confirm('Generar rentas del arrendamiento?');">
				    	    <i class="fa fa-cog"></i> Generar Rentas
				    	</g:link>
				    </sec:ifAllGranted>
				    
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
		    
		    <div class="col-md-8 ">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Renta ${arrendamientoInstance.id} <small>(${arrendamientoInstance.inmueble.descripcion})</small></div>
					  <div class="panel-body">
					    <g:hasErrors bean="${arrendamientoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${arrendamientoInstance}" as="list" />
					    		</ul>
					    	</div>
					    </g:hasErrors>
					    <div class="row">
					    	<div class="col-sm-12">
					    		<div class="alert alert-info">
					    			Cliente: ${arrendamientoInstance.cliente}
					    			<g:if test="${flash.message}">
					    				<span class="label label-warning">${flash.message}</span>
					    			</g:if> 
					    		</div>
					    	</div>
							<div class="col-sm-12">

								<div class="form-group">
									<label class="label-control col-sm-2">Inmueble</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" value="${arrendamientoInstance.inmueble.descripcion}" disabled>
									</div>
								</div>
								<div class="form-group">
									<label class="label-control col-sm-2">Precio</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" value="${arrendamientoInstance.precio}" disabled>
									</div>
									<label class="label-control col-sm-2">Tipo</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" value="${arrendamientoInstance.tipo}" disabled>
									</div>
								</div>

								<div class="form-group">
									<label class="label-control col-sm-2">Inicio</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${formatDate(date:arrendamientoInstance.inicio,format:'dd/MM/yyyy')}" >
									</div>
									<label class="label-control col-sm-2">Fin</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${formatDate(date:arrendamientoInstance.fin,format:'dd/MM/yyyy')}" >
									</div>
								</div>

								<div class="form-group">
									<label class="label-control col-sm-2">Dia de corte</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" 
										value="${arrendamientoInstance.diaDeCorte}" >
									</div>
									
								</div>

								<div class="form-group">
									<label class="label-control col-sm-2">Producto</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" 
										value="${arrendamientoInstance.producto}" >
									</div>
								</div>
								<div class="form-group">
									<label class="label-control col-sm-2">F.Pago</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${arrendamientoInstance.formaDePago}" >
									</div>
									<label class="label-control col-sm-2">Cuenta</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" 
										value="${arrendamientoInstance.cuentaDePago}" >
									</div>
								</div>
								<div class="form-group">
									<label class="label-control col-sm-2">Cuenta contable</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" colsLabel="col-sm-3" cols="col-sm-9"
										value="${arrendamientoInstance.cuentaContable}" >
									</div>
								</div>
								
								<div class="form-group">
									<label class="label-control col-sm-2">Comentario</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" colsLabel="col-sm-3" cols="col-sm-9"
										value="${arrendamientoInstance.comentario}" >
									</div>
								</div>
								
							</div>
						
					  </div>

					</div>
				</g:form>
				</fieldset>
		    </div>

		    <div class="col-md-4">
		    	<div class="panel panel-primary">
		    		<div class="panel-heading">Rentas</div>
		    		<table id="grid" class="table table-striped table-bordered table-condensed">
		    			<thead>
		    				<tr>
		    					<th>Folio</th>
		    					<th>Inicio</th>
		    					<th>Fin</th>
		    					<th>Precio</th>
		    					<th>Venta</th>
		    					%{-- <th>Factura</th> --}%
		    				</tr>
		    			</thead>
		    			<tbody>
		    				<g:each in="${arrendamientoInstance.rentas.sort()}" var="row">
		    					<tr id="${row.id}">
		    						<td >
		    							<g:link  controller="renta" action="show" id="${row.id}">
		    								${fieldValue(bean:row,field:"folio")}
		    							</g:link>
		    						</td>
		    						<td><g:formatDate date="${row.inicio}" format="dd/MM/yyyy"/></td>
		    						<td><g:formatDate date="${row.fin}" format="dd/MM/yyyy"/></td>
		    						<td>${g.formatNumber(number:row.importe,type:'currency')}</td>
		    						<td>
		    							<g:if test="${row.ventaDet}">
		    								<g:link  controller="venta" action="show" id="${row.ventaDet.id}">
		    									${fieldValue(bean:row,field:"folio")}
		    								</g:link>
		    							</g:if>
		    						</td>
		    						%{-- <td>
		    							<g:if test="${row.ventaDet?.venta?.cfdi}">
		    								<g:link  controller="venta" action="show" id="${row.ventaDet.id}">
		    									${fieldValue(bean:row,field:"folio")}
		    								</g:link>
		    							</g:if>
		    						</td> --}%
		    					</tr>
		    				</g:each>
		    			</tbody>
		    		</table>
		    	</div>
		    </div>

		</div><!-- end .row 2 -->

		

	</div>

	
</body>
</html>