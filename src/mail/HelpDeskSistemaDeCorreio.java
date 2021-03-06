package mail;

import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import sistema.GerenciadorConfig;

import config.Config;
import dao.ConfigDAO;
import excecoes.HelpDeskException;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma implementacao do SistemaDeCorreio. Esta classe implementa
 * uma forma de correio baseado nas configuracao do arquivo email_config.xml  e para o sistema
 * 
 * 
 * @author Arthur Farias
 */
public class HelpDeskSistemaDeCorreio implements SistemaDeCorreio {
	
	
	

	public static String USERNAME;

	public static String DOMAIN;

	public static String PASSWORD;

	public static String SMTP_HOSTNAME;

	public static String SMTP_PORT;

	public static String SSL_FACTORY;

	public static String FROM;

	private static Session session;

	/**
	 * Metodo para enviar email
	 * @param destinatarios os destinatarios
	 * @param assunto o assunto do email
	 * @param mensagem o corpo de mensagem do email
	 */
	public void enviarEmail(List<String> destinatarios, String assunto,
			String mensagem) throws Exception {
		new EnviarEmailThread(destinatarios, assunto, mensagem).start();
	}
	/**
	 * Pega a Session do email
	 * @return a session do email
	 * @throws HelpDeskException 
	 */
	public synchronized static Session getSession() throws HelpDeskException {
		if (session == null) {
			carregarConfiguracao();
			boolean debug = false;
			// Set the host smtp address
			Properties props = new Properties();
			props.put("mail.smtp.host", HelpDeskSistemaDeCorreio.SMTP_HOSTNAME);
			props.put("mail.smtp.auth", "true");			
			
			session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						String username = HelpDeskSistemaDeCorreio.USERNAME;

						String password = HelpDeskSistemaDeCorreio.PASSWORD;

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			session.setDebug(debug);
		}
		return session;
	}

	/**
	 * Carrega as configuracoes de email
	 * @throws HelpDeskException 
	 *
	 */
	private synchronized static void carregarConfiguracao() throws HelpDeskException {
		
		
		USERNAME = GerenciadorConfig.getInstance().getConfig(Config.USERNAME).getValor();
		DOMAIN = GerenciadorConfig.getInstance().getConfig(Config.DOMAIN).getValor();
		PASSWORD = GerenciadorConfig.getInstance().getConfig(Config.PASSWORD).getValor();
		SMTP_HOSTNAME =GerenciadorConfig.getInstance().getConfig(Config.SMTP_HOSTNAME).getValor();
		FROM = USERNAME + DOMAIN;
	}	
	
}
