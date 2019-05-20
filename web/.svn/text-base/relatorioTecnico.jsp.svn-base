<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/FacadeAjax.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='js/pesquisa_relatorio.js'></script>
		
<jsp:include page="head_escolher.jsp" flush="true" />

<p id="msg" class="msg">Gerar Relat&oacute;rio </p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><form id="form" name="form" action="/helpdesk/gerarRelatorioTecnico.do">
      <table border="0" align="center" cellpadding="0" cellspacing="3">        
  
        <tr>
          <th><div align="left">Unidade Cliente:</div></th>
          <th>
            <div align="left">
             <select name="idUnidade" id="comboUnidade">
					<option value = " "> selecione </option>
                <c:forEach var="unidade" items="${unidades}" varStatus="posicao">
					<option value="${unidade.idUsuario}">${unidade.nome}</option>
				</c:forEach>
			</select>
            </div></th>
        </tr>
		<tr>
		  <th><div align="left">T&eacute;cnico:</div></th>
		  <th><div align="left">
		    <select name="idTecnico" id="comboTecnico" >
		      <option value = " "> selecione </option>
                <c:forEach var="tecnico" items="${tecnicos}" varStatus="posicao">
					<option value="${tecnico.idUsuario}">${tecnico.nome}</option>
				</c:forEach>
		      </select>
		  </div></th>
		  </tr>
		<tr>
		  <th>Tempo de atendimento: </th>
		  <th><div align="left">
		    <input  name="tempoIda" type="text" id="tempoIda" size="5"/>
		     a 
		     <input  name="tempoVolta" type="text" id="tempoVolta" size="5"/> 
		    dias </div></th>
		  </tr>
		<tr>
          <th><div align="left">Tipo:</div></th>
          <th>
            <div align="left">
            <input  name="minhaUnidade" type="hidden" id="minhaUnidade" value="${unidade.idUsuario}"/>  
				<select name="tipo" id="comboTipo" 
					onchange="javascript:carregaComboMeusSubTipo();">
					<option value = " "> selecione </option>
                <c:forEach var="tipo" items="${tipos}" varStatus="posicao">
					<option value="${tipo.nomeTipo}">${tipo.nomeTipo}</option>
				</c:forEach>
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
   		<tr id="temPatrimonio" style="display: none;">
  			<th>
    		<div align="left">Patrim&ocirc;nio:</div>   			</th> 
   			<th>          
		      <div name="patrimonio"  align="left">
      			<input  name="idPatrimonio" type="text" id="idPatrimonio"/>          
    		  </div>   			</th>
		</tr>
		<tr>
          <th><div align="left">Estado:</div></th>
          <th>
            <div align="left">
				<select name="estado" id="combo">
					<option value="">selecione</option>
					<option value="em analise">Em Análise</option>
					<option value="em autorizacao">Em Autorização</option>
					<option value="aberto">Aberto</option>
					<option value="encaminhado">Encaminhado</option>
					<option value="interencaminhamento">Repassado</option>
					<option value="fechado">Fechado</option>
				</select>
            </div></th>
        </tr>
   		<tr>
          <th colspan="2"><label>
            <div align="right">

              <input name="submeter" type="submit" id="submeter" value="Gerar Relat&oacute;rio" />
            </div>
          </label></th>
        </tr>
      </table>
      <input name="unidadeHidden" type="hidden" value="${unidade.idUsuario}">
      </form>

      <p>&nbsp;</p></td>
  </tr>
</table>

<jsp:include page="foot.jsp" flush="true" />