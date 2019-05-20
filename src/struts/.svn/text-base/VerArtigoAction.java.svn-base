package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Usuario;
import bancoDeSolucao.Artigo;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class VerArtigoAction extends Action {
	


	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String idArtigo = request.getParameter(Constantes.ID_ARTIGO);
			Artigo artigo = facade.getArtigo(idArtigo);			
			request.setAttribute(Constantes.ARTIGO, artigo);
			try {				
				if(usuario.isUnidade()){
					request.setAttribute(Constantes.PODE_EDITAR,false);
				}else{
					String idUnidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario()).getIdUsuario();
					request.setAttribute(Constantes.PODE_EDITAR,facade.existTipoDaUnidade(idUnidade, artigo.getTipo()));
				}								
			} catch (HelpDeskException e) {
				request.setAttribute(Constantes.PODE_EDITAR,false);
			}
			
			
			return map.findForward(Constantes.VER_ARTIGO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
