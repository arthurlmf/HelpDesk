package tipo;


/**
 * Classe que representa um Tipo
 * @author arthur.farias
 *
 */
public class Tipo {

	private PrimaryKeyTipo chaveTipo;
	
	
	public Tipo(){
		chaveTipo = new PrimaryKeyTipo(); 
	}
	
	/**
	 * Construtor da Classe
	 * @param chaveTipo
	 */
	public Tipo(PrimaryKeyTipo chaveTipo) {
		this.chaveTipo = chaveTipo;
	}

	/**
	 * Construtor da Classe
	 * @param nomeTipo
	 * @param idUnidade
	 */
	public Tipo(String idUnidade, String nomeTipo) {
		chaveTipo = new PrimaryKeyTipo(idUnidade,nomeTipo);
		
	}
	
	
	/**
	 * @return the chaveTipo
	 */
	public PrimaryKeyTipo getChaveTipo() {
		return chaveTipo;
	}


	/**
	 * @param chaveTipo the chaveTipo to set
	 */
	public void setChaveTipo(PrimaryKeyTipo chaveTipo) {
		this.chaveTipo = chaveTipo;
	}


	/**
	 * @return the nomeTipo
	 */
	public String getNomeTipo() {
		return getChaveTipo().getNomeTipo();
	}

	/**
	 * @param nomeTipo the nomeTipo to set
	 */
	public void setNomeTipo(String nomeTipo) {
		getChaveTipo().setNomeTipo(nomeTipo);
	}

	/**
	 * @return the idUnidade
	 */
	public String getIdUnidade() {
		return getChaveTipo().getIdUnidade();
	}

	/**
	 * @param idUnidade the idUnidade to set
	 */
	public void setIdUnidade(String idUnidade) {
		getChaveTipo().setIdUnidade(idUnidade);
	}
	
	/**
	 * Recupera uma representação para o Tipo
	 * @param 
	 */
	public String toString(){
		return getNomeTipo();
	}
	/**
	 * Implementacao do Equels
	 */
	public boolean equals(Object o){
		try {
			Tipo t = (Tipo)o;
			return getChaveTipo().equals(t.getChaveTipo());
		} catch (ClassCastException e) {
			return false;		}
	}
	
	
	
}
