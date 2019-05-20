<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br />

<p align="center" id="msg">Interven&ccedil;&atilde;o de Chamado</p>

<table class="form" border="0" align="center" cellspacing="2"
	cellpadding="2">
	<!-- <tr>
    <td>Solicitante:</td>
    <td>${chamadoAtendido.nomeUnidadeSolicitante}</td>
  </tr> -->
	<tr>
		<td>ID do Chamado:</td>
		<td>${chamado.idChamado}</td>
	</tr>
	<tr>
		<td>Data:</td>
		<td>${chamado.data}</td>
	</tr>
	<tr>
		<td>Responsável:</td>
		<td>${chamado.nomeResponsavel}</td>
	</tr>
	<tr>
		<td>Tipo do Chamado:</td>
		<td>${chamado.tipo}</td>
	</tr>
	<tr>
		<td>Subtipo do Chamado:</td>
		<td>${chamado.subtipo}</td>
	</tr>
	<tr>
		<td>Estado:</td>
		<td>${chamado.estado}</td>
	</tr>
	<tr>
		<td>Descricao:</td>
		<td>${chamado.descricao}</td>
	</tr>
</table>
<p></p>
<form name="form" action="/helpdesk/intervirChamado.do">
<table align="center">
	<tr align="center">
		<th valign="top">Descri&ccedil;&atilde;o da
		interven&ccedil;&atilde;o :</th>
		<td><textarea name="descricao" cols="50" rows="6" id="descricao"></textarea>
		</td>
	</tr>
	<tr>


		<td colspan="2">
		<table align="right">
			<tr align="right">

				<td><input name="visivel" type="checkbox" value="incluir"
					checked> <span class="style3">Intervenção visível ao
				solicitante</span></td>
			</tr>
			<tr align="center">
				<td><input name="ok" class="input_button" type="submit" id="ok"
					value="OK"></td>
				<td><input type="button" class="input_button" name="cancelar"
					value="Cancelar" onClick="javascript:history.back()"></td>
			</tr>


			</tr>
		</table>
		</td>


	</tr>
</table>
<input name="idChamado" type="hidden" value="${chamado.idChamado}">
</form>
<br />
<br />

<jsp:include page="foot.jsp" flush="true" />
