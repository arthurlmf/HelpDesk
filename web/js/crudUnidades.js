/**
*/
function carregarCamposUnidades(){
	dwr.util.useLoadingMessage();
	dwr.engine.beginBatch();
	 //	carregarListaUnidadesCadastradas();
	   	carregarListaUnidadesTRE();
	   	DWRUtil.setValue("loginGerenteUnidade"," ");
	   	mostrarNomeGerente();
	dwr.engine.endBatch();
}

/**
* Função que acessa o método da classe java(FacadeAjax), e retorna
* como callback para a função montaXXX();
*/
function carregarListaUnidadesTRE(){
	DWRUtil.removeAllOptions("comboUnidadeTRE");
	FacadeAjax.getUnidadesNaoCadastradas(function montaComboUnidadeTRE(listBeans){
		DWRUtil.removeAllOptions("comboUnidadeTRE");
		DWRUtil.addOptions("comboUnidadeTRE", listBeans, "codigo","idUnidade");
		
	});
	carregarListaUnidadesCadastradas();
}

/**
 * Recupera as unidades cadastradas e coloca dentro do 'select'
 */
function carregarListaUnidadesCadastradas(){
	DWRUtil.removeAllOptions("comboUnidade");
	FacadeAjax.getListaUnidadesSuporte(function montaComboUnidadeCadastradas(listBeans){
		DWRUtil.removeAllOptions("comboUnidade");
		DWRUtil.addOptions("comboUnidade", listBeans, "idUsuario","nome");
	});
}

/*
 * Cadastra uma unidade no sistema do helpdesk
 * */
function cadastrarUmaUnidade(){
	var unidadeSelecionadaBDTRE = DWRUtil.getValue("comboUnidadeTRE"); 
	if(unidadeSelecionadaBDTRE==null || unidadeSelecionadaBDTRE==''){
		alert("Selecione uma unidade a ser cadastrada.");
	}else{
		var loginGerente = DWRUtil.getValue("loginGerenteUnidade");
		var matriculaGerente = DWRUtil.getValue("matriculaGerente");  
		FacadeAjax.cadastrarUnidadeSuporte(unidadeSelecionadaBDTRE,loginGerente,matriculaGerente);
		carregarCamposUnidades();
	}
}

/** Mostrar o nome do gerente e matricula
*/
function mostrarNomeGerente(){
	var unidadeSelecionadaBDTRE = DWRUtil.getValue("comboUnidadeTRE");
	if(unidadeSelecionadaBDTRE==null || unidadeSelecionadaBDTRE==''){
		DWRUtil.setValue("nomeGerente"," ");
		DWRUtil.setValue("matriculaGerente"," ");	
	}else{
		FacadeAjax.getGerenteTRE(unidadeSelecionadaBDTRE,function (tecnico){
			DWRUtil.setValue("nomeGerente",tecnico.nome);
			DWRUtil.setValue("matriculaGerente",tecnico.matricula);
		});
	}
}


/*------------------ Opções do gerenciamento da unidade de suporte ------------- */

function getSetIdUnidadeLogado(){
   FacadeAjax.getIdUnidadeLogado(function(idUnidde){
   		DWRUtil.setValue("comboUnidade",idUnidde);
   		carregaOpcoesUnidadeSuporte();
   });
}
/**
 * Carrega as opcoes da unidde de suporte
 */
function carregaOpcoesUnidadeSuporte(){
	carregaTipoGerencia();	
	carregaTecnicos();
	carregaUnidadesSolicitantes();	
	carregaEdicaoTipos();//no arquivo tipoSubtipo.js
	carregaComboTipo();//no arquivo tipoSubtipo.js
}
/**
 * Carrega o combo da gerencia da unidade
 */
function carregaTipoGerencia(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.getTiposGerenciaMeFirst(unidade,function(listBeans){
			DWRUtil.removeAllOptions("comboGerencia");
			DWRUtil.addOptions("comboGerencia", listBeans);
		})
}
/**
 * Alterar o tipo de gerencia da unidade
 */
function alterarTipoGerencia(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	var tipoGerencia =  DWRUtil.getValue("comboGerencia"); 
	FacadeAjax.alterarGerencia(unidade,tipoGerencia);
}

/* ------------------------------------------ Técnicos ---------------------------------*/	

/**
 * Carrega os combos referentes aos técnicos (tre e do helpdesk)
 */
function carregaTecnicos(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.getTecnicosNaoCadastradas(unidade,function(listBeans){
			DWRUtil.removeAllOptions("comboTecnicoTRE");
			DWRUtil.addOptions("comboTecnicoTRE", listBeans,"matricula","nome");
		})
	FacadeAjax.getTecnicosHelpDesk(unidade,function(listBeans){
			DWRUtil.removeAllOptions("comboTecnicoHelpDesk");
			DWRUtil.addOptions("comboTecnicoHelpDesk", listBeans,"idUsuario","nome");
		})
	mostrarGerenteUnidade();
	
}

/**
 * Cadastrar um tecnico do TRE no Helpdesk
 */
