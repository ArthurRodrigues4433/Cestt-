
package com.cestto.cestto.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FeiraTest {

    @Test
    void deveAdicionarProdutoNaFeira() {
        Feira feira = new Feira("Feira do mês", "Assai");

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        boolean resultado = feira.adicionarProduto(produto);

        assertTrue(resultado);
        assertTrue(feira.getProdutos().contains(produto));
    }

    @Test
    void deveAdicionarDoisProdutosNaFeira() {
        Feira feira = new Feira("Feira do mês", "Assai");

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        Produto produto2 = new Produto(
                "feijão",
                2,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        boolean resultado1 = feira.adicionarProduto(produto);
        boolean resultado2 = feira.adicionarProduto(produto2);

        assertTrue(resultado1);
        assertTrue(resultado2);
        assertEquals(2, feira.getProdutos().size());
    }

    @Test
    void naoDeveAdicionarProdutoEmFeiraFinalizada() {
        Feira feira = new Feira("Feira do mês", "Assai");

        feira.finalizar();

        Produto produto = new Produto(
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        boolean resultado = feira.adicionarProduto(produto);

        assertFalse(resultado);
        assertTrue(feira.getProdutos().isEmpty());
    }

    @Test
    void deveCancelarFeira() {
        Feira feira = new Feira("Feira do mês", "Mix Mateus");

        boolean resultado = feira.cancelar();

        assertTrue(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());

        boolean resultado2 = feira.cancelar();

        assertFalse(resultado2);
    }

    @Test
    void deveSubtotalDosProdutos() {
        Feira feira = new Feira("Feira do mês", "Assai");

        Produto produto = new Produto(
                "feijao",
                6,
                4,
                new BigDecimal("225.00"),
                new BigDecimal("330.00")
        );

        Produto produto2 = new Produto(
                "macarrao",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("20.00")
        );

        Produto produto3 = new Produto(
                "macarrao",
                6,
                4,
                new BigDecimal("12.00"),
                new BigDecimal("20.00")
        );

        feira.adicionarProduto(produto);
        feira.adicionarProduto(produto2);
        feira.adicionarProduto(produto3);

        assertEquals(new BigDecimal("1512.00"), feira.getTotalPrevisto());
    }

    @Test
    void deveRetornarBigDecimalZeroFeiraSemProdutos() {
        Feira feira = new Feira("Feira do mês", "Assai");

        assertEquals(BigDecimal.ZERO, feira.getTotalPrevisto());
    }

    @Test
    void deveCalcularSubtotalDeUmItem() {
        Feira feira = new Feira("Feira do mês", "Assai");

        Produto produto = new Produto(
                "feijao",
                2,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        feira.adicionarProduto(produto);

        assertEquals(new BigDecimal("60.00"), feira.getTotalPrevisto());
    }
}