package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import relacionamento.ChamadoAtendido;
import usuario.Usuario;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class SolicitarChamadoAction extends Action {

	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try {
			Usuario usuario = MySession.getInstance().getUsuario();
			String descricao = request.getParameter("descricao");
			String tipo = request.getParameter("tipo");
			String subtipo = request.getParameter("subtipo");
			String patrimonio = request.getParameter("idPatrimonio");
			String unidadeSuporte = request.getParameter("idUnidade");

			int idChamado = 0;
			if (facade.temPatrimonio(usuario.getIdUsuario(), tipo, subtipo)) {
				try {
					if (!facade.existEquipamento(patrimonio)) {
						request.setAttribute(Constantes.MENSAGEM,
								"PATRIMONIO INVÁLIDO");
						return map.findForward(Constantes.PG_ERRO);
					}
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute(Constantes.MENSAGEM, "ERRO: "
							+ e.getMessage());
					return map.findForward(Constantes.PG_ERRO);
				}
				idChamado = facade.solicitarChamado(descricao, tipo, subtipo,
						patrimonio, usuario.getIdUsuario(), unidadeSuporte);

			} else {
				idChamado = facade.solicitarChamado(descricao, tipo, subtipo,
						"", usuario.getIdUsuario(), unidadeSuporte);
			}
			Chamado chamado = facade.getChamado(idChamado);
			request.setAttribute(Constantes.CHAMADO, chamado);
			ChamadoAtendido chamadoAtendido = facade
					.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			return map.findForward(Constantes.VER_CHAMADO);

		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		} catch (HelpDeskException e) {
			return LoginAction.redirecionarErro(map, request, e.getMessage());
		}
	}
}
