package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FormularioUtil {
    private static final String CAMINHO_FORMULARIO = "formulario.txt";

    public static void criarFormulario() {
        List<String> perguntas = new ArrayList<>(List.of(
                "1 - Qual o nome e sobrenome do pet?",
                "2 - Qual o tipo do pet (Cachorro/Gato)?",
                "3 - Qual o sexo do animal?",
                "4 - Qual o número da casa?",
                "5 - Qual a cidade?",
                "6 - Qual a rua?",
                "7 - Qual a idade aproximada do pet?",
                "8 - Qual o peso aproximado do pet?",
                "9 - Qual a raça do pet?"

        ));

       ArquivoUtil arquivoUtil = new ArquivoUtil(CAMINHO_FORMULARIO);
       arquivoUtil.salvarListaNoArquivo(perguntas);
    }

    public static List<String> lerFormulario() {
        ArquivoUtil arquivoUtil = new ArquivoUtil(CAMINHO_FORMULARIO);
        return ArquivoUtil.lerArquivo();
    }
}
