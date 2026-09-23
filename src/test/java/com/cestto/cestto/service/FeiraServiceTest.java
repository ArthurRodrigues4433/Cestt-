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
        Feira feira = new Feira("Feira do Mês", "Assai");

        feira.cancelar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.CANCELADA, feira.getStatus());
    }

    @Test
    void deveFinalizarFeiraEmAndamento() {
        Feira feira = new Feira("Feira do ano", "Assai");

        boolean resultado = feira.finalizar();

        assertTrue(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }

    @Test
    void naoDeveFinalizarFeiraFinalizadaNovamente(){
        Feira feira = new Feira("Feira do ano", "Assai");

        feira.finalizar();

        boolean resultado = feira.finalizar();

        assertFalse(resultado);
        assertEquals(StatusFeira.FINALIZADA, feira.getStatus());
    }
}
