<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br/>

<style type="text/css">
<!--
.style1 {
	font-family:Arial, Helvetica, sans-serif;
	text-align:left;
	font-size: 14px;
	font-weight:bold;
}
.style2 {
	font-family:Arial, Helvetica, sans-serif;
	text-align:center;
	font-size: 14px;
	font-weight:bold;
}
-->
</style>


<p align="center" id="msg"> Pesquisa HelpDesk </p>
<br>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<form id="form1" name="form1" method="post"
			action="/helpdesk/pesquisarBaseCompleta.do">
		<table border="0" align="center" cellpadding="0" cellspacing="3">
			<tr>				
			  <td><input name="key_word" type="text" size="80" /></td>
			</tr>
			<tr>
			<tr>
				<th height="66" colspan="2"><label>
				  <input type="checkbox" name="artigo" value="pesquisar" checked>
				  Artigo</label>
				  <label>

				  <label>
				  <input type="checkbox" name="faq" value="pesquisar" checked>
				  FAQ</label>
				  <label>
				  <input type="checkbox" name="solucao" value="pesquisar" checked>
				  Solução</label>
				  <div align="center"><input name="submeter" type="submit"
					id="submeter" value="Pesquisar" /></div>
			  </label></th>
			</tr>
		</table>
		</form>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>


<hr>
<p align="left" class="style1">Resultado da Pesquisa: ${total} resultado(s) encontrado(s). </p>

<br>
	

<c:if test="${!(empty artigosResult)}">
	<p id="subtitulo_pesquisa" class="style2">Artigos Encontrados</p>	<br>
	<br>

		
	<table border="0" align="center" width="85%" cellspacing="2"
		cellpadding="2">
	  <tr class="listagem_cabecalho">
    	<td width="16%" align="center" >Tipo</td>
	    <td width="68%" align="center" >Título</td>
	    <td width="16%" align="center" >Ver</td>
	  </tr>
	<c:forEach var="artigo" items="${artigosResult}" varStatus="posicao">
	<form name=${posicao.count} method="post" action="/helpdesk/verArtigo.do">
	  <tr>
        <td align="center">${artigo.tipo}</td>
        <td align="center">${artigo.titulo}</td>
        <td align="center">
	      <input name="idArtigo" type="hidden" id="id" value="${artigo.idArtigo}">			
          <input name="ver" type="submit" value="Detalhes"/>
        </td>
      </tr>
    </form>
  </c:forEach>
    
</table>
    </br>
    <div align="right" id="result_pesquisa"> ${quantArtigosResult} artigo(s) encontrado(s).       
  </div>
  <br><br>
</c:if>




<c:if test="${!(empty faqsResult)}">
	<p id="subtitulo_pesquisa" class="style2">Faq's Encontradas</p>	<br>
	
	<table border="0" align="center" width="85%" cellspacing="2"
		cellpadding="2">
	  <tr class="listagem_cabecalho">
    	<td width="16%" align="center" >Tipo</td>
	    <td width="68%" align="center" >Pergunta</td>
	    <td width="16%" align="center" >Ver</td>
	  </tr>

	  <c:forEach var="faq" items="${faqsResult}" varStatus="posicao">
    	<form name=${posicao.count} method="post" action="/helpdesk/verFaq.do">
	  <tr>
        <td align="center">${faq.tipo}</td>
        <td align="center">${faq.pergunta}</td>
       
        <td align="center">
	      <input name="idFaq" type="hidden" id="id" value="${faq.idFaq}">			
          <input name="ver" type="submit" value="Detalhes"/>        </td>
      </tr>
	 </form>
	  </c:forEach>
  </table>
  </br>
  <div align="right" id="result_pesquisa">  ${quantFAQResult} FAQ(s) encontrada(s). </div><br><br>
</c:if>



	

<c:if test="${!(empty solucoesResult)}">
	<p id="subtitulo_pesquisa" class="style2">Solu&ccedil;&otilde;es de Problemas  Encontrados</p>	<br>
	<table border="0" align="center" width="85%" cellspacing="2"
		cellpadding="2">
	  <tr class="listagem_cabecalho">
	    <td width="16%" align="center" >Tipo</td>
	    <td width="16%" align="center" >Subtipo</td>
	    <td width="52%" align="center" >Descricao</td>
	    <td width="18%" align="center" >Ver</td>
	  </tr>

	  <c:forEach var="solucao" items="${solucoesResult}" varStatus="posicao">
    	<form name=${posicao.count} method="post" action="/helpdesk/verSolucao.do">
     <tr>
        <td align="center">${solucao.chamado.tipo}</td>
        <td align="center">${solucao.chamado.subtipo}</td>
        <td align="center">${solucao.chamado.descricao}</td>
       
        <td align="center">
	      <input name="idSolucao" type="hidden" id="id" value="${solucao.idSolucao}">			
          <input name="ver" type="submit" value="Detalhes"/>        </td>
      </tr>
	    </form>
	  </c:forEach>
  </table>
  </br>
	<div align="right" id="result_pesquisa">  ${quantSolucaoResult} Solução(ões) encontrado(s). </div><br><br>
</c:if>

</br></br>

	

<jsp:include page="foot.jsp" flush="true" />
