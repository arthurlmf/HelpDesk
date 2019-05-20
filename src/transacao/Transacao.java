package transacao;

import util.Data;
/**
 * Classe que representa uma transacao
 * @author arthur.farias
 *
 */
public class Transacao {

	private int idTransacao;

	private String tipo;

	private Data data;

	private boolean visivel;

	public static final String INTERENCAMINHAMENTO = "interencaminhamento";

	public static final String ENCAMINHAMENTO = "encaminhamento";

	public static final String INTERVENCAO = "intervencao";

	public static final String DELEGACAO = "delegacao";

	public static final String APROPRIACAO = "apropriacao";

	public static final String FECHADO = "fechado";

	public static final String VISITA = "visita";

	public Transacao() {

	}

	public Transacao(String tipo, boolean visivel) {
		this.tipo = tipo;
		this.visivel = visivel;
		setData(new Data());
	}

	/**
	 * @return the visivel
	 */
	public boolean isVisivel() {
		return visivel;
	}

	/**
	 * @param visivel the visivel to set
	 */
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	/**
	 * @return the idTransacao
	 */
	public int getIdTransacao() {
		return idTransacao;
	}

	/**
	 * @param idTransacao the idTransacao to set
	 */
	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
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
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}

	public boolean isEncaminhamento() {
		if (tipo.equalsIgnoreCase(ENCAMINHAMENTO)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isInterEncaminhamento() {
		if (tipo.equalsIgnoreCase(INTERENCAMINHAMENTO)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isIntervencao() {
		if (tipo.equalsIgnoreCase(INTERVENCAO)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDelegacao() {
		if (tipo.equalsIgnoreCase(DELEGACAO)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isApropriacao() {
		if (tipo.equalsIgnoreCase(APROPRIACAO)) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return getIdTransacao() + " - " + getTipo();
	}

}
