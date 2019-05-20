package sistema;

import java.util.LinkedList;
import java.util.List;

import relacionamento.Atendimento;
import relacionamento.ChamadosRepassados;
import relacionamento.TecnicoDeUnidade;
import usuario.Unidade;
import util.MsgErros;
import chamado.Chamado;
import dao.AtendimentoDAO;
import dao.ChamadosRepassadosDAO;
import dao.TecnicoDeUnidadeDAO;
import dao.UnidadeDAO;
import excecoes.HelpDeskException;
/**
 * Classe que representao um gerenciador de Relacionamento
 * @author arthur.farias
 *
 */
public class GerenciadorDeRelacionamento {

	public static GerenciadorDeRelacionamento instance = null;

	public static GerenciadorDeRelacionamento getInstance() {
		if (instance == null) {
			instance = new GerenciadorDeRelacionamento();
		}
		return instance;
	}

	private GerenciadorDeRelacionamento() {
		super();
	}

	public void relacionarTipoComUnidade(String tipo, String unidade) throws HelpDeskException {

	}

	public synchronized void relacionarTecnicoComUnidade(String tecnico, String unidade) throws HelpDeskException {
		if (tecnico != null)
			tecnico = tecnico.toLowerCase();
		// testa se existe tecnico
		GerenciadorDeTecnico.getInstance().getTecnico(tecnico);
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(unidade);
		if (!TecnicoDeUnidadeDAO.getInstance().isTecnicoJaRelacionado(tecnico, unidade)) {
			TecnicoDeUnidade tecnicoDeUnidade = new TecnicoDeUnidade(tecnico, unidade);
			TecnicoDeUnidadeDAO.getInstance().insert(tecnicoDeUnidade);
		} else
			throw new HelpDeskException(MsgErros.TECNICO_JA_RELACIONADO.msg());
	}

	public Unidade getUnidade(String tecnico) throws HelpDeskException {
		// testa se existe tecnico
		GerenciadorDeTecnico.getInstance().getTecnico(tecnico);
		TecnicoDeUnidade tecnicoDeUnidade = TecnicoDeUnidadeDAO.getInstance().read(tecnico);
		return GerenciadorDeUnidade.getInstance().getUnidade(tecnicoDeUnidade.getUnidade());
	}

