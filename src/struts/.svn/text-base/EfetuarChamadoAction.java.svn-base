package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Unidade;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class EfetuarChamadoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Unidade unidade = (Unidade) MySession.getInstance().getUsuario();			
			FacadeHelpDesk facade = new FacadeHelpDesk();
			String unidadeSuporte = (String) request.getParameter("unidade");
			String tipo = (String) request.getParameter("tipo");
			String subtipo = (String) request.getParameter("subtipo");
			String descricao = (String) request.getParameter("descricao");
			facade.solicitarChamado(descricao, tipo, subtipo, "idpatrimonio", unidade.getIdUsuario(), unidadeSuporte);
			return map.findForward("ok");
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
		
	}
}
