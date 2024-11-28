package controller;

import model.Conta;
import model.Funcionario;
import utils.DataManager;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private List<Conta> contas; // Lista de contas do banco
    private List<Funcionario> funcionarios; // Lista de funcionários do banco

    public BancoController() {
        contas = new ArrayList<>();
        funcionarios = new ArrayList<>();
        carregarDados(); // Carrega os dados salvos
    }

    // Método para abrir uma nova conta
    public void abrirConta(Conta conta) {
        contas.add(conta);
        salvarDados(); // Salva as alterações no arquivo
    }

    // Método para encerrar uma conta
    public void encerrarConta(int numeroConta) {
        contas.removeIf(conta -> conta.getNumero() == numeroConta);
        salvarDados(); // Salva as alterações no arquivo
    }

    // Método para consultar uma conta específica
    public Conta consultarConta(int numeroConta) {
        return contas.stream()
                .filter(conta -> conta.getNumero() == numeroConta)
                .findFirst()
                .orElse(null); // Retorna null se não encontrar
    }

    // Método para salvar os dados em arquivos
    public void salvarDados() {
        DataManager.salvarContas(contas, "contas.dat");
        DataManager.salvarFuncionarios(funcionarios, "funcionarios.dat");
    }

    // Método para carregar os dados ao iniciar o sistema
    public void carregarDados() {
        contas = DataManager.carregarContas("contas.dat");
        funcionarios = DataManager.carregarFuncionarios("funcionarios.dat");
    }
}
