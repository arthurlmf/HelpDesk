package pesquisa;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import transacao.Transacao;
import util.Data;
import util.HelpDeskUtil;
import util.MsgErros;
import chamado.Chamado;
import dao.ChamadoDAO;
import dao.FecharDAO;
import dao.TransacaoDAO;
import excecoes.HelpDeskException;
/**
 * Classe que representa um Pesquisa Relatorio
 * @author arthur.farias
 *
 */
public class PesquisaRelatorio {
	
	private String unidadeSolicitante;
	
	private String unidadeAtendente;
	
	private String tecnico;
	
	private int tempoIda;
	
	private int tempoVolta;
	
	private String tipo;
	
	private String subtipo;
	
	private String estado;
	
	private String patrimonio;
	
	public PesquisaRelatorio(){
		
	}
	
	public PesquisaRelatorio(String idSolicitante, String idAtendente, String tecnico, String tempoIda, String tempoVolta, String tipo, String subtipo, String estado, String patrimonio) throws HelpDeskException{
		setUnidadeSolicitante(idSolicitante);
		setUnidadeAtendente(idAtendente);
		setTecnico(tecnico);
		setPeriodo(tempoIda, tempoVolta);
		setTipo(tipo);
		setSubtipo(subtipo);
		setEstado(estado);
		setPatrimonio(patrimonio);
	}
	
	private void setPeriodo(String tempoIda, String tempoVolta) throws HelpDeskException {
		this.tempoIda = -1;
		this.tempoVolta = -1;
		try {
			if (!HelpDeskUtil.isNullOrVazio(tempoIda)){
				setTempoIda(Integer.parseInt(tempoIda));			
			}
			if (!HelpDeskUtil.isNullOrVazio(tempoVolta)){
				setTempoVolta(Integer.parseInt(tempoVolta));
			}
		} catch (Exception e){
			throw new HelpDeskException(MsgErros.VALOR_DE_ATRIBUTO_INVALIDO.msg(tempoIda + " ou " + tempoVolta, "Tempo de Atendimento"));  
		}
	}

	/**
	 * @return the tecnico
	 */
	public String getTecnico() {
		return tecnico;
	}

	/**
	 * @param tecnico the tecnico to set
	 */
	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	/**
	 * @return the tempoIda
	 */
	public int getTempoIda() {
		return tempoIda;
	}

	/**
	 * @param tempoIda the tempoIda to set
	 */
	public void setTempoIda(int tempoIda) {
		this.tempoIda = tempoIda;
	}

	/**
	 * @return the tempoVolta
	 */
	public int getTempoVolta() {
		return tempoVolta;
	}

	/**
	 * @param tempoVolta the tempoVolta to set
	 */
	public void setTempoVolta(int tempoVolta) {
		this.tempoVolta = tempoVolta;
	}

	private List<Chamado> filtraData(List<Chamado> list, int ida, int volta){
		List<Chamado> lista = new LinkedList<Chamado>();
		for (Chamado chamado : list) {
			TransacaoDAO dao = TransacaoDAO.getInstance();
			Data dataFechar = dao.getTransacaoFecharDeChamado(chamado.getIdChamado()).getData();
			int diferenca = chamado.getData().diferencaData(dataFechar); 
			if (ida <= diferenca && diferenca <= volta){
				lista.add(chamado);
			}
		}
		return lista;
	}
	
	
	public synchronized List<Chamado> pesquisaChamado(){
		Session session = new ChamadoDAO().openSession();
		/*String queryCompleta = "Select c from Chamado c, ChamadoAtendido ch where c.idChamado = ch.idChamado " +
		"AND ch.unidadeSolicitante like '" + unidadeSolicitante  
		+ "' AND ch.unidadeAtendente like '" + unidadeAtendente + "'";*/
		String queryCompleta = "Select c from Chamado c, ChamadoAtendido ch where c.idChamado = ch.idChamado ";
		if (!HelpDeskUtil.isNullOrVazio(tecnico)){
			queryCompleta += " AND EXISTS(Select t from Apropriacao t, TransacaoDeChamado tc " +
					"where c.idChamado = tc.idChamado AND tc.idTransacao = t.idTransacao " +
					"AND t.tipo like '" + Transacao.APROPRIACAO + "' AND t.tecnicoResponsavel like '" + tecnico + "') " ; 
		}		
		if (!HelpDeskUtil.isNullOrVazio(unidadeAtendente)){
			queryCompleta += " AND ch.unidadeAtendente like '" + unidadeAtendente + "'"; 
		}
		if (!HelpDeskUtil.isNullOrVazio(unidadeSolicitante)){
			queryCompleta += " AND ch.unidadeSolicitante like '" + unidadeSolicitante + "'"; 
		}
		if (!HelpDeskUtil.isNullOrVazio(tipo)){
			queryCompleta += " AND c.tipo like '" + tipo + "'"; 
		}
		if (!HelpDeskUtil.isNullOrVazio(subtipo)){
			queryCompleta += " AND c.subtipo like '" + subtipo + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(estado)){
			queryCompleta += " AND c.estado like '" + estado + "'";
		}
		if (!HelpDeskUtil.isNullOrVazio(patrimonio)){
			queryCompleta += " AND c.idPatrimonio like '" + patrimonio + "'";
		}		
		Query query = session.createQuery(queryCompleta);
		
		List list = query.list();
		session.flush();
		session.close();
		if (tempoIda != -1 && tempoIda != -1){
			 return filtraData(list, tempoIda, tempoVolta);
		}
		return list;
	}
		
	
	/**
	 * @return the unidadeSolicitante
	 */
	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	/**
	 * @param unidadeSolicitante the unidadeSolicitante to set
	 */
	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	/**
	 * @return the unidadeAtendente
	 */
	public String getUnidadeAtendente() {
		return unidadeAtendente;
	}

	/**
	 * @param unidadeAtendente the unidadeAtendente to set
	 */
	public void setUnidadeAtendente(String unidadeAtendente) {
		this.unidadeAtendente = unidadeAtendente;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the subtipo
	 */
	public String getSubtipo() {
		return subtipo;
	}

	/**
	 * @param subtipo the subtipo to set
	 */
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the patrimonio
	 */
	public String getPatrimonio() {
		return patrimonio;
	}

	/**
	 * @param patrimonio the patrimonio to set
	 */
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	

}
