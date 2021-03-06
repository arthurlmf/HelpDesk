package sistema;

import integracaoTRE.UnidadeBDTRE;

import java.util.List;

import relacionamento.ChamadoAtendido;
import tipo.PrimaryKeySubTipo;
import tipo.PrimaryKeyTipo;
import tipo.Subtipo;
import tipo.Tipo;
import transacao.Transacao;
import transacao.Visita;
import usuario.Tecnico;
import usuario.Unidade;
import usuario.Usuario;
import bancoDeSolucao.Artigo;
import bancoDeSolucao.BaseConhecimento;
import bancoDeSolucao.FAQ;
import bancoDeSolucao.GerenciadorDaBaseDeConhecimento;
import bancoDeSolucao.Solucao;
import bdTRE.ConexaoBDTRE;
import bdTRE.LoginLDAP;
import chamado.Chamado;
import config.Config;
import dao.AtendimentoDAO;
import dao.ChamadoAtendidoDAO;
import dao.ChamadoDAO;
import dao.GerenteDeUnidadeDAO;
import dao.SubtipoDAO;
import dao.TecnicoDAO;
import dao.TecnicoDeUnidadeDAO;
import dao.TipoDAO;
import dao.TransacaoDAO;
import dao.TransacaoDeChamadoDAO;
import dao.UsuarioDAO;
import excecoes.HelpDeskException;

/**
 * Classe que representa o Sistema, integrando todos os modulos
 * @author arthur.farias
 *
 */
public class Sistema {

	public GerenciadorDeUnidade gerenciadorUnidade;
	public GerenciadorDeTecnico gerenciadorTecnico;
	public GerenciadorDeTipo gerenciadorTipo;
	public GerenciadorDeRelacionamento gerenciadorRelacionamento;
	public GerenciadorDeChamado gerenciadorChamado;
	public GerenciadorDeUsuario gerenciadorUsuario;
	public GerenciadorDaBaseDeConhecimento base;
	public ConexaoBDTRE bdTRE;
	public GerenciadorConfig gerenciadorConfig;

	public Sistema() {
		this.gerenciadorUnidade = GerenciadorDeUnidade.getInstance();
		this.gerenciadorTecnico = GerenciadorDeTecnico.getInstance().getInstance();
		this.gerenciadorTipo = GerenciadorDeTipo.getInstance();
		this.gerenciadorRelacionamento = GerenciadorDeRelacionamento.getInstance();
		this.gerenciadorChamado = GerenciadorDeChamado.getInstance().getInstance();
		this.gerenciadorUsuario = GerenciadorDeUsuario.getInstance();
		this.base = GerenciadorDaBaseDeConhecimento.getInstance();
		this.bdTRE = new ConexaoBDTRE();
		gerenciadorConfig = GerenciadorConfig.getInstance();
	}

	public String criarUnidade(String idUnidade, String nome, boolean isSuporte) throws HelpDeskException {
		return gerenciadorUnidade.criarUnidade(idUnidade, nome, isSuporte);
	}

	public void alterarUnidadeNome(String idUnidade, String novoNome) throws HelpDeskException {
		gerenciadorUnidade.alterarUnidadeNome(idUnidade, novoNome);
	}

	public String criarTecnico(String idTecnico, String idUnidade) throws HelpDeskException {
		return gerenciadorTecnico.criarTecnico(idTecnico, idUnidade);
	}

	public String criarTecnicoAdministrador(String idTecnico, String nome) throws HelpDeskException {
		return gerenciadorTecnico.criarTecnicoAdministrador(idTecnico, nome);
	}

	public Unidade getUnidade(String idUnidade) throws HelpDeskException {
		return gerenciadorUnidade.getUnidade(idUnidade);
	}

	public boolean isSuporte(String idUnidade) throws HelpDeskException {
		return gerenciadorUnidade.isUnidadeSuporte(idUnidade);
	}

	public void setSuporte(String idUnidade, boolean isSuporte) throws HelpDeskException {
		if (gerenciadorUnidade.isNecessarioAtualizar(idUnidade, isSuporte)) {
			removerSuporte(idUnidade);
			gerenciadorUnidade.setSuporte(idUnidade, isSuporte);
		} else {
			gerenciadorUnidade.setSuporte(idUnidade, isSuporte);
		}
	}

