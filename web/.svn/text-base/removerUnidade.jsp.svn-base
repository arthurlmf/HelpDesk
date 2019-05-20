<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="head_escolher.jsp" flush="true" /><br/>
<p align="center" style="font-weight: bold">Remover Unidade </p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><form id="form1" name="form1" method="post" action="/helpdesk/removerUnidade.do">
      <table border="0" align="center" cellpadding="0" cellspacing="3">
        <tr>
          <th><div align="left">Unidade: </div></th>
          <th> <div align="left">
            <select name="unidade" id="combo">              
              <c:forEach var="unidade" items="${unidades}" varStatus="posicao">
                <option value="${unidade.idUsuario}">${unidade.nome}</option>
              </c:forEach>
            </select>
          </div></th>
        </tr>       
        <tr>
          <th colspan="2"><label>
                
                  <div align="center">
                    <input name="submeter" type="submit" id="submeter" value="Remover" />
                    </div>
                  </label></th>
        </tr>
      </table>
    </form>
        <p>&nbsp;</p></td>
  </tr>
</table>
<jsp:include page="foot.jsp" flush="true" />