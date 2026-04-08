package com.example.avaliacao.service;

import com.example.avaliacao.controller.dto.CoordenadorDTO;
import com.example.avaliacao.model.Entity.Coordenador;
import com.example.avaliacao.model.repository.CoordenadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Coordenador cadastrar(CoordenadorDTO dto) {
        Coordenador coordenador = new Coordenador();
        coordenador.setNome(dto.getNome());
        coordenador.setEmail(dto.getEmail());

        //o Spring criptografar a senha sozinho
        coordenador.setSenha(passwordEncoder.encode(dto.getSenha()));

        coordenador.setMatricula(dto.getMatricula());

        return coordenadorRepository.save(coordenador);
    }

    @Transactional
    public Coordenador atualizar(Long id, CoordenadorDTO dto) {
        Coordenador coordenador = coordenadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordenador não encontrado."));

        coordenador.setNome(dto.getNome());
        coordenador.setEmail(dto.getEmail());
        coordenador.setSenha(dto.getSenha());
        coordenador.setMatricula(dto.getMatricula());

        return coordenadorRepository.save(coordenador);
    }

    @Transactional
    public void deletar(Long id) {
        if (!coordenadorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Coordenador não encontrado.");
        }
        coordenadorRepository.deleteById(id);
    }

    public List<Coordenador> listarTodos() {
        return coordenadorRepository.findAll();
    }
}
