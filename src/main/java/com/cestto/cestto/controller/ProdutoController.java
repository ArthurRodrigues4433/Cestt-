package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.dto.ProdutoRequest;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
}