package model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero, String nome, String cpf) {
        super(numero, "Poupança", nome, cpf);
    }

    @Override
    public String consultarDetalhes() {
        return "Conta Poupança - Saldo: " + getSaldo();
    }
}
