package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tipo.Tipo;
import usuario.Tecnico;
import usuario.Unidade;
import usuario.Usuario;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class CarregarGerarRelatorioAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			Unidade unidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario());
			List<Unidade> unidades = facade.getSolicitantesUnidade(unidade.getIdUsuario());
			List<Tipo> tipos = facade.getTiposDaUnidade(unidade.getIdUsuario());
			List<Tecnico> tecnicos = facade.getTecnicos(unidade.getIdUsuario());
			request.setAttribute(Constantes.TIPOS, tipos);
			request.setAttribute(Constantes.TECNICOS, tecnicos);
			request.setAttribute(Constantes.UNIDADE, unidade);
			request.setAttribute(Constantes.UNIDADES, unidades);
			return map.findForward(Constantes.GERAR_RELATORIO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
