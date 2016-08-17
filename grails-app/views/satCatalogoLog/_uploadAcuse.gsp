<div class="modal fade" id="uploadAcuseDialog" tabindex="-1">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Cargar Acuse de recepcion del SAT</h4>
			</div>
			
			<g:uploadForm name="uploadAcuse" class="form-horizontal" action="uploadAcuse" >
				<div class="modal-body">

					<f:with bean="${new lx.econta.catalogos.RegistroDeAcuseCommand()}">
						<g:hiddenField name="catalogo" value="${catalogoInstance.id}"/>
						<f:field property="fecha" widget-class="form-control"/>
						<div class="form-group">
						    <label for="inputFile" class="col-sm-2 control-label"> Catalogo</label>
						    <div class="col-sm-10">
						    	<input type="file" id="inputFile" name="file"  required 
						    		class="form-control">
						    	<p class="help-block">Seleccion el archivo XML</p>
						    </div>
						</div>
					</f:with>
					
					

					%{-- <div class="form-group">
					    <label for="inputFile" class="col-sm-4 control-label"> Fecha</label>
					    <div class="col-sm-8">
					    	<input type="text" 
					    		name="fecha"  
					    		id="fechaFile" 
					    		required 
					    		class="form-control">
					    </div>
					</div> --}%
					

					

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<g:submitButton class="btn btn-primary" name="aceptar"value="Aceptar" />
				</div>
			</g:uploadForm>


		</div>
		<!-- moda-content -->
	</div>
	<!-- modal-di -->
</div>

