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
import usuario.Usuario;
import util.Util;
import chamado.Chamado;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
import facade.FacadeHelpDesk;
/**
 * Chamados solicitados por uma unidade
 * @author almfarias
 *
 */
public class ListarChamadosRepassadosUnidadeAction extends Action {
	
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();
		try{
			Usuario us = MySession.getInstance().getUsuario();
			Unidade unidade = null;
			Tecnico tecnico = null;
			if(us.isUnidade()){
				unidade = (Unidade) us;
			}else if(us.isTecnico()){
				unidade = facade.getUnidadeDoTecnico(us.getIdUsuario());
				tecnico = (Tecnico) us;
			}
			
			
			//chamados solicitados por esta unidade
			List<Chamado> chamadosRepassados = facade.getChamadosRepassados(unidade.getIdUsuario());
			request.setAttribute(Constantes.UNIDADE,  Util.capitular(unidade.toString()));
			request.setAttribute(Constantes.QUANT_CHAMADOS_RESULT, chamadosRepassados.size());
			request.setAttribute(Constantes.CHAMADOS, chamadosRepassados);
			boolean podeVer =  ((us.isTecnico() && tecnico.isGerente()) || !unidade.isGerenciaDelegacao()); //unidade.isGerenciaDelegacao() && (tecnico == null? false:tecnico.isGerente());
			
			request.setAttribute(Constantes.PODE_VER_CHAMADOS_REPASSADOS,podeVer);
			request.setAttribute(Constantes.PODE_VER_CHAMADOS_REPASSADOS,podeVer);
			if(unidade.isSuporte()){
				return map.findForward(Constantes.UNIDADE_SUPORTE);
			}
			return null;
		} catch (SessaoFinalizadaException e) {
			return map.findForward(Constantes.INDEX);
		}catch(HelpDeskException e ){			
			return LoginAction.redirecionarErro(map,request,e.getMessage());
		}
		
		
	}
}

