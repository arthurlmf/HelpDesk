package transacao;
/**
 * Classe que representa uma Transa��o
 * @author arthur.farias
 *
 */
public class Intervencao extends Transacao {

	private String tecnicoResponsavel;

	private String descricao;

	public Intervencao() {

	}

	public Intervencao(String tecnicoResponsavel, String descricao, boolean visivel) {
		super(INTERVENCAO, visivel);
		setTecnicoResponsavel(tecnicoResponsavel);
		setDescricao(descricao);
	}

	/**
	 * @return the tecnicoResponsavel
	 */
	public String getTecnicoResponsavel() {
		return tecnicoResponsavel;
	}

	/**
	 * @param tecnicoResponsavel the tecnicoResponsavel to set
	 */
	public void setTecnicoResponsavel(String tecnicoResponsavel) {
		this.tecnicoResponsavel = tecnicoResponsavel;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return super.toString() + " - " + getDescricao();
	}

}
