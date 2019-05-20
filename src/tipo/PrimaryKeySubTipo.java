package tipo;

import java.io.Serializable;

/**
 * Representa uma chave primaria Subtipo
 * @author arthur.farias
 *
 */
public class PrimaryKeySubTipo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idUnidade;
	private String nomeTipo;
	private String nomeSubtipo;

	public PrimaryKeySubTipo() {
	}

	public PrimaryKeySubTipo(String idUnidade, String nomeTipo, String nomeSubtipo) {
		setIdUnidade(idUnidade);
		setNomeTipo(nomeTipo);
		setNomeSubtipo(nomeSubtipo);
	}

	/**
	 * @return the idUnidade
	 */
	public String getIdUnidade() {
		return idUnidade;
	}

	/**
	 * @param idUnidade the idUnidade to set
	 */
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	/**
	 * @return the nomeTipo
	 */
	public String getNomeTipo() {
		return nomeTipo;
	}

	/**
	 * @param nomeTipo the nomeTipo to set
	 */
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	/**
	 * @return the nomeSubTipo
	 */
	public String getNomeSubtipo() {
		return nomeSubtipo;
	}

	/**
	 * @param nomeSubTipo the nomeSubTipo to set
	 */
	public void setNomeSubtipo(String nomeSubTipo) {
		this.nomeSubtipo = nomeSubTipo;
	}

	/**
	 * Verifica se as chaves primarias são iguais
	 * 
	 * @param outra
	 * @return
	 */
	public boolean equals(Object outra) {
		PrimaryKeySubTipo outraKey = (PrimaryKeySubTipo) outra;
		return getIdUnidade().equals(outraKey.getIdUnidade()) && getNomeTipo().equals(outraKey.getIdUnidade())
				&& getNomeSubtipo().equals(outraKey.getNomeSubtipo());
	}

	/**
	 * HashCode
	 */
	public int hashCode() {
		return getIdUnidade().hashCode() + getNomeTipo().hashCode() + getNomeSubtipo().hashCode();
	}

	/**
	 * Representacao para este chave primaria
	 * 
	 * @return String
	 */
	public String toString() {
		return "(" + getIdUnidade() + "," + getNomeTipo() + "," + getNomeSubtipo() + ")";
	}

}
