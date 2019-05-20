package dao;

import java.io.Serializable;
import java.util.List;

import usuario.Tecnico;
import excecoes.HelpDeskException;

/**
 * Classe que representa um TecnicoDAO
 * 
 * @author arthur.farias
 *
 */
public class TecnicoDAO extends AbstractDAO {

	public static String className = "Tecnico";
	public static TecnicoDAO instance = null;

	public static TecnicoDAO getInstance() {
		if (instance == null) {
			instance = new TecnicoDAO();
		}
		return instance;
	}

	public TecnicoDAO() {
		super();
	}

	/**
	 * Insere um Tecnico no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param tecnico O Tecnico a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Tecnico tecnico) throws HelpDeskException {
		return super.insert(tecnico);
	}

	/**
	 * Modifica um tecnico no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Tecnico tecnico) throws HelpDeskException {
		super.update(tecnico);
	}

	/**
	 * Remove no Banco de Dados um determinado tecnico.
	 * 
	 * @param tecnico O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Tecnico tecnico) throws HelpDeskException {
		super.delete(tecnico);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Tecnico read(Serializable id) {
		return (Tecnico) super.read(Tecnico.class, id);
	}

	/**
	 * Retorna uma lista de tecnicos do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de tecnicos com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<Tecnico> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as tecnicos contidos no Banco de Dados.
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
