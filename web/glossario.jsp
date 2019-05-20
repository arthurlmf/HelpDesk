<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="helpDesk_head.jsp" flush="true" />

<p align="center" class="style2" id="msg">Gloss&aacute;rio</p>

<div align="center">
  <p align="justify"><span class="style3" id="subtitulo_pesquisa">Chamado</span></p>
  <p align="justify">&Eacute; uma  solicita&ccedil;&atilde;o de ajuda feita por um usu&aacute;rio. H&aacute; tr&ecirc;s tipos de chamado: <strong>software</strong>, <strong>hardware</strong> e <strong>servi&ccedil;o</strong>. Os  chamados de software e hardware t&ecirc;m o campo tipo espec&iacute;fico, que &eacute; uma  especifica&ccedil;&atilde;o mais espec&iacute;fica do componente em quest&atilde;o. Nos chamados de  hardware h&aacute; tamb&eacute;m o campo patrim&ocirc;nio que refere-se ao numero do patrim&ocirc;nio do  equipamento.</p>
  <p align="justify">Os campos <em><strong>ID solicitante</strong></em> e <em><strong>Respons&aacute;vel</strong></em> referem-se &agrave; identifica&ccedil;&atilde;o  &uacute;nica do usu&aacute;rio que solicitou o chamado e &agrave; identifica&ccedil;&atilde;o &uacute;nica do  respons&aacute;vel, o t&eacute;cnico, que est&aacute; com a posse do chamado, respectivamente.</p>
  <p align="justify">O campo <em><strong>Situa&ccedil;&atilde;o</strong></em> refere-se &agrave; situa&ccedil;&atilde;o atual do  chamado que pode ser <em>&ldquo;em analise&rdquo;</em>, <em>&ldquo;aberto&rdquo;</em>, <em>&ldquo;encaminhado&rdquo;</em> e <em>&ldquo;fechado&rdquo;</em>. <br />
  Veja  abaixo a cada situa&ccedil;&atilde;o detalhadamente:</p>
  <div align="justify">
    <ul type="disc">
      <li><em>&ldquo;Em analise&rdquo;:</em> o chamado foi encaminhado mas ainda nenhum t&eacute;cnico       tomou posse;</li>
      <li><em>&ldquo;Aberto&rdquo;:</em> nesse momento, o chamado j&aacute; tem um t&eacute;cnico       respons&aacute;vel;</li>
      <li><em>&ldquo;Encaminhado&rdquo;: </em>o chamado foi encaminhado pelo seu respons&aacute;vel&nbsp; para outro t&eacute;cnico;(ver encaminhamento)</li>
      <li><em>&ldquo;Fechado&rdquo;:</em> o chamado foi solucionado. </li>
    </ul>
  </div>
  <p align="justify">Al&eacute;m  disso, o campo <em><strong>Descri&ccedil;&atilde;o</strong></em> &eacute;, como o  pr&oacute;prio nome nos diz, uma descri&ccedil;&atilde;o do chamado ou um texto que esclare&ccedil;a o  chamado.</p>
  <p align="justify"><span class="style3">Encaminhamento</span></p>
  <p align="justify"> Acontece  quando o t&eacute;cnico repassa um chamado para outro t&eacute;cnico. A cada encaminhamento o  solicitante &eacute; informado por email.<br />
    NOTA: &Eacute;  importante ressaltar que a partir deste ponto o novo t&eacute;cnico ser&aacute; o respons&aacute;vel  e n&atilde;o mais o antigo t&eacute;cnico que encaminhou;</p>
  <p align="justify" class="style3">Interven&ccedil;&atilde;o</p>
  <p align="justify">&Eacute; uma  a&ccedil;&atilde;o feita pelo t&eacute;cnico em rela&ccedil;&atilde;o a um chamado. Uma interven&ccedil;&atilde;o possui a  descri&ccedil;&atilde;o desta a&ccedil;&atilde;o. &nbsp;A cada interven&ccedil;&atilde;o  o solicitante &eacute; informado por email.</p>
  <p align="center"><a href="javascript:self.close();">Fechar</a></p>

</div>

<!-- FOOT INICIO  --> 
 
<area shape="rect" coords="53,22,84,51" href="/helpdesk/indexTecnico.do" alt="P&aacute;gina Inicial">

<br/>			
	</td>
	</tr>
	<tr>
	<th height="5" align="right" bgcolor="#A2A29F">
		<img src="imagens/creditosIndex.gif" border="0" usemap="#Map_foot" />
		
	</th>
</tr>
	<tr>
		<td bgcolor="#993300"><br/>		</td>
	</tr>
</table>

</body>
</html>
<!-- FOOT FIM  -->

