package com.example.avaliacao.controller.Controller;

import com.example.avaliacao.controller.dto.LoginDTO;
import com.example.avaliacao.controller.dto.TokenDTO;
import com.example.avaliacao.model.Entity.Aluno;
import com.example.avaliacao.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenDTO> efetuarLogin (@RequestBody LoginDTO dados) {
        try {
            System.out.println("E-mail que chegou do front: " + dados.getEmail());
            System.out.println("Senha que chegou do front: " + dados.getSenha());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    dados.getEmail(),
                    dados.getSenha()
            );

            Authentication auth = manager.authenticate(authenticationToken);
            String token = tokenService.gerarToken((UserDetails) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(token));

        } catch (Exception e) {
            //se a senha ou email estiverem errados, ele captura o erro e devolve o Status 401
            return ResponseEntity.status(401).body(new TokenDTO("Erro de autenticação: Credenciais inválidas."));
        }
    }
}
