package sistema;

import java.util.List;

import util.MsgErros;
import config.Config;
import dao.ConfigDAO;
import excecoes.HelpDeskException;
/**
 * Classe que representa um gerenciado de Configuracao
 * @author arthur.farias
 *
 */
public class GerenciadorConfig {

	public static GerenciadorConfig instance = null;

	public static GerenciadorConfig getInstance() {
		if (instance == null) {
			instance = new GerenciadorConfig();
		}
		return instance;
	}

	public GerenciadorConfig() {
	}

	public void alterarConfig(String id, String valor) throws HelpDeskException {
		if (valor.isEmpty()) {
			throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("valor"));
		}

		Config c = ConfigDAO.getInstance().read(id);
		c.setValor(valor);
		ConfigDAO.getInstance().update(c);
	}

	public Config getConfig(String id) throws HelpDeskException {
		ConfigDAO conf = ConfigDAO.getInstance();
		return conf.read(id);
	}

	public List<Config> getAllConfigs() {
		return ConfigDAO.getInstance().getAll();
	}

}
