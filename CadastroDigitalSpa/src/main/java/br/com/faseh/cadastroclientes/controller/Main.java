package br.com.faseh.cadastroclientes.controller;

import br.com.faseh.cadastroclientes.entity.Cliente;
import br.com.faseh.cadastroclientes.repository.ClienteRepository;
import br.com.faseh.cadastroclientes.service.ClienteService;
import br.com.faseh.cadastroclientes.util.Conexao;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.getConnection(); Scanner sc = new Scanner(System.in)) {
            ClienteRepository repo = new ClienteRepository(conn);
            ClienteService service = new ClienteService(repo);

            int opcao;
            do {
                System.out.println("\n==== Sistema de Cadastro Digital ====");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Editar Cliente");
                System.out.println("3. Remover Cliente");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:
                        Cliente cliente = capturarDadosCliente(sc, 0);
                        service.cadastrarCliente(cliente);
                        break;
                    case 2:
                        System.out.print("ID do cliente a editar: ");
                        int idEditar = Integer.parseInt(sc.nextLine());
                        Cliente editar = capturarDadosCliente(sc, idEditar);
                        service.atualizarCliente(editar);
                        break;
                    case 3:
                        System.out.print("ID do cliente a remover: ");
                        int idRemover = Integer.parseInt(sc.nextLine());
                        System.out.print("Confirmar remoção (s/n)? ");
                        if (sc.nextLine().equalsIgnoreCase("s")) {
                            service.excluirCliente(idRemover);
                        }
                        break;
                    case 4:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cliente capturarDadosCliente(Scanner sc, int id) {
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF (somente números): ");
        String cpf = sc.nextLine().replaceAll("[^0-9]", "");
        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(sc.nextLine());
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        return (id == 0)
                ? new Cliente(nome, cpf, dataNascimento, email, telefone)
                : new Cliente(id, nome, cpf, dataNascimento, email, telefone);
    }
}

