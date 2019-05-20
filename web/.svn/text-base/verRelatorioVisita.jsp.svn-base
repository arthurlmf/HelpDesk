<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br />

 <p align="center" id="msg"> Relatorio de Visita </p>
<table class="form" border="0" align="center" cellspacing="2"
	cellpadding="2">

	<tr>
		<td>ID do Chamado:</td>
		<td>${chamado.idChamado}</td>
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
		<td>Tecnico Responsavel:</td>
		<td>${usuario.nome}</td>
	</tr>
	<tr>
		<td>Solicitante:</td>
		<td>${chamadoAtendido.nomeUnidadeSolicitante}</td>
	</tr>
		
	<tr>
		<td>Intinerario:</td>
		<td>${visita.intinerario}</td>
	</tr>
	<tr>
		<td>Imprevistos:</td>
		<td>${visita.imprevistos}</td>
	</tr>
	<tr>
		<td>Servicos Realizados:</td>
		<td>${servicos}</td>
	</tr>
	<tr>
		<td>Pendencias:</td>
		<td>${visita.pendencias}</td>
	</tr>
	
</table>


<p align="center">
        <label>
        <a href="/helpdesk/indexTecnico.do">Voltar</a>
        </label>
      </p>
<br>
<br>


<jsp:include page="foot.jsp" flush="true" />
