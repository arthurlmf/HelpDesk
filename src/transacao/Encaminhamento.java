package transacao;
/**
 * Classe que representa um encaminhamento
 * @author arthur.farias
 *
 */
public class Encaminhamento extends Transacao{

	private String tecnicoOrigem;
	
	private String tecnicoDestino;
	
	public Encaminhamento(){
		
	}
	
	
	public Encaminhamento(String tecnicoOrigem, String tecnicoDestino){
		super(ENCAMINHAMENTO, true);
		setTecnicoOrigem(tecnicoOrigem);
		setTecnicoDestino(tecnicoDestino);
	}


	/**
	 * @return the tecnicoOrigem
	 */
	public String getTecnicoOrigem() {
		return tecnicoOrigem;
	}


	/**
	 * @param tecnicoOrigem the tecnicoOrigem to set
	 */
	public void setTecnicoOrigem(String tecnicoOrigem) {
		this.tecnicoOrigem = tecnicoOrigem;
	}


	/**
	 * @return the tecnicoDestino
	 */
	public String getTecnicoDestino() {
		return tecnicoDestino;
	}


	/**
	 * @param tecnicoDestino the tecnicoDestino to set
	 */
	public void setTecnicoDestino(String tecnicoDestino) {
		this.tecnicoDestino = tecnicoDestino;
	}
	
	
	
	
}
