package bancoDeSolucao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import util.HelpDeskUtil;
import dao.ArtigoDAO;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma pesquisa em artigo 
 * 
 * 
 * @author Arthur Farias 
 */
public class PesquisaArtigo {

	private String titulo;
	
	private String texto;
	
	private String tipo;
	
	private String subtipo;
	
	public String getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}
	/**
	 * 
	 * Construtor da classe
	 */
	public PesquisaArtigo(){
		
	}
	/**
	 * Pesquisa em artigos
	 * @param titulo o titulo do artigo
	 * @param texto o texto do artigo
	 * @param tipo o tipo do artigo
	 * @return uma List de Artigo
	 */
	public PesquisaArtigo(String titulo, String texto, String tipo,String subtipo){
		this.subtipo = subtipo;
		setTitulo(titulo);
		setTexto(texto);
		setTipo(tipo);
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
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Returns the titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo The titulo to set.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * Pesquisa todos os arigos
	 * @return a List de Artigo
	 */
	
	public List<Artigo> pesquisar(){
		Session sess = ArtigoDAO.getInstance().openSession();
		Criteria consulta = sess.createCriteria(Artigo.class);
		if (!HelpDeskUtil.isNullOrVazio(getTitulo())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"titulo",getTitulo());
		}
		if (!HelpDeskUtil.isNullOrVazio(getTexto())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"texto",getTexto());
		}
		if (!HelpDeskUtil.isNullOrVazio(getTipo())){
			//consulta.add(Expression.like("tipo", tipo));
			consulta  = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"tipo",getTipo());
		}
		if (!HelpDeskUtil.isNullOrVazio(getSubtipo())){
			//consulta.add(Expression.like("subtipo", subtipo));
			consulta  = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"subtipo",getSubtipo());
		}
		List<Artigo> resultado = consulta.list();
		return resultado;
	}
}
