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

public class CriarTecnicoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String nome = request.getParameter("nome");
			String idTecnico = request.getParameter("id");
			String dominio = MySession.getInstance().getDominio();
			if(!idTecnico.contains(dominio)){
				idTecnico = idTecnico+dominio;
			}
			
			String unidade = request.getParameter("idUnidade");
			facade.criarTecnico(idTecnico.toLowerCase(), nome);
			facade.relacionarTecnicoComUnidade(idTecnico, unidade);
			return map.findForward(Constantes.CRIAR_TECNICO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
