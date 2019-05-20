
function cleanAddOptions(local){
	DWRUtil.removeAllOptions(local);
}

/**
* Função que acessa o método da classe java(FacadeAjax), e retorna
* como callback para a função montaXXX();
*/

function carregaComboSubTipo(){
	dwr.util.useLoadingMessage();

	FacadeAjax.getUnidadeUsuarioSessao(function(unidadeUsuarioLogado){
		var idTipo = DWRUtil.getValue("comboTipo");		
		FacadeAjax.getListaSubTipo(unidadeUsuarioLogado.idUsuario,idTipo, function (listBeans){
			DWRUtil.removeAllOptions("comboSubTipo");
			DWRUtil.addOptions("comboSubTipo", listBeans, "nomeSubTipo");
		});
	})
}

