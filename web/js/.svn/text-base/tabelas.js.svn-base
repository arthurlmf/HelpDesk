
function cleanAddOptions(local){
	DWRUtil.removeAllOptions(local);
}

/**
* Função que acessa o método da classe java(FacadeAjax), e retorna
* como callback para a função montaXXX();
*/
function carregaComboTipo(idUnidade){
	limpaCampos();
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
	//carregaComboSubTipo();
	carregaEdicaoSubTipos();
	
}


/*carrega a lista dos subtipos*/
function carregaComboSubTipo(){
	var idUnidade = DWRUtil.getValue("comboUnidade");
	var idTipo = DWRUtil.getValue("comboTipo");
	FacadeAjax.getListaSubTipo(idUnidade,idTipo,montaComboSubTipo);
}	


/*monta os combos de subtipo*/
function montaComboSubTipo(listBeans){
	DWRUtil.removeAllOptions("comboSubTipo");
	DWRUtil.addOptions("comboSubTipo", listBeans, "nomeSubTipo");
}

/*infromar se um determinado subtipo de um tipo de uma unidade tem patrimonio*/
function mostraPatrimonio(idUnidade,tipo,subtipo){
	return FacadeAjax.temPatrimonio(idUnidade, tipo, subtipo);
}

/*monta os combos da unidade*/
function montaComboUnidade(listBeans){
	DWRUtil.removeAllOptions("comboUnidade");
	DWRUtil.addOptions("comboUnidade", listBeans, "idUsuario","nome");
}


/*carrega o combo das unidades*/
function carregarUnidades(){
	FacadeAjax.getListaUnidades(montaComboUnidade); 
}



function carregaEdicaoTipos(){
 	dwr.util.useLoadingMessage();
 	var idUnidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.getListaTipo(idUnidade, function(listBeans){
			dwr.util.removeAllRows("tipoBody", { filter:function(tr) {
      		return (tr.id != "pattern");
    	}
    	});
		var tipo;
		for (var i = 0; i < listBeans.length; i++) {
	 		tipo = listBeans[i];
	 		id = tipo.nomeTipo;
      		dwr.util.cloneNode("pattern", { idSuffix:id });
      		dwr.util.setValue("tableNomeTipo" + id, tipo.nomeTipo);
        	$("pattern" + id).style.display = "";
	 	}
	 	}); 
	
	 carregaComboTipo(idUnidade);
	 carregaEdicaoSubTipos();
}


var viewed = null;
function editClicked(eleid) {
  // we were an id of the form "edit{id}", eg "edit42". We lookup the "42"
  var idUnidade = DWRUtil.getValue("comboUnidade");
  var tipoSelecionado = FacadeAjax.getTipo(idUnidade,eleid.substring(4));
  DWRUtil.setValue("nomeTipo", eleid.substring(4));
  DWRUtil.setValues(tipoSelecionado);
  viewed = eleid.substring(4);
  DWRUtil.setValue("editarCriarTipo","Editar");
}


function deleteClicked(eleid) {
  // we were an id of the form "delete{id}", eg "delete42". We lookup the "42"
   var idUnidade = DWRUtil.getValue("comboUnidade");
   var tiponome = eleid.substring(6);

  if (confirm("Você tem certeza que deletar o tipo " + tiponome + "?")) {
    dwr.engine.beginBatch();
    FacadeAjax.deletarTipo(idUnidade, tiponome);
    carregaEdicaoTipos(idUnidade);
    dwr.engine.endBatch();
    viewed = null;
  }
}


function writeTipo() {
 // var tipo = viewed;
// var person = { id:viewed, name:null, address:null, salary:null };
  
  var idUnidade = DWRUtil.getValue("comboUnidade");
  var tipoSelecionado = viewed;
  var tipoAlterado = DWRUtil.getValue("nomeTipo");
 
  dwr.engine.beginBatch();
  	if (tipoSelecionado != null){//quer alterar um tipo
  		FacadeAjax.alterarTipo(idUnidade,tipoSelecionado,tipoAlterado);
  		DWRUtil.setValue("editarCriarTipo","Criar");
	}else{//vai criar um tipo
  		FacadeAjax.criarTipo(idUnidade, tipoAlterado);
  	}
  	
  	limpaCampos();
  	carregaEdicaoTipos(idUnidade);
  	viewed = null;
	dwr.engine.endBatch();	
	
 
}



// --------------- Subtipo



function carregaEdicaoSubTipos(){
 dwr.util.useLoadingMessage();
 var idUnidade = DWRUtil.getValue("comboUnidade");
 var idTipo = DWRUtil.getValue("comboTipo");
	 FacadeAjax.getListaSubTipo(idUnidade,idTipo, function(listBeans){
	 	dwr.util.removeAllRows("subtipoBody", { filter:function(tr) {
      		return (tr.id != "patternSubtipo");
    	}});
		var subtipo;
		for (var i = 0; i < listBeans.length; i++) {
	 		subtipo = listBeans[i];
	 		id = subtipo.nomeSubTipo;
      		dwr.util.cloneNode("patternSubtipo", { idSuffix:id });
      		dwr.util.setValue("tableSubtipo" + id, subtipo.nomeSubTipo);
      		dwr.util.setValue("colunaPatrimonio"+id,subtipo.temPatrimonio);
        	$("patternSubtipo" + id).style.display = "";
	 	}
	 }); 
	 //carregaComboTipo(idUnidade);
}




 var subtipoViewed = null;
