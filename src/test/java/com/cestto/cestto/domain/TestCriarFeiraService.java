package com.cestto.cestto.domain;

import com.cestto.cestto.service.FeiraService;

public class TestCriarFeiraService {
    public static void main(String[] args) {
        FeiraService feiraService = new FeiraService();

        Feira feira = feiraService.criarFeira("Feira de padinho", "Mix matinhas");
        System.out.println("Feira: "+ feira.getNome() + " Supermercado:" + feira.getSupermercado() + " Status:"+ feira.getStatus());
    }
}
