public class Banco {
    private String nomeBanco;
    private Conta[] contas;     
    private int quantidadeContas;
    private int proximoNumeroConta;

    public Banco(String nomeBanco, int capacidadeMaxima) {
        this.nomeBanco = nomeBanco;
        this.contas = new Conta[capacidadeMaxima]; 
        this.quantidadeContas = 0;
        this.proximoNumeroConta = 1000;
---
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
    public int cadastrarConta(Conta conta) {
        if (quantidadeContas >= contas.length) {
            return -1; // vetor cheio
        }
        contas[quantidadeContas] = conta;
        quantidadeContas++;
        return conta.getNumero();
    }

    public int gerarNumeroConta() {
        int numero = proximoNumeroConta;
        proximoNumeroConta++;
        return numero;
    }
 
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
 
    public Conta buscarContaPorNumero(int numero) {
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i].getNumero() == numero) {
                return contas[i];
            }
        }
        return null; // não encontrada
    }
 
    public Conta[] buscarContasPorCpf(String cpf) {
        Conta[] encontradas = new Conta[quantidadeContas];
        int contador = 0;
 
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i].getTitular().getCpf().equals(cpf)) {
                encontradas[contador] = contas[i];
                contador++;
            }
        }
 
        Conta[] resultado = new Conta[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = encontradas[i];
        }
        return resultado;
    }

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
 
    public double calcularSaldoTotalDoBanco() {
        double total = 0;
        for (int i = 0; i < quantidadeContas; i++) {
            total += contas[i].getSaldo();
        }
        return total;
    }
 
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
 
    public boolean encerrarConta(int numero) {
        Conta conta = buscarContaPorNumero(numero);
        if (conta == null) {
            return false;
        }
        conta.setAtiva(false);
        return true;
    }
}
