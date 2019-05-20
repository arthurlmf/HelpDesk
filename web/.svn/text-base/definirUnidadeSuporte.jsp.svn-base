<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/crudUnidades.js'></script>
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>

		
		
<p>
  <jsp:include page="head_escolher.jsp" flush="true" />  </p>
<p align="center"><strong>Defini&ccedil;&atilde;o das Unidades de Suporte</strong><br/>
  
  
<script language="javascript"> 
	 document.onload = carregarCamposUnidades();
</script>

</p>
<table border="0" align="center" cellpadding="0" cellspacing="4" bordercolor="#CCCCCC" bgcolor="#FFFFFF">
    <tr>
      <td align="center" valign="top"><span class="style3">Unidades do TRE </span> </td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="center" valign="top"><span class="style3">Unidades de Suporte do  HelpDes</span>k</td>
    </tr>   
    <tr>
      <td align="center" valign="top">
	  <select name="select" id="comboUnidadeTRE" size="18" 
      onChange="javascript: mostrarNomeGerente();">
      </select></td>
      <td  align="center" valign="middle">
      <div  align="left" style="display: none;" id="matriculaGerente">  </div> 
      <div  align="left" id="nomeGerente">      </div> 
     
        <div align="left">Login do Gerente       <br>
          <input name="loginGerenteUnidade" type="text" id="loginGerenteUnidade" size="14">
          <span class="style1">@tre.br</span> </div>
        <p align="center">
          <input name="cadastrarUma" type="button" id="cadastrarUma" value=" &gt; " 
        onclick="javascript: cadastrarUmaUnidade();"/>
      </p></td>
      <td align="center" valign="top">
      	<select name="unidadesCadastradas" id="comboUnidade" size="18">
      	</select>      </td>
    </tr>
  </table>
  
 <jsp:include page="foot.jsp" flush="true" />