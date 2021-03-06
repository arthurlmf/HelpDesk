package integracaoTRE;

/**
 * Classe que representa uma Unidade no BDTRE
 * @author arthur.farias
 *
 */
public class UnidadeBDTRE {

	private int codigo;
	private String idUnidade;
	private String descricao;

	public UnidadeBDTRE() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return getIdUnidade();
	}

	
	public boolean equals(Object other) {
		try {
			UnidadeBDTRE unitre = (UnidadeBDTRE) other;
			if (unitre.getCodigo() == getCodigo()
					&& unitre.getDescricao().equals(getDescricao())
					&& unitre.getIdUnidade().equals(getIdUnidade())) {
				return true;
			} else
				return false;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
}
