package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.model.Entity.SolicitacaoProf;
import com.example.avaliacao.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    //Create aluno
    @PostMapping("/requisitar/{alunoId}/{professorId}")
    public ResponseEntity<String> criarSolicitacao(@PathVariable Long alunoId, @PathVariable Long professorId) {
        solicitacaoService.criarSolicitacao(alunoId, professorId);
        return ResponseEntity.ok("Solicitação enviada com sucesso! Aguarde a aprovação do coordenador.");
    }
    //Aprova coordenador
    @PutMapping("/aprovar/{solicitacaoId}/{coordenadorId}")
    public ResponseEntity<String> aprovarSolicitacao(@PathVariable Long solicitacaoId, @PathVariable Long coordenadorId) {
        solicitacaoService.aprovarSolicitacao(solicitacaoId, coordenadorId);
        return ResponseEntity.ok("Solicitação aprovada! O vínculo entre aluno e professor foi criado.");
    }
    //Deleta coordenador
    @DeleteMapping("/recusar/{solicitacaoId}/{coordenadorId}")
    public ResponseEntity<String> recusarSolicitacao(@PathVariable Long solicitacaoId, @PathVariable Long coordenadorId) {
        solicitacaoService.removerSolicitacao(solicitacaoId, coordenadorId);
        return ResponseEntity.ok("Solicitação recusada e removida do banco.");
    }
    @GetMapping
    public ResponseEntity<List<SolicitacaoProf>> listar() {
        List<SolicitacaoProf> lista = solicitacaoService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    // Endpoint: GET /api/solicitacoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoProf> buscar(@PathVariable Long id) {
        SolicitacaoProf solicitacao = solicitacaoService.buscarPorId(id);
        return ResponseEntity.ok(solicitacao);
    }

}
