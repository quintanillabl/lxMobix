<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Balanza: ${balanzaInstance.id}</title>
</head>
<body>
	<div class="container">
	
		<div class="row form-panel">
			<div class="col-md-12">
				<div class="panel ${balanzaInstance.enviado?'panel-success':'panel-primary'}">
					<!-- Default panel contents -->
  					<div class="panel-heading">
  						Catalogo de cuentas:  ${balanzaInstance.nombre}
  					</div>
  					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<g:render template="form"/>
							</div>
							<div class="col-md-6">
								<g:render template="estadistica"/>
							</div>
							
						</div>
  					</div>
  					<div class="panel-footer">
  						<div class="btn-group">

  							<g:link class="btn btn-default btn-sm" action="index" >
								<i class="fa fa-step-backward"></i> Balanzas</span>
							</g:link>
  						
  							<g:link class="btn btn-default btn-sm" action="mostrarXml" resource="${balanzaInstance}">Ver XML</g:link>
  							
							<g:link class="btn btn-default btn-sm" action="descargarXml" resource="${balanzaInstance}">
								<span class="glyphicon glyphicon-cloud-download"> Descargar XML</span>
							</g:link>
							
							<a href="#uploadAcuseDialog" class="btn btn-default btn-sm" data-toggle="modal">
								<span class="glyphicon glyphicon-cloud-upload"> </span> Registrar acuse
							</a>

							<a href="#uploadAcuseDeAceptacionDialog" class="btn btn-default btn-sm" data-toggle="modal">
								<span class="glyphicon glyphicon-cloud-upload"> </span> Acuse de aceptacion
							</a>

							<g:if test="${balanzaInstance.acuse}">
								<g:link  action="descargarAcuseXml" 
									id="${balanzaInstance.id}"
									class="btn btn-default btn-sm" >
									<span class="glyphicon glyphicon-cloud-download"></span> Descargar acuse
								</g:link>
								%{-- <g:link class="btn btn-default btn-sm" action="mostrarAcuseXml" resource="${balanzaInstance}">
									Ver Acuse
								</g:link> --}%
							</g:if>

							<g:if test="${balanzaInstance.acuseDeAceptacion}">
								<g:link class="btn btn-default btn-sm" action="descargarAcuseDeAceptacion" resource="${balanzaInstance}">
									<span class="glyphicon glyphicon-cloud-download"></span> Acuse de aceptacion
								</g:link>
							</g:if>
							
							<a class="btn btn-default btn-sm"
								onclick="popupCenter('https://ceportalvalidacionprod.clouda.sat.gob.mx', 'myPop1',850,650);"
								 href="javascript:void(0);">Validar  (SAT)</a>

							<g:link  action="delete" class="btn btn-default btn-sm"  
										onclick="return confirm('Eliminar Balanza: ${balanzaInstance.id }?');" id="${balanzaInstance.id }">
								<span class="glyphicon glyphicon-remove-circle"></span> Eliminar
							</g:link>
							
							
							
  						</div>
						
					</div>
				</div>

			</div>

		</div><!-- end .row -->
		
		<g:render template="uploadAcuse"/>
		<g:render template="uploadAcuseDeAceptacion"/>
	
		
	</div>
	<script type="text/javascript">
		function popupCenter(url, title, w, h) {
			var left = (screen.width/2)-(w/2);
			var top = (screen.height/2)-(h/2);
			return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		}
	</script>
	
</body>
</html>

