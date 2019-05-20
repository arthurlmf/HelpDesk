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


<div align="center">
  <p align="center" id="msg"> Relatorio de Visita </p>
  <form name="form1" method="post" action="/helpdesk/confirmarVisita.do">
    <div align="center">
      <p align="center">Intinerario:</p>
      <p align="center">
        <label>
        <textarea name="intinerario" id="intinerario" cols="45" rows="5"></textarea>
        </label>
      </p>
      <p align="center">Imprevistos:</p>
      <p align="center">
        <label>
        <textarea name="imprevistos" id="imprevistos" cols="45" rows="5"></textarea>
        </label>
      </p>
      <table width="800" border="1">
        <tr>
          <td width="200"><label>
            <input type="checkbox" name="cabeamento_estruturado" id="cabeamento_estruturado">
            Cabeamento Estruturado</label></td>
          <td width="200"><label>
            <input type="checkbox" name="instalacoes_eletricas" id="instalacoes_eletricas">
            Instalacoes Eletricas</label></td>
          <td width="200"><label>
            <input type="checkbox" name="instalacoes_fisicas" id="instalacoes_fisicas">
            Instalacoes Fisicas</label></td>
          <td width="172"><label>
            <input type="checkbox" name="organizacao_dos_cabos" id="organizacao_dos_cabos">
            Organizacao dos Cabos</label></td>
        </tr>
        <tr>
          <td><label>
            <input type="checkbox" name="layout_do_ambiente" id="layout_do_ambiente">
            Layout do Ambiente</label></td>
          <td><label>
            <input type="checkbox" name="limpeza_dos_micros" id="limpeza_dos_micros">
            Limpeza dos Micros</label></td>
          <td><label>
            <input type="checkbox" name="limpeza_das_impressoras" id="limpeza_das_impressoras">
            Limpeza das Impressoras</label></td>
          <td><label>
            <input type="checkbox" name="imagens_dos_micros" id="imagens_dos_micros">
            Imagens dos Micros</label></td>
        </tr>
        <tr>
          <td><label>
            <input type="checkbox" name="tensao_dos_estabilizadores" id="tensao_dos_estabilizadores">
            Tensao dos Estabilizadores</label></td>
          <td><label>
            <input type="checkbox" name="tensao_dos_no_breaks" id="tensao_dos_no_breaks">
            Tensao dos NoBreaks</label></td>
          <td colspan="2"><label>
            <input type="checkbox" name="outros" id="outros"/>
            Outros (especificar): </label>
            <label>
            <input type="text" name="outros_string" id="outros_string">
            </label>
          </td>
        </tr>
      </table>
      <p align="center">Servicos Realizados:</p>
      <p align="center">
        <label>
        <textarea name="servicos" id="servicos" cols="45" rows="5"></textarea>
        </label>
      </p>
      <p align="center">Pendencias:</p>
      <p align="center">
        <label>
        <textarea name="pendencias" id="pendencias" cols="45" rows="5"></textarea>
        </label>
      </p>
      <p align="center">
        <label>
        <input type="submit" name="avancar" id="avancar" value="Avancar">
        <input type="button" class="input_button" name="cancelar" value="Cancelar"
					 onClick="javascript:history.back()">
        <input name="idChamado" type="hidden" value="${chamado.idChamado}">
        </label>
      </p>
    </div>
    <jsp:include page="foot.jsp" flush="true" />    

  </form>
  <p>&nbsp;</p>
  <p align="left">&nbsp;</p>
</div>




