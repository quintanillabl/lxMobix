<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Renta ${rentaInstance.folio}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="well well-sm">
					<h4> 
						Renta de arrendamiento ${rentaInstance.folio} ${rentaInstance.arrendamiento.inmueble.descripcion}
						<small>  Cliente: ${rentaInstance.arrendamiento.cliente}
						</small>
					</h4>
					<g:if test="${flash.message}">
						<span class="label label-warning">${flash.message}</span>
					</g:if> 
				</div>
			</div>
		</div><!-- end .row -->


		<div class="row toolbar-panel">
			<div class="col-md-8 col-md-offset-2">
				<div class="btn-group">
				    <g:link controller='arrendamiento' action="show" class="btn btn-default " id="${rentaInstance.arrendamiento.id}">
				        <i class="fa fa-step-backward"></i></span> Arrendamiento ${rentaInstance.arrendamiento.id}
				    </g:link>
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    <g:link action="delete" class="btn btn-danger " 
				    	id="${rentaInstance.id}" onclick="return confirm('Eliminar renta?');">
				        <i class="fa fa-trash"></i> Eliminar
				    </g:link>
				    <g:link action="edit" class="btn btn-default " id="${rentaInstance.id}">
				        <i class="fa fa-pencil"></i> Editar
				    </g:link>
				    <g:link action="generarVenta" class="btn btn-success " id="${rentaInstance.id}"
				    	onclick="return confirm('Generar la venta correspondiente a la renta: ${rentaInstance.folio}?');">
				        <i class="fa fa-cart-arrow-down"></i> Generar venta
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
		    <div class="col-md-8 col-md-offset-2">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Folio ${rentaInstance.folio}</div>
					  <div class="panel-body">
					    <g:hasErrors bean="${rentaInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${rentaInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${rentaInstance}">
							<f:field property="inicio" widget-class="form-control" />
							<f:field property="fin" widget-class="form-control" />
							<f:field property="importe" widget-class="form-control"/>
							<f:field property="fechaDeCobro" widget-class="form-control" label="F. Cobro"/>
							<f:field property="comentario" widget-class="form-control" />
							<div class="form-group">
								<lable class="col-sm-2 control-label">Venta</lable>
								<div class="col-sm-5">
									<p class="form-control-static">
										<g:link action="show" controller="venta" class="control-form" id="${rentaInstance?.ventaDet?.venta?.id}">
											${rentaInstance?.ventaDet?.venta?.folio}
										</g:link>
									</p>
									
									%{-- <input type="text" class="form-control" value="${rentaInstance?.ventaDet?.id}"> --}%
								</div>
							</div>
						</f:with>
						
					  </div>
					  <g:if test="${flash.error}">
					  	<div class="alert alert-danger">
					  		${flash.error}
					  	</div>
					  </g:if>

					</div>
				</g:form>
				</fieldset>
				

		    </div>
		    
		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>