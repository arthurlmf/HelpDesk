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

public class CarregarIntervirChamadoAction extends Action {
	
	



	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
			int idChamado = Integer.parseInt(request.getParameter(Constantes.ID_CHAMADO));
			Chamado chamado = facade.getChamado(idChamado);
			ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO, chamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
				
			return map.findForward(Constantes.INTERVIR_CHAMADO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
		
	}
}
