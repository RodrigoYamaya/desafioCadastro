package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    public static void escreverlinhasArquivo(String caminho, List<String> conteudo) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
            for (String linha : conteudo) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever  no arquivo" + e.getMessage());
        }

    }


    public static void adicionarLinhaArquivo(String caminhoArquivo, String novalinha) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(novalinha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao adicionar linha" + e.getMessage());
        }

    }

    public static List<String> lerLinhasArquivo(String caminho) {
        List<String> linhas = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha.trim());
            }
        } catch (IOException e) {
            System.out.println("error ao escrever linha" + e.getMessage());
        }
        return linhas;
    }
}
