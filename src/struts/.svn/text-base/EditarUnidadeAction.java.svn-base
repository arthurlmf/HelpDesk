package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Unidade;
import usuario.Usuario;
import util.HelpDeskUtil;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class EditarUnidadeAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			String idUnidade = request.getParameter(Constantes.UNIDADE);
			String nomeGerente = request.getParameter(Constantes.NOME_GERENTE);
			String idGerente = request.getParameter(Constantes.EMAIL_GERENTE);
			String dominio = MySession.getInstance().getDominio();
			String suporte = request.getParameter(Constantes.SUPORTE);
			String gerencia = request.getParameter(Constantes.GERENCIA);
			
			boolean isSuporte = !HelpDeskUtil.isNullOrVazio(suporte);
			
			if (isSuporte){
				facade.setSuporte(idUnidade, isSuporte);
				facade.setGerencia(idUnidade, gerencia);				
				if(!idGerente.contains(dominio)){
					idGerente = idGerente+dominio;
				}
				
				facade.criarTecnico(idGerente, nomeGerente);
				facade.relacionarTecnicoComUnidade(idGerente, idUnidade);
				facade.setarGerente(idGerente, idUnidade);
			}			
			
			List<Unidade> unidades = facade.getAllUnidadesSomenteSolicitantes();
			request.setAttribute(Constantes.UNIDADES, unidades);
			return map.findForward(Constantes.EDITAR_UNIDADE);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
