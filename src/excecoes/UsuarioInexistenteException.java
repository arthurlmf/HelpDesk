package excecoes;
/**
 * Classe que representa um usuario inexistente Exception
 * @author arthur.farias
 *
 */
public class UsuarioInexistenteException extends HelpDeskException {

	private static final long serialVersionUID = 1L;

	public UsuarioInexistenteException() {
		super("Usuario nao autenticado");
	}

}
