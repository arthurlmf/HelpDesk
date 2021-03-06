package pesquisa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HelpDeskUtil;
import chamado.Chamado;
import dao.ChamadoDAO;
/**
 * Classe que representa uma Pesquisa Chamado
 * @author arthur.farias
 *
 */
public class PesquisaChamado {

	private String unidadeSolicitante;

	private String unidadeAtendente;

	private String tipo;

	private String subtipo;

	private String estado;

	private String patrimonio;

	public PesquisaChamado() {

	}

	public PesquisaChamado(String idSolicitante, String idAtendente, String tipo, String subtipo, String estado,
			String patrimonio) {
		setUnidadeSolicitante(idSolicitante);
		setUnidadeAtendente(idAtendente);
		setTipo(tipo);
		setSubtipo(subtipo);
		setEstado(estado);
		setPatrimonio(patrimonio);
	}

	public synchronized List<Chamado> pesquisaChamado() {
		Session session = new ChamadoDAO().openSession();
		/*
		 * String queryCompleta =
		 * "Select c from Chamado c, ChamadoAtendido ch where c.idChamado = ch.idChamado "
		 * + "AND ch.unidadeSolicitante like '" + unidadeSolicitante +
		 * "' AND ch.unidadeAtendente like '" + unidadeAtendente + "'";
		 */
		String queryCompleta = "Select c from Chamado c, ChamadoAtendido ch where c.idChamado = ch.idChamado ";
		if (!HelpDeskUtil.isNullOrVazio(unidadeAtendente)) {
			queryCompleta += " AND ch.unidadeAtendente like '" + unidadeAtendente + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(unidadeSolicitante)) {
			queryCompleta += " AND ch.unidadeSolicitante like '" + unidadeSolicitante + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(tipo)) {
			queryCompleta += " AND c.tipo like '" + tipo + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(subtipo)) {
			queryCompleta += " AND c.subtipo like '" + subtipo + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(estado)) {
			queryCompleta += " AND c.estado like '" + estado + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(patrimonio)) {
			queryCompleta += " AND c.idPatrimonio like '" + patrimonio + "'";
		}

		Query query = session.createQuery(queryCompleta);
		List list = query.list();
		session.flush();
		session.close();
		return list;
	}

	/*
	 * Session session = new ChamadoDAO().openSession(); if
	 * (!HelpDeskUtil.isNullOrVazio(tipo)){ } if
	 * (!HelpDeskUtil.isNullOrVazio(subtipo)){ } if
	 * (!HelpDeskUtil.isNullOrVazio(estado)){ } if
	 * (!HelpDeskUtil.isNullOrVazio(patrimonio)){ } String queryCompleta =
	 * "Select c from Chamado c, ChamadoAtendido ch where c.tipo like '" + tipo +
	 * "' AND c.subtipo like '" + subtipo + "' AND c.estado like '" + estado +
	 * "' AND c.idPatrimonio like '" + patrimonio +
	 * "' AND c.idChamado = ch.idChamado AND ch.unidadeSolicitante like '" +
	 * unidadeSolicitante + "' AND ch.unidadeAtendente like '" + unidadeAtendente +
	 * "'"; Query query = session.createQuery(queryCompleta); List list =
	 * query.list(); session.flush(); session.close(); return list;
	 */
	/**
	 * @return the unidadeSolicitante
	 */
	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	/**
	 * @param unidadeSolicitante the unidadeSolicitante to set
	 */
	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	/**
	 * @return the unidadeAtendente
	 */
	public String getUnidadeAtendente() {
		return unidadeAtendente;
	}

	/**
	 * @param unidadeAtendente the unidadeAtendente to set
	 */
	public void setUnidadeAtendente(String unidadeAtendente) {
		this.unidadeAtendente = unidadeAtendente;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the patrimonio
	 */
	public String getPatrimonio() {
		return patrimonio;
	}

	/**
	 * @param patrimonio the patrimonio to set
	 */
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

}
