package view;

import model.Conta; // Importando a classe Conta
import utils.DataManager; // Importando a classe DataManager
import java.util.List; // Importando a interface List
import java.util.ArrayList; // Importando a classe ArrayList
import javax.swing.*;
import java.awt.*;
import model.ContaCorrente;
import model.ContaPoupanca;

public class FormularioAbrirConta extends JFrame {
    private JTextField tfNumeroConta;
    private JTextField tfNome;
    private JTextField tfCpf;
    private JComboBox<String> cbTipoConta;

    public FormularioAbrirConta() {
        setTitle("Abrir Conta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        tfNumeroConta = new JTextField();
        JLabel lblNome = new JLabel("Nome:");
        tfNome = new JTextField();
        JLabel lblCpf = new JLabel("CPF:");
        tfCpf = new JTextField();
        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        cbTipoConta = new JComboBox<>(new String[] { "Corrente", "Poupança" });
        JButton btnCriarConta = new JButton("Criar Conta");

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(lblNome);
        panel.add(tfNome);
        panel.add(lblCpf);
        panel.add(tfCpf);
        panel.add(lblTipoConta);
        panel.add(cbTipoConta);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(btnCriarConta);

        add(panel);

        // Ação para criar a conta
        btnCriarConta.addActionListener(e -> criarConta());

        setVisible(true);
    }

    private void criarConta() {
        String numeroConta = tfNumeroConta.getText().trim(); // Remove espaços em branco
        String nome = tfNome.getText().trim();
        String cpf = tfCpf.getText().trim();
        String tipoConta = (String) cbTipoConta.getSelectedItem();

        // Verifica se o número da conta está vazio
        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O número da conta deve ser preenchido.");
            return; // Sai do método se o campo estiver vazio
        }

        // Verifica se o CPF está vazio
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O CPF deve ser preenchido.");
            return; // Sai do método se o CPF estiver vazio
        }

        // Verifica se o nome está vazio
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome deve ser preenchido.");
            return; // Sai do método se o nome estiver vazio
        }

        try {
            // Tenta converter o número da conta para inteiro
            int numeroContaInt = Integer.parseInt(numeroConta);

            // Verifica o tipo de conta e cria a conta correspondente
            Conta novaConta;
            if ("Corrente".equals(tipoConta)) {
                double limite = 1000.0; // Exemplo de limite
                novaConta = new ContaCorrente(numeroContaInt, nome, cpf, limite); // Passando nome e cpf
            } else if ("Poupança".equals(tipoConta)) {
                novaConta = new ContaPoupanca(numeroContaInt, nome, cpf); // Passando nome e cpf
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de conta inválido!");
                return; // Sai do método se o tipo de conta for inválido
            }

            // Carregar as contas existentes
            List<Conta> contas = DataManager.carregarContas("contas.dat");

            // Adicionar a nova conta à lista
            contas.add(novaConta);

            // Salvar as contas (incluindo a nova conta) no arquivo
            DataManager.salvarContas(contas, "contas.dat");

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!\nNúmero da Conta: " + numeroConta + "\nNome: " + nome + "\nTipo de Conta: " + tipoConta);

            // Fechar o formulário
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número da conta deve ser um número válido.");
        }
    }

}

