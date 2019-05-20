<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
	function preencher(form) {
		form.valor.value = form.idConfig.value;
	}
	
	function preencherHidden(form) {
		form.idDaConfig.value = form.idConfig.text;
	}
	
	
</script>

<jsp:include page="head_escolher.jsp" flush="true" /><br />
<p align="center" style="font-weight: bold">Editar Configurações</p>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		<form id="form1" name="form1" method="post" action="/helpdesk/editarConfiguracoes.do">
		<table border="0" align="center" cellpadding="0" cellspacing="3">
			<tr>
				<th>Configuração:</th>
				<th>
				<div align="left"><select name="idConfig" id="combo" onChange="javascript: this.form.idConfig.value=this.value;">
					<c:forEach var="config" items="${configs}" varStatus="posicao">
						<option value="${config.id}">${config.id}</option>
					</c:forEach>					
				</select></div>
				</th>
			</tr>
			<tr>
				<th>Novo Valor:</th>
				<th>
				<div align="left"><input name="valor" type="text" id="id" size="40" maxlength="45" /></div>
				</th>
			</tr>			
				<th colspan="2"><label>

				<div align="center"><input name="submeter" type="submit"
					id="submeter" value="Editar" /> <input type="reset" name="Reset"
					value="Limpar" /></div>
				</label></th>
			</tr>
		</table>
		<br>
		OBS: CUIDADO AO EDITAR AS CONFIGURACOES DO SISTEMA!
		<br>
		<input name="idConfig" type="hidden" value="">
		</form>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<jsp:include page="foot.jsp" flush="true" />