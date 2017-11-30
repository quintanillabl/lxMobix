<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Venta ${ventaInstane?.id}</title>
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
				        <i class="fa fa-step-backward"></i> Ventas
				    </g:link>
				    <g:link action="create" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nueva
				    </g:link>
				    <g:if test="${!ventaInstance.cfdi}">
				    	
				    	<lx:deleteButton bean="${ventaInstance}"/>
				    	<g:link controller="ventaDet" action="create" class="btn btn-default " id="${ventaInstance.id}">
				    	    <i class="fa fa-cart-plus"></i> Agregar
				    	</g:link>
				    	<g:link action="facturar" class="btn btn-info" 
				    		onclick="return confirm('Facturar la venta ${ventaInstance.id}?');"
				    		id="${ventaInstance.id}">
				    		<i class="fa fa-file-pdf-o fa-fw "></i>&nbsp; Facturar
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
				        
				    </ul>
				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-8 ">
		    	<fieldset disabled>
				<g:form class="form-horizontal" action="update" >	

					<div class="panel panel-primary">
						<div class="panel-heading">${ventaInstance}</div>
				  		<div class="panel-body">
				  			<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
				    		<g:hasErrors bean="${ventaInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${ventaInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>
							<f:with bean="${ventaInstance}">
								<g:hiddenField name="venta.id" value="${ventaInstance.id}"/>
								<f:field property="cliente" widget-class="form-control " />
								<f:field property="tipo" widget-class="form-control " />
								<f:field property="fecha" widget-class="form-control " />
								<f:field property="formaDePago" widget-class="form-control " label="F.Pago"/>
								<f:field property="comentario" widget-class="form-control " />
								<f:field property="usoCfdi" widget-class="form-control " />
								<div class="form-group">
								    <label class="col-sm-2 control-label">Cfdi</label>
								    <div class="col-sm-10">
								    	<g:if test="${ventaInstance.cfdi}">
								    		<p class="form-control-static">
								    			<g:link controller="cfdi" action="show" id="${ventaInstance.cfdi.id}">
								    				${ventaInstance.cfdi.folio} UUID: ${ventaInstance.cfdi.uuid}
								    			</g:link>
								    		</p>
								    	</g:if>
								    	<g:else>
								    		<p class="form-control-static">Pendiente por timbrar en versión de CFDI: ${ventaInstance.empresa.versionDeCfdi}</p>
								    	</g:else>
								      
								    </div>
								</div>
							</f:with>
				  		</div>
					</div>
				</g:form>
				</fieldset>
			</div>
			
			<div class="col-md-4 ">
				<div class="panel panel-primary">
					<div class="panel-heading">Totales</div>
					<div class="panel-body totalesPanel">
						<form id="totalesForm" class="form-horizontal">
							<fieldset disabled>
							<f:with bean="${ventaInstance}">
								<div class="form-group">
									<label class="col-sm-4 control-label" >Importe</label>
									<div class="col-sm-8">
										<input  class="form-control" 
											value="${formatNumber(number:ventaInstance.importe,type:'currency')}">
									</div>
								</div>
								
								<f:field property="descuento"  cols="col-sm-8" colsLabel="col-sm-4"/>
								<div class="form-group">
									<label class="control-label  col-sm-4" >Sub Total</label>
									<div class="col-sm-8">
										<input  class="form-control" 
											value="${formatNumber(number:ventaInstance.subTotal,type:'currency')}">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label  col-sm-4" >Impuesto</label>
									<div class="col-sm-8">
										<input  class="form-control" 
											value="${formatNumber(number:ventaInstance.impuesto,type:'currency')}">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label  col-sm-4" >Total</label>
									<div class="col-sm-8">
										<input  class="form-control" 
											value="${formatNumber(number:ventaInstance.total,type:'currency')}">
									</div>
								</div>
								
							</f:with>
							</fieldset>
						</form>
						
					</div>
				</div>
			</div>

		</div><!-- end .row 2 -->

		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-info">
					<div class="panel-heading">Partidas</div>
					<table id="grid" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Descripcion</th>
								<th>Comentario</th>
								<th>Cantidad</th>
								<th>Unidad</th>
								<th>Precio</th>
								<th>Importe</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${ventaInstance.partidas}" var="row">
								<tr id="${row.id}">
									<td >
										<g:link  controller="ventaDet" action="show" id="${row.id}">
											${fieldValue(bean:row,field:"producto.clave")}
										</g:link>
									</td>
									<td>
										<g:link  controller="ventaDet" action="show" id="${row.id}">
											${fieldValue(bean:row,field:"producto.descripcion")}
										</g:link>
									</td>
									<td>${fieldValue(bean:row,field:"comentario")}</td>
									<td>${formatNumber(number:row.cantidad,format:'##.##')}</td>
									<td>${fieldValue(bean:row,field:"producto.unidad")}</td>
									<td>${g.formatNumber(number:row.precio,type:'currency')}</td>
									<td>${g.formatNumber(number:row.importeNeto,type:'currency')}</td>
									<td>
										<g:if test="${!row.venta.cfdi}">
											<g:link controller="ventaDet" action="delete" id="${row.id}" 
												onclick="return confirm('Eliminar partida ${row.producto.clave}');">
												<span class="glyphicon glyphicon-trash"></span>
											</g:link>
										</g:if>
										
										
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
					
				</div>
			</div>
		</div><!-- end row Grid -->
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
		$(".money").autoNumeric({wEmpty:'zero',aSep:""});
		
		$("form#totalesForm :input").each(function(){
		 	var input = $(this); // This is the jquery object of the input, do what you will
		 	input.removeAttr("type")
		 	.prop('type','text')
		 	.addClass('form-control')
		 	.addClass('text-right');
		});
		
	});
</script>
	
</body>
</html>