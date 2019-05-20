<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<c:if test="${usuario.unidade}">
	<table align="center" border="0" cellspacing="5" cellpadding="3">
  		<tr>
	    <td><a href="/helpdesk/indexUnidadeSuporte.do">Chamados da ${unidade}</a></td>
	    <td><a href="/helpdesk/listarChamadosSolicitadosUnidade.do">Chamados Solicitados</a></td>
	    <td><a href="/helpdesk/listarChamadosRepassadosUnidade.do">Chamados Repassados</a></td>
  		</tr>
	</table>
</c:if>

<c:if test="${usuario.tecnico}">
	<table align="center" border="0" cellspacing="5" cellpadding="3">
  		<tr>
    	<td><a href="/helpdesk/indexTecnico.do">Meus Chamados</a></td>
    	<td><a href="/helpdesk/listarChamadosUnidade.do">Chamados da ${unidade}</a></td>
    	<c:if test="${podeVerChamadosRepassados}">
			<td><a href="/helpdesk/listarChamadosRepassadosUnidade.do">Chamados Repassados</a></td>
		</c:if>
  		</tr>
	</table>
</c:if>