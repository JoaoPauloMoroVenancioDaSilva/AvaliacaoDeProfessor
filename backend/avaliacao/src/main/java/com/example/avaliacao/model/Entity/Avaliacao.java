package com.example.avaliacao.model.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    private String comentario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataAvaliacao = LocalDateTime.now();

    @ManyToOne
    private Aluno avaliadorAluno;

    @ManyToOne
    private Professor avaliadorProf;

    @ManyToOne
    private Professor professorAvaliado;

    @ManyToOne
    private Aluno alunoAvaliado;

    //Construtor
    public Avaliacao() {
    }

    // Construtor completo (Ajuda muito no AvaliacaoService)
    public Avaliacao(Integer nota, String comentario, Professor avaliador, Aluno avaliado) {
        setNota(nota);
        setComentario(comentario);
        setDataAvaliacao(LocalDateTime.now());
        setAlunoAvaliado(avaliado);
        setAvaliadorProf(avaliador);
    }
    public Avaliacao(Integer nota, String comentario, Aluno avaliador, Professor avaliado) {
        setNota(nota);
        setComentario(comentario);
        setDataAvaliacao(LocalDateTime.now());
        setProfessorAvaliado(avaliado);
        setAvaliadorAluno(avaliador);
    }

    //Gett and Sett

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Aluno getAvaliadorAluno() {
        return avaliadorAluno;
    }

    public void setAvaliadorAluno(Aluno avaliadorAluno) {
        this.avaliadorAluno = avaliadorAluno;
    }

    public Professor getProfessorAvaliado() {
        return professorAvaliado;
    }

    public void setProfessorAvaliado(Professor professorAvaliado) {
        this.professorAvaliado = professorAvaliado;
    }

    public Aluno getAlunoAvaliado() {
        return alunoAvaliado;
    }

    public void setAlunoAvaliado(Aluno alunoAvaliado) {
        this.alunoAvaliado = alunoAvaliado;
    }

    public Professor getAvaliadorProf() {
        return avaliadorProf;
    }

    public void setAvaliadorProf(Professor avaliadorProf) {
        this.avaliadorProf = avaliadorProf;
    }

}
