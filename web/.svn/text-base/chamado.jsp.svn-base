<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help Desk TRE</title>
    </head>
     <body>
        <form action="/helpdesk/efetuarChamado.do">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Unidade de Suporte: </td>
                        <td> 
                            <select name="unidade" id="combo">
                                <c:forEach var="unidade" items="${unidades}">
                                    <option value=${unidade.idUsuario}>${unidade.nome}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Tipo: </td>
                        <td>
                            <select name="tipo" id="combo">
                                <c:forEach var="tipo" items="${tipos}">
                                    <option value=${tipo.nome}>${tipo.nome}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Subtipo: </td>
                        <td>
                            <select name="subtipo" id="combo">
                                <c:forEach var="subtipo" items="${subtipos}">
                                    <option value=${subtipo.nome}>${subtipo.nome}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Descrição: </td>
                        <td>
                            <input type="text" name="descricao" value="" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="ok" name="Solicitar" />
        </form>
        
    </body>
</html>
