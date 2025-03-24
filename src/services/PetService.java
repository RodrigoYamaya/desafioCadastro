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
        String[] respostas  = new String[perguntas.size()];

        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(perguntas.get(i) + " ");
            System.out.print("Resposta: ");
            respostas[i] = scanner.nextLine().trim();

            if(i == 0) {
                validarNomePet(respostas[i]);
            }
        }

        Pet novoPet = new Pet(respostas[0],
                respostas[1],
                SexoPet.valueOf(respostas[2].toUpperCase()),
                respostas[3],
                Integer.parseInt(respostas[4]),
                Double.parseDouble(respostas[5]),
                respostas[6]

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
