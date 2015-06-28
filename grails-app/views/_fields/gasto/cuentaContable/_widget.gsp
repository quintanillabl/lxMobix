<%@page expressionCodec="none" %>
<asset:stylesheet src="jquery-ui.css"/>
<asset:javascript src="jquery-ui/autocomplete.js"/>
<g:hiddenField id="cuentaContableId" name="${property}.id" value="${value?.id}" />
<input 
	id="${property}" 
	type="text" 
	name="${property}Nombre"  
	class="form-control cuentaContableField" 
	value="${value}" 
	placeholder="Seleccione la cuenta contable">
</input>

<script type="text/javascript">
$(function(){
	$(".cuentaContableField").autocomplete({
			source:'<g:createLink controller="cuentaContable" action="getCuentasDeDetalleJSON"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#cuentaContableId").val(ui.item.id);
			}
	});
});
</script>



