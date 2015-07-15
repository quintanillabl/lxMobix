<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Gasto ${gastoInstane?.id}</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-12 ">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Gastos
				    </g:link>
				    <g:if test="${!gastoInstance.acuse}">
				    	<a href="#uploadFileDialog" data-toggle="modal" class="btn btn-default">
				    		<i class="fa fa-upload"></i></span> Importar CFDI
				    	</a>
				    	<g:link action="validarEnElSat" onclick="return confirm('Validar en el SAT?');"
				    		class="btn btn-default " id="${gastoInstance.id}">
				    	    <i class="fa fa-check-square-o"></i> Validar (SAT)
				    	</g:link> 
				    </g:if>
				    <g:else>
				    	<g:link  action="mostrarAcuse" 
				    		id="${gastoInstance.id}"
				    		class="btn btn-default " >
				    		<i class="fa fa-file-code-o"></i> Acuse
				    	</g:link>
				    	<g:link  action="descargarAcuse" 
				    		id="${gastoInstance.id}"
				    		class="btn btn-default " >
				    		<i class="fa fa-download"></i> Descargar acuse
				    	</g:link>
				    </g:else>
				    <g:if test="${gastoInstance.cfdiXml}">
				    	<g:link class="btn btn-default " action="mostrarXml" resource="${cfdiInstance}">CFDI</g:link>
				    </g:if>
				    
				    <g:link action="print" class="btn btn-default " id="${gastoInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
				    <g:link controller="gastoDet" action="create" class="btn btn-default " id="${gastoInstance.id}">
				        <i class="fa fa-cart-plus"></i> Agregar concepto
				    </g:link> 
				    
				    <buttn id="saveBtn" class="btn btn-success">
				    	<i class="fa fa-floppy-o"></i> Salvar
				    </buttn>
				  
				    <lx:deleteButton bean="${gastoInstance}" />
					
				   
				</div>
				
				
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	
				<g:form name="updateForm" class="form-horizontal"  action="update" id="${gastoInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading">Proveedor: ${gastoInstance.proveedor} Id: ${gastoInstance.id}</div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${gastoInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${gastoInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${gastoInstance}">
								<g:hiddenField name="gasto.id" value="${gastoInstance.id}"/>
								

								<div class="col-sm-4">
									<f:field property="fecha" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="serie" widget-class="form-control "  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="cuentaContable" widget-class="form-control" label="Cta" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="moneda" 
										widget-class="form-control" 
										widget-readonly="readonly"
										cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="uuid" label="UUID"
										widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:display property="acuseEstado" cols="col-sm-8" colsLabel="col-sm-4"/>

								</div>
								<div class="col-sm-4">
									<f:field property="vencimiento"  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="folio" widget-class="form-control "  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="comentario" widget-class="form-control"  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="tipoDeCambio" label="T.C."
										widget-readonly="readonly"
										widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:display property="acuseCodigoEstatus" cols="col-sm-8" colsLabel="col-sm-4" label="Stat(SAT)"/>
								</div>

								<div class="col-sm-4" id="totalesPanel">
									<f:field property="importe"  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="descuento" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="subTotal" cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="impuestoTasa"  cols="col-sm-8" colsLabel="col-sm-4" label="IVA (%)"/>
									<f:field property="impuesto"  cols="col-sm-8" colsLabel="col-sm-4"/>
									<f:field property="total"  cols="col-sm-8" colsLabel="col-sm-4"/>
								</div>
								
							</f:with>
				  		</div>
				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					<th>Concepto</th>
				  					<th>Descripcion</th>
				  					<th>Unidad</th>
				  					<th>Cantidad</th>
				  					<th>Valor unitario</th>
				  					<th>Importe</th>
				  					
				  					<th>Cuenta</th>
				  					<th>Comentario</th>
				  					<td></td>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${gastoInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						<td >
				  							<g:link  controller="gastoDet" action="edit" id="${row.id}">
				  								${fieldValue(bean:row,field:"concepto")}
				  							</g:link>
				  						</td>
				  						<td>
				  							<g:link  controller="gastoDet" action="edit" id="${row.id}">
				  								${fieldValue(bean:row,field:"descripcion")}
				  							</g:link>
				  						</td>
				  						<td>${fieldValue(bean:row,field:"unidad")}</td>
				  						<td>${formatNumber(number:row.cantidad,format:'##.##')}</td>
				  						<td>${g.formatNumber(number:row.valorUnitario,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.importe,type:'currency')}</td>
				  						
				  						<td>${fieldValue(bean:row,field:"cuentaContable.clave")}</td>
				  						<td>${fieldValue(bean:row,field:"comentario")}</td>
				  						<td>
				  							<g:link controller="gastoDet" action="delete" id="${row.id}" 
				  								onclick="return confirm('Eliminar partida ${row.concepto}');">
				  								<span class="glyphicon glyphicon-trash"></span>
				  							</g:link>
				  						</td>
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
				<div class="col-md-12">
					<div class="alert alert-danger">
						${flash.error}
					</div>
				</div>
			</div>
		</g:if>
		
	<g:render template="uploadXmlFile"/>
	</div>
<script type="text/javascript">
	$(function(){
		//$(".money").autoNumeric({wEmpty:'zero',aSep:""});
		
		$("#totalesPanel :input").each(function(){
		 	var input = $(this); // This is the jquery object of the input, do what you will
		 	input.removeAttr("type").prop('type','text')
		 	.addClass('form-control')
		 	.addClass('text-right');
		});
		$("#saveBtn").click(function(){
			console.log('Salvar la forma');
			$("form[name=updateForm]").submit();
		});

		$('form[name=updateForm]').submit(function(e){
			console.log("Desablidatndo submit button....");
    		$(this).children('input[type=submit]').attr('disabled', 'disabled');
    		var button=$("#saveBtn");
    		button.attr('disabled','disabled')
    		//.attr('value','Procesando...');
    		.html('Procesando...')
    		//e.preventDefault(); 
    		return true;
		});
		
	});
</script>
	
</body>
</html>