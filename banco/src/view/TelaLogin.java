package view;

import controller.BancoController;
import utils.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        // Inicializa o arquivo de funcionários com um usuário padrão, se necessário
        DataManager.inicializarFuncionarios();

        setTitle("Banco Malvader - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));

        JTextField txtUsuario = new JTextField();
        JPasswordField txtSenha = new JPasswordField();

        add(new JLabel("Usuário:"));
        add(txtUsuario);
        add(new JLabel("Senha:"));
        add(txtSenha);

        JButton btnCliente = new JButton("Cliente");
        JButton btnFuncionario = new JButton("Funcionário");

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(btnCliente);
        panelButtons.add(btnFuncionario);
        add(panelButtons);

        // Evento do botão Cliente
        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuCliente(); // Redireciona para o menu do cliente
                dispose(); // Fecha a tela de login
            }
        });

        // Evento do botão Funcionário
        btnFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();

                if (usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }

                // Verificar login no arquivo "funcionarios.dat"
                if (DataManager.validarLoginFuncionario(usuario, senha, "funcionarios.dat")) {
                    try {
                        BancoController bancoController = new BancoController();
                        new MenuFuncionario(bancoController); // Abre o menu do funcionário
                        dispose(); // Fecha a tela de login
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao iniciar o menu do funcionário: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
            }
        });

        setVisible(true); // Torna a janela visível
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}