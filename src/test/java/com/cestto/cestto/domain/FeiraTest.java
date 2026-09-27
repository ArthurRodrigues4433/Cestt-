package com.cestto.cestto.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FeiraTest
{
    @Test
    void deveAdicionarProdutoNaFeira(){
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        Produto produto = new Produto(
                1L,
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
                1L,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        Produto produto2 = new Produto(
                1L,
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
                1L,
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00"));

        boolean resultado = feira.adicionarProduto(produto);

        assertFalse(resultado);
        assertTrue(feira.getProdutos().isEmpty());
    }

    @Test
        void deveCancelarFeira(){

        Feira feira = new Feira(1L, "feira mes", "Mix mateus");

        boolean resultado = feira.cancelar();
        assertTrue(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());

        boolean resultado2 = feira.cancelar();
        assertFalse(resultado2);
    }
}
