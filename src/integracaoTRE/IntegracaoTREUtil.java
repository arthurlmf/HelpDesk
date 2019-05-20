package integracaoTRE;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Classe utilitaria
 * @author arthur.farias
 *
 */
public class IntegracaoTREUtil {

	private static final String ARQUIVO_DE_CONFIGURACAO = "hibernateRemoto.cfg.xml";

	private static SessionFactory sessionFactoryRemota = new Configuration().configure(ARQUIVO_DE_CONFIGURACAO)
			.buildSessionFactory();

	public static SessionFactory getSessionFactoryRemota() {
		return sessionFactoryRemota;
	}

	public static boolean equalsIgnoreDominio(String dominio1, String dominio2) {
		int num1 = dominio1.indexOf("@");
		int num2 = dominio2.indexOf("@");
		dominio1 = dominio1.trim();
		dominio2 = dominio2.trim();
		if ((dominio1.indexOf("@") != -1 && dominio2.indexOf("@") != -1)
				|| (dominio1.indexOf("@") == -1 && dominio2.indexOf("@") == -1)) {
			return dominio1.equalsIgnoreCase(dominio2);
		} else {
			String semArroba1;
			String semArroba2;
			if (dominio1.indexOf("@") == -1) {
				semArroba1 = dominio1;
			} else {
				semArroba1 = dominio1.substring(0, dominio1.indexOf("@"));
			}
			if (dominio2.indexOf("@") == -1) {
				semArroba2 = dominio2;
			} else {
				semArroba2 = dominio2.substring(0, dominio2.indexOf("@"));
			}
			return semArroba1.equalsIgnoreCase(semArroba2);
		}
	}

	private static final String DOMINIO = "@tre.br";

	public static String getDominioSeNaoTiver(String idUsuario) {
		if (!idUsuario.contains(DOMINIO)) {
			String aux = idUsuario + DOMINIO;
			return aux.toLowerCase();
		}
		return idUsuario.toLowerCase();

	}

}
