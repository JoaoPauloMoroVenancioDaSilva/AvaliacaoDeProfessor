package com.example.avaliacao.model.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Professor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private String email;

    @ManyToMany(mappedBy = "professores")
    private List<Aluno> alunos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenador;

    //Construtor

    public Professor(){}
    public Professor(String nome, String email, Coordenador coordenador){
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
}
