package services;

import model.Pet;
import utils.ArquivoUtil;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BuscarPet  {

    public List<Pet> rodarMenuBusca() {
        Scanner scanner = new Scanner(System.in);
        List<Pet> listaPets = ArquivoUtil.lerPetsArquivo();

        if (listaPets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return List.of();
        }

        System.out.println("Digite o tipo de animal para buscar (Cachorro/Gato):");
        String tipoAnimal = scanner.nextLine().trim().toLowerCase();

        List<Pet> petsFiltrados = listaPets.stream()
                .filter(pet -> removerAcentos(pet.getTipoPet().toLowerCase())
                        .contains(removerAcentos(tipoAnimal)))
                .collect(Collectors.toList());

        if (petsFiltrados.isEmpty()) {
            System.out.println("Nenhum pet encontrado para o tipo " + tipoAnimal);
            return List.of();
        }

        int opcao;
        do {
            System.out.println("\nSelecione os critérios de busca:");
            System.out.println("1 - Nome ou sobrenome");
            System.out.println("2 - Sexo");
            System.out.println("3 - Idade (em anos)");
            System.out.println("4 - Peso");
            System.out.println("5 - Raça");
            System.out.println("6 - Endereço");
            System.out.println("7 - Nome e idade");
            System.out.println("8 - Idade e peso");
            System.out.println("9 - Mostrar resultados");
            System.out.println("0 - Sair");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            if (opcao < 0 || opcao > 9) {
                System.out.println("Opção inválida! Escolha entre 0 e 9.");
                continue;
            }

            if (opcao == 9) {
                listarPets(petsFiltrados);
                continue;
            }

            if (opcao == 0) {
                return petsFiltrados;
            }

            List<Pet> resultadoBusca = aplicarFiltro(petsFiltrados, opcao, scanner);

            if (!resultadoBusca.isEmpty()) {
                System.out.println("\nResultados da busca:");
                listarPets(resultadoBusca);
                petsFiltrados = resultadoBusca;
            } else {
                System.out.println("Nenhum pet encontrado com os critérios fornecidos.");
            }
        } while (true);
    }

    private List<Pet> aplicarFiltro(List<Pet> pets, int opcao, Scanner scanner) {
        switch (opcao) {
            case 1: return filtrarPorNome(pets, scanner);
            case 2: return filtrarPorSexo(pets, scanner);
            case 3: return filtrarPorIdade(pets, scanner);
            case 4: return filtrarPorPeso(pets, scanner);
            case 5: return filtrarPorRaca(pets, scanner);
            case 6: return filtrarPorEndereco(pets, scanner);
            case 7: return filtrarPorNomeEIdade(pets, scanner);
            case 8: return filtrarPorIdadeEPeso(pets, scanner);
            default: return pets;
        }
    }

    private List<Pet> filtrarPorNome(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite parte do nome ou sobrenome:");
        String nome = scanner.nextLine().trim();
        String nomeBusca = removerAcentos(nome.toLowerCase());

        return pets.stream()
                .filter(pet -> removerAcentos(pet.getPetNome().toLowerCase()).contains(nomeBusca))
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorSexo(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite o sexo (MACHO/FEMEA):");
        String sexo = scanner.nextLine().trim().toUpperCase();

        return pets.stream()
                .filter(pet -> Optional.ofNullable(pet.getSexoPet())
                        .map(Enum::toString)
                        .filter(s -> s.equals(sexo))
                        .isPresent())
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorIdade(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite a idade: ");

        Optional<Double> idadeOpt = Optional.empty();
        while (!idadeOpt.isPresent()) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return pets;
                }
                idadeOpt = Optional.of(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número (ex: 0.5, 1, 2.5):");
            }
        }

        final double idade = idadeOpt.get();
        return pets.stream()
                .filter(pet -> Optional.ofNullable(pet.getIdade())
                        .filter(i -> i.equals(idade))
                        .isPresent())
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorPeso(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite o peso (em kg):");

        Optional<Double> pesoOpt = Optional.empty();
        while (!pesoOpt.isPresent()) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return pets;
                }
                pesoOpt = Optional.of(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número para o peso:");
            }
        }

        final double peso = pesoOpt.get();
        return pets.stream()
                .filter(pet -> Optional.ofNullable(pet.getPesoPet())
                        .filter(p -> p.equals(peso))
                        .isPresent())
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorRaca(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite parte da raça:");
        String raca = scanner.nextLine().trim();
        String racaBusca = removerAcentos(raca.toLowerCase());

        return pets.stream()
                .filter(pet -> Optional.ofNullable(pet.getPetRaca())
                        .map(r -> removerAcentos(r.toLowerCase()))
                        .filter(r -> r.contains(racaBusca))
                        .isPresent())
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorEndereco(List<Pet> pets, Scanner scanner) {
        System.out.println("Digite parte do endereço:");
        String endereco = scanner.nextLine().trim();
        String enderecoBusca = removerAcentos(endereco.toLowerCase());

        return pets.stream()
                .filter(pet -> Optional.ofNullable(pet.getEndereco())
                        .map(e -> removerAcentos(e.toLowerCase()))
                        .filter(e -> e.contains(enderecoBusca))
                        .isPresent())
                .collect(Collectors.toList());
    }

    private List<Pet> filtrarPorNomeEIdade(List<Pet> pets, Scanner scanner) {
        List<Pet> filtradosPorNome = filtrarPorNome(pets, scanner);
        return filtrarPorIdade(filtradosPorNome, scanner);
    }

    private List<Pet> filtrarPorIdadeEPeso(List<Pet> pets, Scanner scanner) {
        List<Pet> filtradosPorIdade = filtrarPorIdade(pets, scanner);
        return filtrarPorPeso(filtradosPorIdade, scanner);
    }

    private void listarPets(List<Pet> pets) {
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }

        System.out.println("\n=== Resultados ===");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.printf("%d. %s - %s - %s - %s - %.1f anos - %.1fkg - %s%n",
                    i + 1,
                    pet.getPetNome(),
                    pet.getTipoPet(),
                    Optional.ofNullable(pet.getSexoPet()).map(Enum::toString).orElse("N/A"),
                    Optional.ofNullable(pet.getEndereco()).orElse("N/A"),
                    Optional.ofNullable(pet.getIdade()).orElse(0.0),
                    Optional.ofNullable(pet.getPesoPet()).orElse(0.0),
                    pet.getPetRaca());
        }
    }

    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }


}