package dao;

import java.io.Serializable;
import java.util.List;

import config.Config;
import excecoes.HelpDeskException;

/**
 * Classe que representa o config DAO
 * @author Arthur Farias
 *
 */
public class ConfigDAO extends AbstractDAO {

	public static String className = "Config";
	public static ConfigDAO instance = null;

	public static ConfigDAO getInstance() {
		if (instance == null) {
			instance = new ConfigDAO();
		}
		return instance;
	}

	private ConfigDAO() {
		super();
	}

	/**
	 * Insere um Config no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param faq O Config a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Config faq) throws HelpDeskException {
		return super.insert(faq);
	}

	/**
	 * Modifica um faq no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Config faq) throws HelpDeskException {
		super.update(faq);
	}

	/**
	 * Remove no Banco de Dados um determinado faq.
	 * 
	 * @param faq O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Config faq) throws HelpDeskException {
		super.delete(faq);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Config read(Serializable id) {
		return (Config) super.read(Config.class, id);
	}

	/**
	 * Retorna uma lista de faqs do Banco de Dados com as caracteristicas definidas
	 * por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de faqs com as caracteristicas definidas por queryString.
	 */

	public List<Config> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as configs contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public List<Config> getAll() {
		return super.getAll(className);
	}

}
