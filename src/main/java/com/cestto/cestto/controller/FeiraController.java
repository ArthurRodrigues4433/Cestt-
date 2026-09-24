package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.dto.FeiraRequest;
import com.cestto.cestto.service.FeiraService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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

        if  (feira == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma feira em andamento.");
        }

        return ResponseEntity.ok("Feira: "+ feira.getNome() + " criada no supermercado: "+feira.getSupermercado());
    }

    @GetMapping("/api/feiras")
    public ResponseEntity<List<Feira>> getFeiras() {
        return ResponseEntity.ok(feiraService.getFeiras());
    }

    @GetMapping("/api/feiras/{id}")
    public ResponseEntity<Feira> getFeiraPorId(@PathVariable Long id) {

        Feira feira = feiraService.getFeiraPorId(id);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feira);
    }

    @PutMapping("/api/feiras/{id}/finalizar")
    public ResponseEntity<Feira> finalizarFeira(@PathVariable Long id) {

        Feira feira = feiraService.finalizarFeiraPorId(id);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feira);
    }

    @PutMapping("/api/feiras/{id}/cancelar")
    public ResponseEntity<Feira> cancelarFeira(@PathVariable Long id) {
        Feira feira = feiraService.cancelarFeiraPorId(id);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feira);
    }

    @DeleteMapping("/api/feiras/{id}")
    public ResponseEntity<Feira> deletarFeira(@PathVariable Long id) {
        boolean deletada = feiraService.deletarFeiraPorId(id);

        if (!deletada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
