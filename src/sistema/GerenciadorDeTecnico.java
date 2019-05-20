package sistema;

import integracaoTRE.IntegracaoTREUtil;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import relacionamento.GerenteDeUnidade;
import relacionamento.ResponsavelDoChamado;
import relacionamento.TecnicoDeUnidade;
import usuario.Tecnico;
import usuario.Unidade;
import usuario.Usuario;
import util.MsgErros;
import chamado.Chamado;
import dao.ChamadoDAO;
import dao.GerenteDeUnidadeDAO;
import dao.ResponsavelDoChamadoDAO;
import dao.TecnicoDAO;
import dao.TecnicoDeUnidadeDAO;
import dao.UnidadeDAO;
import dao.UsuarioDAO;
import excecoes.HelpDeskException;

/**
 * Classe que representa um gerenciador de Tecnico
 * @author arthur.farias
 *
 */
public class GerenciadorDeTecnico {
	
	private static GerenciadorDeTecnico singleton;

	public static GerenciadorDeTecnico getInstance() {
		if (singleton == null)
			singleton = new GerenciadorDeTecnico();
		return singleton;
	}

	private GerenciadorDeTecnico(){
		
	}
	
	public String criarTecnico(String idTecnico, String nome) throws HelpDeskException{
		try {
			if(idTecnico.isEmpty()){
				throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("login"));
			}
			if(nome.isEmpty()){
				throw new HelpDeskException(MsgErros.CAMPO_VAZIO.msg("nome"));
			}
			idTecnico = IntegracaoTREUtil.getDominioSeNaoTiver(idTecnico);
			Tecnico tecnico = new Tecnico(idTecnico, nome, Usuario.TECNICO);
			TecnicoDAO tecnicoDAO = TecnicoDAO.getInstance();
			tecnicoDAO.insert(tecnico);
			return idTecnico;
		} catch (ConstraintViolationException e) {
			throw new HelpDeskException(MsgErros.OBJETO_EXISTENTE.msg("Tecnico"));
		}
		
	}
	
	
	
	public String criarTecnicoAdministrador(String idTecnico, String nome) throws HelpDeskException{
		try{
			Tecnico tecnico = new Tecnico(idTecnico, nome, Usuario.ADMINISTRADOR);
			TecnicoDAO tecnicoDAO = TecnicoDAO.getInstance();
			tecnicoDAO.insert(tecnico);
			return idTecnico;
		} catch (ConstraintViolationException e) {
			throw new HelpDeskException(MsgErros.OBJETO_EXISTENTE.msg("Tecnico"));
		}
		
	}
	

	
	public  List<Tecnico> getTecnicos(String unidade) throws HelpDeskException{
		//testa se a unidade existe e se eh de suporte
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(unidade);
		TecnicoDeUnidadeDAO tecnicoDeUnidadeDAO = TecnicoDeUnidadeDAO.getInstance();
		List<TecnicoDeUnidade> lista = tecnicoDeUnidadeDAO.getListTecnicosDeUnidade(unidade);
		List out = new LinkedList();
		for(TecnicoDeUnidade tecDeUni : lista) {
			out.add(GerenciadorDeTecnico.getInstance().getTecnico(tecDeUni.getTecnico()));
		}
		return out;
	}
	
	public List<Tecnico> getAllTecnicos(){
		return TecnicoDAO.getInstance().getAll();
	}
	
	public List<Tecnico> getTecnicosMenosEu(String idTecnico) throws HelpDeskException{
		//testa se a unidade existe e se eh de suporte
		Unidade unidade = GerenciadorDeRelacionamento.getInstance().getUnidadeDoTecnico(idTecnico);
		GerenciadorDeUnidade.getInstance().getUnidadeSuporte(unidade.getIdUsuario());
		TecnicoDeUnidadeDAO tecnicoDeUnidadeDAO = TecnicoDeUnidadeDAO.getInstance();
		List<TecnicoDeUnidade> lista = tecnicoDeUnidadeDAO.getListTecnicosDeUnidade(unidade.getIdUsuario());
		List out = new LinkedList();
		for(TecnicoDeUnidade tecDeUni : lista) {
			Tecnico tecnico = GerenciadorDeTecnico.getInstance().getTecnico(tecDeUni.getTecnico());
			if (!tecnico.getIdUsuario().equals(idTecnico)){
				out.add(tecnico);
			}
		}
		return out;
	}

	public Tecnico getTecnico(String idTecnico) throws HelpDeskException {
		testaSeExiste(idTecnico);		
		return TecnicoDAO.getInstance().read(idTecnico);
	}

	
	
	public synchronized void removerTecnico(String idTecnico) throws HelpDeskException {		
 		Tecnico tecnico = getTecnico(idTecnico);
		if (!tecnico.isGerente()){
			encaminharChamadosParaGerente(idTecnico);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.delete(tecnico);
			TecnicoDeUnidadeDAO tecnicoDeUnidadeDAO = new TecnicoDeUnidadeDAO();
			TecnicoDeUnidade tecnicoDeUnidade = tecnicoDeUnidadeDAO.read(idTecnico);
			if(tecnicoDeUnidade != null)//deleta se tiver relacionado com uma unidade
				tecnicoDeUnidadeDAO.delete(tecnicoDeUnidade);
			//TecnicoDAO.getInstance().delete(getTecnico(idTecnico));
		} else {
			throw new HelpDeskException("Impossível remover gerente de uma Unidade!");
		}
	}
	
	private synchronized void encaminharChamadosParaGerente(String idTecnico) throws NumberFormatException, HelpDeskException {
		List<Chamado> lista = getChamadosTecnico(idTecnico);
		for(Chamado ch : lista){
			if(ch.isEncaminhado()){
				GerenciadorDeChamado.getInstance().aceitarChamado(ch.getIdChamado(), idTecnico);
			}
		}
		lista = getChamadosTecnico(idTecnico);
		for(Chamado ch : lista){
			if(ch.isAberto()){
				String meuGerente = getMeuGerente(idTecnico).getIdUsuario();
				GerenciadorDeChamado.getInstance().encaminharChamado(ch.getIdChamado(), getMeuGerente(idTecnico).getIdUsuario());
			}
		}
	}

	private synchronized void removerTecnicoCascade(String idTecnico) throws HelpDeskException {		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.read(idTecnico);
		usuarioDAO.delete(usuario);
		TecnicoDeUnidadeDAO tecnicoDeUnidadeDAO = new TecnicoDeUnidadeDAO();
		TecnicoDeUnidade tecnicoDeUnidade = tecnicoDeUnidadeDAO.read(idTecnico);
		tecnicoDeUnidadeDAO.delete(tecnicoDeUnidade);
	}
	
	public synchronized  void removerTecnicosDaUnidade(String idUnidade) throws HelpDeskException{
		List<Tecnico> lista = getTecnicos(idUnidade);
		for (Tecnico tecnico : lista){
			removerTecnicoCascade(tecnico.getIdUsuario());
		}
	}

	public void testaSeExiste(String idTecnico) throws HelpDeskException {
		if(!existTecnico(idTecnico)){
			throw new HelpDeskException(MsgErros.OBJETO_INEXISTENTE.msg("Tecnico"));
		}
	}
	
	public boolean existTecnico(String idTecnico){
		Usuario us = UsuarioDAO.getInstance().read(idTecnico);
		if(us == null)
			return false;
		return us.isTecnico();
	}

	public synchronized void alterarTecnicoNome(String idTecnico, String novoNome) throws HelpDeskException {
		Tecnico tec = getTecnico(idTecnico);
		tec.setNome(novoNome);
		TecnicoDAO.getInstance().update(tec);
	}
	
	
	
	public synchronized Tecnico getMeuGerente(String tecnico) throws HelpDeskException{
		GerenteDeUnidadeDAO gerenteDeUnidadeDAO = GerenteDeUnidadeDAO.getInstance();		
		String idUnidade = GerenciadorDeRelacionamento.getInstance().getUnidadeDoTecnico(tecnico).getIdUsuario();
		GerenteDeUnidade gerenteDeUnidade = gerenteDeUnidadeDAO.getGerente(idUnidade);
		TecnicoDAO tecnicoDAO = new TecnicoDAO();
		return tecnicoDAO.read(gerenteDeUnidade.getIdTecnico());
	}
	
	
	
	public synchronized List<Chamado> getChamadosTecnico(String idTecnico){
		ResponsavelDoChamadoDAO responsavelDoChamadoDAO = ResponsavelDoChamadoDAO.getInstance();
		List listaResponsaveis = responsavelDoChamadoDAO.getChamadosAtendidos(idTecnico);
		LinkedList<Chamado> listaChamados = new LinkedList<Chamado>();
		java.util.Iterator<ResponsavelDoChamado> it = listaResponsaveis.iterator();
		ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
		while(it.hasNext()){
			ResponsavelDoChamado responsavelDoChamado = (ResponsavelDoChamado) it.next();
			listaChamados.add(chamadoDAO.read(responsavelDoChamado.getIdChamado()));
		}
		return listaChamados;
	}
}
