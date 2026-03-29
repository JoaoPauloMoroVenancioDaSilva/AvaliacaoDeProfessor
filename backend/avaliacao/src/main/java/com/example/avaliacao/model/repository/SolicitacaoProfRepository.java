package com.example.avaliacao.model.repository;

import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.model.Entity.SolicitacaoProf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoProfRepository extends JpaRepository<SolicitacaoProf, Long> {
    List<SolicitacaoProf> findByAprovadaFalse();

    Boolean existsByAlunoIdAndProfessorIdAndAprovadaFalse(Long alunoId, Long professorId);

    boolean existsByAlunoAndProfessor(Aluno aluno, Professor professor);
}
