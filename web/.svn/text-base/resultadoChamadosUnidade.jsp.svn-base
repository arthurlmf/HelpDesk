<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br/>

<p align="center" id="msg"> Resultado da Pesquisa </p>
<table border="0" align="center" width="500" cellspacing="2"
		cellpadding="2">
  <tr class="listagem_cabecalho">
  	<td align="center" width="10">ID</td>
    <td align="center" width="10">Data</td>
    <td align="center" width="50">Tipo</td>
    <td align="center" width="50">Subtipo</td>	
    <td align="center" width="50">Estado</td>    
    <td align="center" width="40">Responsavel</td>
    <td align="center" width="10">Detalhes</td>
  </tr>
  
  <c:forEach var="chamado" items="${chamados}" varStatus="posicao">
     <tr>
       <td width="10" align="center">${chamado.idChamado}</td>
       <td width="10" align="center">${chamado.data}</td>
       <td width="50" align="center">${chamado.tipo}</td>
       <td width="50" align="center">${chamado.subtipo}</td>
       <td width="50" align="center">${chamado.estado}</td>
       <td width="40" align="center">${chamado.nomeResponsavel}</td>       
       <td width="10" align="center">
       	<a href="verChamadoUnidade.do?idChamado=${chamado.idChamado}">Detalhar</a>
       </td>
     </tr>
  </c:forEach>
</table>
<br>
<div align="right" id="result_pesquisa">  ${quantChamadosResult} Chamado(s) encontrado(s). </div>
<br>
<p><a href="javascript:history.back()">voltar</a></p>
<br>



<jsp:include page="foot.jsp" flush="true" />