<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/crudUnidades.js'></script>
<script type="text/javascript" src='js/tipoSubtipo.js'></script>
<style type="text/css">
<!--
.style2 {font-size: 12px}
.style3 {font-size: 10px}
-->
</style>


<p><jsp:include page="head_escolher.jsp" flush="true" /></p>
<p align="center"><strong>Gerenciamento da Unidade de
Suporte</strong><br /> </p>


<script language="javascript"> 

	 document.onload = getSetIdUnidadeLogado();	
	 /*carregaOpcoesUnidadeSuporte(); value="${unidade}"*/
	 	 
</script>

<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>Tipo de Gerencia da Unidade:</td>
		<td><select name="gerencia" id="comboGerencia">
		</select></td>
		<td><input name="tipoGerencia" type="button" id="tipoGerencia"
			value="Alterar" onclick="javascript: alterarTipoGerencia();"></td>
	</tr>
</table>

<input name="unidadesCadastradas" id="comboUnidade" type="hidden" >
<hr>
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="18">
	<tr>
		<td align="center" valign="top">
		<table width="100%" border="2" cellpadding="16" cellspacing="2"
			bordercolor="#FFFFFF">
<tr>
				<td>
				<div align="center"><strong>T&eacute;cnicos da
				Unidade </strong></div>
				</td>
			</tr>
			<tr>
				<td>
				<table border="0" align="center" cellpadding="0" cellspacing="4"
					bordercolor="#CCCCCC" bgcolor="#FFFFFF">
					<tr>
						<td align="center" valign="top"><span class="style3">Tecnicos
						da Unidade </span></td>
						<td align="center" valign="top">&nbsp;</td>
						<td align="center" valign="top"><span class="style3">Tecnicos
						do HelpDes</span>k</td>
						<td align="center" valign="top">&nbsp;</td>
					</tr>
				
					<tr>
						<td align="center" valign="top"><select name="select"
							id="comboTecnicoTRE" size="10">
						</select></td>
						<td align="center" valign="middle">
						<table border="0" cellspacing="0" cellpadding="0">

							<tr>
								<td>
								<div id="loginTecnico">Login</div>
								<input name="loginField" type="text" id="login" size="10"></td>
							</tr>
							<tr>
								<td><span class="style2">@tre.br</span></td>
						  </tr>
						</table>
						<p align="center"><input name="cadastrarUmTecnico"
							type="button" value=" &gt; "
							onclick="javascript: cadastrarTecnico();" /></p>
						<p align="center"><input name="retirarUmTecnico" type="button"
							value=" &lt; " onclick="javascript: retirarTecnico();" /></p>						</td>
						<td align="center" valign="top"><select
							name="tecnicosCadastrados" id="comboTecnicoHelpDesk" size="10">
						</select></td>
						<td align="center" valign="middle">
                        <div class="style2" id="valorNomeGerente">Nome do Gerente:  </div>
                        <div id="nomeGerenteUnidade">  </div>
						<p><input type="button" name="Button" value="Alterar Gerente"
							onclick="javascript: alterarGerenteUnidade();"></p>					  </td>
					</tr>
                    	<tr>
					  <td colspan="2" align="center" valign="top">Nome do Estagiario
					    <input name="nomeEstagiario" type="text" id="nomeEstagiario" size="20"></td>
					  <td align="center" valign="top"><input type="button" name="cadastrar" id="cadastrar" value="Cadastrar Estagi&aacute;rio" onclick="javascript: cadastrarEstagiario();"></td>
					  <td align="center" valign="middle">&nbsp;</td>
				  </tr>
				</table>
			  </td>
			</tr>
		</table>
	  </td>
		<td align="center" valign="top">
		<table border="2" cellpadding="16" cellspacing="2"
			bordercolor="#FFFFFF">
			<tr>
				<td>
				<div align="center"><strong>Definicao
				das Unidade Solicitantes</strong></div>
				</td>
			</tr>
			<tr>
				<td align="center" valign="top">
			      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4"
					bordercolor="#CCCCCC" bgcolor="#FFFFFF">
			  <tr>
						<td align="center" valign="top"><span class="style3">
						Unidade </span> do TRE</td>
						<td align="center" valign="top">&nbsp;</td>
						<td align="center" valign="top"><span class="style3">
						Unidades Solicitantes</span></td>
					</tr>
					<tr>
						<td align="center" valign="top"><select
							name="selectUnidadesNaoSolicitantes"
							id="comboUnidadesNaoSolicitantes" size="10">
						</select></td>
						<td align="center" valign="middle">
						<p align="center"><input name="relacionarSolicitante"
							type="button" value=" &gt; "
							onclick="javascript: cadastrarSolicitante();" /></p>
						<p align="center"><input name="relacionarTodosSolicitantes"
							type="button" value=" &gt;&gt; "
							onclick="javascript: cadastrarTodosSolicitantes();" /></p>
						<p align="center"><input name="retirarSolicitante"
							type="button" value=" &lt; "
							onclick="javascript: retirarSolicitante();" /></p>
						</td>
						<td align="center" valign="top"><select
							name="selectUnidadesSolicitantes" id="comboUnidadesSolicitantes"
							size="10">
						</select></td>
					</tr>
				</table>
			  </td>
		  </tr>
		</table>
		</td>
	</tr>
