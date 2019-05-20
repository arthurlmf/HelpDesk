package relacionamento;
/**
 * Classe que representa um Gerente De Unidade
 * @author arthur.farias
 *
 */
public class GerenteDeUnidade {

	private String idUnidade;

	private String idTecnico;

	public GerenteDeUnidade() {

	}

	public GerenteDeUnidade(String idUnidade, String idTecnico) {
		setIdUnidade(idUnidade);
		setIdTecnico(idTecnico);
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
	 * @return the idtecnico
	 */
	public String getIdTecnico() {
		return idTecnico;
	}

	/**
	 * @param idtecnico the idtecnico to set
	 */
	public void setIdTecnico(String idtecnico) {
		this.idTecnico = idtecnico;
	}

}
