package dao;

import integracaoTRE.UnidadeBDTRE;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import util.HelpDeskUtil;
import excecoes.HelpDeskException;
/**
 * Classe que representa uma Unidade BD TRE Dao
 * @author arthur.farias
 *
 */
public class UnidadeBDTREDAO extends AbstractDAO {

	public static String className = "UnidadeBDTRE";

	public static UnidadeBDTREDAO instance = null;

	public static UnidadeBDTREDAO getInstance() {
		if (instance == null) {
			instance = new UnidadeBDTREDAO();
		}
		return instance;
	}

	public UnidadeBDTREDAO() {
		super();
	}

	protected SessionFactory getSessionFactory() {
		return HelpDeskUtil.getSessionFactoryRemota();
	}

	/**
	 * Insere um UnidadeBDTRE no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param unidadeBdTre O UnidadeBDTRE a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(UnidadeBDTRE unidadeBdTre) throws HelpDeskException {
		return super.insert(unidadeBdTre);
	}

	/**
	 * Modifica um unidadeBdTre no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(UnidadeBDTRE unidadeBdTre) throws HelpDeskException {
		super.update(unidadeBdTre);
	}

	/**
	 * Remove no Banco de Dados um determinado unidadeBdTre.
	 * 
	 * @param unidadeBdTre O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(UnidadeBDTRE unidadeBdTre) throws HelpDeskException {
		super.delete(unidadeBdTre);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public UnidadeBDTRE read(Serializable id) {
		return (UnidadeBDTRE) super.read(UnidadeBDTRE.class, id);
	}

	/**
	 * Retorna uma lista de unidadeBdTres do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de unidadeBdTres com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<UnidadeBDTRE> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as unidadeBdTres contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List getAll() {
		return super.getAll(className);
	}
}
