package view;

import controller.BancoController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Conta; // Para utilizar o modelo de Conta
import utils.DataManager; // Para carregar as contas

public class MenuFuncionario extends JFrame {
    private BancoController bancoController;

    public MenuFuncionario(BancoController bancoController) {
        this.bancoController = bancoController;

        setTitle("Menu Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JButton btnAbrirConta = new JButton("Abrir Conta");
        JButton btnEncerrarConta = new JButton("Encerrar Conta");
        JButton btnConsultarConta = new JButton("Consultar Conta");
        JButton btnAlterarDados = new JButton("Alterar Dados");
        JButton btnCadastroFuncionario = new JButton("Cadastro Funcionario");
        JButton btnGerarRelatorios = new JButton("Gerar Relatórios");
        JButton btnSair = new JButton("Sair");

        panel.add(btnAbrirConta);
        panel.add(btnEncerrarConta);
        panel.add(btnConsultarConta);
        panel.add(btnAlterarDados);
        panel.add(btnCadastroFuncionario);
        panel.add(btnGerarRelatorios);
        panel.add(btnSair);

        // Abrir Conta
        btnAbrirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConta();
            }
        });

        // Encerrar Conta
        btnEncerrarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarConta();
            }
        });

        // Consultar Conta
        btnConsultarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarConta();
            }
        });

        // Alterar Dados
        btnAlterarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarDados();
            }
        });

        // Cadastro Funcionario
        btnCadastroFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        // Gerar Relatórios
        btnGerarRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorios();
            }
        });

        // Sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha o menu de funcionário
            }
        });

        setVisible(true); // Torna a janela visível
    }

    // Métodos que serão chamados pelos botões

    public void abrirConta() {
        // Cria uma nova janela de formulário para abrir conta
        new FormularioAbrirConta();
    }

    public void encerrarConta() {
        // Abre o formulário de encerrar conta
        new FormularioEncerrarConta();
    }

    public void consultarConta() {
        // Tela de consulta de conta
        JFrame frame = new JFrame("Consultar Conta");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);
        JButton btnConsultar = new JButton("Consultar");

        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnConsultar);

        // Ação do botão para consultar a conta
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();

                if (numeroConta.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "O número da conta deve ser preenchido.");
                    return;
                }

                try {
                    // Tentar carregar as contas do arquivo
                    List<Conta> contas = DataManager.carregarContas("contas.dat");

                    // Procurar a conta pelo número
                    boolean contaEncontrada = false;
                    for (Conta conta : contas) {
                        if (String.valueOf(conta.getNumero()).equals(numeroConta)) {
                            // Exibir os detalhes da conta, incluindo nome e CPF
                            JOptionPane.showMessageDialog(frame,
                                    "Número da Conta: " + conta.getNumero() + "\n" +
                                            "Nome: " + conta.getNome() + "\n" +
                                            "CPF: " + conta.getCpf() + "\n" +
                                            "Tipo de Conta: " + conta.getTipoConta() + "\n" +
                                            "Saldo: " + conta.getSaldo() + "\n" +
                                            conta.consultarDetalhes());
                            contaEncontrada = true;
                            break;
                        }
                    }

                    if (!contaEncontrada) {
                        JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao consultar a conta: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    public void alterarDados() {
        // Criar uma nova janela para alterar os dados da conta
        JFrame frame = new JFrame("Alterar Dados da Conta");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelNumeroConta = new JLabel("Número da Conta:");
        JTextField tfNumeroConta = new JTextField(15);
        JButton btnBuscarConta = new JButton("Buscar Conta");

        JLabel labelNome = new JLabel("Novo Nome:");
        JTextField tfNome = new JTextField(15);
        JLabel labelCpf = new JLabel("Novo CPF:");
        JTextField tfCpf = new JTextField(15);

        // Inicialmente desabilita os campos de nome e cpf
        tfNome.setEnabled(false);
        tfCpf.setEnabled(false);

        panel.add(labelNumeroConta);
        panel.add(tfNumeroConta);
        panel.add(btnBuscarConta);
        panel.add(labelNome);
        panel.add(tfNome);
        panel.add(labelCpf);
        panel.add(tfCpf);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setEnabled(false); // Inicialmente desabilita o botão de alterar

        panel.add(btnAlterar);

        // Ação para buscar a conta
        btnBuscarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();

                if (numeroConta.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "O número da conta deve ser preenchido.");
                    return;
                }

                try {
                    // Tentar carregar as contas do arquivo
                    List<Conta> contas = DataManager.carregarContas("contas.dat");

                    // Procurar a conta pelo número
                    boolean contaEncontrada = false;
                    for (Conta conta : contas) {
                        if (String.valueOf(conta.getNumero()).equals(numeroConta)) {
                            // Exibir os dados atuais da conta e habilitar os campos para edição
                            tfNome.setText(conta.getNome());
                            tfCpf.setText(conta.getCpf());
                            tfNome.setEnabled(true);
                            tfCpf.setEnabled(true);
                            btnAlterar.setEnabled(true);
                            contaEncontrada = true;
                            break;
                        }
                    }

                    if (!contaEncontrada) {
                        JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao buscar a conta: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        // Ação para alterar os dados da conta
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = tfNumeroConta.getText().trim();
                String novoNome = tfNome.getText().trim();
                String novoCpf = tfCpf.getText().trim();

                if (novoNome.isEmpty() || novoCpf.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "O nome e o CPF devem ser preenchidos.");
                    return;
                }

                try {
                    // Carregar as contas do arquivo
                    List<Conta> contas = DataManager.carregarContas("contas.dat");

                    // Procurar a conta pelo número
                    boolean contaAlterada = false;
                    for (Conta conta : contas) {
                        if (String.valueOf(conta.getNumero()).equals(numeroConta)) {
                            // Alterar os dados da conta
                            conta.setNome(novoNome); // Atualiza o nome
                            conta.setCpf(novoCpf); // Atualiza o CPF
                            contaAlterada = true;
                            break;
                        }
                    }

                    if (contaAlterada) {
                        // Salvar as contas atualizadas no arquivo
                        DataManager.salvarContas(contas, "contas.dat");
                        JOptionPane.showMessageDialog(frame, "Dados da conta alterados com sucesso!");
                        frame.dispose(); // Fecha a janela de alteração de dados
                    } else {
                        JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao alterar os dados: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    public void cadastrarFuncionario() {
        // Criar uma nova janela para o cadastro de funcionário
        new TelaCadastroFuncionario();  // Chama a classe que cria o formulário
    }

    public void gerarRelatorios() {
        JOptionPane.showMessageDialog(this, "Gerar Relatórios");
    }
}