	public void removerSuporte(String idUnidadeSuporte) throws HelpDeskException {
		gerenciadorRelacionamento.removerSuporte(idUnidadeSuporte);
	}

	public void removerSuporte(String idUnidadeSolicitante, String idUnidadeSuporte) throws HelpDeskException {
		gerenciadorRelacionamento.removerSuporte(idUnidadeSolicitante, idUnidadeSuporte);

	}

	public void removerUnidade(String idUnidade) throws HelpDeskException {
		gerenciadorUnidade.removerUnidade(idUnidade);
	}

	public void zerarSistema() throws HelpDeskException {
		// gerenciadorUnidade.zerar();
		base.zerarBase();
		ChamadoDAO.getInstance().removeAll();
		ChamadoAtendidoDAO.getInstance().removeAll();
		new UsuarioDAO().removeAll();
		TecnicoDAO.getInstance().removeAll();
		TecnicoDeUnidadeDAO.getInstance().removeAll();

		AtendimentoDAO.getInstance().removeAll();

		SubtipoDAO.getInstance().removeAll();
		TipoDAO.getInstance().removeAll();
		SubtipoDAO.getInstance().removeAll();

		TransacaoDAO.getInstance().removeAll();
		// ResponsavelDoChamadoDAO.getInstance().removeAll();
		GerenteDeUnidadeDAO.getInstance().removeAll();
		TransacaoDeChamadoDAO.getInstance().removeAll();

	}

	public String getSuportes(String idUnidade) throws HelpDeskException {
		return gerenciadorRelacionamento.getSuportes(idUnidade);
	}

	public List<Unidade> getUnidadesSuportes(String idUnidadeSolicitante) throws HelpDeskException {
		return gerenciadorRelacionamento.getUnidadesSuportes(idUnidadeSolicitante);
	}

	public PrimaryKeySubTipo criarSubtipo(String idUnidade, String nomeTipo, String nomeSubtipo, boolean patrimonio,
			int prazoAtendimento) throws HelpDeskException {
		return gerenciadorTipo.criarSubtipo(idUnidade, nomeTipo, nomeSubtipo, patrimonio, prazoAtendimento);
	}

	public void relacionarTecnicoComUnidade(String tecnico, String unidade) throws HelpDeskException {
		gerenciadorRelacionamento.relacionarTecnicoComUnidade(tecnico, unidade);
	}

	public void desrelacionarTecnicoComUnidade(String idTecnico) throws HelpDeskException {
		gerenciadorRelacionamento.desrelacionarTecnicoComUnidade(idTecnico);
	}

	public void adicionaSuporte(String idUnidadeSolicitante, String idUnidadeSuporte) throws HelpDeskException {
		gerenciadorRelacionamento.adicionaSuporte(idUnidadeSolicitante, idUnidadeSuporte);
	}

	public int solicitarChamado(String descricao, String tipo, String subtipo, String idPatrimonio,
			String unidadeSolicitante, String unidadeSuporte) throws NumberFormatException, HelpDeskException {
		return gerenciadorChamado.solicitarChamado(descricao.trim(), tipo, subtipo, idPatrimonio, unidadeSolicitante,
				unidadeSuporte);

	}

	public List<Tecnico> getTecnicos(String unidade) throws HelpDeskException {
		return gerenciadorTecnico.getTecnicos(unidade);
	}

	public String getSolicitantes(String idUnidadeSuporte) throws HelpDeskException {
		return gerenciadorRelacionamento.getSolicitantes(idUnidadeSuporte);
	}

	public List<Unidade> getSolicitantesUnidade(String idUnidadeSuporte) throws HelpDeskException {
		return gerenciadorRelacionamento.getSolicitantesUnidade(idUnidadeSuporte);
	}

