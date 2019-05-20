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
import transacao.Visita;
import usuario.Unidade;
import usuario.Usuario;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;

public class VerChamadoTecnicoAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try{
			FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
			int idChamado = Integer.parseInt(request.getParameter(Constantes.ID_CHAMADO));
			List<Transacao> transacoes = facade.getTransacaoParaTecnico(idChamado);
			request.setAttribute(Constantes.TRANSACOES, transacoes);
			//List<Visita> visitas = facade.getVisitas(idChamado);
			//request.setAttribute(Constantes.VISITAS, visitas);
			Chamado chamado = facade.getChamado(idChamado);
			Usuario usuario = MySession.getInstance().getUsuario();
			ChamadoAtendido chamadoAtendido = facade.getChamadoAtendido(idChamado);
			request.setAttribute(Constantes.CHAMADO, chamado);
			request.setAttribute(Constantes.CHAMADO_ATENDIDO, chamadoAtendido);
			String us = usuario.getIdUsuario();
			String res = chamado.getResponsavel();
			boolean isDono = us.equals(res);
			Unidade unidade = facade.getUnidadeDoTecnico(usuario.getIdUsuario());
			boolean ehDahUnidade = facade.chamadoEhAtendido(idChamado, unidade.getIdUsuario());
			request.setAttribute(Constantes.EH_DAH_UNIDADE, ehDahUnidade);
			request.setAttribute(Constantes.IS_DONO, isDono);
			String gerencia = facade.getGerencia(unidade.getIdUsuario());
			request.setAttribute(Constantes.GERENCIA, gerencia);
			boolean isGerente = facade.isGerente(unidade.getIdUsuario(), usuario.getIdUsuario());
			request.setAttribute(Constantes.IS_GERENTE, isGerente);
			/*
			boolean podeApropriar = chamado.isEmAnalise() && (gerencia.equals(Unidade.APROPRIACAO) || (gerencia.equals(Unidade.MISTA) && !isGerente));
			
			boolean podeApropriarEDelegar = chamado.isEmAnalise() && isGerente && !gerencia.equals(Unidade.APROPRIACAO);*/
			
			boolean podeApropriar = chamado.isEmAnalise() && (!gerencia.equals(Unidade.DELEGACAO) || (
					gerencia.equals(Unidade.DELEGACAO)) && isGerente); 
			boolean podeDelegar = chamado.isEmAnalise() && isGerente && !gerencia.equals(Unidade.APROPRIACAO); 
			
			
			
			
			request.setAttribute(Constantes.PODE_APROPRIAR, podeApropriar);
			request.setAttribute(Constantes.PODE_DELEGAR, podeDelegar);
			
			
			return map.findForward(Constantes.VER_CHAMADO_TECNICO);
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
	}
}
