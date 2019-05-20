<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br />


<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/tabelas.js'></script>


<link rel="stylesheet" media="screen" type="text/css" href="tabs.css" />

<span style="font-weight: bold">Selecione a unidade:</span>
<select name="unidade" id="comboUnidade" onchange="javascript:carregaEdicaoTipos();carregaUnidadesSolicitantes();">  
	<option value="" selected="selected">selecione</option>            
    <c:forEach var="unidade" items="${unidades}" varStatus="posicao">
         <option value="${unidade.idUsuario}">${unidade.nome}</option>
	</c:forEach>
</select>
<br />
<br />
<!--

</tr>
<tr>

<td>Radio buttons <span class="small">(id="radio")</span>:</td>
<td>
  <input id="radio" name="radio" value="one" type="radio" checked="checked"/>one<br/>
  <input id="radio" name="radio" value="two" type="radio"/>two
</td>

<td>Check box <span class="small">(id="checkbox")</span>:</td>
<td><input id="checkbox" value="input checkbox" type="checkbox" />input</td>

-->
<div id="tabContents">
<div id="demoDiv">

<table width="90%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<th scope="row">
		<h3>Tipos da Unidade</h3>
		<table border="1" cellpadding="2" cellspacing="6" class="rowed grey">
			<thead>
				<tr>
					<th>Tipo</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody id="tipoBody">
				<tr id="pattern" style="display: none;">
					<td><span id="tableNomeTipo">Tabela com o nome do tipo</span><br />
					</td>
					<td>
						<input id="edit" type="button" value="Editar"onclick="editClicked(this.id)" /> 
						<input id="delete"	type="button" value="Deletar" onclick="deleteClicked(this.id)" />
					</td>
				</tr>
			</tbody>
		</table>
		</th>
		<th scope="row">
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
		</th>
	</tr>
</table>

</div>


<br />
<br />

<div id="tabContents"><span style="font-weight: bold">Selecione
o tipo da unidade: </span> <select name="tipo" id="comboTipo"
	onchange="javascript:carregaEdicaoSubTipos(this.value);">
	<option value="" selected="selected">selecione</option>
</select></div>

<br />
<br />


<div id="demoDiv">
<table width="90%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<th scope="row">
		<h3>Subtipos de Tipo da Unidade</h3>
		<table border="1" cellpadding="2" cellspacing="6" class="rowed grey">
			<thead>
				<tr>
					<th>Tipo</th>
					<th>Tem patrimônio</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody id="subtipoBody">
				<tr id="patternSubtipo" style="display: none;">
					<td><span id="tableSubtipo">Table com Nome do subtipo</span>
					</td>
					<td><span id="colunaPatrimonio"></span></td>
					<td><input id="editSubTipo" type="button" value="Editar"
						onclick="editSubtipoClicked(this.id)" /> <input
						id="deleteSubtipo" type="button" value="Deletar"
						onclick="deleteSubtipoClicked(this.id)" /></td>
				</tr>
			</tbody>
		</table>

		</th>
		<th scope="row">

		<h3>Editar/Criar Subtipo</h3>
		<table class="plain">
			<tr>
				<td>Name:</td>
				<td><input id="textNomeSubTipo" type="text" size="30" /></td>
			</tr>
			<tr>
				<td>Tem patrimônio:</td>
				<td><input name="checkbox" type="checkbox" id="checkboxSubtipo"
					value=""></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button"
					id="editarCriarSubTipo" value="Criar" onclick="writeSubtipo()" />
				<input type="button" value="Cancelar" onclick="clearSubtipo()" /></td>
			</tr>
		</table>
		</th>
	</tr>
</table>

<p>&nbsp;</p>

<div id="tabContents">
  <div align="center">
    <p style="font-weight: bold">Relacionamento de Suportes entre as Unidades</p>
    <table width="90%" border="0" align="center" cellpadding="0"
	cellspacing="0">
      <tr>
        <th scope="row"> <h4> Unidade Solicitante:
          <select name="selectSolicitante" id="unidadeSolicitante" onchange="">
                  <option value="" selected="selected">selecione</option>
                  <c:forEach var="unidadeSolicitante" items="${uniddesNotMine}" varStatus="posicao">
                    <option value="${unidadeSolicitante.idUsuario}">${unidadeSolicitante.nome}</option>
                  </c:forEach>
                </select>
                <input name="button" type="button" id="buttonAdiciona" onclick="javascript:adicionaSolicitante();" value="Adicionar" />
        </h4></th>
        <th scope="row"> <table border="1" cellpadding="2" cellspacing="6" class="rowed grey">
          <thead>
            <tr>
              <th>Unidades Solicitantes </th>
              <th>A&ccedil;&otilde;es</th>
            </tr>
          </thead>
          <tbody id="solicitanteBody">
            <tr id="patternSolcitante" style="display: none;">
              <td><span id="tableSolicitante">Tabela com o nome da Unidade </span><br />
              </td>
              <td><input name="button" type="button" id="deleteSolicitante" onclick="deleteSolicitante(this.id)" value="Deletar" />
              </td>
            </tr>
          </tbody>
        </table>
  
      </tr>
    </table>
    <p>&nbsp; </p>
  </div>
</div>


</div>

</div>



<jsp:include page="foot.jsp" flush="true" />