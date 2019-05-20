package bancoDeSolucao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import sistema.GerenciadorDeChamado;
import transacao.Intervencao;
import transacao.Transacao;
import chamado.Chamado;


/**
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa uma solucao para a base de conhecimento
 * 
 * 
 * @author Arthur Farias 
 */

public class Solucao {

	private int idSolucao;

	private String descricao;

	private Chamado chamado;

	/**
	 * 
	 * Construtor da classe
	 */
	public Solucao() {

	}

	/**
	 * 
	 * Construtor da classe
	 * 
	 * @param chamado
	 *            o chamado
	 */
	public Solucao(Chamado chamado) {
		setChamado(chamado);
		criaDescricao();
	}

	/**
	 * @return Returns the idChamado.
	 */
	public Chamado getChamado() {
		return chamado;
	}

	/**
	 * @param idChamado
	 *            The idChamado to set.
	 */
	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	/**
	 * @return Returns the idSolucao.
	 */
	public int getIdSolucao() {
		return idSolucao;
	}

	/**
	 * @param idSolucao
	 *            The idSolucao to set.
	 */
	public void setIdSolucao(int idSolucao) {
		this.idSolucao = idSolucao;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns uma string.
	 */
	public String toString() {
		return getIdSolucao() + "";
	}

	/**
	 * Cria a descricao da solucao atraves das intervencoes
	 * 
	 */
	private synchronized void criaDescricao() {
		String resultado = "";
		List<Transacao> lista = GerenciadorDeChamado.getInstance().getTransacaoParaTecnico(this.chamado.getIdChamado());
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			Transacao transacao = (Transacao) it.next();
			if (transacao.getTipo().equals(Transacao.INTERVENCAO) && transacao.isVisivel()) {
				Intervencao intervencao = (Intervencao) transacao;
				resultado += intervencao.getDescricao()+"--##--";
			}
		}
		setDescricao(resultado);
	}
	
	/**
	 * Recupaa a descricao das intervenções em uma lista
	 * @return
	 */
	public List<String> getListaDescricao(){
		List<String> lista = new LinkedList<String>();
		StringTokenizer stK = new StringTokenizer(getDescricao(),"--##--");
		while(stK.hasMoreTokens()){
			lista.add(stK.nextToken());
		}
		return lista;
		
	}
	/**
	 * Verifica se uma solucao é igual a outra
	 * 
	 * @param other
	 * @return true, se verdadeiro, false caso contrário.
	 */
	public boolean equals(Object objeto) {
		try {
			Solucao other = (Solucao) objeto;
			if (this.getIdSolucao() == other.getIdSolucao()
					&& this.getChamado().equals(other.getChamado())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
