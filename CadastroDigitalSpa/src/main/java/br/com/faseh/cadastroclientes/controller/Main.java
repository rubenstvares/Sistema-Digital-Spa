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
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n==== Sistema de Cadastro Digital ====");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Editar Cliente");
                System.out.println("3. Remover Cliente");
                System.out.println("4. Pesquisar Cliente por Telefone");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Digite um número.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        Cliente cliente = capturarDadosCliente(sc, 0);
                        if (cliente != null) {
                            service.cadastrarCliente(cliente);
                        }
                        break;
                    case 2:
                        System.out.print("ID do cliente a editar: ");
                        int idEditar = Integer.parseInt(sc.nextLine());
                        Cliente editar = capturarDadosCliente(sc, idEditar);
                        if (editar != null) {
                            service.atualizarCliente(editar);
                        }
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
                        System.out.print("Digite o telefone: ");
                        String telBusca = sc.nextLine();
                        Cliente encontrado = service.buscarPorTelefone(telBusca);
                        if (encontrado != null) {
                            System.out.println("Cliente encontrado:");
                            System.out.println("ID: " + encontrado.getId());
                            System.out.println("Nome: " + encontrado.getNomeCompleto());
                            System.out.println("CPF: " + encontrado.getCpf());
                            System.out.println("Nascimento: " + encontrado.getDataNascimento());
                            System.out.println("Email: " + encontrado.getEmail());
                            System.out.println("Telefone: " + encontrado.getTelefone());
                        } else {
                            System.out.println("Nenhum cliente encontrado com esse telefone.");
                        }
                        break;
                    case 5:
                        System.out.println("Sistema finalizado. Obrigado por utilizar.");
                        continuar = false;
                        continue;
                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }

                // Ao final de cada ação (exceto sair), perguntar:
                System.out.print("\nDeseja continuar? (s/n): ");
                String resposta = sc.nextLine().trim().toLowerCase();
                if (!resposta.equals("s")) {
                    continuar = false;
                    System.out.println("Sistema encerrado pelo usuário.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cliente capturarDadosCliente(Scanner sc, int id) {
        try {
            System.out.print("Nome completo: ");
            String nome = sc.nextLine().trim();

            System.out.print("CPF (somente números): ");
            String cpf = sc.nextLine().replaceAll("[^0-9]", "").trim();

            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            String dataStr = sc.nextLine().trim();
            LocalDate dataNascimento = LocalDate.parse(dataStr);

            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine().trim();

            // Verifica se algum campo obrigatório está vazio
            if (nome.isEmpty() || cpf.isEmpty() || dataStr.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
                System.out.println("Erro: Nenhum campo pode estar vazio.");
                return null;
            }

            return (id == 0)
                    ? new Cliente(nome, cpf, dataNascimento, email, telefone)
                    : new Cliente(id, nome, cpf, dataNascimento, email, telefone);

        } catch (Exception e) {
            System.out.println("Erro: Um ou mais campos foram preenchidos incorretamente. Tente novamente.");
            return null;
        }
    }

}

