package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;
import com.cestto.cestto.domain.Usuario;
import com.cestto.cestto.repository.FeiraRepository;
import com.cestto.cestto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.clearInvocations;

class TestFeiraService {

    private FeiraRepository feiraRepository;
    private UsuarioRepository usuarioRepository;
    private FeiraService feiraService;

    private Usuario usuario;
    private final Long userId = 1L;

    @BeforeEach
    void setUp() {
        feiraRepository = mock(FeiraRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);

        feiraService = new FeiraService(
                feiraRepository,
                usuarioRepository
        );

        usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(userId);
        when(usuarioRepository.findById(userId))
                .thenReturn(Optional.of(usuario));
    }

    private Feira criarFeiraAtiva() {
        when(feiraRepository.findByUsuarioAndStatus(
                usuario,
                StatusFeira.EM_ANDAMENTO
        )).thenReturn(Optional.empty());

        when(feiraRepository.save(any(Feira.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assaí",
                userId
        );

        assertNotNull(feira);

        ReflectionTestUtils.setField(feira, "id", 10L);

        return feira;
    }

    @Test
    void deveCriarFeiraComDadosInformados() {
        Feira feira = criarFeiraAtiva();

        assertEquals("Feira do mês", feira.getNome());
        assertEquals("Assaí", feira.getSupermercado());

        verify(feiraRepository).save(feira);
    }

    @Test
    void deveCriarFeiraComStatusEmAndamento() {
        Feira feira = criarFeiraAtiva();

        assertEquals(StatusFeira.EM_ANDAMENTO, feira.getStatus());
    }

    @Test
    void naoDeveCriarFeiraSeUsuarioNaoExistir() {
        when(usuarioRepository.findById(99L))
                .thenReturn(Optional.empty());

        Feira feira = feiraService.criarFeira(
                "Feira teste",
                "Assaí",
                99L
        );

        assertNull(feira);
        verify(feiraRepository, never()).save(any(Feira.class));
    }

    @Test
    void naoDeveCriarSegundaFeiraEnquantoHouverUmaEmAndamento() {
        when(feiraRepository.findByUsuarioAndStatus(
                usuario,
                StatusFeira.EM_ANDAMENTO
        )).thenReturn(Optional.of(new Feira("Feira existente", "Assaí")));

        Feira feira = feiraService.criarFeira(
                "Nova feira",
                "Atacadão",
                userId
        );

        assertNull(feira);
        verify(feiraRepository, never()).save(any(Feira.class));
    }

    @Test
    void devePermitirFeirasAtivasParaUsuariosDiferentes() {
        Usuario outroUsuario = mock(Usuario.class);
        when(outroUsuario.getId()).thenReturn(2L);

        when(usuarioRepository.findById(2L))
                .thenReturn(Optional.of(outroUsuario));

        when(feiraRepository.findByUsuarioAndStatus(
                usuario,
                StatusFeira.EM_ANDAMENTO
        )).thenReturn(Optional.of(new Feira("Feira A", "Assaí")));

        when(feiraRepository.findByUsuarioAndStatus(
                outroUsuario,
                StatusFeira.EM_ANDAMENTO
        )).thenReturn(Optional.empty());

        when(feiraRepository.save(any(Feira.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Feira feira = feiraService.criarFeira(
                "Feira B",
                "Atacadão",
                2L
        );

        assertNotNull(feira);
        assertEquals(StatusFeira.EM_ANDAMENTO, feira.getStatus());
        verify(feiraRepository).save(feira);
    }

    @Test
    void deveFinalizarFeira() {
        Feira feira = criarFeiraAtiva();

        assertTrue(feira.finalizar());
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraJaFinalizada() {
        Feira feira = criarFeiraAtiva();

        assertTrue(feira.finalizar());
        assertFalse(feira.finalizar());

        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void deveCancelarFeira() {
        Feira feira = criarFeiraAtiva();

        assertTrue(feira.cancelar());
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void naoDeveCancelarFeiraFinalizada() {
        Feira feira = criarFeiraAtiva();

        assertTrue(feira.finalizar());

        assertFalse(feira.cancelar());
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraCancelada() {
        Feira feira = criarFeiraAtiva();

        assertTrue(feira.cancelar());

        assertFalse(feira.finalizar());
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void deveFinalizarFeiraPorId() {
        Feira feira = criarFeiraAtiva();

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        when(feiraRepository.save(feira)).thenReturn(feira);

        clearInvocations(feiraRepository);

        Feira resultado = feiraService.finalizarFeiraPorId(10L, userId);

        assertNotNull(resultado);
        assertEquals(StatusFeira.FINALIZADA, resultado.getStatus());

        verify(feiraRepository).save(feira);
    }

    @Test
    void naoDeveFinalizarFeiraInexistente() {
        when(feiraRepository.findByIdAndUsuarioId(99L, userId))
                .thenReturn(Optional.empty());

        Feira resultado = feiraService.finalizarFeiraPorId(99L, userId);

        assertNull(resultado);
        verify(feiraRepository, never()).save(any(Feira.class));
    }

    @Test
    void deveCancelarFeiraPorId() {
        Feira feira = criarFeiraAtiva();

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        when(feiraRepository.save(feira)).thenReturn(feira);

        clearInvocations(feiraRepository);

        Feira resultado = feiraService.cancelarFeiraPorId(10L, userId);

        assertNotNull(resultado);
        assertEquals(StatusFeira.CANCELADA, resultado.getStatus());

        verify(feiraRepository).save(feira);
    }

    @Test
    void naoDeveCancelarFeiraInexistente() {
        when(feiraRepository.findByIdAndUsuarioId(99L, userId))
                .thenReturn(Optional.empty());

        Feira resultado = feiraService.cancelarFeiraPorId(99L, userId);

        assertNull(resultado);
        verify(feiraRepository, never()).save(any(Feira.class));
    }

    @Test
    void deveDeletarFeiraCancelada() {
        Feira feira = criarFeiraAtiva();
        assertTrue(feira.cancelar());

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        boolean resultado = feiraService.deletarFeiraPorId(10L, userId);

        assertTrue(resultado);
        verify(feiraRepository).delete(feira);
    }

    @Test
    void naoDeveDeletarFeiraEmAndamento() {
        Feira feira = criarFeiraAtiva();

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        boolean resultado = feiraService.deletarFeiraPorId(10L, userId);

        assertFalse(resultado);
        verify(feiraRepository, never()).delete(any(Feira.class));
    }

    @Test
    void naoDeveDeletarFeiraFinalizada() {
        Feira feira = criarFeiraAtiva();
        assertTrue(feira.finalizar());

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        boolean resultado = feiraService.deletarFeiraPorId(10L, userId);

        assertFalse(resultado);
        verify(feiraRepository, never()).delete(any(Feira.class));
    }

    @Test
    void naoDeveDeletarFeiraInexistente() {
        when(feiraRepository.findByIdAndUsuarioId(99L, userId))
                .thenReturn(Optional.empty());

        boolean resultado = feiraService.deletarFeiraPorId(99L, userId);

        assertFalse(resultado);
        verify(feiraRepository, never()).delete(any(Feira.class));
    }

    @Test
    void deveBuscarFeiraPorIdDoUsuario() {
        Feira feira = criarFeiraAtiva();

        when(feiraRepository.findByIdAndUsuarioId(10L, userId))
                .thenReturn(Optional.of(feira));

        Feira resultado = feiraService.getFeiraPorId(10L, userId);

        assertNotNull(resultado);
        assertEquals("Feira do mês", resultado.getNome());
    }

    @Test
    void naoDeveBuscarFeiraDeOutroUsuario() {
        when(feiraRepository.findByIdAndUsuarioId(10L, 2L))
                .thenReturn(Optional.empty());

        Feira resultado = feiraService.getFeiraPorId(10L, 2L);

        assertNull(resultado);
    }

    @Test
    void deveListarFeirasDoUsuario() {
        Feira feira = criarFeiraAtiva();

        when(feiraRepository.findByUsuarioId(userId))
                .thenReturn(List.of(feira));

        List<Feira> resultado = feiraService.listarFeiras(userId);

        assertEquals(1, resultado.size());
        assertEquals(feira, resultado.get(0));
    }

    @Test
    void deveRetornarListaVaziaQuandoUsuarioNaoPossuirFeiras() {
        when(feiraRepository.findByUsuarioId(userId))
                .thenReturn(List.of());

        List<Feira> resultado = feiraService.listarFeiras(userId);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}