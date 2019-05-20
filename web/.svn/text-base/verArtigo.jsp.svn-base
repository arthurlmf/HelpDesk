<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" />



<p align="center" id="msg"> Detalhes do Artigo</p>
<form name="form" >
  <table class="form" border="0" align="center" cellspacing="2"
				cellpadding="2">
             
                   <tr>
	<td>Título: </td>
    <td>${artigo.titulo}</td>
  </tr>
  <tr>
    <td>Texto: </td>
    <td>${artigo.texto}</td>
  </tr>
  <tr>
    <td>Tipo:</td>
    <td>${artigo.tipo}</td>
  </tr>
  <tr>
	<td>Subtipo: </td>
	<td>${artigo.subtipo}</td>
  </tr>
  
	<tr >
    <c:if test="${podeEditar}">
	    <td colspan="2">
			<div align="center">
		    <input type="hidden" name="idArtigo" value="${artigo.idArtigo}">
		    <input type="button" name="ok" value="Editar" onClick="this.form.action='/helpdesk/carregarEditarArtigo.do';this.form.submit()">		     
			<input type="button" name="ok" value="Cancelar" onClick="javascript:history.back()">			     
					  
			 </div>
		</td>
  </c:if>  
  </tr>  
</table>
</form>
<c:if test="${!podeEditar}">
	<p><a href="javascript:history.back()">voltar</a></p>
</c:if>  
<jsp:include page="foot.jsp" flush="true" />
