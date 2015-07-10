<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Poliza ${polizaInstance?.folio}</title>
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
				        <i class="fa fa-step-backward"></i> Pólizas
				    </g:link>
				    <g:link action="print" class="btn btn-default " id="${polizaInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
				    <g:link controller="polizaDet" action="create" class="btn btn-default " id="${polizaInstance.id}">
				        <i class="fa fa-cart-plus"></i> Agregar concepto
				    </g:link> 
				    
				    <buttn id="saveBtn" class="btn btn-success">
				    	<i class="fa fa-floppy-o"></i> Salvar
				    </buttn>
				    <g:link action="delete" class="btn btn-danger " id="${polizaInstance.id}"
				    	onclick="return confirm('Eliminar el poliza: '+${polizaInstance.id});">
				        <i class="fa fa-trash"></i> Eliminar
				    </g:link>
					
				   
				</div>
				
				
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-12 ">
		    	
				<g:form name="updateForm" class="form-horizontal"  action="update" id="${polizaInstance.id}" method="PUT">	

					<div class="panel panel-primary">
						<div class="panel-heading"> 
							${polizaInstance.tipo}  ${polizaInstance.folio} (${polizaInstance.ejercicio - polizaInstance.mes})
							<g:if test="${polizaInstance.cuadre}">
								<span class="label label-danger">La póliza presenta un descuadre no puede ser aplicada en la balanza</span>
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
								%{-- <g:hiddenField name="poliza.id" value="${polizaInstance.id}"/> --}%
								<div class="col-sm-6">
									<f:display property="ejercicio" />
									<f:display property="mes"  />
									<f:field property='tipo' widget-class="form-control"/>
									<f:display property='folio' />
									<f:display property="fecha"  />

								</div>
								<div class="col-sm-6 " >
									<f:field property="concepto"  widget-class="form-control"/>
									<f:field property="comentario"  widget-class="form-control"/>
									<f:display property="debe"  widget="money"/>
									<f:display property="haber"  widget="money"/>
									%{-- <f:display property="cuadre"  widget="money" widget-class="${polizaInstance.cuader?'danger':''}"/> --}%
									<div class="form-group ${polizaInstance.cuadre?'has-error':''}">
									  <label class="col-sm-2 control-label" for="inputError1">Cuadre</label>
									  <div class="col-sm-10">
									  	<input type="text" class="form-control text-right" id="inputError1" 
									  		value="${formatNumber(number:polizaInstance.cuadre,type:'currency')}" disabled>
									  </div>
									  
									</div>
								</div>
							</f:with>
				  		</div>
				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					<th>Cuenta</th>
				  					<th>Descripción</th>
				  					<th>Debe</th>
				  					<th>Haber</th>
				  					<th>Concepto</th>
				  					<th>Asiento</th>
				  					<th>Referencia</th>
				  					<th>Origen</th>
				  					<td></td>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${polizaInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						<td >
				  							<g:link  controller="polizaDet" action="edit" id="${row.id}">
				  								${fieldValue(bean:row,field:"cuenta.clave")}
				  							</g:link>
				  						</td>
				  						<td >
				  							<g:link  controller="polizaDet" action="edit" id="${row.id}">
				  								${fieldValue(bean:row,field:"cuenta.descripcion")}
				  							</g:link>
				  						</td>
				  						<td>${g.formatNumber(number:row.debe,type:'currency')}</td>
				  						<td>${g.formatNumber(number:row.haber,type:'currency')}</td>
				  						<td>
				  							<small>
				  								<abbr title="${row.concepto}">
				  								${org.apache.commons.lang.StringUtils.substring(row.concepto,0,20)}
				  								</abbr>
				  							</small>
				  						</td>
				  						<td>${fieldValue(bean:row,field:"asiento")}</td>
				  						<td>${fieldValue(bean:row,field:"referencia")}</td>
				  						<td>${fieldValue(bean:row,field:"origen")}</td>
				  						<td>
				  							<g:link controller="polizaDet" action="delete" id="${row.id}" 
				  								onclick="return confirm('Eliminar partida ${row.cuenta}');">
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
				  			<p class="text-right">Modificacion por: <strong >${polizaInstance.modificadoPor}</strong> </p> 
				  			
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