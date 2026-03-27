package com.example.avaliacao.model.repository;

import com.example.avaliacao.model.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail (String email);
    Optional<Professor> findByMatricula(String matricula);

    List<Professor> findByAlunosId(Long alunoId);
    List<Professor> findByNomeContainingIgnoreCase(String nome);
    List<Professor> findByCoordenadorId(Long coordenadorId);
}
