
function cleanAddOptions(local){
	DWRUtil.removeAllOptions(local);
}

/**
* Função que acessa o método da classe java(FacadeAjax), e retorna
* como callback para a função montaXXX();
*/

function carregarListaUnidadesTRE(){
	dwr.util.useLoadingMessage();
	FacadeAjax.getUnidadesNaoCadastradas(montaComboUnidadeTRE);

}function montaComboUnidadeTRE(listBeans){
	DWRUtil.removeAllOptions("comboUnidadeTRE");
	DWRUtil.addOptions("comboUnidadeTRE", listBeans, "idUsuario","nome");
	carregarListaUnidadesCadastradas();
}

/**
 * Recupera as unidades cadastradas e coloca dentro do 'select'
 */
function carregarListaUnidadesCadastradas(){
	dwr.util.useLoadingMessage();
	FacadeAjax.getListaUnidades(montaComboUnidadeCadastradas);

}function montaComboUnidadeCadastradas(listBeans){
	DWRUtil.removeAllOptions("comboUnidade");
	DWRUtil.addOptions("comboUnidade", listBeans, "idUsuario","nome");
}

/*
 * Cadastra uma unidade no sistema do helpdesk
 * */
function cadastrarUmaUnidade(){
	var unidadeSelecionadaBDTRE = DWRUtil.getValue("comboUnidadeTRE"); 
	if(unidadeSelecionadaBDTRE==null || unidadeSelecionadaBDTRE==''){
		alert("Selecione uma unidade a ser cadastrada.");
	}else{
		FacadeAjax.cadastrarUnidadeDefault(unidadeSelecionadaBDTRE);
		//carregarListaUnidadesCadastradas();
		carregarListaUnidadesTRE();
	}
}

/*
 * Carrega ps campos do formato das unidades (suporte e tipo de gerencia)
 */
function carregarFormatoDaUnidade(){
	var unidadeSelecionada = DWRUtil.getValue("comboUnidade");
	if (unidadeSelecionada!=null || unidadeSelecionada!=''){
		tratarSuporte(unidadeSelecionada);
	}else{
		$("formatoSuporte").style.display = "none";
		$("formatoBotao").style.display = "none";
	}
}

/*
 * Trata adequadamente as opcoes concernentes as unidades de suportes
 */
function tratarSuporte(unidade){
	FacadeAjax.isSuporte(unidade, function(booleano) {
		DWRUtil.setValue("checkSuporte",booleano);
		exibirOcultarGerencia();
		$("formatoBotao").style.display = "";
		$("formatoSuporte").style.display = "";
	});
}

/*Exibe ou oculta o select referente ao tipo de gerencia.
 * Exibe quando o check box de suporte está marcado
 * Oculta quando o check box de suporte não está marcado
 * */
function exibirOcultarGerencia(){
	unidade = DWRUtil.getValue("comboUnidade");
	valorCheck = DWRUtil.getValue("checkSuporte");
	if(valorCheck){
		$("formatoGerencia").style.display = "";
		FacadeAjax.getTiposGerenciaMeFirst(unidade,function(listBeans){
			DWRUtil.removeAllOptions("comboGerencia");
			DWRUtil.addOptions("comboGerencia", listBeans);
		})
	}else{
		$("formatoGerencia").style.display = "none";
	}
}

/**
 * Alera o formato da unidade. Tipo de alteracao:
 * - tipo de suporte (é de suporte ou naum)
 * - tipo de gerencia quando ela é de suporte (mista, delegacao, apropriacao)
 */
function alterarFormatoUnidade(){
 	dwr.util.useLoadingMessage();
 	var unidadeSelecionada = DWRUtil.getValue("comboUnidade");
 	if (unidadeSelecionada!=null || unidadeSelecionada!=''){
		var valorCheck = DWRUtil.getValue("checkSuporte");
		FacadeAjax.isSuporte(unidadeSelecionada, function(booleano) {
			if( booleano!=valorCheck && (confirm("Tem certeza que quer alterar o tipo da unidade "+unidadeSelecionada+" ?"))){
				    FacadeAjax.alterarTipoSuporte(unidadeSelecionada,valorCheck);
			}
		});
 	}else{
 		alert("Selecione uma unidade.");
 	}
 	carregarFormatoDaUnidade();
}

function alterarGerencia(){
	var unidadeSelecionada = DWRUtil.getValue("comboUnidade");
	var gerencia = DWRUtil.getValue("comboGerencia");
	FacadeAjax.alterarGerencia(unidadeSelecionada,gerencia);
}

