<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" /><br />

 <p align="center" id="msg"> Relatorio de Visita </p>
 <form name="form1" method="post" action="">
   <table class="form" border="0" align="center" cellspacing="2"
	cellpadding="2">
     <tr>
       <td>ID do Chamado:</td>
       <td>${chamado.idChamado}</td>
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
       <td>Rotina de Inspecao:</td>
       <td>${rotina}</td>
     </tr>
     <tr>
       <td>Servicos Realizados:</td>
       <td>${visita.servicosRealizados}</td>
     </tr>
     <tr>
       <td>Pendencias:</td>
       <td>${visita.pendencias}</td>
     </tr>
     <!-- <tr>
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
	</tr> -->
   </table>
   <p align="center">
     <label> ${chamadoAtendido.nomeUnidadeSolicitante} , deseja confirmar a visita?<br>
     <br>
       senha:
  <input type="text" name="senha" id="senha">
  <a href="/helpdesk/indexTecnico.do"><br>
  </a></label>
     <input name="idChamado" type="hidden" value="${chamado.idChamado}">
     <input name="idTecnico" id="idTecnico" type="hidden" value="${visita.idTecnico}">
     <input name="intinerario" type="hidden" id="intinerario" value="${visita.intinerario}">
     <input name="imprevistos" type="hidden" id="imprevistos" value="${visita.imprevistos}">
     <input name="cabeamentoEstruturado" type="hidden" id="cabeamentoEstruturado" value="${visita.cabeamentoEstruturado}">
     <input name="layoutDoAmbiente" type="hidden" id="layoutDoAmbiente" value="${visita.layoutDoAmbiente}">
     <input name="tensaoDosEstabilizadores" type="hidden" id="tensaoDosEstabilizadores" value="${visita.tensaoDosEstabilizadores}">
     <input name="instalacoesEletricas" type="hidden" id="instalacoesEletricas" value="${visita.instalacoesEletricas}">
     <input name="limpezaDosMicros" type="hidden" id="limpezaDosMicros" value="${visita.limpezaDosMicros}">
     <input name="tensaoDosNoBreaks" type="hidden" id="tensaoDosNoBreaks" value="${visita.tensaoDosNoBreaks}">
     <input name="instalacoesFisicas" type="hidden" id="instalacoesFisicas" value="${visita.instalacoesFisicas}">
     <input name="limpezaDasImpressoras" type="hidden" id="limpezaDasImpressoras" value="${visita.limpezaDasImpressoras}">
     <input name="organizacaoDosCabos" type="hidden" id="organizacaoDosCabos" value="${visita.organizacaoDosCabos}">
     <input name="imagensDosMicros" type="hidden" id="imagensDosMicros" value="${visita.imagensDosMicros}">
     <input name="outrosString" type="hidden" id="outrosString" value="${visita.outrosString}">
     <input name="outros" type="hidden" id="outros" value="${visita.outros}">
     <input name="servicosRealizados" type="hidden" id="servicosRealizados" value="${visita.servicosRealizados}">
     <input name="pendencias" type="hidden" id="pendencias" value="${visita.pendencias}">
   </p>
   <p align="center">&nbsp;</p>
   <p align="center">
     <label>
     <input type="submit" name="relatar" id="relatar" value="Relatar"
        onClick="this.form.action='/helpdesk/relatarVisita.do';this.form.submit()">
     <input type="button" name="ok" value="Cancelar" onClick="javascript:history.back()">
     </label>
     <label><a href="/helpdesk/indexTecnico.do"><br>
     <br>
     </a> </label>
   </p>
</form>
 <br>
<br>


<jsp:include page="foot.jsp" flush="true" />
