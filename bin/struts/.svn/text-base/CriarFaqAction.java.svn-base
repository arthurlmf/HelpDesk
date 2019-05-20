
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
import usuario.Usuario;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class CriarFaqAction extends Action {
	
	

	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String pergunta = request.getParameter(Constantes.PERGUNTA);
			String resposta = request.getParameter(Constantes.RESPOSTA);
			String tipo = request.getParameter(Constantes.TIPO);
			String subtipo = request.getParameter(Constantes.SUBTIPO);
			
			facade.criarFaq(pergunta, resposta, tipo, subtipo);
			String idUnidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario()).getIdUsuario();
			List<Tipo> tipos = facade.getTiposDaUnidade(idUnidade);
			request.setAttribute(Constantes.TIPOS, tipos);
			List<Subtipo> subtipos = facade.getSubtiposDeTipo(idUnidade, tipos.get(0).getNomeTipo());
			request.setAttribute(Constantes.SUBTIPOS, subtipos);
			return map.findForward(Constantes.CRIAR_FAQ);
			
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}
}
