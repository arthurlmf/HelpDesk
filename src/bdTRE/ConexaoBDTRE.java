package bdTRE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import util.Data;
import excecoes.HelpDeskException;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma conexao com o banco do tre 
 * 
 * 
 * @author Arthur Farias 
 */



public class ConexaoBDTRE {
	
	private ConnectionOracle conn;
	
	private LoginLDAP loginLDAP; 
	
	
	/**
	 * 
	 * Construtor da classe
	 */
	public ConexaoBDTRE() {
		conn = new ConnectionOracle();
		loginLDAP = LoginLDAP.getInstance();
	}
	/**
	 * Retorna o equipamento 
	 * @param patrimonio o patrimonio do equipamento
	 * @return o equipamento
	 * @throws HelpDeskExcepion caso ocorra algum erro
	 */
	public Equipamento getEquipamento(String patrimonio) throws HelpDeskException{
		patrimonio = maskararPatrimonio(patrimonio);
		return fazerConsulta("select * from linkpb.vw_equip_info e where e.patrimonio = '"+patrimonio+"'").getFirst();
	}
	
	private String maskararPatrimonio(String patrimonio) {
		return patrimonio+ "00";
	}
	/**
	 * Retorna um usuario do sistema
	 * @param id o id do usuario
	 * @return o usuario
	 * @throws HelpDeskExcepion caso ocorra algum erro
	 */
	public Equipamento getUsuarios(String id) throws HelpDeskException{
		return fazerConsulta("select * from usuarios u where u.id = '"+id+"'").getFirst();
	}
	/**
	 * Retorna todos os equipamentos do bando
	 * @return uma List de equipamentos
	 * @throws HelpDeskExcepion caso ocorra algum erro
	 */
	public List<Equipamento> getEquipamentos() throws HelpDeskException{
		return fazerConsulta("select * from linkpb.vw_equip_info");		
	}
	/**
	 * Retorna se existe o equipamento passado
	 * @param patrimonio o patrimonio do equipamento
	 * @return true se existir, false senao
	 * @throws HelpDeskExcepion caso ocorra algum erro 
	 */
	public boolean existsEquipamento(String patrimonio) throws HelpDeskException{
		patrimonio = maskararPatrimonio(patrimonio);
		return !fazerConsulta("select * from linkpb.vw_equip_info e where e.patrimonio = '"+patrimonio+"'").isEmpty();		
	}
	
	/**
	 * Retorna se o usuario esta autenticado
	 * @param username o login do usuario
	 * @param password a senha do usuario
	 * @return true se o usuario esta autenticado, false senao
	 * @throws HelpDeskException caso ocorra algum erro 
	 */
	public boolean isAuthenticatedUser(String username, String password) throws HelpDeskException{
		return loginLDAP.login(username,password);
	}
	
	
	private LinkedList<Equipamento> fazerConsulta(String sqlQuery) throws HelpDeskException {
		LinkedList<Equipamento> lista = new LinkedList(); 
		try {
			conn.open();
			 //Criando um statement para gerenciar comandos SQL
	        Statement st = conn.getInstanceofStatement();
	        //Submeter uma consulta
	        ResultSet rs = st.executeQuery(sqlQuery);
	        //Acessar os dados na consulta
	        while(rs.next()){	        	
	        	Equipamento equi = new Equipamento(rs.getString("patrimonio"),rs.getString("descricao"),tratarData(rs.getString("garantia")),rs.getString("serie"));
	        	lista.add(equi);
			}
	        conn.close();

		} catch (SQLException e) {			
			throw new HelpDeskException(e.getMessage());
		}
		return lista;		
	}

	private Data tratarData(String string) throws HelpDeskException {
		if(string==null){
			return null;
		}
		int ano = Integer.parseInt(string.substring(0, 4));
		int mes =  Integer.parseInt(string.substring(5, 7));
		int dia =  Integer.parseInt(string.substring(8, 10));
		return new Data(dia,mes,ano);
		
	}
	
	

}
