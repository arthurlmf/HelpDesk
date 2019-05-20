package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import relacionamento.ChamadoAtendido;
import usuario.Tecnico;
import usuario.Usuario;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class SelecionarTecnicoEncaminharAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		int idChamado = Integer.parseInt(request.getParameter("idChamado"));
		try{
			Usuario usuario = MySession.getInstance().getUsuario();		
			Chamado chamado = facade.getChamado(idChamado);		
			request.setAttribute(Constantes.CHAMADO, chamado);
			List<Tecnico> tecnicos = facade.getTecnicosMenosEu(usuario.getIdUsuario());
			ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			request.setAttribute(Constantes.TECNICOS, tecnicos);
			return map.findForward(Constantes.ENCAMINHAR_CHAMADO);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
