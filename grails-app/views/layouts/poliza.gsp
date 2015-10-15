
<g:applyLayout name="application">

<html>
<head>
    <title><g:layoutTitle/></title>
    <g:layoutHead/>
</head>
</html>
  <body>

    <div class="container-fluid">
      <div class="row">
        <div class="col-md-12">
          
          <div class="alert alert-info">
            <h4>
              <p class="text-center">  Polizas ${subTipo} del periodo: 
                <a href="#" data-target="#periodoDialog" data-toggle="modal">${periodo?.mes} - ${periodo?.ejercicio} </a> </p>
              <p class="text-center">
                <small>
                  ${session.empresa.nombre} 
                  
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
          	<div class="col-md-3">

            	<div class="list-group">
                
                <g:link class="list-group-item ${subTipo=='TODAS'?'active':''}" 
                  action="index" params="[subTipo:'TODAS']">
                  <i class="fa fa-bars"></i> Todas
                </g:link>

                <g:each in="${procesadores}" var="row">
                  
                  <g:link class="list-group-item ${subTipo==row.nombre?'active':''}" 
                    action="index" params="[subTipo:row.nombre]">
                    <i class="fa fa-bars"></i> ${row.label}
                  </g:link>

                </g:each>
                
                
  				    </div>
          	</div><!-- End .col-md-2 side bar -->

            <div class="col-md-9">
                <g:pageProperty name="page.document"/>    
            </div>
          </div>
          <g:pageProperty name="page.javascript"/>    
      </div>
      
  </body>
</g:applyLayout>