	public boolean isAuthenticatedUser(String idUsuario, String senha) throws HelpDeskException {
		boolean usarLDAP;
		try {
			usarLDAP = Boolean.parseBoolean(gerenciadorConfig.getConfig(Config.USAR_LOGIN_LDAP).getValor());
		} catch (Exception e) {
			usarLDAP = false;
		}
		if (gerenciadorUsuario.isAdministrador(idUsuario)) {
			return gerenciadorUsuario.isAuthenticatedAdministrator(idUsuario, senha);
		}

		if (usarLDAP) {
			return gerenciadorUsuario.isAuthenticatedUser(idUsuario) && LoginLDAP.getInstance().login(idUsuario, senha);
		}
		return gerenciadorUsuario.isAuthenticatedUser(idUsuario);
	}

	public Usuario getUsuario(String idUsuario) throws HelpDeskException {
		return gerenciadorUsuario.getUsuario(idUsuario);
	}

	public List<Unidade> getAllUnidades() {
		return gerenciadorUnidade.getAllUnidades();
	}

	public List<Tipo> getAllTipos() {
		return gerenciadorTipo.getAllTipos();
	}

	public List<Subtipo> getAllSubtipos() {
		return gerenciadorTipo.getAllSubtipos();
	}

	public Chamado getChamado(int idChamado) throws HelpDeskException {
		return gerenciadorChamado.getChamado(idChamado);
	}

	public ChamadoAtendido getChamadoAtendido(int idChamado) {
		return gerenciadorChamado.getChamadoAtendido(idChamado);
	}

	public Tecnico getTecnico(String idTecnico) throws HelpDeskException {
		return gerenciadorTecnico.getTecnico(idTecnico);
	}

	public void removerTecnico(String idTecnico) throws HelpDeskException {
		gerenciadorTecnico.removerTecnico(idTecnico);
	}

	public void alterarTecnicoNome(String idTecnico, String novoNome) throws HelpDeskException {
		gerenciadorTecnico.alterarTecnicoNome(idTecnico, novoNome);
	}

	public Unidade getUnidadeDoTecnico(String idTecnico) throws HelpDeskException {
		return gerenciadorRelacionamento.getUnidadeDoTecnico(idTecnico);
	}

	public List<Tipo> getTiposDaUnidade(String idUnidade) throws HelpDeskException {
		return gerenciadorTipo.getTiposDaUnidade(idUnidade);
	}

	public void setTipo(String idUnidade, String nomeTipo, String newTipo) throws HelpDeskException {
		gerenciadorTipo.setTipo(idUnidade, nomeTipo, newTipo);
	}

	public void removeTipo(String idUnidade, String nomeTipo) throws HelpDeskException {
		gerenciadorTipo.removeTipo(idUnidade, nomeTipo);

	}

	public List<Subtipo> getSubtiposDeTipo(String idUnidade, String tipo) throws HelpDeskException {
		return gerenciadorTipo.getSubtiposDeTipo(idUnidade, tipo);
	}

	public List<Chamado> getChamadosTecnico(String idUsuario) {
		return gerenciadorTecnico.getChamadosTecnico(idUsuario);
	}

	public List<Chamado> getChamadosUnidade(String idUsuario) {
		return gerenciadorChamado.getChamadosUnidade(idUsuario);
	}

	public List<Chamado> getChamadosSolicitados(String idUsuario) {
		return gerenciadorChamado.getChamadosSolicitados(idUsuario);
	}

	public List<Chamado> getChamadosRepassados(String idUnidade) throws HelpDeskException {
		return gerenciadorRelacionamento.getChamadosRepassados(idUnidade);
	}

	public String criaSolucao(Chamado chamado) throws HelpDeskException {
		return this.base.criaSolucao(chamado);
	}

	/**
	 * Cria uma faq
	 * 
	 * @param pergunta pergunta da faq
	 * @param resposta resposta da faq
	 * @param tipo     tipo da faq
	 * @return
	 * @throws HelpDeskException
	 */
	public String criarFaq(String pergunta, String resposta, String tipo, String subtipo) throws HelpDeskException {
		return base.criarFaq(pergunta, resposta, tipo, subtipo);

	}

