<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Nómina asimilado ${nominaAsimiladoInstance?.id}</title>
	
</head>
<body>

	<div class="container form-panel">


		<div class="row toolbar-panel">
			<div class="col-md-10 col-md-offset-1">
				<div class="btn-group">
				    <g:link action="index" class="btn btn-default ">
				        <i class="fa fa-step-backward"></i> Nominas
				    </g:link>

				    <g:link action="print" class="btn btn-default " id="${nominaAsimiladoInstance.id}">
				        <i class="fa fa-print"></i> Imprimir
				    </g:link> 
				    <g:link action="edit" class="btn btn-default " id="${nominaAsimiladoInstance.id}">
				        <i class="fa fa-pencil"></i> Editar
				    </g:link> 
					
						                    <g:if test="${!nominaAsimiladoInstance.cfdi}">
						                    	<div class="btn-group">
						                    		<g:link class="btn btn-default btn-outline" action="generarCfdi" id="${nominaAsimiladoInstance.id}" >
						                    	    	<i class="fa fa-file-excel-o"></i> Generar CFDI
						                    	    </g:link>
						                    	</div>
						                    </g:if>
						                    <g:if test="${nominaAsimiladoInstance.cfdi}">
						                    	<g:link class="btn btn-default btn-outline" 
					  								action="mostrarXml" id="${nominaAsimiladoInstance.cfdi.id}">XML
					  							</g:link>
					  							<g:link class="btn btn-default btn-outline" action="descargarXml" id="${nominaAsimiladoInstance.cfdi.id}" >
													<span class="glyphicon glyphicon-cloud-download"> Descargar XML</span>
												</g:link>
					  							
						                    </g:if>
						                    <g:if test="${nominaAsimiladoInstance.cfdi && !nominaAsimiladoInstance.cfdi.uuid}">
						                    	<div class="btn-group">
						                    		<g:link class="btn btn-primary btn-outline" action="timbrar" id="${nominaAsimiladoInstance.id}" >
						                    	    	<span class="glyphicon glyphicon-screenshot"></span> Timbrar
						                    	    </g:link>
						                    	</div>
						                    </g:if>
						                    <g:if test="${nominaAsimiladoInstance.cfdi && nominaAsimiladoInstance.cfdi.uuid}">
						                    	<div class="btn-group">
					    		                    <lx:printButton label="Imprimir CFDI" action="print" id="${nominaAsimiladoInstance.id}"/>
						                    	</div>
						                    </g:if>

				</div>
			</div>
		</div> 

		<div class="row ">
		    <div class="col-md-10 col-md-offset-1 ">
		    	
				<g:form name="updateForm" class="form-horizontal"   id="${nominaAsimiladoInstance.id}">	

					<div class="panel panel-primary">
						<div class="panel-heading">Folio: ${nominaAsimiladoInstance.id} </div>
				  		<div class="panel-body">
				    		<g:hasErrors bean="${nominaAsimiladoInstance}">
				    			<div class="alert alert-danger">
				    				<ul class="errors" >
				    					<g:renderErrors bean="${nominaAsimiladoInstance}" as="list" />
				    				</ul>
				    			</div>
				    		</g:hasErrors>

							<f:with bean="${nominaAsimiladoInstance}">
								<div class="row">
									<f:display property="asimilado" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
									<f:display property="formaDePago" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
									<f:display property="fecha" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
									<f:display property="pago" widget-class="form-control chosen-select" wrapper="bootstrap3"/>
									<f:display property="concepto" widget-class="form-control" wrapper="bootstrap3"/>
									<f:display property="percepciones" widget="money" wrapper="bootstrap3"/>
									<f:display property="deducciones" widget="money" wrapper="bootstrap3"/>
								</div>
								
								
							</f:with>
				  		</div>

				  		<table id="grid" class="table table-striped table-bordered table-condensed">
				  			<thead>
				  				<tr>
				  					
				  					
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<g:each in="${nominaAsimiladoInstance.partidas}" var="row">
				  					<tr id="${row.id}">
				  						
				  						
				  					</tr>
				  				</g:each>
				  			</tbody>
				  		</table>
				  		
				  		<div class="panel-footer">
				  			<g:if test="${flash.message}">
				  				<span class="label label-warning">${flash.message}</span>
				  			</g:if> 
				  			
				  			
				  		</div>
					</div>
				</g:form>
				
			</div>
			
			

		</div><!-- end .row 2 -->
		
		<g:if test="${flash.error}">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="alert alert-danger">
						${flash.error}
					</div>
				</div>
			</div>
		</g:if>
		
	
	</div>

	
</body>
</html>