package dao;

import integracaoTRE.GerenteBDTRE;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import relacionamento.ChamadosRepassados;
import relacionamento.ResponsavelDoChamado;
import excecoes.HelpDeskException;
/**
 * Classe que representa os Chamados RepassadosDAO
 * @author arthur.farias
 *
 */
public class ChamadosRepassadosDAO extends AbstractDAO {

	public static String className = "ChamadosRepassados";

	public static ChamadosRepassadosDAO instance = null;

	public static ChamadosRepassadosDAO getInstance() {
		if (instance == null) {
			instance = new ChamadosRepassadosDAO();
		}
		return instance;
	}

	public ChamadosRepassadosDAO() {
		super();
	}

	/**
	 * Insere um ChamadosRepassados no Banco de Dados um objeto na tabela
	 * correspondente.
	 * 
	 * @param chamadosRepassados O ChamadosRepassados a ser salvo no Banco de Dados
	 * @return O id do objeto criado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Serializable insert(ChamadosRepassados chamadosRepassados) throws HelpDeskException {
		return super.insert(chamadosRepassados);
	}

	/**
	 * Modifica um chamadosRepassados no Banco de Dados.
	 * 
	 * @param obj O objeto a ser modificado no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void update(ChamadosRepassados chamadosRepassados) throws HelpDeskException {
		super.update(chamadosRepassados);
	}

	/**
	 * Remove no Banco de Dados um determinado chamadosRepassados.
	 * 
	 * @param chamadosRepassados O objeto a ser removido no Banco de Dados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void delete(ChamadosRepassados chamadosRepassados) throws HelpDeskException {
		super.delete(chamadosRepassados);
	}

	/**
	 * Metodo que le um objeto do Banco de Dados a partir de um id.
	 * 
	 * @param id     O id do objeto.
	 * @param classe A classe que o objeto pertence.
	 * @return O objeto lido do Banco de Dados.
	 */

	public ChamadosRepassados read(Serializable id) {
		return (ChamadosRepassados) super.read(ChamadosRepassados.class, id);
	}

	/**
	 * Retorna uma lista de chamadosRepassadoss do Banco de Dados com as
	 * caracteristicas definidas por queryString.
	 * 
	 * @param queryString A string da busca.
	 * @return Uma lista de chamadosRepassadoss com as caracteristicas definidas por
	 *         queryString.
	 */

	public List<ChamadosRepassados> getList(String queryString) {
		return super.getList(queryString);
	}

	/**
	 * Remove todos as chamadosRepassadoss contidos no Banco de Dados.
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void removeAll() throws HelpDeskException {
		super.removeAll(className);
	}

	public Session openSession() {
		return sessionFactoryLocal.openSession();
	}

	public synchronized List<ChamadosRepassados> getAll() {
		return super.getAll(className);
	}

	public synchronized List<ChamadosRepassados> getChamadosQuePassaramPelaUnidade(String idUnidade) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(ChamadosRepassados.class);

		consulta.add(Expression.like("idUsuario", idUnidade));

		List<ChamadosRepassados> resultado = consulta.list();
		return resultado;
	}

	public synchronized List<ChamadosRepassados> getChamadosQuePassaramPelaUnidadeEPeloChamado(int idChamado,
			String idUnidade) {
		Session sess = openSession();
		Criteria consulta = sess.createCriteria(ChamadosRepassados.class);

		consulta.add(Expression.like("idUsuario", idUnidade));
		consulta.add(Expression.like("idChamado", idChamado));

		List<ChamadosRepassados> resultado = consulta.list();
		return resultado;
	}

	public boolean exist(int idChamado, String idUnidadeOrigem) {
		List<ChamadosRepassados> resultado = getChamadosQuePassaramPelaUnidadeEPeloChamado(idChamado, idUnidadeOrigem);
		return !resultado.isEmpty();
	}
}
