package com.example.avaliacao.service;

import com.example.avaliacao.controller.dto.AlunoDTO;
import com.example.avaliacao.model.Entity.Aluno;

import com.example.avaliacao.model.Entity.Coordenador;
import com.example.avaliacao.model.repository.AlunoRepository;
import com.example.avaliacao.model.repository.CoordenadorRepository;
import com.example.avaliacao.model.repository.ProfessorRepository;
import com.example.avaliacao.model.repository.SolicitacaoProfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private SolicitacaoProfRepository solicitacaoProfRepository;
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    //CRUD
    @Transactional
    public Aluno cadastrar(AlunoDTO dto) {
        validarCoordenador(dto.getCoordenadorId());
        Coordenador coordenador = coordenadorRepository.findById(dto.getCoordenadorId())
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
        aluno.setMatricula(dto.getMatricula());

        aluno.setCoordenador(coordenador);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long alunoId, AlunoDTO dto, Long autorId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        boolean ehCoordenador = coordenadorRepository.existsById(autorId);
        boolean ehOAluno = alunoId.equals(autorId);

        if (!ehOAluno && !ehCoordenador) {
            throw new RuntimeException("Acesso negado.");
        }
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
        if (ehCoordenador) {
            aluno.setMatricula(dto.getMatricula());

            // Se o coordenador mudou no DTO, atualizamos o vínculo aqui também
            Coordenador novoCoordenador = coordenadorRepository.findById(dto.getCoordenadorId())
                    .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));
            aluno.setCoordenador(novoCoordenador);
        }

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletar(Long alunoId, Long coordenadorId) {
        validarCoordenador(coordenadorId);
        validarAluno(alunoId);
        alunoRepository.deleteById(alunoId);
    }
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    //Métodos Adicionais
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
