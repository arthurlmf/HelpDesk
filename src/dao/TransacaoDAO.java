package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import relacionamento.TransacaoDeChamado;
import transacao.Transacao;
import excecoes.HelpDeskException;

/**
 * Classe que representa uma Transacao DAO
 * @author arthur.farias
 *
 */
public class TransacaoDAO extends AbstractDAO {

	public static String className = "Transacao";

	public static TransacaoDAO instance = null;

	public static TransacaoDAO getInstance() {
		if (instance == null) {
			instance = new TransacaoDAO();
		}
		return instance;
	}

	private TransacaoDAO() {
		super();
	}

	/**
	 * Insere um Transacao no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param transacao O Transacao a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Transacao transacao) throws HelpDeskException {
		return super.insert(transacao);
	}

	/**
	 * Modifica um transacao no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Transacao transacao) throws HelpDeskException {
		super.update(transacao);
	}

	/**
	 * Remove no Banco de Dados um determinado transacao.
	 * 
	 * @param transacao O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Transacao transacao) throws HelpDeskException {
		super.delete(transacao);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Transacao read(Serializable id) {
		return (Transacao) super.read(Transacao.class, id);
	}

	/**
	 * Retorna uma lista de transacaos do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de transacaos com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Transacao> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as transacaos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List<Transacao> getTransacaoParaTecnico(int idChamado) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Transacao.class);
		consulta.add(Expression.like("idChamado", idChamado));
		consulta.addOrder(Order.desc("data"));
		return consulta.list();
	}

	public synchronized Transacao getTransacaoFecharDeChamado(int idChamado) {
		Session session = new ChamadoDAO().openSession();
		String queryCompleta = "Select t from Transacao t, TransacaoDeChamado tc " + "where tc.idChamado = " + idChamado
				+ " AND t.idTransacao = tc.idTransacao " + "AND t.tipo like 'fechado'";
		Session sess = openSession();
		Query query = session.createQuery(queryCompleta);
		List list = query.list();
		session.flush();
		session.close();
		if (list != null && list.size() > 0) {
			return (Transacao) list.get(0);
		}
		return null;
	}

}
