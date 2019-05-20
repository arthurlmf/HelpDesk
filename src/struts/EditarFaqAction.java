
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
import bancoDeSolucao.FAQ;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class EditarFaqAction extends Action {
	
	

	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String pergunta = request.getParameter(Constantes.PERGUNTA);
			String resposta = request.getParameter(Constantes.RESPOSTA);
			String tipo = request.getParameter(Constantes.TIPO);
			String subtipo = request.getParameter(Constantes.SUBTIPO);
			String idFAQ = request.getParameter(Constantes.ID_FAQ);
			
			facade.alterarAtributoFAQ(idFAQ, Constantes.PERGUNTA, pergunta);
			facade.alterarAtributoFAQ(idFAQ, Constantes.RESPOSTA, resposta);
			facade.alterarAtributoFAQ(idFAQ, Constantes.TIPO, tipo);
			facade.alterarAtributoFAQ(idFAQ, Constantes.SUBTIPO, subtipo);
			
			FAQ faq = facade.getFaq(idFAQ);
			request.setAttribute(Constantes.FAQ, faq);
			String idUnidade = facade.getUnidadeDoTecnico(
					usuario.getIdUsuario()).getIdUsuario();
			List<Tipo> tipos = facade.getTiposDaUnidade(idUnidade);
			Tipo typo = facade.getTipo(idUnidade, faq.getTipo());
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
			return map.findForward(Constantes.EDITAR_FAQ);
			
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}
}
