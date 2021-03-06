package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.TransacaoDeChamado;
import excecoes.HelpDeskException;
/**
 * Classe que representa uma TransacaoDeChamado dao
 * @author arthur.farias
 *
 */
public class TransacaoDeChamadoDAO extends AbstractDAO {

	public static String className = "TransacaoDeChamado";

	public static TransacaoDeChamadoDAO instance = null;

	public static TransacaoDeChamadoDAO getInstance() {
		if (instance == null) {
			instance = new TransacaoDeChamadoDAO();
		}
		return instance;
	}

	private TransacaoDeChamadoDAO() {
		super();
	}

	/**
	 * Insere um TransacaoDeChamado no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param transacaoDeChamado O TransacaoDeChamado a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(TransacaoDeChamado transacaoDeChamado) throws HelpDeskException {
		return super.insert(transacaoDeChamado);
	}

	/**
	 * Modifica um transacaoDeChamado no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(TransacaoDeChamado transacaoDeChamado) throws HelpDeskException {
		super.update(transacaoDeChamado);
	}

	/**
	 * Remove no Banco de Dados um determinado transacaoDeChamado.
	 * 
	 * @param transacaoDeChamado O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(TransacaoDeChamado transacaoDeChamado) throws HelpDeskException {
		super.delete(transacaoDeChamado);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public TransacaoDeChamado read(Serializable id) {
		return (TransacaoDeChamado) super.read(TransacaoDeChamado.class, id);
	}

	/**
	 * Retorna uma lista de transacaoDeChamados do Banco de Dados com as
	 * caracteristicas definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de transacaoDeChamados com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<TransacaoDeChamado> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as transacaoDeChamados contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List<TransacaoDeChamado> getTransacaoDeChamado(int idChamado) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(TransacaoDeChamado.class);
		consulta.add(Expression.like("idChamado", idChamado));
		return consulta.list();
	}
}
