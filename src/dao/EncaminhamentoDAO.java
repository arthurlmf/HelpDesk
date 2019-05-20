package dao;

import java.io.Serializable;
import java.util.List;

import transacao.Encaminhamento;

import excecoes.HelpDeskException;
/**
 * Classe que representa um encaminhameno Dao
 * @author arthur.farias
 *
 */
public class EncaminhamentoDAO extends AbstractDAO {

	public static String className = "Encaminhamento";
	public static EncaminhamentoDAO instance = null;

	public static EncaminhamentoDAO getInstance() {
		if (instance == null) {
			instance = new EncaminhamentoDAO();
		}
		return instance;
	}

	private EncaminhamentoDAO() {
		super();
	}

	/**
	 * Insere um Encaminhamento no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param encaminhamento O Encaminhamento a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Encaminhamento encaminhamento) throws HelpDeskException {
		return super.insert(encaminhamento);
	}

	/**
	 * Modifica um encaminhamento no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Encaminhamento encaminhamento) throws HelpDeskException {
		super.update(encaminhamento);
	}

	/**
	 * Remove no Banco de Dados um determinado encaminhamento.
	 * 
	 * @param encaminhamento O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Encaminhamento encaminhamento) throws HelpDeskException {
		super.delete(encaminhamento);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Encaminhamento read(Serializable id) {
		return (Encaminhamento) super.read(Encaminhamento.class, id);
	}

	/**
	 * Retorna uma lista de encaminhamentos do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de encaminhamentos com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Encaminhamento> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as encaminhamentos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
