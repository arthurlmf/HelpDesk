<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${usuario.administrador}">
	<jsp:include page="head_administrador.jsp" flush="true" /><br/>
</c:if>
<c:if test="${usuario.tecnico}">
	<c:if test="${usuario.gerente}">
		<jsp:include page="head_tecnico_gerente.jsp" flush="true" /><br/>
	</c:if>
	<c:if test="${!usuario.gerente}">
		<jsp:include page="head_tecnico.jsp" flush="true" /><br/>
	</c:if>	
	
</c:if>
<c:if test="${usuario.unidade}">
	<jsp:include page="head_unidade.jsp" flush="true" /><br/>
</c:if>


<br/>




