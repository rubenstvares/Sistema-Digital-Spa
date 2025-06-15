package br.com.faseh.cadastroclientes.service;

import br.com.faseh.cadastroclientes.entity.Cliente;
import br.com.faseh.cadastroclientes.repository.ClienteRepository;

import java.sql.SQLException;

public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        if (isInvalido(cliente)) {
            System.out.println("Erro: Todos os campos são obrigatórios.");
            return;
        }
        try {
            repository.inserir(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Erro: CPF, e-mail ou telefone já cadastrados.");
            } else {
                throw e;
            }
        }
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        if (isInvalido(cliente)) {
            System.out.println("Erro: Todos os campos são obrigatórios.");
            return;
        }
        repository.atualizar(cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void excluirCliente(int id) throws SQLException {
        repository.remover(id);
        System.out.println("Cliente removido com sucesso!");
    }

    private boolean isInvalido(Cliente c) {
        return c.getNomeCompleto().isBlank() || c.getCpf().isBlank()
                || c.getEmail().isBlank() || c.getTelefone().isBlank()
                || c.getDataNascimento() == null;
    }
}
