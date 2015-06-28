<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Arrendamiento</title>
	<asset:stylesheet src="datatables/dataTables.css"/>
	<asset:javascript src="datatables/dataTables.js"/> 
	<asset:javascript src="forms/autoNumeric.js"/>
</head>
<body>

	<div class="container">
		
		<div class="row">

			<div class="col-md-12">

				<div class="page-header">
				  <h1>Alta de arrendamiento<p><small>${session.empresa}</small></p>
				  	<g:if test="${flash.message}">
				  		<small><span class="label label-warning ">${flash.message}</span></small>
				  	</g:if> 
				  </h1>
				</div>
			</div>
		</div><!-- end .row -->

		<div class="row ">
			
			<div class="col-md-6 col-md-offset-3">
				
				<g:form class="form-horizontal" action="save" >	

					<div class="panel panel-primary">
					  <div class="panel-body">
					    <g:hasErrors bean="${arrendamientoInstance}">
					    	<div class="alert alert-danger">
					    		<ul class="errors" >
					    			<g:renderErrors bean="${arrendamientoInstance}" as="list" />
					    		</ul>
					    	</div>
					    	
					    </g:hasErrors>
						<f:with bean="${arrendamientoInstance}">
							<g:hiddenField name="empresa.id" value="${session.empresa.id}"/>
							<f:field property="cliente" widget-class="form-control"/>
							<f:field property="inmueble" widget-class="form-control"/>
								%{-- <g:select class="form-control"  
									name="${property}" 
									value="${value?.id}"
									from="${com.luxsoft.mobix.core.Inmueble.findAll(
    "from Inmueble i where i.empresa=? and i.id not in(select a.inmueble from Arrendamiento a)"
    ,[session.empresa])}" 
									optionKey="id" 
									optionValue="clave"
									noSelection="[null:'Seleccione un inmueble']"
									/> --}%
							%{-- </f:field> --}%
							<f:field property="tipo" widget-class="form-control"/>
							<f:field property="diaDeCorte" widget-class="form-control">
								<g:select class="form-control"  
									name="${property}" 
									value="${value}"
									from="${(1..20)}" 
									/>
							</f:field>
							<f:field property="envioAutomatico" widget-class="form-control"/>
							<f:field property="facturacionAutomatica" widget-class="form-control"/>
							<f:field property="inicio" widget-class="form-control"/>
							<f:field property="fin" widget-class="form-control"/>
							<f:field property="precio" widget-class="form-control moneda-field " widget-type="text"/>
							<f:field property="comentario" widget-class="form-control"/>
						</f:with>
						
					  </div>
					
					 
					  <div class="panel-footer">
					  	
					  	<div class="form-group">
					  		<div class="buttons col-md-offset-2 col-md-4">
					  			<g:submitButton name="Salvar" class="btn btn-primary " />
					  			<g:link action="index" class="btn btn-default"> Cancelar</g:link>
					  		</div>
					  	</div>
					  </div>

					</div>

				</g:form>
				
			</div>
		</div> <!-- end .row 2 -->

	</div>

<script type="text/javascript">
	$(function(){
		$(".moneda-field").autoNumeric({wEmpty:'zero',aSep:""});
	});
</script>
	
</body>
</html>