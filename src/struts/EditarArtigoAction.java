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

public class EditarArtigoAction extends Action {

	

	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try {
			Usuario usuario = MySession.getInstance().getUsuario();
			String titulo = request.getParameter(Constantes.TITULO);
			String texto = request.getParameter(Constantes.TEXTO);
			String tipo = request.getParameter(Constantes.TIPO);
			String subtipo = request.getParameter(Constantes.SUBTIPO);
			String idArtigo = request.getParameter(Constantes.ID_ARTIGO);

			facade.alterarAtributoArtigo(idArtigo, Constantes.TITULO, titulo);
			facade.alterarAtributoArtigo(idArtigo, Constantes.TEXTO, texto);
			facade.alterarAtributoArtigo(idArtigo, Constantes.TIPO, tipo);
			facade.alterarAtributoArtigo(idArtigo, Constantes.SUBTIPO, subtipo);

			Artigo artigo = facade.getArtigo(idArtigo);
			request.setAttribute(Constantes.ARTIGO, artigo);

			String idUnidade = facade.getUnidadeDoTecnico(
					usuario.getIdUsuario()).getIdUsuario();
			List<Tipo> tipos = facade.getTiposDaUnidade(idUnidade);
			Tipo typo = facade.getTipo(idUnidade, artigo.getTipo());
			LinkedList<Tipo> myTipos = new LinkedList<Tipo>(tipos);
			if (myTipos.contains(typo)) {
				myTipos.addFirst(myTipos.remove(myTipos.indexOf(typo)));
			} else {
				myTipos.addFirst(typo);
			}

			request.setAttribute(Constantes.TIPOS, myTipos);
			List<Subtipo> subtipos = facade.getSubtiposDeTipo(idUnidade, typo
					.getNomeTipo());
			request.setAttribute(Constantes.SUBTIPOS, subtipos);
			return map.findForward(Constantes.EDITAR_ARTIGO);
			

		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}
}