	public String getSuportes(String idUnidadeSolicitante) throws HelpDeskException {
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSolicitante);
		AtendimentoDAO dao = AtendimentoDAO.getInstance();
		List<Atendimento> atendimentos = dao.getSuportes(idUnidadeSolicitante);
		String out = "";// "[Transporte], [Eletrico]"
		for (Atendimento atendimento : atendimentos) {
			Unidade unidade = UnidadeDAO.getInstance().read(atendimento.getIdUnidadeSuporte());
			if (!out.isEmpty()) {
				out += ", [" + unidade.getNome() + "]";
			} else {
				out += "[" + unidade.getNome() + "]";
			}
		}
		return out;
	}

	public List<Unidade> getUnidadesSuportes(String idUnidadeSolicitante) throws HelpDeskException {
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSolicitante);
		AtendimentoDAO dao = AtendimentoDAO.getInstance();
		List<Atendimento> atendimentos = dao.getSuportes(idUnidadeSolicitante);
		List<Unidade> unidades = new LinkedList<Unidade>();
		for (Atendimento atendimento : atendimentos) {
			Unidade unidade = UnidadeDAO.getInstance().read(atendimento.getIdUnidadeSuporte());
			unidades.add(unidade);
		}
		return unidades;
	}

	public boolean existSuporte(String idUnidadeSolicitante, String idUnidadeSuporte) throws HelpDeskException {
		List<Unidade> suportes = getUnidadesSuportes(idUnidadeSolicitante);
		Unidade uniSuporte = GerenciadorDeUnidade.getInstance().getUnidadeSuporte((idUnidadeSuporte));
		if (suportes.contains(uniSuporte)) {
			return true;
		}
		return false;
	}

	public String getSolicitantes(String idUnidadeSuporte) throws HelpDeskException {
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidadeSuporte);
		List<Atendimento> atendimentos = getListSolicitantes(idUnidadeSuporte);
		String out = "";// "[Transporte], [Eletrico]"
		for (Atendimento atendimento : atendimentos) {
			Unidade unidade = UnidadeDAO.getInstance().read(atendimento.getIdUnidadeSolicitante());
			if (!out.isEmpty()) {
				out += ", [" + unidade.getNome() + "]";
			} else {
				out += "[" + unidade.getNome() + "]";
			}
		}
		return out;
	}

	public List<Unidade> getSolicitantesUnidade(String idUnidadeSuporte) throws HelpDeskException {
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidadeSuporte);
		List<Atendimento> atendimentos = getListSolicitantes(idUnidadeSuporte);
		List<Unidade> out = new LinkedList<Unidade>();
		for (Atendimento atendimento : atendimentos) {
			Unidade unidade = UnidadeDAO.getInstance().read(atendimento.getIdUnidadeSolicitante());
			out.add(unidade);
		}
		return out;
	}

	public List<Unidade> getNaoSolicitantesUnidade(String idUnidadeSuporte) throws HelpDeskException {
		List<Unidade> solicitantes = getSolicitantesUnidade(idUnidadeSuporte);
		List<Unidade> todas = GerenciadorDeUnidade.getInstance().getAllUnidades();
		todas.removeAll(solicitantes);
		todas.remove(GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSuporte));
		return todas;

	}

	private List<Atendimento> getListSolicitantes(String idUnidadeSuporte) throws HelpDeskException {

		AtendimentoDAO dao = AtendimentoDAO.getInstance();
		List<Atendimento> atendimentos = dao.getSolicitantes(idUnidadeSuporte);
		return atendimentos;
	}

	public synchronized void adicionaSuporte(String idUnidadeSolicitante, String idUnidadeSuporte)
			throws HelpDeskException {
		// testa se existe unidade
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSolicitante);
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSuporte);
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidadeSuporte);

		Atendimento atendimento = new Atendimento(idUnidadeSolicitante, idUnidadeSuporte);
		if (AtendimentoDAO.getInstance().read(idUnidadeSolicitante, idUnidadeSuporte) == null) {
			AtendimentoDAO.getInstance().insert(atendimento);
		}

	}

	public synchronized void removerSuporte(String idUnidadeSuporte) throws HelpDeskException {
		// testa se existe unidade
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSuporte);
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidadeSuporte);

		AtendimentoDAO dao = AtendimentoDAO.getInstance();
		List<Atendimento> atendimentos = getListSolicitantes(idUnidadeSuporte);
		for (Atendimento atendimento : atendimentos) {
			dao.delete(atendimento);
		}
	}

	public synchronized void removerSuporte(String idUnidadeSolicitante, String idUnidadeSuporte)
			throws HelpDeskException {
		// testa se existe unidade
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSolicitante);
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidadeSuporte);
		// testa se a unidade eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidadeSuporte);

		AtendimentoDAO dao = AtendimentoDAO.getInstance();
		Atendimento at = dao.read(idUnidadeSolicitante, idUnidadeSuporte);
		dao.delete(at);
	}

	public Unidade getUnidadeDoTecnico(String idTecnico) throws HelpDeskException {
		// testa se existe tecnico
		GerenciadorDeTecnico.getInstance().getTecnico(idTecnico);
		Unidade unidade = TecnicoDeUnidadeDAO.getInstance().getUnidadedoTecnico(idTecnico);
		if (unidade != null) {
			return unidade;
		} else
			throw new HelpDeskException(MsgErros.TECNICO_NAO_RELACIONADO_COM_UNIDADE.msg());

	}

	public synchronized void desrelacionarTecnicoComUnidade(String idTecnico) throws HelpDeskException {
		// testa se existe tecnico
		GerenciadorDeTecnico.getInstance().getTecnico(idTecnico);
		String unidade = getUnidadeDoTecnico(idTecnico).getIdUsuario();
		TecnicoDeUnidade tecnicoDeUnidade = new TecnicoDeUnidade(idTecnico, unidade);
		TecnicoDeUnidadeDAO.getInstance().delete(tecnicoDeUnidade);
	}

	public synchronized List<Chamado> getChamadosRepassados(String idUnidade) throws HelpDeskException {
		// testa se existe unidade
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidade);
		GerenciadorDeChamado gerCh = GerenciadorDeChamado.getInstance();
		ChamadosRepassadosDAO chDAO = ChamadosRepassadosDAO.getInstance();
		// pega os chamados que passaram pela unidade
		List<ChamadosRepassados> lista = chDAO.getChamadosQuePassaramPelaUnidade(idUnidade);
		List<Chamado> chamados = new LinkedList<Chamado>();
		// adiciona na lista os objetos chamados
		for (ChamadosRepassados ch : lista) {
			Chamado chamado = gerCh.getChamado(ch.getIdChamado());
			String unidadeDona = GerenciadorDeChamado.getInstance().getChamadoAtendido(ch.getIdChamado())
					.getUnidadeAtendente();
			if (!unidadeDona.equalsIgnoreCase(idUnidade)) {
				chamados.add(chamado);
			}
		}
		return chamados;
	}

}
