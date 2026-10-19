
package com.cestto.cestto.controller;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.dto.ProdutoRequest;
import com.cestto.cestto.dto.ProdutoResponse;
import com.cestto.cestto.service.FeiraService;
import com.cestto.cestto.service.ProdutoService;
import com.cestto.cestto.service.UserPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestProdutoController {

    @AfterEach
    void limparAutenticacao() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void deveCriarProdutoPeloController() {
        FeiraService feiraService = mock(FeiraService.class);
        ProdutoService produtoService = mock(ProdutoService.class);

        ProdutoController controller =
                new ProdutoController(produtoService, feiraService);

        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(1L);

        UserPrincipal principal = new UserPrincipal(usuario);

        var autenticacao = new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(autenticacao);

        Feira feira = mock(Feira.class);
        when(feiraService.getFeiraPorId(10L, 1L)).thenReturn(feira);

        ProdutoRequest request = new ProdutoRequest();
        request.setNome("arroz");
        request.setQuantidade(6);
        request.setQuantidadeMinimaAtacado(4);
        request.setPrecoAtacado(new BigDecimal("15.00"));
        request.setPrecoVarejo(new BigDecimal("25.00"));

        Produto produto = mock(Produto.class);
        when(produtoService.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        )).thenReturn(produto);

        when(produto.getProdutoId()).thenReturn(1L);
        when(produto.getNome()).thenReturn("arroz");
        when(produto.getQuantidade()).thenReturn(6);
        when(produto.getQuantidadeMinimaAtacado()).thenReturn(4);
        when(produto.getPrecoAtacado()).thenReturn(new BigDecimal("15.00"));
        when(produto.getPrecoVarejo()).thenReturn(new BigDecimal("25.00"));
        when(produto.getPrecoEscolhido()).thenReturn(new BigDecimal("15.00"));
        when(produto.getSubtotal()).thenReturn(new BigDecimal("90.00"));

        ResponseEntity<ProdutoResponse> response =
                controller.criarProduto(10L, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("arroz", response.getBody().getNome());

        verify(produtoService).criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        );
    }
}
