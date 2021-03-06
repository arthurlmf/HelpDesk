package facade;

import integracaoTRE.GerenciadorDeIntegracao;
import integracaoTRE.IntegracaoTREUtil;
import integracaoTRE.TecnicoBDTRE;
import integracaoTRE.UnidadeBDTRE;

import java.util.LinkedList;
import java.util.List;

import config.Config;

import sistema.GerenciadorConfig;
import struts.MySession;
import tipo.Subtipo;
import tipo.Tipo;
import usuario.Tecnico;
import usuario.Unidade;
import usuario.Usuario;
import util.MsgErros;
import excecoes.HelpDeskException;
import excecoes.SessaoFinalizadaException;
/**
 * Facade to assynchrnous javascript call (AJAX) 
 * @author arthur.farias
 *
 */
public class FacadeAjax {

	/**
	 * 
	 * @param idUnidade
	 * @return
	 * @throws HelpDeskException
	 */
	public List<Tipo> getListaTipo(String idUnidade) throws HelpDeskException {

		List<Tipo> tipos;
		try {
			tipos = FacadeHelpDesk.getInstance().getTiposDaUnidade(idUnidade);
		} catch (HelpDeskException e) {
			tipos = new LinkedList<Tipo>();
		}

		LinkedList<Tipo> ml = new LinkedList<Tipo>(tipos);
		// System.out.println(ml);
		return ml;
	}

	/**
	 * 
	 * @param idUnidade
	 * @param idTipo
	 * @return
	 * @throws HelpDeskException
	 */
	public List<Subtipo> getListaSubTipo(String idUnidade, String idTipo)
			throws HelpDeskException {
		List<Subtipo> subtipos;
		try {
			subtipos = FacadeHelpDesk.getInstance().getSubtiposDeTipo(
					idUnidade, idTipo);
		} catch (Exception e) {
			subtipos = new LinkedList<Subtipo>();
		}

		LinkedList<Subtipo> ml = new LinkedList<Subtipo>(subtipos);
		return ml;
	}

	/*
	 * retorna a Strind disabled quando n�o tem patrimonio e enabled quando tem
	 * patrimonio
	 */

	public String temPatrimonio(String idUnidade, String tipo, String subtipo) {
		try {
			// System.out.println("passou"+FacadeHelpDesk.getInstance().temPatrimonio(idUnidade,
			// tipo, subtipo));
			boolean patrimonio = FacadeHelpDesk.getInstance().temPatrimonio(
					idUnidade, tipo, subtipo);
			int boleano = patrimonio ? 1 : 0;

			// System.out.println("saida: "+boleano);
			return boleano + "";
		} catch (Exception e) {
			return 0 + "";
		}
	}

	/*
	 * Recupera todas as unidades cadastradas
	 */
	public List<Unidade> getListaUnidades() {
		return FacadeHelpDesk.getInstance().getAllUnidades();
	}

	public List<Unidade> getListaUnidadesSuporte() {
		return FacadeHelpDesk.getInstance().getAllUnidadesSuporte();
	}

	/*
	 * recupera o objeto tipo
	 */
	public Tipo getTipo(String idUnidade, String idTipo)
			throws HelpDeskException {
		Tipo tipo = FacadeHelpDesk.getInstance().getTipo(idUnidade, idTipo);
		// System.out.println(tipo + "imprimiu");
		return tipo;
	}

	public void deletarTipo(String idUnidade, String nomeTipo)
			throws HelpDeskException {
		// System.out.println(idUnidade+ " " + nomeTipo);
		FacadeHelpDesk.getInstance().removerTipo(idUnidade, nomeTipo);
	}

	/**
	 * Criar tipo
	 * 
	 * @throws HelpDeskException
	 */
	public void criarTipo(String idUnidade, String tipo)
			throws HelpDeskException {
		FacadeHelpDesk.getInstance().criarTipoDaUnidade(idUnidade, tipo);
	}

	/**
	 * Alterar tipo
	 */
	public void alterarTipo(String idUnidade, String oldTipo, String newTipo)
			throws HelpDeskException {
		// System.out.println(getTipo(idUnidade, oldTipo));
		FacadeHelpDesk.getInstance().setTipo(idUnidade, oldTipo, newTipo);
	}

	/**
	 * recupera um subtipo
	 */
	public Subtipo getSubTipo(String idUnidade, String tipo, String subtipo)
			throws HelpDeskException {
		// System.out.println(idUnidade + " " + tipo + " "+ subtipo);
		return FacadeHelpDesk.getInstance()
				.getSubtipo(idUnidade, tipo, subtipo);
	}

