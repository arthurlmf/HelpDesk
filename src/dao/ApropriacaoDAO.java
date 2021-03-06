package dao;

import java.io.Serializable;
import java.util.List;
import excecoes.HelpDeskException;
import transacao.Apropriacao;

/**
 * Classe que representa uma apropriacao
 * 
 * @author Arthur Farias
 *
 */
public class ApropriacaoDAO extends AbstractDAO {

	public static String className = "Apropriacao";

	public static ApropriacaoDAO instance = null;

	public static ApropriacaoDAO getInstance() {
		if (instance == null) {
			instance = new ApropriacaoDAO();
		}
		return instance;
	}

	private ApropriacaoDAO() {
		super();
	}

	/**
	 * Insere um Apropriacao no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param apropriacao O Apropriacao a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Apropriacao apropriacao) throws HelpDeskException {
		return super.insert(apropriacao);
	}

	/**
	 * Modifica um apropriacao no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(Apropriacao apropriacao) throws HelpDeskException {
		super.update(apropriacao);
	}

	/**
	 * Remove no Banco de Dados um determinado apropriacao.
	 * 
	 * @param apropriacao O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Apropriacao apropriacao) throws HelpDeskException {
		super.delete(apropriacao);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Apropriacao read(Serializable id) {
		return (Apropriacao) super.read(Apropriacao.class, id);
	}

	/**
	 * Retorna uma lista de apropriacaos do Banco de Dados com as caracteristicas
	 * definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de apropriacaos com as caracteristicas definidas por
	 *         queryString.
	 */
	
	public List<Apropriacao> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as apropriacaos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}
}
