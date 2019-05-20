package bancoDeSolucao;

/**
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa uma FAQ no sistema
 * 
 * 
 * @author Arthur Farias 
 */

public class FAQ {

	private int idFaq;

	private String pergunta;

	private String resposta;

	private String tipo;
	
	private String subtipo;
	


	/**
	 * 
	 * Construtor da classe
	 */
	public FAQ() {

	}

	/**
	 * 
	 * Construtor da classe
	 * 
	 * @param pergunta
	 *            a pergunta
	 * @param resposta
	 *            a resposta
	 * @param tipo
	 *            o tipo de faq
	 */
	public FAQ(String pergunta, String resposta, String tipo, String subtipo) {
		this.subtipo = subtipo;
		setPergunta(pergunta);
		setResposta(resposta);
		setTipo(tipo);
	}

	/**
	 * @return Returns the idFaq.
	 */
	public int getIdFaq() {
		return idFaq;
	}

	/**
	 * @param idFaq
	 *            The idFaq to set.
	 */
	public synchronized void setIdFaq(int idFaq) {
		this.idFaq = idFaq;
	}

	/**
	 * @return Returns the pergunta.
	 */
	public String getPergunta() {
		return pergunta;
	}

	/**
	 * @param pergunta
	 *            The pergunta to set.
	 */
	public synchronized void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	/**
	 * @return Returns the resposta.
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 *            The resposta to set.
	 */
	public synchronized void setResposta(String resposta) {
		this.resposta = resposta;
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
	 * @return Returns uma string.
	 */
	public String toString() {
		return getPergunta();
	}

	/**
	 * Verifica se uma FAQ é igual a outra
	 * 
	 * @param other
	 * @return true, se verdadeiro, false caso contrário.
	 */
	public boolean equals(Object objeto) {
		try {
			FAQ otherFAQ = (FAQ) objeto;
			if (this.getIdFaq() == otherFAQ.getIdFaq()
					&& this.getPergunta().equals(otherFAQ.getPergunta())
					&& this.getTipo().equals(otherFAQ.getTipo())
					&& this.getResposta().equals(otherFAQ.getResposta())
					&& this.getSubtipo().equals(otherFAQ.getSubtipo())) {

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
