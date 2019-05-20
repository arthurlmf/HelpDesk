package struts;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import config.Config;

import usuario.Usuario;
import excecoes.HelpDeskException;
import facade.FacadeHelpDesk;

public class LoginForm extends ActionForm {
	
	private String idUsuario;
	private String senha;
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) throws HelpDeskException {
		String dominio = "";
		try {
			dominio = FacadeHelpDesk.getInstance().getConfig(Config.DOMAIN).getValor();
		} catch (NullPointerException e) {
			dominio = "@tre.br";
		}
		if(!idUsuario.contains(dominio)){
			this.idUsuario = idUsuario+dominio;
		}else{
			this.idUsuario = idUsuario;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() throws HelpDeskException{
		FacadeHelpDesk facade = FacadeHelpDesk.getInstance();		
		return facade.getUsuario(idUsuario);
	}	
	
	
	@Override
	public void reset(ActionMapping map, HttpServletRequest req){
		//this.usuario = new Usuario();
	}
}
