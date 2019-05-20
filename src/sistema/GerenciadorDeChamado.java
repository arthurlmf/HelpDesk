package sistema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mail.GerenciadorDeNotificacoes;
import pesquisa.PesquisaChamado;
import pesquisa.PesquisaRelatorio;
import relacionamento.ChamadoAtendido;
import relacionamento.ChamadosRepassados;
import relacionamento.ResponsavelDoChamado;
import relacionamento.TransacaoDeChamado;
import tipo.Subtipo;
import transacao.Apropriacao;
import transacao.Delegacao;
import transacao.Encaminhamento;
import transacao.Fechar;
import transacao.InterEncaminhamento;
import transacao.Intervencao;
import transacao.Transacao;
import transacao.Visita;
import util.HelpDeskUtil;
import util.MsgErros;
import bancoDeSolucao.GerenciadorDaBaseDeConhecimento;
import chamado.Chamado;
import dao.ApropriacaoDAO;
import dao.ChamadoAtendidoDAO;
import dao.ChamadoDAO;
import dao.ChamadosRepassadosDAO;
import dao.DelegacaoDAO;
import dao.EncaminhamentoDAO;
import dao.FecharDAO;
import dao.InterEncaminhamentoDAO;
import dao.IntervencaoDAO;
import dao.ResponsavelDoChamadoDAO;
import dao.TransacaoDAO;
import dao.TransacaoDeChamadoDAO;
import dao.VisitaDAO;
import excecoes.HelpDeskException;
/**
 * Classe que representaum Gerenciador de Chamado
 * @author arthur.farias
 *
 */
public class GerenciadorDeChamado {

	public static GerenciadorDeChamado instance = null;

	public static GerenciadorDeChamado getInstance() {
		if (instance == null) {
			instance = new GerenciadorDeChamado();
		}
		return instance;
	}

	private GerenciadorDeTecnico gerTecnico;

	private GerenciadorDeChamado() {
		gerTecnico = GerenciadorDeTecnico.getInstance().getInstance();
	}

	public String responsavelDoChamado(int idChamado) {
		ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
		try {
			return responsavelDoChamadoDAO.getResponsavelDoChamado(idChamado).getIdTecnico();
		} catch (HelpDeskException e) {
			return "--";
		}
	}

	public synchronized int solicitarChamado(String descricao, String tipo, String subtipo, String idPatrimonio,
			String unidadeSolicitante, String unidadeSuporte) throws NumberFormatException, HelpDeskException {
		if (GerenciadorDeRelacionamento.getInstance().existSuporte(unidadeSolicitante, unidadeSuporte)) {
			if (descricao.isEmpty()) {
				throw new HelpDeskException(MsgErros.DESCRICAO_INVALIDA.msg());
			}

			GerenciadorDeTipo.getInstance().getTipo(unidadeSuporte, tipo);
			trataDescricao(descricao);
			idPatrimonio = tratarSubtipo(tipo, subtipo, idPatrimonio);

			return solicitacarChamadoDeFato(descricao, tipo, subtipo, idPatrimonio, unidadeSolicitante, unidadeSuporte);
		} else {
			throw new HelpDeskException(MsgErros.SUPORTE_INEXISTENTE.msg(
					GerenciadorDeUnidade.getInstance().getUnidade(unidadeSuporte).toString(),
					GerenciadorDeUnidade.getInstance().getUnidade(unidadeSolicitante).toString()));
		}

	}

	private void trataDescricao(String descricao) throws HelpDeskException {
		if (descricao.isEmpty()) {
			throw new HelpDeskException(MsgErros.DESCRICAO_INVALIDA.msg());
		}
	}

	private String tratarSubtipo(String tipo, String subtipo, String idPatrimonio) throws HelpDeskException {
		Subtipo st = GerenciadorDeTipo.getInstance().getSubtipoDeTipo(tipo, subtipo);
		if (st.temPatrimonio()) {
			if (idPatrimonio.isEmpty()) {
				throw new HelpDeskException(MsgErros.PATRIMONIO_INVALIDO_PARA_SUBTIPO.msg(subtipo));
			}
		} else {
			idPatrimonio = "";
		}
		return idPatrimonio;
	}

