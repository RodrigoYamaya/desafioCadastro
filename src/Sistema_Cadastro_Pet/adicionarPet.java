package Sistema_Cadastro_Pet;

import utilities.ArquivoUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class adicionarPet {
    private static final String PASTA_PET = "PETS_CADASTRO";
    private static final String FORMULARIO = "Formulario.txt";

    public void cadastrarPet(List<String> resposta) {
        if (resposta == null || resposta.isEmpty()) {
            System.out.println("Erro: Nenhuma resposta foi fornecida!");
            return;
        }

        String nomePet = resposta.get(0).replace(" ", "").toUpperCase();

        File userFolder = new File(PASTA_PET);
        if (!userFolder.exists()) {
            userFolder.mkdir();
        }

        File[] arquivosExistentes = userFolder.listFiles((dir, name) -> name.endsWith(".txt"));
        int numeroArquivo = (arquivosExistentes != null) ? arquivosExistentes.length + 1 : 1;

        String caminhoArquivo = PASTA_PET + File.separator + numeroArquivo + "_" + nomePet + ".txt";

        ArquivoUtil.escreverlinhasArquivo(caminhoArquivo, resposta);
        System.out.println("Pet cadastrado com sucesso: " + caminhoArquivo);
    }

    public List<String> capturarResposta() {
        Scanner sc = new Scanner(System.in);
        List<String> respostas = new ArrayList<>();
        List<String> perguntas = ArquivoUtil.lerLinhasArquivo(FORMULARIO);

        if (perguntas == null || perguntas.isEmpty()) {
            System.out.println("Erro: Nenhuma pergunta foi cadastrada no formulário!");
            return respostas;
        }

        for (String pergunta : perguntas) {
            System.out.println(pergunta);
            System.out.print("Resposta: ");
            respostas.add(sc.nextLine());
        }

        return respostas;
    }
}
