<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${cuentaBancariaInstance.numero} </title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
</head>
<body>

	<div class="container">
		
		

		<div class="row row-header">
			<div class="col-md-6 col-md-offset-3 toolbar-panel">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Cuentas
				    </g:link>
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-plus"></i> Nuevo
				    </g:link>
				    
				    <sec:ifAllGranted roles="TESORERIA">
				    	<g:link action="delete" class="btn btn-danger " id="${cuentaBancariaInstance.id}">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    </sec:ifAllGranted>
				    <sec:ifAllGranted roles="TESORERIA">
				    	<g:link action="edit" class="btn btn-default " id="${cuentaBancariaInstance.id}">
				    	    <i class="fa fa-pencil"></i> Editar
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
		    <div class="col-md-6 col-md-offset-3">
		    	<fieldset disabled>
				<form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Folio ${cuentaBancariaInstance.id} ${cuentaBancariaInstance.banco.nombre} ${cuentaBancariaInstance.numero} </div>
					  	<div class="panel-body">
					    	<g:hasErrors bean="${cuentaBancariaInstance}">
					    		<div class="alert alert-danger">
					    			<ul class="errors" >
					    				<g:renderErrors bean="${cuentaBancariaInstance}" as="list" />
					    			</ul>
					    		</div>
					    		<g:if test="${flash.message}">
					    			<span class="label label-warning">${flash.message}</span>
					    		</g:if> 
					    	</g:hasErrors>
							<f:with bean="${cuentaBancariaInstance}">
							
								<f:field property="banco" widget-class="form-control" />
								<f:field property="numero" widget-class="form-control" />
								<f:field property="tipo" widget-class="form-control"/>
								<f:field property="nombre" widget-class="form-control"/>
								<f:field property="moneda" widget-class="form-control"/>
								<f:field property="activo" widget-class="form-control"/>
								<f:field property="folioInicial" widget-class="form-control" widget-type="text"/>
								<f:field property="folioFinal" widget-class="form-control" widget-type="text"/>
								<f:field property="folio" widget-class="form-control" widget-type="text"/>
								<f:field property="tasaDeInversion" widget-class="form-control" widget-type="text"/>
								<f:field property="tasaIsr" widget-class="form-control" widget-type="text"/>
								<f:field property="diasInversionIsr" widget-class="form-control" widget-type="text"/>
								<f:field property="plazo" widget-class="form-control" widget-type="text"/>
								<f:field property="cuentaContable" >
									<g:hiddenField id="cuentaId" name="cuentaContable.id" />
									<input type="text" value="${value}" id="cuentaField" class="form-control">
								</f:field>
								<f:field property="impresionTemplate" widget-class="form-control" widget-type="text"/>
							
							</f:with>
					  </div>
					</div>
				</form>
				</fieldset>

		    </div>
		</div>

		<!-- end .row 2 -->

	</div>

	
</body>
</html>