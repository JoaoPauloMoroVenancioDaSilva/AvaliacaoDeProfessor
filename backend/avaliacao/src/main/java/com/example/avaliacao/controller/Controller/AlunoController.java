package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.controller.dto.AlunoDTO;
import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody AlunoDTO dto) {
        try {
            Aluno aluno = alunoService.cadastrar(dto);
            return ResponseEntity.ok(aluno);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PutMapping("/{id}/{autorId}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @PathVariable Long autorId, @RequestBody AlunoDTO dto) {
        try {
            Aluno alunoAtualizado = alunoService.atualizar(id, dto, autorId);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/{coordenadorId}")
    public ResponseEntity<?> deletar(@PathVariable Long id, @PathVariable Long coordenadorId) {
        try {
            alunoService.deletar(id, coordenadorId);
            return ResponseEntity.ok("Aluno removido com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}