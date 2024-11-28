package view;

import model.Conta;
import utils.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormularioEncerrarConta extends JFrame {
    private JTextField tfNumeroConta;
    private JButton btnExcluir;
    private JButton btnCancelar;

    public FormularioEncerrarConta() {
        setTitle("Encerrar Conta");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        tfNumeroConta = new JTextField();
        btnExcluir = new JButton("Excluir");
        btnCancelar = new JButton("Cancelar");

        panel.add(lblNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(new JLabel()); // Espaço vazio
        panel.add(btnExcluir);
        panel.add(btnCancelar);

        add(panel);

        // Evento para excluir a conta
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirConta();
            }
        });

        // Evento para cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha o formulário
            }
        });

        setVisible(true);
    }

    private void excluirConta() {
        String numeroConta = tfNumeroConta.getText().trim();

        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O número da conta deve ser preenchido.");
            return;
        }

        try {
            int numeroContaInt = Integer.parseInt(numeroConta);
            // Carregar as contas existentes
            List<Conta> contas = DataManager.carregarContas("contas.dat");

            // Procurar e remover a conta
            boolean contaRemovida = false;
            for (int i = 0; i < contas.size(); i++) {
                if (contas.get(i).getNumero() == numeroContaInt) {
                    contas.remove(i); // Remover a conta da lista
                    contaRemovida = true;
                    break;
                }
            }

            if (contaRemovida) {
                // Salvar as contas restantes no arquivo
                DataManager.salvarContas(contas, "contas.dat");
                JOptionPane.showMessageDialog(this, "Conta excluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.");
            }

            dispose(); // Fecha o formulário após excluir a conta

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número da conta inválido.");
        }
    }
}
