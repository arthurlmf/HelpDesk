package mail;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma notificao de recebimento de chamado
 * 
 * 
 * @author Arthur Farias
 */

public class NotificacaoEmAnalise  {

	// Constantes para montar o esqueleto do email
	private final int NUMERO_MAXIMO_LINHA = 67;

	private final String NEWLINE = System.getProperty("line.separator");

	private final String LETTERHEAD1 = "Informamos que seu chamado de n� ";

	private final String LETTERHEAD2 = " foi solicitado.";

	private final String MARK = "-------------------------------------------------------------------";

	private String id_Chamado;

	private String data;

	private String hora;

	private String estado;

	private String tipo;

	private String subtipo;

	private String descricao;

	private final String ID = "ID: ";

	private final String DATA = "Data: ";

	private final String HORA = "Hora: ";

	private final String ESTADO = "Estado: ";

	private final String TIPO = "Tipo: ";

	private final String SUBTIPO = "Subtipo: ";

	private final String DESCRICAO = "Descricao: ";

	private final String DETALHES_DO_CHAMADO = "Detalhes do Chamado";

	private final String HELPDESKTRE = "HelpDeskTRE";

	public NotificacaoEmAnalise() {
		id_Chamado = data = hora= estado = tipo= subtipo = descricao = "";
	}
	/**
	 * Retorna a string da noficacao
	 * @return a string
	 */
	public String getText() {
		return toString();
	}

	/**
	 * Retorna a representacao em string de uma NotificacaoEmAnalise
	 */
	public String toString() {
		return  LETTERHEAD1 + id_Chamado + LETTERHEAD2 + NEWLINE +  NEWLINE + MARK + NEWLINE
				+ DETALHES_DO_CHAMADO + NEWLINE + NEWLINE +  ID + id_Chamado + NEWLINE
				+ DATA + data + NEWLINE + HORA + hora + NEWLINE + ESTADO
				+ estado + NEWLINE + TIPO + tipo + NEWLINE + SUBTIPO
						+ subtipo  + NEWLINE+  delimitaString(DESCRICAO +descricao) + NEWLINE  + MARK
				+ NEWLINE + NEWLINE + HELPDESKTRE;
	}

	
	private String delimitaString(String string) {
		String aux = "";
		int count = string.length();
		while(count > 0 && count > NUMERO_MAXIMO_LINHA ){
			if(count > NUMERO_MAXIMO_LINHA){
				aux += string.substring(0, NUMERO_MAXIMO_LINHA) + NEWLINE;
				string = string.substring(NUMERO_MAXIMO_LINHA, string.length());
			}
			count = string.length();
		}
		
		return aux + string;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getId_Chamado() {
		return id_Chamado;
	}

	public void setId_Chamado(String id_Chamado) {
		this.id_Chamado = id_Chamado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}


}
