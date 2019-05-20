package dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import usuario.Unidade;
import util.HelpDeskUtil;
import excecoes.HelpDeskException;

/**
 * Classe que representa uma Unidade DAO
 * @author arthur.farias
 *
 */
public class UnidadeDAO extends AbstractDAO {

	public static String className = "Unidade";

	public static UnidadeDAO instance = null;

	public static UnidadeDAO getInstance() {
		if (instance == null) {
			instance = new UnidadeDAO();
		}
		return instance;
	}

	public UnidadeDAO() {
		super();
	}

	/**
	 * Insere um Unidade no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param unidade O Unidade a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Unidade unidade) throws HelpDeskException {
		return super.insert(unidade);
	}

	/**
	 * Modifica um unidade no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Unidade unidade) throws HelpDeskException {
		super.update(unidade);
	}

	/**
	 * Remove no Banco de Dados um determinado unidade.
	 * 
	 * @param unidade O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Unidade unidade) throws HelpDeskException {
		super.delete(unidade);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Unidade read(Serializable id) {
		return (Unidade) super.read(Unidade.class, id);
	}

	/**
	 * Retorna uma lista de unidades do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de unidades com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Unidade> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as unidades contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List getAll() {
		return super.getAll(className);
	}

	public synchronized boolean existeUnidade(String idUnidade) {
		Unidade unidade = read(idUnidade);
		return unidade != null;
	}

	public synchronized List<Unidade> getAllSuporte() {
		List<Unidade> lista = getAll();
		LinkedList<Unidade> out = new LinkedList<Unidade>();
		for (Unidade un : lista) {
			if (un.isSuporte()) {
				out.add(un);
			}
		}
		return out;
	}

}
