package com.example.avaliacao.controller.dto;

public class CoordenadorDTO {
    private String nome;
    private String email;
    private String senha;
    private String matricula;

    // Construtor
    public CoordenadorDTO() {
    }

    public CoordenadorDTO(String nome, String email, String senha, String matricula) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
    }

    //Gett e Sett
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}