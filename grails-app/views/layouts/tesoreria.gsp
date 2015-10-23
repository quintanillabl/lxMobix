<g:applyLayout name="application">
<html>
  <head>
      <title><g:layoutTitle/></title>
      <g:layoutHead/>
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
              <div class=" ">
                  
                  <div class="col-md-3">

                      <div class="input-group">
                              <span class="input-group-btn">
                                  <button data-target="#periodoDialog" data-toggle="modal" class="btn  btn-outline btn-success ">
                              <i class="fa fa-calendar"></i> 
                          </button>
                              </span>
                          <input type='text' id="filtro" placeholder="Filtrar" class="form-control" autofocus="on">
                      </div>
                      
                  </div>
                  <div class="col-md-4">
                      <g:form class="form-horizontal" action="show">
                          <g:hiddenField id ="searchId" name="id" />
                          <div class="input-group">
                              <input id="searchField" name="searchDesc" type="text" 
                                  class="form-control " placeholder="Buscar registro">
                              <span class="input-group-btn">
                                  <button id="buscarBtn" type="submit" class="btn btn-default" disabled="disabled">
                                      <i class="fa fa-search"></i></span>
                                  </button> 
                              </span>
                          </div>
                      </g:form>
                  </div>  

                  <div class="btn-group">
                      <lx:refreshButton/>
                      <lx:createButton/>
                  </div>
                  <div class="btn-group">
                      <button type="button" name="operaciones"
                              class="btn btn-success btn-outline dropdown-toggle" data-toggle="dropdown"
                              role="menu">
                              Operaciones <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                          <g:pageProperty name="page.operaciones"/>
                      </ul>
                  </div>
                  <div class="btn-group">
                      <button type="button" name="reportes"
                              class="btn btn-primary btn-outline dropdown-toggle" data-toggle="dropdown"
                              role="menu">
                              Reportes <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                          <g:pageProperty name="page.reportes"/>
                      </ul>
                  </div>
              </div>
            </div>

        <g:pageProperty name="page.javascript"/>    
        </div>

    </body>
</g:applyLayout>


