package usuario;

import sistema.GerenciadorDeRelacionamento;
import sistema.GerenciadorDeUnidade;

/**
 * 
 * 
 * 
 * Classe que representa um usuario Tecnico
 * 
 * 
 * @author Arthur Farias 
 */
public class Tecnico extends Usuario {

	/**
	 * 
	 * Construtor da Classe
	 */
	public Tecnico() {

	}

	/**
	 * 
	 * Construtor da Classe
	 * 
	 * @param idEspecialista
	 *            id do especialista
	 * @param tipoEspecialista
	 *            tipo do especialista
	 */
	public Tecnico(String idTecnico, String nome, String tipo) {
		super(idTecnico, nome, tipo);
	}
	
	public Tecnico(String idTecnico, String nome) {
		super(idTecnico, nome, Usuario.TECNICO);
	}

	/**
	 * @return retorna o id do usuario
	 */
	public String toString() {
		return getNome();
	}
	/**
	 * Testa se usuario é gerente
	 * @return true se for gerente
	 */
	public boolean isGerente(){		
		try {
			String idUnidade = GerenciadorDeRelacionamento.getInstance().getUnidade(getIdUsuario()).getIdUsuario();
			return GerenciadorDeUnidade.getInstance().isGerente(idUnidade, getIdUsuario());
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean equals(Object o){
		Tecnico t = (Tecnico) o;
		return t.getIdUsuario().equals(this.getIdUsuario());
	}
}
