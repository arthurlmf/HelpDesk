
package util;

import java.io.Serializable;
import java.util.Calendar;

import excecoes.HelpDeskException;
/**
 * 
 *  
 * Classe que representa uma data comum
 * 
 * 
 * @author Arthur Farias 
 */
public class Data implements Serializable, Comparable {
    private static final long serialVersionUID = 5631663454326635455L;

    /**
     * O maximo valor do ano que esta data pode assumir.
     * @see Calendar#getMaximum(int)
     */
    public static final int MAX_ANO = 292278993;

    /**
     * A data.
     */
    private Calendar data;

    /**
     * Um String na forma da data.
     */
    private String source;

    /**
     * Cria uma data representando agora.
     */
    public Data(){
        data = Calendar.getInstance();
        data.setLenient(false);
    }
 
    /**
     * Cria uma data representando o dia especificado.
     * @param source A string que representa a data desejada. O argumento deve ser da
     *            forma "dd/mm/aaaa..."
     * @throws HelpDeskException 
     * @throws HelpDeskException se a data informada nao existe.
     *
     */
    public Data(String source) throws HelpDeskException{
    		data = Calendar.getInstance();
        data.setLenient(false);	
    	try{
    		source = source.replaceAll("/","");
    		source = source.replaceAll("-","");
    		int dia = Integer.parseInt(source.substring(0,2));
            // 0 janeiro 11 dezembro
            int mes = Integer.parseInt(source.substring(2,4)) - 1;
            int ano = Integer.parseInt(source.substring(4,8));
            data.set(ano, mes, dia);
            this.source = DDMMAAAA();
        }catch(Exception e){
            throw new HelpDeskException(MsgErros.DATA_INVALIDA.toString());
        }
    }
    
    /**
     * Cria uma data representando o dia especificado.
     * @param dia O dia desejado.
     * @param mes O mes desejado.
     * @param ano O ano desejado.
     * @throws HelpDeskException se a data informada nao existe.
     */
    public Data(int dia, int mes, int ano) throws HelpDeskException{
        this(formataNum(dia) + "/" + formataNum(mes) + "/" + formataAno(ano));
    }

    /**
     * Compara a data com outra data.
     * @param o A outra data a comparar esta.
     * @return um valor menor que 0 se esta data for antes da outraData, 0 se esta
     *         data e a outraData forem iguais e um valor maior que 0 se esta data
     *         for depois da outra data;
     * @throws ClassCastException se o argumento nao for uma data.
     */
    public int compareTo(Object o){
        Data outraData = (Data) o;
        if(getAno() < outraData.getAno()){
            return -1;
        }else if(getAno() > outraData.getAno()){
            return 1;
        }else if(getMes() < outraData.getMes()){
            return -1;
        }else if(getMes() > outraData.getMes()){
            return 1;
        }else if(getDia() < outraData.getDia()){
            return -1;
        }else if(getDia() > outraData.getDia()){
            return 1;
        }
        return 0;
    }

    /**
     * Retorna a menor data que pode ser representada, ou seja, 1 de janeiro do ano
     * 1.
     * @return a menor data que pode ser representada, ou seja, 1 de janeiro do ano
     *         1.
     */
    public static Data getMenorData(){
        try{
            return new Data(1, 1, 1);
        }catch(HelpDeskException e){
            System.err.println("N�o pode gerar menor data.");
            return null;
        }
    }

    /**
     * Retorna a maior data que pode ser representada, ou seja, o dia 12 de dezembro
     * do ano 292278993.
     * @return a maior data que pode ser representada, ou seja, o dia 12 de dezembro
     *         do ano 292278993.
     */
    public static Data getMaiorData(){
        try{
            return new Data(31, 12, Data.MAX_ANO);
        }catch(HelpDeskException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna o ano correspondente a esta data.
     * @return O ano desta data.
     */
    public int getAno(){
        return data.get(Calendar.YEAR);
    }

    /**
     * Retorna o m�s correspondente a esta data.
     * @return O m�s desta data.
     */
    public int getMes(){
        return data.get(Calendar.MONTH) + 1;
    }

    /**
     * Retorna um String representando esta data no formato dd/mm/aaaa.
     * @return um String representando esta data no formato dd/mm/aaaa.
     */
    public String DDMMAAAA(){
        return ((getDia() < 10) ? "0" : "") + getDia() + "/" + ((getMes() < 10) ? "0" : "") + getMes() + "/" + getAno();
    }

    /**
     * Retorna o dai correspondente a esta data.
     * @return O dia desta data.
     */
    public int getDia(){
        return data.get(Calendar.DAY_OF_MONTH);
    }
    
    private static String formataNum(int i){
    	return ((i < 10) ? "0" : "") + i;
    }

    private static String formataAno(int a){
    	return ((a < 1000) ? "20" : "") + formataNum(a);
    }
    
    public String getHorario(){
    	return formataNum(data.get(Calendar.HOUR_OF_DAY))+
    	"h "+formataNum(data.get(Calendar.MINUTE))+
    	"min "+formataNum(data.get(Calendar.SECOND))+"s";
    }
    
    public String getHorarioCompacto(){
    	return formataNum(data.get(Calendar.HOUR_OF_DAY))+
    	":"+formataNum(data.get(Calendar.MINUTE));
    	
    }

    /**
     * Retorna esta data.
     * @return retorna esta data.
     */
    public Calendar getData(){
        return data;
    }

    /**
     * Seta esta data.
     * @param cal A data para detar.
     * @throws HelpDeskException Se esta data for invalida.
     */
    public void setData(Calendar cal) throws HelpDeskException{
        try{
            cal.setLenient(false);
            this.data = cal;
            this.source = DDMMAAAA();
        }catch(Exception e){
            throw new HelpDeskException(MsgErros.DATA_INVALIDA.toString());
        }
    }

    /**
     * Retorna a String que representa esta data.
     * @return a String que representa esta data.
     */
    public String getSource(){
        return source;
    }

    /**
     * Seta a String que representa esta data.
     * @param source a nova String que representa esta data.
     */
    public void setSource(String source){
        this.source = source;
    }

    /**
     * Retorna uma representa�ao em forma de String para esta data.
     */
    public String toString(){
        return source;
    }
    
    /**
     * Retorna a diferenca entre as datas
     * @param other
     * @return
     */
    public int diferencaData(Data other){
    	long milisThis = getData().getTimeInMillis();
    	long milisOhter = other.getData().getTimeInMillis();
    	return (int)(milisOhter-milisThis)/(24*60*60*1000);
    }
}
