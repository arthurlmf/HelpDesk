package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Usuario;
import bancoDeSolucao.BaseConhecimento;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class PesquisarBaseAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String palavraChave =  request.getParameter(Constantes.KEY_WORD);
			BaseConhecimento base  = facade.pesquisarAll(palavraChave);
			
			request.setAttribute("artigosResult",base.getListaArtigo());
			request.setAttribute("quantArtigosResult",base.getListaArtigo().size());
			
			request.setAttribute("faqsResult",base.getListaFAQ());
			request.setAttribute("quantFAQResult",base.getListaFAQ().size());
			
			request.setAttribute("solucoesResult",base.getListaSolucao());
			request.setAttribute("quantSolucaoResult",base.getListaSolucao().size());
			
			request.setAttribute("total",base.getListaArtigo().size()+base.getListaFAQ().size()+base.getListaSolucao().size());
			
			return map.findForward(Constantes.RESULTADO_PESQUISAR_BASE);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}
}
