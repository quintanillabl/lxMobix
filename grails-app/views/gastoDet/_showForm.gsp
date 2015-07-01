<div class="panel panel-primary">
	<div class="panel-heading">Gasto ${gastoInstance.id} ${gastoInstance.proveedor}</div>
  	
	<div class="panel-body ">
	
		<legend>  <span id="conceptoLabel">${gastoDetInstance.concepto}</span></legend>
		<g:hasErrors bean="${gastoDetInstance}">
			<div class="alert alert-danger">
				<ul class="errors" >
					<g:renderErrors bean="${gastoDetInstance}" as="list" />
				</ul>
			</div>
		</g:hasErrors>
		
		<form class="form-horizontal">	
			<f:with bean="${gastoDetInstance}">
				<f:display property="descripcion"/>
				<f:display property="unidad"/>
				<f:display property="cantidad"/>
				<f:display property="valorUnitario"  label="Precio"/>
				<f:display property="cuentaContable" label="Cuenta"/>
				<f:display property="comentario" />
			</f:with>
		</form>
	</div>					
 
	<div class="panel-footer">
		<div class="btn-group">
			<g:link class="btn btn-default btn-sm" controller="gasto" action="show" id="${gastoInstance.id}" >
				<span class="glyphicon glyphicon-hand-left"></span> Regresar
			</g:link>
		</div>
	</div>

</div>
