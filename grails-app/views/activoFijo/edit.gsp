<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Activo fijo ${activoFijoInstance.id}</title>
	<asset:javascript src="forms/autoNumeric.js"/>
	<asset:stylesheet src="jquery-ui.css"/>
	<asset:javascript src="jquery-ui/autocomplete.js"/>
</head>
<body>
	
	<div class="container form-panel">



		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#activo" data-toggle="tab"> Activo</a>
			</li>
			<li>
				<a href="#depreciaciones" data-toggle="tab"> Depreciaciones</a>
			</li>
		</ul>

		<div class="tab-content">

			<g:if test="${flash.message}">
				<span class="label label-warning">${flash.message}</span>
			</g:if> 

			<div class="tab-pane active" id="activo">
				
				<div class="form-panel">
					<g:render template="editForm"/>	
				</div>
				

				<div class="col-md-10 col-md-offset-2">
					<div class="btn-group">
					    <lx:backButton/>
					    <g:link action="print" class="btn btn-default " id="${activoFijoInstance.id}">
					        <i class="fa fa-print"></i> Imprimir
					    </g:link> 
					    <g:link action="generarDepreciaciones" 
					    	class="btn btn-default " id="${activoFijoInstance.id}" 
					    	onclick="return confirm('Generar depreciaciones del activo?');">
					        <i class="fa fa-cog"></i> Generar depreciaciones
					    </g:link>
					    
					    <button id="saveBtn" class="btn btn-success">
					    	<i class="fa fa-floppy-o"></i> Actualizar
					    </button>

					    <lx:deleteButton bean="${activoFijoInstance}" />
					</div>
				</div>	
			</div>

			<div class="tab-pane" id="depreciaciones">
				<div class="row row-header">
					<div class="col-md-4">
						<input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
					</div>
					<div class="btn-group">
				    	<a href="#actualizarDialog" data-toggle="modal" class="btn btn-default"> Actualizar Costo</a>
				    </div>
					<div class="col-md-12 grid-panel">
						<g:render template="depreciacionesGrid"/>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="actualizarDialog" tabindex="-1">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Actualizar costos  (INPC)</h4>
				</div>
				<g:form action="actualizarDepreciaciones" class="form-horizontal" >
					<div class="modal-body">
						<div class="form-group">
							<label for="ejercicio" class="control-label col-sm-2">Ejercicio</label>
							<div class="col-sm-10">
								<g:select name="ejercicio" class="form-control" from="${2014..2019}" value="${session.ejercicio}"/>
							</div>
						</div>
						%{-- <div class="form-group">
							<label for="mes" class="control-label col-sm-2">Mes</label>
							<div class="col-sm-10">
								<g:select name="ejercicio" class="form-control" from="${1..12}" value="${session.mes}"/>
							</div>
						</div> --}%
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<g:submitButton class="btn btn-info" name="aceptar"
								value="Actualizar" />
					</div>
				</g:form>
	
			</div>
			<!-- moda-content -->
		</div>
		<!-- modal-di -->
	</div>

	<script type="text/javascript">
		$(function(){
			
			$('#cuentaContable,#valorOriginal,#depreciacionTasa').each(function(){
				$(this).attr("required",true);
			});

			$(".money").autoNumeric('init',{mRound:'B',aSign: '$'});
			$(".tc").autoNumeric('init',{vMin:'0.0000'});
			$(".porcentaje").autoNumeric('init',{vMin:'0.00',vMax:'99.00'});

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
	    		var button=$("#saveBtn");
	    		button.attr('disabled','disabled')
	    		 .html('Procesando...');
	    		$(".money,.porcentaje",this).each(function(index,element){
	    		  var val=$(element).val();
	    		  var name=$(this).attr('name');
	    		  var newVal=$(this).autoNumeric('get');
	    		  $(this).val(newVal);
	    		  console.log('Enviando elemento numerico con valor:'+name+" : "+val+ " new val:"+newVal);
	    		});
	    		return true;
			});	

			        $('#grid').dataTable({
			            responsive: true,
			            aLengthMenu: [[20, 40, 60, 100, -1], [20, 40,60, 100, "Todos"]],
			            "language": {
			            "url": "${assetPath(src: 'plugins/dataTables/dataTables.spanish.txt')}"
			            },
			            "order": [],
			            "info":     false,
				       	"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
			        });

			          $("#filtro").on('keyup',function(e){
			            var term=$(this).val();
			            $('#grid').DataTable().search(
			            $(this).val()
			                    
			            ).draw();
			        });
		});
	</script>
	
</body>
</html>