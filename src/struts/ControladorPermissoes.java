package struts;

import usuario.Tecnico;
import usuario.Unidade;
import usuario.Usuario;


public class ControladorPermissoes {
	
	public static final int UNIDADE = 1;

	public static final int UNIDADE_SUPORTE = 2;
	
	public static final int TECNICO = 3;
	
	public static final int TECNICO_GERENTE = 4;
	
	public static final int ADMINISTRADOR = 5;
	
	private static ControladorPermissoes singleton;
	
	private Usuario usuario;

	

	public static ControladorPermissoes getInstance() {
		if (singleton == null)
			singleton = new ControladorPermissoes();
		return singleton;
	}
	
	public ControladorPermissoes() {
		super();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isPermitido(int permissao){
		if(usuario.isUnidade()){
			Unidade un = (Unidade) usuario;
			if(!un.isSuporte()){
				return permissao == UNIDADE;
			}else{
				return permissao == UNIDADE_SUPORTE;
			}
		}
		if(usuario.isTecnico()){
			Tecnico tec = (Tecnico)usuario;
			if(!tec.isGerente()){
				return permissao == TECNICO;
			}else{
				return permissao == TECNICO_GERENTE;
			}
		}
		if(usuario.isAdministrador()){
			return permissao == ADMINISTRADOR;
		}
		return false;
	}

}
