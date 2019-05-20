package bancoDeSolucao;

import java.util.LinkedHashSet;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma base de conhecimento para o sistema do helpdesk
 * 
 * 
 * @author Arthur Farias 
 */
public class BaseConhecimento {

	private LinkedHashSet<Artigo> listaArtigo;
	private LinkedHashSet<Solucao> listaSolucao;
	private LinkedHashSet<FAQ> listaFAQ;
	/**
	 * 
	 * Construtor da classe
	 */
	public BaseConhecimento(){
		listaArtigo = new LinkedHashSet<Artigo>();
		listaSolucao = new LinkedHashSet<Solucao>();
		listaFAQ = new LinkedHashSet<FAQ>();
	}
	
	/**
	 * Retorna uma representacao em string para a classe
	 */
	public String toString(){
		return "{"+(getListaArtigo()) +"," + (getListaFAQ()) + ","+(getListaSolucao())+"}" ;
	}


	/**
	 * @return Returns the listaArtigo.
	 */
	public LinkedHashSet<Artigo> getListaArtigo() {
		return listaArtigo;
	}


	/**
	 * @param listaArtigo The listaArtigo to set.
	 */
	public void setListaArtigo(LinkedHashSet<Artigo> listaArtigo) {
		this.listaArtigo = listaArtigo;
	}


	/**
	 * @return Returns the listaFAQ.
	 */
	public LinkedHashSet<FAQ> getListaFAQ() {
		return listaFAQ;
	}


	/**
	 * @param listaFAQ The listaFAQ to set.
	 */
	public void setListaFAQ(LinkedHashSet<FAQ> listaFAQ) {
		this.listaFAQ = listaFAQ;
	}


	/**
	 * @return Returns the listaSolucao.
	 */
	public LinkedHashSet<Solucao> getListaSolucao() {
		return listaSolucao;
	}


	/**
	 * @param listaSolucao The listaSolucao to set.
	 */
	public void setListaSolucao(LinkedHashSet<Solucao> listaSolucao) {
		this.listaSolucao = listaSolucao;
	}
	
}
