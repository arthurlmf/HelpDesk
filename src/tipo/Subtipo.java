package tipo;

import util.MsgErros;
import excecoes.HelpDeskException;

/**
 * Chave primaria Subtipo
 * @author arthur.farias
 *
 */
public class Subtipo {

	private PrimaryKeySubTipo chaveSubtipo;
	private boolean temPatrimonio;
	private int prazoAtendimento;

	public Subtipo(PrimaryKeySubTipo chaveSubtipo, boolean temPatrimonio, int prazoAtendimento) {
		super();
		this.chaveSubtipo = chaveSubtipo;
		this.temPatrimonio = temPatrimonio;
		this.prazoAtendimento = prazoAtendimento;
	}

	/**
	 * Construtor para um subtipo de um Tipo
	 * 
	 * @param idTipo
	 * @param nomeSubTipo
	 * @param patrimonio
	 * @throws HelpDeskException
	 */
	public Subtipo(String idUnidade, String nomeTipo, String nomeSubtipo, boolean patrimonio, int prazoAtendimento)
			throws HelpDeskException {
		chaveSubtipo = new PrimaryKeySubTipo(idUnidade, nomeTipo, nomeSubtipo);
		setTemPatrimonio(patrimonio);
		setPrazoAtendimento(prazoAtendimento);
	}

	public Subtipo() {
		chaveSubtipo = new PrimaryKeySubTipo();
	}

	public int getPrazoAtendimento() {
		return prazoAtendimento;
	}

	public void setPrazoAtendimento(int prazoAtendimento) throws HelpDeskException {
		if (prazoAtendimento < 0) {
			throw new HelpDeskException(
					MsgErros.VALOR_DE_ATRIBUTO_INVALIDO.msg(prazoAtendimento + "", "prazo de atendimento"));
		}
		this.prazoAtendimento = prazoAtendimento;
	}

	/**
	 * @return the chaveSubtipo
	 */
	public PrimaryKeySubTipo getChaveSubtipo() {
		return chaveSubtipo;
	}

	/**
	 * @param chaveSubtipo the chaveSubtipo to set
	 */
	public void setChaveSubtipo(PrimaryKeySubTipo chaveSubtipo) {
		this.chaveSubtipo = chaveSubtipo;
	}

	/**
	 * @return the nomeTipo
	 */
	public String getNomeTipo() {
		return chaveSubtipo.getNomeTipo();
	}

	/**
	 * @param nomeTipo the nomeTipo to set
	 */
	public void setNomeTipo(String nomeTipo) {
		this.chaveSubtipo.setNomeTipo(nomeTipo);
	}

	/**
	 * @return the idUnidade
	 */
	public String getIdUnidade() {
		return getChaveSubtipo().getIdUnidade();
	}

	/**
	 * @param idUnidade the idUnidade to set
	 */
	public void setIdUnidade(String idUnidade) {
		getChaveSubtipo().setIdUnidade(idUnidade);
	}

	/**
	 * @return the nomeSubTipo
	 */
	public String getNomeSubTipo() {
		return getChaveSubtipo().getNomeSubtipo();
	}

	/**
	 * @param nomeSubTipo the nomeSubTipo to set
	 */
	public void setNomeSubTipo(String nomeSubTipo) {
		getChaveSubtipo().setNomeSubtipo(nomeSubTipo);
	}

	/**
	 * @return the patrimonio
	 */
	public boolean getTemPatrimonio() {
		return temPatrimonio;
	}

	public boolean temPatrimonio() {
		return getTemPatrimonio();
	}

	/**
	 * @param patrimonio the patrimonio to set
	 */
	public void setTemPatrimonio(boolean patrimonio) {
		this.temPatrimonio = patrimonio;
	}

	/**
	 * Recupera uma representaš~~ao para o subtipo
	 */
	public String toString() {
		return getNomeSubTipo();
	}

}
