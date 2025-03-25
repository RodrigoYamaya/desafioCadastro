package services;


import model.Pet;
import model.SexoPet;
import utils.ArquivoUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {
    private static final String PASTA_PETS = "PETS_CADASTRADOS";


    public static Pet cadastraPet(Scanner scanner) {

        List<String> perguntas = ArquivoUtil.lerArquivo();
        String[] respostas = new String[perguntas.size()];

        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(perguntas.get(i));
            System.out.print("Resposta: ");


            if (i == 6) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Erro: Idade deve ser um número inteiro. Ex: 5");
                    scanner.next();
                    System.out.print("Digite novamente: ");
                }
                respostas[i] = String.valueOf(scanner.nextInt());
                scanner.nextLine();
            }
            else if (i == 7) {
                while (!scanner.hasNextDouble()) {
                    System.out.println("Erro: Peso deve ser um número. Ex: 5.5 ou 5,5");
                    scanner.next();
                    System.out.print("Digite novamente: ");
                }
                respostas[i] = String.valueOf(scanner.nextDouble());
                scanner.nextLine();
            }
            else {
                respostas[i] = scanner.nextLine().trim();
            }

            if (i == 0) {
                validarNomePet(respostas[i]);
            }
        }

        Pet novoPet = new Pet(respostas[0],
                respostas[1],
                SexoPet.valueOf(respostas[2].toUpperCase()),
                respostas[3] + " , " + respostas[4] + " , " + respostas[5],
                Double.parseDouble(respostas[6]),
                Double.parseDouble(respostas[7]),
                respostas[8]

        );
        salvarPet(novoPet);

        return novoPet;


    }

    private static void validarNomePet(String petNome) {
        if (petNome == null || petNome.trim().isEmpty() || !petNome.contains(" ") || !petNome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
            throw new IllegalArgumentException("O pet deve ter nome e sobrenome.");
        }
    }

    public static void salvarPet(Pet pet) {
        File pastaPets = new File(PASTA_PETS);
        if (!pastaPets.exists()) {
            pastaPets.mkdir();
        }

        String nomeArquivo = PASTA_PETS + File.separator + pet.getPetNome().replaceAll(" ", "_") + ".txt";

        ArquivoUtil arquivoUtil = new ArquivoUtil(nomeArquivo);
        arquivoUtil.salvarListaNoArquivo(new ArrayList<>(Arrays.asList(
                "Nome: " + pet.getPetNome(),
                "Tipo: " + pet.getTipoPet(),
                "Sexo: " + pet.getSexoPet(),
                "Endereço: " + pet.getEndereco(),
                "Idade: " + pet.getIdade(),
                "Peso: " + pet.getPesoPet(),
                "Raça: " + pet.getPetRaca()
        )));
        System.out.println("pet cadastrado com sucesso: " + nomeArquivo);
    }

}
