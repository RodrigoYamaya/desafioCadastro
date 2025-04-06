package utils;
    
import java.util.Scanner;

public class ValidarUtils {
    public int lerNumeroValido(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return -1;
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro válido:");
            }
        }
    }

    public double lerDoubleValido(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) return -1.0;
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número decimal válido:");
            }
        }
    }

    public String lerTextoValido(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Entrada inválida! Digite um texto válido:");
        }
    }
}