<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br />

<script type="text/javascript">
function confirmSubmit(msgPergunta)
{
var agree=confirm(msgPergunta);
if (agree)
	return true ;
else
	return false ;
}

function fecharChamado(formulario){
  if(confirmSubmit("Tem certeza que deseja fechar o chamado?")){
	 formulario.action='/helpdesk/fecharChamado.do';
     formulario.submit();  
  }
  


}


</script>


<p align="center" id="msg">${msg}</p>

<table class="form" border="0" align="center" cellspacing="2"
	cellpadding="2">
	<tr>
		<td>ID do Chamado:</td>
		<td>${chamado.idChamado}</td>
	</tr>
	<tr>
		<td>Data:</td>
		<td>${chamado.data}</td>
	</tr>
	<tr>
		<td>Unidade Solicitante:</td>
		<td>${chamadoAtendido.nomeUnidadeSolicitante}</td>
	</tr>
	<tr>
		<td>Unidade Responsável:</td>
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

<br />

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
		<br />
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
		<br />
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
		<br />
	</c:if>

	<c:if test="${transacao.tipo == 'delegacao'}">
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
		<br />
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
		<br />
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

<c:if test="${ehDahUnidade}">
	<p align="center">
	<form name="formPegar">
	<div align="center"><input name="idChamado" type="hidden"value="${chamado.idChamado}">
		<c:if test="${podeApropriar}">
			<input type="submit" name="Apropriar-se" value="Apropriar-se"
				onClick="this.form.action='/helpdesk/apropriarChamado.do';this.form.submit()">
		</c:if> 
		<c:if test="${podeDelegar}">
			<input type="submit" name="Delegar" value="Delegar"
				onClick="this.form.action='/helpdesk/carregarDelegarChamado.do';this.form.submit()">
		</c:if></div>
	</form>


	<c:if test="${isDono}">
		<c:if test="${chamado.estado == 'em autorizacao'}">
			<form name="form">
			<div align="center"><input name="idChamado" type="hidden"
				value="${chamado.idChamado}"> <input type="button"
				name="Autorizar" value="Autorizar"
				onClick="this.form.action='/helpdesk/autorizarChamado.do';this.form.submit();">
			<input type="button" name="Negar" value="Negar"
				onClick="this.form.action='/helpdesk/negarChamado.do';this.form.submit()">

			</div>
			</form>
		</c:if>

		<c:if test="${chamado.estado == 'encaminhado' || chamado.estado == 'delegado'}">
			<form name="form">
			<div align="center"><input name="idChamado" type="hidden"
				value="${chamado.idChamado}"> <input type="button"
				name="Aceitar Chamado" value="Aceitar Chamado"
				onClick="this.form.action='/helpdesk/aceitarChamado.do';this.form.submit()">
			</form>
		</c:if>
		
		<c:if test="${chamado.estado == 'aberto'}">
			<form name="form">
			<table align="center">
				<tr>
					<td>

					<div align="center"><input type="button"
						name="Encaminhar Chamado" value="Encaminhar"
						onClick="this.form.action='/helpdesk/selecionarTecnicoEncaminhar.do';this.form.submit()">
					<input type="button" name="Movimentar Chamado" value="Intervir"
						onClick="this.form.action='/helpdesk/carregarIntervir.do';this.form.submit()">
					<input type="button" name="Repassar Chamado"
						value="Repassar"
						onClick="this.form.action='/helpdesk/carregarRepassamento.do';this.form.submit()">

					<div align="center">
					<input type="button" name="Relatar Visita" value="RelatarVisita"
						onClick="this.form.action='/helpdesk/carregarRelatorioVisita.do';this.form.submit()">
					<input type="button" name="Fechar Chamado" value="Fechar"
						onClick="javascript: fecharChamado(this.form)">
					<input name="idChamado" type="hidden" value="${chamado.idChamado}">
					</div>
					</td>

				</tr>
				<tr>
					<td>
					<div align="right"><input name="baseDados" type="checkbox"
						value="incluir"> <span class="style3">Ao fechar
					chamado incluir na base de conhecimento </span></div>
					</td>
				</tr>
			</table>
			</form>
		</c:if>
	</c:if>
</c:if>

<c:if test="${chamado.fechado}">
	<br>
	<a href="/helpdesk/indexTecnico.do">Voltar</a>
</c:if>

<br>
<br>

<jsp:include page="foot.jsp" flush="true" />
