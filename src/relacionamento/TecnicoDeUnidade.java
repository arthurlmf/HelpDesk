package relacionamento;
/**
 * Classe que representa um Tecnico de Unidade
 * @author arthur.farias
 *
 */
public class TecnicoDeUnidade {

	private String tecnico;

	private String unidade;

	public TecnicoDeUnidade() {

	}

	public TecnicoDeUnidade(String tecnico, String unidade) {
		setTecnico(tecnico);
		setUnidade(unidade);
	}

	/**
	 * @return the tecnico
	 */
	public String getTecnico() {
		return tecnico;
	}

	/**
	 * @param tecnico the tecnico to set
	 */
	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	/**
	 * @return the unidade
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade the unidade to set
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public boolean equals(Object o) {
		try {
			TecnicoDeUnidade outro = (TecnicoDeUnidade) o;
			if (outro.getTecnico().equals(getTecnico()) && outro.getUnidade().equals(getUnidade())) {
				return true;
			} else
				return false;
		} catch (ClassCastException e) {
			return false;
		}

	}

	public String toString() {
		return "[ " + getTecnico() + " | " + getUnidade() + " ]";
	}

}
