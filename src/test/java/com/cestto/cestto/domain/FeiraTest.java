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


    @Test
    void deveSubtotalDosProdutos() {
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        Produto produto = new Produto(
                1L,
                "feijao",
                6,
                4,
                new BigDecimal("225.00"),
                new BigDecimal("330.00")
        );

        Produto produto2 = new Produto(
                1L,
                "macarrao",
                6,
                4,
                new BigDecimal("15.00"),
                new BigDecimal("20.00")
        );

        Produto produto3 = new Produto(
                1L,
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
    void deveRetornarBigDecimalZeroFeirasSemProdutos() {
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        assertEquals(new BigDecimal("0"), feira.getTotalPrevisto());
    }

    @Test
    void deveCalcularSubtotalDeUmItem() {
        Feira feira = new Feira(
                1L,
                "Feira do mês",
                "Assai"
        );

        Produto produto = new Produto(
                1L,
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
