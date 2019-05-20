package mail;

import java.io.File;
import java.util.List;

import util.ConexaoTXT;

/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa  uma implementacao de SistemaDeCorreio. Utiliza a conexao txt
 * para criar emails e salva-los em arquivo. Util para depuracao
 * 
 * 
 * @author Arthur Farias 
 */

public class TXTSistemaDeCorreio implements SistemaDeCorreio {
	private final String NEWLINE = System.getProperty("line.separator");

	/**
	 * 
	 * Construtor da classe
	 */
	public TXTSistemaDeCorreio() {

	}
	/**
	 * Metodo para enviar email
	 * @param destinatarios os destinatarios
	 * @param assunto o assunto do email
	 * @param mensagem o corpo de mensagem do email
	 */
	public void enviarEmail(List<String> destinatarios, String assunto,
			String mensagem) throws Exception {
		if (assunto.contains("analise")) {
			enviarNotificacaoEmAnalise(destinatarios,assunto,mensagem);
		} else if (assunto.contains("intervencao")) {
			enviarNotificacaoIntervencao(destinatarios,assunto,mensagem);
		} else if (assunto.contains("inter-encaminhamento")) {
			enviarNotificacaoInterEncaminhamento(destinatarios,assunto,mensagem);
		} else  {
			enviarNotificacaoEncaminhamento(destinatarios,assunto,mensagem);
		}
	}

	private void enviarNotificacaoInterEncaminhamento(List<String> destinatarios,
			String assunto, String mensagem) {
		ConexaoTXT con = new ConexaoTXT("mensagem"+File.separator+"inter-encaminhamento"+mensagem.hashCode()+".txt");
		con.write("para: " + destinatarios.get(0) + NEWLINE + "assunto: " + assunto
				+ NEWLINE + NEWLINE + mensagem);

	}
	private void enviarNotificacaoEmAnalise(List<String> destinatarios, String assunto,
			String mensagem) {
		ConexaoTXT con = new ConexaoTXT("mensagem"+File.separator+"abertura de chamado"+mensagem.hashCode()+".txt");
		con.write("para: " + destinatarios.get(0) + NEWLINE + "assunto: " + assunto
				+ NEWLINE + NEWLINE + mensagem);
	}

	private void enviarNotificacaoEncaminhamento(List<String> destinatarios,
			String assunto, String mensagem) {
		ConexaoTXT con = new ConexaoTXT("mensagem"+File.separator+"encaminhamento"+mensagem.hashCode()+".txt");
		con.write("para: " + destinatarios.get(0) + NEWLINE + "assunto: " + assunto
				+ NEWLINE + NEWLINE + mensagem);

	}

	private void enviarNotificacaoIntervencao(List<String> destinatarios, String assunto,
			String mensagem) {
		ConexaoTXT con = new ConexaoTXT("mensagem"+File.separator+"intervencao"+mensagem.hashCode()+".txt");
		con.write("para: " + destinatarios.get(0) + NEWLINE + "assunto: " + assunto
				+ NEWLINE + NEWLINE + mensagem);

	}

}
