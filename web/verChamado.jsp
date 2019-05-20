<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br/>
         <table class="form" border="0" align="center" cellspacing="2"
				cellpadding="2">
             
                   <tr>
	<td>ID do Chamado: </td>
    <td>${chamado.idChamado}</td>
  </tr>
  <tr>
    <td>Data: </td>
    <td>${chamado.data}</td>
  </tr>
  <tr>
    <td>Unidade Solicitante:</td>
    <td>${chamadoAtendido.nomeUnidadeSolicitante}</td>
  </tr>
  <tr>
	<td>Unidade Responsável: </td>
	<td>${chamadoAtendido.nomeUnidadeAtendente}</td>
  </tr>
  <tr>
    <td>Técnico Responsável:</td>
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

    
         <c:forEach var="transacao" items="${transacoes}" varStatus="posicao">
  
    <c:if test="${transacao.tipo == 'apropriacao'}">
		<table border="0" align="center" width="500" cellspacing="2"
		cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.tecnicoResponsavel}</td>
			</tr>
		</table>
		<br/>
		</c:if>
    
    <c:if test="${transacao.tipo == 'encaminhamento para unidade'}">
		<table border="0" align="center" width="500" cellspacing="2"
		cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
				
				<td align="center" width="10">Novo Responsável</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.unidadeOrigem}</td>
				<td align="center" width="10">${transacao.unidadeDestino}</td>
			</tr>
		</table>
		<br/>
		</c:if>
    
    <c:if test="${transacao.tipo == 'encaminhamento'}">
		<table border="0" align="center" width="500" cellspacing="2"
		cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
				
				<td align="center" width="10">Novo Responsável</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.tecnicoOrigem}</td>
				<td align="center" width="10">${transacao.tecnicoDestino}</td>
			</tr>
		</table>
		<br/>
		</c:if>
		
		<c:if test="${transacao.tipo == 'intervencao'}">
		<table border="0" align="center" width="500" cellspacing="2"
		cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
				<td align="center" width="10">Descricao</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.tecnicoResponsavel}</td>
				<td align="center" width="10">${transacao.descricao}</td>
			</tr>
			
		</table>
		<br/>
		</c:if>		
		
		<c:if test="${transacao.tipo == 'visita'}">
		<table border="0" align="center" width="500" cellspacing="2"
			cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
				<td align="center" width="10">Detalhes</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.idTecnico}</td>
				<td align="center" width="10"><a href="/helpdesk/verRelatorioVisita.do?idVisita=${transacao.idTransacao}&idChamado=${chamado.idChamado}">detalhar</a></td>
		  </tr>

		</table>
		<br />
	</c:if>
	
	<c:if test="${transacao.tipo == 'fechado'}">
		<table border="0" align="center" width="500" cellspacing="2"
			cellpadding="2">
			<tr class="listagem_cabecalho">
				<td align="center" width="10">Tipo</td>
				<td align="center" width="80">Data</td>
				<td align="center" width="40">Responsável</td>
			</tr>
			<tr class="listagem_linha">
				<td align="center" width="10">${transacao.tipo}</td>
				<td align="center" width="80">${transacao.data}</td>
				<td align="center" width="40">${transacao.tecnicoResponsavel}</td>
			</tr>
		</table>
		<br />
	</c:if>
		
  </c:forEach>
<br>  
<c:if test="${usuario.unidade}">
	<a href="/helpdesk/indexUnidade.do">Voltar</a>	
</c:if>
<c:if test="${usuario.tecnico}">
	<a href="/helpdesk/indexTecnico.do">Voltar</a>	
</c:if>

<br><br>


<jsp:include page="foot.jsp" flush="true" />
    