

<div class="form-group">
	<label class="col-sm-2 control-label" >Tipo</label>
	<div class="col-sm-3">
		 <input class="form-control" value="${it.tipo}"/>
	</div>
	<label class="col-sm-2 control-label" >Sub Tipo</label>
	<div class="col-sm-3">
		 <input class="form-control" value="${it.subTipo}"/>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label" >Naturaleza</label>
	<div class="col-sm-3">
		 <input class="form-control" value="${it.naturaleza}"/>
	</div>
	<label class="col-sm-2 control-label" >Nivel</label>
	<div class="col-sm-3">
		 <input class="form-control" value="${it.nivel}"/>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label" >Detalle</label>
	<div class="col-sm-3">
		 <input type="checkbox" class="form-control" ${it.detalle?'checked':''}/>
	</div>
	<label class="col-sm-2 control-label" >Resultado</label>
	<div class="col-sm-3">
		 <input type="checkbox" class="form-control" ${it.deResultado?'checked':''}/>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label" >Suspendida</label>
	<div class="col-sm-3">
		 <input type="checkbox" class="form-control" ${it.suspendida?'checked':''}/>
	</div>
</div>

<div class="form-group">
	<label class="col-sm-2 control-label" >SAT</label>
	<div class="col-sm-10">
		 <input class="form-control" value="${it.cuentaSat}"/>
	</div>
</div>