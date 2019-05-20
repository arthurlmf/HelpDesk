package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import relacionamento.ChamadoAtendido;
import transacao.Transacao;
import transacao.Visita;
import usuario.Usuario;
import util.Util;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class VerRelatorioVisitaAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
			int idChamado = Integer.parseInt(request.getParameter("idChamado"));
			Chamado chamado = facade.getChamado(idChamado);
			int idVisita = Integer.parseInt(request.getParameter("idVisita"));
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
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}
	
	private String formatarRotina(String string, String rotina) {
		string = string.replace("_", " ");
		string = Util.capitular(string);
		rotina = rotina.concat(string + ", ");
		return rotina;
	}
	
}
