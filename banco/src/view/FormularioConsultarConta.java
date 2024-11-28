package view;

import model.Conta;
import utils.DataManager;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import model.ContaCorrente;
import model.ContaPoupanca;

public class FormularioConsultarConta extends JFrame {
    private JTextField tfNumeroConta;
    private JTextArea taResultadoConsulta;

    public FormularioConsultarConta() {
        setTitle("Consultar Conta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        tfNumeroConta = new JTextField();
        JButton btnConsultar = new JButton("Consultar");
        taResultadoConsulta = new JTextArea(6, 30);
        taResultadoConsulta.setEditable(false);

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnConsultar);
        panel.add(new JScrollPane(taResultadoConsulta));

        add(panel);

        // Ação para consultar a conta
        btnConsultar.addActionListener(e -> consultarConta());

        setVisible(true);
    }

    private void consultarConta() {
        String numeroConta = tfNumeroConta.getText().trim();

        // Verifica se o número da conta está vazio
        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O número da conta deve ser preenchido.");
            return; // Sai do método se o campo estiver vazio
        }

        try {
            int numeroContaInt = Integer.parseInt(numeroConta); // Converte o número da conta para inteiro

            // Carregar as contas existentes
            List<Conta> contas = DataManager.carregarContas("contas.dat");

            // Procurar pela conta com o número fornecido
            Conta contaEncontrada = null;
            for (Conta conta : contas) {
                if (conta.getNumero() == numeroContaInt) {
                    contaEncontrada = conta;
                    break;
                }
            }

            if (contaEncontrada != null) {
                // Exibe os detalhes da conta
                String detalhesConta = "Número da Conta: " + contaEncontrada.getNumero() + "\n";
                detalhesConta += "Tipo de Conta: " + contaEncontrada.getTipoConta() + "\n";
                detalhesConta += "Saldo: " + contaEncontrada.getSaldo() + "\n";
                detalhesConta += contaEncontrada.consultarDetalhes(); // Chama o método de detalhes específico de cada tipo de conta
                taResultadoConsulta.setText(detalhesConta);
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.");
                taResultadoConsulta.setText(""); // Limpa o campo de resultado
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número da conta deve ser um número válido.");
        }
    }
}
