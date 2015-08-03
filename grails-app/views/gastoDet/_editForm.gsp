<asset:javascript src="forms/autoNumeric.js"/>
<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/>

<div class="panel panel-primary">
	<div class="panel-heading">Partida a gasto ${gastoDetInstance.gasto.id} ${gastoDetInstance.gasto.proveedor}</div>
  	
	<div class="panel-body ">
	
		<legend>  <span id="conceptoLabel">Concepto ${gastoDetInstance.descripcion}</span></legend>
		<g:hasErrors bean="${gastoDetInstance}">
			<div class="alert alert-danger">
				<ul class="errors" >
					<g:renderErrors bean="${gastoDetInstance}" as="list" />
				</ul>
			</div>
		</g:hasErrors>

		
		<g:form name="updateForm" action="update" class="form-horizontal" method="PUT">	
			<g:hiddenField name="id" value="${gastoDetInstance.id}"/>
			<f:with bean="${gastoDetInstance}">
				<g:hiddenField name="concepto" value="${gastoDetInstance.concepto}"/>
				%{-- <f:field property="concepto" widget-class="form-control uppercase-field" cols="col-sm-8" colsLabel="col-sm-3"/> --}%
				
				<f:field property="descripcion" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="unidad" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="cantidad" 
					widget-class="form-control numeric" 
					widget-type="text"
					cols="col-sm-8" colsLabel="col-sm-3"/>
				<f:field property="valorUnitario" 
					widget-class="form-control numeric" 
					widget-type="text" cols="col-sm-8" 
					colsLabel="col-sm-3"/>
				<f:field property="cuentaContable"  cols="col-sm-8" colsLabel="col-sm-3">
					<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${gastoDetInstance?.cuentaContable?.id}"/>
					<input type="text" id="cuentaField" class="form-control" value="${gastoDetInstance?.cuentaContable}" >
				</f:field>
				<f:field property="comentario" widget-class="form-control" cols="col-sm-8" colsLabel="col-sm-3"/>
			</f:with>
		</g:form>
	
	</div>					
 
	<div class="panel-footer">
		<div class="btn-group">
			<g:link class="btn btn-default btn-sm" controller="gasto" action="edit" id="${gastoDetInstance.gasto.id}" >
				<span class="glyphicon glyphicon-hand-left"></span> Regresar
			</g:link>
			<button id="saveBtn" class="btn btn-success btn-sm">
					<span class="glyphicon glyphicon-ok"></span> Actualizar
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