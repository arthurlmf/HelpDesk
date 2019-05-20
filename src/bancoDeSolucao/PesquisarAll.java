package bancoDeSolucao;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 
 * <b>HelpDeskTRE</b><br>
 * <br>
 * 
 * 
 * Classe que representa uma pesquisa em todo o banco de solucao
 * 
 * 
 * @author Arthur Farias 
 */
public class PesquisarAll {

	private BaseConhecimento baseConhec;

	public PesquisarAll() {
		baseConhec = new BaseConhecimento();
	}

	/**
	 * Realiza a pesquisa em todo o banco de solucao
	 * 
	 * @param keyWord
	 *            a palavra chave da pesquisa
	 * @return o restultado da pesquisa na BaseConhecimento
	 */
	public BaseConhecimento pesquisar(String keyWord) {
		LinkedHashSet<Artigo> listaArtigo = pesquisarArtigos(keyWord);
		LinkedHashSet<FAQ> listaFAQ = pesquisarFAQs(keyWord);
		LinkedHashSet<Solucao> listaSolucao = pesquisarSolucoes(keyWord);

		baseConhec.setListaArtigo(listaArtigo);
		baseConhec.setListaFAQ(listaFAQ);
		baseConhec.setListaSolucao(listaSolucao);

		return baseConhec;
	}
	
	public BaseConhecimento pesquisar(String keyWord, boolean artigo,
			boolean faq, boolean solucao) {
		if(artigo){
			LinkedHashSet<Artigo> listaArtigo = pesquisarArtigos(keyWord);
			baseConhec.setListaArtigo(listaArtigo);
		}
		if(faq){
			LinkedHashSet<FAQ> listaFAQ = pesquisarFAQs(keyWord);
			baseConhec.setListaFAQ(listaFAQ);
		}
		if(solucao){
			LinkedHashSet<Solucao> listaSolucao = pesquisarSolucoes(keyWord);
			baseConhec.setListaSolucao(listaSolucao);
		}
		return baseConhec;
	}

	private LinkedHashSet<Solucao> pesquisarSolucoes(String keyWord) {
		PesquisaSolucao pesquisarTexto = new PesquisaSolucao(keyWord);
		List<Solucao> listaTexto = pesquisarTexto.pesquisar();

		LinkedHashSet<Solucao> lista = new LinkedHashSet<Solucao>();
		lista.addAll(listaTexto);

		return lista;
	}

	private LinkedHashSet<FAQ> pesquisarFAQs(String keyWord) {
		PesquisaFAQ pesquisarPergunta = new PesquisaFAQ(keyWord, "", "","");
		List<FAQ> listaPergunta = pesquisarPergunta.pesquisar();

		PesquisaFAQ pesquisarResposta = new PesquisaFAQ("", keyWord, "","");
		List<FAQ> listaResposta = pesquisarResposta.pesquisar();
		
		PesquisaFAQ pesquisarTipo = new PesquisaFAQ("", "", keyWord,"");
		List<FAQ> listaTipo = pesquisarTipo.pesquisar();

		PesquisaFAQ pesquisarSubtipo = new PesquisaFAQ("", "", "",keyWord);
		List<FAQ> listaSubtipo = pesquisarSubtipo.pesquisar();

		LinkedHashSet<FAQ> lista = new LinkedHashSet<FAQ>();
		addListaFaq(lista, listaPergunta);
		addListaFaq(lista, listaResposta);
		addListaFaq(lista, listaTipo);
		addListaFaq(lista, listaSubtipo);

		return lista;
	}

	private void addListaFaq(LinkedHashSet<FAQ> listaAtual, List<FAQ> lista) {
		Iterator it = lista.iterator();
		while(it.hasNext()){
			FAQ faq = (FAQ) it.next();
			boolean contem = false;
			Iterator itAtual = listaAtual.iterator();
			while(itAtual.hasNext()){
				FAQ faqAtual = (FAQ) itAtual.next();
				if (faq.getIdFaq() == faqAtual.getIdFaq()){
					contem = true;
				}
			}
			if(!contem){
				listaAtual.add(faq);
			}
		}
	}

	private LinkedHashSet<Artigo> pesquisarArtigos(String keyWord) {

		PesquisaArtigo pesquisarTitulo = new PesquisaArtigo(keyWord, "", "","");
		List<Artigo> listaTitulo = pesquisarTitulo.pesquisar();

		PesquisaArtigo pesquisarTexto = new PesquisaArtigo("", keyWord, "","");
		List<Artigo> listaTexto = pesquisarTexto.pesquisar();
		
		PesquisaArtigo pesquisarTipo = new PesquisaArtigo("","" , keyWord,"");
		
		List<Artigo> listaTextoTipo = pesquisarTipo.pesquisar();
		
		PesquisaArtigo pesquisarSubtipo = new PesquisaArtigo("", "", "",keyWord);
		List<Artigo> listaTextoSubtipo = pesquisarSubtipo.pesquisar();

		LinkedHashSet<Artigo> lista = new LinkedHashSet<Artigo>();
		addListaArtigo(lista, listaTitulo);
		addListaArtigo(lista, listaTexto);
		addListaArtigo(lista, listaTextoTipo);
		addListaArtigo(lista, listaTextoSubtipo);

		return lista;
	}
	
	private void addListaArtigo(LinkedHashSet<Artigo> listaAtual, List<Artigo> lista) {
		Iterator it = lista.iterator();
		while(it.hasNext()){
			Artigo artigo = (Artigo) it.next();
			boolean contem = false;
			Iterator itAtual = listaAtual.iterator();
			while(itAtual.hasNext()){
				Artigo artigoAtual = (Artigo) itAtual.next();
				if (artigo.getIdArtigo() == artigoAtual.getIdArtigo()){
					contem = true;
				}
			}
			if(!contem){
				listaAtual.add(artigo);
			}
		}
	}

	
	
}
