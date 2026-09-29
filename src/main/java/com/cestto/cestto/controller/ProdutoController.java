package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.dto.ProdutoRequest;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<Produto> criarProduto(
            @PathVariable Long feirasId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {

        Feira feira = feiraService.getFeiraPorId(feirasId);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.criarProduto(
                feira,
                produtoRequest.getNome(),
                produtoRequest.getQuantidade(),
                produtoRequest.getQuantidadeMinimaAtacado(),
                produtoRequest.getPrecoAtacado(),
                produtoRequest.getPrecoVarejo()
        );

        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/api/feiras/{feirasId}/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long feirasId) {
        Feira feira = feiraService.getFeiraPorId(feirasId);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(feira.getProdutos());
    }

    @GetMapping("/api/feiras/{feirasId}/produtos/{produtoId}")
    public ResponseEntity<Produto> buscarProdutoPorId(
            @PathVariable Long feirasId,
            @PathVariable Long produtoId) {

        Feira feira = feiraService.getFeiraPorId(feirasId);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoService.buscarProduto(feira, produtoId);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/api/feiras/{feirasId}/produtos/{produtoId}")
    public ResponseEntity<Void> deletarProduto(
            @PathVariable Long feirasId,
            @PathVariable Long produtoId) {

        Feira feira = feiraService.getFeiraPorId(feirasId);

        if (feira == null) {
            return ResponseEntity.notFound().build();
        }

        boolean removido = produtoService.removerProduto(feira, produtoId);

        if (!removido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}