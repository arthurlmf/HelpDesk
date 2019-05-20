package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Usuario;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class PesquisarChamadoUnidadeAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String unidadeSolicitante = usuario.getIdUsuario();
			String unidadeAtendente = request.getParameter("idUnidade");
			String tipo = request.getParameter("tipo");
			String subtipo = request.getParameter("subtipo");
			String estado = request.getParameter("estado");
			String patrimonio = request.getParameter("idPatrimonio");
			List chamados = facade.pesquisaChamados(unidadeSolicitante, unidadeAtendente, tipo, subtipo, estado, patrimonio);
			request.setAttribute(Constantes.QUANT_CHAMADOS_RESULT, chamados.size());
			request.setAttribute(Constantes.CHAMADOS, chamados);
			return map.findForward(Constantes.RESULTADO_CHAMADOS_UNIDADE);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
