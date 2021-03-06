package integracaoTRE;
/**
 * Classe que representa um Gerente no BDTRE
 * @author arthur.farias
 *
 */
public class GerenteBDTRE {

	private int unidade_codigo;
	private String idTecnico;
	private String idUnidade;

	public GerenteBDTRE() {

	}

	public int getUnidade_codigo() {
		return unidade_codigo;
	}

	public void setUnidade_codigo(int unidade_codigo) {
		this.unidade_codigo = unidade_codigo;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String toString() {
		return idTecnico + " " + unidade_codigo;
	}

}