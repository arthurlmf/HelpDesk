package struts;


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

public class CarregarGerenciarUnidadesAction extends Action {
	
	
	

	

	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			if(!usuario.isAdministrador()){
				return map.findForward(Constantes.ACESSO_NEGADO);
			}
			return map.findForward(Constantes.GERENCIAR_UNIDADES);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
