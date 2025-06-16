package br.com.faseh.cadastroclientes.gui;

import br.com.faseh.cadastroclientes.entity.Cliente;
import br.com.faseh.cadastroclientes.repository.ClienteRepository;
import br.com.faseh.cadastroclientes.service.ClienteService;
import br.com.faseh.cadastroclientes.util.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.time.LocalDate;

public class MainGUI extends JFrame {
    private final ClienteService clienteService;

    public MainGUI() throws Exception {
        Connection conn = Conexao.getConnection();
        ClienteRepository repo = new ClienteRepository(conn);
        clienteService = new ClienteService(repo);

        setTitle("Cadastro de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JButton cadastrarBtn = new JButton("Cadastrar Cliente");
        JButton editarBtn = new JButton("Editar Cliente");
        JButton removerBtn = new JButton("Remover Cliente");
        JButton buscarBtn = new JButton("Buscar por Telefone");
        JButton sairBtn = new JButton("Sair");

        cadastrarBtn.addActionListener(this::cadastrarCliente);
        editarBtn.addActionListener(this::editarCliente);
        removerBtn.addActionListener(this::removerCliente);
        buscarBtn.addActionListener(this::buscarCliente);
        sairBtn.addActionListener(e -> System.exit(0));

        add(cadastrarBtn);
        add(editarBtn);
        add(removerBtn);
        add(buscarBtn);
        add(sairBtn);
    }

    private Cliente mostrarFormularioCliente(Cliente existente) {
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField nascimentoField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telefoneField = new JTextField();

        if (existente != null) {
            nomeField.setText(existente.getNomeCompleto());
            cpfField.setText(existente.getCpf());
            nascimentoField.setText(existente.getDataNascimento().toString());
            emailField.setText(existente.getEmail());
            telefoneField.setText(existente.getTelefone());
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome completo:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF (somente números):"));
        panel.add(cpfField);
        panel.add(new JLabel("Data de nascimento (AAAA-MM-DD):"));
        panel.add(nascimentoField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);

        int resultado = JOptionPane.showConfirmDialog(this, panel,
                existente == null ? "Cadastrar Cliente" : "Editar Cliente",
                JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                return new Cliente(
                        nomeField.getText(),
                        cpfField.getText(),
                        LocalDate.parse(nascimentoField.getText()),
                        emailField.getText(),
                        telefoneField.getText()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao processar os dados. Verifique os campos.");
            }
        }
        return null;
    }

    private void cadastrarCliente(ActionEvent e) {
        Cliente cliente = mostrarFormularioCliente(null);
        if (cliente != null) {
            try {
                clienteService.cadastrarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
            }
        }
    }

    private void editarCliente(ActionEvent e) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do cliente a editar:"));
            Cliente existente = clienteService.buscarPorId(id);
            if (existente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado com esse ID.");
                return;
            }
            Cliente cliente = mostrarFormularioCliente(existente);
            if (cliente != null) {
                cliente.setId(id);
                try {
                    clienteService.atualizarCliente(cliente);
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }


    private void removerCliente(ActionEvent e) {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do cliente a remover:"));
            clienteService.excluirCliente(id);
            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage());
        }
    }

    private void buscarCliente(ActionEvent e) {
        try {
            String telefone = JOptionPane.showInputDialog("Digite o telefone:");
            Cliente cliente = clienteService.buscarPorTelefone(telefone);
            if (cliente != null) {
                JOptionPane.showMessageDialog(this, "Cliente encontrado:\nID: " + cliente.getId() +
                        "\nNome: " + cliente.getNomeCompleto() +
                        "\nCPF: " + cliente.getCpf() +
                        "\nNascimento: " + cliente.getDataNascimento() +
                        "\nEmail: " + cliente.getEmail() +
                        "\nTelefone: " + cliente.getTelefone());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainGUI().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
