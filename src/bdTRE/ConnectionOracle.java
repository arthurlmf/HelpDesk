package bdTRE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import excecoes.HelpDeskException;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma conexao com o oracle 
 * 
 * 
 * @author Arthur Farias 
 */
public class ConnectionOracle {
	
	private Connection conn;
	
	public ConnectionOracle(){
		try {
			 //Registrando um drive
			//DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
		} catch (/*SQL*/Exception e) {		
			e.printStackTrace();
		}
	}
	

	/**
	 * Abre a conexao
	 * @throws HelpDeskException caso ocorra algum erro

	 */
	public void open() throws HelpDeskException{		
        //Conectando ao banco de dados oracle
        try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@linkdata:1521:adm","linkpb", "h3lpd3sk");
			 //("jdbc:oracle:oci8:@hostname_orcl", "username", "password");
			 //"jdbc:oracle:oci8:@MyHostString",	"scott", "tiger");
		} catch (SQLException e) {
			throw new HelpDeskException(e.getMessage());
		}
	}
	/**
	 * Fecha a conexao
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public void close() throws HelpDeskException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new HelpDeskException(e.getMessage());
		}
	}
	/**
	 * Cria uma consulta e a retorna
	 * @return um Statement que representa uma consulta
	 * @throws HelpDeskException caso ocorra algum erro
	 */
	public Statement getInstanceofStatement() throws HelpDeskException  {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			throw new HelpDeskException(e.getMessage());
		}
	}

	
	

}
