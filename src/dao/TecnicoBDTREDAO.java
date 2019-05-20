package dao;

import integracaoTRE.TecnicoBDTRE;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import util.HelpDeskUtil;

import excecoes.HelpDeskException;
/**
 * Classe que representa um Tecnico BD TRE
 * @author arthur.farias
 *
 */
public class TecnicoBDTREDAO extends AbstractDAO {

	public static String className = "TecnicoBDTRE";

	public static TecnicoBDTREDAO instance = null;

	public static TecnicoBDTREDAO getInstance() {
		if (instance == null) {
			instance = new TecnicoBDTREDAO();
		}
		return instance;
	}

	public TecnicoBDTREDAO() {
		super();
	}

	protected SessionFactory getSessionFactory() {
		return HelpDeskUtil.getSessionFactoryRemota();
	}

	/**
	 * Insere um TecnicoBDTRE no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param tecnicoBdTre O TecnicoBDTRE a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(TecnicoBDTRE tecnicoBdTre) throws HelpDeskException {
		return super.insert(tecnicoBdTre);
	}

	/**
	 * Modifica um tecnicoBdTre no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(TecnicoBDTRE tecnicoBdTre) throws HelpDeskException {
		super.update(tecnicoBdTre);
	}

	/**
	 * Remove no Banco de Dados um determinado tecnicoBdTre.
	 * 
	 * @param tecnicoBdTre O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(TecnicoBDTRE tecnicoBdTre) throws HelpDeskException {
		super.delete(tecnicoBdTre);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public TecnicoBDTRE read(Serializable id) {
		return (TecnicoBDTRE) super.read(TecnicoBDTRE.class, id);
	}

	/**
	 * Retorna uma lista de tecnicoBdTres do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de tecnicoBdTres com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<TecnicoBDTRE> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as tecnicoBdTres contidos no Banco de Dados.
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
