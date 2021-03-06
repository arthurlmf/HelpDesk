
package bdTRE;

import integracaoTRE.GerenciadorDeIntegracao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

//import trepb.util.acesso.ldap.AutenticacaoLdap;
//import trepb.util.acesso.ldap.FalhaAutenticacaoException;
//import trepb.util.acesso.ldap.FalhaGeralLoginException;
//import trepb.util.acesso.ldap.UsuarioLdap;
import util.Data;
import excecoes.HelpDeskException;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa uma conexao com o tre 
 * 
 * 
 * @author Arthur Farias 
 */

public class ConexaoTRE {
	
	public static ConexaoTRE instance = null;

	public static ConexaoTRE getInstance() {
		if (instance == null) {
			instance = new ConexaoTRE();
		}
		return instance;
	}
	
	private ConnectionOracle conn;
	
//	private AutenticacaoLdap loginLDAP; 
	
	
	/**
	 * 
	 * Construtor da classe
	 */
	public ConexaoTRE() {
		conn = new ConnectionOracle();
		//loginLDAP = AutenticacaoLdap.obterInstancia();
	}
	/**
	 * Retorna o equipamento 
	 * @param patrimonio o patrimonio do equipamento
	 * @return o equipamento
	 * @throws HelpDeskExcepion caso ocorra algum erro
	 */
	public Equipamento getEquipamento(String patrimonio) throws HelpDeskException{
		return fazerConsulta("select * from linkpb.vw_equip_info e where e.patrimonio = '"+patrimonio+"00"+"'").getFirst();
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
		try{
			return !fazerConsulta("select * from linkpb.vw_equip_info e where e.patrimonio = '"+patrimonio+"00"+"'").isEmpty();
		}catch(Exception e){
			throw new HelpDeskException("Problemas na conex�o com o banco de dados de patrim�nio" + "<br>" + e.getMessage());
		}		
	}
	
	/**
	 * Retorna se o usuario esta autenticado
	 * @param username o login do usuario
	 * @param password a senha do usuario
	 * @return true se o usuario esta autenticado, false senao
	 * @throws HelpDeskException caso ocorra algum erro 
	 */
	public boolean isAuthenticatedUser(String username, String password) throws HelpDeskException{		
		if(username.contains("@tre.br"))
			username = username.replaceAll("@tre.br", "");
		
		/*
		try {
			return loginLDAP.loginLdapSimples(username,password);
			
		} catch (FalhaAutenticacaoException e) {
			e.printStackTrace();
			throw new HelpDeskException(e.getMessage());	
			
		} catch (FalhaGeralLoginException e) {
			throw new HelpDeskException(e.getMessage());
		}*/
		return false;//FIXME
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
