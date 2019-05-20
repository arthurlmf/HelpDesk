package bancoDeSolucao;

/**
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa um artigo para o sistema
 * 
 * 
 * @author Arthur Farias 
 */
public class Artigo {

	private int idArtigo;

	private String titulo;

	private String texto;

	private String tipo;
	
	private String subtipo;

	public Artigo() {

	}

	/**
	 * 
	 * Construtor da classe
	 * 
	 * @param titulo
	 *            o titulo do artigo
	 * @param texto
	 *            o texto do artigo
	 * @param tipo
	 *            o tipo do artigo
	 */
	public Artigo(String titulo, String texto, String tipo, String subtipo) {
		this.subtipo = subtipo;
		setTitulo(titulo);
		setTexto(texto);
		setTipo(tipo);
	}

	/**
	 * @return Returns the idArtigo.
	 */
	public int getIdArtigo() {
		return idArtigo;
	}

	/**
	 * @param idArtigo
	 *            The idArtigo to set.
	 */
	public synchronized void setIdArtigo(int idArtigo) {
		this.idArtigo = idArtigo;
	}

	/**
	 * @return Returns the texto.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            The texto to set.
	 */
	public synchronized void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            The tipo to set.
	 */
	public synchronized void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Returns the titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            The titulo to set.
	 */
	public synchronized void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Returns uma string.
	 */
	public synchronized String toString() {
		return getTitulo();
	}

	/**
	 * Verifica se um artigo � igual a outro
	 * 
	 * @param other
	 * @return true, se verdadeiro, false caso contr�rio.
	 */
	public boolean equals(Object other) {
		try {
			Artigo otherArtigo = (Artigo) other;
			if (this.getIdArtigo() == otherArtigo.getIdArtigo()
					&& this.getTexto().equals(otherArtigo.getTexto())
					&& this.getTipo().equals(otherArtigo.getTipo())
					&& this.getTitulo().equals(otherArtigo.getTitulo())
					&& this.getSubtipo().equals(otherArtigo.getSubtipo())) {
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}
}
