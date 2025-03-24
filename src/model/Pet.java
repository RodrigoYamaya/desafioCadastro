package model;


public class Pet {
    private String petNome;
    private String tipoPet;
    SexoPet sexoPet;
    private String endereco;
    private Integer idade;
    private Double pesoPet;
    private String petRaca;

    private static final String NAO_INFORMADO = "NÃO INFORMADO";
    private static final String PASTA_PETS = "PETS_CADASTRADOS";

    public Pet(String petNome, String tipoPet, SexoPet sexoPet, String endereco, Integer idade, Double pesoPet, String petRaca) {
        this.petNome = (petNome == null || petNome.trim().isEmpty() ? NAO_INFORMADO:petNome);
        this.tipoPet = (tipoPet == null || tipoPet.trim().isEmpty() ? NAO_INFORMADO:tipoPet);
        this.sexoPet = sexoPet;
        this.endereco = endereco == null || endereco.trim().isEmpty()? NAO_INFORMADO:endereco;
        this.idade = (idade == null) ? 0 : idade;
        this.pesoPet = (pesoPet == null) ? 0.0 : pesoPet ;
        this.petRaca = (petRaca ==  null || petRaca.trim().isEmpty()? NAO_INFORMADO: petRaca);
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
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

    @Override
    public String toString() {
        return String.format("Pet [Nome: %s, Tipo: %s, Sexo: %s, Endereço: %s, Idade: %d, Peso: %.2f, Raça: %s]",
                petNome, tipoPet, sexoPet, endereco, idade, pesoPet, petRaca);
    }
}



