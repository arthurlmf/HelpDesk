package mail;

import java.util.List;

/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Interface que representa um SistemaDeCorreio para o sistema 
 * 
 * 
 * @author Arthur Farias 
 */
public interface SistemaDeCorreio {

	/**
	 * Metodo para enviar email
	 * @param destinatarios os destinatarios
	 * @param assunto o assunto do email
	 * @param mensagem o corpo de mensagem do email
	 */
	public void enviarEmail(List<String> list, String assunto,
			String mensagem) throws Exception;

}
