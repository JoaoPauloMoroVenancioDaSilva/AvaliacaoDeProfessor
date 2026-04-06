package com.example.avaliacao.security;

import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.model.Entity.Coordenador;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.model.repository.AlunoRepository;
import com.example.avaliacao.model.repository.CoordenadorRepository;
import com.example.avaliacao.model.repository.ProfessorRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetails implements UserDetailsService {

    private final ProfessorRepository professorRepo;
    private final AlunoRepository alunoRepo;
    private final CoordenadorRepository coordenadorRepo;

    public CustomUserDetails(
            ProfessorRepository professorRepo,
            AlunoRepository alunoRepo,
            CoordenadorRepository coordenadorRepo) {

        this.professorRepo = professorRepo;
        this.alunoRepo = alunoRepo;
        this.coordenadorRepo = coordenadorRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Optional<Professor> professor =
                professorRepo.findByEmail(email);

        if (professor.isPresent()) {

            Professor p = professor.get();

            return User.builder()
                    .username(p.getEmail())
                    .password(p.getSenha())
                    .roles("PROFESSOR")
                    .build();
        }

        Optional<Aluno> aluno =
                alunoRepo.findByEmail(email);

        if (aluno.isPresent()) {

            Aluno a = aluno.get();

            return User.builder()
                    .username(a.getEmail())
                    .password(a.getSenha())
                    .roles("ALUNO")
                    .build();
        }

        Optional<Coordenador> coordenador =
                coordenadorRepo.findByEmail(email);

        if (coordenador.isPresent()) {

            Coordenador c = coordenador.get();

            return User.builder()
                    .username(c.getEmail())
                    .password(c.getSenha())
                    .roles("COORDENADOR")
                    .build();
        }

        throw new UsernameNotFoundException(
                "Usuário não encontrado com email: " + email);
    }
}
