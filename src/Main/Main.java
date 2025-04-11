package Main;

import services.BuscarPet;
import services.CadastrarPet;
import services.ListagensPets;
import services.alterarCadastroPet;
import utils.FormularioUtil;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        FormularioUtil.criarFormulario();
        CadastrarPet cadastrarPet = new CadastrarPet();
        BuscarPet buscarPet = new BuscarPet();



        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1- Cadastrar um novo pet");
            System.out.println("2- Alterar os dados do pet cadastrado");
            System.out.println("3- Deletar um pet cadastrado");
            System.out.println("4- Listar todos os pets cadastrados");
            System.out.println("5- Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6- Sair");
            System.out.print("Escolha uma opção: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número entre 1 e 6.");
                sc.next();
                continue;
            }

            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao < 1 || opcao > 6) {
                System.out.println("Opção inválida! Escolha um número entre 1 e 6.");
                continue;
            }

            switch (opcao) {
                case 1:
                    try {
                        cadastrarPet.cadastraPet(sc);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro no cadastro: " + e.getMessage());
                    }
                    break;

                case 2:
                    alterarCadastroPet.alterarPet();
                    break;

                case 3:
                    break;

                case 4:
                    ListagensPets.listarTodosPets();
                    break;

                case 5:
                    buscarPet.rodarMenuBusca();
                    break;

                case 6:
                    System.out.println("Saindo do sistema...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}