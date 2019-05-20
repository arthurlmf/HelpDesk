package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import transacao.InterEncaminhamento;
import excecoes.HelpDeskException;
/**
 * Classe que representa um InterEncaminhamento
 * @author arthur.farias
 *
 */
public class InterEncaminhamentoDAO extends AbstractDAO {

	public static String className = "InterEncaminhamento";
	public static InterEncaminhamentoDAO instance = null;

	public static InterEncaminhamentoDAO getInstance() {
		if (instance == null) {
			instance = new InterEncaminhamentoDAO();
		}
		return instance;
	}

	private InterEncaminhamentoDAO() {
		super();
	}

	/**
	 * Insere um InterEncaminhamento no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param interEncaminhamento O InterEncaminhamento a ser salvo no Banco de
	 *                            Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(InterEncaminhamento interEncaminhamento) throws HelpDeskException {
		return super.insert(interEncaminhamento);
	}

	/**
	 * Modifica um interEncaminhamento no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(InterEncaminhamento interEncaminhamento) throws HelpDeskException {
		super.update(interEncaminhamento);
	}

	/**
	 * Remove no Banco de Dados um determinado interEncaminhamento.
	 * 
	 * @param interEncaminhamento O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(InterEncaminhamento interEncaminhamento) throws HelpDeskException {
		super.delete(interEncaminhamento);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public InterEncaminhamento read(Serializable id) {
		return (InterEncaminhamento) super.read(InterEncaminhamento.class, id);
	}

	/**
	 * Retorna uma lista de interEncaminhamentos do Banco de Dados com as
	 * caracteristicas definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de interEncaminhamentos com as caracteristicas definidas
	 *         por queryString.
	 */

	public List<InterEncaminhamento> getList(String queryString) {
		return super.getList(queryString);
	}

	public synchronized InterEncaminhamento getRequisicaoDeInterEncaminhamento(int idChamado) {
		Session session = new ChamadoDAO().openSession();
		String queryCompleta = "Select i from InterEncaminhamento i, TransacaoDeChamado t where t.idTransacao = i.idTransacao "
				+ "AND t.idChamado like '" + idChamado + "' AND i.visivel = " + false;

		Query query = session.createQuery(queryCompleta);
		List list = query.list();
		session.flush();
		session.close();
		return (InterEncaminhamento) list.get(0);
	}

	/**
	 * Remove todos as interEncaminhamentos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
