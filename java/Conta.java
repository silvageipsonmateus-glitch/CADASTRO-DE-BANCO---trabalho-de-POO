public abstract class Conta {
    private int numero;
    private Cliente titular;
    private double saldo;
    private boolean ativa;
    public Conta(int numero, Cliente titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }
    public int getNumero() {
        return numero;
    }
 
    public void setNumero(int numero) {
        this.numero = numero;
    }
 
    public Cliente getTitular() {
        return titular;
    }
 
    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
 
    public double getSaldo() {
        return saldo;
    }
 
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
 
    public boolean isAtiva() {
        return ativa;
    }
 
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
 
    public boolean depositar(double valor) {
        if (valor <= 0) {
            return false;
        }
        this.saldo += valor;
        return true;
    }
 

    public boolean sacar(double valor) {
        if (valor <= 0 || valor > this.saldo) {
            return false;
        }
        this.saldo -= valor;
        return true;
    }
    public abstract double calcularRendimento();
 
    public abstract String getTipo();
 
    @Override
    public String toString() {
        String status = ativa ? "ATIVA" : "INATIVA";
        return "Conta nº " + numero +
               " | Tipo: " + getTipo() +
               " | Titular: " + titular.getNome() +
               " | Saldo: R$ " + String.format("%.2f", saldo) +
               " | Status: " + status;
    }
}