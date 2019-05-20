package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.ResponsavelDoChamado;
import tipo.PrimaryKeyTipo;
import tipo.Tipo;
import excecoes.HelpDeskException;

/**
 * Classe que representa um Tipo DAO
 * @author arthur.farias
 *
 */
public class TipoDAO extends AbstractDAO {

	public static String className = "Tipo";

	public static TipoDAO instance = null;

	public static TipoDAO getInstance() {
		if (instance == null) {
			instance = new TipoDAO();
		}
		return instance;
	}

	private TipoDAO() {
		super();
	}

	/**
	 * Insere um Tipo no Banco de Dados um objeto na tabela correspondente.
	 * 
	 * @param tipo O Tipo a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(Tipo tipo) throws HelpDeskException {
		return super.insert(tipo);
	}

	/**
	 * Modifica um tipo no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public synchronized void update(Tipo tipo, String novoNomeTipo) throws HelpDeskException {
		Session sess = openSession();
		// String queryCompleta = "update tipo.Tipo t set t.chaveTipo.nomeTipo =
		// 'Hidraulico' where t.chaveTipo.nomeTipo = 'Mecanico'";
		String conditionQuery = "chaveTipo.idUnidade" + " = '" + tipo.getIdUnidade() + "' and " + "chaveTipo.nomeTipo"
				+ " = '" + tipo.getNomeTipo() + "'";
		String updateString = "chaveTipo.nomeTipo" + " = '" + novoNomeTipo + "'";
		super.uptadeQuery(updateString, conditionQuery, className);
	}

	/**
	 * Remove no Banco de Dados um determinado tipo.
	 * 
	 * @param tipo O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(Tipo tipo) throws HelpDeskException {
		super.delete(tipo);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public Tipo read(Serializable id) {
		return (Tipo) super.read(Tipo.class, id);
	}

	/**
	 * Retorna uma lista de tipos do Banco de Dados com as caracteristicas definidas
	 * por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de tipos com as caracteristicas definidas por queryString.
	 */

	public List<Tipo> getList(String queryString) {
		return super.getList(queryString);
	}

	public synchronized boolean existeTipoDaUnidade(String idUnidade, String tipo) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Tipo.class);
		PrimaryKeyTipo pkt = new PrimaryKeyTipo(idUnidade, tipo);
		consulta.add(Expression.like("chaveTipo", pkt));
		List<ResponsavelDoChamado> resultado = consulta.list();
		return resultado.size() != 0;
	}

	public synchronized boolean existeTipo(String tipo) {
		List<Tipo> lista = getAll();
		for (Tipo tp : lista) {
			if (tp.getNomeTipo().equalsIgnoreCase(tipo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recupera uma lista de tipos da unidade em ordem alfabética
	 * 
	 * @param idUnidade
	 * @return
	 */
	public synchronized List<Tipo> getTiposUnidade(String idUnidade) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(Tipo.class);
		String conditionQuery = "chaveTipo.idUnidade" + " like '" + idUnidade + "'" + " order by "
				+ "chaveTipo.nomeTipo " + "asc";
		String queryCompleta = "from " + className + " objeto where " + conditionQuery;
		List<Tipo> resultado = getList(queryCompleta);
		return resultado;
	}

	/**
	 * Remove todos as tipos contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public synchronized List<Tipo> getAll() {
		return super.getAll(className);
	}
}