</table>
<hr>
<table border="2" align="center" cellpadding="16" cellspacing="4" bordercolor="#FFFFFF">
<tr>
		<td bgcolor="#F2F2F2">
		<div align="center"><strong>Definicao dos
		Tipos e Subtipos da</strong><strong> Unidade </strong></div>
	  </td>
  </tr>
	<tr>
		<td>

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="4">
	  <tr>
				<th valign="top" scope="row">
				  <table border="2" align="center" cellspacing="6"
					bordercolor="#DBDBDB">
					<tr>
						<td align="center" valign="top">
						<h3>Tipos da Unidade</h3>
						<table border="1" cellpadding="2" cellspacing="6"
							class="rowed grey">
							<thead>
								<tr>
									<th>Tipo</th>
									<th>A&ccedil;&otilde;es</th>
								</tr>
							</thead>
							<tbody id="tipoBody">
								<tr id="pattern" style="display: none;">
									<td><span id="tableNomeTipo">Tabela com o nome do
									tipo</span><br />
									</td>
									<td><input id="edit" type="button" value="Editar"
										onclick="editClicked(this.id)" /> <input id="delete"
										type="button" value="Deletar" onclick="deleteClicked(this.id)" />
									</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td align="center" valign="top">
						<h3>Editar/Criar Tipo</h3>
						<table class="plain">
							<tr>
								<td>Name:</td>
								<td><input id="nomeTipo" type="text" size="30" /></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="button"
									id="editarCriarTipo" value="Criar" onclick="writeTipo()" /> <input
									type="button" value="Cancelar" onclick="clearTipo()" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</th>
				<th scope="row">
				<div id="tabContents"><span style="font-weight: bold">Selecione
				o tipo: <br>
				</span> <select name="tipo" id="comboTipo"
					onchange="javascript:carregaEdicaoSubTipos(this.value);">
					<option value="" selected="selected">selecione</option>
				</select></div>
				<h3>&nbsp;</h3>
				</th>
				<th scope="row">
				<table border="2" align="center" cellspacing="6"
					bordercolor="#DBDBDB">
					<tr>
						<td align="center" valign="top">
						<h3>Subtipos de Tipo da Unidade</h3>
						<table border="1" cellpadding="2" cellspacing="6"
							class="rowed grey">
							<thead>
								<tr>
									<th>Tipo</th>
									<th>Prazo</th>
									<th>Tem patrimonio</th>
									<th>A&ccedil;&otilde;es</th>
								</tr>
							</thead>
							<tbody id="subtipoBody">
								<tr id="patternSubtipo" style="display: none;">
									<td><span id="tableSubtipo">Table com Nome do
									subtipo</span></td>
									<td><span id="colunaPrazo"></span></td>
									<td><span id="colunaPatrimonio"></span></td>
									<td><input id="editSubTipo" type="button" value="Editar"
										onclick="editSubtipoClicked(this.id)" /> <input
										id="deleteSubtipo" type="button" value="Deletar"
										onclick="deleteSubtipoClicked(this.id)" /></td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td align="center" valign="top">
						<h3>Editar/Criar Subtipo</h3>
						<table class="plain">
							<tr>
								<td>Name:</td>
								<td><input id="textNomeSubTipo" type="text" size="30" /></td>
							</tr>
							<tr>
							  <td>Prazo de Atendimento:</td>
							  <td><input name="prazo" type="text" id="prazoText" size="5"> 
							    dias </td>
						  </tr>
							<tr>
								<td>Tem patrimonio:</td>
								<td><input name="checkbox" type="checkbox"
									id="checkboxSubtipo" value=""></td>
							</tr>
							<tr>
								<td colspan="2" align="right"><input type="button"
									id="editarCriarSubTipo" value="Criar" onclick="writeSubtipo()" />
								<input type="button" value="Cancelar" onclick="clearSubtipo()" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</th>
			</tr>
		</table>
	  </td>
	</tr>
</table>


