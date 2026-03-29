package com.example.avaliacao.service;

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
import java.util.Optional;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;


}
