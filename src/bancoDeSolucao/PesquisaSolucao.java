package bancoDeSolucao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import util.HelpDeskUtil;
import dao.ArtigoDAO;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma pesquisa em solucoes 
 * 
 * 
 * @author Arthur Farias 
 */
public class PesquisaSolucao {

	private String texto;
	/**
	 * 
	 * Construtor da classe
	 */
	public PesquisaSolucao(){
		
	}
	/**
	 * 
	 * Construtor da classe
	 * @param texto o texto da pesquisa
	 */
	public PesquisaSolucao(String texto){
		setTexto(texto);
	}

	/**
	 * @return Returns the texto.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto The texto to set.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * Pesquisa as solucoes
	 * @return lista de solucoes
	 */
	
	public List<Solucao> pesquisar(){
		Session sess = ArtigoDAO.getInstance().openSession();
		Criteria consulta = sess.createCriteria(Solucao.class);
		if (!HelpDeskUtil.isNullOrVazio(getTexto())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"descricao",getTexto());
		}
		List<Solucao> resultado = consulta.list();
		return resultado;
	}
}
