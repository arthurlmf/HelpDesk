package struts;


import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tipo.Subtipo;
import tipo.Tipo;
import usuario.Usuario;
import bancoDeSolucao.Artigo;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class CarregarEditarArtigoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String idArtigo = request.getParameter(Constantes.ID_ARTIGO);
			Artigo artigo = facade.getArtigo(idArtigo);			
			request.setAttribute(Constantes.ARTIGO, artigo);
			
			String idUnidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario()).getIdUsuario();
			List<Tipo> tipos = facade.getTiposDaUnidade(idUnidade);
			Tipo tipo = facade.getTipo(idUnidade, artigo.getTipo());
			LinkedList<Tipo> myTipos = new LinkedList<Tipo>(tipos);
			if(myTipos.contains(tipo)){
				myTipos.addFirst(myTipos.remove(myTipos.indexOf(tipo)));
			}else{
				myTipos.addFirst(tipo);
			}
			
			request.setAttribute(Constantes.TIPOS, myTipos);
			List<Subtipo> subtipos = facade.getSubtiposDeTipo(idUnidade, tipo.getNomeTipo());
			request.setAttribute(Constantes.SUBTIPOS, subtipos);
			return map.findForward(Constantes.EDITAR_ARTIGO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
