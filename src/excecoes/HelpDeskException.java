package excecoes;

/**
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa um exception do HelpDeskTRE
 * 
 * 
 * @author Arthur Farias
 */
public class HelpDeskException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Cria uma exce��o usada caso haja algum erro na excecu��o de alguma tarefa do
	 * JATO.
	 * 
	 */
	public HelpDeskException() {
		super();
	}

	/**
	 * Cria uma exce��o usada caso haja algum erro na excecu��o de alguma tarefa do
	 * Help Desk.
	 * 
	 * @param message a mensagem de erro.
	 * @param causa   a causa do erro.
	 */
	public HelpDeskException(String message, Throwable causa) {
		super(message, causa);
	}

	/**
	 * Cria uma exce��o usada caso haja algum erro na excecu��o de alguma tarefa do
	 * Help Desk.
	 * 
	 * @param message a mensagem de erro.
	 */
	public HelpDeskException(String message) {
		super(message);
	}

	/**
	 * Cria uma exce��o usada caso haja algum erro na excecu��o de alguma tarefa do
	 * Help Desk.
	 * 
	 * @param causa a causa do erro.
	 */
	public HelpDeskException(Throwable causa) {
		super(causa);
	}

}
