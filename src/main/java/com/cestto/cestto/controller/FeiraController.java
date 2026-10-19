package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.dto.FeiraRequest;
import com.cestto.cestto.dto.FeiraResponse;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
public class FeiraController {

    private final FeiraService feiraService;

    public FeiraController(FeiraService feiraService) {
        this.feiraService = feiraService;
    }

    @PostMapping("/api/feiras")
    public ResponseEntity<String> criarFeira(@RequestBody FeiraRequest feiraRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        if (feiraRequest.getNome() == null || feiraRequest.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        if (feiraRequest.getSupermercado() == null || feiraRequest.getSupermercado().isBlank()) {
            return ResponseEntity.badRequest().body("Erro ao criar feira.");
        }

        Feira feira = feiraService.criarFeira(
                feiraRequest.getNome(),
                feiraRequest.getSupermercado(),
                userPrincipal.getId()
        );

        if  (feira == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma feira em andamento.");
        }

        return ResponseEntity.ok("Feira: "+ feira.getNome() + " criada no supermercado: "+feira.getSupermercado());
    }

    @GetMapping("/api/feiras")
    public ResponseEntity<List<FeiraResponse>> getFeiras() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        List<FeiraResponse> feiras = feiraService.listarFeiras(userPrincipal.getId())
                .stream()
                .map(FeiraResponse::new)
                .toList();

        return ResponseEntity.ok(feiras);
    }

    @GetMapping("/api/feiras/{id}")
    public ResponseEntity<FeiraResponse> getFeiraPorId(@PathVariable Long id) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.getFeiraPorId(
                id,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new FeiraResponse(feira));
    }


    @PutMapping("/api/feiras/{id}/finalizar")
    public ResponseEntity<FeiraResponse> finalizarFeira(@PathVariable Long id) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.finalizarFeiraPorId(
                id,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new FeiraResponse(feira));
    }

    @PutMapping("/api/feiras/{id}/cancelar")
    public ResponseEntity<FeiraResponse> cancelarFeira(@PathVariable Long id) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.cancelarFeiraPorId(
                id,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new FeiraResponse(feira));
    }

    @DeleteMapping("/api/feiras/{id}")
    public ResponseEntity<Void> deletarFeira(@PathVariable Long id) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        boolean deletada = feiraService.deletarFeiraPorId(
                id,
                userPrincipal.getId()
        );

        if (!deletada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}