package util;

public enum MsgErros {

	
	CAMPO_VAZIO {
		public String msg(String... args) {
			return "ERRO 500 - O campo " + args[0] + " não pode ser vazio.";
		}
	},
	VALOR_DE_ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "ERRO 501 - O valor \""+args[0]+"\" é inválido para \""+args[1]+"\".";
		}
	},
	ERRO_REMOCAO{
		public String msg(String... args){
			return "ERRO 502 - Impossível realizar remoção: "+args[0];
		}
	}, 
	ERRO_ALTERACAO{
		public String msg(String... args){
			return "ERRO 503 - Impossível realizar alteração: "+args[0];
		}
	}
	, 
	ERRO_ACESSO_ARQUIVO{
		public String msg(String... args){
			return "ERRO 504 - Erro no acesso ao arquivo "+args[0]+".";
		}
	}
	, 
	ATRIBUTO_INVALIDO{
		public String msg(String... args){
			return "ERRO 505 - Atributo \"" + args[0] + "\" não existe.";
		}
	},
	ERRO_ARQUIVO_IMAGEM{
		public String msg(String... args){
			return "ERRO 506 - O Arquivo não é uma Imagem.";
		}
	},
	OBJ_NOT_FOUND{
		public String msg(String... args){
			return "ERRO 507 - \""+args[0]+"\" não foi encontrado.";
		}
		
	},
	ERRO_AUTENTICACAO{
		public String msg(String... args){
			return "ERRO 508 - "+args[0]+".";
		}
	} ,
	OPER_NAO_REALIZADA{
		public String msg(String... args){
			return "ERRO 509 - Operação de \""+args[0]+"\"não realizada. "+args[1];
		}
	},
	DATA_INVALIDA{
		public String msg(String... args){
			return "ERRO 510 - Data inválida para o campo \""+args[0]+"\".";
		}
		
	},
	
	OBJETO_INEXISTENTE{
		public String msg(String... args){
			return "ERRO 511 - "+args[0]+" inexistente." ;
		}
	},

	UNIDADE_NAO_TIPO_SUPORTE{
		public String msg(String... args){
			return "ERRO 512 - A unidade nao oferece suporte para este tipo de chamado.";
		}
		
	},
	TECNICO_NAO_RELACIONADO_COM_UNIDADE{
		public String msg(String... args){
			return "ERRO 513 - Tecnico nao esta relacionado com alguma unidade.";
		}
		
	},
	TECNICO_JA_RELACIONADO{
		public String msg(String... args){
			return "ERRO 514 - Tecnico ja relacionado com uma unidade";
		}
		
	},
	RELACIONAMENTO_EXISTENTE{
		public String msg(String... args){
			return "ERRO 515 - Relacionamento ja existente";
		}
		
	},
	UNIDADE_NAO_SUPORTE{
		public String msg(String... args){
			return "ERRO 516 - A unidade "+args[0]+" nao fornece suporte";
		}
		
	},
	
	OBJETO_EXISTENTE{
		public String msg(String... args){
			return "ERRO 517 - "+args[0]+" ja existente";
		}
		
	},
	UNIDADE_NOT_TIPO{
		public String msg(String... args){
			return "ERRO 518 - Unidade nao contem tipo.";
		}
		
	},
	CHAMADO_NAO_PODE_SER_FECHADO{
		public String msg(String... args){
			return "ERRO 519 - O chamado nao pode ser fechado.";
		}
		
	},
	SUPORTE_INEXISTENTE{
		public String msg(String... args){
			return "ERRO 520 - A unidade "+args[0]+" nao fornece suporte para a unidade "+args[1]+".";
		}
		
	},
	TIPO_INEXISTENTE_PARA_UNIDADE_SUPORTE{
		public String msg(String... args){
			return "ERRO 521 - A unidade "+args[0]+" nao fornece suporte para o tipo "+args[1]+".";
		}
		
	},
	SUBTIPO_INEXISTENTE_PARA_TIPO{
		public String msg(String... args){
			return "ERRO 522 - O Subtipo "+args[0]+" nao existente para o tipo "+args[1]+".";
		}
		
	},
	PATRIMONIO_INVALIDO_PARA_SUBTIPO{
		public String msg(String... args){
			return "ERRO 523 - Patrimonio invalido para o subtipo "+args[0]+".";
		}
	},
	DESCRICAO_INVALIDA{
		public String msg(String... args){
			return "ERRO 524 - Descricao Invalida.";
		}
		
	},
	CHAMADO_JA_FECHADO{
		public String msg(String... args){
			return "ERRO 525 - O chamado "+args[0]+" já está fechado.";
		}
		
	},
	CHAMADO_NAO_ABERTO{
		public String msg(String... args){
			return "ERRO 526 - O chamado "+args[0]+" nao esta aberto.";
		}
		
	}
;

	public String msg(String... args) {
		return "ERRO 499";
	}



}
