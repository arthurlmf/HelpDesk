package bdTRE;

import java.io.Serializable;

import util.Data;
/**
 * 
 * <b>HelpDeskTRE</b><br><br>
 *  
 *  
 * Classe que representa um equipamento no banco do tre
 * 
 * 
 * @author Arthur Farias 
 */
public class Equipamento implements Serializable {


	private static final long serialVersionUID = 1L;

	private  String patrimonio;

	private  String descricao;

	private  Data garantia;

	private  String serie;
	
	/**
	 * 
	 * Construtor da classe
	 * @param patrimonio o patrimonio do equipamento
	 * @param descricao a descricao do equipamento
	 * @param data a data do equipamento
	 * @param serie a serie do equipamento
	 */
	public Equipamento(String patrimonio, String descricao, Data data,
			String serie) {
		this.patrimonio = patrimonio;
		this.descricao = descricao;
		this.garantia = data;
		this.serie = serie;
	}
	/**
	 * Construtor default da classe
	 *
	 */
	public Equipamento() {		
	}

	/**
	 * @return Returns the data.
	 */
	public Data getGarantia() {
		return garantia;
	}

	/**
	 * @param data The data to set.
	 */
	public void setGarantia(Data garantia) {
		this.garantia = garantia;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns the patrimonio.
	 */
	public String getPatrimonio() {
		return patrimonio;
	}

	/**
	 * @param patrimonio The patrimonio to set.
	 */
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	/**
	 * @return Returns the serie.
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie The serie to set.
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * Retorna a representacao em string da classe
	 */
	public String  toString(){
		return this.patrimonio + " " + descricao + " " + garantia + " " + serie;
	}

}
