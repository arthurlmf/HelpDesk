package bancoDeSolucao;

import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import sistema.GerenciadorDeChamado;
import util.HelpDeskReflexao;
import util.MsgErros;
import chamado.Chamado;
import dao.ArtigoDAO;
import dao.FaqDAO;
import dao.SolucaoDAO;
import excecoes.HelpDeskException;

/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que gerencia toda a parte de base de conhecimento do sistema 
 * 
 * 
 * @author Arthur Farias 
 */
public class GerenciadorDaBaseDeConhecimento {
	
	
	public static GerenciadorDaBaseDeConhecimento instance = null;

	public static GerenciadorDaBaseDeConhecimento getInstance() {
		if (instance == null) {
			instance = new GerenciadorDaBaseDeConhecimento();
		}
		return instance;
	}

	
	/**
	 * 
	 * Construtor da classe
	 */
	 private GerenciadorDaBaseDeConhecimento(){
		
	}
	/**
	 * Cria uma solucao para o chamdo
	 * @param  o chamado
	 * @return 
	 * @throws HelpDeskException
	 */
	public synchronized String criaSolucao(Chamado chamado ) throws HelpDeskException {
		Solucao solucao = new Solucao(chamado);
				
		
		SolucaoDAO solucaoDAO = SolucaoDAO.getInstance();
		Integer id = (Integer) solucaoDAO.insert(solucao);
		return id.toString();
	}
	/**
	 * Cria uma faq
	 * @param pergunta pergunta da faq
	 * @param resposta resposta da faq
	 * @param tipo tipo da faq
	 * @return
	 * @throws HelpDeskException
	 */
	public synchronized String criarFaq(String pergunta, String resposta, String tipo,String subtipo) throws HelpDeskException {
		FAQ faq = new FAQ(pergunta.trim(), resposta.trim(), tipo,subtipo); 
		FaqDAO faqDAO = FaqDAO.getInstance();
		return faqDAO.insert(faq).toString();
	}
	/**
	 * Cria um Artigo
	 * @param titulo titulo do artigo
	 * @param texto texto do artigo
	 * @param tipo tipo do artigo
	 * @return
	 * @throws HelpDeskException
	 */
	public synchronized String criarArtigo(String titulo, String texto, String tipo,String subtipo) throws HelpDeskException {
		Artigo artigo = new Artigo(titulo.trim(), texto.trim(), tipo,subtipo);
		ArtigoDAO artigoDAO = ArtigoDAO.getInstance();
		return artigoDAO.insert(artigo).toString();
	}
	/**
	 * Retorna a solucao
	 * @param idSolucao o id da solucao
	 * @return a solucao
	 * @throws HelpDeskException
	 */
	public synchronized Solucao getSolucao(String idSolucao) throws HelpDeskException {
		Solucao solucao = SolucaoDAO.getInstance().read(Integer.parseInt(idSolucao));
		solucao.setChamado(GerenciadorDeChamado.getInstance().getChamado(Integer.parseInt(idSolucao)));
		if(solucao==null){
			throw new HelpDeskException(MsgErros.OBJ_NOT_FOUND.msg("solução"));
		}
		return solucao;
	}
	/**
	 * Retorna a faq
	 * @param idFaq o id da faq
	 * @return a FAQ
	 * @throws HelpDeskException
	 */
	public synchronized FAQ getFaq(String idFaq) throws HelpDeskException {
		FAQ faq= FaqDAO.getInstance().read(Integer.parseInt(idFaq));
		if(faq==null){
			throw new HelpDeskException(MsgErros.OBJ_NOT_FOUND.msg("FAQ"));
		}
		return faq;
	}
	/**
	 * Retorna o artigo em String
	 * @param idArtigoFaq o id do artigo
	 * @return o Artigo
	 * @throws HelpDeskException
	 */
	public synchronized Artigo getArtigo(String idArtigoFaq) throws HelpDeskException {
		Artigo artigo= ArtigoDAO.getInstance().read(Integer.parseInt(idArtigoFaq));
		if(artigo==null){
			throw new HelpDeskException(MsgErros.OBJ_NOT_FOUND.msg("artigo"));
		}
		return artigo;
	}
	/**
	 * Faz uma pesquisa nos moldes da pesquisa do google
	 * @param consulta a consluta
	 * @param variavel a string que sera usada na busca
	 * @param diferenciador  o diferenciador das consultas
	 * @return o Criteria
	 */
	public synchronized static Criteria pesquisarLikeGoogle(Criteria consulta,String variavel, String diferenciador){
		StringTokenizer tokenizer = new StringTokenizer(diferenciador);
		while(tokenizer.hasMoreTokens()){
			String token = "%"+tokenizer.nextToken()+"%";
			consulta.add(Expression.like(variavel, token).ignoreCase());
		}	
		return consulta;		
	}
	/**
	 * Pesquisa em artigos
	 * @param titulo o titulo do artigo
	 * @param texto o texto do artigo
	 * @param tipo o tipo do artigo
	 * @return uma List de Artigo
	 */
	public synchronized List<Artigo> pesquisarArtigo(String titulo, String texto, String tipo,String subtipo) {
		PesquisaArtigo pesquisaArtigo = new PesquisaArtigo(titulo, texto, tipo,subtipo);
		return pesquisaArtigo.pesquisar();
	}
	/**
	 * Pesquisa em faqs
	 * @param pergunta a pergunta da faq
	 * @param resposta a resposta da faq
	 * @param tipo o tipo da faq
	 * @return uma List de FAQ
	 */
	public synchronized List<FAQ> pesquisarFAQ(String pergunta, String resposta, String tipo,String subtipo) {
		PesquisaFAQ pesquisarFAQ = new PesquisaFAQ(pergunta, resposta, tipo,subtipo);
		return pesquisarFAQ.pesquisar();
	}
	