	/**
	 * Cria um Artigo
	 * 
	 * @param titulo titulo do artigo
	 * @param texto  texto do artigo
	 * @param tipo   tipo do artigo
	 * @return
	 * @throws HelpDeskException
	 */
	public String criarArtigo(String titulo, String texto, String tipo, String subtipo) throws HelpDeskException {
		return base.criarArtigo(titulo, texto, tipo, subtipo);

	}

	/**
	 * Retorna a solucao
	 * 
	 * @param idSolucao o id da solucao
	 * @return a solucao
	 * @throws HelpDeskException
	 */
	public Solucao getSolucao(String idSolucao) throws HelpDeskException {
		return base.getSolucao(idSolucao);

	}

	/**
	 * Retorna a faq
	 * 
	 * @param idFaq o id da faq
	 * @return a FAQ
	 * @throws HelpDeskException
	 */
	public FAQ getFaq(String idFaq) throws HelpDeskException {
		return base.getFaq(idFaq);

	}

	/**
	 * Retorna o artigo em String
	 * 
	 * @param idArtigo o id do artigo
	 * @return o Artigo
	 * @throws HelpDeskException
	 */
	public Artigo getArtigo(String idArtigo) throws HelpDeskException {
		return base.getArtigo(idArtigo);

	}

	/**
	 * Pesquisa em artigos
	 * 
	 * @param titulo o titulo do artigo
	 * @param texto  o texto do artigo
	 * @param tipo   o tipo do artigo
	 * @return uma List de Artigo
	 */
	public List<Artigo> pesquisarArtigo(String titulo, String texto, String tipo, String subtipo) {
		return base.pesquisarArtigo(titulo, texto, tipo, subtipo);

	}

	/**
	 * Pesquisa em faqs
	 * 
	 * @param pergunta a pergunta da faq
	 * @param resposta a resposta da faq
	 * @param tipo     o tipo da faq
	 * @return uma List de FAQ
	 */
	public List<FAQ> pesquisarFAQ(String pergunta, String resposta, String tipo, String subtipo) {
		return base.pesquisarFAQ(pergunta, resposta, tipo, subtipo);

	}

	/**
	 * Zerar todas as Solu��es Cadastradas
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void zeraSolucao() throws HelpDeskException {
		base.zeraSolucao();
	}

	/**
	 * Zerar todas as Faqs cadastradas
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void zeraFaq() throws HelpDeskException {
		base.zeraFaq();
	}

	/**
	 * Zerar todos os artigos cadastrados
	 * 
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void zeraArtigo() throws HelpDeskException {
		base.zeraArtigo();
	}

	/**
	 * Realiza a pesquisa de uma palvavra chave em todo o banco de solucao
	 * 
	 * @param keywWor
	 * @return
	 */
	public BaseConhecimento pesquisarAll(String keywWor) {
		return base.pesquisarAll(keywWor);
	}

	/**
	 * Altera o atributo de um artigo
	 * 
	 * @param idArtigo     o id do artigo
	 * @param nomeAtributo o nome do atributo
	 * @param novoAtributo o novo atributo
	 * @throws HelpDeskException
	 */
	public void alterarAtributoArtigo(String idArtigo, String nomeAtributo, String novoAtributo)
			throws HelpDeskException {
		base.alterarAtributoArtigo(idArtigo, nomeAtributo, novoAtributo);
	}

	/**
	 * Altera o atributo de um FAQ
	 * 
	 * @param idArtigo     o id do FAQ
	 * @param nomeAtributo o nome do atributo
	 * @param novoAtributo o novo atributo
	 * @throws HelpDeskException
	 * @throws HelpDeskException
	 */
	public void alterarAtributoFAQ(String idFAQ, String nomeAtributo, String novoAtributo) throws HelpDeskException {
		base.alterarAtributoFAQ(idFAQ, nomeAtributo, novoAtributo);

	}

	public int encaminharChamado(int idChamado, String tecnicoDestino) throws NumberFormatException, HelpDeskException {
		return gerenciadorChamado.encaminharChamado(idChamado, tecnicoDestino);
	}

	public void aceitarChamado(int idChamado, String tecnicoResponsavel) throws HelpDeskException {
		gerenciadorChamado.aceitarChamado(idChamado, tecnicoResponsavel);
	}

