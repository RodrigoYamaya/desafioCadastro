package utils;

import model.Pet;
import model.SexoPet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArquivoUtil {

    private static final String CAMINHO_PASTA = "PETS_CADASTRADOS";
    private static String caminhoArquivo;

    public ArquivoUtil(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
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

    public static void escritaArquivoPet(Pet pet, String nomeArquivo) {
        File pasta = new File(CAMINHO_PASTA);
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        File arquivo = new File(pasta, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("nome: " + pet.getPetNome());
            writer.newLine();
            writer.write("Tipo: " + pet.getTipoPet());
            writer.newLine();
            writer.write("Sexo: " + pet.getSexoPet());
            writer.newLine();
            writer.write("Endereço: " + pet.getEndereco());
            writer.newLine();
            writer.write("Idade: " + pet.getIdade());
            writer.newLine();
            writer.write("Peso: " + pet.getPesoPet());
            writer.newLine();
            writer.write("Raca: " + pet.getPetRaca());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
        }
    }


    public static File encontrarArquivoDoPet(Pet petOrigens) {
        File pasta = new File(CAMINHO_PASTA);
        if (!pasta.exists() || !pasta.isDirectory()) {
            return null;
        }

        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".txt"));

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    Pet petArquivo = new Pet();

                    while ((linha = br.readLine()) != null) {
                        linha = linha.trim();
                        if (linha.isEmpty()) continue;

                        int separator = linha.indexOf(":");
                        if (separator <= 0) continue;

                        String chave = linha.substring(0, separator).trim().toLowerCase();
                        String valor = linha.substring(separator + 1).trim();

                        switch (chave) {
                            case "nome":
                                petArquivo.setPetNome(valor);
                                break;
                            case "endereço":
                                petArquivo.setEndereco(valor);
                                break;
                            case "idade":
                                petArquivo.setIdade(Double.parseDouble(valor));
                                break;
                            case "peso":
                                petArquivo.setPesoPet(Double.parseDouble(valor));
                                break;
                            case "raça":
                                petArquivo.setPetRaca(valor);
                                break;
                        }
                    }

                    if (
                            petArquivo.getPetNome().equalsIgnoreCase(petOrigens.getPetNome()) &&
                                    Objects.equals(petArquivo.getEndereco(), petOrigens.getEndereco()) &&
                                    Double.compare(petArquivo.getIdade(), petOrigens.getIdade()) == 0 &&
                                    Double.compare(petArquivo.getPesoPet(), petOrigens.getPesoPet()) == 0 &&
                                    Objects.equals(petArquivo.getPetRaca(), petOrigens.getPetRaca())
                    ) {
                        return arquivo;
                    }

                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
                }
            }
        }

        return null;
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

}