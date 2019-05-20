
function cleanAddOptions(local){
	DWRUtil.removeAllOptions(local);
}

/**
* Função que acessa o método da classe java(FacadeAjax), e retorna
* como callback para a função montaXXX();
*/

function carregaComboTipo(){
	dwr.util.useLoadingMessage();
	var idUnidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.getListaTipo(idUnidade,montaComboTipo);

}
/**
* Função de callback que limpa a combo e depois a remonta.
* O processo para remover a combo é necessário se não os valores
* seram somados com os já existentes.
* @param {Object} listBeans
*/
function montaComboTipo(listBeans){
	DWRUtil.removeAllOptions("comboTipo");
	DWRUtil.addOptions("comboTipo", listBeans, "nomeTipo");
		//carregar o combo de subtipo
	carregaComboSubTipo();
}


/*carrega a lista dos subtipos*/
function carregaComboSubTipo(){
	dwr.util.useLoadingMessage();
	var idUnidade = DWRUtil.getValue("comboUnidade");
	var nomeTipo = DWRUtil.getValue("comboTipo");	
	FacadeAjax.getListaSubTipo(idUnidade,nomeTipo,montaComboSubTipo);
	
}	


/*monta os combos de subtipo*/
function montaComboSubTipo(listBeans){
	DWRUtil.removeAllOptions("comboSubTipo");
	DWRUtil.addOptions("comboSubTipo", listBeans, "nomeSubTipo");
	mostraPatrimonio();
}

/*infromar se um determinado subtipo de um tipo de uma unidade tem patrimonio*/
function mostraPatrimonio(){
	
	var idUnidade = DWRUtil.getValue("comboUnidade");
	var nomeTipo = DWRUtil.getValue("comboTipo");
	var nomeSubtipo = DWRUtil.getValue("comboSubTipo");

 	FacadeAjax.temPatrimonio(idUnidade, nomeTipo, nomeSubtipo, function(booleano) {
		if(booleano!=0){
			$("temPatrimonio").style.display = "";
		}else{
			$("temPatrimonio").style.display = "none";
		}	
  	});
}


/*carrega a lista dos subtipos*/
function carregaComboTecnico(){
	dwr.util.useLoadingMessage();
	var idUnidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.getTecnicosUnidade(idUnidade,function(listBeans){
							DWRUtil.removeAllOptions("comboTecnico");
							DWRUtil.addOptions("comboTecnico", listBeans,"idUsuario","nome");
				 })

}

/*carrega a lista dos subtipos*/
function carregaComboTecnicoAlterar(){
	dwr.util.useLoadingMessage();
	var idUnidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.getTecnicosUnidade(idUnidade,function(listBeans){
							DWRUtil.removeAllOptions("comboTecnico");
							DWRUtil.addOptions("comboTecnico", listBeans,"idUsuario","nome");
							listaUnidadesMeFirst();
				 })

}

function listaUnidadesMeFirst(){
		
	var idTecnico = DWRUtil.getValue("comboTecnico");
	FacadeAjax.getTecnico(idTecnico, function(tecnico){
				DWRUtil.setValue("novoNome",tecnico.nome);
			});

	FacadeAjax.getUnidadesMeFirst(idTecnico,montaMinhaUnidade);
}

function montaMinhaUnidade(listBeans){
	DWRUtil.removeAllOptions("comboUnidadeNova");
	DWRUtil.addOptions("comboUnidadeNova", listBeans,"idUsuario","nome");
}