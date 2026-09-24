package com.cestto.cestto.service;

import com.cestto.cestto.domain.Feira;
import com.cestto.cestto.domain.StatusFeira;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        Feira feira = new Feira(1l, "Feira do Mês", "Assai");

        feira.cancelar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void deveFinalizarFeiraEmAndamento() {
        Feira feira = new Feira(1l, "Feira do ano", "Assai");

        boolean resultado = feira.finalizar();

        assertTrue(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraFinalizadaNovamente(){
        Feira feira = new Feira(1l, "Feira do ano", "Assai");

        feira.finalizar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void deveCancelarFeiraEmAndamento() {
        Feira feira = new Feira(1l,"Feira do ano", "Assai");

        boolean resultado = feira.cancelar();

        assertTrue(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void naoDeveCancelarFeiraCanceladaNovamente(){
        Feira feira = new Feira(1l,"Feira do ano", "Assai");
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
        Feira feiraEncontrada = feiraService.getFeiraPorId(1l);

        assertNull(feiraEncontrada);
    }

}
