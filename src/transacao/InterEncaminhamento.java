package transacao;

import sistema.GerenciadorDeTecnico;
import excecoes.HelpDeskException;

/**
 * Classe que representa um InterEncamimamento
 * @author arthur.farias
 *
 */
public class InterEncaminhamento extends Transacao{

	private String unidadeOrigem;
	
	private String unidadeDestino;
	
	private String tecnicoResponsavel;
	
	public InterEncaminhamento(){
		
	}
	
	public InterEncaminhamento(String unidadeOrigem, String unidadeDestino, String tecnicoResponsavel, boolean visivel){
		super(INTERENCAMINHAMENTO, visivel);
		setUnidadeOrigem(unidadeOrigem);
		setUnidadeDestino(unidadeDestino);
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

	/**
	 * @return the unidadeOrigem
	 */
	public String getUnidadeOrigem() {
		return unidadeOrigem;
	}


	/**
	 * @param unidadeOrigem the unidadeOrigem to set
	 */
	public void setUnidadeOrigem(String unidadeOrigem) {
		this.unidadeOrigem = unidadeOrigem;
	}


	/**
	 * @return the unidadeDestino
	 */
	public String getUnidadeDestino() {
		return unidadeDestino;
	}


	/**
	 * @param unidadeDestino the unidadeDestino to set
	 */
	public void setUnidadeDestino(String unidadeDestino) {
		this.unidadeDestino = unidadeDestino;
	}
	
	public String getNomeTecnicoResponsavel() {		
		try {
			return GerenciadorDeTecnico.getInstance().getTecnico(getTecnicoResponsavel()).toString();
		} catch (HelpDeskException e) {
			return getTecnicoResponsavel();
		}
		
	}
	
	
}
