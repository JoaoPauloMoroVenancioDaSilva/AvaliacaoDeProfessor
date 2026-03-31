package com.example.avaliacao.service;

import com.example.avaliacao.controller.dto.AvaliacaoDTO;
import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.model.Entity.Avaliacao;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.model.repository.AlunoRepository;
import com.example.avaliacao.model.repository.AvaliacaoRepository;
import com.example.avaliacao.model.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public Avaliacao salvar(AvaliacaoDTO dto) {

        //verifica quem está avaliando quem
        if ("ALUNO".equalsIgnoreCase(dto.getTipoAvaliador())) {

            //busca o aluno avaliador
            Aluno avaliador = alunoRepository.findById(dto.getAvaliadorId())
                    .orElseThrow(() -> new RuntimeException("Aluno avaliador não encontrado."));

            //busca o professor que está sendo avaliado
            Professor avaliado = professorRepository.findById(dto.getAvaliadoId())
                    .orElseThrow(() -> new RuntimeException("Professor avaliado não encontrado."));

            //(nota, comentario, Aluno avaliador, Professor avaliado)
            Avaliacao avaliacao = new Avaliacao(dto.getNota(), dto.getComentario(), avaliador, avaliado);

            return avaliacaoRepository.save(avaliacao);

        } else if ("PROFESSOR".equalsIgnoreCase(dto.getTipoAvaliador())) {

            //busca o professor avaliador
            Professor avaliador = professorRepository.findById(dto.getAvaliadorId())
                    .orElseThrow(() -> new RuntimeException("Professor avaliador não encontrado."));

            //busca o aluno que está sendo avaliado
            Aluno avaliado = alunoRepository.findById(dto.getAvaliadoId())
                    .orElseThrow(() -> new RuntimeException("Aluno avaliado não encontrado."));

            //(nota, comentario, Professor avaliador, Aluno avaliado)
            Avaliacao avaliacao = new Avaliacao(dto.getNota(), dto.getComentario(), avaliador, avaliado);

            return avaliacaoRepository.save(avaliacao);
        } else {
            //se o front-end mandar qualquer coisa diferente de "ALUNO" ou "PROFESSOR", bloqueia a requisição
            throw new RuntimeException("Tipo de avaliador inválido. Envie 'ALUNO' ou 'PROFESSOR'.");
        }
    }

    public List<Avaliacao> buscarAvaliacoesDoProfessor(Long professorId) {
        return avaliacaoRepository.findByProfessorAvaliadoId(professorId);
    }

    public List<Avaliacao> buscarAvaliacoesDoAluno(Long alunoId) {
        return avaliacaoRepository.findByAlunoAvaliadoId(alunoId);
    }
}
