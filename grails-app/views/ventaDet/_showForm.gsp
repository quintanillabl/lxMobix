<g:form class="form-horizontal" action="save" >	

	<div class="panel panel-primary">
		<div class="panel-heading">Agregar partida a venta ${ventaInstance.folio} ${ventaInstance.cliente} (${ventaInstance.tipo})</div>
		<div class="panel-body col-md-10">
		<fieldset>
			<legend>  <span id="productoLabel">Seleccione un producto</span></legend>
			<g:hasErrors bean="${ventaDetInstance}">
				<div class="alert alert-danger">
					<ul class="errors" >
						<g:renderErrors bean="${ventaDetInstance}" as="list" />
					</ul>
				</div>
			</g:hasErrors>
			<g:form name="ventaDetForm" class="form-horizontal">	
			
				<g:hiddenField name="venta.id" value="${ventaInstance.id}"/>
				
				
				
				<div class="form-group">
					<label for="producto" class="col-sm-3 control-label">Producto</label>
					<div class="col-sm-9">
						
						<g:hiddenField id="productoId" name="producto.id" autocomplete="off"/>
						<input id="producto" class="form-control" 
							name="producto.descripcion"
							type="text"  autofocus="on" palceholder="Seleccione un producto/servicio"
							autocomplete="off">
					</div>
				</div>
				
				<div class="form-group">
					<label for="cantidad" class="col-sm-3 control-label">Cantidad</label>
					<div class="col-sm-4">
						<input id="cantidad" class="form-control"
							value="${ventaDetInstance?.cantidad}" 
							name="cantidad"
							type="text"  autocomplete="off">
					</div>
				</div>

				<div class="form-group">
					<label for="precio" class="col-sm-3 control-label ">Precio</label>
					<div class="col-sm-4">
						<input id="precio" class="form-control data-moneda" 
							name="precio"  value="${ventaDetInstance?.precio }"
							autocomplete="off">
					</div>
				</div>

				<div class="form-group">
					<label for="descuentoTasa" class="col-sm-3 control-label">Descuento (%)</label>
					<div class="col-sm-4">
						<input id="descuentoTasa" class="form-control data-descuento" 
							name="descuentoTasa"  
							value="${ventaDetInstance?.descuentoTasa }"
							autocomplete="off" >
					</div>
				</div>
			
			</g:form>
		</fieldset>
		</div>					
	 
		<div class="panel-footer">
			<div class="btn-group">
				<g:link class="btn btn-default btn-sm" controller="venta" action="show" id="${ventaInstance.id}" >
					<span class="glyphicon glyphicon-hand-left"></span> Regresar
				</g:link>
				<button id="next" class="btn btn-success btn-sm">
						<span class="glyphicon glyphicon-ok"></span> Salvar
					</button>
			</div>
		</div><!-- end .panel-footer -->

	</div>

</g:form>


<script type="text/javascript">
	$(document).ready(function(){
		$(".data-moneda").autoNumeric({wEmpty:'zero',aSep:""});
		$(".data-descuento").autoNumeric({vMin: '0', vMax: '99.9999'})
		$("#producto").autocomplete({
			source:'<g:createLink controller="producto" action="getProductosAsJSON"/>',
            minLength: 2,
            select:function(e,ui){
				console.log('Producto seleccionado: '+ui.item.value+ 'Precio: '+ui.item.precio);
				$("#productoId").val(ui.item.id);
				$("#productoLabel").text(ui.item.descripcion);
				$("#precio").autoNumeric('set', ui.item.precio);
				validar();		
			}
		});
		$("#cantidad").on('blur',function(e){
			validar();
		});

		$("input[type=submit]").attr("disabled", "disabled");
		
		

		var validar=function(){
			var cantidad= $("#cantidad").val();
			var producto= $("#productoId").val();
			var res=( cantidad>0 && producto>0 );
			console.log('Valido cantidad: '+cantidad);
			console.log('Valido producto: '+producto);
			if(res){
				$("input[type=submit]").removeAttr("disabled");
				$("#next").removeAttr("disabled");
			}else{
				$("input[type=submit]").attr("disabled", "disabled");
				$("#next").attr("disabled", "disabled");
			}
		};

		$("#next").attr("disabled", "disabled");
		$("#next").click(function(){
			console.log('Salvar la forma');
			$("#ventaDetForm").submit();
		});
		
	});
</script>