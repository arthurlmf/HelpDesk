package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tipo.Subtipo;
import tipo.Tipo;
import usuario.Unidade;
import usuario.Usuario;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class ChamadoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
			List<Unidade> listaUnidades = facade.getAllUnidades();
			List<Tipo> listaTipos = facade.getAllTipos();
			List<Subtipo> listaSubtipos = facade.getAllSubtipos();
			request.setAttribute(Constantes.UNIDADES, listaUnidades);
			request.setAttribute(Constantes.TIPOS, listaTipos);
			request.setAttribute(Constantes.SUBTIPOS, listaSubtipos);
			return map.findForward(Constantes.OK);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