	/**
	 * Zerar todas as Soluções Cadastradas
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public synchronized void zeraSolucao() throws HelpDeskException {
		SolucaoDAO.getInstance().removeAll();
	}

	/**
	 * Zerar todas as Faqs cadastradas
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public  synchronized void zeraFaq() throws HelpDeskException {
		FaqDAO.getInstance().removeAll();
	}
	
	/**
	 * Zerar todos os artigos cadastrados
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public synchronized void zeraArtigo() throws HelpDeskException {
		ArtigoDAO.getInstance().removeAll();
	}


	/**
	 * Realiza a pesquisa de uma palvavra chave em todo o 
	 * banco de solucao
	 * @param keywWor
	 * @return 
	 */
	public synchronized BaseConhecimento pesquisarAll(String keywWor) {
		PesquisarAll pesquisador = new PesquisarAll();
		return pesquisador.pesquisar(keywWor);
	}
	
	public BaseConhecimento pesquisarAll(String palavraChave, boolean artigo,
			boolean faq, boolean solucao) {
		PesquisarAll pesquisador = new PesquisarAll();
		return pesquisador.pesquisar(palavraChave,artigo,faq,solucao);
	}
	
	/**
	 * Altera o atributo de um artigo
	 * @param idArtigo o id do artigo
	 * @param nomeAtributo o nome do atributo
	 * @param novoAtributo o novo atributo
	 * @throws HelpDeskException 
	 */
	public synchronized void alterarAtributoArtigo(String idArtigo, String nomeAtributo, String novoAtributo) throws HelpDeskException {
		Artigo artigo = getArtigo(idArtigo);
		HelpDeskReflexao.setAtributo(artigo, nomeAtributo, novoAtributo);
		ArtigoDAO.getInstance().update(artigo);
	}
	/**
	 * Altera o atributo de um FAQ
	 * @param idArtigo o id do FAQ
	 * @param nomeAtributo o nome do atributo
	 * @param novoAtributo o novo atributo
	 * @throws HelpDeskException 
	 * @throws HelpDeskException 
	 */
	public synchronized void alterarAtributoFAQ(String idFAQ, String nomeAtributo, String novoAtributo) throws HelpDeskException {
		FAQ faq = getFaq(idFAQ);
		HelpDeskReflexao.setAtributo(faq, nomeAtributo, novoAtributo);
		FaqDAO.getInstance().update(faq);
		
	}
	public synchronized void zerarBase() throws HelpDeskException {
		zeraArtigo();
		zeraFaq();
		zeraSolucao();		
	}


	

}
