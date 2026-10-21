package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.ItemConferencia;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.domain.StatusConferencia;
import com.cestto.cestto.dto.ItemNotaDTO;
import com.cestto.cestto.repository.ItemConferenciaRepository;
import com.cestto.cestto.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemConferenciaServiceTest {

    @Test
    void deveCriarItemConferencia() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

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

        when(produtoRepository.findByCodigoProdutoAndFeira(
                "123456",
                feira
        )).thenReturn(Optional.of(produto));

        when(itemConferenciaRepository.save(any(ItemConferencia.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ItemConferencia item = service.criarItemConferencia(
                feira,
                "Arroz 5kg",
                "123456",
                1,
                new BigDecimal("25.00"),
                new BigDecimal("25.00")
        );

        assertNotNull(item);

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

        verify(produtoRepository).findByCodigoProdutoAndFeira(
                "123456",
                feira
        );

        verify(itemConferenciaRepository).save(item);
    }

    @Test
    void deveRetornarNullQuandoProdutoNaoExisteNaFeira() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        when(produtoRepository.findByCodigoProdutoAndFeira(
                "999999",
                feira
        )).thenReturn(Optional.empty());

        ItemConferencia item = service.criarItemConferencia(
                feira,
                "Produto inexistente",
                "999999",
                1,
                new BigDecimal("10.00"),
                new BigDecimal("10.00")
        );

        assertNull(item);

        verify(produtoRepository).findByCodigoProdutoAndFeira(
                "999999",
                feira
        );

        verifyNoInteractions(itemConferenciaRepository);
    }

    @Test
    void deveMarcarComoConferidoQuandoPrecoForIgual(){

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        Produto produto = new Produto(
                "Arroz 5kg",
                1,
                5,
                new BigDecimal("25.00"),
                new BigDecimal("25.00")
        );

        when(produtoRepository.findByCodigoProdutoAndFeira(
                "123456",
                feira
        )).thenReturn(Optional.of(produto));

        when(itemConferenciaRepository.save(any(ItemConferencia.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ItemConferencia item = service.criarItemConferencia(
                feira,
                "Arroz 5kg",
                "123456",
                1,
                new BigDecimal("25.00"),
                new BigDecimal("25.00")
        );

        assertNotNull(item);

        assertEquals(
                StatusConferencia.CONFERIDO,
                item.getStatus()
        );
    }

    @Test
    void deveMarcarComoDivergenteQuandoPrecoForDiferente() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

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

        when(produtoRepository.findByCodigoProdutoAndFeira(
                "123456",
                feira
        )).thenReturn(Optional.of(produto));

        when(itemConferenciaRepository.save(any(ItemConferencia.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ItemConferencia item = service.criarItemConferencia(
                feira,
                "Arroz 5kg",
                "123456",
                1,
                new BigDecimal("27.00"),
                new BigDecimal("27.00")
        );

        assertNotNull(item);

        assertEquals(
                StatusConferencia.DIVERGENTE,
                item.getStatus()
        );

        assertSame(feira, item.getFeira());
        assertSame(produto, item.getProduto());

        assertEquals("Arroz 5kg", item.getNome());
        assertEquals("123456", item.getCodigoProduto());

        assertEquals(1, item.getQuantidadeCobrada());

        assertEquals(
                new BigDecimal("27.00"),
                item.getPrecoUnitarioCobrado()
        );

        assertEquals(
                new BigDecimal("27.00"),
                item.getSubtotalCobrado()
        );

        verify(produtoRepository).findByCodigoProdutoAndFeira(
                "123456",
                feira
        );

        verify(itemConferenciaRepository).save(item);
    }

    @Test
    void deveConferirProdutosDaFeira() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        Produto arroz = new Produto(
                "Arroz 5kg",
                1,
                5,
                new BigDecimal("20.00"),
                new BigDecimal("25.00")
        );
        arroz.definirCodigoProduto("123456");

        Produto feijao = new Produto(
                "Feijão 1kg",
                1,
                5,
                new BigDecimal("8.00"),
                new BigDecimal("10.00")
        );
        feijao.definirCodigoProduto("654321");

        Produto leite = new Produto(
                "Leite 1L",
                1,
                5,
                new BigDecimal("5.00"),
                new BigDecimal("6.00")
        );
        leite.definirCodigoProduto("999999");

        when(produtoRepository.findByFeira(feira))
                .thenReturn(List.of(arroz, feijao, leite));

        List<ItemNotaDTO> itensNota = List.of(
                new ItemNotaDTO(
                        "Arroz 5kg",
                        "123456",
                        1,
                        new BigDecimal("25.00"),
                        new BigDecimal("25.00")
                ),
                new ItemNotaDTO(
                        "Feijão 1kg",
                        "654321",
                        1,
                        new BigDecimal("10.00"),
                        new BigDecimal("10.00")
                )
        );

        List<ItemConferencia> resultado =
                service.conferir(feira, itensNota);

        assertEquals(3, resultado.size());

        assertEquals(
                StatusConferencia.CONFERIDO,
                resultado.get(0).getStatus()
        );

        assertEquals(
                StatusConferencia.CONFERIDO,
                resultado.get(1).getStatus()
        );

        assertEquals(
                StatusConferencia.AUSENTE_NA_NOTA,
                resultado.get(2).getStatus()
        );

        assertEquals("Leite 1L", resultado.get(2).getNome());

        assertEquals(
                0,
                resultado.get(2).getQuantidadeCobrada()
        );

        assertNull(
                resultado.get(2).getPrecoUnitarioCobrado()
        );

        assertNull(
                resultado.get(2).getSubtotalCobrado()
        );

        verify(itemConferenciaRepository, times(3))
                .save(any(ItemConferencia.class));
    }

    @Test
    void deveMarcarItemComoNaoPlanejadoQuandoEstiverNaNotaMasNaoNaFeira() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        Produto arroz = new Produto(
                "Arroz 5kg",
                1,
                5,
                new BigDecimal("20.00"),
                new BigDecimal("25.00")
        );

        arroz.definirCodigoProduto("123456");

        when(produtoRepository.findByFeira(feira))
                .thenReturn(List.of(arroz));

        List<ItemNotaDTO> itensNota = List.of(
                new ItemNotaDTO(
                        "Arroz 5kg",
                        "123456",
                        1,
                        new BigDecimal("25.00"),
                        new BigDecimal("25.00")
                ),
                new ItemNotaDTO(
                        "Café 500g",
                        "777777",
                        1,
                        new BigDecimal("12.00"),
                        new BigDecimal("12.00")
                )
        );

        List<ItemConferencia> resultado =
                service.conferir(feira, itensNota);

        assertEquals(2, resultado.size());

        ItemConferencia itemNaoPlanejado = resultado.get(1);

        assertEquals(
                StatusConferencia.NAO_PLANEJADO,
                itemNaoPlanejado.getStatus()
        );

        assertEquals(
                "Café 500g",
                itemNaoPlanejado.getNome()
        );

        assertEquals(
                "777777",
                itemNaoPlanejado.getCodigoProduto()
        );

        assertEquals(
                1,
                itemNaoPlanejado.getQuantidadeCobrada()
        );

        assertEquals(
                new BigDecimal("12.00"),
                itemNaoPlanejado.getPrecoUnitarioCobrado()
        );

        assertEquals(
                new BigDecimal("12.00"),
                itemNaoPlanejado.getSubtotalCobrado()
        );

        assertNull(itemNaoPlanejado.getProduto());

        verify(itemConferenciaRepository, times(2))
                .save(any(ItemConferencia.class));
    }

    @Test
    void deveMarcarComoDivergenteQuandoQuantidadeForDiferente() {

        ItemConferenciaRepository itemConferenciaRepository =
                mock(ItemConferenciaRepository.class);

        ProdutoRepository produtoRepository =
                mock(ProdutoRepository.class);

        ItemConferenciaService service =
                new ItemConferenciaService(
                        itemConferenciaRepository,
                        produtoRepository
                );

        Feira feira = new Feira(
                "Feira da semana",
                "Mix Mateus"
        );

        Produto arroz = new Produto(
                "Arroz 5kg",
                2,
                5,
                new BigDecimal("20.00"),
                new BigDecimal("25.00")
        );

        arroz.definirCodigoProduto("123456");

        when(produtoRepository.findByFeira(feira))
                .thenReturn(List.of(arroz));

        List<ItemNotaDTO> itensNota = List.of(
                new ItemNotaDTO(
                        "Arroz 5kg",
                        "123456",
                        1,
                        new BigDecimal("25.00"),
                        new BigDecimal("25.00")
                )
        );

        List<ItemConferencia> resultado =
                service.conferir(feira, itensNota);

        assertEquals(1, resultado.size());

        ItemConferencia item = resultado.get(0);

        assertEquals(
                StatusConferencia.DIVERGENTE,
                item.getStatus()
        );

        assertEquals(
                "Arroz 5kg",
                item.getNome()
        );

        assertEquals(
                "123456",
                item.getCodigoProduto()
        );

        assertEquals(
                1,
                item.getQuantidadeCobrada()
        );

        assertEquals(
                new BigDecimal("25.00"),
                item.getPrecoUnitarioCobrado()
        );

        assertEquals(
                new BigDecimal("25.00"),
                item.getSubtotalCobrado()
        );

        verify(itemConferenciaRepository)
                .save(item);
    }
}