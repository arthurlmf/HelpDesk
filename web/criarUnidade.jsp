<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/unidadesSuportes.js'></script>
		
		
<jsp:include page="head_escolher.jsp" flush="true" /><br/>

<form id="form" name="form" action="/helpdesk/criarUnidade.do">
<table class="form" border="0" align="center" cellspacing="2"
				cellpadding="2">
  <tr>
    <td>Nome:</td>
    <td><input type="text" name="nome" /></td>
  </tr>
  <tr>
    <td>E-mail:</td>
    <td><input type="text" name="email" /> ${DOMAIN}</td>
  </tr>
  <tr>
    <td>Suporte:</td>
    <td>
    	<input type="checkbox" name="suporte" value="checkbox" id="checkSuporte"
    	onClick="javascript: defineSuporte()"/>    </td>
  </tr>
  
  <tr>
    <td colspan="2">
	<div id="suporteCampos" style="display: none;">
	   <table width="100%" border="0" >
          <tr>
            <td>Gerencia:</td>
            <td>
            	<select name="gerencia" id="combo">
					<option value="apropriacao">Apropriação</option>
					<option value="delegacao">Delegação</option>
					<option value="misto">Misto</option>
				</select>	</td>
		  </tr>
			<tr>
				<td colspan="2">Criação do gerente da unidade</td>
			</tr>
			<tr>
				<th>E-mail do gerente:</th>
				<th>
				<div align="left"><input name="emailGerente" type="text"
					id="id" maxlength="80" /> ${DOMAIN}</div>
				</th>
			</tr>
			<tr>
				<th>Nome do gerente:</th>
				<th>
				<div align="left"><input name="nomeGerente" type="text"
					id="nome" maxlength="80" /></div>
				</th>
			</tr>
		</table>
	</div> 
	</td>
    </tr>
 
	<tr>
	  <th colspan="2"><label>
    	  <div align="right">
              <input name="submeter" type="submit" id="submeter" value="Criar Unidade"/>
              <input type="reset" name="Reset" value="Limpar"/>
          </div>
          </label></th>
      </tr>
</table>
</form>

<jsp:include page="foot.jsp" flush="true" />