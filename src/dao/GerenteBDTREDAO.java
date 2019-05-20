package dao;

import integracaoTRE.GerenteBDTRE;
import integracaoTRE.UnidadeBDTRE;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import util.HelpDeskUtil;

import excecoes.HelpDeskException;
/**
 * Classe que representa um Gerente BD TRE DAO
 * @author arthur.farias
 *
 */
public class GerenteBDTREDAO extends AbstractDAO {

	public static String className = "GerenteBDTRE";

	public static GerenteBDTREDAO instance = null;

	public static GerenteBDTREDAO getInstance() {
		if (instance == null) {
			instance = new GerenteBDTREDAO();
		}
		return instance;
	}

	public GerenteBDTREDAO() {
		super();
	}

	protected SessionFactory getSessionFactory() {
		return HelpDeskUtil.getSessionFactoryRemota();
	}

	/**
	 * Insere um GerenteBDTRE no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param gerente O GerenteBDTRE a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(GerenteBDTRE gerente) throws HelpDeskException {
		return super.insert(gerente);
	}

	/**
	 * Modifica um gerente no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(GerenteBDTRE gerente) throws HelpDeskException {
		super.update(gerente);
	}

	/**
	 * Remove no Banco de Dados um determinado gerente.
	 * 
	 * @param gerente O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(GerenteBDTRE gerente) throws HelpDeskException {
		super.delete(gerente);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public GerenteBDTRE read(Serializable id) {
		return (GerenteBDTRE) super.read(GerenteBDTRE.class, id);
	}

	/**
	 * Retorna uma lista de gerentes do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de gerentes com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<GerenteBDTRE> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as gerentes contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List<GerenteBDTRE> getAll() {
		return super.getAll(className);
	}

	public GerenteBDTRE read(UnidadeBDTRE uniTre) throws HelpDeskException {
		List<GerenteBDTRE> lista = getAll();
		for (GerenteBDTRE gerTre : lista) {
			if (gerTre.getIdUnidade().equals(uniTre.getIdUnidade())) {
				return gerTre;
			}
		}
		throw new HelpDeskException("Não existe gerente para esta unidade");

	}
}
