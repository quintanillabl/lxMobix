
<!doctype html>
<html>
<head>
	
	<title>Auxiliar contable (Balanza)</title>
	<asset:javascript src="forms/forms.js"/>
	<asset:stylesheet src="datatables/jquery.dataTables.css"/>
	<asset:javascript src="datatables/jquery.dataTables.js"/> 
</head>
<body>

	<div class="container">
		<div class="row row-header"> <%-- Header --%>
              <div class="col-md-12">
                <div class="alert alert-info">
                  <h4>
                    <p class="text-center"> 
                    	<g:link action="show" id="${saldo.id}">
                    		Auxiliar contable cuenta: ${saldo.cuenta} 
                    	</g:link>
                      
                    </p>
                    <p class="text-center"> 
                      (<small>${session.empresa.nombre}</small>)
                    </p>
                    <p class="text-center"> 
                      <small>
                      	S. Inicial: ${lx.moneyFormat(number:saldo.saldoInicial)}
                      	S. Final: ${lx.moneyFormat(number:saldo.saldoFinal)}
                      </small>
                    </p>
                  </h4>
                  <g:if test="${flash.message}">
                    <span class="label label-warning">${flash.message}</span>
                  </g:if> 
                </div>
              </div>
		</div>

		<div class="row">
			<div class="col-md-2">
				<input type='text' id="filtro" 	placeholder="Filtrar" class="form-control" autofocus="on">
			</div>
			<div class="col-md-1">
			    <div class="btn-group">
			        <button type="button" name="reportes"
			                class="btn btn-default dropdown-toggle" data-toggle="dropdown"
			                role="menu">
			                Reportes <span class="caret"></span>
			        </button>
			        <ul class="dropdown-menu">
        		    	<li>
 							<g:jasperReport
 								controller="saldoPorCuentaContable"
 		 						action="imprimirAuxiliarContable" 
 		 						jasper="AuxiliarContable" 
 		 						format="PDF" 
 		 						name="Imprimir auxiliar">
 								<g:hiddenField name="id" value="${saldo.id}"/>
 							</g:jasperReport>
        		    	</li>
			        </ul>
			    </div>
			</div>
			<div class="col-md-9">
				<form class="form-inline">
					%{-- <div class="form-group">
					    <label for="exampleInputEmail1"> Inicial </label>
					    <input id="totalDebe" class="form-control"  >
					</div> --}%
					<div class="form-group">
					    <label for="totalDebe"> Debe</label>
					    <input class="form-control" id="totalDebe" >
					</div>
					<div class="form-group">
					    <label for="totalHaber"> Haber</label>
					    <input class="form-control" id="totalHaber" >
					</div>
					<g:link action="imprimirAuxiliarContable" id="${saldo.id}" class="btn btn-default">
						<i class="fa fa-print"></i> Reporte
					</g:link>
					%{-- <div class="form-group">
					    <label for="totalCuadre"> Cuadre</label>
					    <input class="form-control" id="totalCuadre" >
					</div> --}%
				</form>
				
			</div>
		</div>

		<div class="row">
			<div class="col-md-12 grid-panel">
						
						<table id="grid" 
							class="table table-striped table-hover table-bordered table-condensed ">
							<thead>
								<tr>
									<td>Poliza</td>
									<th>Cuenta</th>
									<th>Concepto</th>
									<th>Debe</th>
									<th>Haber</th>
									<th>Descripcion</th>
									<th>Referencia</th>
									<th>Asiento</th>
									%{-- <th>Entidad</th> --}%
									<th>Origen</th>
									
									
								</tr>
							</thead>
							<tbody>
								<g:each in="${partidas}" var="row">
									<tr id="${row.id}">
										<td>
										<g:link controller="poliza" action="mostrarPoliza" target="_blank" id="${row.poliza.id}">
											${row.poliza.tipo }- ${row.poliza.folio  }
										</g:link>
										</td>
										<td>${fieldValue(bean: row, field: "cuenta.clave")}</td>
										<td>${fieldValue(bean: row, field: "cuenta.descripcion")}</td>
										<td><g:formatNumber number="${row.debe}" format="########.##"/></td>
										<td><g:formatNumber number="${row.haber}" format="########.##"/></td>
										<td>${fieldValue(bean: row, field: "descripcion")}</td>
										<td>${fieldValue(bean: row, field: "referencia")}</td>
										<td>${fieldValue(bean: row, field: "asiento")}</td>
										%{-- <td>
											${org.apache.commons.lang.StringUtils.abbreviate(row.entidad,10,10)}
										</td> --}%
										<td><g:formatNumber number="${row.origen}" format="########"/></td>
									</tr>
								</g:each>
							</tbody>
							<tfoot>
								<tr>
									<th>Poliza</th>
									<th>Cuenta</th>
									<th>Concepto</th>
									<th>Debe</th>
									<th>Haber</th>
									<th>Descripcion</th>
									<th>Referencia</th>
									<th>Asiento</th>
									%{-- <th>Entidad</th> --}%
									<th>Origen</th>
								</tr>
							</tfoot>
						</table>
			</div>
		</div>
		
			<script type="text/javascript">
				
				$(function(){
					//$(".money").autoNumeric({vMin:'-999999999.00',wEmpty:'zero',mRound:'B'});
		 			$('#grid').dataTable({
		                responsive: true,
		                "paging":   false,
		                "ordering": false,
		                "info":     false,
		                "dom": '<"toolbar col-md-4">rt<"bottom"lp>',
			    		"fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
				         	var debe=0;
				         	var haber=0;
				         	var cuadre=0;
				         	for(var i=iStart;i<iEnd;i++){
				         		
				         		var d1=parseFloat(aaData[ aiDisplay[i] ][3]);
				         		var d2=parseFloat(aaData[ aiDisplay[i] ][4]);
				         		debe+=d1;
				         		haber+=d2;
				         		
				         		
				         	}
		         	
				         	cuadre=debe-haber
				         	$('#totalDebe').val(debe)
				         	$('#totalHaber').val(haber)
				         	//$('#totalCuadre').val(cuadre)
				         	
		         		}
		            });
                	$("#filtro").on('keyup',function(e){
                		var term=$(this).val();
                		$('#grid').DataTable().search(
            				$(this).val()
                		        
                		).draw();
                	});


					// $("#cuenta").autocomplete({
					// 		source:'<g:createLink controller="cuentaContable" action="cuentasDeDetalleJSONList"/>',
					// 		minLength:3,
					// 		select:function(e,ui){
					// 			//console.log('Valor seleccionado: '+ui.item.id);
					// 			$("#cuentaId").val(ui.item.id);
					// 		}
					// });
					
				});
		
			</script>
	</div>
	
	
 	




</body>
</html>