	/**
	 * Deletar um subtipo
	 * 
	 * @param idUnidade
	 * @param nomeTipo
	 * @throws HelpDeskException
	 */
	public void deletarSubTipo(String idUnidade, String nomeTipo,
			String nomeSubtipo) throws HelpDeskException {
		// System.out.println(idUnidade+ " " + nomeTipo);
		FacadeHelpDesk.getInstance().removerSubtipo(idUnidade, nomeTipo,
				nomeSubtipo);
	}

	/**
	 * Criar tipo
	 * 
	 * @throws HelpDeskException
	 */
	public void criarSubTipo(String idUnidade, String tipo, String subtipo,
			boolean patrimonio, String prazoAtendimento)
			throws HelpDeskException {
		// System.out.println("criando sub" + idUnidade + tipo +
		// subtipo+prazoAtendimento);
		int prazo = -10;
		try {
			prazo = Integer.parseInt(prazoAtendimento);
		} catch (NumberFormatException e) {
			throw new HelpDeskException(MsgErros.VALOR_DE_ATRIBUTO_INVALIDO
					.msg(prazoAtendimento, "prazo de atendimento"));
		}

		FacadeHelpDesk.getInstance().criarSubtipo(idUnidade, tipo, subtipo,
				patrimonio, prazo);
	}

	/**
	 * Alterar subtipo
	 * 
	 * @param idUnidade
	 * @param tipo
	 * @param oldSubtipo
	 * @param newSubtipo
	 * @param patrimonio
	 * @throws HelpDeskException
	 */
	public void alterarSubTipo(String idUnidade, String tipo,
			String oldSubtipo, String newSubtipo, boolean patrimonio,
			String novoPrazoAtendimento) throws HelpDeskException {
		int prazo = -10;
		try {
			prazo = Integer.parseInt(novoPrazoAtendimento);
		} catch (NumberFormatException e) {
			throw new HelpDeskException(MsgErros.VALOR_DE_ATRIBUTO_INVALIDO
					.msg(novoPrazoAtendimento, "prazo de atendimento"));
		}
		FacadeHelpDesk.getInstance().alterarSubtipo(idUnidade, tipo,
				oldSubtipo, newSubtipo, patrimonio, prazo);
		// FacadeHelpDesk.getInstance().setTipo(idUnidade, oldTipo, newTipo);
	}

	public void imprime(String valor) {
		System.out.println(valor);
	}

	/**
	 * 
	 * @param idUnidade
	 * @return
	 */
	public List<Tecnico> getTecnicosUnidade(String idUnidade) {

		try {
			return FacadeHelpDesk.getInstance().getTecnicos(idUnidade);
		} catch (HelpDeskException e) {
			return new LinkedList<Tecnico>();
		}
	}

	/**
	 * 
	 * @param idUnidade
	 * @return
	 */
	public List<Unidade> getUnidadesMeFirst(String idTecnico) {

		try {
			List<Unidade> listaTudo = FacadeHelpDesk.getInstance()
					.getAllUnidadesSuporte();
			Unidade minha = FacadeHelpDesk.getInstance().getUnidadeDoTecnico(
					idTecnico);
			listaTudo.remove(minha);
			LinkedList<Unidade> minhaPrimeiro = new LinkedList<Unidade>(
					listaTudo);
			minhaPrimeiro.addFirst(minha);
			return minhaPrimeiro;
		} catch (HelpDeskException e) {
			return new LinkedList<Unidade>();
		}
	}

	/**
	 * Recupera o tecncios a partir de seu identificador
	 * 
	 * @param idTecnico
	 * @return
	 * @throws HelpDeskException
	 */
	public Tecnico getTecnico(String idTecnico) {
		try {
			return FacadeHelpDesk.getInstance().getTecnico(idTecnico);
		} catch (HelpDeskException e) {
			return new Tecnico("", "", "");
		}
	}

	public Usuario getUsuarioSessao() {
		try {
			// imprime( MySession.getInstance().getUsuario().toString());
			return MySession.getInstance().getUsuario();
		} catch (SessaoFinalizadaException e) {
			return new Usuario("", "", "");
		}
	}

	public Unidade getUnidadeUsuarioSessao() {
		try {
			return FacadeHelpDesk.getInstance().getUnidadeDoTecnico(
					getUsuarioSessao().getIdUsuario());
		} catch (HelpDeskException e) {
			return new Unidade("", "", false);
		}
	}

	public List<Unidade> getListaSolicitantes(String idUnidade) {
		List<Unidade> lista;
		try {
			lista = FacadeHelpDesk.getInstance().getSolicitantesUnidade(
					idUnidade);
		} catch (HelpDeskException e) {
			lista = new LinkedList<Unidade>();
		}
		return lista;

	}

