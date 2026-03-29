package com.example.avaliacao.controller.dto;


public class ProfessorDTO {
    private String nome;
    private String email;
    private String senha;
    private String matricula;
    private Long coordenadorId;
    //Construtor
    public ProfessorDTO(){}
    public ProfessorDTO(String nome, String email, Long coordenador, String senha, String matricula){
        setNome(nome);
        setEmail(email);
        setMatricula(matricula);
        setCoordenadorId(coordenador);
        setSenha(senha);
    }
    //Gett and Sett
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getCoordenadorId() {
        return coordenadorId;
    }

    public void setCoordenadorId(Long coordenadorId) {
        this.coordenadorId = coordenadorId;
    }
}