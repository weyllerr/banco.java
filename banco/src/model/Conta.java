package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements Serializable {
    private int numero;
    private double saldo;
    private double limite; // Adicionado para suporte ao limite
    private String tipoConta;
    private String nome; // Nome do titular da conta
    private String cpf;  // CPF do titular da conta
    private List<String> extrato; // Lista para registrar transações

    // Construtor
    public Conta(int numero, String tipoConta, String nome, String cpf) {
        this.numero = numero;
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
        this.limite = 0.0; // Inicializando limite como padrão
        this.nome = nome;
        this.cpf = cpf;
        this.extrato = new ArrayList<>(); // Inicializa o extrato
    }

    // Métodos de acesso e modificação
    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getTipoConta() {
        return tipoConta;
    }

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

    // Métodos de operação
    public void depositar(double valor) {
        saldo += valor;
        extrato.add("Depósito: R$ " + valor); // Registra a transação no extrato
    }

    public boolean sacar(double valor) {
        if (valor <= saldo + limite) { // Permite saque considerando o limite
            saldo -= valor;
            extrato.add("Saque: R$ " + valor); // Registra a transação no extrato
            return true;
        }
        return false; // Falha se saldo + limite forem insuficientes
    }

    public List<String> getExtrato() {
        return extrato; // Retorna as transações registradas
    }

    // Método abstrato para detalhes específicos da conta
    public abstract String consultarDetalhes();
}
