package view;

import model.Funcionario;
import utils.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroFuncionario extends JFrame {
    
    public TelaCadastroFuncionario() {
        setTitle("Cadastrar Novo Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        
        // Campos do formulário
        JTextField txtNome = new JTextField();
        JTextField txtUsuario = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        
        add(new JLabel("Nome:"));
        add(txtNome);
        
        add(new JLabel("Usuário:"));
        add(txtUsuario);
        
        add(new JLabel("Senha:"));
        add(txtSenha);
        
        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);
        
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar os campos
                String nome = txtNome.getText().trim();
                String usuario = txtUsuario.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();

                if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.");
                    return;
                }
                
                // Criar um novo funcionário
                Funcionario novoFuncionario = new Funcionario(nome, "", "", "", usuario, senha);
                
                // Carregar os funcionários existentes e adicionar o novo
                List<Funcionario> funcionarios = DataManager.carregarFuncionarios("funcionarios.dat");
                funcionarios.add(novoFuncionario);
                
                // Salvar a lista atualizada no arquivo
                DataManager.salvarFuncionarios(funcionarios, "funcionarios.dat");
                
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                dispose(); // Fechar a janela de cadastro
            }
        });

        setVisible(true); // Exibir a tela
    }

    public static void main(String[] args) {
        new TelaCadastroFuncionario(); // Para testar a tela de cadastro
    }
}
