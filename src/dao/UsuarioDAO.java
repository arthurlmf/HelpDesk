package dao;

import java.io.Serializable;
import java.util.List;

import usuario.Usuario;
import excecoes.HelpDeskException;

/**
 * Classe que representa um Usuario DAO
 * @author arthur.farias
 *
 */
public class UsuarioDAO extends AbstractDAO {

	public static String className = "Usuario";

	public static UsuarioDAO instance = null;

	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}

	public UsuarioDAO() {
		super();
	}

	/**
	 * Insere um Usuario no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param usuario O Usuario a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Usuario usuario) throws HelpDeskException {
		return super.insert(usuario);
	}

	/**
	 * Modifica um usuario no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Usuario usuario) throws HelpDeskException {
		super.update(usuario);
	}

	/**
	 * Remove no Banco de Dados um determinado usuario.
	 * 
	 * @param usuario O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Usuario usuario) throws HelpDeskException {
		super.delete(usuario);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Usuario read(Serializable id) {
		return (Usuario) super.read(Usuario.class, id);
	}

	/**
	 * Retorna uma lista de usuarios do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de usuarios com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Usuario> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as usuarios contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
