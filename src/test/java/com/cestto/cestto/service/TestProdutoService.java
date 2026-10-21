package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.Produto;
import com.cestto.cestto.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.clearInvocations;

class TestProdutoService {

    private ProdutoRepository produtoRepository;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        service = new ProdutoService(produtoRepository);

        when(produtoRepository.save(any(Produto.class)))
                .thenAnswer(invocation -> {
                    Produto produto = invocation.getArgument(0);

                    if (produto.getProdutoId() == null) {
                        ReflectionTestUtils.setField(
                                produto,
                                "produtoId",
                                1L
                        );
                    }

                    return produto;
                });
    }

    private Feira criarFeira() {
        Feira feira = new Feira("Feira do mês", "Assaí");

        ReflectionTestUtils.setField(feira, "id", 10L);

        return feira;
    }

    private Produto criarProduto(Feira feira) {
        Produto produto = service.criarProduto(
                feira,
                "123456",
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        assertNotNull(produto);

        when(produtoRepository.findByProdutoIdAndFeira(
                anyLong(),
                eq(feira)
        )).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);

            if (produto.getProdutoId().equals(id)) {
                return Optional.of(produto);
            }

            return Optional.empty();
        });

        return produto;
    }

    @Test
    void naoDeveAdicionarProdutoEmFeiraFinalizada() {
        Feira feira = criarFeira();
        feira.finalizar();

        Produto resultado = service.criarProduto(
                feira,
                "123456",
                "arroz",
                6,
                4,
                new BigDecimal("25.00"),
                new BigDecimal("30.00")
        );

        assertNull(resultado);
        assertTrue(feira.getProdutos().isEmpty());

        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    void deveBuscarProdutoPorId() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        Produto resultado = service.buscarProduto(
                produto.getProdutoId(),
                feira
        );

        assertNotNull(resultado);
        assertEquals(produto, resultado);
    }

    @Test
    void naoDeveEncontrarProdutoInexistente() {
        Feira feira = criarFeira();
        criarProduto(feira);

        Produto resultado = service.buscarProduto(22L, feira);

        assertNull(resultado);
    }

    @Test
    void deveAlterarProduto() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        clearInvocations(produtoRepository);

        Produto resultado = service.alterarProduto(
                produto.getProdutoId(),
                feira,
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

        verify(produtoRepository).save(produto);
    }

    @Test
    void naoDeveAlterarProdutoInexistente() {
        Feira feira = criarFeira();
        criarProduto(feira);

        Produto resultado = service.alterarProduto(
                99L,
                feira,
                "feijao",
                10,
                4,
                new BigDecimal("8.00"),
                new BigDecimal("9.00")
        );

        assertNull(resultado);
    }

    @Test
    void deveRecusarProdutoSemPrecoAtacadoQuandoQuantidadeMinimaForMaiorQueZero() {
        Feira feira = criarFeira();

        Produto produto = service.criarProduto(
                feira,
                "123456",
                "arroz",
                6,
                4,
                null,
                new BigDecimal("25.00")
        );

        assertNull(produto);
        assertTrue(feira.getProdutos().isEmpty());

        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    void naoDeveAlterarProdutoEmFeiraFinalizada() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        feira.finalizar();

        Produto resultado = service.alterarProduto(
                produto.getProdutoId(),
                feira,
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

        assertEquals(
                new BigDecimal("30.00"),
                produto.getPrecoVarejo()
        );
    }

    @Test
    void naoDeveAlterarProdutoEmFeiraCancelada() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        feira.cancelar();

        Produto resultado = service.alterarProduto(
                produto.getProdutoId(),
                feira,
                "feijao",
                10,
                5,
                new BigDecimal("28.00"),
                new BigDecimal("45.00")
        );

        assertNull(resultado);
        assertEquals("arroz", produto.getNome());
        assertEquals(6, produto.getQuantidade());
    }

    @Test
    void deveRemoverProdutoEmFeiraEmAndamento() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        boolean resultado = service.removerProduto(
                produto.getProdutoId(),
                feira
        );

        assertTrue(resultado);

        // A remoção chama o repositório.
        verify(produtoRepository).delete(produto);
    }

    @Test
    void naoDeveRemoverProdutoEmFeiraFinalizada() {
        Feira feira = criarFeira();
        Produto produto = criarProduto(feira);

        feira.finalizar();

        boolean resultado = service.removerProduto(
                produto.getProdutoId(),
                feira
        );

        assertFalse(resultado);

        verify(produtoRepository, never()).delete(any(Produto.class));
    }

    @Test
    void naoDeveRemoverProdutoInexistente() {
        Feira feira = criarFeira();
        criarProduto(feira);

        boolean resultado = service.removerProduto(555L, feira);

        assertFalse(resultado);

        verify(produtoRepository, never()).delete(any(Produto.class));
    }
}