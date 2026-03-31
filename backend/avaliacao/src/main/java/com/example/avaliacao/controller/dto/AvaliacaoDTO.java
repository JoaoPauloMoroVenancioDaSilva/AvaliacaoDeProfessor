package com.example.avaliacao.controller.dto;

public class AvaliacaoDTO {
    private Integer nota;
    private Long avaliadorId;
    private Long avaliadoId;
    private String tipoAvaliador;
    private String comentario;

    //construtor vazio
    public AvaliacaoDTO() {}

    //construtor completo
    public AvaliacaoDTO(Integer nota, String comentario, Long avaliadorId, Long avaliadoId, String tipoAvaliador) {
        this.nota = nota;
        this.comentario = comentario;
        this.avaliadorId = avaliadorId;
        this.avaliadoId = avaliadoId;
        this.tipoAvaliador = tipoAvaliador;
    }

    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public Long getAvaliadorId() {
        return avaliadorId;
    }
    public void setAvaliadorId(Long avaliadorId) {
        this.avaliadorId = avaliadorId;
    }
    public Long getAvaliadoId() {
        return avaliadoId;
    }
    public void setAvaliadoId(Long avaliadoId) {
        this.avaliadoId = avaliadoId;
    }
    public String getTipoAvaliador() {
        return tipoAvaliador;
    }
    public void setTipoAvaliador(String tipoAvaliador) {
        this.tipoAvaliador = tipoAvaliador;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}


