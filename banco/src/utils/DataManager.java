package utils;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.dat";
    private static final String ARQUIVO_CONTAS = "contas.dat";

    // Método para salvar contas
    public static void salvarContas(List<Conta> contas, String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(contas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar contas
    public static List<Conta> carregarContas(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Conta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se houver erro
        }
    }

    // Método para salvar funcionários
    public static void salvarFuncionarios(List<Funcionario> funcionarios, String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar funcionários
    public static List<Funcionario> carregarFuncionarios(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Retorna uma lista vazia se houver erro
        }
    }

    // Método para inicializar o arquivo de funcionários com um usuário padrão
    public static void inicializarFuncionarios() {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);
        if (!arquivo.exists()) {
            List<Funcionario> funcionarios = new ArrayList<>();
            funcionarios.add(new Funcionario("admin", "1234", "1234567890", "Rua A", "admin", "1234")); // Usuário padrão
            funcionarios.add(new Funcionario("funcionario1", "senha123", "9876543210", "Rua B", "func1", "senha123")); // Outro exemplo
            salvarFuncionarios(funcionarios, ARQUIVO_FUNCIONARIOS);
            System.out.println("Arquivo de funcionários inicializado com usuário padrão.");
        } else {
            System.out.println("Arquivo de funcionários já existe. Não foi necessário inicializar.");
        }
    }

    // Método para validar o login do funcionário
    public static boolean validarLoginFuncionario(String usuario, String senha, String arquivo) {
        List<Funcionario> funcionarios = carregarFuncionarios(arquivo);
        System.out.println("Verificando login para: " + usuario);
        
        for (Funcionario f : funcionarios) {
            System.out.println("Usuário carregado: " + f.getUsuario() + ", Senha: " + f.getSenha());
            if (f.getUsuario().equals(usuario) && f.getSenha().equals(senha)) {
                return true; // Login válido
            }
        }
        return false; // Login inválido
    }

    // Método para inicializar o arquivo de contas com contas padrão
    public static void inicializarContas() {
        File arquivo = new File(ARQUIVO_CONTAS);
        if (!arquivo.exists()) {
            List<Conta> contas = new ArrayList<>();
            contas.add(new ContaCorrente(1001, "João", "12345678901", 5000.0)); // Exemplo de Conta Corrente
            contas.add(new ContaPoupanca(2002, "Maria", "98765432100")); // Exemplo de Conta Poupança
            salvarContas(contas, ARQUIVO_CONTAS);
            System.out.println("Arquivo de contas inicializado com contas padrão.");
        }
    }
}