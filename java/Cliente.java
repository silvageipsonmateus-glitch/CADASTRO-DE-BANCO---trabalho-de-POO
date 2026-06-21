public class Cliente {
 
    // ---------- Atributos ----------
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
 
    // ---------- Construtor ----------
    public Cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }
 
    // ---------- Getters e Setters ----------
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getCpf() {
        return cpf;
    }
 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
 
    public String getTelefone() {
        return telefone;
    }
 
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
 
    public String getEndereco() {
        return endereco;
    }
 
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
 
    // ---------- Métodos ----------
 
    /**
     * Retorna uma representação textual organizada dos dados do cliente.
     * Sobrescreve o método toString() da classe Object.
     */
    @Override
    public String toString() {
        return "Nome: " + nome +
               " | CPF: " + cpf +
               " | Telefone: " + telefone +
               " | Endereço: " + endereco;
    }
}