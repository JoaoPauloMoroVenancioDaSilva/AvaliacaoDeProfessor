package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.controller.dto.AvaliacaoDTO;
import com.example.avaliacao.model.Entity.Avaliacao;
import com.example.avaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        Avaliacao novaAvaliacao = avaliacaoService.salvar(avaliacaoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<Avaliacao>> listarPorProfessor(@PathVariable Long id) {
        List<Avaliacao> avaliacoes = avaliacaoService.buscarAvaliacoesDoProfessor(id);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<Avaliacao>> listarPorAluno(@PathVariable Long id) {
        List<Avaliacao> avaliacoes = avaliacaoService.buscarAvaliacoesDoAluno(id);
        return ResponseEntity.ok(avaliacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
        Avaliacao atualizada = avaliacaoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
