package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import chamado.Chamado;

import usuario.Unidade;
import util.Util;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class IndexUnidadeSuporteAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Unidade unidade = (Unidade) MySession.getInstance().getUsuario();
			request.setAttribute(Constantes.UNIDADE, Util.capitular(unidade.toString()));
			
			//meus chamados, ou seja, chamados que foram solicitados para esta unidade
			List<Chamado> chamados = facade.getChamadosUnidade(unidade.getIdUsuario());
			request.setAttribute(Constantes.QUANT_CHAMADOS_RESULT, chamados.size());
			request.setAttribute(Constantes.CHAMADOS, chamados);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
		
				
		return  map.findForward(Constantes.UNIDADE_SUPORTE);
		
		
		
	}
}