	public void adicionaUnidadeSolicitante(String idUnidadeSuporte,
			String idSolicitante) throws HelpDeskException {
		GerenciadorDeIntegracao.getInstance().adicionaSuporte(idUnidadeSuporte,
				idSolicitante);
	}

	public void tirarUnidadeSolicitante(String idUnidadeSuporte,
			String idSolicitante) throws HelpDeskException {
		GerenciadorDeIntegracao.getInstance().removerSuporte(idUnidadeSuporte,
				idSolicitante);
	}

	/**
	 * Recupera todas as Unidades cadastrada na BASe do TRE
	 * 
	 * @return
	 */
	public List<UnidadeBDTRE> getUnidadesBDTRE() {
		List<UnidadeBDTRE> lista = FacadeHelpDesk.getInstance()
				.getAllUnidadesTRE();
		return lista;
	}

	/**
	 * Recupera todas as Unidades cadastrada na BASe do TRE
	 * 
	 * @return
	 */
	public List<UnidadeBDTRE> getUnidadesNaoCadastradas() {
		return GerenciadorDeIntegracao.getInstance().getUnidadesNaoIntegradas();
	}

	/**
	 * Recupera os t�cnicos do TRE que n�o estao no helpdesk
	 * 
	 * @param idUnidade
	 * @return
	 */
	public List<TecnicoBDTRE> getTecnicosNaoCadastradas(String idUnidade) {
		return GerenciadorDeIntegracao.getInstance().getTecnicosNaoIntegrados(
				idUnidade);
	}

	/**
	 * Recupera os tecncios do helpdesk de uma unidade
	 * 
	 * @param unidade
	 * @return
	 * @throws HelpDeskException
	 */
	public List<Tecnico> getTecnicosHelpDesk(String unidade)
			throws HelpDeskException {
		List<Tecnico> list = FacadeHelpDesk.getInstance().getTecnicos(unidade);
		return list;
	}

	/**
	 * Cadastra uma unidade com os parametros default (solicitante)da Base do
	 * TRE no sistema do helpdesk
	 * 
	 * @param sigla
	 * @throws HelpDeskException
	 */
	public void cadastrarUnidadeSuporte(String codigo, String loginGerente,
			String matricula) throws HelpDeskException {
		// imprime("<<<<<"+codigo);
		UnidadeBDTRE unidadeBDTRE = GerenciadorDeIntegracao.getInstance()
				.getUnidadeBDTRE(new Integer(codigo));

		GerenciadorDeIntegracao.getInstance().integrarUnidadeSuporte(
				unidadeBDTRE, loginGerente, matricula);
		// imprime(getUnidadesNaoCadastradas().size()+"<<<<<");
	}

