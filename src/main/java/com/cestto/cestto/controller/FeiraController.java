package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.dto.FeiraRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class FeiraController {

    @PostMapping("/api/feiras")
    public ResponseEntity<String> criarFeira(@RequestBody FeiraRequest feiraRequest) {
        if (feiraRequest.getNome() == null || feiraRequest.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        if (feiraRequest.getSupermercado() == null || feiraRequest.getSupermercado().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        Feira feira = new Feira(
                feiraRequest.getNome(),
                feiraRequest.getSupermercado()
        );

        return ResponseEntity.ok("Feira: "+ feira.getNome() + " criada no supermercado: "+feira.getSupermercado());
    }
}
