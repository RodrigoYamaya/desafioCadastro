package services;

import model.Pet;
import utils.ArquivoUtil;

import java.util.List;
import java.util.Optional;

public class ListagensPets {
    public static void listarTodosPets() {
        List<Pet> listarTodosPets = ArquivoUtil.lerPetsArquivo();
        if (listarTodosPets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado.");
            return;
        }

        System.out.println("\n=== Todos os Pets Cadastrados ===");
        for (int i = 0; i < listarTodosPets.size(); i++) {
            Pet pet = listarTodosPets.get(i);
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
}