	private synchronized int solicitacarChamadoDeFato(String descricao, String tipo, String subtipo,
			String idPatrimonio, String unidadeSolicitante, String unidadeSuporte) throws HelpDeskException {
		Chamado chamado = new Chamado(descricao, tipo, subtipo, idPatrimonio);

		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		int idChamado = Integer.parseInt(chamadoDAO.insert(chamado).toString());
		ChamadoAtendido chamadoAtendido = new ChamadoAtendido(idChamado, unidadeSolicitante, unidadeSuporte);
		ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
		chamadoAtendidoDAO.insert(chamadoAtendido);
		GerenciadorDeNotificacoes.getInstance().notificarEmAnalise(
				chamadoAtendidoDAO.read(idChamado).getUnidadeSolicitante(), chamadoDAO.read(idChamado));
		return idChamado;
	}

	public List<Chamado> getChamadosUnidade(String unidadeSuporte) {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
		List lista = chamadoAtendidoDAO.getChamadosUnidade(unidadeSuporte);
		List<Chamado> listaChamado = new LinkedList<Chamado>();
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			ChamadoAtendido chamadoAtendido = (ChamadoAtendido) it.next();
			Chamado chamado = chamadoDAO.read(chamadoAtendido.getIdChamado());
			listaChamado.add(chamado);
		}
		return listaChamado;
	}

	public List<Chamado> getChamadosSolicitados(String unidadeSolicitante) {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
		List lista = chamadoAtendidoDAO.getChamadosSolicitados(unidadeSolicitante);
		List<Chamado> listaChamado = new LinkedList<Chamado>();
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			ChamadoAtendido chamadoAtendido = (ChamadoAtendido) it.next();
			Chamado chamado = chamadoDAO.read(chamadoAtendido.getIdChamado());
			listaChamado.add(chamado);
		}
		return listaChamado;
	}

	/**
	 * Obtém um chamado através do seu identificador
	 * 
	 * @param idChamado o identificador do chamado
	 * @return o chamado caso seja encontrado
	 * @throws HelpDeskException se o identicador for inválido ou inexistente
	 */
	public Chamado getChamado(int idChamado) throws HelpDeskException {
		if (HelpDeskUtil.isNullOrVazio(idChamado + "")) {
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE.msg("Chamado"));
		}
		int idChamadoInt = HelpDeskUtil.toInt(idChamado + "",
				MsgErros.VALOR_DE_ATRIBUTO_INVALIDO.msg(idChamado + "", "Identificador de Chamado"));
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamadoInt);
		if (chamado == null) {
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE.msg("Chamado"));
		}
		return chamado;
	}

	public ChamadoAtendido getChamadoAtendido(int idChamado) {
		ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
		return chamadoAtendidoDAO.read(idChamado);
	}

	public synchronized int encaminharChamado(int idChamado, String tecnicoDestino)
			throws NumberFormatException, HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isAberto()) {
			// pega o antigo responsavel e altera para o novo e salva no banco
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			ResponsavelDoChamado responsavelDoChamado = getResponsavelDoChamado(idChamado);
			String antigoResponsavel = responsavelDoChamado.getIdTecnico();
			responsavelDoChamado.setIdTecnico(tecnicoDestino);
			responsavelDoChamadoDAO.update(responsavelDoChamado);
			// cria o encaminhamento em si e salva no banco
			Encaminhamento encaminhamento = new Encaminhamento(antigoResponsavel, tecnicoDestino);
			EncaminhamentoDAO encaminhamentoDAO = EncaminhamentoDAO.getInstance();
			int idTransacao = Integer.parseInt(encaminhamentoDAO.insert(encaminhamento).toString());
			// cria o relacionamento entre o chamado e a transacao e salva no
			// banco
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.ENCAMINHADO);
			chamadoDAO.update(chamado);

			GerenciadorDeNotificacoes.getInstance().notificarEncaminhamento(
					ChamadoAtendidoDAO.getInstance().read(idChamado).getUnidadeSolicitante(),
					encaminhamentoDAO.read(idTransacao), chamadoDAO.read(idChamado));

			return idTransacao;
		}
		throw new HelpDeskException(MsgErros.CHAMADO_NAO_ABERTO.msg(idChamado + ""));
	}

	public synchronized void aceitarChamado(int idChamado, String tecnicoResponsavel) throws HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isEncaminhado() || chamado.isDelegado()) {
			// cria a apropriacao em si e salva no banco
			Apropriacao apropriacao = new Apropriacao(tecnicoResponsavel);
			ApropriacaoDAO apropriacaoDAO = ApropriacaoDAO.getInstance();
			int idTransacao = Integer.parseInt(apropriacaoDAO.insert(apropriacao).toString());
			// cria o relacionamento entre o chamado e a transacao e salva no
			// banco
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.ABERTO);
			chamadoDAO.update(chamado);
		} else {
			throw new HelpDeskException();
		}
	}

	public synchronized int intervirChamado(int idChamado, String descricao, boolean visivel)
			throws NumberFormatException, HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isAberto()) {
			if (descricao.isEmpty()) {
				throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("descricao"));
			}
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			ResponsavelDoChamado responsavelDoChamado = responsavelDoChamadoDAO.read(idChamado);
			Intervencao intervencao = new Intervencao(responsavelDoChamado.getIdTecnico(), descricao, visivel);
			IntervencaoDAO intervencaoDAO = IntervencaoDAO.getInstance();
			int idTransacao = Integer.parseInt(intervencaoDAO.insert(intervencao).toString());
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);

			ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
			ChamadoAtendido ca = chamadoAtendidoDAO.read(chamado.getIdChamado());
			if (visivel) {
				GerenciadorDeNotificacoes.getInstance().notificarIntervencao(
						ChamadoAtendidoDAO.getInstance().read(idChamado).getUnidadeSolicitante(),
						intervencaoDAO.read(idTransacao), chamadoDAO.read(idChamado), ca);
			}
			return idTransacao;
		}
		throw new HelpDeskException(MsgErros.CHAMADO_NAO_ABERTO.msg(idChamado + ""));
	}

	public synchronized void fecharChamado(int idChamado, boolean adicionarBase, String tecnicoResponsavel)
			throws NumberFormatException, HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isAberto()) {
			// cria a apropriacao em si e salva no banco
			Fechar fechar = new Fechar(tecnicoResponsavel);
			FecharDAO fecharDAO = FecharDAO.getInstance();
			int idTransacao = Integer.parseInt(fecharDAO.insert(fechar).toString());
			// cria o relacionamento entre o chamado e a transacao e salva no
			// banco
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// muda o estado do Chamado
			if (adicionarBase) {
				GerenciadorDaBaseDeConhecimento.getInstance().criaSolucao(getChamado(idChamado));
			}
			chamado.setEstado(Chamado.FECHADO);
			chamadoDAO.update(chamado);
		} else {
			if (chamado.isFechado()) {
				throw new HelpDeskException(MsgErros.CHAMADO_JA_FECHADO.msg(idChamado + ""));
			}
			throw new HelpDeskException(MsgErros.CHAMADO_NAO_PODE_SER_FECHADO.msg(idChamado + ""));
		}
	}

	public synchronized int delegarChamado(int idChamado, String tecnicoOrigem, String tecnicoDestino)
			throws NumberFormatException, HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isEmAnalise()) {
			Delegacao delegacao = new Delegacao(tecnicoOrigem, tecnicoDestino);
			DelegacaoDAO delegacaoDAO = DelegacaoDAO.getInstance();
			int idTransacao = Integer.parseInt(delegacaoDAO.insert(delegacao).toString());
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// cria o relacionamento entre o tecnico e o chamado e salva no
			// banco
			ResponsavelDoChamado responsavelDoChamado = new ResponsavelDoChamado(idChamado, tecnicoDestino);
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			responsavelDoChamadoDAO.insert(responsavelDoChamado);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.DELEGADO);
			chamadoDAO.update(chamado);
			return idTransacao;
		}
		throw new HelpDeskException();
	}

	public synchronized int apropriarChamado(int idChamado, String tecnicoResponsavel)
			throws NumberFormatException, HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isEmAnalise()) {
			// cria a apropriacao em si e salva no banco
			Apropriacao apropriacao = new Apropriacao(tecnicoResponsavel);
			ApropriacaoDAO apropriacaoDAO = ApropriacaoDAO.getInstance();
			int idTransacao = Integer.parseInt(apropriacaoDAO.insert(apropriacao).toString());
			// cria o relacionamento entre o chamado e a transacao e salva no
			// banco
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// cria o relacionamento entre o tecnico e o chamado e salva no
			// banco
			ResponsavelDoChamado responsavelDoChamado = new ResponsavelDoChamado(idChamado, tecnicoResponsavel);
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			responsavelDoChamadoDAO.insert(responsavelDoChamado);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.ABERTO);
			chamadoDAO.update(chamado);
			return idTransacao;
		}
		throw new HelpDeskException();
	}

	public InterEncaminhamento getRequisicaoDeInterEncaminhamento(int idChamado) {
		List<Transacao> listaTransacao = getTransacaoParaTecnico(idChamado);
		return (InterEncaminhamento) listaTransacao.get(listaTransacao.size() - 1);
	}

	public synchronized void autorizarInterEncaminhamento(int idChamado) throws HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isEmAutorizacao()) {
			InterEncaminhamentoDAO interEncaminhamentoDAO = InterEncaminhamentoDAO.getInstance();
			InterEncaminhamento interEncaminhamento = getRequisicaoDeInterEncaminhamento(idChamado);
			// novo responsavel
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			ResponsavelDoChamado responsavelDoChamado = getResponsavelDoChamado(idChamado);
			responsavelDoChamado.setIdTecnico(interEncaminhamento.getTecnicoResponsavel());
			responsavelDoChamadoDAO.update(responsavelDoChamado);
			// nova unidade responsavel
			ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
			ChamadoAtendido chamadoAtendido = chamadoAtendidoDAO.getChamadoAtendidos(idChamado);
			chamadoAtendido.setUnidadeAtendente(interEncaminhamento.getUnidadeDestino());
			chamadoAtendidoDAO.update(chamadoAtendido);
			//
			responsavelDoChamadoDAO.delete(responsavelDoChamado);
			// cria o encaminhamento em si e salva no banco
			interEncaminhamento.setVisivel(true);
			interEncaminhamentoDAO.update(interEncaminhamento);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.EM_ANALISE);
			chamadoDAO.update(chamado);

			GerenciadorDeNotificacoes.getInstance().notificarInterEncaminhamento(
					ChamadoAtendidoDAO.getInstance().read(idChamado).getUnidadeSolicitante(),
					interEncaminhamentoDAO.read(interEncaminhamento.getIdTransacao()), chamadoDAO.read(idChamado));
		} else {
			throw new HelpDeskException();
		}
	}

	public synchronized void negarInterEncaminhamento(int idChamado) throws HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isEmAutorizacao()) {
			InterEncaminhamentoDAO interEncaminhamentoDAO = InterEncaminhamentoDAO.getInstance();
			InterEncaminhamento interEncaminhamento = interEncaminhamentoDAO
					.getRequisicaoDeInterEncaminhamento(idChamado);
			// novo responsavel
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			ResponsavelDoChamado responsavelDoChamado = getResponsavelDoChamado(idChamado);
			responsavelDoChamado.setIdTecnico(interEncaminhamento.getTecnicoResponsavel());
			responsavelDoChamadoDAO.update(responsavelDoChamado);
			// muda o estado do Chamado
			chamado.setEstado(Chamado.ABERTO);
			chamadoDAO.update(chamado);
			// remove o interEncaminhamento
			interEncaminhamentoDAO.delete(interEncaminhamento);
		} else {
			throw new HelpDeskException();
		}
	}

	public synchronized int interEncaminharChamado(int idChamado, String idUnidadeDestino) throws HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isAberto()) {
			// pega o antigo responsavel e altera para o novo e salva no banco
			ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
			ResponsavelDoChamado responsavelDoChamado = getResponsavelDoChamado(idChamado);
			String idAntigoResponsavel = responsavelDoChamado.getIdTecnico();
			String idGerente = gerTecnico.getMeuGerente(idAntigoResponsavel).getIdUsuario();
			String estado = "";
			String responsavel = null;
			boolean visivel = false;
			if (!idAntigoResponsavel.equals(idGerente)) {
				estado = Chamado.EM_AUTORIZACAO;
				visivel = false;
				responsavel = idGerente;
				responsavelDoChamado.setIdTecnico(idGerente);
				responsavelDoChamadoDAO.update(responsavelDoChamado);
			} else {
				// nova unidade responsavel
				ChamadoAtendidoDAO chamadoAtendidoDAO = ChamadoAtendidoDAO.getInstance();
				ChamadoAtendido chamadoAtendido = chamadoAtendidoDAO.getChamadoAtendidos(idChamado);
				chamadoAtendido.setUnidadeAtendente(idUnidadeDestino);
				chamadoAtendidoDAO.update(chamadoAtendido);
				estado = Chamado.EM_ANALISE;
				visivel = false;
				responsavelDoChamadoDAO.delete(responsavelDoChamado);

			}
			// cria o encaminhamento em si e salva no banco
			String idUnidadeOrigem = GerenciadorDeRelacionamento.getInstance().getUnidadeDoTecnico(idGerente)
					.getIdUsuario();
			InterEncaminhamento interEncaminhamento = new InterEncaminhamento(idUnidadeOrigem, idUnidadeDestino,
					idAntigoResponsavel, visivel);
			InterEncaminhamentoDAO interEncaminhamentoDAO = InterEncaminhamentoDAO.getInstance();
			int idTransacao = Integer.parseInt(interEncaminhamentoDAO.insert(interEncaminhamento).toString());
			// cria o relacionamento entre o chamado e a transacao e salva no
			// banco
			TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
			TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
			transacaoDeChamadoDAO.insert(transacaoDeChamado);
			// muda o estado do Chamado
			chamado.setEstado(estado);
			chamadoDAO.update(chamado);
			if (idAntigoResponsavel.equals(idGerente)) {
				GerenciadorDeNotificacoes.getInstance().notificarInterEncaminhamento(
						ChamadoAtendidoDAO.getInstance().read(idChamado).getUnidadeSolicitante(),
						interEncaminhamentoDAO.read(interEncaminhamento.getIdTransacao()), chamadoDAO.read(idChamado));
			}

			// adicionar ao historico do chamados repassados
			adicionarChamadosRepassados(idChamado, idUnidadeOrigem, idTransacao);

			return idTransacao;
		}
		throw new HelpDeskException(MsgErros.CHAMADO_NAO_ABERTO.msg(idChamado + ""));
	}

	private void adicionarChamadosRepassados(int idChamado, String idUnidadeOrigem, int idTransacao)
			throws HelpDeskException {
		ChamadosRepassadosDAO repDAO = ChamadosRepassadosDAO.getInstance();
		if (!repDAO.exist(idChamado, idUnidadeOrigem)) {
			ChamadosRepassados chamadosRepassados = new ChamadosRepassados(idTransacao, idChamado, idUnidadeOrigem);
			repDAO.insert(chamadosRepassados);
		}

	}

	public ResponsavelDoChamado getResponsavelDoChamado(int idChamado) throws HelpDeskException {
		ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
		return responsavelDoChamadoDAO.getResponsavelDoChamado(idChamado);
	}

	public synchronized List<Transacao> getTransacaoParaUsuario(int idChamado) {
		TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
		List<TransacaoDeChamado> listaTransacaoDeChamado = transacaoDeChamadoDAO.getTransacaoDeChamado(idChamado);
		List<Transacao> listaTransacao = new LinkedList<Transacao>();
		Iterator<TransacaoDeChamado> it = listaTransacaoDeChamado.iterator();
		TransacaoDAO transacaoDAO = TransacaoDAO.getInstance();
		while (it.hasNext()) {
			TransacaoDeChamado transacaoDeChamado = (TransacaoDeChamado) it.next();
			Transacao transacao = transacaoDAO.read(transacaoDeChamado.getIdTransacao());
			if (transacao.isVisivel()) {
				listaTransacao.add(transacao);
			}
		}
		return listaTransacao;
	}

	public synchronized List<Transacao> getTransacaoParaTecnico(int idChamado) {
		TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
		List<TransacaoDeChamado> listaTransacaoDeChamado = transacaoDeChamadoDAO.getTransacaoDeChamado(idChamado);
		List<Transacao> listaTransacao = new LinkedList<Transacao>();
		Iterator<TransacaoDeChamado> it = listaTransacaoDeChamado.iterator();
		TransacaoDAO transacaoDAO = TransacaoDAO.getInstance();
		while (it.hasNext()) {
			TransacaoDeChamado transacaoDeChamado = (TransacaoDeChamado) it.next();
			listaTransacao.add(transacaoDAO.read(transacaoDeChamado.getIdTransacao()));
		}
		return listaTransacao;
	}

	public synchronized List<Chamado> pesquisaChamados(String idSolicitante, String idAtendente, String tipo,
			String subtipo, String estado, String patrimonio) {
		PesquisaChamado p = new PesquisaChamado(idSolicitante, idAtendente, tipo, subtipo, estado, patrimonio);
		return p.pesquisaChamado();
	}

	public boolean chamadoEhAtendido(int idChamado, String idUnidade) {
		ChamadoAtendidoDAO chamadoAtendidoDAO = new ChamadoAtendidoDAO();
		return chamadoAtendidoDAO.chamadoEhAtendido(idChamado, idUnidade);
	}

	public int criarRelatorioVisita(int idChamado, String idTecnico, String intinerario, String imprevistos,
			boolean cabeamentoEstuturado, boolean layoutDoAmbiente, boolean tensaoDosEstabilizadores,
			boolean instalacoesEletricas, boolean limpezaDosMicros, boolean tensaoDosNoBreaks,
			boolean instalacoesFisicas, boolean limpezaDasImpressoras, boolean organizacaoDosCabos,
			boolean imagensDosMicros, String outrosString, boolean outros, String servicosRealizados, String pendencias)
			throws HelpDeskException {
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		Chamado chamado = chamadoDAO.read(idChamado);
		if (chamado.isFechado()) {
			throw new HelpDeskException(MsgErros.CHAMADO_JA_FECHADO.msg(idChamado + ""));
		}
		VisitaDAO visitadao = VisitaDAO.getInstance();
		int idTransacao = (Integer) visitadao.insert(new Visita(idTecnico, intinerario, imprevistos,
				cabeamentoEstuturado, layoutDoAmbiente, tensaoDosEstabilizadores, instalacoesEletricas,
				limpezaDosMicros, tensaoDosNoBreaks, instalacoesFisicas, limpezaDasImpressoras, organizacaoDosCabos,
				imagensDosMicros, outrosString, outros, servicosRealizados, pendencias));
		TransacaoDeChamado transacaoDeChamado = new TransacaoDeChamado(idChamado, idTransacao);
		TransacaoDeChamadoDAO transacaoDeChamadoDAO = TransacaoDeChamadoDAO.getInstance();
		transacaoDeChamadoDAO.insert(transacaoDeChamado);
		return idTransacao;
	}

	public Visita getVisita(int idVisita) {
		VisitaDAO visitadao = VisitaDAO.getInstance();
		return visitadao.read(idVisita);
	}

	public List gerarRelatorio(String unidadeSolicitante, String unidadeAtendente, String tecnico, String ida,
			String volta, String tipo, String subtipo, String estado, String patrimonio) throws HelpDeskException {
		PesquisaRelatorio p = new PesquisaRelatorio(unidadeSolicitante, unidadeAtendente, tecnico, ida, volta, tipo,
				subtipo, estado, patrimonio);
		return p.pesquisaChamado();
	}

}
