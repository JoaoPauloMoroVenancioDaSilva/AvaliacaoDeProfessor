package com.example.avaliacao.model.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private String senha;

    @ManyToMany(mappedBy = "professores")
    private List<Aluno> alunos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    @JsonBackReference
    @JsonIgnoreProperties({"alunos", "professores"})
    private Coordenador coordenador;

    //Construtor

    public Professor(){}
    public Professor(String nome, String email, Coordenador coordenador, String senha, String matricula){
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
