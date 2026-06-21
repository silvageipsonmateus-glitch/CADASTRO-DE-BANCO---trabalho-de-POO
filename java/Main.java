
import java.ultil.scaner;
public class Main {
 
   
    static Scanner scanner = new Scanner(System.in);
 
    static Banco banco = new Banco("Banco Java POO", 100);
 
    public static void main(String[] args) {
        int opcao;
 
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
 
            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    banco.listarContas();
                    break;
                case 3:
                    buscarConta();
                    break;
                case 4:
                    realizarDeposito();
                    break;
                case 5:
                    realizarSaque();
                    break;
                case 6:
                    banco.mostrarContasNegativas();
                    break;
                case 7:
                    banco.mostrarContaComMaiorSaldo();
                    break;
                case 8:
                    banco.exibirRelatorioGeral();
                    break;
                case 9:
                    aplicarRendimentoPoupanca();
                    break;
                case 10:
                    encerrarConta();
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
 
            System.out.println(); // linha em branco para separar as interações
 
        } while (opcao != 0);
 
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE CADASTRO BANCÁRIO - POO");
        System.out.println("========================================");
        System.out.println("1  - Cadastrar nova conta");
        System.out.println("2  - Listar todas as contas");
        System.out.println("3  - Buscar conta pelo número");
        System.out.println("4  - Realizar depósito");
        System.out.println("5  - Realizar saque");
        System.out.println("6  - Mostrar contas com saldo negativo");
        System.out.println("7  - Mostrar conta com maior saldo");
        System.out.println("8  - Relatório geral do banco");
        System.out.println("9  - Aplicar rendimento (Poupança)");
        System.out.println("10 - Encerrar conta");
        System.out.println("0  - Sair");
        System.out.println("========================================");
    }
 
    public static void cadastrarConta() {
        System.out.println("--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
 
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
 
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
 
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
 
        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
 
        System.out.println("--- Tipo de Conta ---");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipo = lerInteiro("Escolha o tipo de conta: ");
 
        double saldoInicial = lerDouble("Saldo inicial de depósito: R$ ");
        int numero = banco.gerarNumeroConta();
 
        Conta novaConta;
 
        if (tipo == 1) {
            double limite = lerDouble("Limite do cheque especial: R$ ");
            novaConta = new ContaCorrente(numero, cliente, saldoInicial, limite);
        } else if (tipo == 2) {
            double taxa = lerDouble("Taxa de rendimento mensal (ex: 0.005 para 0,5%): ");
            novaConta = new ContaPoupanca(numero, cliente, saldoInicial, taxa);
        } else {
            System.out.println("Tipo inválido! Cadastro cancelado.");
            return;
        }
 
        int resultado = banco.cadastrarConta(novaConta);
 
        if (resultado == -1) {
            System.out.println("Não foi possível cadastrar: limite de contas do banco atingido.");
        } else {
            System.out.println("Conta cadastrada com sucesso! Número da conta: " + resultado);
        }
    }
 
    public static void buscarConta() {
        int numero = lerInteiro("Informe o número da conta: ");
        Conta conta = banco.buscarContaPorNumero(numero);
 
        if (conta == null) {
            System.out.println("Conta não encontrada.");
        } else {
            System.out.println("Conta encontrada:");
            System.out.println(conta);
        }
    }

    public static void realizarDeposito() {
        int numero = lerInteiro("Número da conta: ");
        Conta conta = banco.buscarContaPorNumero(numero);
 
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
 
        double valor = lerDouble("Valor do depósito: R$ ");
        boolean sucesso = conta.depositar(valor);
 
        if (sucesso) {
            System.out.println("Depósito realizado com sucesso! Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public static void realizarSaque() {
        int numero = lerInteiro("Número da conta: ");
        Conta conta = banco.buscarContaPorNumero(numero);
 
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
 
        double valor = lerDouble("Valor do saque: R$ ");
        boolean sucesso = conta.sacar(valor);
 
        if (sucesso) {
            System.out.println("Saque realizado com sucesso! Novo saldo: R$ " + String.format("%.2f", conta.getSaldo()));
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public static void aplicarRendimentoPoupanca() {
        int numero = lerInteiro("Número da conta poupança: ");
        Conta conta = banco.buscarContaPorNumero(numero);
 
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
 
        if (conta instanceof ContaPoupanca) {
            ContaPoupanca poupanca = (ContaPoupanca) conta;
            double rendimento = poupanca.calcularRendimento();
            poupanca.aplicarRendimento();
            System.out.println("Rendimento de R$ " + String.format("%.2f", rendimento) + " aplicado com sucesso!");
            System.out.println("Novo saldo: R$ " + String.format("%.2f", poupanca.getSaldo()));
        } else {
            System.out.println("Esta operação é válida apenas para contas poupança.");
        }
    }
 
    public static void encerrarConta() {
        int numero = lerInteiro("Número da conta a ser encerrada: ");
        boolean sucesso = banco.encerrarConta(numero);
 
        if (sucesso) {
            System.out.println("Conta encerrada com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
 
    public static int lerInteiro(String mensagem) {
        int valor = 0;
        boolean valido = false;
 
        while (!valido) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                valor = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
            }
        }
        return valor;
    }
    public static double lerDouble(String mensagem) {
        double valor = 0;
        boolean valido = false;
 
        while (!valido) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                valor = Double.parseDouble(entrada.replace(",", "."));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números (ex: 100.50).");
            }
        }
        return valor;
    }
}
 

