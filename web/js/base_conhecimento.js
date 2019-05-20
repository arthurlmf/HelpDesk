
function cleanAddOptions(local){
	DWRUtil.removeAllOptions(local);
}

/**
* Fun��o que acessa o m�todo da classe java(FacadeAjax), e retorna
* como callback para a fun��o montaXXX();
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

