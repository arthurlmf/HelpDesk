package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.GerenteDeUnidade;
import excecoes.HelpDeskException;
/**
 * Classe que representa Gerente da Unidade Dao
 * 
 * @author arthur.farias
 *
 */
public class GerenteDeUnidadeDAO extends AbstractDAO {

	public static String className = "GerenteDeUnidade";
	public static GerenteDeUnidadeDAO instance = null;

	public static GerenteDeUnidadeDAO getInstance() {
		if (instance == null) {
			instance = new GerenteDeUnidadeDAO();
		}
		return instance;
	}

	private GerenteDeUnidadeDAO() {
		super();
	}

	/**
	 * Insere um GerenteDeUnidade no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param gerenteDeUnidade O GerenteDeUnidade a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(GerenteDeUnidade gerenteDeUnidade) throws HelpDeskException {
		return super.insert(gerenteDeUnidade);
	}

	/**
	 * Modifica um gerenteDeUnidade no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(GerenteDeUnidade gerenteDeUnidade) throws HelpDeskException {
		super.update(gerenteDeUnidade);
	}

	/**
	 * Remove no Banco de Dados um determinado gerenteDeUnidade.
	 * 
	 * @param gerenteDeUnidade O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(GerenteDeUnidade gerenteDeUnidade) throws HelpDeskException {
		super.delete(gerenteDeUnidade);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public GerenteDeUnidade read(Serializable id) {
		return (GerenteDeUnidade) super.read(GerenteDeUnidade.class, id);
	}

	/**
	 * Retorna uma lista de gerenteDeUnidades do Banco de Dados com as
	 * caracteristicas definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de gerenteDeUnidades com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<GerenteDeUnidade> getList(String queryString) {
		return super.getList(queryString);
	}

	public synchronized GerenteDeUnidade getGerente(String unidade) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(GerenteDeUnidade.class);

		consulta.add(Expression.like("idUnidade", unidade));

		List<GerenteDeUnidade> resultado = consulta.list();
		return resultado.get(0);
	}

	/**
	 * Remove todos as gerenteDeUnidades contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
