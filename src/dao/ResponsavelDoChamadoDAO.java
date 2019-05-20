package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.ResponsavelDoChamado;
import excecoes.HelpDeskException;

/**
 * Classe que representa um ResponsavelChamado DAO
 * @author arthur.farias
 *
 */
public class ResponsavelDoChamadoDAO extends AbstractDAO {

	public static String className = "ResponsavelDoChamadoDAO";

	public static ResponsavelDoChamadoDAO instance = null;

	public static ResponsavelDoChamadoDAO getInstance() {
		if (instance == null) {
			instance = new ResponsavelDoChamadoDAO();
		}
		return instance;
	}

	private ResponsavelDoChamadoDAO() {
		super();
	}

	/**
	 * Insere um ResponsavelDoChamadoDAO no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param ResponsavelDoChamadoDAO O ResponsavelDoChamadoDAO a ser salvo no Banco
	 *                                de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(ResponsavelDoChamado responsavelDoChamado) throws HelpDeskException {
		return super.insert(responsavelDoChamado);
	}

	/**
	 * Modifica um responsavelDoChamadoDAO no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(ResponsavelDoChamado responsavelDoChamado) throws HelpDeskException {
		super.update(responsavelDoChamado);
	}

	/**
	 * Remove no Banco de Dados um determinado responsavelDoChamadoDAO.
	 * 
	 * @param ResponsavelDoChamadoDAO O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(ResponsavelDoChamado responsavelDoChamado) throws HelpDeskException {
		super.delete(responsavelDoChamado);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public ResponsavelDoChamado read(Serializable id) {
		return (ResponsavelDoChamado) super.read(ResponsavelDoChamado.class, id);
	}

	/**
	 * Retorna uma lista de responsavelDoChamadoDAOs do Banco de Dados com as
	 * caracteristicas definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de responsavelDoChamadoDAOs com as caracteristicas
	 *         definidas por queryString.
	 */

	public List<ResponsavelDoChamado> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as responsavelDoChamadoDAOs contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized ResponsavelDoChamado getResponsavelDoChamado(int idChamado) throws HelpDeskException {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(ResponsavelDoChamado.class);

		consulta.add(Expression.like("idChamado", idChamado));

		List<ResponsavelDoChamado> resultado = consulta.list();
		try {
			return resultado.get(0);
		} catch (Exception exception) {
			throw new HelpDeskException();
		}
	}

	public synchronized List<ResponsavelDoChamado> getChamadosAtendidos(String idTecnico) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(ResponsavelDoChamado.class);

		consulta.add(Expression.like("idTecnico", idTecnico));

		List<ResponsavelDoChamado> resultado = consulta.list();
		return resultado;
	}
}
