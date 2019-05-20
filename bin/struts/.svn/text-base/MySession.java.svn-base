package struts;

import javax.servlet.http.HttpSession;

import usuario.Usuario;
import excecoes.SessaoFinalizadaException;

public class MySession {

	private static MySession singleton;
	
	private HttpSession session;
	
	private String dominio;
	
	//private ControladorPermissoes controlador;
	


	public static MySession getInstance() {
		if (singleton == null)
			singleton = new MySession();
		return singleton;
	}

	

	public MySession() {
		super();
		// this.controlador = ControladorPermissoes.getInstance();
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Usuario getUsuario() throws SessaoFinalizadaException {
		try {
			Usuario usuario = (Usuario) getSession().getAttribute(
					Constantes.USUARIO);
			if (usuario == null) {
				throw new SessaoFinalizadaException("Sessao finalizada");
			}
			return usuario;
		} catch (Exception e) {
			throw new SessaoFinalizadaException("Sessao finalizada");
		}

	}
	
	public String getDominio() {
		return dominio;
	}



	public void setDominio(String dominio) {
		this.dominio = dominio;
		getSession().setAttribute(Constantes.DOMAIN,dominio);
	}
	
//	public ControladorPermissoes getControlador() {
//		return controlador;
//	}
//
//
//
//	public void setControlador(ControladorPermissoes controlador) {
//		this.controlador = controlador;
//	}


}
