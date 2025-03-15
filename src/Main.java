import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import Sistema_Cadastro_Pet.adicionarPet;
import Sistema_Cadastro_Pet.adicionarPergunta;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        adicionarPet addPet = new adicionarPet();
        adicionarPergunta addPergunta = new adicionarPergunta();



        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1- Cadastrar um novo pet");
            System.out.println("2- Buscar dados do pet cadastrado");
            System.out.println("3- Deletar um pet cadastrado");
            System.out.println("4- Listar todos os pets cadastrados");
            System.out.println("5- Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n=== Cadastro de Pet ===");
                    List<String> respostas = addPet.capturarResposta();
                    addPet.cadastrarPet(respostas);
                    break;
            }




        }



    }
}
