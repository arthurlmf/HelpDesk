package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import excecoes.HelpDeskException;

/**
 * 
 *  
 * Classe que representa uma reflexao para o helpdesd
 * 
 * 
 * @author Arthur Farias 
 */
public class HelpDeskReflexao {

	/**
	 * Retorna um atributo do objeto passado, desde de que ele implemente o
	 * metodo get segundo a convencao para o atributo desejado.
	 * 
	 * @param objeto
	 *            O objeto sob o qual se invocara o get.
	 * @param atributo
	 *            O atributo desejado do objeto.
	 * @return O valor do atributo especificado do objeto.
	 * @throws AtributoNaoExisteExeption
	 *             Caso o atributo especificado nao exista.
	 */
	public static Object getAtributo(Object objeto, String atributo, boolean isBoolean) throws HelpDeskException {
		try {
			Method m = null;
			if (isBoolean){
				m = objeto.getClass().getMethod("is" + corrigeStringAtributo(atributo));
			}else{
				m = objeto.getClass().getMethod("get" + corrigeStringAtributo(atributo));
			}
			Object valor = m.invoke(objeto);
			return valor;
		} catch (Exception e) {
			throw new HelpDeskException("Atributo \"" + atributo + "\" não existe.");
		}
	}
	
	public static Object getAtributo(Object objeto, String atributo) throws HelpDeskException {
		return getAtributo(objeto, atributo, false);
	}


	/**
	 * Altera um atributo do objeto passado, desde de que ele implemente o
	 * metodo set segundo a convencao para o atributo desejado.
	 * 
	 * @param objeto
	 *            O objeto sob o qual se invocara o get.
	 * @param atributo
	 *            O atributo que se deseja alterar do objeto passado.
	 * @param value
	 *            O novo valor do Atributo especificado.
	 * @throws HelpDeskException
	 *             Caso nao seja possivel setar o atributo pedido.
	 */
	public static void setAtributo(Object objeto, String atributo, String value) throws HelpDeskException {
		Method m;
		try {
			m = objeto.getClass().getDeclaredMethod("set" + corrigeStringAtributo(atributo),
					Class.forName("java.lang.String"));
			m.invoke(objeto, value);
		} catch (InvocationTargetException e) {
			throw new HelpDeskException(MsgErros.VALOR_DE_ATRIBUTO_INVALIDO.msg(value, atributo));
		} catch (Exception e) {
			throw new HelpDeskException(MsgErros.ATRIBUTO_INVALIDO.msg(atributo));
		}
	}

	/**
	 * Torna a primeira letra de uma palavra em maiuscula
	 * 
	 * @param atributo
	 *            A string que deve ser modificada.
	 * @return Uma nova String equivalente a primeira, mas com a primeira letra
	 *         maiuscula.
	 */
	private static String corrigeStringAtributo(String atributo) {
		String primeiraLetra = String.valueOf(atributo.charAt(0));
		return atributo.replaceFirst(primeiraLetra, primeiraLetra.toUpperCase());
	}

}
