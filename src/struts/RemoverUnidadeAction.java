package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Unidade;
import usuario.Usuario;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class RemoverUnidadeAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String idUnidade = request.getParameter("unidade");
			facade.removerUnidade(idUnidade);			
			List<Unidade> unidades = facade.getAllUnidades();
			request.setAttribute(Constantes.UNIDADES, unidades);
			return map.findForward(Constantes.REMOVER_UNIDADE);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
