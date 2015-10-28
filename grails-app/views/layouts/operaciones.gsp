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
                    <p class="text-center"> 
                      <g:pageProperty name="page.header"/>
                    </p>
                    <p class="text-center"> 
                      (<small>${session.empresa.nombre}</small>)
                    </p>
                  </h4>
                  <g:if test="${flash.message}">
                    <span class="label label-warning">${flash.message}</span>
                  </g:if> 
                </div>
              </div>

            </div>

            <div class="row toolbar-panel">
              <div id="toolbar" class="col-md-4">
                <input type='text' id="filtro" 
                  placeholder="Filtrar" class="form-control" autofocus="on">
              </div>

                %{-- <div class="col-md-4">
                  
                  <div class="input-group">
                          
                      <input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
                  </div>
                </div> --}%

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

                
            });
        </script>  
        </div>

        <g:pageProperty name="page.javascript"/>    

    </body>
</g:applyLayout>


