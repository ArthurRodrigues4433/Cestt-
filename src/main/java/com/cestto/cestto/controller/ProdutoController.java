package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.dto.ProdutoRequest;
import com.cestto.cestto.dto.ProdutoResponse;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cestto.cestto.service.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;
    private final FeiraService feiraService;

    public ProdutoController(
            ProdutoService produtoService,
            FeiraService feiraService) {
        this.produtoService = produtoService;
        this.feiraService = feiraService;
    }

    @PostMapping("/api/feiras/{feirasId}/produtos")
    public ResponseEntity<ProdutoResponse> criarProduto(
            @PathVariable Long feirasId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.getFeiraPorId(
                feirasId,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.criarProduto(
                feira,
                produtoRequest.getCodigoProduto(),
                produtoRequest.getNome(),
                produtoRequest.getQuantidade(),
                produtoRequest.getQuantidadeMinimaAtacado(),
                produtoRequest.getPrecoAtacado(),
                produtoRequest.getPrecoVarejo()
        );

        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(new ProdutoResponse(produto));
    }

    @GetMapping("/api/feiras/{feirasId}/produtos")
    public ResponseEntity<List<ProdutoResponse>> listarProdutos(@PathVariable Long feirasId) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.getFeiraPorId(
                feirasId,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                feira.getProdutos()
                        .stream()
                        .map(ProdutoResponse::new)
                        .toList()
        );
    }

    @GetMapping("/api/feiras/{feirasId}/produtos/{produtoId}")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(
            @PathVariable Long feirasId,
            @PathVariable Long produtoId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.getFeiraPorId(
                feirasId,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.buscarProduto(produtoId, feira);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }

    @DeleteMapping("/api/feiras/{feirasId}/produtos/{produtoId}")
    public ResponseEntity<Void> deletarProduto(
            @PathVariable Long feirasId,
            @PathVariable Long produtoId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal =
                (UserPrincipal) authentication.getPrincipal();

        Feira feira = feiraService.getFeiraPorId(
                feirasId,
                userPrincipal.getId()
        );

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        boolean removido = produtoService.removerProduto(produtoId, feira);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}