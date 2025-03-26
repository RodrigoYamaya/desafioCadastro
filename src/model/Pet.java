package model;


import java.time.LocalDateTime;

public class Pet {
    private String petNome;
    private String tipoPet;
    SexoPet sexoPet;
    private String endereco;
    private double idade;
    private Double pesoPet;
    private String petRaca;
    private LocalDateTime dataCadastro;
    
    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet(String petNome, String tipoPet, SexoPet sexoPet, String endereco, double idade, Double pesoPet, String petRaca) {
        this.petNome = (petNome == null || petNome.trim().isEmpty() ? NAO_INFORMADO:petNome);
        this.tipoPet = (tipoPet == null || tipoPet.trim().isEmpty() ? NAO_INFORMADO:tipoPet);
        this.sexoPet = sexoPet;
        this.endereco = endereco == null || endereco.trim().isEmpty()? NAO_INFORMADO:endereco;
        this.pesoPet = (pesoPet == null) ? 0.0 : pesoPet ;
        this.petRaca = (petRaca ==  null || petRaca.trim().isEmpty()? NAO_INFORMADO: petRaca);

        if(!this.petRaca.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+")) {
            System.out.println("entrada invalida! Digite somente letras. ");
        }

        if (idade > 20) {
            throw new IllegalArgumentException(
                    String.format("Idade inválida: %d anos. O máximo permitido é 20 anos.", idade)
            );
        }

        // Conversão de meses para fração de ano
        double idadeConvertida = idade;

        if (idade > 0 && idade < 12) {
            idadeConvertida = idade / 12.0;  // A conversão de meses para anos

        }

        this.idade = idadeConvertida;


        if(this.pesoPet < 0.5 || this.pesoPet > 60) {
            throw new IllegalArgumentException(
                    String.format("Peso inválido: Deve ser entre 0.5kg e 60kg.", this.pesoPet)
            );
        }

    }

    public String getPetNome() {
        return petNome;
    }

    public void setPetNome(String petNome) {
        this.petNome = petNome;
    }

    public String getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(String tipoPet) {
        this.tipoPet = tipoPet;
    }

    public SexoPet getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(SexoPet sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public Double getPesoPet() {
        return pesoPet;
    }

    public void setPesoPet(Double pesoPet) {
        this.pesoPet = pesoPet;
    }

    public String getPetRaca() {
        return petRaca;
    }

    public void setPetRaca(String petRaca) {
        this.petRaca = petRaca;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return String.format("Pet [Nome: %s, Tipo: %s, Sexo: %s, Endereço: %s, Idade: %d, Peso: %.2f, Raça: %s]",
                petNome, tipoPet, sexoPet, endereco, idade, pesoPet, petRaca);
    }
}



