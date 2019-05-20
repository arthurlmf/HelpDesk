package struts;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts2.ServletActionContext;

import usuario.Usuario;
import util.HelpDeskUtil;
import bancoDeSolucao.BaseConhecimento;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class PesquisarBaseCompletaAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//HttpServletRequest request = ServletActionContext.getRequest();
//		Principal user2 = request.getUserPrincipal();
//		System.out.println(user2);
//		String authType = request.getAuthType();         // http or https
//		String user = request.getRemoteUser();           // the user principal (in string)
//		//Principalprincipal = request.getUserPrincipal(); // get a Principal object
//		boolean isAuth = request.isUserInRole("patrick");
		
		
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String palavraChave = (String) request.getParameter(Constantes.KEY_WORD);
			String artigoPesquisar =request.getParameter("artigo");
			String faqPesquisar = request.getParameter("faq");
			String solucaoPesquisar = request.getParameter("solucao");
			
			boolean artigo = incluir(artigoPesquisar);
			boolean faq = incluir(faqPesquisar);
			boolean solucao = incluir(solucaoPesquisar);
			if(!artigo && !faq && !solucao){
				return map.findForward(Constantes.INDEX_PESQUISAR_BASE);
			}
			
			BaseConhecimento base  = facade.pesquisarAll(palavraChave,artigo,faq,solucao);
			
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

	private boolean incluir(String string) {
		boolean incluirBool = false;
		if (!HelpDeskUtil.isNullOrVazio(string)) {
			incluirBool = true;
		}
		return incluirBool;
	}
}
