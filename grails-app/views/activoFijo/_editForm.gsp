<g:hasErrors bean="${activoFijoInstance}">
	<div class="alert alert-danger">
		<ul class="errors" >
			<g:renderErrors bean="${activoFijoInstance}" as="list" />
		</ul>
	</div>
</g:hasErrors>	
<div class="">
	<g:form name="updateForm" class="form-horizontal" action="update" method="PUT" >
		<g:hiddenField name="id" value="${activoFijoInstance.id}"/>
		<g:hiddenField name="version" value="${activoFijoInstance.version}"/>
		<div class="col-md-6">
			<f:with bean="${activoFijoInstance}">

				
				<f:field property="gastoDet" wrapper="bootstrap3" label="Gasto">
					<input type="text" name="gastoDet.id" value="" class="form-control">
				</f:field>

				<f:display property="adquisicion" wrapper="bootstrap3"/>
				
				<f:field property="cuentaContable"  wrapper="bootstrap3" label="Cuenta">
					<g:hiddenField id="cuentaId" name="cuentaContable.id" value="${activoFijoInstance?.cuentaContable?.id}"/>
					<input type="text" id="cuentaField" class="form-control" value="${activoFijoInstance?.cuentaContable}" >
				</f:field>
				
				<f:field property="descripcion" 
					widget-class="form-control "  wrapper="bootstrap3"/>

				<f:field property="factura" label="Factura"
					widget-class="form-control "  
					wrapper="bootstrap3"/>

				<f:field property="serie" label="No Serie"
					widget-class="form-control "  wrapper="bootstrap3"/>

				<f:field property="modelo" 
					widget-class="form-control "  wrapper="bootstrap3"/>

				
			</f:with>
		</div>
		<div class="col-md-6">
			<f:with bean="${activoFijoInstance}">
				<f:field property="valorOriginal" 
					widget="money" wrapper="bootstrap3"/>

				<f:field property="valorDepreciable" label="Depreciable" 
					widget="money" wrapper="bootstrap3"/>

				<f:field property="tipoDeDepreciacion" wrapper="bootstrap3" label="Tipo"/>

				<f:field property="depreciacionTasa" label="Tasa(%)"
					widget="porcentaje" wrapper="bootstrap3"/>

				<f:field property="consignatario" 
					widget-class="form-control "  wrapper="bootstrap3"/>

				<f:field property="comentario" 
					widget-class="form-control "  wrapper="bootstrap3"/>
			</f:with>
		</div>
	</g:form>	
</div>
