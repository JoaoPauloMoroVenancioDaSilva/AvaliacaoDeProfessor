package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.controller.dto.CoordenadorDTO;
import com.example.avaliacao.model.Entity.Coordenador;
import com.example.avaliacao.service.CoordenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {

    @Autowired
    private CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<Coordenador> cadastrar(@RequestBody CoordenadorDTO dto) {
        return ResponseEntity.ok(coordenadorService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Coordenador>> listarTodos() {
        return ResponseEntity.ok(coordenadorService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CoordenadorDTO dto) {
        try {
            return ResponseEntity.ok(coordenadorService.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            coordenadorService.deletar(id);
            return ResponseEntity.ok("Coordenador removido com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}