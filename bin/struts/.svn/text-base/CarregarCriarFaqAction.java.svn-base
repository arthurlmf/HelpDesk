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

public class CarregarCriarFaqAction extends Action  {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
//			if(!isPermitido()){
//				return map.findForward(Constantes.ACESSO_NEGADO);
//			}
			
			Usuario usuario = MySession.getInstance().getUsuario();
			
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

//	private boolean isPermitido() {
//		return MySession.getInstance().getControlador().isPermitido(ControladorPermissoes.TECNICO) || MySession.getInstance().getControlador().isPermitido(ControladorPermissoes.TECNICO_GERENTE);
//	}

}
