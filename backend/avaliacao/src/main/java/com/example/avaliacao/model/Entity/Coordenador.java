package com.example.avaliacao.model.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Coordenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    @OneToMany(mappedBy = "coordenador")
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "coordenador")
    private List<Professor> professores = new ArrayList<>();

    //Construtor
    public Coordenador(){}
    public Coordenador(String nome, String email){
        this.nome = nome;
        this.email = email;
    }
    //Gett and Sett

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

}