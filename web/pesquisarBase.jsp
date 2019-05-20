<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br />
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
<c:if test="${usuario.tecnico}">
	<br>
	<table align="center" border="0" cellspacing="5" cellpadding="3">
	  <tr>
	    <td><a href="/helpdesk/carregarCriarFaq.do">Criar FAQ</a></b></td>
	    <td><a href="/helpdesk/carregarCriarArtigo.do">Criar Artigo</a></td>
	  </tr>
	</table>
</c:if>	

<br><br>
<jsp:include page="foot.jsp" flush="true" />