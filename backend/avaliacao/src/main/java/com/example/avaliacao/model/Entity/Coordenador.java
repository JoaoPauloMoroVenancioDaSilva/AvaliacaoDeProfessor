package com.example.avaliacao.model.Entity;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIgnore
    private String senha;
    @OneToMany(mappedBy = "coordenador")
    @JsonManagedReference
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "coordenador")
    @JsonManagedReference
    private List<Professor> professores = new ArrayList<>();

    //Construtor
    public Coordenador(){}
    public Coordenador(String nome, String email, String senha, String matricula){
        setNome(nome);
        setEmail(email);
        setMatricula(matricula);
        setSenha(senha);
    }
    //Gett and Sett

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {this.id = id;}

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}