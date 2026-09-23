package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.dto.FeiraRequest;
import com.cestto.cestto.service.FeiraService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController

public class FeiraController {

    private final FeiraService feiraService;

    public FeiraController(FeiraService feiraService) {
        this.feiraService = feiraService;
    }

    @PostMapping("/api/feiras")
    public ResponseEntity<String> criarFeira(@RequestBody FeiraRequest feiraRequest) {
        if (feiraRequest.getNome() == null || feiraRequest.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        if (feiraRequest.getSupermercado() == null || feiraRequest.getSupermercado().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        Feira feira = feiraService.criarFeira(
                feiraRequest.getNome(),
                feiraRequest.getSupermercado()
        );

        return ResponseEntity.ok("Feira: "+ feira.getNome() + " criada no supermercado: "+feira.getSupermercado());
    }
}
