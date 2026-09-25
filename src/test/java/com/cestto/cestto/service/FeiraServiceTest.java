package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeiraServiceTest {

    @Test
    void deveCriarFeiraComDadosInformados() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        assertEquals("Feira do mês", feira.getNome());
        assertEquals("Assai", feira.getSupermercado());
    }

    @Test
    void deveCriarFeiraComSatusEmAndamento() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        assertEquals(StatusFeira.EM_ANDAMENTO, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraCancelada() {
        Feira feira = new Feira(1L, "Feira do Mês", "Assai");

        feira.cancelar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void deveFinalizarFeiraEmAndamento() {
        Feira feira = new Feira(1L, "Feira do ano", "Assai");

        boolean resultado = feira.finalizar();

        assertTrue(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraFinalizadaNovamente(){
        Feira feira = new Feira(1L, "Feira do ano", "Assai");

        feira.finalizar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void deveCancelarFeiraEmAndamento() {
        Feira feira = new Feira(1L,"Feira do ano", "Assai");

        boolean resultado = feira.cancelar();

        assertTrue(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void naoDeveCancelarFeiraCanceladaNovamente(){
        Feira feira = new Feira(1L,"Feira do ano", "Assai");
        feira.cancelar();

        boolean resultado = feira.cancelar();

        assertFalse(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void naoDeveCriarFeiraSeTiverEmAndamento() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        assertEquals(StatusFeira.EM_ANDAMENTO, feira.getStatus());

        Feira feira2 = feiraService.criarFeira(
                "Feira do ano",
                "mix"
        );

        assertNull(feira2);
    }

    @Test
    void deveCriarNovaFeiraDepoisDeFinalizarAnterior() {

        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        feira.finalizar();

        Feira feira2 = feiraService.criarFeira(
                "Feira do ano",
                "mix"
        );

        assertNotNull(feira2);
    }

    @Test
    void deveCriarNovaFeiraDepoisDeCancelarAnterior() {

        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        feira.cancelar();

        Feira feira2 = feiraService.criarFeira(
                "Feira do ano",
                "mix"
        );

        assertNotNull(feira2);
    }

    @Test
    void deveEncontrarFeiraPeloId() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira feiraEncontrada = feiraService.getFeiraPorId(feira.getId());

        assertNotNull(feiraEncontrada);
        assertEquals(feira.getId(), feiraEncontrada.getId());
    }

    @Test
    void deveRetornarNullQuandoFeiraNaoExiste() {
        FeiraService feiraService = new FeiraService();
        Feira feiraEncontrada = feiraService.getFeiraPorId(1L);

        assertNull(feiraEncontrada);
    }

    @Test
    void deveFinalizarFeiraPorId() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira finalizarFeira = feiraService.finalizarFeiraPorId(feira.getId());

        assertNotNull(finalizarFeira);
        assertEquals(StatusFeira.FINALIZADA, finalizarFeira.getStatus());
    }

    @Test
    void deveCancelarFeiraPorId() {

        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira cancelarFeira = feiraService.cancelarFeiraPorId(feira.getId());

        assertNotNull(cancelarFeira);
        assertEquals(StatusFeira.CANCELADA, cancelarFeira.getStatus());
    }

    @Test
    void naoDeveCancelarFeiraNovamente() {

        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira cancelarFeira = feiraService.cancelarFeiraPorId(feira.getId());

        Feira cancelarFeiraNovamente = feiraService.cancelarFeiraPorId(feira.getId());

        assertNotNull(cancelarFeira);
        assertNull(cancelarFeiraNovamente);
    }

    @Test
    void naoDeveCancelarFeiraFinalizada() {

        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira finalizarFeira = feiraService.finalizarFeiraPorId(feira.getId());

        Feira cancelarFeiraNovamente = feiraService.cancelarFeiraPorId(feira.getId());

        assertNotNull(finalizarFeira);
        assertNull(cancelarFeiraNovamente);
    }

    @Test
    void deveRetornarNullAoCancelarFeiraInexistente() {
        FeiraService feiraService = new FeiraService();

        Feira cancelarFeira = feiraService.cancelarFeiraPorId(1L);

        assertNull(cancelarFeira);
    }

    @Test
    void deveDeletarFeiraCanceladaPorId() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira cancelarFeira = feiraService.cancelarFeiraPorId(feira.getId());

        boolean resultado = feiraService.deletarFeiraPorId(feira.getId());

        assertNotNull(cancelarFeira);
        assertTrue(resultado);
        assertFalse(feiraService.getFeiras().contains(feira));
    }

    @Test
    void naoDeveDeletarFeiraEmAndamentoPorId() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );


        boolean resultado = feiraService.deletarFeiraPorId(feira.getId());

        assertFalse(resultado);
        assertTrue(feiraService.getFeiras().contains(feira));
    }

    @Test
    void naoDeveDeletarFeiraFinalizadaPorId() {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira(
                "Feira do mês",
                "Assai"
        );

        Feira finalizarFeira = feiraService.finalizarFeiraPorId(feira.getId());

        boolean resultado = feiraService.deletarFeiraPorId(feira.getId());

        assertEquals(StatusFeira.FINALIZADA, finalizarFeira.getStatus());
        assertFalse(resultado);
        assertTrue(feiraService.getFeiras().contains(feira));
    }

    @Test
    void deveRetornarFalseAoDeletarFeiraInexistente() {
        FeiraService feiraService = new FeiraService();

        boolean deletarFeira = feiraService.deletarFeiraPorId(1L);

        assertFalse(deletarFeira);
    }
}
