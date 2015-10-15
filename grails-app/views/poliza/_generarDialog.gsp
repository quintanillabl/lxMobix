<div class="modal fade" id="generarDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					Generar póliza tipo ${subTipo}
					
				</h4>
			</div>
			<g:form action="generar" class="form-horizontal" >
				<div class="modal-body">
					<f:with bean="${new com.luxsoft.lx.contabilidad.GeneradorDePolizaCommand()}">
						<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
						<g:hiddenField name="tipo" value="${subTipo}"/>
						<g:hiddenField name="ejercicio" value="${session.periodoContable.ejercicio}"/>
						<g:hiddenField name="mes" value="${session.periodoContable.mes}"/>
						<g:hiddenField name="procesador.id" value="${procesador?.id}"/>
						<fieldset disabled>
							<f:field property="ejercicio" widget-class="form-control" value="${session.periodoContable.ejercicio}"/>
							<f:field property="mes" widget-class="form-control" value="${session.periodoContable.mes}"/>
						</fieldset>
						
						<f:field property="fecha" />
					</f:with>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<g:submitButton class="btn btn-info" name="aceptar"
							value="Generar" />
				</div>
			</g:form>

		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->
</div>