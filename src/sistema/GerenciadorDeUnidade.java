package sistema;

import integracaoTRE.GerenciadorDeIntegracao;
import integracaoTRE.UnidadeBDTRE;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import relacionamento.GerenteDeUnidade;
import usuario.Tecnico;
import usuario.Unidade;
import util.MsgErros;
import dao.GerenteDeUnidadeDAO;
import dao.UnidadeDAO;
import dao.UsuarioDAO;
import excecoes.HelpDeskException;
/**
 * Classe que representa um gerenciador de Unidade
 * @author arthur.farias
 *
 */
public class GerenciadorDeUnidade {

	private static GerenciadorDeUnidade singleton;

	public static GerenciadorDeUnidade getInstance() {
		if (singleton == null)
			singleton = new GerenciadorDeUnidade();
		return singleton;
	}

	private GerenciadorDeUnidade() {
	}

	public String criarUnidade(String idUnidade, String nome, boolean isSuporte)
			throws HelpDeskException {
		try {
			Unidade unidade = new Unidade(idUnidade, nome, isSuporte);
			UnidadeDAO dao = UnidadeDAO.getInstance();
			dao.insert(unidade);
			return idUnidade;
		} catch (ConstraintViolationException e) {
			throw new HelpDeskException(MsgErros.OBJETO_EXISTENTE
					.msg("Unidade"));
		}

	}

	public synchronized void alterarUnidadeNome(String idUnidade,
			String novoNome) throws HelpDeskException {
		Unidade unidade = getUnidade(idUnidade);
		unidade.setNome(novoNome);
		UnidadeDAO dao = UnidadeDAO.getInstance();
		dao.update(unidade);
	}

