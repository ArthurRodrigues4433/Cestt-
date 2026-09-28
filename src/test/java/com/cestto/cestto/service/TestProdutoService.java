package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class TestProdutoService {

    @Test
    void naoDeveAdicionarProdutoEmFeiraFinalizada() {

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        feira.finalizar();

        ProdutoService service = new ProdutoService();

        Produto resultado =  service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        assertNull(resultado);
        assertTrue(feira.getProdutos().isEmpty());
    }

    @Test
    void deveBuscarProdutoPorId() {

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto =  service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        Produto resultado = service.buscarProduto(feira, produto.getProdutoId());

        assertNotNull(resultado);
        assertEquals(produto, resultado);
    }

    @Test
    void naoDeveEncontrarProdutoInexistente(){

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto =  service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        Produto resultado = service.buscarProduto(feira, 22L);

        assertNull(resultado);
    }

    @Test
    void deveAlterarProduto() {

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        Produto resultado = service.alterarProduto(
                feira,
                produto.getProdutoId(),
                "feijao",
                10,
                4,
                new BigDecimal("8.00"),
                new BigDecimal("9.00")
        );

        assertNotNull(resultado);
        assertEquals("feijao", resultado.getNome());
        assertEquals(10, resultado.getQuantidade());
        assertEquals(new BigDecimal("8.00"), resultado.getPrecoAtacado());
        assertEquals(new BigDecimal("9.00"), resultado.getPrecoVarejo());
        assertEquals(new BigDecimal("8.00"), resultado.getPrecoEscolhido());
        assertEquals(new BigDecimal("80.00"), resultado.getSubtotal());
    }


    @Test
    void naoDeveAlterarProdutoInexistente() {

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto resultado = service.alterarProduto(
                feira,
                99L,
                "feijao",
                10,
                4,
                new BigDecimal("8.00"),
                new BigDecimal("9.00")
        );

        assertNull(resultado);
    }


    @Test
    void deveRecusarProdutoSemPrecoAtacadoQuandoQuantidadeMinimaForMaiorQueZero(){

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                null,
                new BigDecimal("25.00")
        );

        assertNull(produto);
        assertTrue(feira.getProdutos().isEmpty());
    }

    @Test
    void naoDeveAlterarProdutoEmFeiraFinalizadaOuCancelada(){

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        );

        feira.finalizar();

        Produto resultado = service.alterarProduto(
                feira,
                produto.getProdutoId(),
                "feijao",
                10,
                5,
                new BigDecimal("28.00"),
                new BigDecimal("45.00")
        );

        assertNull(resultado);
        assertEquals("arroz", produto.getNome());
        assertEquals(6, produto.getQuantidade());
        assertEquals(4, produto.getQuantidadeMinimaAtacado());
        assertEquals(new BigDecimal("25.00"), produto.getPrecoVarejo());
    }

    @Test
    void deveRemoverProdutoEmFeirasEmAndamento(){

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        );

        boolean resultado = service.removerProduto(feira, produto.getProdutoId());

        assertTrue(resultado);
        assertTrue(feira.getProdutos().isEmpty());
    }

    @Test
    void naoDeveRemoverProdutoEmFeiraFinalizada(){

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        );

        feira.finalizar();

        boolean resultado = service.removerProduto(feira, produto.getProdutoId());

        assertFalse(resultado);
        assertFalse(feira.getProdutos().isEmpty());
    }

    @Test
    void naoDeveRemoverProdutoInexistente(){
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        ProdutoService service = new ProdutoService();

        Produto produto = service.criarProduto(
                feira,
                "arroz",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("25.00")
        );

        boolean resultado = service.removerProduto(feira, 555L);

        assertFalse(resultado);
        assertFalse(feira.getProdutos().isEmpty());
    }

}