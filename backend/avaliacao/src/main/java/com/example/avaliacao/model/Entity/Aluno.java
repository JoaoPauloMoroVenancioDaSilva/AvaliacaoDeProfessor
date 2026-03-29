package com.example.avaliacao.model.Entity;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIgnore
    private String senha;

    @ManyToMany
    @JoinTable(name = "aluno_professor")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Professor> professores = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Coordenador coordenador;

    //Construtor
    public Aluno(){}
    public Aluno(String nome, String email, Coordenador coordenador, String senha, String matricula){
    setNome(nome);
    setEmail(email);
    setMatricula(matricula);
    setCoordenador(coordenador);
    setSenha(senha);
    }
    //Gett and Sett

    public void setId(Long id) {
        this.id = id;
    }
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

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
