package br.com.faseh.cadastroclientes.entity;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public Cliente(String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente(int id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this(nomeCompleto, cpf, dataNascimento, email, telefone);
        this.id = id;
    }

    public int getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    public void setId(int id) { this.id = id; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
