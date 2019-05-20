<html>
	<head>
		<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
		<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
		<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
		<script type="text/javascript" src='js/combos.js'></script>
		<title>DWR: Ajax for Java - Andre</title>
	</head>

	<body>
		Página com exemplos do addOptions();<br><br>
		
		<form name="form" >
		<strong>1,2 - DWRUtil.addOptions("comboTipo", listBeans, "nomeTipo");</strong><br>
		Exemplo combo de Pessoa:
		<a href="javascript:carregaComboTipo('informatica@tre.br')" >Carregar</a> / <a href="javascript:cleanAddOptions('comboTipo')" >Limpar</a>
		<select id="comboTipo" onchange="this.form.nomeTipo.value=this.value"></select>
	
		<br><br>
	    <input name="nomeTipo" type="hidden" value="">
		<strong>1,2 - DWRUtil.addOptions("comboSubTipo", listBeans, "nomeSubtipo");</strong><br>
		Exemplo combo de Pessoa:
		<a href="javascript:carregaComboSubTipo('informatica@tre.br','Software')" >Carregar</a> / <a href="javascript:cleanAddOptions('comboSubTipo')" >Limpar</a>
		<select id="comboSubTipo"></select>
		</form>
		
	</body>
</html>