	/**
	 * verifica se uma unidade � de suporte
	 * 
	 * @param unidadeSelecionada
	 * @return
	 * @throws HelpDeskException
	 */
	public int isSuporte(String unidadeSelecionada) throws HelpDeskException {
		Unidade un = FacadeHelpDesk.getInstance()
				.getUnidade(unidadeSelecionada);
		// imprime("passou");
		if (un.isSuporte()) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Recupera uma lista com os tipos de gerencia, sendo que no inicio da lista
	 * est� o tipo da gerencia da unidade passada no paramentro
	 * 
	 * @param unidade
	 * @return
	 * @throws HelpDeskException
	 */
	public List<String> getTiposGerenciaMeFirst(String unidade)
			throws HelpDeskException {
		imprime(unidade + "-----");
		Unidade un = FacadeHelpDesk.getInstance().getUnidade(unidade);
		LinkedList<String> lista = new LinkedList<String>();
		lista.add(Unidade.APROPRIACAO);
		lista.add(Unidade.DELEGACAO);
		lista.add(Unidade.MISTA);
		lista.remove(un.getGerencia());
		lista.addFirst(un.getGerencia());
		return lista;
	}

	/**
	 * Alterar o tipo de suporte de uma unidade
	 * 
	 * @param unidade
	 * @param suporte
	 * @throws HelpDeskException
	 */
	public void alterarTipoSuporte(String unidade, boolean suporte)
			throws HelpDeskException {
		FacadeHelpDesk.getInstance().setSuporte(unidade, suporte);
	}

	/**
	 * Alterar o tipo de gerencia de uma unidade
	 * 
	 * @param unidade
	 * @param gerencia
	 * @throws HelpDeskException
	 */
	public void alterarGerencia(String unidade, String gerencia)
			throws HelpDeskException {
		FacadeHelpDesk.getInstance().setGerencia(unidade, gerencia);
	}

	/**
	 * Altera o tipo de suporte e o tipo de gerencia de uma unidade
	 * 
	 * @param unidade
	 * @param suporte
	 * @param gerencia
	 * @throws HelpDeskException
	 */
	public void alterarSuporteGerencia(String unidade, boolean suporte,
			String gerencia) throws HelpDeskException {
		alterarTipoSuporte(unidade, suporte);
		alterarGerencia(unidade, gerencia);
	}

	/**
	 * Integra um tecnico do BDTRE atraves da sua matricula, adcionando o login
	 * 
	 * @param matricula
	 * @param login
	 * @throws HelpDeskException
	 */
	public void integrarTecnico(String matricula, String login)
			throws HelpDeskException {
		GerenciadorDeIntegracao.getInstance().integrarTecnico(matricula, login);

	}

	/**
	 * Recupera o nome do Gerente da Unidade
	 * 
	 * @param idUnidade
	 * @return
	 * @throws HelpDeskException
	 */
	public Tecnico getGerenteUnidade(String idUnidade) throws HelpDeskException {
		return FacadeHelpDesk.getInstance().getGerente(idUnidade);
	}

	/**
	 * Altera o gerente de uma unidade
	 * 
	 * @param idUnidade
	 * @param tecnico
	 * @throws HelpDeskException
	 */
	public void setarGerente(String idUnidade, String tecnico)
			throws HelpDeskException {
		FacadeHelpDesk.getInstance().setarGerente(tecnico, idUnidade);
	}

	/**
	 * Retirar um t�cnio da sua unidade no helpdesk
	 * 
	 * @param idTecnico
	 * @throws HelpDeskException
	 */
	public void retirarTecnicoUnidade(String idTecnico)
			throws HelpDeskException {
		FacadeHelpDesk.getInstance().removerTecnico(idTecnico);
	}

	/**
	 * Recupera uma lista das Unidades do BDTRE que n�o s�o solicinttantes da
	 * Unidade
	 * 
	 * @param idUnidadeSuporte
	 * @return
	 * @throws HelpDeskException
	 */
	public List<UnidadeBDTRE> getListaNaoSolicitantes(String idUnidadeSuporte)
			throws HelpDeskException {
		return GerenciadorDeIntegracao.getInstance()
				.getUnidadesNaoSolicitantes(idUnidadeSuporte);
	}

	/**
	 * REcupera o Gerente da unidade
	 * 
	 * @param idUnidade
	 * @return
	 * @throws HelpDeskException
	 */
	public TecnicoBDTRE getGerenteTRE(String idUnidade)
			throws HelpDeskException {
		GerenciadorDeIntegracao ger = GerenciadorDeIntegracao.getInstance();
		// imprime("----->>>>>>" +idUnidade);
		String idTecnico = ger.getGerenteBDTRE(new Integer(idUnidade))
				.getIdTecnico();
		return ger.getTecnicoBDTRE(idTecnico);
	}

	/**
	 * Recupera o id da unidade do usuario logado
	 * 
	 * @return
	 * @throws HelpDeskException
	 */
	public String getIdUnidadeLogado() throws HelpDeskException {
		// imprime("---"+getUsuarioSessao().getMeuIdOuDaMinhaUnidade()+"---");
		return getUsuarioSessao().getMeuIdOuDaMinhaUnidade();
	}

	public void cadastrarEstagiario(String nomeEstagiario, String login,
			String idUnidade) throws HelpDeskException {
		FacadeHelpDesk.getInstance().criarTecnico(login, nomeEstagiario);
		idUnidade = IntegracaoTREUtil.getDominioSeNaoTiver(idUnidade);
		login = IntegracaoTREUtil.getDominioSeNaoTiver(login);
		FacadeHelpDesk.getInstance().relacionarTecnicoComUnidade(login,
				idUnidade);
	}

	public void adicionaTodasUnidadeSolicitante(String idUnidadeSuporte)
			throws HelpDeskException {
		List<UnidadeBDTRE> lista = GerenciadorDeIntegracao.getInstance()
				.getUnidadesNaoSolicitantes(idUnidadeSuporte);
		for (UnidadeBDTRE un : lista) {
			adicionaUnidadeSolicitante(idUnidadeSuporte, un.getIdUnidade());
		}
	}

	public String getPrazoDefault() {
		int prazo = -1;
		try {
			prazo = Integer.parseInt(GerenciadorConfig.getInstance().getConfig(
					Config.PRAZO_DO_SUBTIPO_DEFAULT).getValor());
		} catch (Exception e) {
			prazo = 10;
		}
		return prazo+"";
	}

}
