package utils;

import model.Pet;
import model.SexoPet;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArquivoUtil {

    private static String caminhoArquivo;
    private static final String CAMINHO_PASTA = "PETS_CADASTRADOS";

    public ArquivoUtil(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvarListaNoArquivo(List<String> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : lista) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("arquivo salvo com sucesso! " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static List<String> lerArquivo() {
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

    public static List<Pet> lerPetsArquivo() {
        List<Pet> listaPets = new ArrayList<>();
        File pasta = new File(CAMINHO_PASTA);

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Pasta 'PETS_CADASTRADOS' não encontrada.");
            return listaPets;
        }

        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo de pet encontrado na pasta.");
            return listaPets;
        }

        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                Pet pet = new Pet();
                String linha;

                while ((linha = br.readLine()) != null) {
                    linha = linha.trim();
                    if (linha.isEmpty()) continue;


                    int separatorIndex = linha.indexOf(":");
                    if (separatorIndex <= 0) continue;

                    String chave = linha.substring(0, separatorIndex).trim().toLowerCase();
                    String valor = linha.substring(separatorIndex + 1).trim();

                    switch (chave) {
                        case "nome":
                            pet.setPetNome(valor);
                            break;
                        case "tipo":
                            pet.setTipoPet(valor.toLowerCase());
                            break;
                        case "sexo":
                            try {
                                pet.setSexoPet(SexoPet.valueOf(valor.toUpperCase()));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Sexo inválido no arquivo " + arquivo.getName() + ": " + valor);
                            }
                            break;
                        case "endereço":
                            pet.setEndereco(valor);
                            break;
                        case "idade":
                            try {
                                pet.setIdade(Double.parseDouble(valor));
                            } catch (NumberFormatException e) {
                                System.out.println("Idade inválida no arquivo " + arquivo.getName() + ": " + valor);
                            }
                            break;
                        case "peso":
                            try {
                                pet.setPesoPet(Double.parseDouble(valor));
                            } catch (NumberFormatException e) {
                                System.out.println("Peso inválido no arquivo " + arquivo.getName() + ": " + valor);
                            }
                            break;
                        case "raça":
                            pet.setPetRaca(valor);
                            break;
                    }
                }

                if (pet.getPetNome() != null && pet.getTipoPet() != null) {
                    listaPets.add(pet);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }
        }

        return listaPets;
    }
}