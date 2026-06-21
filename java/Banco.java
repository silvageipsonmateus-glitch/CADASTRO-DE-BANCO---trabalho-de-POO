public class Banco {
 
    // ---------- Atributos ----------
    private String nomeBanco;
    private Conta[] contas;     // vetor de contas (array)
    private int quantidadeContas; // controla quantas posições do vetor estão em uso
    private int proximoNumeroConta;
 
    // ---------- Construtor ----------
    public Banco(String nomeBanco, int capacidadeMaxima) {
        this.nomeBanco = nomeBanco;
        this.contas = new Conta[capacidadeMaxima]; // tamanho fixo do vetor
        this.quantidadeContas = 0;
        this.proximoNumeroConta = 1000; // número inicial das contas
    }
 
    // ---------- Getters e Setters ----------
    public String getNomeBanco() {
        return nomeBanco;
    }
 
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
 
    public Conta[] getContas() {
        return contas;
    }
 
    public int getQuantidadeContas() {
        return quantidadeContas;
    }
 
    // ---------- Funcionalidades obrigatórias ----------
 
    /**
     * Cadastra uma nova conta no vetor, caso ainda haja espaço.
     * Retorna o número gerado para a conta, ou -1 se o banco estiver cheio.
     */
    public int cadastrarConta(Conta conta) {
        if (quantidadeContas >= contas.length) {
            return -1; // vetor cheio
        }
        contas[quantidadeContas] = conta;
        quantidadeContas++;
        return conta.getNumero();
    }
 
    /**
     * Gera o próximo número de conta disponível (sequencial).
     */
    public int gerarNumeroConta() {
        int numero = proximoNumeroConta;
        proximoNumeroConta++;
        return numero;
    }
 
    /**
     * Lista todas as contas cadastradas no vetor.
     */
    public void listarContas() {
        if (quantidadeContas == 0) {
            System.out.println("Nenhuma conta cadastrada no momento.");
            return;
        }
        System.out.println("===== LISTA DE CONTAS =====");
        for (int i = 0; i < quantidadeContas; i++) {
            System.out.println((i + 1) + ") " + contas[i]);
        }
    }
 
    /**
     * Busca uma conta pelo número informado.
     * Retorna a conta encontrada ou null se não existir.
     */
    public Conta buscarContaPorNumero(int numero) {
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i].getNumero() == numero) {
                return contas[i];
            }
        }
        return null; // não encontrada
    }
 
    /**
     * Busca todas as contas de um cliente pelo CPF.
     * Como é necessário retornar múltiplos resultados, utiliza-se
     * outro vetor (array) para armazenar as contas encontradas.
     */
    public Conta[] buscarContasPorCpf(String cpf) {
        Conta[] encontradas = new Conta[quantidadeContas];
        int contador = 0;
 
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i].getTitular().getCpf().equals(cpf)) {
                encontradas[contador] = contas[i];
                contador++;
            }
        }
 
        // Cria um vetor do tamanho exato de resultados encontrados
        Conta[] resultado = new Conta[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = encontradas[i];
        }
        return resultado;
    }
 
    /**
     * Mostra todas as contas que estão com saldo negativo
     * (somente é possível em ContaCorrente, devido ao cheque especial).
     */
    public void mostrarContasNegativas() {
        System.out.println("===== CONTAS COM SALDO NEGATIVO =====");
        boolean encontrouAlguma = false;
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i].getSaldo() < 0) {
                System.out.println(contas[i]);
                encontrouAlguma = true;
            }
        }
        if (!encontrouAlguma) {
            System.out.println("Nenhuma conta está com saldo negativo.");
        }
    }
 
    /**
     * Mostra a conta com o maior saldo cadastrado no banco.
     */
    public void mostrarContaComMaiorSaldo() {
        if (quantidadeContas == 0) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        Conta maior = contas[0];
        for (int i = 1; i < quantidadeContas; i++) {
            if (contas[i].getSaldo() > maior.getSaldo()) {
                maior = contas[i];
            }
        }
        System.out.println("Conta com maior saldo:");
        System.out.println(maior);
    }
 
    /**
     * Relatório simples: soma o saldo de todas as contas cadastradas.
     */
    public double calcularSaldoTotalDoBanco() {
        double total = 0;
        for (int i = 0; i < quantidadeContas; i++) {
            total += contas[i].getSaldo();
        }
        return total;
    }
 
    /**
     * Relatório simples: exibe um resumo geral do banco.
     */
    public void exibirRelatorioGeral() {
        System.out.println("===== RELATÓRIO GERAL DO BANCO " + nomeBanco.toUpperCase() + " =====");
        System.out.println("Total de contas cadastradas: " + quantidadeContas);
        System.out.println("Saldo total do banco: R$ " + String.format("%.2f", calcularSaldoTotalDoBanco()));
 
        int totalCorrente = 0;
        int totalPoupanca = 0;
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i] instanceof ContaCorrente) {
                totalCorrente++;
            } else if (contas[i] instanceof ContaPoupanca) {
                totalPoupanca++;
            }
        }
        System.out.println("Contas Correntes: " + totalCorrente);
        System.out.println("Contas Poupança: " + totalPoupanca);
    }
 
    /**
     * Encerra (inativa) uma conta pelo número.
     */
    public boolean encerrarConta(int numero) {
        Conta conta = buscarContaPorNumero(numero);
        if (conta == null) {
            return false;
        }
        conta.setAtiva(false);
        return true;
    }
}
