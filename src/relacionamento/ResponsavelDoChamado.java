package relacionamento;

/**
 * Classe que representa um Responsavel do Chamado
 * @author arthur.farias
 *
 */
public class ResponsavelDoChamado {

	private int idChamado;
	
	private String idTecnico;
	
	public ResponsavelDoChamado(){
		
	}
	
	public ResponsavelDoChamado(int idChamado, String idTecnico){
		setIdChamado(idChamado);
		setIdTecnico(idTecnico);
	}

	
	/**
	 * @return the idChamado
	 */
	public int getIdChamado() {
		return idChamado;
	}

	/**
	 * @param idChamado the idChamado to set
	 */
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	/**
	 * @return the idTecnico
	 */
	public String getIdTecnico() {
		return idTecnico;
	}

	/**
	 * @param idTecnico the idTecnico to set
	 */
	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}
	
	
}