	public Unidade getUnidade(String idUnidade) throws HelpDeskException {

		if (existsUnidade(idUnidade)) {
			UnidadeDAO dao = UnidadeDAO.getInstance();
			return dao.read(idUnidade);
		} else
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE
					.msg("Unidade"));
	}

	public boolean isUnidadeSuporte(String idUnidade) throws HelpDeskException {
		return getUnidade(idUnidade).isSuporte();
	}

	public boolean isNecessarioAtualizar(String idUnidade, boolean isSuporte)
			throws HelpDeskException {
		Unidade unidade = getUnidade(idUnidade);
		if (unidade.isSuporte() && isSuporte) {
			return false;// retorna que nao houve mudança
		}
		if (!isSuporte) {
			return true;// retorna que houve mudanca
		} else
			return false;// retorna que nao houve mudanca

	}

	public synchronized void setSuporte(String idUnidade, boolean isSuporte)
			throws HelpDeskException {
		Unidade unidade = getUnidade(idUnidade);
		unidade.setSuporte(isSuporte);
		UnidadeDAO dao = UnidadeDAO.getInstance();
		dao.update(unidade);
	}

	public synchronized void removerUnidade(String idUnidade)
			throws HelpDeskException {
		if (getUnidade(idUnidade).isSuporte()) {
			GerenciadorDeTecnico.getInstance().removerTecnicosDaUnidade(
					idUnidade);
			GerenteDeUnidadeDAO gerenteDeUnidadeDAO = GerenteDeUnidadeDAO
					.getInstance();
			GerenteDeUnidade gerenteDeUnidade = gerenteDeUnidadeDAO
					.read(idUnidade);
			if (gerenteDeUnidade != null) {
				gerenteDeUnidadeDAO.delete(gerenteDeUnidade);
			}
		}
		UsuarioDAO dao = new UsuarioDAO();
		Unidade unidade = getUnidade(idUnidade);
		dao.delete(unidade);
	}

	public void zerar() throws HelpDeskException {

	}

	public boolean existsUnidade(String idUnidade) {
		UnidadeDAO dao = UnidadeDAO.getInstance();
		Unidade unidade = dao.read(idUnidade);
		if (unidade == null) {
			return false;
		} else
			return true;
	}

	public synchronized void setarGerente(String gerente, String idUnidade)
			throws HelpDeskException {
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(idUnidade);
		GerenciadorDeTecnico.getInstance().testaSeExiste(gerente);
		
		GerenteDeUnidadeDAO gerenteDeUnidadeDAO = GerenteDeUnidadeDAO
				.getInstance();
		if(temGerente(idUnidade)){
			GerenteDeUnidade gerenteDeUnidade = gerenteDeUnidadeDAO.read(idUnidade);
			gerenteDeUnidade.setIdTecnico(gerente);
			gerenteDeUnidadeDAO.update(gerenteDeUnidade);
		}else{
			GerenteDeUnidade gerenteDeUnidade = new GerenteDeUnidade(idUnidade,
					gerente);
			gerenteDeUnidadeDAO.insert(gerenteDeUnidade);
		}		
	}

	public synchronized Tecnico getGerente(String idUnidade)
			throws HelpDeskException {
		try {
			GerenteDeUnidadeDAO gerenteDeUnidadeDAO = GerenteDeUnidadeDAO
					.getInstance();
			GerenteDeUnidade gerenteDeUnidade = gerenteDeUnidadeDAO
					.getGerente(idUnidade);
			return GerenciadorDeTecnico.getInstance().getTecnico(
					gerenteDeUnidade.getIdTecnico());
		} catch (Exception e) {
			throw new HelpDeskException("Unidade sem gerente!!");
		}

	}

	public synchronized boolean isGerente(String idUnidade, String idUsuario)
			throws HelpDeskException {
		Tecnico gerente = getGerente(idUnidade);
		return gerente.getIdUsuario().equals(idUsuario);
	}

	public Unidade getUnidadeSuporte(String idUnidade) throws HelpDeskException {
		Unidade unidade = getUnidade(idUnidade);
		if (unidade.isSuporte())
			return unidade;
		else
			throw new HelpDeskException("Unidade " + unidade.getNome()
					+ " nao fornece suporte");
	}

	public List<Unidade> getAllUnidades() {
		UnidadeDAO dao = UnidadeDAO.getInstance();
		List<Unidade> lista = dao.getAll();
		return lista;
	}

	public List<Unidade> getAllUnidadesSuporte() {
		UnidadeDAO dao = UnidadeDAO.getInstance();
		List<Unidade> lista = dao.getAllSuporte();
		return lista;
	}

	public synchronized void setGerencia(String idUnidade, String tipoGerencia)
			throws HelpDeskException {
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.read(idUnidade);
		unidade.setGerencia(tipoGerencia);
		unidadeDAO.update(unidade);
	}

	public String getGerencia(String idUnidade) {
		UnidadeDAO unidadeDAO = new UnidadeDAO();
		Unidade unidade = unidadeDAO.read(idUnidade);
		return unidade.getGerencia();
	}

	public List<String> getGerencias() {
		List<String> lista = new LinkedList<String>();
		lista.add(Unidade.APROPRIACAO);
		lista.add(Unidade.DELEGACAO);
		lista.add(Unidade.MISTA);
		return lista;
	}

	public boolean temGerente(String idUnidade) {
		try {
			GerenteDeUnidade t = GerenteDeUnidadeDAO.getInstance().getGerente(
					idUnidade);
			return t != null;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean unidadeDoTecnicotemGerente(String tecnico)
			throws HelpDeskException {
		Tecnico t = GerenciadorDeTecnico.getInstance().getInstance()
				.getTecnico(tecnico);
		return temGerente(GerenciadorDeRelacionamento.getInstance()
				.getUnidadeDoTecnico(tecnico).getIdUsuario());

	}

	public List<Unidade> getAllUnidadesSomenteSolicitantes() {
		List<Unidade> todas = getAllUnidades();
		List<Unidade> todasSuportes = getAllUnidadesSuporte();
		for (Unidade u : todasSuportes) {
			todas.remove(u);
		}
		return todas;

	}

	public List<UnidadeBDTRE> getAllUnidadesTRE() {
		return GerenciadorDeIntegracao.getInstance().getAllUnidadeBDTRE();
	}

	

	
}
