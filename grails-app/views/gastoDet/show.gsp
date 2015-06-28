<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Partida de gasto </title>
	
</head>
<body>

	<div class="container">

		<div class="row catalogo-panel">

			<div class="col-md-6 col-md-offset-3 ">

				<g:if test="${flash.message}">
					<div class="alert alert-warning">
						${flash.message}
					</div>
				</g:if> 

				<g:render template="showForm"/> 

				<g:if test="${flash.error}">
					<div class="alert alert-danger">
						${flash.error}
					</div>
				</g:if> 
			
			</div>
			
			

		</div> <!-- end .row 1 -->
	</div>
	
	
</body>
</html>