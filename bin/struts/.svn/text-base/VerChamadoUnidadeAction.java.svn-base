package struts;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import relacionamento.ChamadoAtendido;
import transacao.Transacao;
import usuario.Usuario;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class VerChamadoUnidadeAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Usuario usuario = MySession.getInstance().getUsuario();
			FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
			int idChamado = Integer.parseInt(request.getParameter("idChamado"));
			List<Transacao> transacoes = facade.getTransacaoParaTecnico(idChamado);
			request.setAttribute(Constantes.TRANSACOES, transacoes);
			Chamado chamado = facade.getChamado(idChamado);		
			request.setAttribute(Constantes.CHAMADO, chamado);
			ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			return map.findForward(Constantes.VER_CHAMADO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
