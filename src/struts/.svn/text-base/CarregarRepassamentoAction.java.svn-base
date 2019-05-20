package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import relacionamento.ChamadoAtendido;
import usuario.Unidade;
import usuario.Usuario;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class CarregarRepassamentoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		int idChamado = Integer.parseInt(request.getParameter(Constantes.ID_CHAMADO));
		try{
			Usuario usuario = MySession.getInstance().getUsuario();		
			Chamado chamado = facade.getChamado(idChamado);		
			request.setAttribute(Constantes.CHAMADO, chamado);
			Unidade unidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario());
			List<Unidade> unidades = facade.getUnidadesSuportes(unidade.getIdUsuario());
			ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			request.setAttribute(Constantes.UNIDADES, unidades);
			return map.findForward(Constantes.REPASSAR_CHAMADO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
