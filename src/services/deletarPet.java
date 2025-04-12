package services;

import model.Pet;
import utils.ArquivoUtil;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class deletarPet {
    private static final Scanner scanner = new Scanner(System.in);

    public static void deletarPet() {
        List<Pet> listaPetDelete = ArquivoUtil.lerPetsArquivo();
        if (listaPetDelete.isEmpty()) {
            System.out.println("Nenhum pet foi encontrado.");
            return;
        }

        System.out.println("\n=== Lista dos Pets Cadastrados ===");
        for (int i = 0; i < listaPetDelete.size(); i++) {
            Pet pet = listaPetDelete.get(i);
            System.out.println((i + 1) + ". " + pet.getPetNome() + " (" + pet.getTipoPet() + ")");
        }

        int indiceSelecionado = -1;
        while (true) {
            System.out.println("Digite o número do pet que deseja excluir: ");
            try {
                indiceSelecionado = Integer.parseInt(scanner.nextLine()) - 1;

                if (indiceSelecionado >= 0 && indiceSelecionado < listaPetDelete.size()) {
                    break;
                } else {
                    System.out.println("Número inválido. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }

        Pet petSelecionadoDelete = listaPetDelete.get(indiceSelecionado);
        File arquivoPetDelete = ArquivoUtil.encontrarArquivoDoPet(petSelecionadoDelete);


        System.out.print("Tem certeza que deseja deletar o pet \"" + petSelecionadoDelete.getPetNome() + "\"? (SIM/NÃO): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (confirmacao.equals("sim")) {
            if (arquivoPetDelete != null && arquivoPetDelete.exists()) {
                if (arquivoPetDelete.delete()) {
                    System.out.println("Pet deletado com sucesso.");
                } else {
                    System.out.println("Não foi possível excluir o pet.");
                }
            } else {
                System.out.println("Arquivo do pet não encontrado.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