	public int intervirChamado(int idChamado, String descricao, boolean visivel)
			throws NumberFormatException, HelpDeskException {
		return gerenciadorChamado.intervirChamado(idChamado, descricao.trim(), visivel);
	}

	public void fecharChamado(int idChamado, boolean adicionarBase, String tecnicoResponsavel)
			throws NumberFormatException, HelpDeskException {
		gerenciadorChamado.fecharChamado(idChamado, adicionarBase, tecnicoResponsavel);
	}

	public int apropriarChamado(int idChamado, String tecnicoResponsavel)
			throws NumberFormatException, HelpDeskException {
		return gerenciadorChamado.apropriarChamado(idChamado, tecnicoResponsavel);
	}

	public void autorizarInterEncaminhamento(int idChamado) throws HelpDeskException {
		gerenciadorChamado.autorizarInterEncaminhamento(idChamado);
	}

	public void negarInterEncaminhamento(int idChamado) throws HelpDeskException {
		gerenciadorChamado.negarInterEncaminhamento(idChamado);
	}

	public int interEncaminharChamado(int idChamado, String unidadeDestino) throws HelpDeskException {
		return gerenciadorChamado.interEncaminharChamado(idChamado, unidadeDestino);
	}

	public List<Transacao> getTransacaoParaTecnico(int idChamado) {
		return gerenciadorChamado.getTransacaoParaTecnico(idChamado);
	}

	public List<Transacao> getTransacaoParaUsuario(int idChamado) {
		return gerenciadorChamado.getTransacaoParaUsuario(idChamado);
	}

	public void setarGerente(String gerente, String idUnidade) throws HelpDeskException {
		gerenciadorUnidade.setarGerente(gerente, idUnidade);
	}

	public List<Tecnico> getTecnicosMenosEu(String idTecnico) throws HelpDeskException {
		return gerenciadorTecnico.getTecnicosMenosEu(idTecnico);
	}

	public PrimaryKeyTipo criarTipoDaUnidade(String idUnidade, String nome) throws HelpDeskException {
		return gerenciadorTipo.criarTipoDaUnidade(idUnidade, nome);
	}

	public boolean existEquipamento(String patrimonio) throws HelpDeskException {
		boolean ehPraTestar;
		try {
			ehPraTestar = Boolean
					.parseBoolean(gerenciadorConfig.getConfig(Config.TESTAR_EXISTENCIA_PATRIMONIO).getValor());
		} catch (Exception e) {
			ehPraTestar = false;
		}
		if (ehPraTestar) {
			return bdTRE.existsEquipamento(patrimonio);
		}
		return true;
	}

	public void removerSubtipo(String idUnidade, String nomeTipo, String nomeSubtipo) throws HelpDeskException {
		gerenciadorTipo.removerSubtipo(idUnidade, nomeTipo, nomeSubtipo);

	}

	public void alterarSubtipo(String idUnidade, String tipo, String subtipo, String novoSubtipo,
			boolean novoTemPatrimonio, int novoPrazoAtendimento) throws HelpDeskException {
		gerenciadorTipo.alterarSubtipo(idUnidade, tipo, subtipo, novoSubtipo, novoTemPatrimonio, novoPrazoAtendimento);

	}

	public boolean temPatrimonio(String idUnidade, String tipo, String subtipo) throws HelpDeskException {
		return gerenciadorTipo.temPatrimonio(idUnidade, tipo, subtipo);
	}

	public List<Unidade> getAllUnidadesSuporte() {
		return gerenciadorUnidade.getAllUnidadesSuporte();
	}

	public Tipo getTipoDaUnidade(String idUnidade, String idTipo) throws HelpDeskException {
		return gerenciadorTipo.getTipo(idUnidade, idTipo);
	}

	public List<Chamado> pesquisaChamados(String idSolicitante, String idAtendente, String tipo, String subtipo,
			String estado, String patrimonio) {
		return gerenciadorChamado.pesquisaChamados(idSolicitante, idAtendente, tipo, subtipo, estado, patrimonio);
	}

