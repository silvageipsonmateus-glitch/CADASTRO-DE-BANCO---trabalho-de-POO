public class ContaPoupanca extends Conta {
 
    // ---------- Atributo específico ----------
    private double taxaRendimentoMensal; // exemplo: 0.005 = 0,5% ao mês
 
    // ---------- Construtor ----------
    public ContaPoupanca(int numero, Cliente titular, double saldoInicial, double taxaRendimentoMensal) {
        super(numero, titular, saldoInicial);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }
 
    // ---------- Getters e Setters ----------
    public double getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }
 
    public void setTaxaRendimentoMensal(double taxaRendimentoMensal) {
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }
 
    // ---------- Implementação dos métodos abstratos ----------
 
    /**
     * Calcula o rendimento mensal da poupança com base na taxa cadastrada.
     */
    @Override
    public double calcularRendimento() {
        return getSaldo() * taxaRendimentoMensal;
    }
 
    /**
     * Aplica o rendimento diretamente no saldo da conta.
     */
    public void aplicarRendimento() {
        double rendimento = calcularRendimento();
        depositar(rendimento);
    }
 
    @Override
    public String getTipo() {
        return "Poupança";
    }
 
    @Override
    public String toString() {
        return super.toString() + " | Taxa Rendimento: " + String.format("%.2f", taxaRendimentoMensal * 100) + "% a.m.";
    }
}