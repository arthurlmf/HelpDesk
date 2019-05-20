package struts;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import usuario.Usuario;
import config.Config;
import excecoes.HelpDeskException;
import excecoes.UsuarioInexistenteException;
import facade.FacadeHelpDesk;

public class LoginAction extends Action {
	
	private FacadeHelpDesk facade = FacadeHelpDesk.getInstance();

	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		LoginForm formulario = (LoginForm) form;
		try{			
			if (facade.isAuthenticatedUser(formulario.getIdUsuario(),formulario.getSenha())) {//tem q passar a senha tambem
				//pega o Usuario e o coloca como atributo da sessao
				Usuario usuario = formulario.getUsuario();				
				request.getSession().setAttribute(Constantes.USUARIO,usuario);
				
				//
				String dominio = facade.getConfig(Config.DOMAIN).getValor();
				
				MySession.getInstance().setSession(request.getSession());
				MySession.getInstance().setDominio(dominio);
				//MySession.getInstance().getControlador().setUsuario(usuario);
				
				if(usuario.isTecnico()){					
					return map.findForward(Constantes.PG_INDEX_TECNICO);
				}else if (usuario.isUnidade()){
					return map.findForward(Constantes.INDEX_UNIDADE);
				} else {
					return map.findForward(Constantes.PG_INDEX_ADMINISTRADOR);
				}
			}			
		}catch(UsuarioInexistenteException e ){			
			return erroLogin(map,request,"Usuario inválido");
		}catch(NullPointerException e ){			
			return map.findForward(Constantes.INDEX);
		}catch(Exception e ){			
			return redirecionarErro(map,request,e.getMessage());
		}
		return erroLogin(map,request,"Usuario inválido");
		
	}
	
	public static ActionForward erroLogin(ActionMapping map, HttpServletRequest request,String mensagem) {
		request.setAttribute(Constantes.MSG_AUTENTICACAO, mensagem);
		return map.findForward(Constantes.INDEX);//msgAutenticacao
	}

	public static ActionForward redirecionarErro(ActionMapping map, HttpServletRequest request,String mensagem) {
		request.setAttribute(Constantes.MENSAGEM, mensagem);
		return map.findForward(Constantes.PG_ERRO);
	}

}
