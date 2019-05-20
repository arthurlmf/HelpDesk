<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br/>
<p align="center" style="font-weight: bold">Criar Tecnico </p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><form id="form1" name="form1" method="post" action="/helpdesk/criarTecnico.do">
      <table border="0" align="center" cellpadding="0" cellspacing="3">
        <tr>
          <th>Unidade de Suporte:</th>
          <th><div align="left">
            <select name="idUnidade" id="combo"> 
				<c:forEach var="unidade" items="${unidades}" varStatus="posicao">
					<option value="${unidade.idUsuario}">${unidade.nome}</option>
				</c:forEach>
           </select>
          </div></th>
        </tr>        
        <tr>
          <th>E-mail:</th>
          <th><div align="left">
            <input name="id" type="text" id="id" maxlength="80"/> ${DOMAIN}
          </div></th>
        </tr>
        <tr>
          <th>Nome:</th>
          <th><div align="left">
            <input name="nome" type="text"  id="nome" maxlength="80"/>
          </div></th>
        </tr>
        <tr>
          <th colspan="2"><label>
                
                  <div align="center">
                    <input name="submeter" type="submit" id="submeter" value="Criar" />
                    <input type="reset" name="Reset" value="Limpar" />
                    </div>
                  </label></th>
        </tr>
      </table>
    </form>
        <p>&nbsp;</p></td>
  </tr>
</table>
<jsp:include page="foot.jsp" flush="true" />