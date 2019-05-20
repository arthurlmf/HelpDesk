package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import chamado.Chamado;

import relacionamento.ChamadoAtendido;
import transacao.Visita;
import util.HelpDeskUtil;
import util.Util;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class RelatarVisitaAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		int idChamado = Integer.parseInt(request.getParameter(Constantes.ID_CHAMADO));
		String idTecnico = request.getParameter("idTecnico");
		String intinerario = request.getParameter("intinerario");
		String imprevistos = request.getParameter("imprevistos");
		boolean cabeamentoEstruturado = false;
		
		if (request.getParameter("cabeamentoEstruturado").equals("true")) {
			cabeamentoEstruturado = true;
		}
		boolean instalacoesEletricas = false;

		if (request.getParameter("instalacoesEletricas").equals("true")) {
			instalacoesEletricas = true;
		}
		boolean instalacoesFisicas = false;

		if (request.getParameter("instalacoesFisicas").equals("true")) {
			instalacoesFisicas = true;
		}
		boolean organizacaoDosCabos = false;

		if (request.getParameter("organizacaoDosCabos").equals("true")) {
			organizacaoDosCabos = true;
		}
		boolean layoutDoAmbiente = false;

		if (request.getParameter("layoutDoAmbiente").equals("true")) {
			layoutDoAmbiente = true;
		}
		boolean limpezaDosMicros = false;

		if (request.getParameter("limpezaDosMicros").equals("true")) {
			limpezaDosMicros = true;
		}
		boolean limpezaDasImpressoras = false;

		if (request.getParameter("limpezaDasImpressoras").equals("true")) {
			limpezaDasImpressoras = true;
		}
		boolean imagensDosMicros = false;

		if (request.getParameter("imagensDosMicros").equals("true")) {
			imagensDosMicros = true;
		}
		boolean tensaoDosEstabilizadores = false;

		if (request.getParameter("tensaoDosEstabilizadores").equals("true")) {
			tensaoDosEstabilizadores = true;
		}
		boolean tensaoDosNoBreaks = false;

		if (request.getParameter("tensaoDosNoBreaks").equals("true")) {
			tensaoDosNoBreaks = true;
		}

		boolean outros = false;
		if (request.getParameter("outros").equals("true")) {
			outros = true;
		}
		String outrosString = request.getParameter("outrosString");
		String servicosRealizados = request.getParameter("servicosRealizados");
		String pendencias = request.getParameter("pendencias");
		String idUsuario = facade.getChamadoAtendido(idChamado).getUnidadeSolicitante();
		String senha = request.getParameter("senha");
		
		if(!facade.isAuthenticatedUser(idUsuario, senha)){
			request.setAttribute(Constantes.MENSAGEM,
						"AUTENTICAÇÃO INVÁLIDA");
			return map.findForward(Constantes.PG_ERRO);
		}
		try {
			
		int idVisita = facade.criarRelatorioVisita(idChamado, idTecnico, intinerario, imprevistos, cabeamentoEstruturado,
				layoutDoAmbiente, tensaoDosEstabilizadores, instalacoesEletricas, limpezaDosMicros,
				tensaoDosNoBreaks, instalacoesFisicas, limpezaDasImpressoras, organizacaoDosCabos, 
				imagensDosMicros, outrosString, outros, servicosRealizados, pendencias);
		
		Chamado chamado = facade.getChamado(idChamado);
		Visita visita = facade.getVisita(idVisita);
		ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
		request.setAttribute(Constantes.CHAMADO, chamado);
		request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
		request.setAttribute(Constantes.VISITA, visita);
		
		String listaDeRotinas = new String();
		
		if (visita.isCabeamentoEstruturado()) {
			listaDeRotinas = formatarRotina("cabeamento_estruturado", listaDeRotinas);
		}
		if (visita.isInstalacoesEletricas()) {
			listaDeRotinas = formatarRotina("instalacoes_eletricas", listaDeRotinas);
		}
		if (visita.isInstalacoesFisicas()) {
			listaDeRotinas = formatarRotina("instalacoes_fisicas", listaDeRotinas);
		}
		if (visita.isOrganizacaoDosCabos()) {
			listaDeRotinas = formatarRotina("organizacao_dos_cabos", listaDeRotinas);
		}
		if (visita.isLayoutDoAmbiente()) {
			listaDeRotinas = formatarRotina("layout_do_ambiente", listaDeRotinas);
		}
		if (visita.isLimpezaDosMicros()) {
			listaDeRotinas = formatarRotina("limpeza_dos_micros", listaDeRotinas);
		}
		if (visita.isLimpezaDasImpressoras()) {
			listaDeRotinas = formatarRotina("limpeza_das_impressoras", listaDeRotinas);
		}
		if (visita.isImagensDosMicros()) {
			listaDeRotinas = formatarRotina("imagens_dos_micros", listaDeRotinas);
		}
		if (visita.isTensaoDosEstabilizadores()) {
			listaDeRotinas = formatarRotina("tensao_dos_estabilizadores", listaDeRotinas);
		}
		if (visita.isTensaoDosNoBreaks()) {
			listaDeRotinas = formatarRotina("tensao_dos_no_breaks", listaDeRotinas);
		}
		if (visita.isOutros()) {
			listaDeRotinas = formatarRotina(visita.getOutrosString(), listaDeRotinas);
		}
		
		request.setAttribute("servicos", listaDeRotinas);		
		
		return map.findForward(Constantes.VER_RELATORIO_VISITA);

		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		} catch (HelpDeskException e) {
			return LoginAction.redirecionarErro(map, request, e.getMessage());
		}		
	}
	
	private String formatarRotina(String string, String rotina) {
		string = string.replace("_", " ");
		string = Util.capitular(string);
		rotina = rotina.concat(string + ", ");
		return rotina;
	}
	
}

