package com.example.avaliacao.model.repository;

import com.example.avaliacao.model.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByEmail(String email);

    List<Aluno> findByCoordenadorId(Long coordenadorId);
}
