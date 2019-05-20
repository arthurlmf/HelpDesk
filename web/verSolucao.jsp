<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br/>

<p align="center" id="msg">   Detalhes da Solução </p>
<p align="center">&nbsp;</p>
<p align="center" id="msg"></p>

<table width="85%" border="0" align="center"
				cellpadding="2" cellspacing="2" class="form">
  <tr>
    <th width="130"><div align="left">Tipo do Chamado:</div></th>
    <td width="690" align="left">${solucao.chamado.tipo}</td>
  </tr>
  <tr>
    <th><div align="left">Subtipo:</div></th>
    <td><div align="left">${solucao.chamado.subtipo} </div></td>
  </tr>
  <tr>
    <th><div align="left">Descricao: </div></th>
    <td><div align="left"> ${solucao.chamado.descricao} </div></td>
  </tr>
   <tr>
    <th ><div align="left">Intervencões:</div></th>
    <td><div align="left"> 
	   
		 <c:forEach var="descricaoSolucao" items="${solucao.listaDescricao}" varStatus="posicao">
	   	 	<span style="font-weight: bold">${posicao.count} - </span>
			${descricaoSolucao}
			<br/>
	     </c:forEach>
	     </div></td>
  </tr>
</table>
<br>
<br>
<a href="javascript:history.back()">voltar</a></p>
<br><br>


<jsp:include page="foot.jsp" flush="true" />
