package com.example.avaliacao.controller.dto;
public class SolicitacaoDTO {

    private Long alunoId;
    private Long professorId;

    // Construtor
    public SolicitacaoDTO() {
    }
    public SolicitacaoDTO(Long alunoId, Long professorId) {
        this.alunoId = alunoId;
        this.professorId = professorId;
    }

    // Getters e Setters
    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}