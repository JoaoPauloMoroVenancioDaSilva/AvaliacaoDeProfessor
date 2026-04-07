package com.example.avaliacao.controller.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping("/https")
    public Map<String, Object> verificarHttps(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        response.put("secure", request.isSecure());
        response.put("scheme", request.getScheme());
        response.put("serverName", request.getServerName());
        response.put("serverPort", request.getServerPort());

        if (request.isSecure()) {
            response.put("mensagem", "A conexão está utilizando HTTPS.");
        } else {
            response.put("mensagem", "A conexão está utilizando HTTP.");
        }

        return response;
    }
}
