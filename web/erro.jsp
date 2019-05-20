<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="helpDesk_head.jsp" flush="true" />

<form name="form" method="post">
<table align="center">

	<tr>
		<td>
		<p>${mensagem}</p>
		<BR />
		<p><a href="javascript:history.back()">voltar</a></p>
		</td>
	</tr>
</table>
</form>




<jsp:include page="foot.jsp" flush="true" />
