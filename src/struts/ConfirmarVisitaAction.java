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
import usuario.Usuario;
import util.HelpDeskUtil;
import util.Util;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class ConfirmarVisitaAction extends Action {

	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		int idChamado = Integer.parseInt(request
				.getParameter(Constantes.ID_CHAMADO));
		try {
			Usuario usuario = MySession.getInstance().getUsuario();

			boolean cabeamento_estruturado = false;
			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("cabeamento_estruturado"))) {
				cabeamento_estruturado = true;
			}
			boolean instalacoes_eletricas = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("instalacoes_eletricas"))) {
				instalacoes_eletricas = true;
			}
			boolean instalacoes_fisicas = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("instalacoes_fisicas"))) {
				instalacoes_fisicas = true;
			}
			boolean organizacao_dos_cabos = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("organizacao_dos_cabos"))) {
				organizacao_dos_cabos = true;
			}
			boolean layout_do_ambiente = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("layout_do_ambiente"))) {
				layout_do_ambiente = true;
			}
			boolean limpeza_dos_micros = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("limpeza_dos_micros"))) {
				limpeza_dos_micros = true;
			}
			boolean limpeza_das_impressoras = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("limpeza_das_impressoras"))) {
				limpeza_das_impressoras = true;
			}
			boolean imagens_dos_micros = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("imagens_dos_micros"))) {
				imagens_dos_micros = true;
			}
			boolean tensao_dos_estabilizadores = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("tensao_dos_estabilizadores"))) {
				tensao_dos_estabilizadores = true;
			}
			boolean tensao_dos_no_breaks = false;

			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("tensao_dos_no_breaks"))) {
				tensao_dos_no_breaks = true;
			}

			boolean outros = false;
			if (!HelpDeskUtil.isNullOrVazio(request.getParameter("outros"))) {
				outros = true;
			}
			String imprevistos = request.getParameter("imprevistos");
			String intinerario = request.getParameter("intinerario");
			String servicos = request.getParameter("servicos");
			String pendencias = request.getParameter("pendencias");
			String outros_string = request.getParameter("outros_string");
			
			String listaDeRotinas = new String();
			
			if (cabeamento_estruturado) {
				listaDeRotinas = formatarRotina("cabeamento_estruturado", listaDeRotinas);
			}
			if (instalacoes_eletricas) {
				listaDeRotinas = formatarRotina("instalacoes_eletricas", listaDeRotinas);
			}
			if (instalacoes_fisicas) {
				listaDeRotinas = formatarRotina("instalacoes_fisicas", listaDeRotinas);
			}
			if (organizacao_dos_cabos) {
				listaDeRotinas = formatarRotina("organizacao_dos_cabos", listaDeRotinas);
			}
			if (layout_do_ambiente) {
				listaDeRotinas = formatarRotina("layout_do_ambiente", listaDeRotinas);
			}
			if (limpeza_dos_micros) {
				listaDeRotinas = formatarRotina("limpeza_dos_micros", listaDeRotinas);
			}
			if (limpeza_das_impressoras) {
				listaDeRotinas = formatarRotina("limpeza_das_impressoras", listaDeRotinas);
			}
			if (imagens_dos_micros) {
				listaDeRotinas = formatarRotina("imagens_dos_micros", listaDeRotinas);
			}
			if (tensao_dos_estabilizadores) {
				listaDeRotinas = formatarRotina("tensao_dos_estabilizadores", listaDeRotinas);
			}
			if (tensao_dos_no_breaks) {
				listaDeRotinas = formatarRotina("tensao_dos_no_breaks", listaDeRotinas);
			}
			if (outros) {
				listaDeRotinas = formatarRotina(outros_string, listaDeRotinas);
			}

			Visita visita = new Visita(usuario.getIdUsuario(),
					intinerario, imprevistos, cabeamento_estruturado,
					layout_do_ambiente, tensao_dos_estabilizadores,
					instalacoes_eletricas, limpeza_dos_micros,
					tensao_dos_no_breaks, instalacoes_fisicas,
					limpeza_das_impressoras, organizacao_dos_cabos,
					imagens_dos_micros, outros_string, outros, servicos,
					pendencias);
			
			Chamado chamado = facade.getChamado(idChamado);
			ChamadoAtendido chamadoAtendido = facade
					.getChamadoAtendido(idChamado);

			request.setAttribute(Constantes.CHAMADO, chamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			request.setAttribute(Constantes.VISITA, visita);
			request.setAttribute("rotina", listaDeRotinas);
			
			
			

			return map.findForward(Constantes.CONFIRMAR_RELATORIO_VISITA);

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
