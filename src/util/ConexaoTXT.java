package util;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * 
 *  
 *  
 * Classe que representa uma conexao que faz o read e write de arquivos txt
 * 
 * 
 * @author Arthur Farias 
 */
public class ConexaoTXT implements Conexao {

    private String fileName;//Nome do arquivo para leitura e gravação

    /**
     * Contrutor da classe
     * 
     * @param filename
     *            o nome do arquivo
     */
    public ConexaoTXT(String filename) {
        this.fileName = filename;
        try {
            grava("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Realiza a gravação dem um Object afim de se obter a persistencia dele, a gravacao
     * eh feita em um arquivo .txt
     * 
     * @param obj
     *            o Object que sera gravado
     */
    public void write(Object obj) {
        try {// construct an OutputStreamWriter on a FileOutputStream.
            grava((String) obj);
        } catch (IOException e) {
            System.out.println("Erro ao tentar gravar o arquivo.");
        }
    }

    /**
     * Metodo privado para gravar no arquivo uma String
     * @param string a String a ser gravada
     * @throws IOException se houver algum erro com o arquivo
     */
    private void grava(String string) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(string);
        fw.flush();
        fw.close();
    }
    /**
     * Le um Object de um arquivo .txt
     * 
     * @return O Object lido, ou null se nao for encontrado
     */
    public Object read() {
        try {
            FileReader fw = new FileReader(fileName);
            Scanner sc = new Scanner(fw);
            String relatorio = "";
            while (sc.hasNextLine()) {
                relatorio += sc.nextLine() + "\n";
            }
            fw.close();
            return relatorio;
        } catch (IOException e) {
        }
        return null;
    }

}
