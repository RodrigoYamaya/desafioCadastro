package services;

import model.Pet;
import utils.ArquivoUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class alterarCadastroPet {

    private static final Scanner scanner = new Scanner(System.in);

    public static void alterarPet() {
        List<Pet> listaPets = ArquivoUtil.lerPetsArquivo();

        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado encontrado.");
            return;
        }

        System.out.println("=== Lista de Pets Cadastrados ===");
        for (int i = 0; i < listaPets.size(); i++) {
            Pet pet = listaPets.get(i);
            System.out.println((i + 1) + ". " + pet.getPetNome() + " (" + pet.getTipoPet() + ")");
        }

        System.out.print("Digite o número do pet que deseja alterar: ");
        int indiceSelecionado;
        try {
            indiceSelecionado = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (indiceSelecionado < 0 || indiceSelecionado >= listaPets.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Pet petSelecionado = listaPets.get(indiceSelecionado);


        File arquivoAntigo = ArquivoUtil.encontrarArquivoDoPet(petSelecionado);

        System.out.println("=== Alteração de Dados ===");

        System.out.print("Novo nome (deixe em branco para manter): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) petSelecionado.setPetNome(novoNome);

        System.out.print("Novo endereço (deixe em branco para manter): ");
        String novoEndereco = scanner.nextLine();
        if (!novoEndereco.isBlank()) petSelecionado.setEndereco(novoEndereco);

        System.out.print("Nova idade (deixe em branco para manter): ");
        String novaIdade = scanner.nextLine();
        if (!novaIdade.isBlank()) {
            try {
                petSelecionado.setIdade(Double.parseDouble(novaIdade));
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida.");
            }
        }

        System.out.print("Novo peso (deixe em branco para manter): ");
        String novoPeso = scanner.nextLine();
        if (!novoPeso.isBlank()) {
            try {
                petSelecionado.setPesoPet(Double.parseDouble(novoPeso));
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido.");
            }
        }

        System.out.print("Nova raça (deixe em branco para manter): ");
        String novaRaca = scanner.nextLine();
        if (!novaRaca.isBlank()) petSelecionado.setPetRaca(novaRaca);


        if (arquivoAntigo != null && arquivoAntigo.exists()) {
            if (arquivoAntigo.delete()) {
                System.out.println("Arquivo antigo excluído: " + arquivoAntigo.getName());
            } else {
                System.out.println("Não foi possível excluir o arquivo antigo.");
            }
        }


        String dataHoraAtual = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm").format(new Date());
        String nomeNovoArquivo = dataHoraAtual + " - " + petSelecionado.getPetNome().toUpperCase() + ".txt";
        ArquivoUtil.escritaArquivoPet(petSelecionado, nomeNovoArquivo);

        System.out.println("Pet alterado com sucesso!");
    }
}
