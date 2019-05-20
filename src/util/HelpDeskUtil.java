package util;

import java.util.StringTokenizer;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import excecoes.HelpDeskException;
/**
 * Classe utility
 * @author arthur.farias
 *
 */
public class HelpDeskUtil {

	private static final String ARQUIVO_DE_CONFIGURACAO = "hibernateRemoto.cfg.xml";
	
	private static SessionFactory sessionFactoryLocal = new Configuration().configure().buildSessionFactory();
	
	private static SessionFactory sessionFactoryRemota = new Configuration().configure(ARQUIVO_DE_CONFIGURACAO).buildSessionFactory();
	
	public static SessionFactory getSessionFactoryLocal(){
		return sessionFactoryLocal;
	}
	
	public static SessionFactory getSessionFactoryRemota(){
		return sessionFactoryRemota;
	}
	
	public static boolean isNull(Object o){
		return o==null;
	}
	
	public static boolean isVazio(String valor){
		return valor.trim().isEmpty();
	}
	
	public static boolean isNullOrVazio(String valor){
		return isNull(valor)||isVazio(valor);
	}
	
	public static String getFirstToken(String palavra, String token){
		StringTokenizer stK = new StringTokenizer(palavra, token);
		if (stK.hasMoreTokens()){
			return stK.nextToken();
		}
		return "";
	}
	
	public static String getNumberFormatado(int numero){
		String result=numero+"";
		int quantDigitos = result.length();
		int maxDigitos=6;
		for(int i=quantDigitos;i<maxDigitos;i++){
			result="0"+result;
		}
		return result;
	}
	
	public static int toInt(String valorString, String msgErro) throws HelpDeskException {
		int valor;
		try{
			valor = Integer.parseInt(valorString);
		}catch(Exception e){
			throw new HelpDeskException(msgErro);
		}
		return valor;
	}
	
	

}
