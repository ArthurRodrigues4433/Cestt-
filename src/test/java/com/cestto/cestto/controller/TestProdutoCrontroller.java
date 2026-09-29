package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.dto.ProdutoRequest;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestProdutoCrontroller {

    @Test
    void deveCriarProdutoPeloController(){

        FeiraService feiraService = new FeiraService();
        ProdutoService produtoService = new ProdutoService();
        ProdutoController controller = new ProdutoController(produtoService, feiraService);

        Feira feira = feiraService.criarFeira("Feira do mês", "Assai");

        ProdutoRequest request = new ProdutoRequest();
        request.setNome("arroz");
        request.setQuantidade(6);
        request.setQuantidadeMinimaAtacado(4);
        request.setPrecoAtacado(new BigDecimal("15.00"));
        request.setPrecoVarejo(new BigDecimal("25.00"));

        ResponseEntity<Produto> response = controller.criarProduto(feira.getId(),  request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("arroz", response.getBody().getNome());
    }

    @Test
    void naoDeveCriarProdutoEmFeiraInexistente(){
        FeiraService feiraService = new FeiraService();
        ProdutoService produtoService = new ProdutoService();
        ProdutoController controller = new ProdutoController(produtoService, feiraService);

        Feira feira = feiraService.criarFeira("Feira do mês", "Assai");

        ProdutoRequest request = new ProdutoRequest();
        request.setNome("arroz");
        request.setQuantidade(6);
        request.setQuantidadeMinimaAtacado(4);
        request.setPrecoAtacado(new BigDecimal("15.00"));
        request.setPrecoVarejo(new BigDecimal("25.00"));

        ResponseEntity<Produto> response = controller.criarProduto(99L,  request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