function editSubtipoClicked(eleid) {
  // we were an id of the form "editSubTipo{id}", eg "edit42". We lookup the "42"
  var idUnidade = DWRUtil.getValue("comboUnidade");
  var idTipo = DWRUtil.getValue("comboTipo");
  FacadeAjax.getSubTipo(idUnidade,idTipo,eleid.substring(11),
  			function(subtipoSelecionado){
  				DWRUtil.setValue("textNomeSubTipo", subtipoSelecionado.nomeSubTipo);
  				DWRUtil.setValue("checkboxSubtipo",subtipoSelecionado.temPatrimonio);
				subtipoViewed = eleid.substring(11);
				DWRUtil.setValue("editarCriarSubTipo","Editar");
		   })
}


function deleteSubtipoClicked(eleid) {
  // we were an id of the form "deleteSubtipo{id}", eg "delete42". We lookup the "42"
   var idUnidade = DWRUtil.getValue("comboUnidade");
   var idTipo = DWRUtil.getValue("comboTipo");
   var subtipoNome = eleid.substring(13);

  if (confirm("Você tem certeza que deseja deletar o subtipo " + subtipoNome + " do tipo "+idTipo+"?")) {
    dwr.engine.beginBatch();
    FacadeAjax.deletarSubTipo(idUnidade, idTipo,subtipoNome);
    carregaEdicaoSubTipos();
    dwr.engine.endBatch();
    limpaCampos();
    subtipoViewed = null;
  }
}


function writeSubtipo() {
 
  var idUnidade = DWRUtil.getValue("comboUnidade");
  var idTipo = DWRUtil.getValue("comboTipo");
  
  var subtipoSelecionado = subtipoViewed;
  var subtipoAlterado = DWRUtil.getValue("textNomeSubTipo");
  var patrimonioTem = DWRUtil.getValue("checkboxSubtipo");
 
    dwr.engine.beginBatch();
    if (subtipoSelecionado != null){//quer alterar um tipo
  		FacadeAjax.alterarSubTipo(idUnidade,idTipo,subtipoSelecionado,subtipoAlterado,patrimonioTem);
  		DWRUtil.setValue("editarCriarSubTipo","Criar");
	}else{//vai criar um subtipo
    	FacadeAjax.criarSubTipo(idUnidade, idTipo,subtipoAlterado,patrimonioTem);
  	}
  	limpaCampos();
  	subtipoViewed = null;
  	carregaEdicaoSubTipos();	
	dwr.engine.endBatch();
	
 
}

function limpaCampos(){
	DWRUtil.setValue("nomeTipo", null);//esvaziar o text nomeTipo
	DWRUtil.setValue("textNomeSubTipo", null);
	DWRUtil.setValue("checkboxSubtipo",false);//marcar false
	DWRUtil.setValue("editarCriarTipo","Criar");
	DWRUtil.setValue("editarCriarSubTipo","Criar");
}



function carregaUnidadesSolicitantes(){
 //	dwr.util.useLoadingMessage();
 	var idUnidade = DWRUtil.getValue("comboUnidade");
	FacadeAjax.getListaSolicitantes(idUnidade, function(listBeans){
			dwr.util.removeAllRows("solicitanteBody", { filter:function(tr) {
      		return (tr.id != "patternSolcitante");
    	}
    	});
		var unidade;
		for (var i = 0; i < listBeans.length; i++) {
	 		unidade = listBeans[i];
	 		id = unidade.idUsuario;
      		dwr.util.cloneNode("patternSolcitante", { idSuffix:id });
      		dwr.util.setValue("tableSolicitante" + id, unidade.nome);
        	$("patternSolcitante" + id).style.display = "";
	 	}
	 	}); 
	
}

function adicionaSolicitante(){


  var idUnidade = DWRUtil.getValue("comboUnidade");
  
  var newSolicitante = DWRUtil.getValue("selectSolicitante");
 
    dwr.engine.beginBatch();
   	FacadeAjax.adicionaUnidadeSolicitante(idUnidade, newSolicitante);
	carregaUnidadesSolicitantes();
	dwr.engine.endBatch();	

}

function deleteSolicitante(eleid){

  var idUnidade = DWRUtil.getValue("comboUnidade");
  // we were an id of the form "deleteSolicitante{id}", eg "deleteSolicitante42". We lookup the "42"
   var solicitante = eleid.substring(17);
 
   if (confirm("Você tem certeza que deseja tirar o suporte a unidade " + solicitante+"?")) {
    dwr.engine.beginBatch();
    FacadeAjax.tirarUnidadeSolicitante(idUnidade, solicitante);
	carregaUnidadesSolicitantes();
    dwr.engine.endBatch();
 
  }
 
}

