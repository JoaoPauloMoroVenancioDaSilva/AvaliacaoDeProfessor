package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.controller.dto.ProfessorDTO;
import com.example.avaliacao.model.Entity.Professor;
import com.example.avaliacao.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class    ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ProfessorDTO dto) {
        try {
            Professor professor = professorService.cadastrar(dto, dto.getCoordenadorId());
            return ResponseEntity.ok(professor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Listar todos os Professores
    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @PutMapping("/{id}/{autorId}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @PathVariable Long autorId, @RequestBody ProfessorDTO dto) {
        try {
            Professor professorAtualizado = professorService.atualizar(id, dto, autorId);
            return ResponseEntity.ok(professorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deletar Professor (Ação do Coordenador)
    @DeleteMapping("/{id}/{coordenadorId}")
    public ResponseEntity<?> deletar(@PathVariable Long id, @PathVariable Long coordenadorId) {
        try {
            professorService.deletar(id, coordenadorId);
            return ResponseEntity.ok("Professor removido com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}