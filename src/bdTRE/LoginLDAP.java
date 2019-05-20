package bdTRE;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import sistema.GerenciadorConfig;

import config.Config;
import dao.ConfigDAO;
import excecoes.HelpDeskException;

/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que faz todas as configuracoes de login do sistema HelpDeskTRE. Ela faz
 * a integracao do sistema com o banco do TRE.
 * FAz a autenticacao 
 * 
 * 
 * @author Arthur Farias 
 */

public class LoginLDAP {

	

	private String url_ldap;
	
	private String login;

	private String lotacao;

	private String email;

	private String nome;

	private String funcao;
	
	private String member;

	private static LoginLDAP loginLDAP;
	
	
	/**
	 * Retorna a instancia unica do LoginLDAP
	 * @return a instancia do LoginLDAP
	 */
	public static synchronized LoginLDAP getInstance(){
		if(loginLDAP == null)
			loginLDAP =  new LoginLDAP();
		return loginLDAP;
		
	}
	
	/**
	 * Carrega as configuracoes de email
	 * @throws HelpDeskException 
	 *
	 */
	private void carregarConfiguracao() throws HelpDeskException {		
		url_ldap = GerenciadorConfig.getInstance().getConfig(Config.URL_LDAP).getValor();
	}	

	/**
	 * Efetua o login do usuário Se login inválido, lança uma exceção do tipo
	 * HelpDeskException
	 * @throws HelpDeskException 
	 */

	public boolean login(String username, String password)
			throws HelpDeskException {
		
		try{
		// Tenta criar o contexto com os dados de login
		LdapContext ctx = null;
		this.login = username;
		boolean isAutenticado = false;
		try {
			ctx = new InitialLdapContext(ldap(username, password), null);
			isAutenticado = true;
			setAtributos(ctx);
		} catch (javax.naming.AuthenticationException e) {
			isAutenticado = false;
		} finally {
			if (ctx != null)
				ctx.close();
			ctx = null;
		}
		return isAutenticado;
		
		} catch (NamingException e) {
			throw new HelpDeskException("Login LDAP falhou/ "+e.getMessage());
		}
	}

	/**
	 * Seta os atributos
	 * @param ctx o contexto
	 * @throws NamingException caso ocorra algume erro
	 */
	public void setAtributos(LdapContext ctx) throws NamingException {

		Attributes atts = ctx.getAttributes("CN=" + this.login
				+ ",CN=Users,DC=tre-pb,DC=gov,DC=br");
		this.setNome(atts.get("displayName").get().toString());
		this.setEmail(atts.get("mail").get().toString());
		this.setLotacao(atts.get("physicalDeliveryOfficeName").get()
						.toString());
		this.setFuncao(atts.get("description").get().toString());
		this.setMember(atts.get("memberOf").get().toString());
	}


	
	/**
	 * Carrega toda a configuracao do ldap
	 * @param username o nome do usuario
	 * @param password a senha
	 * @return o hashtable com as configuracoes
	 * @throws HelpDeskException 
	 */
	
	public Hashtable ldap(String username, String password) throws HelpDeskException {
		carregarConfiguracao();
		Hashtable env = new Hashtable(11);
		String dn = "CN=" + username + ",CN=Users,DC=tre-pb,DC=gov,DC=br";
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url_ldap);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, dn);
		env.put(Context.SECURITY_CREDENTIALS, password);
		return env;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the funcao.
	 */
	public String getFuncao() {
		return funcao;
	}

	/**
	 * @param funcao The funcao to set.
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Returns the lotacao.
	 */
	public String getLotacao() {
		return lotacao;
	}

	/**
	 * @param lotacao The lotacao to set.
	 */
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	/**
	 * @return Returns the member.
	 */
	public String getMember() {
		return member;
	}

	/**
	 * @param member The member to set.
	 */
	public void setMember(String member) {
		this.member = member;
	}

	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


}