function cadastrarTecnico(){
	var matriculaTecnico = DWRUtil.getValue("comboTecnicoTRE");
	var login = DWRUtil.getValue("login");  
	if((matriculaTecnico==null ||matriculaTecnico=='')){
		alert("Selecione um tecnico do TRE.");
	}else{
		FacadeAjax.integrarTecnico(matriculaTecnico,login);
		carregaTecnicos();	
	}
	
}


/**
 * Remover um tecnico da unidade
 */
function retirarTecnico(){
	var tecnico = DWRUtil.getValue("comboTecnicoHelpDesk");
	if((tecnico==null ||tecnico=='')){
		alert("Selecione um tecnico do HelpDesk.");
	}else{
		FacadeAjax.retirarTecnicoUnidade(tecnico);
	}
}
/**
 * Mostra o gerente da Unidade
 */
function mostrarGerenteUnidade(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.getGerenteUnidade(unidade, function(tecnico){
			DWRUtil.setValue("nomeGerenteUnidade",tecnico.nome);
	});
}
/**
 *Efetua a alteracao do gerente da unidade 
 */
function alterarGerenteUnidade(){
	var tecnico = DWRUtil.getValue("comboTecnicoHelpDesk");
	var unidade = DWRUtil.getValue("comboUnidade"); 
	if((tecnico==null ||tecnico=='')){
		alert("Selecione um tecnico do HelpDesk para alterar o gerente.");
	}else{
		if (confirm("Você tem certeza que deseja alterar o gerente da unidade?")) {
			FacadeAjax.setarGerente(unidade, tecnico);	
		}
		
	}
	mostrarGerenteUnidade();
}

function cadastrarEstagiario(){
	var nomeEstagiario = DWRUtil.getValue("nomeEstagiario");
	var loginEstagiario = DWRUtil.getValue("login"); 
	var unidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.cadastrarEstagiario(nomeEstagiario,loginEstagiario,unidade);
	carregaTecnicos();
	DWRUtil.setValue("nomeEstagiario","");
	DWRUtil.setValue("login","");
}

/* ------------------------------------------ Unidades Solicitantes ---------------------------------*/	
/**
 * Carrega os combos das Unidades solicintantes
 */
function carregaUnidadesSolicitantes(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	carregarListaNaoSolicitantes();
	carregarListaSolicitantes();
}

/**
 * Carrega o combo com as unidades que nao são solicitantes da unidade de suporte
 */
function carregarListaNaoSolicitantes(){
	dwr.util.useLoadingMessage();
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.getListaNaoSolicitantes(unidade, function montaComboUnidadeTRE(listBeans){
		DWRUtil.removeAllOptions("comboUnidadesNaoSolicitantes");
		DWRUtil.addOptions("comboUnidadesNaoSolicitantes", listBeans,"idUnidade");
	});
}

/**
 * Carrega o combo com as unidades solicitantes
 */
function carregarListaSolicitantes(){
	dwr.util.useLoadingMessage();
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.getListaSolicitantes(unidade, function montaComboUnidadeTRE(listBeans){
		DWRUtil.removeAllOptions("comboUnidadesSolicitantes");
		DWRUtil.addOptions("comboUnidadesSolicitantes", listBeans, "idUnidade","nome");
	});
}

/**
 * Adicionar uma unidade solicitnate a uma de suporte
 */
function cadastrarSolicitante(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	var notSolicitante = DWRUtil.getValue("comboUnidadesNaoSolicitantes");
	if((notSolicitante==null ||notSolicitante=='')){
		alert("Selecione uma unidade do TRE.");
	}else{
		FacadeAjax.adicionaUnidadeSolicitante(unidade,notSolicitante);
		carregaUnidadesSolicitantes()	
	}	
}

function cadastrarTodosSolicitantes(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	FacadeAjax.adicionaTodasUnidadeSolicitante(unidade);
	carregaUnidadesSolicitantes()	
		
}
/*
function ano(elem){
          for (var i = document.f1.model.options.length; i >= 0; i--) 
          document.f1.model.options[i] = null;
          if (elem.selectedIndex != -1){
            if (elem.options[elem.selectedIndex].value==1){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
               if (elem.options[elem.selectedIndex].value==2){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==3){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==4){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==5){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==6){
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==7){
              document.f1.model.options[document.f1.model.options.length] = new Option('2006','2006');
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
              document.f1.model.options[document.f1.model.options.length] = new Option('2007','2007');
            }
            if (elem.options[elem.selectedIndex].value==11){
              document.f1.model.options[document.f1.model.options.length] = new Option('2006','2006');
            }
            if (elem.options[elem.selectedIndex].value==12){
              document.f1.model.options[document.f1.model.options.length] = new Option('2006','2006');
            }
          }
        }

*/
/**
 * Retirar o relacionamento de uma unidade solicitante da de suporte
 */
function retirarSolicitante(){
	var unidade = DWRUtil.getValue("comboUnidade"); 
	var solicitante = DWRUtil.getValue("comboUnidadesSolicitantes");
	if((solicitante==null ||solicitante=='')){
		alert("Selecione uma unidade do Solicitante.");
	}else{
		FacadeAjax.tirarUnidadeSolicitante(unidade,solicitante);
		carregaUnidadesSolicitantes()	
	}
}
	
/* ------------------------------------------ Tipos e Subtipos---------------------------------*/	
/*no arquivo tipoSubtipo.js */