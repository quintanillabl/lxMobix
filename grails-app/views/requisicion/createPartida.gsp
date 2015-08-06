<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Requisición ${requisicionInstance?.id}</title>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container form-panel">

		%{-- <div class="row toolbar-panel">
			<div class="col-md-8 col-md-offset-2">
				<div class="btn-group">
				    <lx:backButton label="Requisicion ${requisicionInstance.id}" id="${requisicionInstance.id}" action="edit"/>
				    <buttn id="saveBtn" class="btn btn-success">
				    	<i class="fa fa-floppy-o"></i> Salvar
				    </buttn>
				</div>
			</div>
		</div>  --}%

		<div class="row ">
		    <div class="col-md-10 col-md-offset-1 ">
		    	
				<g:form name="createForm" class="form-horizontal"  action="savePartida"  method="POST">	

					<div class="panel panel-primary">
						<div class="panel-heading">Cuentas pendientes: ${requisicionInstance.proveedor}</div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${requisicionDetInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${requisicionDetInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>
							<g:hiddenField name="requisicion.id" value="${requisicionInstance.id}"/>
							<f:with bean="${requisicionDetInstance}">
								<f:field property="cuentaPorPagar" wrapper="bootstrap3">
									<g:hiddenField id="cxpId" name="cuentaPorPagar.id" value="${value}" />
									<input 
										id="cxpField" 
										type="text" 
										class="form-control" 
										value="${value}" 
										placeholder="Seleccione la cuenta por pagar">
									</input>
								</f:field>
								<f:field property="requisitado" widget="money" wrapper="bootstrap3"/>
								<f:field property="comentario" widget-class="form-control" wrapper="bootstrap3"/>
							</f:with>
				  		</div>
				  		<div class="panel-footer">
				  			<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
				  			
				  			<div class="form-group">
				  				
				  				<div class="col-md-4">

				  					<g:link action="edit" class="btn btn-default" id="${requisicionInstance.id}"> Cancelar</g:link>
				  					<button id="saveBtn" class="btn btn-success">
				  							<span class="glyphicon glyphicon-ok"></span> Salvar
				  					</button>
				  				</div>
				  			</div>
				  		</div>
					</div>
				</g:form>
				
			</div>
			
			

		</div><!-- end .row 2 -->
		
		<g:if test="${flash.error}">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
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
		$(".money").autoNumeric('init',{wEmpty:'zero',aSign: '$'});

		

		$('form[name=createForm]').submit(function(e){
    		var button=$("#saveBtn");
    		button.attr('disabled','disabled')
    		.html('Procesando...');
    		$(".money,.porcentaje,.tc",this).each(function(index,element){
    		  var val=$(element).val();
    		  var name=$(this).attr('name');
    		  var newVal=$(this).autoNumeric('get');
    		  $(this).val(newVal);
    		  console.log('Enviando elemento numerico con valor:'+name+" : "+val+ " new val:"+newVal);
    		});
    		//e.preventDefault(); 
    		return true;
		});

		$("#cxpField").autocomplete({
			source:'<g:createLink  action="cxpPendientes" id="${requisicionInstance.id}"/>',
			minLength:1,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#cxpId").val(ui.item.id);
				$("#requisitado").autoNumeric('set',ui.item.pendiente)
			}
		});
		
	});
</script>
	
</body>
</html>