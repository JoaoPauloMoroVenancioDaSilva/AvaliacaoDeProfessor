package com.example.avaliacao.model.repository;

import com.example.avaliacao.model.Entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByProfessorAvaliadoId(Long id);
    List<Avaliacao> findByAlunoAvaliadoId(Long id);
}
