package Sistema_Cadastro_Pet;

import java.io.File;
import java.io.IOException;

public class adicionarPergunta implements ICriarFormulario {
    private static final String CAMINHO_ARQUIVO = "Formulario.txt";  // Arquivo direto no diretório do projeto

    @Override
    public void criarFormularioPadrao() {
        criarArquivo();
    }

    private void criarArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);

        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado com sucesso: " + CAMINHO_ARQUIVO);
            } else {
                System.out.println("O arquivo já existe: " + CAMINHO_ARQUIVO);
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
}
