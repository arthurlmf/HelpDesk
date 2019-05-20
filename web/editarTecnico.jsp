<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/combos.js'></script>

<jsp:include page="head_escolher.jsp" flush="true" /><br/>

<br/>
<p align="center" style="font-weight: bold">Editar Tecnico </p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><form id="form1" name="form1" method="post" action="/helpdesk/editarTecnico.do">
      <table border="0" align="center" cellpadding="0" cellspacing="3">
        <tr>
          <th><div align="left">Unidade: </div></th>
          <th> <div align="left">
              <select name="unidade" id="comboUnidade" onchange="javascript:carregaComboTecnicoAlterar();">                      	<option value="">selecione</option>
                <c:forEach var="unidade" items="${unidades}" varStatus="posicao">
                  <option value="${unidade.idUsuario}">${unidade.nome}</option>
                </c:forEach>
              </select>
          </div></th>
        </tr>
        <th><div align="left">Tecnico: </div></th>
          <th> <div align="left">
              <select name="tecnico" id="comboTecnico" 
	           onchange="javascript:listaUnidadesMeFirst();">                
              </select>
          </div></th>        
        <tr>
          <th>Novo nome:</th>
          <th><div align="left">
            <input name="novoNome" type="text"  id="nomeNovo" size="50" maxlength="45"/>
          </div></th>
        </tr>
        <th><div align="left">Nova Unidade: </div></th>
          <th> <div align="left">
              <select name="novaUnidade" id="comboUnidadeNova">                
              </select>
          </div></th>
        <tr>
          <th colspan="2"><label>
                <div align="center">
                  <input name="submeter" type="submit" id="submeter" value="Editar" />
                </div>
            </label></th>
        </tr>
      </table>
    </form>
        <p>&nbsp;</p></td>
  </tr>
</table>
<jsp:include page="foot.jsp" flush="true" />