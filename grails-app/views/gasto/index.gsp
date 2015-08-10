<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Gastos</title>
	%{-- <asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/>  --}%
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">
				<div class="alert alert-info">
					<h4>
						<p class="text-center"> Registro de Gastos  </p>
						<p class="text-center"><small>${session.empresa.nombre}</small></p>
							
					</h4>
					<g:if test="${flash.message}">
						<span class="label label-warning">${flash.message}</span>
					</g:if> 
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row toolbar-panel">
		    <div class="col-md-6">
		    	<input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
		      </div>

		    <div class="btn-group">
		        
		        <g:link action="index" class="btn btn-default ">
		            <span class="glyphicon glyphicon-repeat"></span> Refrescar
		        </g:link>
		    </div>
		    <div class="btn-group">
		        <button type="button" name="operaciones"
		                class="btn btn-default dropdown-toggle" data-toggle="dropdown"
		                role="menu">
		                Operaciones <span class="caret"></span>
		        </button>
		        <ul class="dropdown-menu">
		            <li>
		                <g:link action="create" ><i class="fa fa-plus"></i> Nuevo</g:link>
		            </li>
		            
		            <li>
		            	<a href="#uploadFileDialog" data-toggle="modal" >
		            		<i class="fa fa-upload"></i></span> Importar CFDI
		            	</a>
		            </li>
		            <li>
		            	<g:link action="corregirProveedores">
		            		Corregir proveedores
		            	</g:link>
		            </li>
		        </ul>
		    </div>
		    <div class="btn-group">
		        <button type="button" name="reportes"
		                class="btn btn-default dropdown-toggle" data-toggle="dropdown"
		                role="menu">
		                Reportes <span class="caret"></span>
		        </button>

		        <ul class="dropdown-menu">
		              
		            
		        </ul>
		    </div>
		    
		</div>

		<div class="row">
			<div class="col-md-12">
				<table id="grid" class="table table-striped table-bordered table-condensed">

					<thead>
						<tr>
							<th>Id</th>
							<th>Proveedor</th>
							<th>UUID</th>
							<th>Folio</th>
							<th>Fecha</th>
							<th>Vencimiento</th>
							<th>Total</th>
							<th>Requisitado</th>
							<th>Pagos</th>
							<th>Saldo</th>
							%{-- <th>Modificado</th> --}%
						</tr>
					</thead>
					<tbody>
						<g:each in="${gastoInstanceList}" var="row">
							<tr id="${row.id}" class="${row?.acuseCodigoEstatus?.startsWith('S')?'success':'warning' }">
								<td >
									<g:link  action="show" id="${row.id}">
										${formatNumber(number:row.id,format:'####')}
									</g:link>
								</td>
								<td >
									<g:link  action="show" id="${row.id}">
										
										<abbr title="${row.proveedor.nombre}">
											${org.apache.commons.lang.StringUtils.left(row.proveedor.nombre,20)}
										</abbr>
									</g:link>
								</td>
								<td>
									<g:link  action="show" id="${row.id}">
										<abbr title="${row.uuid}">
											${org.apache.commons.lang.StringUtils.substringAfterLast(row.uuid,'-')}
										</abbr>
									</g:link>
								</td>
								
								<td>
									<abbr title="${row.folio}">
										${org.apache.commons.lang.StringUtils.right(row.folio,5)}
									</abbr>
								</td>
								<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
								<td><g:formatDate date="${row.vencimiento}" format="dd/MM/yyyy"/></td>
								<td>${formatNumber(number:row.total,type:"currency")}</td>
								<td>${formatNumber(number:row.requisitado,type:"currency")}</td>
								<td>${formatNumber(number:row.pagosAplicados,type:"currency")}</td>
								<td>${formatNumber(number:row.saldo,type:"currency")}</td>
								%{-- <td>
									<small>
										<g:formatDate date="${row.lastUpdated}" format="dd/MM/yyyy HH:mm"/>
									</small>
									
								</td> --}%
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${gastoInstanceCount ?: 0}"/>
				</div>
			</div>
		</div> <!-- end .row 2 -->
		%{-- <g:render template="uploadXmlFile"/> --}%
		<g:render template="uploadXmlFile"/>

	</div>


	<script type="text/javascript">
		$(document).ready(function(){
			
			// $('#grid').dataTable( {
	  //       	"paging":   false,
	  //       	"ordering": false,
	  //       	"info":     false
	  //       	,"dom": '<"toolbar col-md-4">rt<"bottom"lp>'
	  //   	} );
 			$('#grid').dataTable({
                responsive: true,
                "language": {
					"url": "${assetPath(src: 'plugins/dataTables/dataTables.spanish.txt')}"
	    		},
	    		"dom": 'T<"clear">lfrtip',
	    		"tableTools": {
	    		    "sSwfPath": "${assetPath(src: 'plugins/dataTables/swf/copy_csv_xls_pdf.swf')}"
	    		},
	    		"order": []
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