package dao;

import java.io.Serializable;
import java.util.List;

import bancoDeSolucao.FAQ;
import excecoes.HelpDeskException;
/**
 * Classe que representa uma FAQ Dao
 * @author arthur.farias
 *
 */
public class FaqDAO extends AbstractDAO {

	public static String className = "FAQ";
	public static FaqDAO instance = null;

	public static FaqDAO getInstance() {
		if (instance == null) {
			instance = new FaqDAO();
		}
		return instance;
	}

	private FaqDAO() {
		super();
	}

	/**
	 * Insere um FAQ no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param faq O FAQ a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(FAQ faq) throws HelpDeskException {
		return super.insert(faq);
	}

	/**
	 * Modifica um faq no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(FAQ faq) throws HelpDeskException {
		super.update(faq);
	}

	/**
	 * Remove no Banco de Dados um determinado faq.
	 * 
	 * @param faq O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(FAQ faq) throws HelpDeskException {
		super.delete(faq);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public FAQ read(Serializable id) {
		return (FAQ) super.read(FAQ.class, id);
	}

	/**
	 * Retorna uma lista de faqs do Banco de Dados com as caracteristicas definidas
	 * por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de faqs com as caracteristicas definidas por queryString.
	 */

	public List<FAQ> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as faqs contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

}
