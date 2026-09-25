package com.cestto.cestto.domain;

import com.cestto.cestto.service.FeiraService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestAdicionarProduto
{
    @Test
    void deveAdicionarProdutoNaFeira(){
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        feira.adicionarProduto(produto);

        assertTrue(feira.getProdutos().contains(produto));
    }

    @Test
    void deveAdicionarDoisProdutosNaFeira() {

        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        Produto produto2 = new Produto(
                "feijão",
                2,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        feira.adicionarProduto(produto);
        feira.adicionarProduto(produto2);

        assertEquals(2, feira.getProdutos().size());
    }

    @Test
    void naoDeveAdicionarProdutoEmFeiraFinalizada() {
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        feira.finalizar();

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        boolean resultado = feira.adicionarProduto(produto);

        assertFalse(resultado);
        assertTrue(feira.getProdutos().isEmpty());
    }
}
