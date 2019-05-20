package transacao;

import util.Data;
/**
 * Classe que representa uma Visita
 * @author arthur.farias
 *
 */
public class Visita extends Transacao{

	public String idTecnico;
	public String intinerario;
	public String imprevistos;
	public boolean cabeamentoEstruturado;
	public boolean layoutDoAmbiente;
	public boolean tensaoDosEstabilizadores;
	public boolean instalacoesEletricas;
	public boolean limpezaDosMicros;
	public boolean tensaoDosNoBreaks;
	public boolean instalacoesFisicas;
	public boolean limpezaDasImpressoras;
	public boolean organizacaoDosCabos;
	public boolean imagensDosMicros;
	public String outrosString;
	public boolean outros;
	public String servicosRealizados;
	public String pendencias;
	
	public Visita() {
	}
	
	public Visita(String idTecnico, String intinerario, String imprevistos, boolean cabeamentoEstuturado, 
			boolean layoutDoAmbiente, boolean tensaoDosEstabilizadores,	boolean instalacoesEletricas,
			boolean limpezaDosMicros, boolean tensaoDosNoBreaks, boolean instalacoesFisicas,
			boolean limpezaDasImpressoras, boolean organizacaoDosCabos,	boolean imagensDosMicros,
			String outrosString, boolean outros, String servicosRealizados, String pendencias) {
		super(VISITA,true);
		setIdTecnico(idTecnico);
		setIntinerario(intinerario);
		setImprevistos(imprevistos);
		setCabeamentoEstruturado(cabeamentoEstuturado);
		setLayoutDoAmbiente(layoutDoAmbiente);
		setTensaoDosEstabilizadores(tensaoDosEstabilizadores);
		setInstalacoesEletricas(instalacoesEletricas);
		setLimpezaDosMicros(limpezaDosMicros);
		setTensaoDosNoBreaks(tensaoDosNoBreaks);
		setInstalacoesFisicas(instalacoesFisicas);
		setLimpezaDasImpressoras(limpezaDasImpressoras);
		setOrganizacaoDosCabos(organizacaoDosCabos);
		setImagensDosMicros(imagensDosMicros);
		setOutrosString(outrosString);
		setOutros(outros);
		setServicosRealizados(servicosRealizados);
		setPendencias(pendencias);
	}

	public String getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(String idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getIntinerario() {
		return intinerario;
	}

	public void setIntinerario(String intinerario) {
		this.intinerario = intinerario;
	}

	public String getImprevistos() {
		return imprevistos;
	}

	public void setImprevistos(String imprevistos) {
		this.imprevistos = imprevistos;
	}

	public boolean isCabeamentoEstruturado() {
		return cabeamentoEstruturado;
	}

	public void setCabeamentoEstruturado(boolean cabeamentoEstruturado) {
		this.cabeamentoEstruturado = cabeamentoEstruturado;
	}

	public boolean isLayoutDoAmbiente() {
		return layoutDoAmbiente;
	}

	public void setLayoutDoAmbiente(boolean layoutDoAmbiente) {
		this.layoutDoAmbiente = layoutDoAmbiente;
	}

	public boolean isTensaoDosEstabilizadores() {
		return tensaoDosEstabilizadores;
	}

	public void setTensaoDosEstabilizadores(boolean tensaoDosEstabilizadores) {
		this.tensaoDosEstabilizadores = tensaoDosEstabilizadores;
	}

	public boolean isInstalacoesEletricas() {
		return instalacoesEletricas;
	}

	public void setInstalacoesEletricas(boolean instalacoesEletricas) {
		this.instalacoesEletricas = instalacoesEletricas;
	}

	public boolean isLimpezaDosMicros() {
		return limpezaDosMicros;
	}

	public void setLimpezaDosMicros(boolean limpezaDosMicros) {
		this.limpezaDosMicros = limpezaDosMicros;
	}

	public boolean isTensaoDosNoBreaks() {
		return tensaoDosNoBreaks;
	}

	public void setTensaoDosNoBreaks(boolean tensaoDosNoBreaks) {
		this.tensaoDosNoBreaks = tensaoDosNoBreaks;
	}

	public boolean isInstalacoesFisicas() {
		return instalacoesFisicas;
	}

	public void setInstalacoesFisicas(boolean instalacoesFisicas) {
		this.instalacoesFisicas = instalacoesFisicas;
	}

	public boolean isLimpezaDasImpressoras() {
		return limpezaDasImpressoras;
	}

	public void setLimpezaDasImpressoras(boolean limpezaDasImpressoras) {
		this.limpezaDasImpressoras = limpezaDasImpressoras;
	}

	public boolean isOrganizacaoDosCabos() {
		return organizacaoDosCabos;
	}

	public void setOrganizacaoDosCabos(boolean organizacaoDosCabos) {
		this.organizacaoDosCabos = organizacaoDosCabos;
	}

	public boolean isImagensDosMicros() {
		return imagensDosMicros;
	}

	public void setImagensDosMicros(boolean imagensDosMicros) {
		this.imagensDosMicros = imagensDosMicros;
	}

	public String getOutrosString() {
		return outrosString;
	}

	public void setOutrosString(String outrosString) {
		this.outrosString = outrosString;
	}

	public boolean isOutros() {
		return outros;
	}

	public void setOutros(boolean outros) {
		this.outros = outros;
	}

	public String getServicosRealizados() {
		return servicosRealizados;
	}

	public void setServicosRealizados(String servicosRealizados) {
		this.servicosRealizados = servicosRealizados;
	}

	public String getPendencias() {
		return pendencias;
	}

	public void setPendencias(String pendencias) {
		this.pendencias = pendencias;
	}
	
}
