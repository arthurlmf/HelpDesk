package dao;

import java.io.Serializable;
import java.util.List;

import transacao.Apropriacao;
import transacao.Fechar;
import excecoes.HelpDeskException;
/**
 * Classe que representa um Fechar DAO
 * @author arthur.farias
 *
 */
public class FecharDAO extends AbstractDAO {

	public static String className = "Fechar";

	public static FecharDAO instance = null;

	public static FecharDAO getInstance() {
		if (instance == null) {
			instance = new FecharDAO();
		}
		return instance;
	}

	private FecharDAO() {
		super();
	}

	/**
	 * Insere um Fechar no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param fechar O Fechar a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Fechar fechar) throws HelpDeskException {
		return super.insert(fechar);
	}

	/**
	 * Modifica um fechar no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Fechar fechar) throws HelpDeskException {
		super.update(fechar);
	}

	/**
	 * Remove no Banco de Dados um determinado fechar.
	 * 
	 * @param fechar O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Fechar fechar) throws HelpDeskException {
		super.delete(fechar);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Fechar read(Serializable id) {
		return (Fechar) super.read(Fechar.class, id);
	}

	/**
	 * Retorna uma lista de fechars do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de fechars com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Fechar> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as fechars contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
