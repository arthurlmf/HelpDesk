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
<script type="text/javascript" src='js/unidadesSuportes.js'></script>

<jsp:include page="head_escolher.jsp" flush="true" /><br />

<br />
<p align="center" style="font-weight: bold">Editar Unidade</p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<form id="form1" name="form1" method="post"
			action="/helpdesk/editarUnidade.do">
		<table border="0" align="center" cellpadding="0" cellspacing="3">
			<tr>
				<th>
				<div align="left">Unidade:</div>
				</th>
				<th>
				<div align="left"><select name="unidade" id="comboUnidade">
					<option value="">selecione</option>
					<c:forEach var="unidade" items="${unidades}" varStatus="posicao">
						<option value="${unidade.idUsuario}">${unidade.nome}</option>
					</c:forEach>
				</select></div>
				</th>
			</tr>


			<tr>
				<td>Suporte:</td>
				<td><input type="checkbox" name="suporte" value="checkbox"
					id="checkSuporte" onClick="javascript: defineSuporte()" /></td>
			</tr>

			<tr>
				<td colspan="2">
				<div id="suporteCampos" style="display: none;">
				<table width="100%" border="0">
					<tr>
						<td>Gerencia:</td>
						<td><select name="gerencia" id="combo">
							<option value="apropriacao" selected="selected">Apropriação</option>
							<option value="delegacao">Delegação</option>
							<option value="misto">Misto</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2">Criação do gerente da unidade</td>
					</tr>
					<tr>
						<th>E-mail do gerente:</th>
						<th>
						<div align="left"><input name="emailGerente" type="text"
							id="id" maxlength="80" /> ${DOMAIN}</div>
						</th>
					</tr>
					<tr>
						<th>Nome do gerente:</th>
						<th>
						<div align="left"><input name="nomeGerente" type="text"
							id="nome" maxlength="80" /></div>
						</th>
					</tr>
				</table>
				</div>
				</td>
			</tr>


			<tr>
				<th colspan="2"><label>
				<div align="center"><input name="submeter" type="submit"
					id="submeter" value="Editar" /></div>
				</label></th>
			</tr>
		</table>
		</form>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<jsp:include page="foot.jsp" flush="true" />