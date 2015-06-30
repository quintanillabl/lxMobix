<asset:javascript src="forms/autoNumeric.js"/>
<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/>

<div class="panel panel-primary">
	<div class="panel-heading">Agregar concepto para póliza ${polizaInstance.tipo} ${polizaInstance.folio} (${polizaInstance.mes} - ${polizaInstance.ejercicio})</div>
  	
	<div class="panel-body ">
	
		<legend>  <span id="conceptoLabel">Seleccione un concepto</span></legend>
		<g:hasErrors bean="${polizaDetInstance}">
			<div class="alert alert-danger">
				<ul class="errors" >
					<g:renderErrors bean="${polizaDetInstance}" as="list" />
				</ul>
			</div>
		</g:hasErrors>

		
		<g:form name="updateForm" action="save" class="form-horizontal">	
			<g:hiddenField name="poliza.id" value="${polizaInstance.id}"/>
			<f:with bean="${polizaDetInstance}">
				<f:field property="cuenta"  cols="col-sm-8" colsLabel="col-sm-3">
					<g:hiddenField id="cuentaId" name="cuenta.id" />
					<input type="text" id="cuentaField" class="form-control" >
				</f:field>
				<f:field property="debe" 
					widget-class="form-control numeric" 
					widget-type="text" cols="col-sm-8" 
					colsLabel="col-sm-3"/>
				<f:field property="haber" 
					widget-class="form-control numeric" 
					widget-type="text" cols="col-sm-8" 
					colsLabel="col-sm-3"/>
				<f:field property="concepto" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="asiento" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="referencia" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="entidad" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="origen" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				
			</f:with>
		</g:form>
	
	</div>					
 
	<div class="panel-footer">
		<div class="btn-group">
			<g:link class="btn btn-default btn-sm" controller="poliza" action="edit" id="${polizaInstance.id}" >
				<span class="glyphicon glyphicon-hand-left"></span> Regresar
			</g:link>
			<button id="saveBtn" class="btn btn-success btn-sm">
					<span class="glyphicon glyphicon-ok"></span> Salvar
			</button>
		</div>
	</div><!-- end .panel-footer -->

</div>




<script type="text/javascript">
	$(function(){
		
		$(".numeric").autoNumeric({wEmpty:'zero',aSep:"",lZero: 'deny'});
		$("#cuentaField").autocomplete({
			source:'<g:createLink controller="cuentaContable" action="getCuentasDeDetalleJSON"/>',
			minLength:3,
			select:function(e,ui){
				$("#cuentaId").val(ui.item.id);
			}
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