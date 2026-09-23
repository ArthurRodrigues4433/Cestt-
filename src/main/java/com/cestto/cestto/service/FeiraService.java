package com.cestto.cestto.service;

import org.springframework.stereotype.Service;
import com.cestto.cestto.domain.Feira;

@Service
public class FeiraService {

    public Feira criarFeira(String nome, String supermercado) {
        Feira feira = new Feira(nome, supermercado);
        return feira;
    }

}
