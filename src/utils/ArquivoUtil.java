package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static String caminhoArquivo;

    public ArquivoUtil(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvarListaNoArquivo(List<String> lista) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : lista) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("arquivo salvo com sucesso! " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static  List<String> lerArquivo() {
        List<String> conteudo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return conteudo;

    }


}
