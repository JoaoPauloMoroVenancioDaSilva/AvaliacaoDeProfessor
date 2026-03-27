package com.example.avaliacao.model.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    @ManyToMany
    @JoinTable(name = "aluno_professor")
    private List<Professor> professores = new ArrayList<>();

    @ManyToOne
    private Coordenador coordenador;

    //Construtor
    public Aluno(){}
    public Aluno(String nome, String email, Coordenador coordenador){
        this.nome = nome;
        this.email = email;
        this.coordenador = coordenador;
    }
    //Gett and Sett
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
