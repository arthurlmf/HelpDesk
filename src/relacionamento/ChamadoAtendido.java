package relacionamento;

import excecoes.HelpDeskException;
import sistema.GerenciadorDeUnidade;

/**
 * Classe que representa um Chamado Atendido
 * @author arthur.farias
 *
 */
public class ChamadoAtendido {

	private int idChamado;

	private String unidadeSolicitante;

	private String unidadeAtendente;

	public ChamadoAtendido() {

	}

	public ChamadoAtendido(int idChamado, String unidadeSolicitante, String unidadeAtendente) {
		setIdChamado(idChamado);
		setUnidadeSolicitante(unidadeSolicitante);
		setUnidadeAtendente(unidadeAtendente);
	}

	/**
	 * @return the idChamado
	 */
	public int getIdChamado() {
		return idChamado;
	}

	/**
	 * @param idChamado the idChamado to set
	 */
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

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

	public String getNomeUnidadeSolicitante() throws HelpDeskException {
		return GerenciadorDeUnidade.getInstance().getUnidade(unidadeSolicitante).toString();
	}

	public String getNomeUnidadeAtendente() throws HelpDeskException {
		return GerenciadorDeUnidade.getInstance().getUnidade(unidadeAtendente).toString();
	}

}
