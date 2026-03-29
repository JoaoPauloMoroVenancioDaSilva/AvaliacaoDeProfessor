package com.example.avaliacao.service;

import com.example.avaliacao.controller.dto.ProfessorDTO;
import com.example.avaliacao.model.Entity.Coordenador;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.model.repository.AlunoRepository;
import com.example.avaliacao.model.repository.CoordenadorRepository;
import com.example.avaliacao.model.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CoordenadorRepository coordenadorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    //CRUD
    @Transactional
    public Professor cadastrar(ProfessorDTO dto, Long coordenadorId) {
        Coordenador coordenador = coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        professor.setMatricula(dto.getMatricula());
        professor.setCoordenador(coordenador);

        return professorRepository.save(professor);
    }

    @Transactional
    public Professor atualizar(Long professorId, ProfessorDTO dto, Long autorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        boolean ehProfessor = professorId.equals(autorId);
        boolean ehCoordenador = coordenadorRepository.existsById(autorId);

        if (!ehProfessor && !ehCoordenador) {
            throw new RuntimeException("Acesso negado:");
        }

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(dto.getSenha());
        if (ehCoordenador) {
            professor.setMatricula(dto.getMatricula());
            Coordenador novoCoordenador = coordenadorRepository.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new RuntimeException("Coordenador  não encontrado."));
            professor.setCoordenador(novoCoordenador);
        }
        return professorRepository.save(professor);

    }

    @Transactional
    public void deletar(Long professorId, Long coordenadorId) {
        // Valida se quem quer deletar é um coordenador
        validarCoordenador(coordenadorId);
        validarProfessor(professorId);

        professorRepository.deleteById(professorId);
    }


    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    // Métodos auxiliares

    private void validarCoordenador(Long coordenadorId) {
        coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));
    }
    private void validarAluno(Long alunoId) {
        alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }
    private void validarProfessor(Long professorId) {
        professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));
    }
}
