package transacao;
/**
 * Classe que representa um Apropriacao
 * @author arthur.farias
 *
 */
public class Apropriacao extends Transacao {

	private String tecnicoResponsavel;

	public Apropriacao() {

	}

	public Apropriacao(String tecnicoResponsavel) {
		super(APROPRIACAO, true);
		setTecnicoResponsavel(tecnicoResponsavel);
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

}
