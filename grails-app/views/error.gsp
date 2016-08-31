<!DOCTYPE html>
<html>
	<head>
		<title>Luxor Backend Error</title>
		<asset:stylesheet src="errors.css"/>
		<meta name="layout" content="application"/>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<g:if env="development">
						%{-- <g:renderException exception="${exception}" /> --}%
					</g:if>
					
					<div class="well">
						<h4>Error interno en Luxor Engine</h4>
					</div>
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>Mensaje</th>
									<th>Descripción</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Error</td>
									<td class="danger">${org.apache.commons.lang.exception.ExceptionUtils.getRootCauseMessage(exception)}</td>
								</tr>
								<tr>
									<td>Tipo</td>
									<td class="danger">${org.apache.commons.lang.exception.ExceptionUtils.getRootCause(exception).class}</td>
								</tr>
								<tr>
									<td>Hora</td>
									<td><g:formatDate date="${new Date()}" format="dd/MM/yyyy mm:ss"/></td>
								</tr>
								<tr>
									<td>Controlador</td>
									<td>${controllerName}</td>
								</tr>
								<tr>
									<td>Acción</td>
									<td>${actionName}</td>
								</tr>
							</tbody>
						</table>
					
					
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<g:if env="development">
						<g:renderException exception="${exception}" />
					</g:if>
				</div>
			</div>
		</div>
		
	</body>
</html>

%{-- <!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>An error has occurred</li>
			</ul>
		</g:else>
	</body>
</html> --}%
