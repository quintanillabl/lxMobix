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
						    <span class="glyphicon glyphicon-repeat"></span> Catálogo
						</g:link>
						<g:link action="index" class="btn btn-default ">
						    <i class="fa fa-plus"></i> Nueva
						</g:link>
					</g:else>
				    
				    
				    <%-- Acciones de administrador --%>
				    <sec:ifAllGranted roles="ADMIN">
				    	<g:link action="delete" class="btn btn-danger " id="${cuentaContableInstance.id}" 
				    		onclick="return confirm('Eliminar el cuenta: ${cuentaContableInstance.clave}?');">
				    	    <i class="fa fa-trash"></i> Eliminar
				    	</g:link>
				    </sec:ifAllGranted>
				    <sec:ifAllGranted roles="CONTABILIDAD">
				    	<g:link action="edit" class="btn btn-default " id="${cuentaContableInstance.id}">
				    	    <i class="fa fa-pencil"></i> Editar
				    	</g:link>
				    	<g:if test="${!cuentaContableInstance.detalle}">
				    		<g:link action="createSubCuenta" class="btn btn-default " id="${cuentaContableInstance.id}" >
				    		    <i class="fa fa-cog"></i> Agregar sub cuenta
				    		</g:link>
				    	</g:if>
				    	
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
		    
		    <div class="col-md-8  ">
		    	<fieldset disabled>
				<g:form class="form-horizontal"  >	
					<div class="panel panel-primary">
						<div class="panel-heading">Cuenta ${cuentaContableInstance} 
							<g:if test="${cuentaContableInstance.padre}">
								<small>(${cuentaContableInstance.padre})</small>
							</g:if>
							<g:if test="${flash.message}">
								<small><span class="label label-warning ">${flash.message}</span></small>
							</g:if> 
						</div>
					  <div class="panel-body">

					    <g:render template="showForm" bean="${cuentaContableInstance}"/>
					</div>

				</g:form>
				</fieldset>
		    </div>
		    
		    <div class="col-md-4">
		    	<div class="panel panel-primary">
		    		<div class="panel-heading">Sub Cuentas</div>
		    		<table id="grid" class="table table-striped table-bordered table-condensed">
		    			<thead>
		    				<tr>
		    					<th>Clave</th>
		    					<th>Descripción</th>
		    					<th>Detalle</th>
		    				</tr>
		    			</thead>
		    			<tbody>
		    				<g:each in="${cuentaContableInstance.subCuentas.sort()}" var="row">
		    					<tr id="${row.id}">
		    						<td >
		    							<g:link  action="show" id="${row.id}">
		    								${fieldValue(bean:row,field:"clave")}
		    							</g:link>
		    						</td>
		    						<td>${fieldValue(bean:row,field:"descripcion")}</td>
		    						<td><input type="checkbox" ${row.detalle?'checked':''} disabled/></td>
		    						
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