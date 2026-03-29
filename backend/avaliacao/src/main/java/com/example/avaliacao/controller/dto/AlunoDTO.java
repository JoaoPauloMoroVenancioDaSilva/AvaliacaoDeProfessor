package com.example.avaliacao.controller.dto;

public class AlunoDTO {
    private String nome;
    private String email;
    private String senha;
    private String matricula;
    private Long coordenadorId;

    // Construtores
    public AlunoDTO() {}

    public AlunoDTO(String nome, String email, String senha, String matricula, Long coordenadorId) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
        this.coordenadorId = coordenadorId;
    }

    // Gette e Sett
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Long getCoordenadorId() { return coordenadorId; }
    public void setCoordenadorId(Long coordenadorId) { this.coordenadorId = coordenadorId; }
}