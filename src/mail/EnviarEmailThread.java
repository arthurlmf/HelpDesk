package mail;

import java.util.List;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma Thread para enviar um email em um fluxo a parte.
 * 
 * 
 * @author Arthur Farias
 */
public class EnviarEmailThread extends Thread {

	private String threadName;

	private List<String> recipients;

	private String subject;

	private String message;
	/**
	 * 
	 * Construtor da classe
	 * @param recipients
	 * @param subject
	 * @param message
	 */
	public EnviarEmailThread(List<String> recipients, String subject, String message) {
		this.recipients = recipients;
		this.subject = subject;
		this.message = message;
		this.threadName = "Thread de envio do email de assunto '"+subject+"'";
	}
	/**
	 * Meodo que executa a thread
	 */
	public void run() {
		boolean enviado = false;		
		try {
			// create a message
			Message msg = new MimeMessage(HelpDeskSistemaDeCorreio.getSession());

			// set the from and to address
			InternetAddress addressFrom;
			addressFrom = new InternetAddress(HelpDeskSistemaDeCorreio.FROM);
			msg.setFrom(addressFrom);
			
			//set Recipients
			InternetAddress[] addressTo = new InternetAddress[recipients.size()];
			int i=0;
			for(String recipient : this.recipients){
				addressTo[i++] = new InternetAddress(recipient);
			}			
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			
			// Optional : You can also set your custom headers in the Email if
			// you
			// Want
			msg.addHeader("MyHeaderName", "myHeaderValue");

			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setContent(message, "text/plain");
			Transport.send(msg);
			enviado = true;
		} catch (Exception e) {
			System.out.println("Erro na thread " + threadName);
			e.printStackTrace();
		} finally {
			if(enviado){
				System.out.println("Email de assunto \""+ subject +"\" enviado com sucesso");
			}else{
				System.out.println("Erro. Email não enviado");
			}
		}
	}
}