	public boolean chamadoEhAtendido(int idChamado, String idUnidade) {
		return gerenciadorChamado.chamadoEhAtendido(idChamado, idUnidade);
	}

	public void setGerencia(String idUnidade, String tipoGerencia) throws HelpDeskException {
		gerenciadorUnidade.setGerencia(idUnidade, tipoGerencia);
	}

	public String getGerencia(String idUnidade) {
		return gerenciadorUnidade.getGerencia(idUnidade);
	}

	public Tecnico getGerente(String idUnidade) throws HelpDeskException {
		return gerenciadorUnidade.getGerente(idUnidade);
	}

	public boolean isGerente(String idUnidade, String idUsuario) throws HelpDeskException {
		return gerenciadorUnidade.isGerente(idUnidade, idUsuario);
	}

	public int delegarChamado(int idChamado, String tecnicoOrigem, String tecnicoDestino)
			throws NumberFormatException, HelpDeskException {
		return gerenciadorChamado.delegarChamado(idChamado, tecnicoOrigem, tecnicoDestino);
	}

	public Subtipo getSubtipo(String idUnidade, String tipo, String subtipo) throws HelpDeskException {
		return gerenciadorTipo.getSubtipo(idUnidade, tipo, subtipo);
	}

	public List<Config> getAllConfigs() {
		return gerenciadorConfig.getAllConfigs();
	}

	public void alterarConfig(String id, String valor) throws HelpDeskException {
		gerenciadorConfig.alterarConfig(id, valor);

	}

	public Config getConfig(String id) throws HelpDeskException {
		return gerenciadorConfig.getConfig(id);
	}

	public BaseConhecimento pesquisarAll(String palavraChave, boolean artigo, boolean faq, boolean solucao) {
		return base.pesquisarAll(palavraChave, artigo, faq, solucao);
	}

	public boolean existTipoDaUnidade(String idUnidade, String tipo) throws HelpDeskException {
		return gerenciadorTipo.existTipoDaUnidade(idUnidade, tipo);
	}

	public List<String> getGerencias() {
		return gerenciadorUnidade.getGerencias();
	}

	public List<Unidade> getAllUnidadesSomenteSolicitantes() {
		return gerenciadorUnidade.getAllUnidadesSomenteSolicitantes();
	}

	public List<UnidadeBDTRE> getAllUnidadesTRE() {
		return gerenciadorUnidade.getAllUnidadesTRE();
	}

	public boolean existUsuario(String idUsuario) {
		return gerenciadorUsuario.existUsuario(idUsuario);
	}

	public int criarRelatorioVisita(int idChamado, String idTecnico, String intinerario, String imprevistos,
			boolean cabeamentoEstuturado, boolean layoutDoAmbiente, boolean tensaoDosEstabilizadores,
			boolean instalacoesEletricas, boolean limpezaDosMicros, boolean tensaoDosNoBreaks,
			boolean instalacoesFisicas, boolean limpezaDasImpressoras, boolean organizacaoDosCabos,
			boolean imagensDosMicros, String outrosString, boolean outros, String servicosRealizados, String pendencias)
			throws HelpDeskException {
		return gerenciadorChamado.criarRelatorioVisita(idChamado, idTecnico, intinerario, imprevistos,
				cabeamentoEstuturado, layoutDoAmbiente, tensaoDosEstabilizadores, instalacoesEletricas,
				limpezaDosMicros, tensaoDosNoBreaks, instalacoesFisicas, limpezaDasImpressoras, organizacaoDosCabos,
				imagensDosMicros, outrosString, outros, servicosRealizados, pendencias);
	}

	public Visita getVisita(int idVisita) {
		return gerenciadorChamado.getVisita(idVisita);
	}

	public List gerarRelatorio(String unidadeSolicitante, String unidadeAtendente, String tecnico, String ida,
			String volta, String tipo, String subtipo, String estado, String patrimonio) throws HelpDeskException {
		return gerenciadorChamado.gerarRelatorio(unidadeSolicitante, unidadeAtendente, tecnico, ida, volta, tipo,
				subtipo, estado, patrimonio);
	}
}
