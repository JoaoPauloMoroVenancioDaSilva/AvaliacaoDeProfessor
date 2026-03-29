package com.example.avaliacao.service;

import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.model.Entity.SolicitacaoProf;
import com.example.avaliacao.model.repository.AlunoRepository;
import com.example.avaliacao.model.repository.CoordenadorRepository;
import com.example.avaliacao.model.repository.ProfessorRepository;
import com.example.avaliacao.model.repository.SolicitacaoProfRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoProfRepository solicitacaoProfRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CoordenadorRepository coordenadorRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    //Criar solicitacao
    @Transactional
    public void criarSolicitacao(Long alunoId, Long professorId) {


        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        if (solicitacaoProfRepository.existsByAlunoAndProfessor(aluno, professor)) {
            throw new RuntimeException("Você já tem uma solicitação em andamento para este professor.");
        }

        SolicitacaoProf solicitacao = new SolicitacaoProf();
        solicitacao.setAluno(aluno);
        solicitacao.setProfessor(professor);
        solicitacao.setAprovada(false); // Começa como não aprovada

        solicitacaoProfRepository.save(solicitacao);
    }

    //Aprovar solicitacao
    @Transactional
    public void aprovarSolicitacao(Long solicitacaoId, Long coordenadorId) {
        coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));

        SolicitacaoProf solicitacao = solicitacaoProfRepository.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada."));
        solicitacao.setAprovada(true);

        Aluno aluno = solicitacao.getAluno();
        Professor professor = solicitacao.getProfessor();

        if (!aluno.getProfessores().contains(professor)) {
            aluno.getProfessores().add(professor);
            professor.getAlunos().add(aluno);
        }
        alunoRepository.save(aluno);
        solicitacaoProfRepository.save(solicitacao);
    }

    //remover solicitacao
    @Transactional
    public void removerSolicitacao(Long solicitacaoId, Long coordenadorId) {
        coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado."));

        SolicitacaoProf solicitacao = solicitacaoProfRepository.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada."));


        Aluno aluno = solicitacao.getAluno();
        Professor professor = solicitacao.getProfessor();
        if (aluno != null && professor != null) {
            aluno.getProfessores().remove(professor);
            professor.getAlunos().remove(aluno);
        }
        solicitacaoProfRepository.delete(solicitacao);
    }

    public List<SolicitacaoProf> listarTodas() {
        return solicitacaoProfRepository.findAll();
    }

    // Busca uma solicitação específica por ID
    public SolicitacaoProf buscarPorId(Long id) {
        //return solicitacaoProfRepository.findById(id)
        //        .orElseThrow(() -> new RuntimeException("ID não encontrado."));
        return solicitacaoProfRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação " + id + " não existe no banco."));
    }
}
