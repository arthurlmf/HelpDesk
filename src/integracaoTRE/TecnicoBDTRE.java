package integracaoTRE;
/**
 * Classe que representa um Tecnico no BDTRE
 * @author arthur.farias
 *
 */
public class TecnicoBDTRE {

	private String matricula;
	private String nome;
	private int unidade_codigo;
	private String idUnidade;

	public TecnicoBDTRE() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return getNome();
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
