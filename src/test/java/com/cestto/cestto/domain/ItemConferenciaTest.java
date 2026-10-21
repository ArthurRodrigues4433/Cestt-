package com.cestto.cestto.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemConferenciaTest {
        @Test
        void deveCriarItemConferenciaComoConferido() {

            Feira feira = new Feira(
                    "Feira da semana",
                    "Mix Mateus"
            );

            Produto produto = new Produto(
                    "Arroz 5kg",
                    1,
                    5,
                    new BigDecimal("20.00"),
                    new BigDecimal("25.00")
            );

            produto.setFeira(feira);

            ItemConferencia item = new ItemConferencia(
                    feira,
                    produto,
                    "Arroz 5kg",
                    "123456",
                    1,
                    new BigDecimal("25.00"),
                    new BigDecimal("25.00"),
                    StatusConferencia.CONFERIDO
            );

            assertSame(feira, item.getFeira());
            assertSame(produto, item.getProduto());
            assertEquals("Arroz 5kg", item.getNome());
            assertEquals("123456", item.getCodigoProduto());
            assertEquals(1, item.getQuantidadeCobrada());
            assertEquals(
                    new BigDecimal("25.00"),
                    item.getPrecoUnitarioCobrado()
            );
            assertEquals(
                    new BigDecimal("25.00"),
                    item.getSubtotalCobrado()
            );
            assertEquals(
                    StatusConferencia.CONFERIDO,
                    item.getStatus()
            );
        }

    @Test
    void deveCriarItemConferenciaComoAusenteNaNota() {

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        Produto produto = new Produto(
                "Arroz 5kg",
                1,
                5,
                new BigDecimal("20.00"),
                new BigDecimal("25.00")
        );

        produto.setFeira(feira);

        ItemConferencia item = new ItemConferencia(
                feira,
                produto,
                produto.getNome(),
                "123456",
                0,
                null,
                BigDecimal.ZERO,
                StatusConferencia.AUSENTE_NA_NOTA
        );

        assertSame(feira, item.getFeira());
        assertSame(produto, item.getProduto());
        assertEquals("Arroz 5kg", item.getNome());
        assertEquals("123456", item.getCodigoProduto());
        assertEquals(0, item.getQuantidadeCobrada());
        assertNull(item.getPrecoUnitarioCobrado());
        assertEquals(
                BigDecimal.ZERO,
                item.getSubtotalCobrado()
        );
        assertEquals(
                StatusConferencia.AUSENTE_NA_NOTA,
                item.getStatus()
        );
    }

    @Test
    void deveCriarItemConferenciaComoNaoPlanejado() {

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        ItemConferencia item = new ItemConferencia(
                feira,
                null,
                "Arroz 5kg",
                "123456",
                1,
                new BigDecimal("25.00"),
                new BigDecimal("25.00"),
                StatusConferencia.NAO_PLANEJADO
        );

        assertSame(feira, item.getFeira());
        assertNull(item.getProduto());
        assertEquals("Arroz 5kg", item.getNome());
        assertEquals("123456", item.getCodigoProduto());
        assertEquals(1, item.getQuantidadeCobrada());
        assertEquals(
                new BigDecimal("25.00"),
                item.getPrecoUnitarioCobrado()
        );
        assertEquals(
                new BigDecimal("25.00"),
                item.getSubtotalCobrado()
        );
        assertEquals(
                StatusConferencia.NAO_PLANEJADO,
                item.getStatus()
        );
    }
}