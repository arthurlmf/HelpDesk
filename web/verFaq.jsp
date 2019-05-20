<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" />



<p align="center" id="msg"> Detalhes da FAQ</p>
<form name="form" >
  <table class="form" border="0" align="center" cellspacing="2"
				cellpadding="2">
             
                   <tr>
	<td>Pergunta: </td>
    <td>${faq.pergunta}</td>
  </tr>
  <tr>
    <td>Resposta: </td>
    <td>${faq.resposta}</td>
  </tr>
  <tr>
    <td>Tipo:</td>
    <td>${faq.tipo}</td>
  </tr>
  <tr>
	<td>Subtipo: </td>
	<td>${faq.subtipo}</td>
  </tr>
  
	<tr >
    <c:if test="${podeEditar}">
	    <td colspan="2">
			<div align="center">
		    <input type="hidden" name="idFaq" value="${faq.idFaq}">
		    <input type="button" name="ok" value="Editar" onClick="this.form.action='/helpdesk/carregarEditarFaq.do';this.form.submit()">		     
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
