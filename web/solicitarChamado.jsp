<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/combos.js'></script>
		
<jsp:include page="head_escolher.jsp" flush="true" />

<SCRIPT language=JavaScript>

//Funções não estao sendo usadas
function selectNext(campo) {
     //pause(5000);  
	campo.focus();	
}

function pause(numberMillis){
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true){
		now = new Date();
		if (now.getTime() > exitTime)
		return;
	}
}

</SCRIPT>	

<p id="msg" class="msg">Solicitar Chamado</p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><form id="form" name="form" action="/helpdesk/solicitarChamado.do">
      <table border="0" align="center" cellpadding="0" cellspacing="3">        
   <tbody id="tipoBody">
        <tr>
          <th><div align="left">Unidade de Suporte:</div></th>
          <th>
            <div align="left">
              <select name="idUnidade" id="comboUnidade" 
				onchange="javascript:carregaComboTipo();">
				<option value = " "> selecione </option>
                <c:forEach var="unidade" items="${unidades}" varStatus="posicao">
					<option value="${unidade.idUsuario}">${unidade.nome}</option>
				</c:forEach>
              </select>
            </div></th>
        </tr>
	
		<tr>
          <th><div align="left">Tipo:</div></th>
          <th>
            <div align="left">
				<select name="tipo" id="comboTipo" 
					onchange="javascript:carregaComboSubTipo();">
				</select>
            </div></th>
        </tr>
	
		<tr>
          <th><div align="left">Subtipo:</div></th>
          <th>
            <div align="left">
              <select name='subtipo' id="comboSubTipo"
               onchange="javascript:mostraPatrimonio();">
		  	</select>
            </div></th>
        </tr>
   		<tr id="temPatrimonio" style="display:none; ">
  			<th>
    		<div align="left">Patrim&ocirc;nio:</div>
   			</th> 
   			<th>          
		      <div name="patrimonio"  align="left">
      			<input  name="idPatrimonio" type="text" id="idPatrimonio"/>          
    		  </div>
   			</th>
		</tr>
        <tr>
          <th valign="top"><div align="left">Descric&atilde;o:</div></th>
          <th>
            <div align="left">
              <textarea name="descricao" cols="50" rows="8" id="descricao"></textarea>
            </div></th>
        </tr>
        <tr>
          <th colspan="2"><label>
            <div align="right">
              <input name="submeter" type="submit" id="submeter" value="Solicitar chamado" />
              <input type="reset" name="Reset" value="Limpar" 
              onclick="javascript:cleanAddOptions(this.form.subtipo);
              javascript:cleanAddOptions(this.form.tipo);"/>
            </div>
          </label></th>
        </tr>
		</tbody>
      </table>
      </form>

      <p>&nbsp;</p></td>
  </tr>
</table>

<jsp:include page="foot.jsp" flush="true" />