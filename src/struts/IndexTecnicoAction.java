package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Tecnico;
import usuario.Unidade;
import util.Util;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class IndexTecnicoAction extends Action {
	
	

	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Tecnico tecnico = (Tecnico)MySession.getInstance().getUsuario();
			setCabecalho(request, facade, tecnico);	
			Unidade unidadeDoTecnico = facade.getUnidadeDoTecnico(tecnico.getIdUsuario());
			List<Chamado> chamados = facade.getChamadosTecnico(tecnico.getIdUsuario());		
			request.setAttribute(Constantes.QUANT_CHAMADOS_RESULT, chamados.size());
			request.setAttribute(Constantes.CHAMADOS, chamados);
			
			boolean podeVer =  tecnico.isGerente() || !unidadeDoTecnico.isGerenciaDelegacao();
			request.setAttribute(Constantes.PODE_VER_CHAMADOS_REPASSADOS,podeVer);
			return map.findForward(Constantes.TECNICO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
	}

	private static void setCabecalho(HttpServletRequest request,
			FacadeHelpDesk facade, Tecnico tecnico) throws HelpDeskException {
		Unidade unidadeDoTecnico = facade.getUnidadeDoTecnico(tecnico.getIdUsuario());
		request.setAttribute(Constantes.UNIDADE, Util.capitular(unidadeDoTecnico.toString()));	
	}

	
}

