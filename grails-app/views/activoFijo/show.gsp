
<%@ page import="com.luxsoft.lx.af.ActivoFijo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activoFijo.label', default: 'ActivoFijo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-activoFijo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-activoFijo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list activoFijo">
			
				<g:if test="${activoFijoInstance?.factura}">
				<li class="fieldcontain">
					<span id="factura-label" class="property-label"><g:message code="activoFijo.factura.label" default="Factura" /></span>
					
						<span class="property-value" aria-labelledby="factura-label"><g:fieldValue bean="${activoFijoInstance}" field="factura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.inpc}">
				<li class="fieldcontain">
					<span id="inpc-label" class="property-label"><g:message code="activoFijo.inpc.label" default="Inpc" /></span>
					
						<span class="property-value" aria-labelledby="inpc-label"><g:fieldValue bean="${activoFijoInstance}" field="inpc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.tipoDeDepreciacion}">
				<li class="fieldcontain">
					<span id="tipoDeDepreciacion-label" class="property-label"><g:message code="activoFijo.tipoDeDepreciacion.label" default="Tipo De Depreciacion" /></span>
					
						<span class="property-value" aria-labelledby="tipoDeDepreciacion-label"><g:fieldValue bean="${activoFijoInstance}" field="tipoDeDepreciacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.depreciacionTasa}">
				<li class="fieldcontain">
					<span id="depreciacionTasa-label" class="property-label"><g:message code="activoFijo.depreciacionTasa.label" default="Depreciacion Tasa" /></span>
					
						<span class="property-value" aria-labelledby="depreciacionTasa-label"><g:fieldValue bean="${activoFijoInstance}" field="depreciacionTasa"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.gastoDet}">
				<li class="fieldcontain">
					<span id="gastoDet-label" class="property-label"><g:message code="activoFijo.gastoDet.label" default="Gasto Det" /></span>
					
						<span class="property-value" aria-labelledby="gastoDet-label"><g:link controller="gastoDet" action="show" id="${activoFijoInstance?.gastoDet?.id}">${activoFijoInstance?.gastoDet?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.modelo}">
				<li class="fieldcontain">
					<span id="modelo-label" class="property-label"><g:message code="activoFijo.modelo.label" default="Modelo" /></span>
					
						<span class="property-value" aria-labelledby="modelo-label"><g:fieldValue bean="${activoFijoInstance}" field="modelo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.serie}">
				<li class="fieldcontain">
					<span id="serie-label" class="property-label"><g:message code="activoFijo.serie.label" default="Serie" /></span>
					
						<span class="property-value" aria-labelledby="serie-label"><g:fieldValue bean="${activoFijoInstance}" field="serie"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.consignatario}">
				<li class="fieldcontain">
					<span id="consignatario-label" class="property-label"><g:message code="activoFijo.consignatario.label" default="Consignatario" /></span>
					
						<span class="property-value" aria-labelledby="consignatario-label"><g:fieldValue bean="${activoFijoInstance}" field="consignatario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.comentario}">
				<li class="fieldcontain">
					<span id="comentario-label" class="property-label"><g:message code="activoFijo.comentario.label" default="Comentario" /></span>
					
						<span class="property-value" aria-labelledby="comentario-label"><g:fieldValue bean="${activoFijoInstance}" field="comentario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.adquisicion}">
				<li class="fieldcontain">
					<span id="adquisicion-label" class="property-label"><g:message code="activoFijo.adquisicion.label" default="Adquisicion" /></span>
					
						<span class="property-value" aria-labelledby="adquisicion-label"><g:fieldValue bean="${activoFijoInstance}" field="adquisicion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.costoActualizado}">
				<li class="fieldcontain">
					<span id="costoActualizado-label" class="property-label"><g:message code="activoFijo.costoActualizado.label" default="Costo Actualizado" /></span>
					
						<span class="property-value" aria-labelledby="costoActualizado-label"><g:fieldValue bean="${activoFijoInstance}" field="costoActualizado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.cuentaContable}">
				<li class="fieldcontain">
					<span id="cuentaContable-label" class="property-label"><g:message code="activoFijo.cuentaContable.label" default="Cuenta Contable" /></span>
					
						<span class="property-value" aria-labelledby="cuentaContable-label"><g:link controller="cuentaContable" action="show" id="${activoFijoInstance?.cuentaContable?.id}">${activoFijoInstance?.cuentaContable?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="activoFijo.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${activoFijoInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.depreciacion}">
				<li class="fieldcontain">
					<span id="depreciacion-label" class="property-label"><g:message code="activoFijo.depreciacion.label" default="Depreciacion" /></span>
					
						<span class="property-value" aria-labelledby="depreciacion-label"><g:fieldValue bean="${activoFijoInstance}" field="depreciacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.depreciacionAcumulada}">
				<li class="fieldcontain">
					<span id="depreciacionAcumulada-label" class="property-label"><g:message code="activoFijo.depreciacionAcumulada.label" default="Depreciacion Acumulada" /></span>
					
						<span class="property-value" aria-labelledby="depreciacionAcumulada-label"><g:fieldValue bean="${activoFijoInstance}" field="depreciacionAcumulada"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.depreciaciones}">
				<li class="fieldcontain">
					<span id="depreciaciones-label" class="property-label"><g:message code="activoFijo.depreciaciones.label" default="Depreciaciones" /></span>
					
						<g:each in="${activoFijoInstance.depreciaciones}" var="d">
						<span class="property-value" aria-labelledby="depreciaciones-label"><g:link controller="depreciacion" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="activoFijo.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${activoFijoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.empresa}">
				<li class="fieldcontain">
					<span id="empresa-label" class="property-label"><g:message code="activoFijo.empresa.label" default="Empresa" /></span>
					
						<span class="property-value" aria-labelledby="empresa-label"><g:link controller="empresa" action="show" id="${activoFijoInstance?.empresa?.id}">${activoFijoInstance?.empresa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="activoFijo.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${activoFijoInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activoFijoInstance?.valorOriginal}">
				<li class="fieldcontain">
					<span id="valorOriginal-label" class="property-label"><g:message code="activoFijo.valorOriginal.label" default="Valor Original" /></span>
					
						<span class="property-value" aria-labelledby="valorOriginal-label"><g:fieldValue bean="${activoFijoInstance}" field="valorOriginal"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:activoFijoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${activoFijoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
