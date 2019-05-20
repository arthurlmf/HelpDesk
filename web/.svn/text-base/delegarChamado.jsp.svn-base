<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br/>



<p align="center" id="msg"> Delegação de Chamado </p>

<table class="form" border="0" align="center" cellspacing="2"
				cellpadding="2">
  <tr>
    <td>Solicitante:</td>
    <td>${chamadoAtendido.nomeUnidadeSolicitante}</td>
  </tr>
  <tr>
    <td>Data: </td>
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

<br/>

<form name="form" action="/helpdesk/delegarChamado.do">
<table align="center">
   <tr align="center">
	    <th >Selecione o técnico para o delegação do chamado:</th>
    	<td>
			<select name="tecnico" id="combo2">
				<option>Selecione...</option>
				<c:forEach var="usuario" items="${tecnicos}" varStatus="posicao">
					<option value="${usuario.idUsuario}">${usuario.nome}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
	<tr>
	<td colspan="2">
		<table align="right">
				<tr align="right">
					<td><input name="ok" class="input_button" type="submit" id="ok"
						value="OK"></td>
					<td><input name="cancelar" class="input_button" type="button" id="cancelar"
						value="Cancelar"
						onclick="document.location.href='index';"></td>
				</tr>
		</table>
	</td>
	</tr>
</table>
<input name="idChamado" type="hidden" value="${chamado.idChamado}">
</form>

<jsp:include page="foot.jsp" flush="true" />
