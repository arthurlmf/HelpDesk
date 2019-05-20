package mail;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma notificacao de encaminhamento 
 * 
 * 
 * @author Arthur Farias 
 */
public class NotificacaoInterEncaminhamento {

	// Constantes para montar o esqueleto do email
	private final int NUMERO_MAXIMO_LINHA = 67;

	private final String NEWLINE = System.getProperty("line.separator");

	private final String LETTERHEAD1 = "Informamos que seu chamado de nº ";
	
	private final String LETTERHEAD2 = " foi repassado para outra unidade";

	private final String MARK = "-------------------------------------------------------------------";

	private final String ID = "ID: ";

	private final String DATA = "Data: ";

	private final String HORA = "Hora: ";

	private final String ESTADO = "Estado: ";

	private final String TIPO = "Tipo: ";

	private final String DESCRICAO = "Descricao: ";

	private final String DETALHES_DO_CHAMADO = "Detalhes do Chamado";

	private final String DETALHES_DO_TRANSACAO = "Detalhes da Transacao";

	private final String UNIDADE_RESPONSAVEL = "Unidade responsavel: ";

	private final String HELPDESKTRE = "HelpDeskTRE";

	private final String DESCRICAO1 = "O chamado de nº ";//  "foi encaminhado para a unidade ";

	private final String DESCRICAO2 = " foi encaminhado para o tecnico ";

	private final String DESCRICAO3 = " da unidade ";

	private String id_Transacao;

	private String datatransacao;

	private String tipoTransacao;

	private String id_Chamado;

	private String data;

	private String hora;

	private String estado;

	private String tipo;
	
	private String descricao;

	private String unidadeResponsavel;
	
	private String novaUnidade;

	private String novoTecnico;

	private String horaTransacao;


	/**
	 * 
	 * Construtor da classe
	 */
	public NotificacaoInterEncaminhamento() {
		id_Chamado = data = hora = estado = tipo = descricao = unidadeResponsavel = novoTecnico = id_Transacao = horaTransacao = datatransacao = "";

	}

	/**
	 * Retorna a string da noficaao
	 * @return a string
	 */
	public String getText() {
		return toString();
	}

	/**
	 * Retorna a representacao em string de uma  NotificacaoEncaminhamento
	 */
	public String toString() {
		String out = delimitaString(LETTERHEAD1 + id_Chamado+ LETTERHEAD2 ) + NEWLINE + NEWLINE
		+ MARK + NEWLINE + DETALHES_DO_TRANSACAO + NEWLINE + NEWLINE
		+ ID + id_Transacao + NEWLINE + DATA + datatransacao + NEWLINE
		+ HORA + horaTransacao + NEWLINE + TIPO + tipoTransacao
		+ NEWLINE + UNIDADE_RESPONSAVEL + unidadeResponsavel + NEWLINE
		+ delimitaString(DESCRICAO + DESCRICAO1 + id_Chamado + DESCRICAO2 +novoTecnico + DESCRICAO3 + novaUnidade) + NEWLINE
		+ MARK + NEWLINE + NEWLINE + NEWLINE 
		+ MARK + NEWLINE + DETALHES_DO_CHAMADO + NEWLINE + NEWLINE + ID
		+ id_Chamado + NEWLINE + DATA + data + NEWLINE + HORA + hora
		+ NEWLINE + ESTADO + estado + NEWLINE + TIPO + tipo + NEWLINE				
		+ delimitaString(DESCRICAO + descricao) + NEWLINE + MARK
		+ NEWLINE + NEWLINE + HELPDESKTRE;		
		return out;
	}

	/**
	 * Delimita a string para ele ficar como foi especificada(ate a linha 30)
	 * 
	 * @param string
	 *            a string a ser delimitada
	 * @param inicio
	 *            o inicio da linha
	 * @return a linha delimitada
	 */
	private String delimitaString(String string) {
		String aux = "";
		int count = string.length();
		while (count > 0 && count > NUMERO_MAXIMO_LINHA) {
			if (count > NUMERO_MAXIMO_LINHA) {
				aux += string.substring(0, NUMERO_MAXIMO_LINHA) + NEWLINE;
				string = string.substring(NUMERO_MAXIMO_LINHA, string.length());
			}
			count = string.length();
		}

		return aux + string;
	}

	/**
	 * @return Returns the data.
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data The data to set.
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return Returns the datatransacao.
	 */
	public String getDatatransacao() {
		return datatransacao;
	}

	/**
	 * @param datatransacao The datatransacao to set.
	 */
	public void setDatatransacao(String datatransacao) {
		this.datatransacao = datatransacao;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the hora.
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora The hora to set.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return Returns the horaTransacao.
	 */
	public String getHoraTransacao() {
		return horaTransacao;
	}

	/**
	 * @param horaTransacao The horaTransacao to set.
	 */
	public void setHoraTransacao(String horaTransacao) {
		this.horaTransacao = horaTransacao;
	}

	/**
	 * @return Returns the id_Chamado.
	 */
	public String getId_Chamado() {
		return id_Chamado;
	}

	/**
	 * @param id_Chamado The id_Chamado to set.
	 */
	public void setId_Chamado(String id_Chamado) {
		this.id_Chamado = id_Chamado;
	}

	/**
	 * @return Returns the id_Transacao.
	 */
	public String getId_Transacao() {
		return id_Transacao;
	}

	/**
	 * @param id_Transacao The id_Transacao to set.
	 */
	public void setId_Transacao(String id_Transacao) {
		this.id_Transacao = id_Transacao;
	}

	/**
	 * @return Returns the novoTecnico.
	 */
	public String getNovoTecnico() {
		return novoTecnico;
	}

	/**
	 * @param novoTecnico The novoTecnico to set.
	 */
	public void setNovoTecnico(String novoTecnico) {
		this.novoTecnico = novoTecnico;
	}

	
	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Returns the tipoTransacao.
	 */
	public String getTipoTransacao() {
		return tipoTransacao;
	}

	/**
	 * @param tipoTransacao The tipoTransacao to set.
	 */
	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getUnidadeResponsavel() {
		return unidadeResponsavel;
	}

	public void setUnidadeResponsavel(String unidadeResponsavel) {
		this.unidadeResponsavel = unidadeResponsavel;
	}

	public String getNovaUnidade() {
		return novaUnidade;
	}

	public void setNovaUnidade(String novaUnidade) {
		this.novaUnidade = novaUnidade;
	}

	

}
