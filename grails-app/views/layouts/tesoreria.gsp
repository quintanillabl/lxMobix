<g:applyLayout name="application">
<html>
  <head>
      <title><g:layoutTitle/></title>
      <g:layoutHead/>
      <asset:stylesheet src="jquery-ui.css"/>
      <asset:javascript src="jquery-ui/autocomplete.js"/>
  </head>
</html>
    <body>
        <div class="container">
            <div class="row row-header"> <%-- Header --%>
              <div class="col-md-12">
                <div class="alert alert-info">
                  <h4>
                    <p class="text-center"> <g:pageProperty name="page.header"/>  (<small>${session.empresa.nombre}</small>)</p>
                    <p class="text-center"><small>${session.periodoTesoreria.asPeriodoText()}</small></p>
                  </h4>
                  <g:if test="${flash.message}">
                    <span class="label label-warning">${flash.message}</span>
                  </g:if> 
                </div>
              </div>

            </div>

            <div class="row toolbar-panel">
                <div class="col-md-4">
                  %{-- <input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on"> --}%
                  <div class="input-group">
                          <span class="input-group-btn">
                              <button data-target="#periodoDialog" data-toggle="modal" class="btn  btn-outline btn-success ">
                                <i class="fa fa-calendar"></i> 
                              </button>
                          </span>
                      <input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
                  </div>
                </div>

                <div class="btn-group">
                    <lx:refreshButton/>
                    <lx:createButton/>
                </div>
                <div class="btn-group">
                    <button type="button" name="operaciones"
                            class="btn btn-default btn-outline dropdown-toggle" data-toggle="dropdown"
                            role="menu">
                            Operaciones <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <g:pageProperty name="page.operaciones"/>
                    </ul>
                </div>
                <div class="btn-group">
                    <button type="button" name="reportes"
                            class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                            role="menu">
                            Reportes <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                      <g:pageProperty name="page.reportes"/>
                    </ul>
                </div>
                <div class="modal fade" id="periodoDialog" tabindex="-1">
                    <div class="modal-dialog ">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Cambiar periodo</h4>
                            </div>
                            <g:form action="cambiarPeriodo" class="form-horizontal" >
                                <div class="modal-body">
                                  <div class="form-group">
                                    <label for="property" class="control-label col-sm-2">Periodo</label>
                                    <div class="col-sm-10">
                                      <input id="fechaField"
                                        type="text" class="form-control " name="fecha"
                                          value="${formatDate(date:session.periodoTesoreria,format:'dd/MM/yyyy')}">
                                    </div>
                                  </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <g:submitButton class="btn btn-info" name="aceptar"
                                            value="Aceptar" />
                                </div>
                            </g:form>
                
                        </div>
                        <!-- moda-content -->
                    </div>
                    <!-- modal-di -->
                </div>
            </div>

            <div class="row">
              <div class="col-md-12">
                <g:pageProperty name="page.grid"/>    
              </div>
            </div>

        
        
        <script type="text/javascript">
            $(function(){
                $('#grid').dataTable({
                    responsive: true,
                    aLengthMenu: [[20, 40, 60, 100, -1], [20, 40,60, 100, "Todos"]],
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

                $("#fechaField").datepicker({
                  showAnim: "fadeIn",
                  showOptions: { direction: "up" },
                  format: 'dd/mm/yyyy',
                });
            });
        </script>  
        </div>

        <g:pageProperty name="page.javascript"/>    

    </body>
</g:applyLayout>


