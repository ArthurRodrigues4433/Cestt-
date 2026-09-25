package com.cestto.cestto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class TestProduto {

    @Test
    void deveUsarPrecoVarejoQuandoNaoExistePrecoAtacado() {

    Produto produto = new Produto("arroz", 12, 4, null, new BigDecimal("30.00"));

    produto.escolherPreco();

    assertEquals(produto.getPrecoVarejo(), produto.getPrecoEscolhido());
    }

    @Test
    void deveUsarPrecoVarejoQuandoPrecosForemIguais() {

        Produto produto = new Produto("arroz", 12, 4, new BigDecimal("30.00"), new BigDecimal("30.00"));

        produto.escolherPreco();

        assertEquals(produto.getPrecoVarejo(), produto.getPrecoEscolhido());
    }

    @Test
    void deveUsarPrecoVarejoQuandoQuantidadeMinimaForMaiorQueQuantidade() {

        Produto produto = new Produto("arroz", 1, 4, new BigDecimal("25.00"), new BigDecimal("30.00"));

        produto.escolherPreco();

        assertEquals(produto.getPrecoVarejo(), produto.getPrecoEscolhido());
    }

    @Test
    void deveUsarPrecoAtacadoQuandoQuantidadeForIgualAoMinimo() {

        Produto produto = new Produto("arroz", 4, 4, new BigDecimal("25.00"), new BigDecimal("30.00"));

        produto.escolherPreco();

        assertEquals(produto.getPrecoAtacado(), produto.getPrecoEscolhido());
    }

    @Test
    void deveUsarPrecoAtacadoQuandoQuantidadeForMaiorQueOMinimo() {

        Produto produto = new Produto("arroz", 6, 4, new BigDecimal("25.00"), new BigDecimal("30.00"));

        produto.escolherPreco();

        assertEquals(produto.getPrecoAtacado(), produto.getPrecoEscolhido());
    }

    @Test
    void deveCalcularSubtotal() {

        Produto produto = new Produto("arroz", 6, 4, new BigDecimal("25.00"), new BigDecimal("30.00"));

        produto.escolherPreco();
        produto.calcularSubtotal();

        assertEquals(new BigDecimal("150.00"),  produto.getSubtotal());
    }


}
