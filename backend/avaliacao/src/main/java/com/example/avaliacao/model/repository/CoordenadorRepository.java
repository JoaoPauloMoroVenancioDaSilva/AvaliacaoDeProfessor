package com.example.avaliacao.model.repository;

import com.example.avaliacao.model.Entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    Optional<Coordenador> findByEmail (String email);

    List<Coordenador> findByNomeContainingIgnoreCase(String nome);
}
