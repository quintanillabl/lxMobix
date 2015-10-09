<g:applyLayout name="application">

<html>
<head>
    <title><g:layoutTitle/></title>
    <g:layoutHead/>
</head>
</html>
  <body>

    <div class="container">
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
          	<div class="col-md-2">

            	<div class="list-group">
                
                <g:link class="list-group-item ${subTipo=='TODAS'?'active':''}" 
                  action="index" params="[subTipo:'TODAS']">
                  <i class="fa fa-bars"></i> TODAS
                </g:link>

                <g:link class="list-group-item ${subTipo=='FACTURACION'?'active':''}" 
                  action="index" params="[subTipo:'FACTURACION']">
                  <i class="fa fa-bars"></i> Facturación
                </g:link>
                <g:link class="list-group-item ${subTipo=='GENERICA'?'active':''}" 
                  action="index" params="[subTipo:'GENERICA']">
                  <i class="fa fa-bars"></i> GENERICA
                </g:link> 
  				    </div>
          	</div><!-- End .col-md-2 side bar -->

            <div class="col-md-10">
                <g:pageProperty name="page.document"/>    
            </div>
          </div>
          <g:pageProperty name="page.javascript"/>    
      </div>
      
  </body>
</g:applyLayout>