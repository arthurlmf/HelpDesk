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
 * Classe que representa uma pesquisa em FAQ 
 * 
 * 
 * @author Arthur Farias 
 */
public class PesquisaFAQ {

	private String pergunta;
	
	private String resposta;
	
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
	public PesquisaFAQ(){
		
	}
	/**
	 * 
	 * Construtor da classe
	 * @param pergunta a pergunta da faq
	 * @param resposta a resposta da faq
	 * @param tipo o tipo da faq
	 */
	public PesquisaFAQ(String pergunta, String resposta, String tipo,String subtipo){
		this.subtipo = subtipo;
		setPergunta(pergunta);
		setResposta(resposta);
		setTipo(tipo);
	}

	/**
	 * @return Returns the resposta.
	 */
	public String getResposta() {
		return resposta;
	}

	/**
	 * @param resposta The resposta to set.
	 */
	public void setResposta(String resposta) {
		this.resposta = resposta;
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
	 * @return Returns the pergunta.
	 */
	public String getPergunta() {
		return pergunta;
	}

	/**
	 * @param pergunta The pergunta to set.
	 */
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	/**
	 * Pesquisa todos as FAQ's
	 * @return a List de FAQ
	 */
	public List<FAQ> pesquisar(){
		Session sess = ArtigoDAO.getInstance().openSession();
		Criteria consulta = sess.createCriteria(FAQ.class);
		if (!HelpDeskUtil.isNullOrVazio(getPergunta())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"pergunta",getPergunta());
		}
		if (!HelpDeskUtil.isNullOrVazio(getResposta())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"resposta",getResposta());
		}
		if (!HelpDeskUtil.isNullOrVazio(getTipo())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"tipo",getTipo());
		}
		if (!HelpDeskUtil.isNullOrVazio(getSubtipo())){
			consulta = GerenciadorDaBaseDeConhecimento.pesquisarLikeGoogle(consulta,"subtipo",getSubtipo());
		}
		List<FAQ> resultado = consulta.list();
		return resultado;
	}
}
