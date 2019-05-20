package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.ResponsavelDoChamado;

import chamado.Chamado;
import excecoes.HelpDeskException;

/**
 * Classe que repressenta um Chamado DAO
 * @author Arthur Farias
 *
 */
public class ChamadoDAO extends AbstractDAO {

	public static String className = "Chamado";

	public static ChamadoDAO instance = null;

	public static ChamadoDAO getInstance() {
		if (instance == null) {
			instance = new ChamadoDAO();
		}
		return instance;
	}

	public ChamadoDAO() {
		super();
	}

	/**
	 * Insere um Chamado no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param chamado O Chamado a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Chamado chamado) throws HelpDeskException {
		return super.insert(chamado);
	}

	/**
	 * Modifica um chamado no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Chamado chamado) throws HelpDeskException {
		super.update(chamado);
	}

	/**
	 * Remove no Banco de Dados um determinado chamado.
	 * 
	 * @param chamado O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Chamado chamado) throws HelpDeskException {
		super.delete(chamado);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Chamado read(Serializable id) {
		return (Chamado) super.read(Chamado.class, id);
	}

	/**
	 * Retorna uma lista de chamados do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de chamados com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Chamado> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as chamados contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List<ResponsavelDoChamado> getChamadosTecnico(String idTecnico) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(ResponsavelDoChamado.class);

		consulta.add(Expression.like("idTecnico", idTecnico));

		List<ResponsavelDoChamado> resultado = consulta.list();
		return resultado;
	}

	public Session openSession() {
		return sessionFactoryLocal.openSession();
	}
}
