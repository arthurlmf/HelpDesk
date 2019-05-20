package usuario;

import excecoes.HelpDeskException;
import sistema.GerenciadorDeRelacionamento;
import util.Util;

/**
 * 
 * 
 * 
 * 
 * Classe que representa um tipo Usuario
 * 
 * 
 * @author Arthur Farias 
 */
public class Usuario {
	/**
	 * String final tecnico
	 */
	public static final String TECNICO = "tecnico";

	/**
	 * String final unidade
	 */
	public static final String UNIDADE = "unidade";

	/**
	 * String final administrador
	 */
	public static final String ADMINISTRADOR = "administrador";

	private String idUsuario;

	private String tipoUsuario;

	private String nome;

	/**
	 * @param id
	 *            id do usuario
	 * @param tipoUsuario
	 *            tipo do usuario
	 */
	public Usuario(String idUsuario, String nome, String tipoUsuario) {
		setIdUsuario(idUsuario);
		setNome(nome);
		setTipoUsuario(tipoUsuario);
	}

	/**
	 * 
	 * Construtor da Classe
	 */
	public Usuario() {

	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return Util.capitular(nome);
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return O id
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param id
	 *            O id a ser setado
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario.toLowerCase();
	}

	/**
	 * @return O tipo do usuario
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario
	 *            O tipo do usuario a ser setado
	 */
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * 
	 * @return se é uma unidade
	 */
	public boolean isUnidade() {
		if (tipoUsuario.equalsIgnoreCase(UNIDADE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return se é um tecnico
	 */
	public boolean isTecnico() {
		if (tipoUsuario.equalsIgnoreCase(TECNICO)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return se é um administrador
	 */
	public boolean isAdministrador() {
		if (tipoUsuario.equalsIgnoreCase(ADMINISTRADOR)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * to string da classe
	 */
	public String toString() {
		return getIdUsuario();
	}

	/**
	 * um equals
	 * 
	 * @param outro
	 *            outro usuario
	 */
	public boolean equals(Object outro) {
		try {
			Usuario outroUsuario = (Usuario) outro;
			if (this.getIdUsuario().equalsIgnoreCase(
					outroUsuario.getIdUsuario())
					&& getNome().equalsIgnoreCase(outroUsuario.getNome())
					&& getTipoUsuario().equalsIgnoreCase(
							outroUsuario.getTipoUsuario())) {
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}
	
	public String getMeuIdOuDaMinhaUnidade() throws HelpDeskException{
		if(isUnidade()){
			return getIdUsuario();
		}
		if(isTecnico()){
			return GerenciadorDeRelacionamento.getInstance().getUnidadeDoTecnico(getIdUsuario()).getIdUsuario();
		}
		return null;
	}

}
