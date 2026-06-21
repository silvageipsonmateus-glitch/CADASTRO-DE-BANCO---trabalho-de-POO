public class ContaPoupanca extends Conta {
 
    private double taxaRendimentoMensal;
 
    public ContaPoupanca(int numero, Cliente titular, double saldoInicial, double taxaRendimentoMensal) {
        super(numero, titular, saldoInicial);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }
 
    public double getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }
 
    public void setTaxaRendimentoMensal(double taxaRendimentoMensal) {
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }
 
    @Override
    public double calcularRendimento() {
        return getSaldo() * taxaRendimentoMensal;
    }
 
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