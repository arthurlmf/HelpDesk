<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="head_escolher.jsp" flush="true" />


<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript"
	src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/base_conhecimento.js'></script>


<p align="center" id="msg"> Detalhes do Artigo</p>

<form name="form" >
	<table class="form" border="0" align="center" cellspacing="2"
					cellpadding="2">
	  <tr>
	    <th width="40">Título:</th>
	    <th width="147"><input name="titulo" type="text" size="80" maxlength="200"  value="${artigo.titulo}"></th>
	  </tr>
	  <tr>
	    <th height="132">Texto: </th>
	    <th><div align="left">
	      <textarea name="texto" cols="57" rows="10">${artigo.texto}</textarea>
	    </div></th>
	  </tr>
	   <tr>
	    <th>Tipo:</th>
	    <th>
	      <div align="left">
	         <select name="tipo" id="comboTipo" onchange="javascript:carregaComboSubTipo();">              
		         <option value="">Selecione</option>
		         <c:forEach var="tipo" items="${tipos}" varStatus="posicao">
		            <option value="${tipo.nomeTipo}">${tipo.nomeTipo}</option>
		         </c:forEach>
	         </select>
	          </div>
	   </th>
	   </tr>	   
	   <tr>
	   <th>Subtipo:</th>
	    <th>
	      <div align="left">
	         <select name="subtipo" id="comboSubTipo">              
		     </select>
	       </div>
	   </th>
		</tr>
	   <tr >
	    <td colspan="2">
			   <div align="center">
		         <input type="button" name="ok" value="OK" 
			  onClick="this.form.action='/helpdesk/editarArtigo.do';this.form.submit()">
			     <input name="restaurar" type="reset" id="restaurar" value="Restaurar" >
			  <input type="button" name="cancelar" value="Cancelar" onClick="this.form.action='/helpdesk/indexPesquisarBase.do';this.form.submit()"> 
			  
			  <input type="hidden" name="idArtigo" value="${artigo.idArtigo}">
			  
		</div></td>
	  </tr>
	
	</table>
</form>
<jsp:include page="foot.jsp" flush="true" />
