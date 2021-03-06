package usuario;

/**
 * 
 * 
 * 
 * 
 * Classe que representa um suario tipo Unidade
 * 
 * 
 * @author Arthur Farias 
 */
public class Unidade extends Usuario {

	private boolean suporte;
	
	private String gerencia;
	
	public static final String DELEGACAO = "delegacao"; 
	
	public static final String APROPRIACAO = "apropriacao";
	
	public static final String MISTA = "mista";

	/**
	 * 
	 * Construtor da Classe
	 */
	public Unidade() {

	}

	/**
	 * 
	 * Construtor da Classe
	 * 
	 */
	public Unidade(String idUnidade, String nome, boolean suporte) {
		super(idUnidade, nome, UNIDADE);
		setSuporte(suporte);
		setGerencia(APROPRIACAO);
	}
	
	

	
	
	/**
	 * @return the gerencia
	 */
	public String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia the gerencia to set
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return the suporte
	 */
	public boolean isSuporte() {
		return suporte;
	}

	/**
	 * @param suporte
	 *            the suporte to set
	 */
	public void setSuporte(boolean suporte) {
		if(!suporte){
			setGerencia(APROPRIACAO);
		}
		this.suporte = suporte;		
	}

	/**
	 * @return to string da classe
	 */
	public String toString() {
		return getNome();
	}
	

	/**
	 * um equals
	 * 
	 * @param outro
	 *            outro usuario
	 */
	public boolean equals(Object outro) {
		try {
			Unidade outroUsuario = (Unidade) outro;
			if(super.equals(outro) && (isSuporte() == outroUsuario.isSuporte())){
				return true;
			}
		} catch (ClassCastException e) {
	}
		return false;
	}
	
		
	public String getIdUnidade() {
		return getIdUsuario();
	}
	
	public boolean isGerenciaMista(){
		return getGerencia().equals(MISTA);
	}
	
	public boolean isGerenciaApropriacao(){
		return getGerencia().equals(APROPRIACAO);
	}
	
	public boolean isGerenciaDelegacao(){
		return getGerencia().equals(DELEGACAO);
	}
	
	
}
