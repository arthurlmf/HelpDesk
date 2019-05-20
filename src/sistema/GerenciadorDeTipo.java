package sistema;

import java.util.List;

import config.Config;

import tipo.PrimaryKeySubTipo;
import tipo.PrimaryKeyTipo;
import tipo.Subtipo;
import tipo.Tipo;
import usuario.Unidade;
import util.HelpDeskUtil;
import util.MsgErros;
import dao.SubtipoDAO;
import dao.TipoDAO;
import excecoes.HelpDeskException;

/**
 * Classe que representa um gerenciador de Tipo
 * @author arthur.farias
 *
 */
public class GerenciadorDeTipo {

	private static GerenciadorDeTipo singleton;

	public static GerenciadorDeTipo getInstance() {
		if (singleton == null)
			singleton = new GerenciadorDeTipo();
		return singleton;
	}

	private GerenciadorDeTipo() {
	}

	public List<Tipo> getTiposDaUnidade(String idUnidade)
			throws HelpDeskException {
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidade);
		if (!GerenciadorDeUnidade.getInstance().isUnidadeSuporte(idUnidade)) {
			throw new HelpDeskException(MsgErros.UNIDADE_NOT_TIPO.msg());
		}
		TipoDAO tipoDAO = TipoDAO.getInstance();
		List<Tipo> tipos = tipoDAO.getTiposUnidade(idUnidade);
		return tipos;
	}

	/**
	 * Criar um Subtipo de um tipo de uma unidade
	 * 
	 * @param idUnidade
	 *            o identificador da unidade
	 * @param tipo
	 *            o tipo a qual o subtipo pertence
	 * @param nome_subtipo
	 *            o nome do subtipo
	 * @param patrimonio
	 *            para indicar a existencia de um identificador de patrimonio
	 * @return a chave do subTipo criado
	 * @throws HelpDeskException
	 */
	public PrimaryKeySubTipo criarSubtipo(String idUnidade, String nomeTipo,
			String nome_subtipo, boolean patrimonio, int prazoAtendimento) throws HelpDeskException {
		Tipo tipo = getTipo(idUnidade, nomeTipo);
		if (HelpDeskUtil.isNullOrVazio(nome_subtipo)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Subtipo"));
		}

		SubtipoDAO subtipoDAO = SubtipoDAO.getInstance();
		if (subtipoDAO.existeSubtipo(idUnidade, nomeTipo, nome_subtipo)) {
			throw new HelpDeskException(MsgErros.OBJETO_EXISTENTE
					.msg("Subtipo"));
		}

		Subtipo subtipo = new Subtipo(idUnidade, nomeTipo, nome_subtipo,
				patrimonio, prazoAtendimento);
		subtipoDAO.insert(subtipo);
		PrimaryKeySubTipo idSubtipo = subtipo.getChaveSubtipo();
		return idSubtipo;
	}

	public PrimaryKeyTipo criarTipoDaUnidade(String idUnidade, String nome)
			throws HelpDeskException {
		GerenciadorDeUnidade.getInstance().getUnidade(idUnidade);
		TipoDAO tipoDAO = TipoDAO.getInstance();
		if (tipoDAO.existeTipoDaUnidade(idUnidade, nome)) {
			throw new HelpDeskException(MsgErros.OBJETO_EXISTENTE.msg("Tipo"));
		}
		if (HelpDeskUtil.isNullOrVazio(nome)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Tipo"));
		}
		if (!GerenciadorDeUnidade.getInstance().isUnidadeSuporte(idUnidade)) {
			throw new HelpDeskException(MsgErros.UNIDADE_NAO_SUPORTE
					.msg(idUnidade));
		}

		Tipo tipo = new Tipo(idUnidade, nome);
		tipoDAO.insert(tipo);
		PrimaryKeyTipo idTipo = tipo.getChaveTipo();
		return idTipo;

	}

	public Tipo getTipo(String idUnidade, String idTipo)
			throws HelpDeskException {
		TipoDAO tipoDAO = TipoDAO.getInstance();
		if (HelpDeskUtil.isNullOrVazio(idTipo)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Tipo"));
		}
		if (!tipoDAO.existeTipo(idTipo)) {
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE.msg("Tipo"));
		}

		if (!tipoDAO.existeTipoDaUnidade(idUnidade, idTipo)) {
			throw new HelpDeskException(
					MsgErros.TIPO_INEXISTENTE_PARA_UNIDADE_SUPORTE.msg(
							GerenciadorDeUnidade.getInstance().getUnidade(idUnidade)
									.toString(), idTipo));
		}
		Tipo tipo = tipoDAO.read(new PrimaryKeyTipo(idUnidade, idTipo));
		return tipo;
	}

	public List<Tipo> getAllTipos() {
		TipoDAO tipoDAO = TipoDAO.getInstance();
		return tipoDAO.getAll();
	}

	public List<Subtipo> getAllSubtipos() {
		SubtipoDAO subtipoDAO = SubtipoDAO.getInstance();
		return subtipoDAO.getAll();
	}

	public synchronized void setTipo(String idUnidade, String nomeTipo, String newTipo2)
			throws HelpDeskException {
		Tipo tipo = getTipo(idUnidade, nomeTipo);
		if (HelpDeskUtil.isNullOrVazio(newTipo2)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Tipo"));
		}
		TipoDAO tipoDAO = TipoDAO.getInstance();
		tipoDAO.update(tipo, newTipo2);

	}

	public synchronized void removeTipo(String idUnidade, String nomeTipo)
			throws HelpDeskException {
		Tipo tipo = getTipo(idUnidade, nomeTipo);
		if (HelpDeskUtil.isNullOrVazio(nomeTipo)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Tipo"));
		}
		TipoDAO tipoDAO = TipoDAO.getInstance();
		tipoDAO.delete(tipo);

	}

	public List<Subtipo> getSubtiposDeTipo(String idUnidade, String nomeTipo)
			throws HelpDeskException {
		Tipo tipo = getTipo(idUnidade, nomeTipo);
		SubtipoDAO dao = SubtipoDAO.getInstance();
		List<Subtipo> listaSubTipo = dao.getSubtipos(idUnidade, nomeTipo);
		boolean criarSubtipoAutomaticamente;
		int prazo = -1;
		try {
			criarSubtipoAutomaticamente = Boolean.parseBoolean(GerenciadorConfig.getInstance().getConfig(Config.CRIAR_SUBTIPO_AUTOMATICAMENTE).getValor());
			try {
			prazo = Integer.parseInt(GerenciadorConfig.getInstance().getConfig(Config.PRAZO_DO_SUBTIPO_DEFAULT).getValor());
			} catch (Exception e) {
				prazo = 10;
			}
		} catch (Exception e) {
			criarSubtipoAutomaticamente = false;
		}
		if(criarSubtipoAutomaticamente && listaSubTipo.isEmpty()){
			criarSubtipo(idUnidade, nomeTipo, nomeTipo, false, 10);
			listaSubTipo = dao.getSubtipos(idUnidade, nomeTipo);
		}
		return listaSubTipo;
	}

	public Subtipo getSubtipoDeTipo(String nomeTipo, String nomeSubtipo)
			throws HelpDeskException {
		List<Subtipo> subtipos = getSubtiposDeTipo(getUnidadeDoTipo(nomeTipo)
				.getIdUsuario(), nomeTipo);
		SubtipoDAO subtipoDAO = SubtipoDAO.getInstance();
		if (!subtipoDAO.existeSubTipo(nomeSubtipo)) {
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE
					.msg("Subtipo"));
		}
		for (Subtipo subtipo : subtipos) {
			if (subtipo.getNomeSubTipo().equalsIgnoreCase(nomeSubtipo)) {
				return subtipo;
			}
		}
		throw new HelpDeskException(MsgErros.SUBTIPO_INEXISTENTE_PARA_TIPO.msg(
				nomeSubtipo, nomeTipo));
	}

	public Unidade getUnidadeDoTipo(String nomeTipo) throws HelpDeskException {
		TipoDAO tipoDAO = TipoDAO.getInstance();
		if (!tipoDAO.existeTipo(nomeTipo)) {
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE.msg("Tipo"));
		}
		List<Tipo> allTipos = getAllTipos();
		for (Tipo tipo : allTipos) {
			if (tipo.getNomeTipo().equalsIgnoreCase(nomeTipo)) {
				return GerenciadorDeUnidade.getInstance().getUnidade(tipo.getIdUnidade());
			}
		}
		return null;
	}

	public synchronized void removerSubtipo(String idUnidade, String nomeTipo,
			String nome_subtipo) throws HelpDeskException {
		Tipo tipo = getTipo(idUnidade, nomeTipo);
		if (HelpDeskUtil.isNullOrVazio(nome_subtipo)) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("Subtipo"));
		}

		SubtipoDAO subtipoDAO = SubtipoDAO.getInstance();
		Subtipo subtipo = getSubtipoDeTipo(nomeTipo, nome_subtipo);
		subtipoDAO.delete(subtipo);
	}

	public synchronized void alterarSubtipo(String idUnidade, String nomeTipo,
			String nomeSubtipo, String novoSubtipo, boolean novoTemPatrimonio, int novoPrazoAtendimento)
			throws HelpDeskException {
		removerSubtipo(idUnidade, nomeTipo, nomeSubtipo);
		criarSubtipo(idUnidade, nomeTipo, novoSubtipo, novoTemPatrimonio,novoPrazoAtendimento);
	}

	public boolean temPatrimonio(String idUnidade, String tipo, String subtipo)
			throws HelpDeskException {
		Subtipo sub = getSubtipoDeTipo(tipo, subtipo);
		return sub.temPatrimonio();

	}
	
	public Subtipo getSubtipo(String idUnidade, String tipo, String subtipo) throws HelpDeskException {
		return getSubtipoDeTipo(tipo, subtipo);
	}

	public synchronized boolean existTipoDaUnidade(String idUnidade, String tipo) throws HelpDeskException {		
		List<Tipo> tipos =  getTiposDaUnidade(idUnidade);
		for(Tipo t : tipos){
			if(t.getNomeTipo().equalsIgnoreCase(tipo)){
				return true;
			}
		}
		return false;
		
	}

}
