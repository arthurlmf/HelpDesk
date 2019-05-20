package chamado;

import excecoes.HelpDeskException;
import sistema.GerenciadorDeChamado;
import sistema.GerenciadorDeTecnico;
import util.Data;

/**
 * 
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa
 * 
 * 
 * @author Arthur Farias 
 */
public class Chamado {
	/**
	 * String final em analise
	 */
	public static final String EM_AUTORIZACAO = "em autorizacao";
	/**
	 * String final em analise
	 */
	public static final String EM_ANALISE = "em analise";
	/**
	 * String final aberto
	 */
	public static final String DELEGADO = "delegado";
	/**
	 * String final aberto
	 */
	public static final String ABERTO = "aberto";
	/**
	 * String final encaminhado
	 */
	public static final String ENCAMINHADO = "encaminhado";
	/**
	 * String final fechado
	 */
	public static final String FECHADO = "fechado";

	private int idChamado;

	private Data data;

	private String tipo;

	private String subtipo;

	private String idPatrimonio;

	private String estado = EM_ANALISE;

	private String descricao;

	/**
	 * 
	 * Construtor da Classe
	 */
	public Chamado() {

	}

	/**
	 * 
	 * Construtor da Classe
	 * 
	 * @param descricao
	 *            descricao do chamado
	 * @param idPatrimonio
	 * @param tipoChamado
	 *            tipo do chamado
	 */
	public Chamado(String descricao, String tipo, String subtipo,
			String idPatrimonio) {
		setDescricao(descricao);
		setTipo(tipo);
		setSubtipo(subtipo);
		setIdPatrimonio(idPatrimonio);
		setData(new Data());
	}

	/**
	 * @return the idPatrimonio
	 */
	public String getIdPatrimonio() {
		return idPatrimonio;
	}

	/**
	 * @param idPatrimonio
	 *            the idPatrimonio to set
	 */
	public void setIdPatrimonio(String idPatrimonio) {
		this.idPatrimonio = idPatrimonio;
	}

	/**
	 * @return the idChamado
	 */
	public int getIdChamado() {
		return idChamado;
	}

	/**
	 * @param idChamado
	 *            the idChamado to set
	 */
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
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
	 * @param subtipo
	 *            the subtipo to set
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
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return GerenciadorDeChamado.getInstance().responsavelDoChamado(idChamado);
	}

	public String getNomeResponsavel() {
		try {
			return GerenciadorDeTecnico.getInstance().getTecnico(
					GerenciadorDeChamado.getInstance().responsavelDoChamado(idChamado))
					.toString();
		} catch (HelpDeskException e) {
			return GerenciadorDeChamado.getInstance().responsavelDoChamado(idChamado)
					.toString();
		}

	}

	/**
	 * @return to string da classe
	 */
	public String toString() {
		return getIdChamado() + "";
	}

	public boolean isAberto() {
		return getEstado().equals(ABERTO);
	}

	public boolean isEmAnalise() {
		return getEstado().equals(EM_ANALISE);
	}

	public boolean isEncaminhado() {
		return getEstado().equals(ENCAMINHADO);
	}

	public boolean isFechado() {
		return getEstado().equals(FECHADO);
	}
	
	public boolean isEmAutorizacao() {
		return getEstado().equals(EM_AUTORIZACAO);
	}
	
	public boolean isDelegado() {
		return getEstado().equals(DELEGADO);
	}

}
