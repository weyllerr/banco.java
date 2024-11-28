package model;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(int numero, String nome, String cpf, double limite) {
        super(numero, "Corrente", nome, cpf);
        this.limite = limite;
    }

    @Override
    public String consultarDetalhes() {
        return "Limite: " + limite;
    }
}
