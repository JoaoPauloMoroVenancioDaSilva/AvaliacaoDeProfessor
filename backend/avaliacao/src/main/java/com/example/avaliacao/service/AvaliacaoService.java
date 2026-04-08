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
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno avaliador não encontrado."));

            //busca o professor que está sendo avaliado
            Professor avaliado = professorRepository.findById(dto.getAvaliadoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor avaliado não encontrado."));

            //(nota, comentario, Aluno avaliador, Professor avaliado)
            Avaliacao avaliacao = new Avaliacao(dto.getNota(), dto.getComentario(), avaliador, avaliado);

            return avaliacaoRepository.save(avaliacao);

        } else if ("PROFESSOR".equalsIgnoreCase(dto.getTipoAvaliador())) {

            //busca o professor avaliador
            Professor avaliador = professorRepository.findById(dto.getAvaliadorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor avaliador não encontrado."));

            //busca o aluno que está sendo avaliado
            Aluno avaliado = alunoRepository.findById(dto.getAvaliadoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno avaliado não encontrado."));

            //(nota, comentario, Professor avaliador, Aluno avaliado)
            Avaliacao avaliacao = new Avaliacao(dto.getNota(), dto.getComentario(), avaliador, avaliado);

            return avaliacaoRepository.save(avaliacao);
        } else {
            //se o front-end mandar qualquer coisa diferente de "ALUNO" ou "PROFESSOR", bloqueia a requisição
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de avaliador inválido. Envie 'ALUNO' ou 'PROFESSOR'.");
        }
    }

    @Transactional
    public Avaliacao atualizar(Long id, AvaliacaoDTO avaliacaoDTO){
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> buscarAvaliacoesDoProfessor(Long professorId) {
        return avaliacaoRepository.findByProfessorAvaliadoId(professorId);
    }

    public List<Avaliacao> buscarAvaliacoesDoAluno(Long alunoId) {
        return avaliacaoRepository.findByAlunoAvaliadoId(alunoId);
    }

    @Transactional
    public void deletar(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Avaliação não encontrada.");
        }
        avaliacaoRepository.deleteById(id);
    }
}
