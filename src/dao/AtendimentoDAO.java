package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import relacionamento.Atendimento;
import excecoes.HelpDeskException;

/**
 * Classe que representa um Atendimento DAO
 * @author Arthur Farias
 *
 */
public class AtendimentoDAO extends AbstractDAO {

	public static String className = "Atendimento";

	public static AtendimentoDAO instance = null;

	public static AtendimentoDAO getInstance() {
		if (instance == null) {
			instance = new AtendimentoDAO();
		}
		return instance;
	}

	private AtendimentoDAO() {
		super();
	}

	/**
	 * Insere um Atendimento no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param atendimento O Atendimento a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Atendimento atendimento) throws HelpDeskException {
		return super.insert(atendimento);
	}

	/**
	 * Modifica um atendimento no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Atendimento atendimento) throws HelpDeskException {
		super.update(atendimento);
	}

	/**
	 * Remove no Banco de Dados um determinado atendimento.
	 * 
	 * @param atendimento O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Atendimento atendimento) throws HelpDeskException {
		super.delete(atendimento);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Atendimento read(Serializable id) {
		return (Atendimento) super.read(Atendimento.class, id);
	}

	/**
	 * Retorna uma lista de atendimentos do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de atendimentos com as caracteristicas definidas por
	 *         queryString.
	 */
	
	public List<Atendimento> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as atendimentos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized Atendimento read(String idUnidadeSolicitante, String idUnidadeSuporte) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Atendimento.class);

		consulta.add(Expression.like("idUnidadeSolicitante", idUnidadeSolicitante));
		consulta.add(Expression.like("idUnidadeSuporte", idUnidadeSuporte));

		List<Atendimento> resultado = consulta.list();
		if (resultado.isEmpty()) {
			return null;
		}
		return resultado.get(0);
	}

	public synchronized List<Atendimento> getSuportes(String idUnidadeSolicitante) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Atendimento.class);

		consulta.add(Expression.like("idUnidadeSolicitante", idUnidadeSolicitante));

		List<Atendimento> resultado = consulta.list();
		return resultado;
	}

	public synchronized List<Atendimento> getSolicitantes(String idUnidadeSuporte) {

		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Atendimento.class);

		consulta.add(Expression.like("idUnidadeSuporte", idUnidadeSuporte));

		List<Atendimento> resultado = consulta.list();
		return resultado;
	}

	public synchronized List<Atendimento> getAtendimento(String idUnidadeSuporte) {

		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Atendimento.class);

		consulta.add(Expression.like("idUnidadeSuporte", idUnidadeSuporte));

		List<Atendimento> resultado = consulta.list();
		return resultado;
	}

}
