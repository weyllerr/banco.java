package view;

import model.Conta; // Certifique-se de que esta classe existe e está no pacote correto
import utils.DataManager; // Certifique-se de que esta classe existe e está no pacote correto
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuCliente extends JFrame {

    public MenuCliente() {
        setTitle("Banco Malvader - Menu Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Melhor organização dos botões

        // Botões e suas ações
        JButton saldoButton = new JButton("Saldo");
        saldoButton.addActionListener(e -> consultarSaldo());
        panel.add(saldoButton);

        JButton depositoButton = new JButton("Depósito");
        depositoButton.addActionListener(e -> realizarDeposito());
        panel.add(depositoButton);

        JButton saqueButton = new JButton("Saque");
        saqueButton.addActionListener(e -> realizarSaque());
        panel.add(saqueButton);

        JButton extratoButton = new JButton("Extrato");
        extratoButton.addActionListener(e -> consultarExtrato());
        panel.add(extratoButton);

        JButton limiteButton = new JButton("Consultar Limite");
        limiteButton.addActionListener(e -> consultarLimite());
        panel.add(limiteButton);

        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(e -> {
            new TelaLogin(); // Retorna à tela de login
            dispose();
        });
        panel.add(sairButton);

        add(panel);
        setVisible(true);
    }

    private void consultarSaldo() {
        criarJanelaInteracao("Consultar Saldo", "Número da Conta:", (numeroConta) -> {
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");
                Conta conta = contas.stream()
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()
                        .orElse(null);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Número da Conta: " + conta.getNumero() + "\n" +
                                    "Saldo: R$ " + conta.getSaldo());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar saldo: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void realizarDeposito() {
        criarJanelaInteracaoComValor("Realizar Depósito", (numeroConta, valor) -> {
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");
                Conta conta = contas.stream()
                        .filter(c -> c.getNumero() == numeroConta)
                        .findFirst()
                        .orElse(null);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {
                    conta.depositar(valor);
                    DataManager.salvarContas(contas, "contas.dat");
                    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar depósito: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void realizarSaque() {
        criarJanelaInteracaoComValor("Realizar Saque", (numeroConta, valor) -> {
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");
                Conta conta = contas.stream()
                        .filter(c -> c.getNumero() == numeroConta)
                        .findFirst()
                        .orElse(null);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else if (conta.getSaldo() < valor) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
                } else {
                    conta.sacar(valor);
                    DataManager.salvarContas(contas, "contas.dat");
                    JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar saque: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void consultarExtrato() {
        criarJanelaInteracao("Consultar Extrato", "Número da Conta:", (numeroConta) -> {
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");
                Conta conta = contas.stream()
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()
                        .orElse(null);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {
                    StringBuilder extrato = new StringBuilder("Extrato da Conta " + conta.getNumero() + ":\n\n");
                    List<String> transacoes = conta.getExtrato();

                    if (transacoes.isEmpty()) {
                        extrato.append("Nenhuma transação encontrada.");
                    } else {
                        for (String transacao : transacoes) {
                            extrato.append(transacao).append("\n");
                        }
                    }

                    JOptionPane.showMessageDialog(null, extrato.toString());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar extrato: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void consultarLimite() {
        criarJanelaInteracao("Consultar Limite", "Número da Conta:", (numeroConta) -> {
            try {
                List<Conta> contas = DataManager.carregarContas("contas.dat");
                Conta conta = contas.stream()
                        .filter(c -> String.valueOf(c.getNumero()).equals(numeroConta))
                        .findFirst()
                        .orElse(null);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Número da Conta: " + conta.getNumero() + "\n" +
                                    "Limite: R$ " + conta.getLimite());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar limite: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void criarJanelaInteracao(String titulo, String label, InteracaoSimples acao) {
        JFrame frame = new JFrame(titulo);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel lblNumeroConta = new JLabel(label);
        JTextField tfNumeroConta = new JTextField(15);
        JButton btnAcao = new JButton("Confirmar");

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnAcao);

        frame.add(panel);
        frame.setVisible(true);

        btnAcao.addActionListener(e -> {
            String numeroConta = tfNumeroConta.getText().trim();
            if (numeroConta.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O número da conta deve ser preenchido.");
            } else {
                acao.executar(numeroConta);
            }
        });
    }

    private void criarJanelaInteracaoComValor(String titulo, InteracaoComValor acao) {
        JFrame frame = new JFrame(titulo);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);
        JLabel lblValor = new JLabel("Valor:");
        JTextField tfValor = new JTextField(15);
        JButton btnAcao = new JButton("Confirmar");

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(lblValor);
        panel.add(tfValor);
        panel.add(btnAcao);

        frame.add(panel);
        frame.setVisible(true);

        btnAcao.addActionListener(e -> {
            try {
                int numeroConta = Integer.parseInt(tfNumeroConta.getText().trim());
                double valor = Double.parseDouble(tfValor.getText().trim());
                acao.executar(numeroConta, valor);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Número da conta e valor devem ser válidos.");
            }
        });
    }

    @FunctionalInterface
    private interface InteracaoSimples {
        void executar(String numeroConta);
    }

    @FunctionalInterface
    private interface InteracaoComValor {
        void executar(int numeroConta, double valor);
    }

    public static void main(String[] args) {
        new MenuCliente();
    }
}
