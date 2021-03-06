package sistema;

import config.Config;
import usuario.Unidade;
import usuario.Usuario;
import dao.UsuarioDAO;
import excecoes.HelpDeskException;
import excecoes.UsuarioInexistenteException;

/**
 * Classe que representa um Gerenciador de Usuario
 * @author arthur.farias
 *
 */
public class GerenciadorDeUsuario {

	private static GerenciadorDeUsuario singleton;

	public static GerenciadorDeUsuario getInstance() {
		if (singleton == null)
			singleton = new GerenciadorDeUsuario();
		return singleton;
	}

	private GerenciadorDeUsuario() {
	}

	public boolean isAuthenticatedUser(String idUsuario) throws HelpDeskException {
		if (!existUsuario(idUsuario)) {
			return false;
		} else {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario user = dao.read(idUsuario);
			if (user.isUnidade()) {
				Unidade un = (Unidade) user;
				if (un.isSuporte() && !GerenciadorDeUnidade.getInstance().temGerente(un.getIdUsuario())) {
					throw new HelpDeskException("Erro: Unidade sem gerente");
				}
				return true;
			}
			if (user.isTecnico()
					&& !GerenciadorDeUnidade.getInstance().unidadeDoTecnicotemGerente(user.getIdUsuario())) {
				throw new HelpDeskException("Erro: Unidade de " + user.getIdUsuario() + " sem gerente");
			}
			return true;// administrador ou tecnico com gerente
		}
	}

	public Usuario getUsuario(String idUsuario) throws HelpDeskException {
		if (!isAuthenticatedUser(idUsuario))
			throw new UsuarioInexistenteException();
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.read(idUsuario);
		return user;
	}

	public boolean isAdministrador(String idUsuario) throws HelpDeskException {
		Usuario us = getUsuario(idUsuario);
		return us.isAdministrador();
	}

	public boolean isAuthenticatedAdministrator(String idUsuario, String senha) throws HelpDeskException {
		if (isAdministrador(idUsuario)) {
			String key = GerenciadorConfig.getInstance().getConfig(Config.SENHA_ADMINISTRADOR).getValor();
			return key.equals(senha);
		}
		return false;
	}

	public boolean existUsuario(String idUsuario) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.read(idUsuario);
		return user != null;
	}

}
